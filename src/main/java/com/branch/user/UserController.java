package com.branch.user;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.HttpClientErrorException;

import com.branch.repo.RepoService;

@RestController
@RequestMapping("/user")
public class UserController {
	private final UserService userService;
	private final RepoService repoService;
	
	public UserController(UserService userService, RepoService repoService) {
		this.userService = userService;
		this.repoService = repoService;
	}
	
	@GetMapping("/{username}")
	public ResponseEntity<Object> getUser(@PathVariable String username) {
		ResponseEntity<Object> response;
		
		try {
			User user = userService.getUser(username);
			user.setRepos(repoService.getRepos(username));
			
			response = ResponseEntity.status(HttpStatus.OK).body(user);
		} catch (HttpClientErrorException exception) {
			response = ResponseEntity.status(exception.getStatusCode()).body(exception.getResponseBodyAsString());
		}
		
		return response;
	}
}