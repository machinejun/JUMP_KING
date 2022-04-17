package jumpking_1;

import Obstacle_object.Obstacle;

public interface Moveable {
	void left();
	void right();
	void jump();
	void drop();
	void rideCloude(Obstacle obstacle, int x,boolean direction);
	int jumpup();
}
