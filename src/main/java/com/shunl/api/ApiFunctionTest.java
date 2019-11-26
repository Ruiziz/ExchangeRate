package com.shunl.api;

import com.google.gson.JsonObject;

import java.util.ArrayList;

import com.google.gson.JsonElement;
public class ApiFunctionTest {

    public static void main(String[] args) throws Exception {
        Api api = new Api("USD");
        JsonObject jsonobj = api.getCurrencyInfo();
        String date = api.getUpdateDate();

        System.out.println(date);
        
        
    }
        
    
        
    
    
} 