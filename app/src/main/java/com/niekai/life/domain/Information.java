package com.niekai.life.domain;

import java.io.Serializable;

public class Information implements Serializable {

	private String title;
	private String digest;
	private String imgSrc;
	private String url;

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDigest() {
		return digest;
	}

	public void setDigest(String digest) {
		this.digest = digest;
	}

	public String getImgSrc() {
		return imgSrc;
	}

	public void setImgSrc(String imgSrc) {
		this.imgSrc = imgSrc;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public Information(String title, String digest, String imgSrc, String url) {
		this.title = title;
		this.digest = digest;
		this.imgSrc = imgSrc;
		this.url = url;
	}

}
