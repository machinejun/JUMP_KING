package jumpking_1;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.RepaintManager;

// 메인 쓰레드는 바쁨 -> 키보드 이벤트 처리를 해야하기 때문
// 백그라운드에서 계속 관찰
public class BackgroundPlayerService implements Runnable {
	private int stage;
	private BufferedImage image;
	private BufferedImage image1;
	private BufferedImage image2;
	private BufferedImage image3;
	private BufferedImage image4;
	private Player player;
	private BackgroundMap backgroundMap;

	public BackgroundPlayerService(Player player, BackgroundMap backgroundMap) {
		this.player = player;
		this.backgroundMap = backgroundMap;
		try {
			image1 = ImageIO.read(new File("images/stage1bg.png"));	//1번맵
			image2 = ImageIO.read(new File("images/stage2bg.png")); // 2번 맵
			// image3 = ImageIO.read(new File("images/stage3bg.png")); // 3번맵
			// image4 = ImageIO.read(new File("images/stage4bg.png")); // 4번맵
		} catch (IOException e) {
			e.printStackTrace();
		}
		image = image1;
	}

	@Override
    public void run() {

        while (true) {
        	if(backgroundMap.getStageNum() == 1) {
        		image = image1;
        	}else if(backgroundMap.getStageNum() == 2) {
        		image = image2;
        	}
        	
        	Color leftFootColor;
        	Color leftCenterColor;
			Color leftTopColor; 
			Color CenterFootColor ;
			Color CenterTopColor ;
			Color rightFootColor ;
			Color rightCenterColor;
			Color rightTopColor ;
        	
        	
        	 // 자바의 좌표 기준은 왼쪽 위, 아이콘 크기 50, 50
        	try {
        		leftFootColor = new Color(image.getRGB(player.getX() + 0, player.getY() + 50 )); //
            	leftCenterColor = new Color(image.getRGB(player.getX() + 0, player.getY() + 35)); //
    			leftTopColor = new Color(image.getRGB(player.getX() + 0, player.getY()));
    			CenterFootColor = new Color(image.getRGB(player.getX() + 25, player.getY() + 40));
    			CenterTopColor = new Color(image.getRGB(player.getX() + 25, player.getY()));
    			rightFootColor = new Color(image.getRGB(player.getX() + 25 + 25, player.getY() + 50));
    			rightCenterColor = new Color(image.getRGB(player.getX() + 25 + 25, player.getY() + 35));
    			rightTopColor = new Color(image.getRGB(player.getX() + 25 + 25, player.getY()));
			} catch (ArrayIndexOutOfBoundsException e) {
				try {
					Thread.sleep(16);
				} catch (InterruptedException e1) {
					e1.printStackTrace();
				}
				leftFootColor = new Color(image.getRGB(player.getX() + 0, player.getY() + 50 )); //
	        	leftCenterColor = new Color(image.getRGB(player.getX() + 0, player.getY() + 35)); //
				leftTopColor = new Color(image.getRGB(player.getX() + 0, player.getY()));
				CenterFootColor = new Color(image.getRGB(player.getX() + 25, player.getY() + 40));
				CenterTopColor = new Color(image.getRGB(player.getX() + 25, player.getY()));
				rightFootColor = new Color(image.getRGB(player.getX() + 25 + 25, player.getY() + 50));
				rightCenterColor = new Color(image.getRGB(player.getX() + 25 + 25, player.getY() + 35));
				rightTopColor = new Color(image.getRGB(player.getX() + 25 + 25, player.getY()));
			}
        	
             

             
            // 바닥 검사
            if (leftFootColor.getRGB() != Color.red.getRGB() && rightFootColor.getRGB() != Color.red.getRGB() && CenterFootColor.getRGB() != Color.red.getRGB()){
            	if (!(player.isJump()) && !(player.isDrop())) {
            		//player.setStand(false);
                    player.drop();
                }
            }else {
            	//player.setStand(true);
            	player.setDrop(false);
            	
            }
            
            
           
            
            // 바닥 벽 왼쪽 충돌
            if (leftCenterColor.getRGB() == Color.red.getRGB() && leftFootColor.getRGB() == Color.red.getRGB()) {
				player.setLeftWallcrash(true);
				player.setLeft(false);
				player.right();
				try {
					Thread.sleep(10);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				player.setRight(false);

			//바닥벽 오른쪽 충돌 
			} else if (rightCenterColor.getRGB() == Color.red.getRGB() && rightFootColor.getRGB() == Color.red.getRGB() 
							&& leftFootColor.getRGB() == Color.red.getRGB()) {
				player.setRightWallcrash(true);
				player.setRight(false);
				player.left();
				try {
					Thread.sleep(10);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				player.setLeft(false);
				
				
	        } else {
				player.setLeftWallcrash(false);
				player.setRightWallcrash(false);
				
			}
            
            // 머리가 먼자 부딪힘
			if (rightTopColor.getRGB() == Color.red.getRGB() && leftFootColor.getRGB() == Color.white.getRGB()) {
				player.drop();
			}
			else if (leftTopColor.getRGB() == Color.red.getRGB() && rightFootColor.getRGB() == Color.white.getRGB()) {
				player.drop();
			}
			
            
            
            try {
				Thread.sleep(2);
			} catch (Exception e) {
				e.printStackTrace();
			}
            
			
		}
        
        

	}// end of run
}