package com.kwd.ISBN;

public class ValidateISBN {

	private static final int LONG_ISBN_LENGTH = 13;
	private static final int SHORT_ISBN_LENGTH = 10;

	public boolean checkISBN(String isbn) {
		isbn = isbn.toUpperCase();

		if (isbn.length() == LONG_ISBN_LENGTH) {
			return isValidLongISBN(isbn);
		} else if (isbn.length() == SHORT_ISBN_LENGTH) {
			return isValidShortISBN(isbn);
		}
		throw new NumberFormatException("Isbn number must be 10 or 13 digits long");
	}

	private boolean isValidShortISBN(String isbn) {
		int total = 0;
		for (int i = 0; i < SHORT_ISBN_LENGTH; i++) {
			if (i == 9 && isbn.charAt(i) == 'X') {
				total += 10;
			} else {
				checkIfDigit(isbn.charAt(i));
				total += (Character.getNumericValue(isbn.charAt(i))) * (SHORT_ISBN_LENGTH - i);
			}
		}
		return ((total % 11) == 0);
	}

	private boolean isValidLongISBN(String isbn) {
		int total = 0, multiplier = 0;
		for (int i = 0; i < LONG_ISBN_LENGTH; i++) {
			checkIfDigit(isbn.charAt(i));
			multiplier = (i % 2 == 0) ? 1 : 3;
			total += (Character.getNumericValue(isbn.charAt(i))) * multiplier;
		}
		return ((total % 10) == 0);
	}

	private void checkIfDigit(char c) {
		if (!Character.isDigit(c)) {
			throw new NumberFormatException("Isbn number can only contain digits");
		}
	}

}
