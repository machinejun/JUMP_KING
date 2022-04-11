package jumpking_1;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

// 메인 쓰레드는 바쁨 -> 키보드 이벤트 처리를 해야하기 때문
// 백그라운드에서 계속 관찰
public class BackgroundPlayerService implements Runnable {

	private BufferedImage image;
	private Player player;

	public BackgroundPlayerService(Player player) {
		this.player = player;
		try {
			image = ImageIO.read(new File("images/stage1bg.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	@Override
    public void run() {
        // 도전과제 ! 던지거나 처리
        // 색상 확인
        while (true) {

            // 자바의 좌표 기준은 왼쪽 위, 아이콘 크기 50, 50
            Color leftFootColor = new Color(image.getRGB(player.getX() - 20, player.getY() + 45)); //
            Color rightFootColor = new Color(image.getRGB(player.getX() + 50 , player.getY() + 45));
            Color leftTopColor = new Color(image.getRGB(player.getX() -20, player.getY() + 5));
            Color rightTopColor = new Color(image.getRGB(player.getX() + 50 , player.getY() + 5));

            int bottomColor = image.getRGB(player.getX() + 10, player.getY() + 50 + 5) // -1
                    + image.getRGB(player.getX() + 50 - 10, player.getY() + 50 + 5); // -1
            int topColor = image.getRGB(player.getX() + 10, player.getY() + 5) // -1
                    + image.getRGB(player.getX() + 50 - 10, player.getY() + 5); // -1
            
       

         // -2 가 아니라면 !!!. 바닥 충돌 확인
            if (bottomColor != -2) {
                player.setDrop(false);
            } else { // 바닥 색깔이 하얀색
                // 점프 하는 순간 down 메서드가 호출
                if (!player.isJump() && !player.isDrop()) {
                    player.drop();
                }
            }
            if (topColor != -2) {
                player.setJump(false);
            }
            if (leftFootColor.getRed() == 255 && leftFootColor.getGreen() == 0 && leftFootColor.getBlue() == 0) {
                player.setLeftWallcrash(true);
                player.setLeft(false);
                System.out.println("왼쪽바닥 부딪힘");
            } else if (rightFootColor.getRed() == 255 && rightFootColor.getGreen() == 0
                    && rightFootColor.getBlue() == 0) {
                player.setRightWallcrash(true);
                player.setRight(false);
                System.out.println(" 오른쪽바닥 부딪힘");
            } else if (leftTopColor.getRed() == 255 && leftTopColor.getGreen() == 0 && leftTopColor.getBlue() == 0) {
                player.setLeftWallcrash(true);
                player.setJump(false); 
                System.out.println("왼쪽 머리 부딪힘");
            } else if (rightTopColor.getRed() == 255 && rightTopColor.getGreen() == 0 && rightTopColor.getBlue() == 0) {
                player.setRightWallcrash(true);
                player.setJump(false);
                System.out.println("오른쪽 머리 부딪힘");
            }

            else {
                player.setLeftWallcrash(false);
                player.setRightWallcrash(false);
                player.setLeftTopcrash(false);
                player.setRightTopcrash(false);
            }

//		System.out.println("==============================");
//		System.out.println("왼쪽 색상 : " + leftcolor);
//		System.out.println("오른쪽 색상 : " + rightcolor);
//		System.out.println("==============================");
			try {
				// 캐릭터가 이동 될 때 값을 못 찾는 경우가 있다.
				// 작업자를 너무 재워서 그런거임
				Thread.sleep(1);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

	}
}
