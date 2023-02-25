package com.branch.user;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

@ExtendWith(MockitoExtension.class)
public class UserServiceTest {
	@Mock
	RestTemplate mockRestTemplate;
	

	@Test
	public void testGetUser() throws Exception {
		UserService service = new UserService(mockRestTemplate);
		
		ObjectMapper objectMapper = new ObjectMapper();
		objectMapper.registerModule(new JavaTimeModule());
		User userMock = objectMapper.readValue(this.getClass().getResource("octocatUser.json"), User.class);
		
		when(mockRestTemplate.getForObject(Mockito.anyString(), Mockito.any())).thenReturn(userMock);
		
		User user = service.getUser("octocat");
		
		assertEquals("octocat", user.getUserName());
		assertEquals("The Octocat", user.getDisplayName());
		assertEquals("https://avatars.githubusercontent.com/u/583231?v=4", user.getAvatar());
		assertEquals("San Francisco", user.getGeoLocation());
		assertNull(user.getEmail());
		assertEquals("https://github.com/octocat", user.getUrl());
		assertEquals("2011-01-25T18:44:36", user.getCreatedAt().toString());
		assertNull(user.getRepos());
	}
}
