package com.vivekananda.atm.model;

import java.util.List;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@RequiredArgsConstructor
public class OpeningHours {

	private int dayOfWeek;
	private List<Hours> hours;
}
