package com.vivekananda.atm.repository.impl;

import static com.vivekananda.atm.util.AppUtils.getObjectListFromJson;
import static com.vivekananda.atm.util.AppUtils.trimExtraChars;

import java.net.URI;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import org.springframework.web.client.RestTemplate;

import com.vivekananda.atm.model.AtmDetails;
import com.vivekananda.atm.repository.AtmRepository;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Repository
public class AtmRepositoryImpl implements AtmRepository {

	@Value("${api.atm.url}")
	private URI atmInfoUrl;
	
	@Autowired
	private RestTemplate restTemplate;
	
	/**
	 * Used to get all ATM's information
	 * return returns all ATM's information
	 */
	@Override
	@Cacheable("atms")
	public List<AtmDetails> getAllAtmDetails() {
		log.info("inside of getAllAtmDetails()");
		
		ResponseEntity<String> atmsResponse = restTemplate.exchange(atmInfoUrl, HttpMethod.GET, httpEntity(), String.class);
		String jsonStr = trimExtraChars(atmsResponse.getBody());
		return getObjectListFromJson(jsonStr, AtmDetails.class);
	}

	/**
	 * Used to get ATM's information by the city name
	 * @param city name of the city to filter
	 * return filtered ATM's information will be returned
	 */
	/* @Override
	@Cacheable(value = "city-atms")
	public List<AtmDetails> getAtmDetailsByCity(String city) {
		log.info("inside of getAtmDetailsByCity() - city: " + city);

		ResponseEntity<String> atmsResponse = restTemplate.exchange(atmInfoUrl, HttpMethod.GET, httpEntity(), String.class);
		String jsonStr = trimExtraChars(atmsResponse.getBody());
		List<AtmDetails> allAtms = getObjectListFromJson(jsonStr, AtmDetails.class);

		return allAtms.stream()
				.filter(atm -> atm.getAddress().getCity().equalsIgnoreCase(city))
				.collect(Collectors.toList());
	} */

	/**
	 * Used to set accepting media type as application/json in the headers 
	 * @return returns HttpEntity after adding the headers
	 */
	private HttpEntity<String> httpEntity() {
		HttpHeaders headers = new HttpHeaders();
		headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
		
		return new HttpEntity<>(headers);
	}

}
