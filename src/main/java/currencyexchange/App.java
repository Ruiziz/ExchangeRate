package currencyexchange;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;



import java.net.HttpURLConnection;


import java.net.URL;
import java.util.Scanner;

public class App {

    
public static void main(String[] args) throws IOException {
        
Scanner scanner = new Scanner(System.in);
// Setting URL
String url_str = "https://api.exchangerate-api.com/v4/latest/USD";


URL url = new URL(url_str);

HttpURLConnection request = (HttpURLConnection) url.openConnection();
request.connect();


// Convert to JSON
JsonParser jp = new JsonParser();
JsonObject jsonobj = new JsonObject();
JsonElement root = jp.parse(new InputStreamReader((InputStream) request.getContent()));
jsonobj = root.getAsJsonObject();

// Accessing object
JsonObject req_result = jsonobj.get("rates").getAsJsonObject();
System.out.println("from USD to What?");
String to = scanner.nextLine();
Double result = req_result.get(to).getAsDouble();
System.out.println("input amount");
Double amount = scanner.nextDouble();

System.out.println("So " + amount + " USD equals " + amount*result + " " +to);
scanner.close();
    
}


}

