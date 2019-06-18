package com.facebook.app.entity;

import javax.persistence.*;

@Entity
@Table(name="users")
public class Users {

	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;
	private String facebookId;
	private String name ;
	private String gender ;
	private String urlOfImageFile;
	
	public Users(int id, String facebookId, String name, String gender, String urlOfImageFile) {
		super();
		this.id = id;
		this.facebookId = facebookId;
		this.name = name;
		this.gender = gender;
		this.urlOfImageFile = urlOfImageFile;
	}

	public String getFacebookId() {
		return facebookId;
	}

	public void setFacebookId(String facebookId) {
		this.facebookId = facebookId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getUrlOfImageFile() {
		return urlOfImageFile;
	}

	public void setUrlOfImageFile(String urlOfImageFile) {
		this.urlOfImageFile = urlOfImageFile;
	}

	public int getId() {
		return id;
	}

	@Override
	public String toString() {
		return "Users [id=" + id + ", facebookId=" + facebookId + ", name=" + name + ", gender=" + gender
				+ ", urlOfImageFile=" + urlOfImageFile + "]";
	}
	
	
	
	

}
