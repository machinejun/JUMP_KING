package jumpking_1;

public class PlayerStageObserver implements Runnable {
	Player player;
	BackgroundMap backgroundMap;

	public PlayerStageObserver(Player player, BackgroundMap backgroundMap) {
		this.player = player;
		this.backgroundMap = backgroundMap;

	}

	@Override
	public void run() {

		while (true) {
			int i = 1;
			if( 0 <= i && i <= 6) {
				if (player.getY() <= 50 && backgroundMap.getStageNum() == 1) {
					backgroundMap.setStageNum(2);
					backgroundMap.changeStage();
					player.setY(910);
				} else if (player.getY() >= 950 && backgroundMap.getStageNum() == 2) {
					backgroundMap.setStageNum(1);
					backgroundMap.changeStage();
					player.setY(60);	
				} else if (player.getY() <= 50 && backgroundMap.getStageNum() == 2) {
					backgroundMap.setStageNum(3);
					backgroundMap.changeStage();
					player.setY(910);
				} else if (player.getY() >= 950 && backgroundMap.getStageNum() == 3) {
					backgroundMap.setStageNum(2);
					backgroundMap.changeStage();
					player.setY(70);	
				} else if (player.getY() <= 50 && backgroundMap.getStageNum() == 3) {
					backgroundMap.setStageNum(4);
					backgroundMap.changeStage();
					player.setY(910);
				} else if (player.getY() >= 950 && backgroundMap.getStageNum() == 4) {
					backgroundMap.setStageNum(3);
					backgroundMap.changeStage();
					player.setY(70);	
				} else if (player.getY() <= 50 && backgroundMap.getStageNum() == 4) {
					backgroundMap.setStageNum(5);
					backgroundMap.changeStage();
					player.setY(910);
				} else if (player.getY() >= 950 && backgroundMap.getStageNum() == 5) {
					backgroundMap.setStageNum(4);
					backgroundMap.changeStage();
					player.setY(70);
				}  

			
			}
			

			try {
				Thread.sleep(8);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

	}

}
