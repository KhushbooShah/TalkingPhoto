package com.example.demo.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
@IdClass(CompositeKey.class)
public class UserFriendMapping {

	@Id
	@ManyToOne
	@JoinColumn(name="fbIDUser")
	private UserEntity userObj;
	@Id
	private String fbIDFriend;
	
	public UserEntity getUserObj() {
		return userObj;
	}
	public void setUserObj(UserEntity userObj) {
		this.userObj = userObj;
	}
	public String getFbIDFriend() {
		return fbIDFriend;
	}
	public void setFbIDFriend(String fbIDFriend) {
		this.fbIDFriend = fbIDFriend;
	}
	@Override
	public String toString() {
		return "UserFriendMapping [fbIDUser=" + userObj + ", fbIDFriend=" + fbIDFriend + "]";
	}
	
	
	
	public UserFriendMapping(String fbIDFriend) {
		super();
		this.fbIDFriend = fbIDFriend;
	}
	public UserFriendMapping()
	{
		super();
	}
}
