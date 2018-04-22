package com.example.demo.controller;

import java.util.ArrayList;
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
import com.example.demo.entity.UserFriendMapping;
import com.example.demo.repository.FriendRepository;
import com.example.demo.repository.PostRepository;
import com.example.demo.repository.UserRepository;
import com.example.demo.service.UploadToS3;

@Controller
public class ProfileController {
	
	@Autowired
	UserRepository repo;

	@Autowired
	FriendRepository friendRepo;

	@Autowired
	PostRepository postRepo;
	
	@Autowired
	UploadToS3 upload;
	
	
	@PostMapping(value="/saveProfile")
	public ModelAndView saveProfileDetails(
			@RequestParam("imageDp") MultipartFile displayPic,
			@RequestParam(name="name") String userName,
			@RequestParam(name="desc") String description,
			HttpServletRequest req) throws Exception {
		
		String userFacebookId = (String) req.getSession().getAttribute("loggedUserID");
		UserEntity user = repo.findByFbIDUser(userFacebookId);
		
		if(displayPic != null && displayPic.getInputStream() != null && !displayPic.getOriginalFilename().equalsIgnoreCase("")) {
			String imageURL = upload.UploadToS3(displayPic.getOriginalFilename(), displayPic.getInputStream());
			user.setDisplayImageURL(imageURL != null ? imageURL : user.getDisplayImageURL());
		}
		
		user.setName(userName != null ? userName : user.getName());
		user.setDescription(description != null ? description : user.getDescription());
		
		user.setProfileCreated(true);
		repo.save(user);
		
		ModelAndView mv = new ModelAndView();
		mv.addObject("userObj",user);
		mv.setViewName("viewProfile");
		return mv;
	}
	
	
	@GetMapping(value="/viewProfile")
	public ModelAndView viewProfile(
			HttpServletRequest req) throws Exception {

		String userFacebookId = (String) req.getSession().getAttribute("loggedUserID");
		UserEntity user = repo.findByFbIDUser(userFacebookId);
		List<Post> listOfUserPost = postRepo.findAllByuserObj(user);
		
		
		ModelAndView mv = new ModelAndView();
		mv.addObject("userObj",user);
		mv.addObject("postObj", listOfUserPost);

		mv.setViewName("viewProfile");
		return mv;
	}
	
	
	@GetMapping(value="/viewFriends")
	public ModelAndView renderFriendList(HttpServletRequest req) {

		String userFacebookId = (String) req.getSession().getAttribute("loggedUserID");
		UserEntity user = repo.findByFbIDUser(userFacebookId);
		List<UserFriendMapping> friend = friendRepo.findByuserObj(user);
		
		List<String> friendId = new ArrayList<String>();
		for (UserFriendMapping userFriendMapping : friend) {
			friendId.add(userFriendMapping.getFbIDFriend());
		}
		List<UserEntity> friendsProfileInfo = (List<UserEntity>) repo.findByFbIDUserIn(friendId);
		ModelAndView mv = new ModelAndView();
		mv.addObject("friends", friendsProfileInfo);
		mv.setViewName("viewFriends");
		return mv;
	}
	
	@PostMapping(value="/viewFriendProfile")
	public ModelAndView viewFriendProfile(
			@RequestParam(name="friendId")String friendId,
			HttpServletRequest req) {
		
		ModelAndView createProfileView = new ModelAndView();

		UserEntity friendProfile = repo.findByFbIDUser(friendId);
		List<Post> listOfFriendPost = postRepo.findAllByuserObj(friendProfile);
		
		createProfileView.addObject("postObj", listOfFriendPost);
		createProfileView.addObject("userObj",friendProfile);

		createProfileView.setViewName("viewFriends");

		return createProfileView;
	}
	
	@PostMapping(value="/editProfile")
	public ModelAndView editProfile(
		@RequestParam(name="editProfileUser") String userId,	
			HttpServletRequest req) {
		UserEntity user = repo.findByFbIDUser(userId);
		ModelAndView mv = new ModelAndView();
		mv.addObject("userObj",user);
		mv.setViewName("createProfile");
		return mv;

	}
}
