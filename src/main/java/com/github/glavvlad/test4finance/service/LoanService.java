package com.github.glavvlad.test4finance.service;

import com.github.glavvlad.test4finance.dto.ApplyLoanDto;
import com.github.glavvlad.test4finance.dto.LoanDto;

/**
 * @author Vlad on 08.10.17.
 */
public interface LoanService {

	LoanDto apply(ApplyLoanDto dto, String ip);
}
