package jumpking_1;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class BackgroundMap2 extends JLabel{

	private ImageIcon ImageOP;
	private ImageIcon Image1;
	private ImageIcon Image2;
	private ImageIcon Image3;
	private ImageIcon ImageEd;
	private ImageIcon Image;
	
	private int stageNum = 1;
	
	
	
	public BackgroundMap2() {
		Image1 = new ImageIcon("images/stage1_1bg.png");
		Image2 = new ImageIcon("images/stage1.png");
		Image = Image1;
		setIcon(Image);
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
