package jumpking_1;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

public class BackgroundMap extends JPanel{

	private BufferedImage ImageOP;
	private BufferedImage Image1;
	private BufferedImage Image2;
	private BufferedImage Image3;
	private BufferedImage ImageEd;
	private BufferedImage Image;
	
	private int stageNum = 1;
	
	
	public BackgroundMap() {
		try {
			//ImageOP = ImageIO.read(new File("images/stage1_1.png"));
			Image1 = ImageIO.read(new File("images/stage1_1bg.png"));
			Image2 = ImageIO.read(new File("images/stage1.png"));
			//Image3 = ImageIO.read(new File("images/stage1_1.png"));
			//ImageEd = ImageIO.read(new File("images/stage1_1.png"));	
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Image = Image1;
	}
	
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.drawImage(Image, 0, 0, getWidth(), getHeight(), null);
	}
	
	public void changeStage() {
		if(stageNum == 0) {
			//Image = ImageOP;
		}else if(stageNum == 1) {
			Image = Image1;
		}else if(stageNum == 2) {
			Image = Image2;
		}else if(stageNum == 3) {
			//Image = Image3;
		}else if(stageNum == 4) {
			//Image = ImageEd;
		}
		repaint();
	}

	public int getStageNum() {
		return stageNum;
	}

	public void setStageNum(int stageNum) {
		this.stageNum = stageNum;
	}
	
	

}
