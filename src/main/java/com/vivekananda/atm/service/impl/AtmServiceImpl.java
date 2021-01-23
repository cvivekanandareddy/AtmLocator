package com.vivekananda.atm.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vivekananda.atm.model.AtmDetails;
import com.vivekananda.atm.repository.AtmRepository;
import com.vivekananda.atm.service.AtmService;

@Service
public class AtmServiceImpl implements AtmService {

	@Autowired
	private AtmRepository atmRepository;

	@Override
	public List<AtmDetails> getAllAtms() {
		return atmRepository.getAllAtmDetails();
	}

	@Override
	public List<AtmDetails> getAtmsByCity(String city) {
		List<AtmDetails> allAtms = atmRepository.getAllAtmDetails();
		return allAtms.stream()
				.filter(atm -> atm.getAddress().getCity().equalsIgnoreCase(city))
				.collect(Collectors.toList());
	}

}
