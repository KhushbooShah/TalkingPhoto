package com.example.demo.entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;


@Entity
public class UserEntity {

	@Id
	private String fbIDUser;
	private String name;
	
	@Column(unique=true)
	private String email;
	private String description;
	private String displayImageURL;
	
	private boolean profileCreated;
	@OneToMany(mappedBy="userObj", cascade = CascadeType.ALL)
	private List<UserFriendMapping> friendList;

	
	@OneToMany(mappedBy="userObj", cascade = CascadeType.ALL)
	private List<Post> postList;
	
	@OneToMany(mappedBy="userObj", cascade = CascadeType.ALL)
	private List<Comment> commentList;

	@OneToMany(mappedBy="userObj", cascade = CascadeType.ALL)
	private List<Notification> notificationList;
	
	public String getFbIDUser() {
		return fbIDUser;
	}

	public void setFbIDUser(String fbIDUser) {
		this.fbIDUser = fbIDUser;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getDisplayImageURL() {
		return displayImageURL;
	}

	public void setDisplayImageURL(String displayImageURL) {
		this.displayImageURL = displayImageURL;
	}

	public List<UserFriendMapping> getFriendList() {
		return friendList;
	}

	public void setFriendList(List<UserFriendMapping> friendList) {
		this.friendList = friendList;
	}

	
	public boolean isProfileCreated() {
		return profileCreated;
	}

	public void setProfileCreated(boolean profileCreated) {
		this.profileCreated = profileCreated;
	}

	
	public List<Post> getPostList() {
		return postList;
	}

	public void setPostList(List<Post> postList) {
		this.postList = postList;
	}

	public List<Comment> getCommentList() {
		return commentList;
	}

	public void setCommentList(List<Comment> commentList) {
		this.commentList = commentList;
	}

	public List<Notification> getNotificationList() {
		return notificationList;
	}

	public void setNotificationList(List<Notification> notificationList) {
		this.notificationList = notificationList;
	}

	public UserEntity() {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	public String toString() {
		return "UserEntity [fbIDUser=" + fbIDUser + ", name=" + name + ", email=" + email + ", description="
				+ description + ", displayImageURL=" + displayImageURL + ", profileCreated=" + profileCreated
				+ ", friendList=" + friendList + ", postList=" + postList + ", commentList=" + commentList
				+ ", notificationList=" + notificationList + "]";
	}

	public UserEntity(String fbIDUser, String name, String email, String description, String displayImageURL,
			boolean profileCreated, List<UserFriendMapping> friendList, List<Post> postList, List<Comment> commentList,
			List<Notification> notificationList) {
		super();
		this.fbIDUser = fbIDUser;
		this.name = name;
		this.email = email;
		this.description = description;
		this.displayImageURL = displayImageURL;
		this.profileCreated = profileCreated;
		this.friendList = friendList;
		this.postList = postList;
		this.commentList = commentList;
		this.notificationList = notificationList;
	}

 
	
	
	}
