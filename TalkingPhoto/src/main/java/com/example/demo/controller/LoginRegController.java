package com.example.demo.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.example.demo.entity.Post;
import com.example.demo.entity.UserEntity;
import com.example.demo.entity.UserFriendMapping;
import com.example.demo.repository.PostRepository;
import com.example.demo.repository.UserRepository;



@Controller
public class LoginRegController {
	
	@Autowired
	UserRepository loginRegRepo;
	
	@Autowired
	PostRepository postRepo;

	@GetMapping
	public ModelAndView renderIndexPage() {
		return new ModelAndView("index");
	}

	

	@PostMapping(value="/redirectForm")
	public ModelAndView setUserDetails(
			@RequestParam(name="facebookId") String facebookId,
			@RequestParam(name="facebookName") String facebookName,
			@RequestParam(name="facebookEmail") String facebookEmail,
			@RequestParam(value="friendId") String friendId,
			HttpServletRequest req) throws Exception{
		
			
		
		req.getSession().setAttribute("loggedUserID", facebookId);
		
		
		UserEntity user;
		user= loginRegRepo.findByFbIDUser(facebookId);
		
		if(user != null && user.getFbIDUser()!=null) {
			
			ModelAndView createProfileView = new ModelAndView();
			createProfileView.addObject("userObj",user);
			if(user.isProfileCreated()) {
			
				List<Post> listOfUserPost = postRepo.findAllByuserObj(user);
				createProfileView.addObject("postObj", listOfUserPost);
				createProfileView.setViewName("viewProfile");
			}
			else
				createProfileView.setViewName("createProfile");
			return createProfileView;
			
		}
		else
		{
			List<UserFriendMapping> friendList = new ArrayList<UserFriendMapping>();
			user = new UserEntity(facebookId, facebookName, facebookEmail, null, null, false,friendList,null,null,null);
			
			
			
			JSONArray arr = new JSONArray(friendId);
			for(int i=0;i<arr.length();i++) {
				JSONObject var = (JSONObject)arr.get(i);
				UserFriendMapping friends = new UserFriendMapping(var.getString("id"));
				friends.setUserObj(user);
				user.getFriendList().add(friends);
			}
			
			
			loginRegRepo.save(user);
			
			
			ModelAndView createProfileView = new ModelAndView();
			createProfileView.addObject("userObj",user);
			createProfileView.setViewName("createProfile");
			return createProfileView;
			
		}
		
		
	}
}
