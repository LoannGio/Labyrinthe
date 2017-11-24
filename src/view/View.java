package view;

import javafx.stage.Stage;

public class View {
	
	private static View instance = new View();
	
	private View() {
		
	}
	
	public static View getInstance() {
		return instance;
	}
	
	public void start(Stage primaryStage) {
		//TODO
	}

}
