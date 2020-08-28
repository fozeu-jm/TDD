package com.kwd.ISBN;

public class ValidateISBN {

	public boolean checkISBN(String isbn) {
		isbn = isbn.toUpperCase();

		if (!(isbn.length() == 10 || isbn.length() == 13))
			throw new NumberFormatException("Isbn number must be 10 or 13 digits");

		if (isbn.length() == 13) {
			int total = 0, multiplier = 0;
			for (int i = 0; i < isbn.length(); i++) {
				multiplier = (i % 2 == 0) ? 1 : 3;
				if (!Character.isDigit(isbn.charAt(i))) {
					throw new NumberFormatException("Isbn number can only contain digits");
				}
				total += (Character.getNumericValue(isbn.charAt(i))) * multiplier;
			}
			return ((total % 10) == 0);
		} else {
			int total = 0;
			for (int i = 0; i < isbn.length(); i++) {
				if (i == 9 && isbn.charAt(i) == 'X') {
					total += 10;
				} else {
					if (!Character.isDigit(isbn.charAt(i))) {
						throw new NumberFormatException("Isbn number can only contain digits");
					}
					total += (Character.getNumericValue(isbn.charAt(i))) * (10 - i);
				}
			}
			return ((total % 11) == 0);
		}
	}

}
