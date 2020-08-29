package com.kwd.ISBN;

import static org.junit.Assert.*;

import org.junit.Test;

import static org.mockito.Mockito.*;

public class StockManagementTests {
	DataService testWebservice = mock(DataService.class);
	DataService testDBservice = mock(DataService.class);

	@Test 
	public void testcanGetACorrectLocatorCode() {
		String isbn = "0140177396";
		when(testDBservice.lookUp(anyString())).thenReturn(new Book("of Mice And Men", "J. Steinbeck",isbn));
		when(testWebservice.lookUp(anyString())).thenReturn(new Book("of Mice And Men", "J. Steinbeck",isbn));
		StockManager manager = new StockManager();
		manager.setDBservice(testDBservice);
		manager.setWebService(testWebservice);
		String locatorCode = manager.getLocatorCode(isbn);
		assertEquals("7396J4", locatorCode);
	}
	
	@Test
	public void databaseIsUsedIFDataIsPresent() {
		String isbn = "0140177396";
		DataService DBservice = mock(DataService.class);
		DataService Webservice = mock(DataService.class);
		
		when(DBservice.lookUp(isbn)).thenReturn(new Book("abc","abc",isbn));
		
		StockManager manager = new StockManager();
		manager.setDBservice(DBservice);
		manager.setWebService(Webservice);
		
		String locatorCode = manager.getLocatorCode(isbn);
		
		verify(DBservice,times(1)).lookUp(isbn);
		verify(Webservice,never()).lookUp(anyString());
	}
	
	@Test
	public void webServiceIsUsedIfDataIsNotPresentInDatabase() {
		String isbn = "0140177396";
		DataService DBservice = mock(DataService.class);
		DataService Webservice = mock(DataService.class);
		
		when(DBservice.lookUp(isbn)).thenReturn(null);
		
		when(Webservice.lookUp(isbn)).thenReturn(new Book("abc","abc",isbn));
		
		StockManager manager = new StockManager();
		manager.setDBservice(DBservice);
		manager.setWebService(Webservice);
		
		String locatorCode = manager.getLocatorCode(isbn);
		
		verify(DBservice,times(1)).lookUp(isbn);
		verify(Webservice).lookUp("0140177396");
	}
}
