package Obstacle_object;

import javax.swing.ImageIcon;

import jumpking_1.BackgroundMap;
import jumpking_1.Player;

public class ObstacleObserver {

	public void check(Player player, Obstacle obstacle) {
		if (obstacle.collideRec(player, obstacle.getX(), obstacle.getY())) {

			if (player.getY() < obstacle.getY()) {

				player.setDrop(false);
				int x = player.getX() - obstacle.getX();
				if (!player.isRide()) {
					player.rideCloude(obstacle, x, obstacle.isDirection());
				}
			} else {
				if (player.isJump())
					player.drop();
			}
		}
	}

	public void drink(Player player, Waterpill obstacle) {
		int x = obstacle.getX();
		int y = obstacle.getY();
		if (obstacle.collideRec(player, x, y)) {

			if (player.getX() > obstacle.getX() - 25 && player.getX() < obstacle.getX() + obstacle.getWidth()) {
				obstacle.setDrink(false);
				obstacle.setVisible(false);
				player.jumpup();

			}
		}
	}

	public void save(Player player, Princess obstacle, BackgroundMap backgroundMap) {
		int x = obstacle.getX();
		int y = obstacle.getY();
		if (obstacle.collideRec(player, x, y)) {
			if (player.getX() > obstacle.getX() - 25 && player.getX() < obstacle.getX() + obstacle.getWidth()) {
				obstacle.setSave(false);
				obstacle.setVisible(false);
				backgroundMap.setStageNum(6);
				backgroundMap.changeStage();
				player.setVisible(false);
			}
		}
	}

	public void monsterhit(Player player, Obstacle obstacle, BackgroundMap backgroundMap) {
		if (obstacle.collideRec(player, obstacle.getX(), obstacle.getY())) {
			// 충돌이 나면
			if (player.getX() < obstacle.getX()) {// 플레이어의 x가 더 작으면
				player.hit(obstacle);
			} else if (player.getX() > obstacle.getX()) {
				player.hit(obstacle);
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				player.setVisible(false);
				obstacle.setVisible(false);
				backgroundMap.setStageNum(7);
				backgroundMap.changeStage();
			}
		}

	}
}