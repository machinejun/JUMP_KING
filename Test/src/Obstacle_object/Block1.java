package Obstacle_object;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.text.html.HTMLDocument.HTMLReader.HiddenAction;

import jumpking_1.BackgroundMap;
import jumpking_1.Player;
import lombok.Getter;
import lombok.Setter;
@Getter
@Setter
public class Block1 extends Obstacle{
	private Block1 block12 = this;
	private ObstacleObserver observer;
	private BackgroundMap backgroundMap;
	private ImageIcon block1;
	private int x ;
	private int y ;
	private Player player;
	
	
	
	public Block1(int x, int y) {
		this.x = x;
		this.y = y;
		player = Player.getInstance(backgroundMap);
		backgroundMap = player.getBackgroundMap();
		observer = new ObstacleObserver();
		addObstacle();
	}
	
	@Override
	void addObstacle() {
		block1 = new ImageIcon("images/cloude.png");
		setSize(200,50);
		setLocation(x,y);
		setIcon(block1);
		controlBlock();
	}
	
	public void controlBlock(){
		new Thread(new Runnable() {
			@Override
			public void run() {

				direction = true;
				
				while(backgroundMap.getStageNum() == 3) {
					if (direction) {
						x += 5;
					} else {
						x -= 5;
					}

					if (x >= 900) {
						System.out.println("wh");
						direction = false;
					}

					if (x <= 350) {
						direction = true;
					}
					check(player);
					setLocation(x, y);

					try {
						Thread.sleep(100);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
				setIcon(null);
				remove(block12);
			}
		}).start();
	}
	
	private void check(Player player) {
		observer.check(player, this);	
	}
	
	

}
