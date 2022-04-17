package Obstacle_object;

import javax.swing.ImageIcon;

import jumpking_1.BackgroundMap;
import jumpking_1.Player;
import lombok.Data;
@Data
public class Princess extends Obstacle {

	private Princess princess = this;
	private ObstacleObserver observer;
	private BackgroundMap backgroundMap;
	private ImageIcon princesss;
	private int x;
	private int y;
	private Player player;
	private boolean save = true;

	public Princess(int x, int y) {
		this.x = x;
		this.y = y;
		player = Player.getInstance(backgroundMap);
		backgroundMap = player.getBackgroundMap();
		observer = new ObstacleObserver();
		addObstacle();
	}

	@Override
	void addObstacle() {
		princesss = new ImageIcon("images/Princess.png");
		setSize(74, 84);
		setLocation(x, y);
		setIcon(princesss);
		her();
	}

	public void her() {
		save = true;

		new Thread(new Runnable() {

			@Override
			public void run() {

				while (backgroundMap.getStageNum() == 5) {
					save(player);
					try {
						Thread.sleep(100);
					} catch (InterruptedException e) {

						e.printStackTrace();
					}

				}

				save = false;
				setIcon(null);
				remove(princess);
			}
		}).start();

	}

	private void save(Player player) {
		observer.save(player, this, backgroundMap);

	}

}
