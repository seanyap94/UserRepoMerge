package com.branch.repo;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.databind.ObjectMapper;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;
import java.util.List;

@ExtendWith(MockitoExtension.class)
public class RepoServiceTest {
	@Mock
	RestTemplate mockRestTemplate;
	

	@Test
	public void testGetRepos() throws Exception {
		RepoService service = new RepoService(mockRestTemplate);
		
		ObjectMapper objectMapper = new ObjectMapper();
		Repo[] repoMock = objectMapper.readValue(this.getClass().getResource("octocatRepos.json"), Repo[].class);
		
		when(mockRestTemplate.getForObject(Mockito.anyString(), Mockito.any())).thenReturn(repoMock);
		
		List<Repo> repos = service.getRepos("octocat");
		
		assertEquals(8, repos.size());
		assertEquals("boysenberry-repo-1", repos.get(0).getName());
		assertEquals("https://github.com/octocat/boysenberry-repo-1", repos.get(0).getUrl());
	}
}
