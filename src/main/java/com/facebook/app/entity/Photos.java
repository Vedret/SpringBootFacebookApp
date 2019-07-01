package com.facebook.app.entity;

import java.util.Arrays;
import javax.persistence.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

@Entity
@Table(name = "photos")
public class Photos {

	@Id
	private String photoName;
	private String facebookId;

	@ManyToOne
	@OnDelete(action = OnDeleteAction.CASCADE)
	@JoinColumn(name = "facebookId", referencedColumnName = "facebookId", insertable = false, updatable = false)
	private Users users;

	@Lob
	private byte[] facebookPhoto;
	private String facebookPhotoUrl;

	public Photos() {
	}

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

	@Override
	public String toString() {
		return "Photos [photoName=" + photoName + ", facebookId=" + facebookId + ", users=" + users + ", facebookPhoto="
				+ Arrays.toString(facebookPhoto) + ", facebookPhotoUrl=" + facebookPhotoUrl + "]";
	}

}
