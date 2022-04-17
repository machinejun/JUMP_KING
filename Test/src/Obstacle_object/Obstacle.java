package Obstacle_object;

import javax.swing.JLabel;

import jumpking_1.Player;

public abstract class Obstacle extends JLabel{
	boolean direction = true;
	boolean drinking = true;
	
	abstract void addObstacle();
	
	
	
	public boolean isDirection() {
		return direction;
	}



	public void setDirection(boolean direction) {
		this.direction = direction;
	}



	public boolean collideRec(Player player, int x, int y) {
		int Px = player.getX() + (player.getWidth()/2);
		int Py = player.getY() + (player.getHeight()/2);
		
		int Ox = x + (this.getWidth()/2);
		int Oy = y + (this.getHeight()/2);
		
		int xdistance = Math.abs(Ox - Px);
		int ydistance = Math.abs(Oy - Py);
		


		
		if( xdistance < (this.getWidth()/2) && ydistance < (this.getHeight()/2)) {
			return true;
		}else {
			return false;
		}
	}
	
}
