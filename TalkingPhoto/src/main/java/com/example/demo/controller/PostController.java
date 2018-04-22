package com.example.demo.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.Base64;
import java.util.Base64.Decoder;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.example.demo.entity.Post;
import com.example.demo.entity.UserEntity;
import com.example.demo.repository.PostRepository;
import com.example.demo.repository.UserRepository;
import com.example.demo.service.UploadToS3;

@Controller
public class PostController {
	
	@Autowired
	UploadToS3 upload;
	
	@Autowired
	PostRepository postRepo;
	
	@Autowired
	UserRepository userRepo;

	@GetMapping(value="/createPost")
	public ModelAndView renderCreatePost() {
		
		ModelAndView mv = new ModelAndView();
		mv.setViewName("createPost");
		return mv;
	}
	
	@PostMapping(value="/savePost")
	public ModelAndView savePost(
			@RequestParam(name="imagePost") MultipartFile imagePost,
			@RequestParam(name="audioData") String audioData,
			@RequestParam(name="text") String textCaption,
			HttpServletRequest req
			) throws Exception {
		
		if(audioData.isEmpty())
			throw new Exception("No audio data found");
		Decoder decoderObj = Base64.getDecoder();
		byte[] decodedByteObj = decoderObj.decode(audioData.split(",")[1]);
		FileOutputStream fileOutputStreamObj;
		fileOutputStreamObj = new FileOutputStream("AudioTemp.webm");
		fileOutputStreamObj.write(decodedByteObj);
		fileOutputStreamObj.close();
		
		String userFacebookId = (String) req.getSession().getAttribute("loggedUserID");
		
		String imageURL = upload.UploadToS3(imagePost.getOriginalFilename(), imagePost.getInputStream());
		
		Date currentDate = new Date();
		boolean isActive = true;
		
		UserEntity userObj = userRepo.findByFbIDUser(userFacebookId);
		
		/*saving the post initially to generate unique post id : start*/
		Post postObj;
		postObj = new Post(imageURL, textCaption, null, currentDate, userObj, isActive);
		postObj = postRepo.save(postObj);
		/*saving the post initially to generate unique post id : end*/
		
		/*using user's id and post id (generated above) as unique name while uploading audio : start*/
		String postAudioURL = upload.UploadToS3(userFacebookId+"~"+postObj.getPostId()+".webm", new FileInputStream("AudioTemp.webm"));
		postObj.setPostAudioURL(postAudioURL);
		postObj = postRepo.save(postObj);
		/*using user's id and post id (generated above) as unique name while uploading audio : end*/
		
		List<Post> listOfUserPost = postRepo.findAllByuserObj(userObj);
		
		ModelAndView mv = new ModelAndView();
		mv.addObject("postObj", listOfUserPost);
		
		mv.addObject("userObj",userObj);

		mv.setViewName("viewProfile");

		return mv;
	}
	
	@PostMapping(value="/viewPost")
	public ModelAndView viewPost(
			@RequestParam(name="postIdForViewPost")String postId,
			@RequestParam(name="comingFromScreenName") String screenName,
			HttpServletRequest req) {
		
		Post viewPostObj;
		ModelAndView mv = new ModelAndView();
		viewPostObj= postRepo.findOne(Integer.parseInt(postId));
		mv.addObject("viewPostObj", viewPostObj);
		
		if(postId != null && !postId.equalsIgnoreCase("") && screenName.equalsIgnoreCase("viewProfile"))
		{
			mv.setViewName("viewProfile");
		}
		if(postId != null && !postId.equals("") && screenName.equalsIgnoreCase("viewFriends")) {
			mv.setViewName("viewFriends");
		}
		return mv;
		
	}
	
	
}
