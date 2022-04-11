package jumpking_1;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class BackgroundMap extends JLabel{
	private BufferedImage bufferedImageOP;
	private BufferedImage bufferedImage1;
	private BufferedImage bufferedImage2;
	private BufferedImage bufferedImage3;
	private BufferedImage bufferedImageEd;
	private BufferedImage bufferedImage;
	
	
	public BackgroundMap() {
		try {
			bufferedImage1 = ImageIO.read(new File("images/stage1bg.png"));
			//bufferedImage2 = ImageIO.read(new File("images/stage2.png"));
			//bufferedImage3 = ImageIO.read(new File("images/stage3.png"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		bufferedImage = bufferedImage1;
	}
	
	public void changeStage(int stage) {
		if(stage == 0) {
			bufferedImage = bufferedImageOP;	
		}else if(stage == 1) {
			bufferedImage = bufferedImage1;
		}else if(stage == 2) {
			bufferedImage = bufferedImage2;
		}else if(stage == 3) {
			bufferedImage = bufferedImage3;
		}else if(stage == 4) {
			bufferedImage = bufferedImageEd;
		}
		repaint();
	}
	
	@Override
	protected void paintComponent(Graphics g) {
		g.drawImage(bufferedImage,0, 0, 1440, 940, null);
	}

	public BufferedImage getBufferedImageOP() {
		return bufferedImageOP;
	}

	public void setBufferedImageOP(BufferedImage bufferedImageOP) {
		this.bufferedImageOP = bufferedImageOP;
	}

	public BufferedImage getBufferedImage1() {
		return bufferedImage1;
	}

	public void setBufferedImage1(BufferedImage bufferedImage1) {
		this.bufferedImage1 = bufferedImage1;
	}

	public BufferedImage getBufferedImage2() {
		return bufferedImage2;
	}

	public void setBufferedImage2(BufferedImage bufferedImage2) {
		this.bufferedImage2 = bufferedImage2;
	}

	public BufferedImage getBufferedImage3() {
		return bufferedImage3;
	}

	public void setBufferedImage3(BufferedImage bufferedImage3) {
		this.bufferedImage3 = bufferedImage3;
	}

	public BufferedImage getBufferedImageEd() {
		return bufferedImageEd;
	}

	public void setBufferedImageEd(BufferedImage bufferedImageEd) {
		this.bufferedImageEd = bufferedImageEd;
	}

	public BufferedImage getBufferedImage() {
		return bufferedImage;
	}

	public void setBufferedImage(BufferedImage bufferedImage) {
		this.bufferedImage = bufferedImage;
	}
	
}
