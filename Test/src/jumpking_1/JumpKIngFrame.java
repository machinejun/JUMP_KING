package jumpking_1;

import java.awt.BorderLayout;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class JumpKIngFrame extends JFrame {
	private BackgroundMap backgroundMap;
	private Player player;

	public JumpKIngFrame() {

		initObject();
		initSetting();
		initListener();
		new Thread(new PlayerStageObserver(player, backgroundMap)).start();

	}

	private void initObject() {
		backgroundMap = new BackgroundMap();
		player = new Player(backgroundMap);
		setLayout(null);
		setContentPane(backgroundMap);
		backgroundMap.add(player);

	}

	private void initSetting() {
		setVisible(true);
		setSize(1400, 1040);
		setResizable(false);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

	}

	private void initListener() {
		addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				System.out.println(e.getKeyCode());

				switch (e.getKeyCode()) {
				case KeyEvent.VK_LEFT:
					if (!player.isLeft() && !player.isLeftWallcrash()) {
						player.left();
					}

					break;
				case KeyEvent.VK_RIGHT:
					if (!player.isRight() && !player.isRightWallcrash()) {
						player.right();
					}
					break;
				case KeyEvent.VK_UP:

					break;
				case KeyEvent.VK_SPACE:
//					if (player.isLeft() && player.isLeftjumpWallcrash()) {
//						player.jumpL();
//					}
					System.out.println("점프");
					player.getChargeJump();

					System.out.println(player.getPlayerWay());
					if (PlayerWay.LEFT == player.getPlayerWay()) {
						System.out.println("왼쪽 방향을 보고 있습니다.");
					} else {
						System.out.println("오른쪽 방향을 보고 있습니다.");
					}
					break;
				}

			} // end of keyPressd
				// 키보드 화살표 해제 이벤트 처리

			@Override
			public void keyReleased(KeyEvent e) {
				switch (e.getKeyCode()) {
				case KeyEvent.VK_LEFT:
					player.setLeft(false);
					break;
				case KeyEvent.VK_RIGHT:
					player.setRight(false);
					break;
				case KeyEvent.VK_SPACE:
					if (!player.isJump() && !player.isDrop()) {
						player.jump();
					}
					break;

				}
			}

			

		});
	}

	public static void main(String[] args) {
		new JumpKIngFrame();
	}

}
