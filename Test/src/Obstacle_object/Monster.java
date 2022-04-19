package Obstacle_object;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

import jumpking_1.BackgroundMap;
import jumpking_1.Player;
import lombok.Data;

@Data
public class Monster extends Obstacle{

	private boolean hitting = true;
	private Monster monster1 = this;
	private int x;
	private int y;
	private JLabel jLabel;
	private ImageIcon monsterIcon = new ImageIcon("images/monster1.png");
								    
//	                             
	private BackgroundMap backgroundMap;
	private Player player; 
	private ObstacleObserver observer;
	
	public Monster(int x, int y) {
		this.x = x;
		this.y = y;
		player = Player.getInstance(backgroundMap);
		backgroundMap = player.getBackgroundMap();
		observer = new ObstacleObserver();
		addObstacle();
		
	}
	@Override
	void addObstacle() { 
		
		setSize(100, 100);
		setLocation(x , y);
		setIcon(monsterIcon);
		
		MonsterLc();
	}
    public void MonsterLc() {
    	hitting = true;
    	new Thread(new Runnable() {
			
			@Override
			public void run() {
				direction = true;
				while(backgroundMap.getStageNum() == 5) {				
					if (direction) {
						x += 2;
					} else {
						x -= 2;
					}

					if (x >= 850) {
						
						direction = false;
					}

					if (x <= 350) {
						direction = true;
					}
	
					monsterhit(player, backgroundMap);
					setLocation(x , y);
					try {
						Thread.sleep(10);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				hitting = false;
				setIcon(null);
				remove(monster1);
			}
		}).start();
    }
    private void monsterhit(Player player, BackgroundMap backgroundMap) {
    	observer.monsterhit(player, this, backgroundMap);
    }
	
}