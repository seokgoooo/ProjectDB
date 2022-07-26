// 할일 : 버튼 누르면 기능 실행되게 연결
//       랭킹 기능 쪽 통계 활용, 표현 방법 고민
// 완료 : GUI 구현

package secondFrame;

import javax.swing.JFrame;
import javax.swing.UIManager;
import javax.swing.JButton;
import java.awt.GridLayout;
import java.awt.Color;
import java.awt.Font;

public class SecondFrame extends JFrame {
	public SecondFrame() {
		super();
		setTitle("선택");
		setSize(1180, 820);
		getContentPane().setBackground(UIManager.getColor("window"));
		getContentPane().setLayout(new GridLayout(2, 3, 0, 0));

		JButton btn1 = new JButton("사자성어 퀴즈");
		getContentPane().add(btn1);
		btn1.setBackground(new Color(255, 255, 255));
		btn1.setForeground(new Color(0, 102, 102));
		btn1.setFont(new Font("한컴산뜻돋움", Font.PLAIN, 30));

		JButton btn2 = new JButton("국가-수도 퀴즈");
		getContentPane().add(btn2);
		btn2.setBackground(new Color(0, 102, 102));
		btn2.setForeground(new Color(255, 255, 255));
		btn2.setFont(new Font("한컴산뜻돋움", Font.PLAIN, 30));

		JButton btn3 = new JButton("퀴즈 랭킹");
		getContentPane().add(btn3);
		btn3.setBackground(new Color(255, 255, 255));
		btn3.setForeground(new Color(0, 102, 102));
		btn3.setFont(new Font("한컴산뜻돋움", Font.PLAIN, 30));

		JButton btn4 = new JButton("음악 퀴즈");
		getContentPane().add(btn4);
		btn4.setBackground(new Color(0, 102, 102));
		btn4.setForeground(new Color(255, 255, 255));
		btn4.setFont(new Font("한컴산뜻돋움", Font.PLAIN, 30));

		JButton btn5 = new JButton("월드컵");
		getContentPane().add(btn5);
		btn5.setBackground(new Color(255, 255, 255));
		btn5.setForeground(new Color(0, 102, 102));
		btn5.setFont(new Font("한컴산뜻돋움", Font.PLAIN, 30));

		JButton btn6 = new JButton("월드컵 랭킹");
		getContentPane().add(btn6);
		btn6.setBackground(new Color(0, 102, 102));
		btn6.setForeground(new Color(255, 255, 255));
		btn6.setFont(new Font("한컴산뜻돋움", Font.PLAIN, 30));

		setResizable(false);
		setLocationRelativeTo(null);
	}
}
