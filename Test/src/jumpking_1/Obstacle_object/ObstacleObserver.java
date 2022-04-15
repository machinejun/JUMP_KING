package jumpking_1.Obstacle_object;

import jumpking_1.Player;

public class ObstacleObserver {
	
	public void check(Player player, Obstacle obstacle) {
		if(obstacle.collideRec(player, obstacle.getX(), obstacle.getY())) {
			player.setDrop(false);
		}
	}
}
