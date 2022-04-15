package jumpking_1;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

import javax.imageio.ImageIO;

// 메인 쓰레드는 바쁨 -> 키보드 이벤트 처리를 해야하기 때문
// 백그라운드에서 계속 관찰
public class BackgroundPlayerService implements Runnable {
	private int stage;
	private BufferedImage image;
	private BufferedImage image1;
	private BufferedImage image2;
	private BufferedImage image3;
	private BufferedImage image4;
	private BufferedImage image5;
	private BufferedImage imageEd;
	private Player player;
	private BackgroundMap backgroundMap;

	public BackgroundPlayerService(Player player, BackgroundMap backgroundMap) {
		this.player = player;
		this.backgroundMap = backgroundMap;
		try {
			image1 = ImageIO.read(new File("images/stage1bg.png"));	//1번맵
			image2 = ImageIO.read(new File("images/stage2bg.png")); // 2번 맵
			image3 = ImageIO.read(new File("images/stage3bg.png")); // 3번맵
			image4 = ImageIO.read(new File("images/stage4bg.png")); // 4번맵
			image5 = ImageIO.read(new File("images/stage5bg.png")); // 4번맵

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
        	}else if(backgroundMap.getStageNum() == 3) {
        		image = image3;
        	}else if(backgroundMap.getStageNum() == 4) {
        		image = image4;
        	}else if(backgroundMap.getStageNum() == 5) {
        		image = image5;
        	}else if(backgroundMap.getStageNum() == 2) {
        		image = imageEd;
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
        		leftFootColor = new Color(image.getRGB(player.getX() + 8, player.getY() + 50)); //
            	leftCenterColor = new Color(image.getRGB(player.getX() + 8, player.getY() + 26)); //
    			leftTopColor = new Color(image.getRGB(player.getX() + 8, player.getY()));
    			
    			CenterFootColor = new Color(image.getRGB(player.getX() + 25, player.getY() + 50));
    			CenterTopColor = new Color(image.getRGB(player.getX() + 25, player.getY()));
    			
    			rightFootColor = new Color(image.getRGB(player.getX() + 42, player.getY() + 50));
    			rightCenterColor = new Color(image.getRGB(player.getX() + 42, player.getY() + 26));
    			rightTopColor = new Color(image.getRGB(player.getX() + 42, player.getY()));
			} catch (ArrayIndexOutOfBoundsException e) {
				try {
					Thread.sleep(20);
				} catch (InterruptedException e1) {
					e1.printStackTrace();
				}
				leftFootColor = new Color(image.getRGB(player.getX() + 0, player.getY() + 52 )); //
            	leftCenterColor = new Color(image.getRGB(player.getX() + 0, player.getY() + 26)); //
    			leftTopColor = new Color(image.getRGB(player.getX() + 0, player.getY()));
    			
    			CenterFootColor = new Color(image.getRGB(player.getX() + 25, player.getY() + 52));
    			CenterTopColor = new Color(image.getRGB(player.getX() + 25, player.getY()));
    			
    			rightFootColor = new Color(image.getRGB(player.getX() + 46, player.getY() + 52));
    			rightCenterColor = new Color(image.getRGB(player.getX() + 46, player.getY() + 26));
    			rightTopColor = new Color(image.getRGB(player.getX() + 46, player.getY()));
			}
        	
             

             
            // 바닥 검사
        	HashSet<Color> bottomCheck = new HashSet<Color>();
        	bottomCheck.add(leftFootColor);
        	bottomCheck.add(rightFootColor);
        	bottomCheck.add(CenterFootColor);
        	
            if (bottomCheck.contains(Color.red) && CenterFootColor.getRGB() != Color.white.getRGB()){
            	player.setDrop(false);
            }else {
            	if (!(player.isJump()) && !(player.isDrop())) {
                    player.drop();
                }
            }
            
          //바닥벽 오른쪽 충돌 
            HashSet<Color> RightCheck = new HashSet<Color>();
            RightCheck.add(rightTopColor);
            RightCheck.add(rightCenterColor);
            
            if (RightCheck.contains(Color.red)) {
            	player.setRightWallcrash(true);
            	player.setRight(false);
            }else {
            	player.setRightWallcrash(false);
            }
            
            // 왼쪽 충돌
            HashSet<Color> leftCheck = new HashSet<Color>();
            leftCheck.add(leftTopColor);
            leftCheck.add(leftCenterColor);
            
            
            if (leftCheck.contains(Color.red)) {
            	player.setLeftWallcrash(true);
            	player.setLeft(false);
            }else {
            	player.setLeftWallcrash(false);
            }
            
            
            // 천장 검사
            HashSet<Color> TopCheck = new HashSet<Color>();
            TopCheck.add(leftTopColor);
            TopCheck.add(rightTopColor);
            TopCheck.add(CenterTopColor);
        
            if ((TopCheck.contains(Color.red)) && CenterFootColor.getRGB() == Color.white.getRGB()){
            	if(player.isJump()) player.drop();
            }
            
        
				
			
			
    
            try {
				Thread.sleep(2);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}// end of run
}