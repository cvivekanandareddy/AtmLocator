package com.vivekananda.atm.repository;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.vivekananda.atm.model.AtmDetails;

@Repository
public interface AtmRepository {

	List<AtmDetails> getAllAtmDetails();
	List<AtmDetails> getAtmDetailsByCity(String city);
}
