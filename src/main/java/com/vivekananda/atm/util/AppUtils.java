package com.vivekananda.atm.util;

import java.util.List;
import java.util.Objects;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.vivekananda.atm.exception.ParseException;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class AppUtils {
	
	private static final ObjectMapper mapper = new ObjectMapper()
			.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

	private AppUtils() {}

	/**
	 * This method is used to convert the JSON string as List of the objects of specified class type
	 * @param jsonStr JSON string to convert
	 * @param clazz Class name to convert
	 * @return returns converted List of objects
	 * @throws JsonProcessingException
	 */
	public static final <T> List<T> getObjectListFromJson(String jsonStr, Class<T> clazz){
		JavaType type = mapper.getTypeFactory().constructCollectionType(List.class, clazz);
        try {
			return mapper.readValue(jsonStr, type);
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
	public static final String trimExtraChars(String obj) {
		Objects.requireNonNull(obj, "Object can not be null");
		int endIndexOfSquare = obj.lastIndexOf(']');
		int endIndexOfFlower = obj.lastIndexOf('}');

		int endStartIndex = endIndexOfSquare > endIndexOfFlower ? endIndexOfSquare : endIndexOfFlower;
		String endTrimmedStr = obj.substring(0, endStartIndex + 1);

		int startIndexOfSquare = obj.indexOf('[');
		int startIndexOfFlower = obj.indexOf('{');
		int startIndex = startIndexOfSquare < startIndexOfFlower ? startIndexOfSquare : startIndexOfFlower;
		
		return endTrimmedStr.substring(startIndex - 1, endTrimmedStr.length());
	}

}
