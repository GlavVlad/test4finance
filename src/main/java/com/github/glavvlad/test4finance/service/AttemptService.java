package com.github.glavvlad.test4finance.service;

import java.time.LocalDateTime;

/**
 * @author Vlad on 08.10.17.
 */
public interface AttemptService {

	int countAttemptsByIpAfterTime(String ip, LocalDateTime time);

	void add(String ip);
}
