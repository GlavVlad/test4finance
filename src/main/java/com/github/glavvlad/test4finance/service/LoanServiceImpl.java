package com.github.glavvlad.test4finance.service;

import com.github.glavvlad.test4finance.dto.ApplyLoanDto;
import com.github.glavvlad.test4finance.dto.ClientDto;
import com.github.glavvlad.test4finance.dto.LoanDto;
import com.github.glavvlad.test4finance.dto.TermDto;
import com.github.glavvlad.test4finance.entity.Loan;
import com.github.glavvlad.test4finance.exception.ApplyLoanException;
import com.github.glavvlad.test4finance.repository.LoanRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalTime;

import static com.github.glavvlad.test4finance.exception.ApplyLoanException.REJECTION_BAD_TIME;
import static com.github.glavvlad.test4finance.exception.ApplyLoanException.REJECTION_MAX_ATTEMPT;
import static java.time.LocalDateTime.now;
import static java.time.LocalTime.MIDNIGHT;
import static java.time.LocalTime.of;
import static org.springframework.transaction.annotation.Propagation.REQUIRED;

/**
 * @author Vlad on 08.10.17.
 */

@Service
public class LoanServiceImpl implements LoanService {


	@Autowired
	private LoanRepository repository;

	@Autowired
	private ClientService clientService;

	@Autowired
	private TermService termService;

	@Autowired
	private AttemptService attemptService;

	@Value("${attempt.max}")
	private int maxAttempt;

	@Transactional(propagation = REQUIRED)
	@Override
	public LoanDto apply(ApplyLoanDto dto, String ip, LocalTime now) {
		TermDto term = termService.findOne(dto.getTermId());

		if (isBadTime(now) && isBadAmount(dto.getAmount(), term.getMaxAmount())) {
			throw new ApplyLoanException(REJECTION_BAD_TIME);
		}

		if (isBadAttempt(ip)) {
			throw new ApplyLoanException(REJECTION_MAX_ATTEMPT);
		}

		attemptService.add(ip);

		ClientDto client = clientService.save(new ClientDto(dto.getFirstName(), dto.getSurname()));
		Loan loan = new Loan();
		loan.setAmount(dto.getAmount());
		loan.setClientId(client.getId());
		loan.setEnd(dto.getEnd());
		loan.setTermId(dto.getTermId());
		loan = repository.save(loan);
		LoanDto loanDto = mapToDto(loan);
		loanDto.setClient(client);
		loanDto.setTerm(term);
		return loanDto;
	}

	/**
	 * Check if time bad for attempt to take loan
	 *
	 * @return true if bad time
	 */
	private boolean isBadTime(LocalTime now) {
		return now.isAfter(MIDNIGHT) && now.isBefore(of(6, 0));
	}

	/**
	 * Check if client tried to take loan with not appropriate amount
	 *
	 * @param amount    that client want to take
	 * @param maxAmount max possible amount
	 * @return true if client want not appropriate amount
	 */
	private boolean isBadAmount(BigDecimal amount, BigDecimal maxAmount) {
		return maxAmount.compareTo(amount) <= 0;
	}

	/**
	 * Check if maximum number of attempts reached
	 *
	 * @param ip address from which the attempt was made
	 * @return true if it is not possible
	 */
	private boolean isBadAttempt(String ip) {
		return attemptService.countAttemptsByIpAfterTime(ip, now().minusDays(1)) >= maxAttempt;
	}

	private LoanDto mapToDto(Loan loan) {
		LoanDto dto = new LoanDto();
		dto.setAmount(loan.getAmount());
		dto.setEnd(loan.getEnd());
		dto.setId(loan.getId());
		return dto;
	}

}
