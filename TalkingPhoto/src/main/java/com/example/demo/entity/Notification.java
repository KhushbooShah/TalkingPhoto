package com.example.demo.entity;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

@Entity
public class Notification {

	@Id
	private int notificationId;
	
	@ManyToOne
	@JoinColumn(name="fbIDUser")
	private UserEntity userObj;
	
	@OneToOne
	@JoinColumn(name="postId")
	private Post postObjNotification;
	
	@OneToOne
	@JoinColumn(name="commentId")
	private Comment commentObj;
	
	private Date notiCrtDate;

	public int getNotificationId() {
		return notificationId;
	}

	public void setNotificationId(int notificationId) {
		this.notificationId = notificationId;
	}

	public UserEntity getUserObj() {
		return userObj;
	}

	public void setUserObj(UserEntity userObj) {
		this.userObj = userObj;
	}

	


	public Post getPostObjNotification() {
		return postObjNotification;
	}

	public void setPostObjNotification(Post postObjNotification) {
		this.postObjNotification = postObjNotification;
	}

	public Date getNotiCrtDate() {
		return notiCrtDate;
	}

	public void setNotiCrtDate(Date notiCrtDate) {
		this.notiCrtDate = notiCrtDate;
	}


	public Comment getCommentObj() {
		return commentObj;
	}

	public void setCommentObj(Comment commentObj) {
		this.commentObj = commentObj;
	}

	public Notification() {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	public String toString() {
		return "Notification [notificationId=" + notificationId + ", userObj=" + userObj + ", postObjNotification="
				+ postObjNotification + ", commentObj=" + commentObj + ", notiCrtDate=" + notiCrtDate + "]";
	}

	public Notification(int notificationId, UserEntity userObj, Post postObjNotification, Comment commentObj,
			Date notiCrtDate) {
		super();
		this.notificationId = notificationId;
		this.userObj = userObj;
		this.postObjNotification = postObjNotification;
		this.commentObj = commentObj;
		this.notiCrtDate = notiCrtDate;
	}

	
	
	
	
}
