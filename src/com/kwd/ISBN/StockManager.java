package com.kwd.ISBN;

public class StockManager {
	private DataService webService;
	private DataService DBservice;
	
	public String getLocatorCode(String isbn) {
		Book book = DBservice.lookUp(isbn);
		if(book == null) book = webService.lookUp(isbn);
		StringBuilder locator = new StringBuilder();
		locator.append(isbn.substring(isbn.length() - getExternalSubstractor()));
		locator.append(book.getAuthor().substring(0,1));
		locator.append(book.getTitle().split(" ").length); 
		return locator.toString();
	}
	
	public int getExternalSubstractor() {
		return 115;
	}
	
	public void setDBservice(DataService dBservice) {
		DBservice = dBservice;
	}

	public void setWebService(DataService service) {
		this.webService = service;
	}
}
