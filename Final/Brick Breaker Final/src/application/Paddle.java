package application;

import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

/**
 * <p>This class is used to make a paddle that can bounce the ball around.</p>
 */
public class Paddle {
	
	double paddleX = 350;
	double paddleY = 540;
	double paddleWidth = 140;
	double paddleHeight = 15;
	Rectangle paddle = new Rectangle(paddleX, paddleY, paddleWidth, paddleHeight);
	
	Paddle(){
		paddle.setStroke(Color.DARKRED);
		paddle.setFill(Color.DARKORANGE);
	}
	
	/**
	 * <p>This method is used to move the paddle left and right using the arrow keys.</p>
	 * @param scene (Scene; is used to move the paddle in the scene and to also prevent the paddle from moving off screen.)
	 */
	public void movePaddle(Scene scene) {
		final int moveAmount = 10;
		scene.setOnKeyPressed(e -> {
			if (e.getCode() == KeyCode.LEFT && (paddle.getX() / 2) - moveAmount > -5) {
				paddle.setX(paddle.getX() - moveAmount);
			}
				
			if (e.getCode() == KeyCode.RIGHT && (paddle.getX() / 2) + moveAmount < 360) {
				paddle.setX(paddle.getX() + moveAmount); 
			}
			
		});
		
	}
	
}