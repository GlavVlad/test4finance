package com.github.glavvlad.test4finance.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

/**
 * @author Vlad on 08.10.17.
 */

@Getter
@Setter
@AllArgsConstructor
public class ClientDto {

	private Long id;
	private String firstName;
	private String surname;

	public ClientDto() {
	}

	public ClientDto(String firstName, String surname) {
		this.firstName = firstName;
		this.surname = surname;
	}

}
