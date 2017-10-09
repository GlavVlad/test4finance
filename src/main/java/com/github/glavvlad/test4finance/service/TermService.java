package com.github.glavvlad.test4finance.service;

import com.github.glavvlad.test4finance.dto.TermDto;

/**
 * @author Vlad on 08.10.17.
 */
public interface TermService {

	TermDto findOne(Long id);
}
