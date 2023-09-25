package application;

import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.shape.Line;
import javafx.scene.text.Font;

/**
 * <p>This class is used to display the text for Time, Lives, and Score at the top of the screen.</p>
 */
public class GameInterface {
	GridPane topInterface = new GridPane();
	
	int score = 0;
	Label scoreDisplay = new Label(Integer.toString(score));
	
	int lives = 3;
	Label livesDisplay = new Label(Integer.toString(lives));
	
	Label elapsedSeconds = new Label("00");
	Label elapsedMinutes = new Label("00");
	long startSeconds = System.nanoTime();
	int minutes = 0;
	
	/**
	 * <p>This method is used to update the text to display how much time has passed since starting the game.</p>
	 */
	public void timer() {
		long seconds = (System.nanoTime() - startSeconds) / 1000000000;
		
		if (seconds == 60 && minutes < 10) {
			minutes += 1;
			startSeconds = System.nanoTime();
			elapsedSeconds.setText(String.valueOf(seconds));
			startSeconds = System.nanoTime();
			elapsedMinutes.setText("0" + String.valueOf(minutes));
		}
		
		if (seconds == 60 && minutes >= 10) {
			minutes += 1;
			startSeconds = System.nanoTime();
			elapsedSeconds.setText(String.valueOf(seconds));
			startSeconds = System.nanoTime();
			elapsedMinutes.setText(String.valueOf(minutes));
		}
		
		if (minutes < 10) elapsedMinutes.setText("0" + String.valueOf(minutes));
		else elapsedMinutes.setText(String.valueOf(minutes));
		
		if (seconds < 10) elapsedSeconds.setText("0" + String.valueOf(seconds));
		else elapsedSeconds.setText(String.valueOf(seconds));
	}
	
	/**
	 * <p>Updates the score.</p>
	 */
	public void updateScore() {
		scoreDisplay.setText(Integer.toString(score * 100));
	}
	
	/**
	 * <p>Updates the lives.</p>
	 */
	public void updateLives() {
		livesDisplay.setText(Integer.toString(lives));
	}
	
	/**
	 * <p>This method sets the color, font, size, and arranges the text for the top interface.</p>
	 */
	public void createInterface() {
		Line line = new Line(0, 100 , 840, 100);
		line.setStyle("-fx-stroke: orange;");
		line.setStrokeWidth(2.5);
		
		HBox textInterface = new HBox();
		topInterface.add(textInterface, 0, 0);
		topInterface.add(line, 0, 1);
		
		textInterface.setPadding(new Insets(5));
		Label text1 = new Label(" Time: ");
		Label text2 = new Label(":");
		Label text3 = new Label("Lives: ");
		Label text4 = new Label("Score: ");
		double fontSize = 22;
		
		textInterface.getChildren().add(text1);
		textInterface.getChildren().add(elapsedMinutes);
		textInterface.getChildren().add(text2);
		textInterface.getChildren().add(elapsedSeconds);
		textInterface.getChildren().add(new Label("                                                                              "));
		textInterface.getChildren().add(text3);
		textInterface.getChildren().add(livesDisplay);
		textInterface.getChildren().add(new Label("                                                                              "));
		textInterface.getChildren().add(text4);
		textInterface.getChildren().add(scoreDisplay);
		
		text1.setStyle("-fx-text-fill: orange;");
		text2.setStyle("-fx-text-fill: orange;");
		text3.setStyle("-fx-text-fill: orange;");
		text4.setStyle("-fx-text-fill: orange;");
		elapsedMinutes.setStyle("-fx-text-fill: orange;");
		elapsedSeconds.setStyle("-fx-text-fill: orange;");
		scoreDisplay.setStyle("-fx-text-fill: orange;");
		livesDisplay.setStyle("-fx-text-fill: orange;");
		text1.setFont(new Font("Arial", fontSize));
		text2.setFont(new Font("Arial", fontSize));
		text3.setFont(new Font("Arial", fontSize));
		text4.setFont(new Font("Arial", fontSize));
		elapsedMinutes.setFont(new Font("Arial", fontSize));
		elapsedSeconds.setFont(new Font("Arial", fontSize));
		scoreDisplay.setFont(new Font("Arial", fontSize));
		livesDisplay.setFont(new Font("Arial", fontSize));
	}
	
}