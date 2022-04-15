package jumpking_1.Obstacle_object;

import jumpking_1.Player;

public class ObstacleObserver {
	
	public void check(Player player, Obstacle obstacle) {
		if(obstacle.collideRec(player, obstacle.getX(), obstacle.getY())) {
			System.out.println("충돌");
			if(player.getY()< obstacle.getY()) {
				System.out.println("멈춤");
				player.setDrop(false);
				int x = player.getX()  - obstacle.getX(); 
				if(!player.isRide()) {
					player.rideCloude(obstacle, x ,obstacle.isDirection());
				}
			}else {
				if(player.isJump()) player.drop();
			}
		}
	}
}
