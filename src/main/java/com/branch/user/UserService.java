package com.branch.user;

import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

@Service
public class UserService {
	private final RestTemplate restTemplate;
	private static final String GITHUB_USER_API = "https://api.github.com/users/";
	private static final String REPOS = "repos";
	
	public UserService(RestTemplate restTemplate) {
		this.restTemplate = restTemplate;
	}
	
	public User getUser(String username) throws HttpClientErrorException {
		return restTemplate.getForObject(GITHUB_USER_API + username, User.class);
	}
}
