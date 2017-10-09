package com.github.glavvlad.test4finance.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;

/**
 * @author Vlad on 08.10.17.
 */

@Getter
@Setter
public class ApplyLoanDto {

	private String firstName;
	private String surname;
	private Long termId;

	private BigDecimal amount;

	@JsonFormat(pattern = "yyyy-MM-dd")
	private LocalDate end;
}
