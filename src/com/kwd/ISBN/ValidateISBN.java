package com.kwd.ISBN;

public class ValidateISBN {

	public boolean checkISBN(String isbn) {
		if(isbn.length() != 10) throw new NumberFormatException("Isbn number must be 10 digits");
		int total = 0;
		for (int i = 0; i < 10; i++) {
			if(!Character.isDigit(isbn.charAt(i))) 
				throw new NumberFormatException("Isbn number should contain only numbers");
			total += isbn.charAt(i) * (10 - i);
		}
		return ((total % 11) == 0);
	}

}
