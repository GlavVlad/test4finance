package com.github.glavvlad.test4finance.dto;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;

/**
 * @author Vlad on 08.10.17.
 */

@Getter
@Setter
public class LoanDto {

	private Long id;
	private BigDecimal amount;
	private LocalDate end;
	private ClientDto client;
	private TermDto term;
}
