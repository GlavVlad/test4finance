package com.github.glavvlad.test4finance.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

/**
 * @author Vlad on 08.10.17.
 */

@Getter
@Setter
@AllArgsConstructor
public class TermDto {

	private BigDecimal maxAmount;
	private BigDecimal percent;
}
