package com.vivekananda.atm.model;

import java.util.List;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@RequiredArgsConstructor
public class AtmDetails {

	private Address address;
	private long distance;
	private List<OpeningHours> openingHours;
	private String functionality;
	private String type;
}
