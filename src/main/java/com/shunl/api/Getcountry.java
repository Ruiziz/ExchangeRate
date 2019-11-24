package com.shunl.api;

import java.util.Set;


import com.google.gson.JsonObject;


public class Getcountry{

    Set<String> countryList;

    public Getcountry() throws Exception {
        Api api = new Api("USD");
        JsonObject jsonobj = api.getCurrencyInfo();
        //JsonElement countries = jsonobj.get("rates");
        JsonObject countries2 = jsonobj.getAsJsonObject("rates");
        Set<String> countryList = countries2.keySet();
        this.countryList = countryList;
    }
        
    
    
} 