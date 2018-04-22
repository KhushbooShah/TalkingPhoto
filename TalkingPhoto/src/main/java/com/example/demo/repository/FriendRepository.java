package com.example.demo.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.CompositeKey;
import com.example.demo.entity.UserEntity;
import com.example.demo.entity.UserFriendMapping;


@Repository

public interface FriendRepository extends CrudRepository<UserFriendMapping, CompositeKey>{

	List<UserFriendMapping> findByuserObj(UserEntity user);
	

}
