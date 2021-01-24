package com.vivekananda.atm.service.impl;

import static org.junit.Assert.assertNotNull;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.vivekananda.atm.model.AtmDetails;
import com.vivekananda.atm.repository.AtmRepository;

class AtmServiceImplTest {

	@InjectMocks
	private AtmServiceImpl service;
	
	@Mock
	private AtmRepository repository;

	@BeforeEach
	public void setUp() {
		MockitoAnnotations.openMocks(this);
	}

	@Test
	void getAllAtmsTest() {
		List<AtmDetails> atmList = service.getAllAtms();
		assertNotNull(atmList);
	}

	@Test
	void getAtmsByCityTest() {
		List<AtmDetails> atmList = service.getAtmsByCity("test");
		assertNotNull(atmList);
	}
}
