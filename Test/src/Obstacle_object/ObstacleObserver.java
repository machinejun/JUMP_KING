package Obstacle_object;

import javax.swing.ImageIcon;

import jumpking_1.BackgroundMap;
import jumpking_1.Player;

public class ObstacleObserver {
	
	public void check(Player player, Obstacle obstacle) {
		System.out.println(obstacle.getX());
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
	public void drink(Player player, Waterpill obstacle) {
		System.out.println(obstacle.getX() + "/" + obstacle.getY());
		int x = obstacle.getX();
		int y = obstacle.getY(); 
		System.out.println(obstacle.getWidth());
		if(obstacle.collideRec(player, x, y)) {
			System.out.println("충돌확인");
			if(player.getX() > obstacle.getX() - 25 && player.getX() < obstacle.getX() + obstacle.getWidth()) {
				obstacle.setDrink(false);
				obstacle.setVisible(false);
				player.jumpup();
				System.out.println("점프업완료");
			}
		}
	}
	public void save(Player player, Princess obstacle, BackgroundMap backgroundMap) {
		int x = obstacle.getX();
		int y = obstacle.getY(); 
		if(obstacle.collideRec(player, x, y)) {
			if(player.getX() > obstacle.getX() - 25 && player.getX() < obstacle.getX() + obstacle.getWidth()) {
				obstacle.setSave(false);
				obstacle.setVisible(false);
				backgroundMap.setStageNum(6);
				backgroundMap.changeStage();
				player.setVisible(false);
	}
}
}
}