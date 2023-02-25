package com.branch.user;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.branch.repo.Repo;
import com.branch.repo.RepoService;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

@ExtendWith(MockitoExtension.class)
public class UserControllerTest {
	@Mock
	UserService mockUserService;
	
	@Mock
	RepoService mockRepoService;
	

	@Test
	public void testGetUser() throws Exception {
		UserController userController = new UserController(mockUserService, mockRepoService);
		
		ObjectMapper objectMapper = new ObjectMapper();
		objectMapper.registerModule(new JavaTimeModule());
		
		User userMock = objectMapper.readValue(mockUserService.getClass().getResource("octocatUser.json"), User.class);
		Repo[] repoMock = objectMapper.readValue(mockRepoService.getClass().getResource("octocatRepos.json"), Repo[].class);
		
		when(mockUserService.getUser("octocat")).thenReturn(userMock);
		when(mockRepoService.getRepos("octocat")).thenReturn(Arrays.asList(repoMock));
		
		ResponseEntity<Object> response = userController.getUser("octocat");
		
		User user = (User) response.getBody();
		
		assertEquals(HttpStatus.OK, response.getStatusCode());
		assertEquals("octocat", user.getUserName());
		assertEquals("The Octocat", user.getDisplayName());
		assertEquals("https://avatars.githubusercontent.com/u/583231?v=4", user.getAvatar());
		assertEquals("San Francisco", user.getGeoLocation());
		assertNull(user.getEmail());
		assertEquals("https://github.com/octocat", user.getUrl());
		assertEquals("2011-01-25T18:44:36", user.getCreatedAt().toString());
		
		List<Repo> repos = user.getRepos();
		
		assertEquals(8, repos.size());
		assertEquals("boysenberry-repo-1", repos.get(0).getName());
		assertEquals("https://github.com/octocat/boysenberry-repo-1", repos.get(0).getUrl());
	}
}
