package com.shunl.GUIdemo;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

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

	

	private static Double rate=1.0;
	
	private String rawName, targetName;
	
	static Stage primaryStage;
    @Override
    public void start(Stage primaryStage) throws Exception {
    	// set the title of scene
    	primaryStage.setTitle("Calculator!");
    	
    	// assign the whole structure
    	BorderPane bp = new BorderPane();
        Scene scene = new Scene(bp, 300, 450);
   
         
    	// assign all items
		// set the Menu of raw currency
		Api apiInitial = new Api("USD");
		Set<String> countryName = apiInitial.getCountry();
		List<String> countryList = new ArrayList<String>();
		countryList.addAll(countryName);
		
		
		MenuButton menuRaw = new MenuButton("Raw Currency");
		for(int i = 0; i < countryList.size(); i++) {
			MenuItem menuItem = new MenuItem(countryList.get(i));
			menuRaw.getItems().add(menuItem);
			menuItem.setOnAction(value -> {
				
				// get the name of raw currency
				rawName = menuItem.getText();
				menuRaw.setText(rawName);
				
			});
			
		}
		// set the new menu button
		MenuButton menuTarget = new MenuButton("Target Currency");
		// loop
		for (int j =0; j < countryList.size(); j++) {
			MenuItem menuTargetItem = new MenuItem(countryList.get(j));
			menuTarget.getItems().add(menuTargetItem);
			menuTargetItem.setOnAction(valueTarget -> {
				
				// get the name of target currency
				targetName = menuTargetItem.getText();
				// set a new object
				Api apiRaw;
				try {
					// get the rate (for raw currency)
					apiRaw = new Api(rawName);
					rate = apiRaw.getRate(apiRaw.getCurrencyInfo(), targetName);
				} catch (Exception e) {
					
					e.printStackTrace();
				}
				menuTarget.setText(targetName);
								
			});
		}
		
		
    	// set a TextField where input the number of raw currency user wants to convert
    	TextField  rawCurrency = new TextField();
    	rawCurrency.setMaxWidth(250);
    	// label for rawCurrency
    	Label label1 = new Label("Currency Type");
    	// set the click button
        Button button = new Button("Upadate Rates");
        // set a TextField where output the number of target currency user wants to get
        TextField targetCurrency = new TextField();
        targetCurrency.setMinWidth(250);
        // label for targetCurrency
    	Label label2 = new Label("Currency Type");
        
        // use the input to calculate formula to get the output
    	
        button.setOnAction(value ->  {
        	try {
				// get the rate (for raw currency)
				Api apiupdate = new Api(rawName);
				rate = apiupdate.getRate(apiupdate.getCurrencyInfo(), targetName);
				targetCurrency.setPromptText("Rate updated! Date: " + apiupdate.getUpdateDate());
			} catch (Exception e) {
				
				targetCurrency.setPromptText("You did not choose a currency!");
			}
        });
        
       
        //TODO Textfield action
    	rawCurrency.setPromptText("Here is original currency!");
    	targetCurrency.setPromptText("Here is target currency!");
        rawCurrency.setOnKeyTyped(value -> {
        	String rawstr = rawCurrency.getText();
        	Double out=0.0;
        	if(rawstr.isBlank())rawstr="0";
        	if(rawstr.matches("[a-z]")) {
        		rawCurrency.deletePreviousChar();
        	}else {
        	out=Double.parseDouble(rawstr);
        	}
        		
        	
        	//if(rawstr.contains("f") || rawstr.contains("d")) rawCurrency.deletePreviousChar();
        	//if(rawstr.isBlank()) {
        	//	out=0.0;
        	//}      		
            Double outvalue = out*rate;
            targetCurrency.setText(outvalue.toString());
        	
        });
        
        
    
        
        // combine the two menus and two text fields
        // set 5 pixels between every child nodes
        VBox vbox = new VBox(10);
        vbox.setPadding(new Insets(20));
        vbox.getChildren().addAll(rawCurrency, menuRaw, label1, targetCurrency, menuTarget, label2, button);
		;
   

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
            padButton.setMinSize(50, 50);
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
    


    public static void main(String[] args) throws Exception { 
    	
    	launch(); 
    }

}