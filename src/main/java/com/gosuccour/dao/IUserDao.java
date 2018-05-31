package com.gosuccour.dao;

import org.springframework.data.repository.CrudRepository;

import com.gosuccour.entity.User;


public interface IUserDao extends CrudRepository<User, Long>{
	public User findByUsername(String username);
	
}
