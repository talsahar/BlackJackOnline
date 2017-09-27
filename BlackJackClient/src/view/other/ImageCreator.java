package view.other;

import javafx.scene.image.Image;

public class ImageCreator {

	public Image create(String path){
		return new Image(getClass().getResourceAsStream(path));
		
	}
	
}
