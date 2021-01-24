package com.vivekananda.atm.repository;

import java.util.List;

import com.vivekananda.atm.model.AtmDetails;

public interface AtmRepository {

	List<AtmDetails> getAllAtmDetails();
	//List<AtmDetails> getAtmDetailsByCity(String city);
}
