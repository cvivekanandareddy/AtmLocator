package com.vivekananda.atm.model;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@RequiredArgsConstructor
public class Hours {

	private String hourFrom;
	private String hourTo;
}
