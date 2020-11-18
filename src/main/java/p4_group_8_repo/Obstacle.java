package p4_group_8_repo;

import javafx.scene.image.Image;

public abstract class Obstacle extends Actor {
	private int speed;
	
	//Constructor
	public Obstacle(int x, int y, int speed) {

		setX(x);
		setY(y);
		this.speed = speed;
		
	}

	//Action when method called
	@Override
	public void act(long now) {
		move(speed , 0);
		KeepWithinWindow(speed);
	}
	
	//Keep obstacle within game window borders
	private void KeepWithinWindow(int speed) {
		
		if (getX() > 600 && speed>0)
			setX(-200);
		if (getX() < -50 && speed<0)
			setX(600);
		
	}
	
}