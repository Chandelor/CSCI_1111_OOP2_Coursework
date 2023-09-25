package application;

import java.util.ArrayList;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

/**
 * <p>This class is used to set up the bricks for the game.</p>
 */
public class Brick {
	ArrayList<Rectangle> brickArray = new ArrayList<>();
	AnchorPane brickLayout = new AnchorPane();
	
	/**
	 * <p>This method uses a loop to create rectangles that act as bricks to fill a set area defined by the height and width.</p>
	 */
	public void createBricks() {
		brickLayout.setMinSize(850, 0);
		brickLayout.setMaxSize(850, 0);
    	
        int spacing = 1;
        for (double height = 175; height > 0; height -= 35) {
            for (double width = 762; width > 0; width -= 30) {
                if (spacing % 3 == 0) {
                    Rectangle rectangle = new Rectangle(width, height - 10, 75, 25);
                    rectangle.setFill(Color.RED);
                    brickLayout.getChildren().add(rectangle);
                    brickArray.add(rectangle);
                }
                
                spacing++;
            }
            
        }
    	
	}

}
