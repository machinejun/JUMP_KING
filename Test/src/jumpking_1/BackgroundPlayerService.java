package jumpking_1;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

// 메인 쓰레드는 바쁨 -> 키보드 이벤트 처리를 해야하기 때문
// 백그라운드에서 계속 관찰
public class BackgroundPlayerService implements Runnable {
	private BackgroundMap2 backgroundMap;
	private BufferedImage image;
	private Player player;

	public BackgroundPlayerService(Player player, BackgroundMap2 backgroundMap) {
		this.backgroundMap = backgroundMap;
		this.player = player;
		try {
			image = ImageIO.read(new File("images/stage1_1bg.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	@Override
    public void run() {

        while (true) {
        	 // 자바의 좌표 기준은 왼쪽 위, 아이콘 크기 50, 50
        	 Color leftFootColor = new Color(image.getRGB(player.getX() , player.getY() - 10)); //
        	 Color leftTopColor = new Color(image.getRGB(player.getX() , player.getY() + 30 ));
             Color rightFootColor = new Color(image.getRGB(player.getX() + 50 , player.getY() - 10));
             Color rightTopColor = new Color(image.getRGB(player.getX() + 50 , player.getY() +30  ));
             
             //System.out.println(leftFootColor);
             
            
            if (leftFootColor.getRGB() == Color.white.getRGB() && rightFootColor.getRGB() == Color.white.getRGB()){
            	System.out.println("떨어짐");
            	if (!(player.isJump()) && !(player.isDrop())) {
            		System.out.println("떨어짐1");
                    player.drop();
                }
            }else { 
            	System.out.println("안떨어짐");
            	player.setDrop(false);
            }
//            if (topColor != -2) {
//                player.setJump(false);
//            }
            
            
            if (leftFootColor.getRGB() == Color.red.getRGB() && leftTopColor.getRGB() == Color.red.getRGB()) {
                System.out.println(leftFootColor.getRed() + "/" + leftFootColor.getBlue());
                System.out.println(leftTopColor.getRed() + "/" + leftTopColor.getBlue());
            	player.setLeftWallcrash(true);
                player.setLeft(false);
                System.out.println("왼쪽바닥 부딪힘");
            } else if (rightFootColor.getRGB() == Color.red.getRGB() && rightTopColor.getRGB() == Color.red.getRGB()) {
                player.setRightWallcrash(true);
                player.setRight(false);
                System.out.println(" 오른쪽바닥 부딪힘");
            }
            else {
                player.setLeftWallcrash(false);
                player.setRightWallcrash(false);

            }
            
            if(player.getY() > 850 && backgroundMap.getStageNum() == 1) {
            	backgroundMap.setStageNum(2);
            	backgroundMap.changeStage();
            }
            
            try {
				Thread.sleep(10);
			} catch (Exception e) {
				e.printStackTrace();
			}
            
			
		}

	}
}