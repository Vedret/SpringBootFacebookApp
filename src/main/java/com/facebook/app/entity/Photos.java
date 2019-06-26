package com.facebook.app.entity;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;

@Entity
@Table(name="photos")
public class Photos {
	
	@Id
	private String photoName;
	private String facebookId ;
	
	@Column(nullable = false)
    @Lob
    private byte[] facebookPhoto;
	private String facebookPhotoUrl  ;
	
	public Photos() {}

	

	public String getFacebookId() {
		return facebookId;
	}

	public void setFacebookId(String facebookId) {
		this.facebookId = facebookId;
	}

	public byte[] getFacebookPhoto() {
		return facebookPhoto;
	}

	public void setFacebookPhoto(byte[] facebookPhoto) {
		this.facebookPhoto = facebookPhoto;
	}

	public String getFacebookPhotoUrl() {
		return facebookPhotoUrl;
	}

	public void setFacebookPhotoUrl(String facebookPhotoUrl) {
		this.facebookPhotoUrl = facebookPhotoUrl;
	}



	public String getPhotoName() {
		return photoName;
	}



	public void setPhotoName(String photoName) {
		this.photoName = photoName;
	}
	
	
	
	
	
	
	
	
	

}
