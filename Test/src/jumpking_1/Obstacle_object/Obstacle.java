package jumpking_1.Obstacle_object;

import javax.swing.JLabel;

import jumpking_1.Player;

public abstract class Obstacle extends JLabel{
	boolean flag = true;

	
	abstract void addObstacle();
	
	public boolean isFlag() {
		return flag;
	}
	public void setFlag(boolean flag) {
		this.flag = flag;
	}
	
	public boolean collideRec(Player player, int x, int y) {
		double PCx = player.getX() + (player.getWidth()/2);
		double PCy = player.getY() + (player.getHeight()/2);
		
		double Ox = x + (this.getWidth()/2);
		double Oy = y + (this.getHeight()/2);
		
		double d = Math.scalb((Ox - PCx), 2) + Math.scalb((Oy - PCy), 2);
		double distance = Math.sqrt(d);
		
		double g = (PCy - Oy) / (Ox - PCx);
		double digree = Math.atan(g);

		
		if( (Ox - PCx) <= distance * Math.sin(digree) && (PCy - Oy) <= distance * Math.cos(digree)) {
			return true;
		}else {
			return false;
		}
	}
	
}
