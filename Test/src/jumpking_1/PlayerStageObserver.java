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
			if( 0 <= i && i <= 5) {
				if (player.getY() <= 50 && backgroundMap.getStageNum() == i) {
					backgroundMap.setStageNum(++i);
					backgroundMap.changeStage();
					player.setY(910);
				} else if (player.getY() >= 950 && backgroundMap.getStageNum() == i) {
					backgroundMap.setStageNum(--i);
					backgroundMap.changeStage();
					player.setY(70);	
				} 
			}
			
			// 5스테이지 일때 조건
//			if(player.getX() == 600 && player.getY() == 400) {
//				backgroundMap.setStageNum(6);
//				backgroundMap.changeStage();
//			}
			

			try {
				Thread.sleep(8);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

	}

}
