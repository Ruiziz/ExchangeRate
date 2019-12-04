package com.shunl.GUIdemo;

import static org.junit.jupiter.api.Assertions.*;
import com.shunl.api.*;
import java.io.IOException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Set;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.google.gson.JsonObject;

class Tests {

	
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
		
		api1 = new Api(test1);
		api2 = new Api(test2);
		api3 = new Api(test3);
		
	}

	@Test
	void testGetCurrencyInfo() throws IOException {
		
		JsonObject json1 = api1.getCurrencyInfo();
		assertTrue(json1.has("rates"));
		
		JsonObject json2 = api2.getCurrencyInfo();
		assertTrue(json2.has("base"));
		
		JsonObject json3 = api3.getCurrencyInfo();
		assertTrue(json3.has("date"));
		
				
	}
	
	@Test
	void testGetRate() throws Exception {
		Api testRate = new Api("USD");
		JsonObject json1 = api1.getCurrencyInfo();
		JsonObject json2 = api2.getCurrencyInfo();

		Double rate_USD_CNY = testRate.getRate(json1,"CNY");
		assertTrue(rate_USD_CNY>0);
		
		Double rate_USD_CAD = testRate.getRate(json1,"CAD");
		assertTrue(rate_USD_CAD>0);
		
		Double rate_CNY_CAD = api2.getRate(json2,"CAD");
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
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		String dateToString = sdf.format(d);
		String date = api1.getUpdateDate();
		assertEquals(dateToString, date);
		
		
	}

}
