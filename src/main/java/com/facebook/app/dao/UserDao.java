package com.facebook.app.dao;
import org.springframework.social.facebook.api.PagedList;
import org.springframework.social.facebook.api.Post;
import org.springframework.stereotype.Service;

import com.facebook.app.entity.Photos;
import com.facebook.app.entity.Users;

@Service

public interface UserDao {

	Users findById(String facebookId);
	boolean isUserExist(Users user);
	void saveUser(Users user);
	void deleteUser(String facebookId);
	void saveImage (PagedList<Post> posts)  ;
	Photos findPhotosById(String facebookId);
}
