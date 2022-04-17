package jumpking_1;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

import Obstacle_object.Block1;
import Obstacle_object.Monster;
import Obstacle_object.Obstacle;
import Obstacle_object.Princess;
import Obstacle_object.Waterpill;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
public class BackgroundMap extends JLabel {
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
		if (stageNum == 0) {
			Image = ImageOP;
		} else if (stageNum == 1) {
			Image = Image1;
		} else if (stageNum == 2) {
			Image = Image2;
		} else if (stageNum == 3) {
			Image = Image3;
			Obstacle obstacle1 = new Block1(628, 700);
			Obstacle obstacle2 = new Block1(628, 300);
			this.add(obstacle1);
			this.add(obstacle2);
		} else if (stageNum == 4) {
			Image = Image4;
			Obstacle obstacle3 = new Waterpill(250, 900);
			this.add(obstacle3);
		} else if (stageNum == 5) {
			Image = Image5;
			Obstacle obstacle4 = new Princess(1100, 300);
			Obstacle obstacle5 = new Monster(500, 300);
			this.add(obstacle4);
			this.add(obstacle5);
		} else if(stageNum == 6 ) {
			Image = ImageEd;
		} else {
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
