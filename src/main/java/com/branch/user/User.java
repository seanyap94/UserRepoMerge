package com.branch.user;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonSetter;

import java.time.LocalDateTime;
import java.util.List;

import com.branch.repo.Repo;

@JsonIgnoreProperties(ignoreUnknown = true)
public class User {
	private String userName;
	private String displayName;
	private String avatar;
	private String geoLocation;
	private String email;
	private String url;
	private LocalDateTime createdAt;
	private List<Repo> repos;
	
	@JsonGetter("user_name")
	public String getUserName() {
		return userName;
	}
	
	@JsonSetter("login")
	public void setUserName(String userName) {
		this.userName = userName;
	}
	
	@JsonGetter("display_name")
	public String getDisplayName() {
		return displayName;
	}
	
	@JsonSetter("name")
	public void setDisplayName(String displayName) {
		this.displayName = displayName;
	}

	@JsonGetter("avatar")
	public String getAvatar() {
		return avatar;
	}
	
	@JsonSetter("avatar_url")
	public void setAvatar(String avatar) {
		this.avatar = avatar;
	}

	@JsonGetter("geo_location")
	public String getGeoLocation() {
		return geoLocation;
	}

	@JsonSetter("location")
	public void setGeoLocation(String geoLocation) {
		this.geoLocation = geoLocation;
	}

	@JsonGetter("email")
	public String getEmail() {
		return email;
	}

	@JsonSetter("avatar")
	public void setEmail(String email) {
		this.email = email;
	}

	@JsonGetter("url")
	public String getUrl() {
		return url;
	}

	@JsonSetter("html_url")
	public void setUrl(String url) {
		this.url = url;
	}

	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
	@JsonGetter("created_at")
	public LocalDateTime getCreatedAt() {
		return createdAt;
	}

	@JsonFormat(pattern="yyyy-MM-dd'T'HH:mm:ss'Z'")
	@JsonSetter("created_at")
	public void setCreatedAt(LocalDateTime createdAt) {
		this.createdAt = createdAt;
	}
	
	@JsonGetter("repos")
	public List<Repo> getRepos() {
		return repos;
	}
	
	@JsonSetter("repos")
	public void setRepos(List<Repo> repos) {
		this.repos = repos;
	}
}
