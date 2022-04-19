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
	private ImageIcon ImageGameover;
	private ImageIcon Image;

	private int stageNum = 0;

	public BackgroundMap() {
		ImageOP = new ImageIcon("images/op.png");      //오프닝화면
		Image1 = new ImageIcon("images/stage1.png");   //1스테이지
		Image2 = new ImageIcon("images/stage2.png");   //2스테이지
		Image3 = new ImageIcon("images/stage3.png");   //3스테이지
		Image4 = new ImageIcon("images/stage4.png");   //4스테이지
		Image5 = new ImageIcon("images/stage5.png");   //5스테이지
		ImageEd = new ImageIcon("images/gameover.png");//엔딩이미지
		ImageGameover = new ImageIcon("images/youdie.png");//엔딩이미지
		Image = ImageOP; //어떤 화면을 보여줄건지
		setIcon(Image);  //Image를 아이콘으로 셋팅함

	}

	public void changeStage() {
		if (stageNum == 0) {    // stageNum이 0이면
			Image = ImageOP;
		} else if (stageNum == 1) {// stageNum이 1이면
			Image = Image1;
		} else if (stageNum == 2) {// stageNum이 2이면
			Image = Image2;
		} else if (stageNum == 3) {// stageNum이 3이면 스테이지3에 구름발판을 넣는다
			Image = Image3;
			Obstacle obstacle1 = new Block1(628, 700);
			Obstacle obstacle2 = new Block1(628, 300);
			this.add(obstacle1);
			this.add(obstacle2);
		} else if (stageNum == 4) {//// stageNum이 4이면 스테이지4에 물약을 넣는다
			Image = Image4;
			Obstacle obstacle3 = new Waterpill(250, 900);
			this.add(obstacle3);
		} else if (stageNum == 5) {//// stageNum이 5이면 스테이지5에 몬스터와 프린세스를 넣는다.
			Image = Image5;
			Obstacle obstacle4 = new Princess(1100, 300);
			Obstacle obstacle5 = new Monster(500, 300);
			this.add(obstacle4);
			this.add(obstacle5);
		} else if(stageNum == 6 ) {
			Image = ImageEd;
		} else if (stageNum == 7) {
			Image = ImageGameover;
		}
		else {
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
