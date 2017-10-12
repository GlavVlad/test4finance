package com.github.glavvlad.test4finance.controller;

import com.github.glavvlad.test4finance.dto.ApplyLoanDto;
import com.github.glavvlad.test4finance.dto.LoanDto;
import com.github.glavvlad.test4finance.service.LoanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalTime;

/**
 * @author Vlad on 08.10.17.
 */

@RestController
@RequestMapping("loan")
public class LoanController {

	@Autowired
	private LoanService service;

	@GetMapping
	public String test(HttpServletRequest request) {
		return request.getRemoteAddr();
	}

	@PostMapping
	public LoanDto apply(@RequestBody ApplyLoanDto dto, HttpServletRequest request) {
		return service.apply(dto, request.getRemoteAddr(), LocalTime.now());
	}
}
