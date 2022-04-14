package jumpking_1;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;

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
        		leftFootColor = new Color(image.getRGB(player.getX() + 8, player.getY() + 52 )); //
            	leftCenterColor = new Color(image.getRGB(player.getX() + 8, player.getY() + 26)); //
    			leftTopColor = new Color(image.getRGB(player.getX() + 8, player.getY()));
    			
    			CenterFootColor = new Color(image.getRGB(player.getX() + 25, player.getY() + 52));
    			CenterTopColor = new Color(image.getRGB(player.getX() + 25, player.getY()));
    			
    			rightFootColor = new Color(image.getRGB(player.getX() + 42, player.getY() + 52));
    			rightCenterColor = new Color(image.getRGB(player.getX() + 42, player.getY() + 26));
    			rightTopColor = new Color(image.getRGB(player.getX() + 42, player.getY()));
			} catch (ArrayIndexOutOfBoundsException e) {
				try {
					Thread.sleep(16);
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
        	
            if ((bottomCheck.contains(Color.red))){
            	if(bottomCheck.size() <= 2) {
            		if(!player.isDrop()) player.drop();	
            	}
            	player.setDrop(false);
            }else {
            	if (!(player.isJump()) && !(player.isDrop())) {
                    player.drop();
                }
            }
            
            // 천장 검사
            HashSet<Color> TopCheck = new HashSet<Color>();
            TopCheck.add(leftTopColor);
            TopCheck.add(rightTopColor);
            TopCheck.add(CenterTopColor);
        
            if ((TopCheck.size() > 1)){
            	if(!player.isDrop()) player.drop();
            }
            
            // 왼쪽 충돌
            ArrayList<Color> leftCheck = new ArrayList<Color>();
            leftCheck.add(leftTopColor);
            leftCheck.add(leftCenterColor);
            leftCheck.add(leftFootColor);
            
            if (leftCheck.contains(Color.red)) {
            	int count = 0;
            	
            	for (Color color : leftCheck) {
					if(color.equals(Color.red)) {
						count++;
					}
				}
            	
            	if( count == 3) {
            		player.setLeftWallcrash(true);
    				player.setLeft(false);
    				
            	}else if (count == 2) {
            		player.setLeftWallcrash(true);
            	}else if(count ==1) {
            		player.setLeftWallcrash(true);
            		if(leftCheck.get(2).equals(Color.red)) {
            			player.setLeftWallcrash(false);
            		}
            		
            	}
            }else {
            	player.setLeftWallcrash(false);
            }
				
			
			//바닥벽 오른쪽 충돌 
            ArrayList<Color> RightCheck = new ArrayList<Color>();
            RightCheck.add(rightTopColor);
            RightCheck.add(rightCenterColor);
            RightCheck.add(rightFootColor);
            
            if (RightCheck.contains(Color.red)) {
            	int count = 0;
            	
            	for (Color color : RightCheck) {
					if(color.equals(Color.red)) {
						count++;
					}
				}
            	
            	if( count == 3) {
            		player.setRightWallcrash(true);
    				player.setRight(false);
    				
            	}else if (count == 2) {
            		player.setRightWallcrash(true);
            	}else if(count ==1) {
            		player.setRightWallcrash(true);
            		if(RightCheck.get(2).equals(Color.red)) {
            			
            			player.setRightWallcrash(false);
            		}
            	}
            }else {
            	player.setRightWallcrash(false);
            }
    
            try {
				Thread.sleep(2);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}// end of run
}