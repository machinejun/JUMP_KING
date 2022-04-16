package jumpking_1;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

import jumpking_1.Obstacle_object.Block1;
import jumpking_1.Obstacle_object.Obstacle;
import lombok.Getter;

@Getter
public class BackgroundMap extends JLabel{
	private ImageIcon ImageOP;
	private ImageIcon Image1;
	private ImageIcon Image2;
	private ImageIcon Image3;
	private ImageIcon Image4;
	private ImageIcon Image5;
	private ImageIcon ImageEd;
	private ImageIcon Image;
	

	
	private int stageNum = 1;
	
	
	
	public BackgroundMap() {
		ImageOP = new ImageIcon("images/op.png");
		Image1 = new ImageIcon("images/stage1.png");
		Image2 = new ImageIcon("images/stage2.png");
		Image3 = new ImageIcon("images/stage3.png");
		Image4 = new ImageIcon("images/stage4.png");
		Image5 = new ImageIcon("images/stage5.png");
		ImageEd = new ImageIcon("images/gameover.png");
		Image = Image1;
		setIcon(Image);
		
	}
	
	
	public void changeStage() {
		if(stageNum == 0) {
			Image = ImageOP;
		}else if(stageNum == 1) {
			Image = Image1;
		}else if(stageNum == 2) {
			Image = Image2;
			
		}else if(stageNum == 3) {
			Image = Image3;
			Obstacle obstacle1  = new Block1(628, 700);
			Obstacle obstacle2  = new Block1(628, 300);
			this.add(obstacle1);
			this.add(obstacle2);
		}else if(stageNum == 4) {
			Image = Image4;
		}else if(stageNum == 5) {
			Image = Image5;
		}else {
			Image = ImageEd;
		}
		setIcon(Image);

		
	}

	public int getStageNum() {
		return stageNum;
	}

	public void setStageNum(int stageNum) {
		this.stageNum = stageNum;
	}
	
	

}
