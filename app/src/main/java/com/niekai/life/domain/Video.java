package com.niekai.life.domain;

import java.io.Serializable;

public class Video implements Serializable{
	private String title;
	private String description;
	private String cover;
	private String videoUrl;

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescribtion(String description) {
		this.description = description;
	}

	public String getCover() {
		return cover;
	}

	public void setCover(String cover) {
		this.cover = cover;
	}

	public String getVideoUrl() {
		return videoUrl;
	}

	public void setVideoUrl(String videoUrl) {
		this.videoUrl = videoUrl;
	}

	public Video(String title, String description, String cover, String videoUrl) {
		this.title = title;
		this.description = description;
		this.cover = cover;
		this.videoUrl = videoUrl;
	}

	public Video() {
	}
	
}
