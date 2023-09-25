package application;

import java.io.FileNotFoundException;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.util.Duration;

/**
 * @author Chandelor
 * <p>Date: Septemper 20th, 2023</p>
 *
 * <p>
 * This code will create a Brick Breaker game where a player will break bricks using a ball that will continually bounce around the screen.
 * The player will use a paddle prevent the ball from hitting the bottom of the screen and to control where the ball is heading.
 * Once the player has either ran out of lives or broken all of the bricks the game will end.
 * </p>
 */
public class Brick_Breaker_Final extends Application {
	
	Timeline timer = new Timeline(new KeyFrame(Duration.millis(10), new EventHandler<ActionEvent>() {
		@Override
		public void handle(ActionEvent arg0) {
			if (!bricks.brickArray.isEmpty()) {
				gameInterface.timer();
				gameInterface.updateScore();
				gameInterface.updateLives();
				paddle.movePaddle(scene);
				ball.moveBall();
				ballCollision();
				paddleCollision(ball);
				bricks.brickArray.removeIf(brick -> {
					try {
						return brickCollision(brick);
					} 
					
					catch (FileNotFoundException e) {
						return false;
					}
					
				});
					
			}
			
			else {
				gameInterface.updateScore();
				endGame();
				timer.stop();
			}
			
		}
		
	}));
	
	GameInterface gameInterface = new GameInterface();
	BorderPane brickBreakerPane = new BorderPane();
	Scene scene = new Scene(brickBreakerPane, 850, 600);
	Pane ballAndPaddle = new Pane();
	Paddle paddle = new Paddle();
	Brick bricks = new Brick();
	Ball ball = new Ball();

	/**
	 * <p>Launches the program</p>
	 * @param args(String[]; used to start the program.)
	 */
	public static void main(String[] args) {
		launch(args);
	}
	
	/**
	 * <p>The start method is pulling all of the panes together and adding them to the scene that is diplayed to the user.</p>
	 */
	@Override
	public void start(Stage primaryStage) throws Exception {
		runGame();
		bricks.createBricks();
		gameInterface.createInterface();
		ballAndPaddle.getChildren().add(paddle.paddle);
		ballAndPaddle.getChildren().add(ball.pane);
		brickBreakerPane.setTop(gameInterface.topInterface);
		brickBreakerPane.setCenter(bricks.brickLayout);
		brickBreakerPane.setBottom(ballAndPaddle);
		brickBreakerPane.setStyle("-fx-background-color: black;");
		
		primaryStage.setTitle("Brick Breaker"); // Set the stage title
		primaryStage.setScene(scene); // Place the scene in the stage
		primaryStage.show(); // Display the stage
		primaryStage.setResizable(false);
	}
	
	/**
	 * <p>Detects if the ball has collided with a brick.</p>
	 * @param brick (Rectangle; detects if this brick has been collided with.)
	 * @return boolean (boolean; true/false whether or not the brick was collided with.)
	 * @throws FileNotFoundException (Throws an exception if the brick can't be found.)
	 */
	public boolean brickCollision(Rectangle brick) throws FileNotFoundException {
		if (ball.circle.getBoundsInParent().intersects(brick.getBoundsInParent())) {
	        boolean rightOfBrick = ball.circle.getCenterX() >= ((brick.getX() + brick.getWidth()) - ball.circle.getRadius());
	        boolean leftOfBrick = ball.circle.getCenterX() <= (brick.getX() + ball.circle.getRadius());
	        boolean topOfBrick = ball.circle.getCenterY() <= (brick.getY() + ball.circle.getRadius());
	        boolean bottomOfBrick = ball.circle.getCenterY() >= ((brick.getY() + brick.getHeight()) - ball.circle.getRadius());
	
	        if (rightOfBrick || leftOfBrick) ball.moveX *= 1;
	        if (topOfBrick || bottomOfBrick) ball.moveY *= -1;
	        
	        bricks.brickLayout.getChildren().remove(brick);
	        gameInterface.score++;
	        
	        return true;
		}
		
		return false;
	}
	
	/**
	 * <p>
	 * Detects if the ball has collided with one of the 4 walls.
	 * If the bottom wall is hit then checkLives() is run.
	 * </p>
	 */
	public void ballCollision() {
		boolean hitRight = ball.circle.getCenterX() >= scene.getWidth() - ball.circle.getRadius();
		boolean hitLeft = ball.circle.getCenterX() <= ball.circle.getRadius();
		boolean hitTop = ball.circle.getCenterY() <= ball.circle.getRadius() - 2;
		boolean hitBottom = ball.circle.getCenterY() >= ((scene.getHeight() - 40) - ball.circle.getRadius());
		
		if (hitRight || hitLeft) ball.moveX *= -1;
		if (hitTop) ball.moveY *= -1;
		if (hitBottom) checkLives();
	}
	
	/**
	 * <p>Checks if the ball has collided with the paddle and reverses the balls dircetion if is has.</p>
	 * @param ball (Ball; gets the circle to check for collision.)
	 */
	public void paddleCollision(Ball ball) {
		if (ball.circle.intersects(paddle.paddle.getBoundsInParent())) ball.moveY *= -1;
	}
	
	/**
	 * <p>Starts the timer which runs all animations.</p>
	 */
	public void runGame() {
		timer.setCycleCount(Timeline.INDEFINITE);
		timer.play();
	}
	
	/**
	 * <p>
	 * Checks if the game was ended with 0 lives.
	 * If the game ended with more than 0 lives left a "You Win!" text is displayed.
	 * Stops the timer after end text is displayed.
	 * </p>
	 */
	public void endGame() {
		if (gameInterface.lives == 0) {
			Label endText = new Label("Game Over");
			endText.setStyle("-fx-text-fill: orange;");
			endText.setFont(new Font("Arial", 50));
			brickBreakerPane.setCenter(endText);
			BorderPane.setMargin(endText, new Insets(200));
		}
		
		else {
			Label endText = new Label("You Win!");
			endText.setStyle("-fx-text-fill: orange;");
			endText.setFont(new Font("Arial", 50));
			brickBreakerPane.setCenter(endText);
			BorderPane.setMargin(endText, new Insets(200));
		}
		
		timer.stop();
	}
	
	/**
	 * <p>
	 * Checks how many lives the user has left.
	 * If no lives are left endGame() is run.
	 * If there are remaining lives then the ball is reset to the center and lives are decreased by 1.
	 * </p>
	 */
	public void checkLives() {
		if (gameInterface.lives == 0) endGame();
		else {
			gameInterface.lives--;
			ball.circle.setCenterX(425);
			ball.circle.setCenterY(500);
			ball.moveY *= -1;
		}
		
	}
	
}


