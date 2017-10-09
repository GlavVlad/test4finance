package com.github.glavvlad.test4finance.repository;

import com.github.glavvlad.test4finance.entity.Loan;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author Vlad on 08.10.17.
 */
public interface LoanRepository extends JpaRepository<Loan, Long> {
}
