package jumpking_1;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class BubbleFrame extends JFrame {

	private JLabel backgroundMap;
	private Player player;
	
	public BubbleFrame() {
		initObject();
		initSetting();
		initListener();
		setVisible(true);
	}

	

	private void initObject() {
		backgroundMap = new JLabel(new ImageIcon("images/stage1.png"));
		setContentPane(backgroundMap);
		player = new Player();
		add(player);
		
	}

	private void initSetting() {
		setSize(1440,940);
		setLayout(null); // absoulte (좌표값으로 자유롭게 그림을 그릴 수 있다.)

		setLocationRelativeTo(null); // JFrame 가운데 배치 하기
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

	}

	private void initListener() {
		addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				System.out.println(e.getKeyCode());

				switch (e.getKeyCode()) {
				case KeyEvent.VK_LEFT:
					if(!player.isLeft() && !player.isLeftWallcrash()){ 
						player.left();
					}
					
					break;
				case KeyEvent.VK_RIGHT:
					if(!player.isRight() && !player.isRightWallcrash()){
						player.right();
					}
					break;
				case KeyEvent.VK_UP:
			    
					break;
				case KeyEvent.VK_SPACE:
					player.getChargeJump();
					   
					System.out.println(player.getPlayerWay());
					if(PlayerWay.LEFT == player.getPlayerWay()) {
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
					if(!player.isJump() && !player.isDrop()) {
						 player.jump();
					}
					break;
//				case KeyEvent.VK_UP:
//					player.setUp(false);
//					break;
//				case KeyEvent.VK_DOWN:
//					player.setDown(false);
//					break;
//				default:
//					break;
				}
			}

		});
	}

	public static void main(String[] args) {
		new BubbleFrame();
	}

}
