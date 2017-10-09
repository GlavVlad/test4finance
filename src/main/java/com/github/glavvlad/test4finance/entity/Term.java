package com.github.glavvlad.test4finance.entity;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import java.math.BigDecimal;

import static javax.persistence.GenerationType.SEQUENCE;

/**
 * @author Vlad on 08.10.17.
 */

@Data
@Entity
public class Term {

	@Id
	@SequenceGenerator(name = "term_id_seq", sequenceName = "term_id_seq")
	@GeneratedValue(strategy = SEQUENCE, generator = "term_id_seq")
	private Long id;

	@Column(name = "max_amount")
	private BigDecimal maxAmount;

	private BigDecimal percent;
}
