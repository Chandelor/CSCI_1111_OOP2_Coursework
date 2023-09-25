# Brick Breaker

## Synopsis
Brick Breaker is a game where a player uses the left/right arrow keys to move a paddle that is used to bounce a ball around the screen. The main goal of the game is to get the ball to break bricks that are spread around until there are none left.

## Motivation
I decided on this for my final project because I felt that it would help me learn a bunch of new code and really test most of the things I have learned so far.

## How to Run
To run the Brick Breaker game you will need to download the project folder and import it to your code editors workspace. After that's been done all that's left is to run the program since all the necessary files are already in the project folder. 
[Game Image](BrickBreakerScreen.png)

## Code Example
Shown below is an example of the code I used for the games collision. The collision was one of the more difficult aspects of this project since I had no prior knowledge of how to do collision, so it was a big step for me when I actually got it working.
```
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
	
	public void ballCollision() {
		boolean hitRight = ball.circle.getCenterX() >= scene.getWidth() - ball.circle.getRadius();
		boolean hitLeft = ball.circle.getCenterX() <= ball.circle.getRadius();
		boolean hitTop = ball.circle.getCenterY() <= ball.circle.getRadius() - 2;
		boolean hitBottom = ball.circle.getCenterY() >= ((scene.getHeight() - 40) - ball.circle.getRadius());
		
		if (hitRight || hitLeft) ball.moveX *= -1;
		if (hitTop) ball.moveY *= -1;
		if (hitBottom) checkLives();
	}
	
	public void paddleCollision(Ball ball) {
		if (ball.circle.intersects(paddle.paddle.getBoundsInParent())) ball.moveY *= -1;
	}
```

## Tests
For the assignment I didn't need to use JUnit for the testing. To test the examples I've shown you will need a circle and two rectangle objects, one rectangle to act as the paddle and the other for the brick, with the circle acting as the ball that will be bouncing around the screen. You will need to set up a Timeline/AnimationTimer when running these checks and for moving the ball.

## Contributors
The majority of the code was done but me, but I did get an idea of how to run the collision from the youtube channel Random Code.
Channel Link: https://www.youtube.com/@Randomcode_0