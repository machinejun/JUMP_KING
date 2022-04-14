package jumpking_1;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
public class Player extends JLabel implements Moveable {
	private BackgroundMap backgroundMap;
	
	// 위치 상태
	private int x;
	private int y;

	// 플레이어의 방향
	private PlayerWay playerWay;

	// 움직임 상태
	private boolean left;
	private boolean right;
	private boolean jump;
	private boolean drop;

	// 플레이어 속도 상태
	private final int SPEDD = 4;
	private final int JUMP = 2;

	// 벽에 충돌한 상태
	private boolean LeftWallcrash;
	private boolean RightWallcrash;

	private ImageIcon playerR;
	private ImageIcon playerL;
	private ImageIcon playerWalkingL;
	private ImageIcon jumpLeftmotion;
	private ImageIcon chargeJump;

	public Player(BackgroundMap m) {
		this.backgroundMap = m;
		initObject();
		initsettting();
		initBackgroundPlayerService();
	}

	private void initBackgroundPlayerService() {
		new Thread(new BackgroundPlayerService(this, backgroundMap)).start();
		

	}

	private void initObject() {
		playerR = new ImageIcon("images/chR1.png");
		playerL = new ImageIcon("images/chL1.png");
		playerWalkingL = new ImageIcon("images/leftwalking.png");
		chargeJump = new ImageIcon("images/chjm.png");
	}		
	private void initsettting() {
		x = 300;
		y = 500;

		left = false;
		right = false;
		jump = false;
		drop = false;

		LeftWallcrash = false;
		RightWallcrash = false;

		playerWay = PlayerWay.RIGHT;

		setIcon(playerR);
		setSize(50, 50);
		setLocation(x, y);
	}

	// 이벤트 핸들러
	@Override
	public void left() {
		System.out.println("left");
		playerWay = PlayerWay.LEFT;
		left = true;
		new Thread(new Runnable() {
			@Override
			public void run() {
				while (left) {
					setIcon(playerL);
					x = x - SPEDD;
					
					try {
						Thread.sleep(10);
					} catch (Exception e) {
						e.printStackTrace();

					}
					setLocation(x, y);
					//setIcon(playerL);
				}

			}
		}).start();

	}

	@Override
	public void right() {
		System.out.println("right");
		playerWay = PlayerWay.RIGHT;
		right = true;
		new Thread(new Runnable() {

			@Override
			public void run() {
				while (right) {
					setIcon(playerR);
					x = x + SPEDD;
					
					try {
						Thread.sleep(10);
					} catch (Exception e) {
						e.printStackTrace();
					}
					setLocation(x, y);
				}
			}
		}).start();
	}

	// left + up , right + up
	@Override
	public void jump() {
		System.out.println("up");
		jump = true;
		new Thread(new Runnable() {
			@Override
			public void run() {
				for (int i = 0; i < (180 / JUMP); i++) {
					y = y - JUMP;
					setLocation(x, y);
					try {
						Thread.sleep(3);
					} catch (Exception e) {
						e.printStackTrace();
					}

				}
				jump = false;
				drop();
			}
		}).start();
	}

	@Override
	public void drop() {
		System.out.println("down");
		drop = true;
		new Thread(new Runnable() {

			@Override
			public void run() {
				while (drop) {
					y = y + JUMP;
					setLocation(x, y);
					try {
						Thread.sleep(3);
					} catch (InterruptedException e) {

					}

				}
				drop = false;
			}
		}).start();
	}


	@Override
	public void jumpcharge() {
		setIcon(chargeJump);
		
	}

	
 
}
