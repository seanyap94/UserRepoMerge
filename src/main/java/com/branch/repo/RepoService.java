package com.branch.repo;

import java.util.Arrays;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class RepoService {
	private final RestTemplate restTemplate;
	private static final String GITHUB_USER_API = "https://api.github.com/users/";
	private static final String REPOS_API = "/repos";
	
	public RepoService(RestTemplate restTemplate) {
		this.restTemplate = restTemplate;
	}
	
	public List<Repo> getRepos(String username) {
		return Arrays.asList(restTemplate.getForObject(GITHUB_USER_API + username + REPOS_API, Repo[].class));
	}
}
