package com.vivekananda.atm.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.vivekananda.atm.model.AtmDetails;

@Service
public interface AtmService {

	List<AtmDetails> getAllAtms();
	List<AtmDetails> getAtmsByCity(String city);
}
