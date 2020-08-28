package com.kwd.ISBN;

import static org.junit.Assert.*;

import org.junit.Test;

public class ValidateISBNTest {

	@Test
	public void checkValidISBN() {
		ValidateISBN validator = new ValidateISBN();
		boolean result = validator.checkISBN("0201309513");
		assertTrue("First Value",result);
		result = validator.checkISBN("0374194327");
		assertTrue("Second Value", result);
	}
	
	@Test
	public void checkInvalidISBN() {
		ValidateISBN validator = new ValidateISBN();
		boolean result = validator.checkISBN("0201309517");
		assertFalse(result);
	}
	
	@Test(expected = NumberFormatException.class)
	public void NineDigitsNotAllowed() {
		ValidateISBN validator = new ValidateISBN();
		validator.checkISBN("123456789");
	}
	
	@Test(expected = NumberFormatException.class)
	public void lettersNotAllowed() {
		ValidateISBN validator = new ValidateISBN();
		validator.checkISBN("helloworld");
	}

}
