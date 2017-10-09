package com.github.glavvlad.test4finance.entity;

import lombok.Data;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import java.time.LocalDateTime;

import static javax.persistence.GenerationType.SEQUENCE;

/**
 * @author Vlad on 08.10.17.
 */

@Data
@RequiredArgsConstructor
@Entity
public class Attempt {

	@Id
	@SequenceGenerator(name = "attempt_id_seq", sequenceName = "attempt_id_seq")
	@GeneratedValue(strategy = SEQUENCE, generator = "attempt_id_seq")
	private Long id;

	@NonNull
	private String ip;
	private LocalDateTime time = LocalDateTime.now();
}
