package com.github.glavvlad.test4finance.entity;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import java.math.BigDecimal;
import java.time.LocalDate;

import static javax.persistence.FetchType.LAZY;
import static javax.persistence.GenerationType.SEQUENCE;

/**
 * @author Vlad on 08.10.17.
 */

@Data
@Entity
public class Loan {

	@Id
	@SequenceGenerator(name = "loan_id_seq", sequenceName = "loan_id_seq")
	@GeneratedValue(strategy = SEQUENCE, generator = "loan_id_seq")
	private Long id;

	private BigDecimal amount;

	@Column(name = "end_date")
	private LocalDate end;

	@Column(name = "client_id")
	private Long clientId;

	@ManyToOne(fetch = LAZY)
	@JoinColumn(name = "client_id", referencedColumnName = "id", insertable = false, updatable = false)
	private Client client;

	@Column(name = "term_id")
	private Long termId;

	@ManyToOne(fetch = LAZY)
	@JoinColumn(name = "term_id", referencedColumnName = "id", insertable = false, updatable = false)
	private Term term;
}
