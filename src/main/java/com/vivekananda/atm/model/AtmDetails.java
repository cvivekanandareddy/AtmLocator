package com.vivekananda.atm.model;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class AtmDetails {

	private Address address;
	private long distance;
	private List<OpeningHours> openingHours;
	private String functionality;
	private String type;
}
