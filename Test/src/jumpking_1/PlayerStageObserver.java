package jumpking_1;

public class PlayerStageObserver implements Runnable{
	Player player;
	BackgroundMap backgroundMap;
	
	public PlayerStageObserver(Player player, BackgroundMap backgroundMap) {
		this.player =player;
		this.backgroundMap = backgroundMap;
		
	}
	

	@Override
	public void run() {
		
		while(true) {
			System.out.println(player.getY());
			System.out.println("스테이지: " +backgroundMap.getStageNum());
			if(player.getY() < 50 && backgroundMap.getStageNum() == 1) {
				System.out.println("working");
				player.setY(900);
	            backgroundMap.setStageNum(2);
	            backgroundMap.changeStage();
	            
			}
		}
		
	}

}
