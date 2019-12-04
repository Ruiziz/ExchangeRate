package com.shunl.api;

import static org.junit.Assert.*;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Set;

import com.google.gson.JsonObject;
import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;

import java.io.IOException;

import java.net.URL;

class ApiTest {

	URL real_url;

	String url;

	static String url_str = "https://api.exchangerate-api.com/v4/latest/";

	String test1;
	String test2;
	String test3;
	String test4;

	Api api1, api2, api3, api4;

	@BeforeEach
	void setUp() throws Exception {
		String test1 = "USD"; // right name
		String test2 = "CNY"; // right name
		String test3 = "CAD"; // right name
		String test4 = "ZRZ"; // wrong name
		api1 = new Api(test1);
		api2 = new Api(test2);
		api3 = new Api(test3);
		api4 = new Api(test4);

	}

	@Test
	void testGetCurrencyInfo() throws IOException {
		
		JsonObject json1 = api1.getCurrencyInfo();
		assertTrue(json1.has(test1));
		
		JsonObject json2 = api2.getCurrencyInfo();
		assertTrue(json2.has(test2));
		
		JsonObject json3 = api3.getCurrencyInfo();
		assertTrue(json3.has(test3));
		
		JsonObject json4 = api4.getCurrencyInfo();
		assertFalse(json3.has(test4));
				
	}

	@Test
	void testGetRate() throws IOException {
		JsonObject json1 = api1.getCurrencyInfo();
		JsonObject json2 = api2.getCurrencyInfo();

		Double rate_USD_CNY = api1.getRate(json1, test2);
		assertTrue(rate_USD_CNY>0);
		
		Double rate_USD_CAD = api1.getRate(json1, test3);
		assertTrue(rate_USD_CAD>0);
		
		Double rate_CNY_CAD = api2.getRate(json2, test3);
		assertTrue(rate_CNY_CAD>0);
	}
	
	@Test
	void testGetCountry() throws Exception {
		
		Set<String> countryList = api1.getCountry();
		assertTrue(countryList.size() > 30);
		
		
		Set<String> countryList2 = api2.getCountry();
		assertTrue(countryList2.size() > 50);
		
		
		Set<String> countryList3 = api3.getCountry();
		assertTrue(countryList3.size() > 40);
		
	}
	
	@Test
	void testGetUpdateDate() throws IOException {
		Date d = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-mm-dd");
		String dateToString = sdf.format(d);
		String date = api1.getUpdateDate();
		assertEquals(dateToString, date);
		
		
	}
}
