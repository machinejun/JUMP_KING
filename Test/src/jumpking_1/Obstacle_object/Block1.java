package jumpking_1.Obstacle_object;

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
	private int x = 628;
	private int y = 750;
	private Player player;
	
	
	
	public Block1() {
		player = Player.getInstance(backgroundMap);
		backgroundMap = player.getBackgroundMap();
		observer = new ObstacleObserver();
		addObstacle();
	}
	
	@Override
	void addObstacle() {
		block1 = new ImageIcon("images/cloude.png");
		setSize(194,47);
		setLocation(x,y);
		setIcon(block1);
		controlBlock();
	}
	
	public void controlBlock(){
		new Thread(new Runnable() {
			@Override
			public void run() {
				boolean direction = true;
				
				while(backgroundMap.getStageNum() == 2) {
					if (direction) {
						x += 10;
					} else {
						x -= 10;
					}

					if (x >= 800) {
						System.out.println("wh");
						direction = false;
					}

					if (x <= 130) {
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
