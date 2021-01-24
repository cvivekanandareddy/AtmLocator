package com.vivekananda.atm.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.ResponseEntity;

import com.vivekananda.atm.exception.ParseException;
import com.vivekananda.atm.model.AtmDetails;
import com.vivekananda.atm.service.AtmService;

@RunWith(MockitoJUnitRunner.class)
class AtmInfoContollerMockTest {

	@Mock
	private AtmService service;
	
	@InjectMocks
	private AtmInfoContoller controller;

	@BeforeEach
	public void setUp() {
		MockitoAnnotations.openMocks(this);
	}

	@Test
	void getAllAtmsInfoTest1() throws Exception {
		ResponseEntity<List<AtmDetails>> response = controller.getAllAtmsInfo();
		assertNotNull(response);
		assertEquals(200, response.getStatusCodeValue());
		assertEquals(0, response.getBody().size());
	}

	@Test
	void getAllAtmsInfoByCityTest1() throws Exception {
		ResponseEntity<List<AtmDetails>> response = controller.getAtmsInfoByCity("test");
		assertNotNull(response);
		assertEquals(200, response.getStatusCodeValue());
		assertEquals(0, response.getBody().size());
	}

	@Test
	void getAllAtmsInfoByCityTest2() throws Exception {
		Mockito.when(service.getAllAtms()).thenThrow(ParseException.class);;
		ResponseEntity<List<AtmDetails>> response = controller.getAtmsInfoByCity("test");
		assertNotNull(response);
		assertEquals(200, response.getStatusCodeValue());
		assertEquals(0, response.getBody().size());
	}
}
