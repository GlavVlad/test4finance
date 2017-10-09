package com.github.glavvlad.test4finance.service;

import com.github.glavvlad.test4finance.dto.TermDto;
import com.github.glavvlad.test4finance.entity.Term;
import com.github.glavvlad.test4finance.exception.TermException;
import com.github.glavvlad.test4finance.repository.TermRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.function.Function;

/**
 * @author Vlad on 08.10.17.
 */

@Service
public class TermServiceImpl implements TermService {

	private static final String TERM_NOT_FOUND = "Term not found";

	@Autowired
	private TermRepository repository;
	private Function<Term, TermDto> mapToDto = e -> new TermDto(e.getMaxAmount(), e.getPercent());

	@Override
	public TermDto findOne(Long id) {
		Term term = Optional.ofNullable(repository.findOne(id))
				.orElseThrow(() -> new TermException(TERM_NOT_FOUND));
		return mapToDto.apply(term);
	}
}
