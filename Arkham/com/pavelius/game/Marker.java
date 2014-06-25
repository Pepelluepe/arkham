package com.pavelius.game;

import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;

public class Marker extends ImageView {
	
	private static DropShadow dropShadow;
	
	private DropShadow getDropShadow() {
		if(dropShadow==null) {
			dropShadow = new DropShadow();
			dropShadow.setRadius(15.0);
			dropShadow.setOffsetX(3.0);
			dropShadow.setOffsetY(3.0);
			dropShadow.setColor(Color.color(0.5, 0.5, 0.5));
		}
		return dropShadow;
	}
	
	Marker(Token location) {
		Image e = new Image("com/pavelius/game/markers/AshcanPeteMarker.png"); 
		setImage(e);
		setEffect(getDropShadow());
		move(location);
	}
	
	void move(double x, double y) {
		setLayoutX(x);
		setLayoutY(y);		
	}
	
	void move(Token pos) {
		move(pos.get(Token.PosX) - getImage().getWidth()/2,
				pos.get(Token.PosY) - getImage().getHeight()/2);
	}	

}
