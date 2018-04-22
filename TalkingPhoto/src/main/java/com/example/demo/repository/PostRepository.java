package com.example.demo.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.example.demo.entity.Post;
import com.example.demo.entity.UserEntity;

public interface PostRepository extends CrudRepository<Post, Integer> {

	List<Post> findAllByuserObj(UserEntity user);
}
