package com.vivekananda.atm.controller;

import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.vivekananda.atm.model.AtmDetails;
import com.vivekananda.atm.util.AppUtils;

@SpringBootTest
@WebAppConfiguration
class AtmInfoContollerTest {

	private MockMvc mockMvc;
	private String API_BASE_URL = "/api/atmInfo";
	private List<String> cities = Arrays.asList("Delft", "Lichtenvoorde", "VLAARDINGEN", "Druten", "Houten", "Maarssen", "Woudsend", "Amsterdam", "Schoonhoven", "Heerlen");

	@Autowired
	protected WebApplicationContext wac;

	@BeforeEach
	public void setUp() throws Exception {
		this.mockMvc = MockMvcBuilders.webAppContextSetup(wac).build();
	}

	@Test
	void getAllAtmsInfoTest() throws Exception {
		String content = this.mockMvc
				.perform(MockMvcRequestBuilders.get(API_BASE_URL + "/all").contentType(MediaType.APPLICATION_JSON_VALUE))
				.andExpect(MockMvcResultMatchers.status().isOk()).andReturn().getResponse().getContentAsString();
		List<AtmDetails> atmList = AppUtils.getObjectListFromJson(content, AtmDetails.class);

		assertNotNull(atmList);
		assertNotEquals(atmList.size(), 0);
	}

	@Test
	@RepeatedTest(value = 9)
	void getAllAtmsInfoByCityTest() throws Exception {
		String content = this.mockMvc
				.perform(MockMvcRequestBuilders.get(API_BASE_URL + "/byCity/" + getRandomString()).contentType(MediaType.APPLICATION_JSON_VALUE))
				.andExpect(MockMvcResultMatchers.status().isOk()).andReturn().getResponse().getContentAsString();
		List<AtmDetails> atmList = AppUtils.getObjectListFromJson(content, AtmDetails.class);

		assertNotNull(atmList);
		assertNotEquals(atmList.size(), 0);
	}
	
	private String getRandomString() {
		Random rand = new Random();
		return this.cities.get(rand.nextInt(this.cities.size()));
	}
}
