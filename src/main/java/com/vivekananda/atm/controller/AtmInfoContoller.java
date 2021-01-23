package com.vivekananda.atm.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.vivekananda.atm.model.AtmDetails;
import com.vivekananda.atm.service.AtmService;

import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/atmInfo")
public class AtmInfoContoller {

	@Autowired
	private AtmService atmService;

	@GetMapping(path = "/all", produces = MediaType.APPLICATION_JSON_VALUE)
	@ApiOperation(value = "Check all ATM's information")
	public List<AtmDetails> getAllAtmsInfo() {
		return atmService.getAllAtms();
	}
	
	@GetMapping(path = "/byCity/{city}", produces = MediaType.APPLICATION_JSON_VALUE)
	@ApiOperation(value = "Check avialable ATM's information in the city")
	public List<AtmDetails> getAtmsInfoByCity(@PathVariable String city) {
		return atmService.getAtmsByCity(city);
	}
}
