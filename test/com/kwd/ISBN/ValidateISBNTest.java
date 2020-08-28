package com.kwd.ISBN;

import static org.junit.Assert.*;

import org.junit.Test;

public class ValidateISBNTest {

	@Test
	public void checkValidISBN() {
		ValidateISBN validator = new ValidateISBN();
		boolean result = validator.checkISBN("0201309513");
		assertTrue("First Value", result);
		result = validator.checkISBN("0374194327");
		assertTrue("Second Value", result);
	}

	@Test
	public void checkInvalid10DigitISBN() {
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

	@Test
	public void ISBNEndingInXAreValid() {
		ValidateISBN validator = new ValidateISBN();
		boolean result = validator.checkISBN("012000030X");
		assertTrue("With X", result);
	}
	
	@Test
	public void checkValid13DigitISBNNumber() {
		ValidateISBN validator = new ValidateISBN();
		boolean result = validator.checkISBN("9780374230920");
		assertTrue("13 Value", result);
		
		result = validator.checkISBN("9780316499019");
		assertTrue("Second 13 Value", result);
	}
	
	@Test
	public void checkInvalid13DigitNumber() {
		ValidateISBN validator = new ValidateISBN();
		boolean result = validator.checkISBN("9780374230929");
		assertFalse(result);
	}
	
	@Test(expected = NumberFormatException.class)
	public void lettersNotAllowedIn13DigitISBN() {
		ValidateISBN validator = new ValidateISBN();
		validator.checkISBN("97803GJ230929");
	}

}
