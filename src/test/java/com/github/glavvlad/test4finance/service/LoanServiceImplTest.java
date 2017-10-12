package com.github.glavvlad.test4finance.service;

import com.github.glavvlad.test4finance.dto.ApplyLoanDto;
import com.github.glavvlad.test4finance.dto.ClientDto;
import com.github.glavvlad.test4finance.dto.LoanDto;
import com.github.glavvlad.test4finance.dto.TermDto;
import com.github.glavvlad.test4finance.entity.Loan;
import com.github.glavvlad.test4finance.exception.ApplyLoanException;
import com.github.glavvlad.test4finance.repository.LoanRepository;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.platform.runner.JUnitPlatform;
import org.junit.runner.RunWith;
import org.mockito.AdditionalAnswers;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

import static com.github.glavvlad.test4finance.exception.ApplyLoanException.REJECTION_BAD_TIME;
import static com.github.glavvlad.test4finance.exception.ApplyLoanException.REJECTION_MAX_ATTEMPT;
import static java.time.LocalTime.MIDNIGHT;
import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;

/**
 * @author Vlad on 13.10.17.
 */
@RunWith(JUnitPlatform.class)
@SpringBootTest
@ExtendWith(SpringExtension.class)
class LoanServiceImplTest {

	private static final String CLIENT_FIRST_NAME = "Ivan";
	private static final String CLIENT_SURNAME = "Ivanov";
	private static final String IP = "1.1.1.1";
	@Autowired
	private LoanService service;
	@MockBean
	private TermService termService;
	@MockBean
	private AttemptService attemptService;
	@MockBean
	private ClientService clientService;
	@MockBean
	private LoanRepository repository;
	@Value("${attempt.max}")
	private int maxAttempt;

	@Test
	void applyOkTest() {
		mockReturnTermServiceFindOne();
		mockReturnClientServiceSave();

		when(repository.save(any(Loan.class))).then(AdditionalAnswers.returnsFirstArg());

		LoanDto apply = service.apply(getApplyLoanDto("500"), IP, LocalTime.NOON);

		Assert.assertNotNull(apply);
	}

	@Test
	void applyBadTimeApplyLoanExceptionTest() {
		mockReturnTermServiceFindOne();
		mockReturnClientServiceSave();

		Throwable exception = assertThrows(ApplyLoanException.class,
				() -> service.apply(getApplyLoanDto("1000"), IP, MIDNIGHT.plusMinutes(1)));
		assertEquals(REJECTION_BAD_TIME, exception.getMessage());
	}

	@Test
	void applyMaxAttemptApplyLoanExceptionTest() {
		mockReturnTermServiceFindOne();
		mockReturnClientServiceSave();

		when(attemptService.countAttemptsByIpAfterTime(eq(IP), any(LocalDateTime.class))).thenReturn(maxAttempt);

		Throwable exception = assertThrows(ApplyLoanException.class,
				() -> service.apply(getApplyLoanDto("500"), IP, MIDNIGHT.plusMinutes(1)));
		assertEquals(REJECTION_MAX_ATTEMPT, exception.getMessage());
	}

	private void mockReturnTermServiceFindOne() {
		BigDecimal maxAmount = new BigDecimal("1000");
		BigDecimal percent = new BigDecimal("13.3");
		when(termService.findOne(Mockito.any())).thenReturn(new TermDto(maxAmount, percent));
	}

	private void mockReturnClientServiceSave() {
		ClientDto returnedClientDto = new ClientDto(1L, CLIENT_FIRST_NAME, CLIENT_SURNAME);
		when(clientService.save(any(ClientDto.class))).thenReturn(returnedClientDto);
	}


	private ApplyLoanDto getApplyLoanDto(String amount) {
		ApplyLoanDto dto = new ApplyLoanDto();
		dto.setAmount(new BigDecimal(amount));
		dto.setEnd(LocalDate.of(2018, 1, 1));
		dto.setFirstName(CLIENT_FIRST_NAME);
		dto.setSurname(CLIENT_SURNAME);
		dto.setTermId(1L);
		return dto;
	}
}