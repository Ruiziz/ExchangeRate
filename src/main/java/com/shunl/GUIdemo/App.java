package com.shunl.GUIdemo;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;


/**
 * JavaFX App
 */
public class App extends Application {

    @Override
    public void start(Stage primaryStage) {
    	primaryStage.setTitle("Calculator!");
    	TextField label = new TextField();
        Button button = new Button("Click");
        TextField label2 = new TextField("Output!");
        button.setOnAction(value ->  {
        	String txt = label.getText();
        	Double num = Double.parseDouble(txt);
        	num = num*3.14;
        	txt = num.toString();
           label2.setText(txt);
        });
        
        HBox hbox = new HBox(label,button,label2);
        hbox.setSpacing(5);
        
        Numpad numpad = new Numpad();
        VBox pad = numpad.setUpNumPad();
        Scene scene = new Scene(hbox, 400, 200);
    	primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch();
    }

}