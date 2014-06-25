package com.pavelius.game;

import java.util.ArrayList;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class ArkhamHorror extends Application {

	static int terrorLevel;
	static ArrayList<Monster> outskirts = new ArrayList<Monster>();
	static ArrayList<Monster> sky = new ArrayList<Monster>();
	static Investigator sheriff;
	static AnchorPane canvas = new AnchorPane();
	
	public void start(Stage primaryStage) {
		ScrollPane root = new ScrollPane();
		canvas.getChildren().add(new MapView());
		root.setContent(canvas);
		Marker e1 = new Marker(Token.Docks);
		canvas.getChildren().add(e1);
		primaryStage.setTitle("Arkham horror");
		primaryStage.setScene(new Scene(root, 800, 600));
		primaryStage.show();
	}

	public static void main(String[] args) {
		launch(args);
	}

}