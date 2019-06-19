package com.facebook.app.dao;

import java.util.List;

import org.apache.catalina.User;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import com.facebook.app.entity.Users;

@Service

public interface UserDao {

	Users findById(String facebookId);


	

}
