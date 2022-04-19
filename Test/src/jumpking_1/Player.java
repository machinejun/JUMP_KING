package jumpking_1;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

import Obstacle_object.Block1;
import Obstacle_object.Obstacle;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
public class Player extends JLabel implements Moveable {
	private static Player instance;
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
	private boolean ride;
	private boolean behit;
	private int jumpup = 1;

	// 플레이어 속도 상태
	private final int SPEED = 2;
	private final int DROPSPEED = 2;
	private int JUMPPOWER; // Drop , jump 값은 변수선언

	// 벽에 충돌한 상태
	private boolean LeftWallcrash;
	private boolean RightWallcrash;
	private boolean RightjumpWallcrash;
	private boolean LeftjumpWallcrash;

	private ImageIcon[] playerR = { new ImageIcon("images/chR1.png"), // 0(오른쪽보는)
									new ImageIcon("images/RS.png"),   // 1(중간다리)
									new ImageIcon("images/RRL.png"),  // 2(왼쪽다리)
									new ImageIcon("images/RRR.png") };// 3(오른쪽다리)

	private ImageIcon[] playerL = { new ImageIcon("images/chL1.png"),  // 0(왼쪽쪽보는)
									new ImageIcon("images/LS.png"),    // 1(중간다리)
									new ImageIcon("images/LLL.png"),   // 2(오른쪽다리)
									new ImageIcon("images/LLR.png") }; // 3(왼쪽다리)
	private ImageIcon playerWalkingL;

	private ImageIcon[] jumpLeftmotion = { new ImageIcon("images/jmplm.png"), // (왼쪽 점프)
										   new ImageIcon("images/jmdlm.png") };// (왼쪽 다운)

	private ImageIcon[] jumpRightmotion = { new ImageIcon("images/jmplmR.png"), // (오른쪽 점프)
											new ImageIcon("images/jmdlmR.png") };// (오른쪽 다운);
	private ImageIcon chargeJump;
	private ImageIcon downR;

	public static Player getInstance(BackgroundMap m) {
		if (instance == null) {
			instance = new Player(m);
		}

		return instance;
	}

	private Player(BackgroundMap m) {
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
		x = 200;
		y = 600;
		// 480, 800
		left = false;
		right = false;
		jump = false;
		stand = false;
		drop = false;
		ride = false;
		behit = false;

		LeftWallcrash = false;
		RightWallcrash = false;
		RightjumpWallcrash = false;
		LeftjumpWallcrash = false;

		setIcon(playerR[0]);
		setSize(50, 50);
		setLocation(x, y);
	}

	// 이벤트 핸들러
	@Override
	public void left() {
		left = true;
		new Thread(new Runnable() {
			@Override
			public void run() {
				while (left) {
					for (int i = 0; i < 4 ; i++) {
						setIcon(playerL[i]);
						x = x - SPEED;
						setLocation(x, y);
						try {
							Thread.sleep(5);
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
		right = true;
		new Thread(new Runnable() {
			@Override
			public void run() {
				while (right) {
					for (int i = 0; i < 4; i++) {
						setIcon(playerR[i]);
						x = x + SPEED;
						setLocation(x, y);
						try {
							Thread.sleep(5);
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
				setIcon(jumpRightmotion[0]);
				for (int i = 0; i < 35; i++) {
					y = y - (5 * jumpup);
					setLocation(x, y);
					try {
						Thread.sleep(15);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
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
		drop = true;
		JUMPPOWER = 2;
		new Thread(new Runnable() {
			@Override
			public void run() {
				while (drop) {
					y = y + 5;
					setLocation(x, y);
					setJump(false);
					try {
						Thread.sleep(20);
					} catch (InterruptedException e) {

					}
				}
				drop = false;
			}
		}).start();
	}

	@Override
	public void rideCloude(Obstacle obstacle, int Xdistance, boolean Odirection) {
		jump = false;
		drop = false;
		ride = true;
		new Thread(new Runnable() {
			@Override
			public void run() {
				do {
					ride = obstacle.collideRec(instance, obstacle.getX(), obstacle.getY());			
					setLocation(obstacle.getX() + Xdistance, obstacle.getY() - 10);
					try {
						Thread.sleep(100);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}

				} while (ride);
			}
		}).start();

	}

	@Override
	public int jumpup() {
		jumpup = 2;
		return 0;
	}

	@Override
	public void hit(Obstacle obstacle) {
		behit = true;
		jump = false;
		drop = false;
		new Thread(new Runnable() {
			@Override
			public void run() {
				for (int i = 0; i < 30; i++) {// 장애물 왼쪽 부딪힘
					if ((x + 50) <  obstacle.getX() +(obstacle.getWidth()/2)) {	
						x = x - 2;
						y = y - 6;
					} else if (x > obstacle.getX() +(obstacle.getWidth()/2)) {
						x = x + 2;
						y = y - 6;
					}

					try {
						Thread.sleep(5);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					setLocation(x, y);
				}
				behit = false;
				

			}
		}).start();
		
	}
}
