package Obstacle_object;

import javax.swing.ImageIcon;

import jumpking_1.BackgroundMap;
import jumpking_1.Player;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
public class Waterpill extends Obstacle {

	private Waterpill redpotion = this;
	private ObstacleObserver observer;
	private BackgroundMap backgroundMap;
	private ImageIcon potion;
	private int x;
	private int y;
	private Player player;
	private boolean drink = true;

	public Waterpill(int x, int y) {
		this.x = x;
		this.y = y;
		player = Player.getInstance(backgroundMap);
		backgroundMap = player.getBackgroundMap();
		observer = new ObstacleObserver();
		addObstacle();

	}

	@Override
	void addObstacle() {
		potion = new ImageIcon("images/pill.png");
		setSize(30, 50);
		setLocation(x, y);
		setIcon(potion);
		veiw();

	}

	public void veiw() {
		drink = true;
		new Thread(new Runnable() {
			
			@Override
			public void run() {
				
				while (backgroundMap.getStageNum() == 2 ) {
					drink(player);
					try {
						Thread.sleep(100);
					} catch (InterruptedException e) {
					
						e.printStackTrace();
					}
					
				}
				
				drink = false;
				setIcon(null);
				remove(redpotion);
			}
		}).start();

	}

	private void drink(Player player) {
		observer.drink(player, this);

	}
}
