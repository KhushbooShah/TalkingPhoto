package com.example.demo.entity;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

@Entity
public class Post {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int postId;
	private String postImageURL;
	private String postCaption;
	private String postAudioURL;
	private Date postCrtDate;
	
	@ManyToOne
	@JoinColumn(name="fbIDUser")
	private UserEntity userObj;
	
	@OneToMany(mappedBy="postObj", cascade = CascadeType.ALL)
	private List<Comment> commentList;
	
	private boolean isActive;
	
	@OneToOne(mappedBy="postObjNotification")
	private Notification notificationList;
	
	
	public Notification getNotificationList() {
		return notificationList;
	}
	public void setNotificationList(Notification notificationList) {
		this.notificationList = notificationList;
	}
	public int getPostId() {
		return postId;
	}
	public void setPostId(int postId) {
		this.postId = postId;
	}
	public String getPostImageURL() {
		return postImageURL;
	}
	public void setPostImageURL(String postImageURL) {
		this.postImageURL = postImageURL;
	}
	public String getPostCaption() {
		return postCaption;
	}
	public void setPostCaption(String postCaption) {
		this.postCaption = postCaption;
	}
	public String getPostAudioURL() {
		return postAudioURL;
	}
	public void setPostAudioURL(String postAudioURL) {
		this.postAudioURL = postAudioURL;
	}
	public Date getPostCrtDate() {
		return postCrtDate;
	}
	public void setPostCrtDate(Date postCrtDate) {
		this.postCrtDate = postCrtDate;
	}
	
	public UserEntity getUserObj() {
		return userObj;
	}
	public void setUserObj(UserEntity userObj) {
		this.userObj = userObj;
	}
	public List<Comment> getCommentList() {
		return commentList;
	}
	public void setCommentList(List<Comment> commentList) {
		this.commentList = commentList;
	}
	public boolean isActive() {
		return isActive;
	}
	public void setActive(boolean isActive) {
		this.isActive = isActive;
	}
	
	
	public Post()
	{
		super();
	}
	@Override
	public String toString() {
		return "Post [postImageURL=" + postImageURL + ", postCaption=" + postCaption
				+ ", postAudioURL=" + postAudioURL + ", postCrtDate=" + postCrtDate + ", userObj=" + userObj
				+ ", isActive=" + isActive
				+ "]";
	}
	public Post(String postImageURL, String postCaption, String postAudioURL, Date postCrtDate,
			UserEntity userObj, boolean isActive) {
		super();
		this.postImageURL = postImageURL;
		this.postCaption = postCaption;
		this.postAudioURL = postAudioURL;
		this.postCrtDate = postCrtDate;
		this.userObj = userObj;
		this.isActive = isActive;
	}
	
	
}
