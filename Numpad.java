package ruizi.ExchangeRateCalculator;

import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class Numpad {
	Button button0 = new Button("0");
	Button button1 = new Button("1");
	Button button2 = new Button("2");
	Button button3 = new Button("3");
	Button button4 = new Button("4");
	Button button5 = new Button("5");
	Button button6 = new Button("6");
	Button button7 = new Button("7");
	Button button8 = new Button("8");
	Button button9 = new Button("9");
	HBox hb1,hb2,hb3,hb4;
	public Numpad() {
		
		 hb1 = new HBox(button1,button2,button3);
		 hb2 = new HBox(button4,button5,button6);
		 hb3 = new HBox(button7,button8,button9);
		 hb4 = new HBox(new Button("C"),button0);
		
	}
	
	public VBox setUpNumPad() {
		
		VBox vb = new VBox(hb4,hb1,hb2,hb3);
		return vb;
	}

}