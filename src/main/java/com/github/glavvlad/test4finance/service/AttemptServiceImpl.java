package com.github.glavvlad.test4finance.service;

import com.github.glavvlad.test4finance.entity.Attempt;
import com.github.glavvlad.test4finance.repository.AttemptRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDateTime;

/**
 * @author Vlad on 08.10.17.
 */

@Service
public class AttemptServiceImpl implements AttemptService {

	@Autowired
	private AttemptRepository repository;

	@Override
	public int countAttemptsByIpAfterTime(String ip, LocalDateTime time) {
		return repository.countByIpAndTimeAfter(ip, time);
	}

	@Transactional
	@Override
	public void add(String ip) {
		repository.save(new Attempt(ip));
	}

}
