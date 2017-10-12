package com.github.glavvlad.test4finance.exception;

/**
 * @author Vlad on 08.10.17.
 */
public class ApplyLoanException extends RuntimeException {

	public static final String REJECTION_BAD_TIME = "Rejection. Bad time for max amount of loan.";
	public static final String REJECTION_MAX_ATTEMPT = "Rejection. Max attempts reached.";

	public ApplyLoanException() {
		super();
	}

	public ApplyLoanException(String message) {
		super(message);
	}
}
