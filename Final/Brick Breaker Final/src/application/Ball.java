package application;

import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

/**
 * <p>This class is used to create a ball that moves around the screen.</p>
 */
public class Ball {
	double moveX = 1;
	double moveY = 3;
	double ballX = 425;
	double ballY = 500;
	double radius = 10;
	
	Circle circle = new Circle(ballX, ballY, radius);
	Pane pane = new Pane(circle);
	
	Ball() {
		circle.setFill(Color.BLUE);
		pane.setMinSize(850, 600);
		pane.setMaxSize(850, 600);
	}
	
	/**
	 * <p>This method is used in the timer to move the ball around the screen.</p>
	 */
	public void moveBall() {
		circle.setCenterX(circle.getCenterX() + moveX);
		circle.setCenterY(circle.getCenterY() - moveY);
	}
	
}

