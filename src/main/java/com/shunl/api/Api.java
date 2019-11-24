package com.shunl.api;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Set;


public class Api {
	URL real_url;
	
	
	String url;
	
	
	static String url_str = "https://api.exchangerate-api.com/v4/latest/";

    public Api(String currency) throws Exception {

    	// Setting URL
    	url = url_str + currency;
    	
    	this.real_url = new URL(url);
    }
    
    public JsonObject getCurrencyInfo() throws IOException {
    	HttpURLConnection request = (HttpURLConnection) real_url.openConnection();
    	request.connect();
    	JsonParser jp = new JsonParser();
    	JsonObject jsonobj = new JsonObject();
    	JsonElement root = jp.parse(new InputStreamReader((InputStream) request.getContent()));
    	jsonobj = root.getAsJsonObject();
    	
    	return jsonobj;
    }
    
    public Double getRate(JsonObject jsonobj,String currency) {
    	JsonObject req_result = jsonobj.get("rates").getAsJsonObject();
    	Double result = req_result.get(currency).getAsDouble();
    	return result;
    }
	
	public Set<String> getCountry() throws Exception {
		Getcountry country = new Getcountry();
		return country.countryList;
	}
    
    

}



