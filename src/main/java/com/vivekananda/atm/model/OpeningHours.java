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
public class OpeningHours {

	private int dayOfWeek;
	private List<Hours> hours;
}
