package com.github.glavvlad.test4finance.repository;

import com.github.glavvlad.test4finance.entity.Attempt;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;

/**
 * @author Vlad on 08.10.17.
 */
public interface AttemptRepository extends JpaRepository<Attempt, Long> {

	int countByIpAndTimeAfter(String ip, LocalDateTime time);
}
