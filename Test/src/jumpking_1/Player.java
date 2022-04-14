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
	private boolean stand;
	private boolean drop;

	// 플레이어 속도 상태
	private final int SPEED = 2;
	private final int DROPSPEED = 2;
	private int JUMPPOWER; // Drop , jump 값은 변수선언
	private int vector = 1;

	// 벽에 충돌한 상태
	private boolean LeftWallcrash;
	private boolean RightWallcrash;
	private boolean RightjumpWallcrash;
	private boolean LeftjumpWallcrash;

	private ImageIcon[] playerR = { new ImageIcon("images/chR1.png"), // 0(오른쪽보는)
			new ImageIcon("images/RS.png"), // 1(중간다리)
			new ImageIcon("images/RRL.png"), // 2(왼쪽다리)
			new ImageIcon("images/RRR.png") };// 3(오른쪽다리)

	private ImageIcon[] playerL = { new ImageIcon("images/chL1.png"), // 0(왼쪽쪽보는)
			new ImageIcon("images/LS.png"), // 1(중간다리)
			new ImageIcon("images/LLL.png"), // 2(오른쪽다리)
			new ImageIcon("images/LLR.png") }; // 3(왼쪽다리)
	private ImageIcon playerWalkingL;

	private ImageIcon[] jumpLeftmotion = { new ImageIcon("images/jmplm.png"), // (왼쪽 점프)
			new ImageIcon("images/jmdlm.png") };// (왼쪽 다운)

	private ImageIcon[] jumpRightmotion = { new ImageIcon("images/jmplmR.png"), // (오른쪽 점프)
			new ImageIcon("images/jmdlmR.png") };// (오른쪽 다운);
	private ImageIcon chargeJump;
	private ImageIcon downR;

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
		chargeJump = new ImageIcon("images/chjm.png");
	}

	private void initsettting() {
		x = 300;
		y = 500;

		left = false;
		right = false;
		jump = false;
		stand = false;
		drop = false;

		LeftWallcrash = false;
		RightWallcrash = false;
		RightjumpWallcrash = false;
		LeftjumpWallcrash = false;

		playerWay = PlayerWay.NULL;

		setIcon(playerR[0]);
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
					for (int i = 1; i < 4; i++) {
						setIcon(playerL[i]);
						x = x - SPEED / 2;
						setLocation(x, y);
						try {
							Thread.sleep(3);
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
					}
				}
				left = false;

				setIcon(playerL[0]);

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
					for (int i = 1; i < 4; i++) {
						setIcon(playerR[i]);
						x = x + SPEED / 2;
						setLocation(x, y);
						try {
							Thread.sleep(3);
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
					}
				}

				right = false;

				setIcon(playerR[0]);

			}
		}).start();
	}

	// left + up , right + up
	@Override
	public void jump() {
		jump = true;
		new Thread(new Runnable() {
			@Override
			public void run() {
				
				if (playerWay == PlayerWay.NULL) {
					for (int i = 0; i < 45; i++) {
						y = y - 4;
						setLocation(x, y);
						try {
							Thread.sleep(20);
						} catch (InterruptedException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
				}

				if (playerWay == PlayerWay.RIGHT) {
					setIcon(jumpRightmotion[0]);
					// x 120 y 240
					for (int i = 0; i < 30; i++) {				
						if (i / 10 == 0) {
							x = x + (4);
							y = y - 10;
						} else if (i / 10 == 1) {
							x = x + (4);
							y = y - 6;
						} else if (i / 10 == 2) {
							x = x + (4);
							y = y - 2;
						}
						setLocation(x, y);
						try {
							Thread.sleep(20);
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
					}	
				}
				
				
				if (playerWay == PlayerWay.LEFT) {
					setIcon(jumpRightmotion[0]);
					// x 120 y 240
					for (int i = 0; i < 30; i++) {
							if (i / 10 == 0) {
								x = x - (4);
								y = y - 10;
							} else if (i / 10 == 1) {
								x = x - (4);
								y = y - 6;
							} else if (i / 10 == 2) {
								x = x - (4);
								y = y - 2;
							}
							setLocation(x, y);
							try {
								Thread.sleep(10);
							} catch (InterruptedException e) {
								e.printStackTrace();
							}
						}
				}
				jump = false;
				drop();
			}
		}).start();

	}

	@Override
	public void jumpcharge() {

	}

	@Override
	public void drop() {
		if(drop == true) {
			return;
		}
		System.out.println("down");
		drop = true;
		JUMPPOWER = 2;
		new Thread(new Runnable() {
			@Override
			public void run() {
				while (drop) {
					if (playerWay == PlayerWay.NULL) {
						y = y + 4;
					} else if (playerWay == PlayerWay.RIGHT) {
						x = x + 2;
						y = y + 6;
					} else if (playerWay == PlayerWay.LEFT) {
						x = x - 2;
						y = y + 6;
					}

					setLocation(x, y);
					try {
						Thread.sleep(10);
					} catch (InterruptedException e) {

					}

				}
				drop = false;
			}
		}).start();
	}
}
