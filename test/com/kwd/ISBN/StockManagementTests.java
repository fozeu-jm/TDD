package com.kwd.ISBN;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Spy;

import static org.mockito.Mockito.*;

public class StockManagementTests {
	DataService testWebservice;
	DataService testDBservice;
	
	@Spy
	StockManager manager;

	@Before
	public void setup() {
		testWebservice = mock(DataService.class);
		testDBservice = mock(DataService.class);
		manager = spy(new StockManager());
		manager.setWebService(testWebservice);
		manager.setDBservice(testDBservice);
		//substitute method with spy from mockito
		doReturn(4).when(manager).getExternalSubstractor();
	}

	@Test
	public void testcanGetACorrectLocatorCode() {
		String isbn = "0140177396";
		when(testDBservice.lookUp(anyString())).thenReturn(new Book("of Mice And Men", "J. Steinbeck", isbn));
		String locatorCode = manager.getLocatorCode(isbn);
		assertEquals("7396J4", locatorCode);
	}

	@Test
	public void databaseIsUsedIFDataIsPresent() {
		String isbn = "0140177396";
		when(testDBservice.lookUp(isbn)).thenReturn(new Book("abc", "abc", isbn));
		manager.getLocatorCode(isbn);
		verify(testDBservice, times(1)).lookUp(isbn); 
		verify(testWebservice, never()).lookUp(anyString());
	}

	@Test
	public void webServiceIsUsedIfDataIsNotPresentInDatabase() {
		String isbn = "0140177396";
		when(testDBservice.lookUp(isbn)).thenReturn(null);
		when(testWebservice.lookUp(isbn)).thenReturn(new Book("abc", "abc", isbn));
		manager.getLocatorCode(isbn);
		verify(testDBservice, times(1)).lookUp(isbn);
		verify(testWebservice).lookUp("0140177396");
	}
}
