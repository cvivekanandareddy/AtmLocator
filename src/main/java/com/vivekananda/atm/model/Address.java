package com.vivekananda.atm.model;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@RequiredArgsConstructor
public class Address {

	private String street;
	private String housenumber;
	private String postalcode;
	private String city;
	private GeoLocation geoLocation;
	
}
