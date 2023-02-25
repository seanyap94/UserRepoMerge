package com.branch.repo;

import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonSetter;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Repo {
	private String name;
	private String url;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	@JsonGetter("url")
	public String getUrl() {
		return url;
	}

	@JsonSetter("html_url")
	public void setUrl(String url) {
		this.url = url;
	}
}
