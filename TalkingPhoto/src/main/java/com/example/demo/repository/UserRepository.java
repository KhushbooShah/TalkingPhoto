package com.example.demo.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import com.example.demo.entity.UserEntity;

@Repository
public interface UserRepository extends CrudRepository<UserEntity, Integer>{
	public UserEntity findByEmail(String email);
	public List<UserEntity> findAll();
	public UserEntity findByFbIDUser(String id);
	public List<UserEntity> findByFbIDUserIn(List<String> friendId);

}
