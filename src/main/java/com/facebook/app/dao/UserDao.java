package com.facebook.app.dao;

import org.springframework.stereotype.Service;

import com.facebook.app.entity.Users;

@Service
public interface UserDao {

	Users findById(String username);
}
