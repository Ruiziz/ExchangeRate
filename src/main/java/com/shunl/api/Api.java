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

/**
 * This is the API part.
 */
public class Api {
	URL real_url;
	
	
	String url;
	
	
	static String url_str = "https://api.exchangerate-api.com/v4/latest/";
	/**
	 * This is the API we want to use to get currency exchange rate.
	 * @param currency The Currency you want to exchange
	 * @throws Exception
	 */
    public Api(String currency) throws Exception {

    	// Setting URL
    	url = url_str + currency;
    	
    	this.real_url = new URL(url);
    }
    /** 
	 * Get the currency info
	 * @return a JsonObject includes date, rate, etc.
	 * @throws IOException
	 */
    public JsonObject getCurrencyInfo() throws IOException {
    	//connect to the url
    	HttpURLConnection request = (HttpURLConnection) real_url.openConnection();
    	request.connect();
    	//parse to Json.
    	JsonParser jp = new JsonParser();
    	JsonObject jsonobj = new JsonObject();
    	JsonElement root = jp.parse(new InputStreamReader((InputStream) request.getContent()));
    	jsonobj = root.getAsJsonObject();
    	
    	return jsonobj;
    }
    /**
	 * Get exchange rates for 52 countries.
	 * @param jsonobj 
	 * @param currency The currency you want to exchange to.
	 * @return exchange rate.
	 */
    public Double getRate(JsonObject jsonobj,String currency) {
    	JsonObject req_result = jsonobj.get("rates").getAsJsonObject();
    	Double result = req_result.get(currency).getAsDouble();
    	return result;
    }
	/**
	 * get country list as a set
	 * @return a set of countries.
	 * @throws Exception
	 */
	public Set<String> getCountry() throws Exception {
		JsonObject jsonobj = getCurrencyInfo();
        //Get the rates and know the countries this api have.
        JsonObject countries2 = jsonobj.getAsJsonObject("rates");
        //obtain the Keyset to get a Set of country code.
        Set<String> countryList = countries2.keySet();
		return countryList;
	}
	/**
	 * Get update date as string
	 * @return a string showing the update date
	 * @throws IOException
	 */
	public String getUpdateDate() throws IOException {
		JsonObject jsonobj = getCurrencyInfo();
		String date = jsonobj.get("date").getAsString();
		return date;
	}
    

}



