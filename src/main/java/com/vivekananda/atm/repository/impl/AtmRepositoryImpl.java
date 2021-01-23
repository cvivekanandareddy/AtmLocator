package com.vivekananda.atm.repository.impl;

import java.net.URI;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

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

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.vivekananda.atm.exception.ParseException;
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
		return getObjectListFromJson(atmsResponse.getBody(), AtmDetails.class);
	}

	/**
	 * Used to get ATM's information by the city name
	 * @param city name of the city to filter
	 * return filtered ATM's information will be returned
	 */
	@Override
	@Cacheable(value = "city-atms")
	public List<AtmDetails> getAtmDetailsByCity(String city) {
		log.info("inside of getAtmDetailsByCity() - city: " + city);

		ResponseEntity<String> atmsResponse = restTemplate.exchange(atmInfoUrl, HttpMethod.GET, httpEntity(), String.class);
		List<AtmDetails> allAtms = getObjectListFromJson(atmsResponse.getBody(), AtmDetails.class);

		return allAtms.stream()
				.filter(atm -> atm.getAddress().getCity().equalsIgnoreCase(city))
				.collect(Collectors.toList());
	}

	/**
	 * Used to set accepting media type as application/json in the headers 
	 * @return returns HttpEntity after adding the headers
	 */
	private HttpEntity<String> httpEntity() {
		HttpHeaders headers = new HttpHeaders();
		headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
		
		return new HttpEntity<>(headers);
	}

	/**
	 * This method is used to convert the JSON string as List of the objects of specified class type
	 * @param json JSON string to convert
	 * @param clazz Class name to convert
	 * @return returns converted List of objects
	 * @throws JsonProcessingException
	 */
	private <T> List<T> getObjectListFromJson(String json, Class<T> clazz){
		ObjectMapper mapper = new ObjectMapper();
		mapper = mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
		JavaType type = mapper.getTypeFactory().constructCollectionType(List.class, clazz);
        try {
			return mapper.readValue(trimExtraChars(json), type);
		} catch (JsonProcessingException e) {
			log.error("Unable to parse the json data. " + e.getMessage(), e);
			throw new ParseException(e);
		}
	}

	/**
	 * This method is used to trim extra characters in the string to make it as JSON
	 * @param obj input string to convert JSON string
	 * @return JSON string will be returned
	 */
	private String trimExtraChars(String obj) {
		Objects.requireNonNull(obj, "Object can not be null");
		int endIndexOfSquare = obj.lastIndexOf(']');
		int endIndexOfFlower = obj.lastIndexOf('}');
		int startIndexOfSquare = obj.indexOf('[');
		int startIndexOfFlower = obj.indexOf('{');
		
		String trimmedEndIndexStr = obj.substring(endIndexOfSquare < endIndexOfFlower ? endIndexOfSquare : endIndexOfFlower, obj.length());
		
		return trimmedEndIndexStr.substring(startIndexOfSquare > startIndexOfFlower ? startIndexOfSquare - 1 : startIndexOfFlower - 1, trimmedEndIndexStr.length());
	}

}
