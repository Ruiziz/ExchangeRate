package com.shunl.GUIdemo;
import com.google.gson.JsonObject;
import com.shunl.api.*;
public class SetUp {
	
	public Double SetRate(String CountryCode) throws Exception {
		Api api = new Api(CountryCode);
		JsonObject jsonobj = api.getCurrencyInfo();
		Double rate = api.getRate(jsonobj, "CNY");
		return rate;
	}
	
}
