package com.niekai.life.domain;

import java.io.Serializable;

public class Joke implements Serializable {

	private String author;
	private String content;
	private String picUrl;
	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getPicUrl() {
		return picUrl;
	}
	public void setPicUrl(String picUrl) {
		this.picUrl = picUrl;
	}
	public Joke(String author, String content, String picUrl) {
		this.author = author;
		this.content = content;
		this.picUrl = picUrl;
	}
	
}
