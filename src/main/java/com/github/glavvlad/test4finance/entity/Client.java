package com.github.glavvlad.test4finance.entity;

import lombok.Data;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;

import static javax.persistence.GenerationType.SEQUENCE;

/**
 * @author Vlad on 08.10.17.
 */

@Data
@RequiredArgsConstructor
@Entity
public class Client {

	@Id
	@SequenceGenerator(name = "client_id_seq", sequenceName = "client_id_seq")
	@GeneratedValue(strategy = SEQUENCE, generator = "client_id_seq")
	private Long id;

	@NonNull
	@Column(name = "first_name")
	private String firstName;

	@NonNull
	private String surname;
}
