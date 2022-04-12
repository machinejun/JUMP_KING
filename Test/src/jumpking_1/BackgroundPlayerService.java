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

	private BufferedImage image;
	private Player player;

	public BackgroundPlayerService(Player player) {
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
        	 Color leftFootColor = new Color(image.getRGB(player.getX() , player.getY() + 50)); //
        	 Color leftTopColor = new Color(image.getRGB(player.getX() , player.getY()  ));
             Color rightFootColor = new Color(image.getRGB(player.getX() + 25 , player.getY() + 50));
             Color rightTopColor = new Color(image.getRGB(player.getX() + 25 , player.getY()  ));
             
             //System.out.println(leftFootColor);
             
            
            if (leftFootColor.getRGB() == Color.white.getRGB() && rightFootColor.getRGB() == Color.white.getRGB()){
            	if (!(player.isJump()) && !(player.isDrop())) {
                    player.drop();
                }
            }else { 

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

            } else if (rightFootColor.getRGB() == Color.red.getRGB() && rightTopColor.getRGB() == Color.red.getRGB()) {
                player.setRightWallcrash(true);
                player.setRight(false);

            }
            else {
                player.setLeftWallcrash(false);
                player.setRightWallcrash(false);

            }
            
            
            
            try {
				Thread.sleep(10);
			} catch (Exception e) {
				e.printStackTrace();
			}
            
			
		}

	}
}