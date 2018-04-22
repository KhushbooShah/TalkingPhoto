package com.example.demo.entity;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

@Entity
public class Comment {

	@Id
	private int commentId;
	private String commentText;
	
	@ManyToOne
	@JoinColumn(name="fbIDUser")
	private UserEntity userObj;
	
	@ManyToOne
	@JoinColumn(name="postId")
	private Post postObj;
	
	private Date commentCrtDate;
	
	@OneToOne(mappedBy="commentObj", cascade = CascadeType.ALL)
	private Notification notificationList;

	public int getCommentId() {
		return commentId;
	}

	public void setCommentId(int commentId) {
		this.commentId = commentId;
	}

	public String getCommentText() {
		return commentText;
	}

	public void setCommentText(String commentText) {
		this.commentText = commentText;
	}

	public UserEntity getUserObj() {
		return userObj;
	}

	public void setUserObj(UserEntity userObj) {
		this.userObj = userObj;
	}

	public Post getPostObj() {
		return postObj;
	}

	public void setPostObj(Post postObj) {
		this.postObj = postObj;
	}

	public Date getCommentCrtDate() {
		return commentCrtDate;
	}

	public void setCommentCrtDate(Date commentCrtDate) {
		this.commentCrtDate = commentCrtDate;
	}


	public Notification getNotificationList() {
		return notificationList;
	}

	public void setNotificationList(Notification notificationList) {
		this.notificationList = notificationList;
	}

	public Comment() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Comment(int commentId, String commentText, UserEntity userObj, Post postObj, Date commentCrtDate,
			Notification notificationList) {
		super();
		this.commentId = commentId;
		this.commentText = commentText;
		this.userObj = userObj;
		this.postObj = postObj;
		this.commentCrtDate = commentCrtDate;
		this.notificationList = notificationList;
	}

	@Override
	public String toString() {
		return "Comment [commentId=" + commentId + ", commentText=" + commentText + ", userObj=" + userObj
				+ ", postObj=" + postObj + ", commentCrtDate=" + commentCrtDate + ", notificationList="
				+ notificationList + "]";
	}




}
