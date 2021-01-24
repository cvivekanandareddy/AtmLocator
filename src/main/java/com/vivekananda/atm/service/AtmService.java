package com.vivekananda.atm.service;

import java.util.List;

import com.vivekananda.atm.model.AtmDetails;

public interface AtmService {

	List<AtmDetails> getAllAtms();
	List<AtmDetails> getAtmsByCity(String city);
}
