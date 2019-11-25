package com.shunl.GUIdemo;
import java.util.ArrayList;

import com.google.gson.JsonObject;
import com.shunl.api.Api;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent; 
import javafx.application.Application;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.StageStyle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;

/**
 * JavaFX App
 */
public class App extends Application {

	

	private static Double rate;
	
	static Stage primaryStage;
    @Override
    public void start(Stage primaryStage){
    	// set the title of scene
    	primaryStage.setTitle("Calculator!");
    	
    	// assign the whole structure
    	BorderPane bp = new BorderPane();
        Scene scene = new Scene(bp, 300, 500);
   
         
    	// assign all items
    	// set the Menu of raw currency
    	MenuItem menuItem11 = new MenuItem("Option 1");
        MenuItem menuItem21 = new MenuItem("Option 2");
        MenuItem menuItem31 = new MenuItem("Option 3");
        MenuButton menuButton1 = new MenuButton("Raw Currency", null, menuItem11, menuItem21, menuItem31);

        // set the Menu of target currency
    	MenuItem menuItem12 = new MenuItem("Option 1");
        MenuItem menuItem22 = new MenuItem("Option 2");
        MenuItem menuItem32 = new MenuItem("Option 3");
        MenuButton menuButton2 = new MenuButton("Target Currency", null, menuItem12, menuItem22, menuItem32);

    	// set a TextField where input the number of raw currency user wants to convert
    	TextField  rawCurrency = new TextField();
    	// label for rawCurrency
    	Label label1 = new Label("Currency Type");
    	// set the click button
        Button button = new Button("Convert");
        // set a TextField where output the number of target currency user wants to get
        TextField targetCurrency = new TextField();
        // label for targetCurrency
    	Label label2 = new Label("Currency Type");
        
        // use the input to calculate formula to get the output
        button.setOnAction(value ->  {
        	String rawstr = rawCurrency.getText();
        	Double out;
        	try {
        		out=Double.parseDouble(rawstr);
        	}catch (Exception e) {
        		out = 0.0;
        	}
            	Double outvalue = out*rate;
            	targetCurrency.setText(outvalue.toString());
        });
       
        
        
    
        
        // combine the two menus and two text fields
        // set 5 pixels between every child nodes
        VBox vbox = new VBox(10);
        vbox.setPadding(new Insets(20));
        vbox.getChildren().addAll(rawCurrency, menuButton1, label1, targetCurrency, menuButton2, label2, button);;
   

        // set the number pad  
        String[] keys =
        {
            "1", "2", "3", 
            "4", "5", "6", 
            "7", "8", "9", 
            "0", ".", "C",
        };
        
        GridPane numPad = new GridPane();
        ArrayList<Button> numberButtons = new ArrayList<>();
        for (int i = 0; i < 12; i++)
        {
            Button padButton = new Button(keys[i]);
            // add the button to the button list
            numberButtons.add(i, padButton);
            
            padButton.getStyleClass().add("num-button");
            numPad.add(padButton, i % 4, (int) Math.ceil(i / 4));
        }
       
        // set on the action for buttons to get the virtual keyboard input
        //TODO
    /*    for (int j = 0; j <10; j++) {
       
        	 // get the string of this button
        	 String buttonValue = numberButtons.get(j).getText();
        	 // if user want to clear
        	 if (buttonValue == "C") {
        		 makeClearButton(numberButtons.get(j));}
        	 // set the event and calculate the value now
        	 makeNumericButton(buttonValue, numberButtons.get(j));
        	 // apply the value to rawCurrency
        	 rawCurrency.setText(value.toString());
        	 String txt = rawCurrency.getText();
         	 Double num = Double.parseDouble(txt);
         	 num = num*3.14;
         	 txt = num.toString();
             targetCurrency.setText(txt);       	 
        }*/
        //TODO Shun
        for(Button bt:numberButtons) {
        	String btvalue = bt.getText();
        	if(btvalue=="C") {
        		makeClearButton(bt);
        		bt.setOnAction(value ->  {
        			rawCurrency.clear();
        			targetCurrency.clear();
                });
        	}else {
        	bt.setOnAction(value ->  {
        		rawCurrency.appendText(btvalue);
            	Double num = Double.parseDouble(rawCurrency.getText());
            	num = num*rate;
            	String btvalue2 = num.toString();               
                //System.out.println(btvalue);
                targetCurrency.setText(btvalue2);
            });
        	}
        }
 
        
        // output the scene
        vbox.getChildren().add(numPad);
        bp.setLeft(vbox);
     
        primaryStage.setScene(scene);
        primaryStage.show();
        
    }
    /*
    private void makeNumericButton(final String s, Button button) {
        button.setOnAction(new EventHandler<ActionEvent>() {
	        @Override
	        public void handle(ActionEvent actionEvent) {	       
	                  value = value * 10 + Double.parseDouble(s);
	                  value = Double.parseDouble(s);
	                  System.out.print(value);
	              }
	            });}*/
    	
        
    
    private void makeClearButton(Button button) {
        button.setStyle("-fx-base: mistyrose;");
        /*button.setOnAction(new EventHandler<ActionEvent>() {
	    @Override
	    public void handle(ActionEvent actionEvent) {
	            value = (double) 0;
	            System.out.print(value);
	          }
	        });*/
       
      }
    
    static void Rate(String CountryCode) throws Exception {
    	//Initial exchange rate
    	//Now is static rate
    	Api api = new Api("USD");
		JsonObject jsonobj = api.getCurrencyInfo();
		rate = api.getRate(jsonobj, "CNY");
    }

    public static void main(String[] args) throws Exception { 
    	Rate("USD");
    	launch(); 
    }

}