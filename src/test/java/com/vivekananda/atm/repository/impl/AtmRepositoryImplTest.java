package com.vivekananda.atm.repository.impl;

import static org.junit.Assert.assertNull;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import com.vivekananda.atm.model.AtmDetails;

class AtmRepositoryImplTest {

	@InjectMocks
	private AtmRepositoryImpl repository;
	
	@Mock
	private RestTemplate restTemplate;

	@BeforeEach
	public void setUp() {
		MockitoAnnotations.openMocks(this);
	}

	@Test
	void getAllAtmDetailsTest() {
		List<AtmDetails> atmList = null;
		try {
			atmList = repository.getAllAtmDetails();
		} catch (Exception e) {
		}
		assertNull(atmList);
	}

	@Test
	void getAllAtmsTest2() {
		Mockito.when(restTemplate.exchange("https://www.ing.nl/api/locator/atms/", HttpMethod.GET, httpEntity(), String.class)).thenReturn(ResponseEntity.ok("abc"));
		List<AtmDetails> atmList = null;
		try {
			atmList = repository.getAllAtmDetails();
		} catch (Exception e) {
		}
		assertNull(atmList);
	}

	private HttpEntity<String> httpEntity() {
		HttpHeaders headers = new HttpHeaders();
		headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
		
		return new HttpEntity<>(headers);
	}
}
