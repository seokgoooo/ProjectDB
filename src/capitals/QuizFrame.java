package capitals;

import java.awt.Color;
import java.awt.GridLayout;
import java.util.List;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URL;
import java.util.ArrayList;

import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;

import com.sun.jndi.toolkit.url.Uri;

import attempts.AttemptsDAO;
import attempts.AttemptsDAOImpl;
import javax.swing.JCheckBox;
import java.awt.Component;
import java.awt.Dimension;

import javax.swing.JTabbedPane;
import javax.swing.JLabel;

public class QuizFrame {
	int user = 1;
	private AttemptsDAO attemptsDao = new AttemptsDAOImpl();
	private Manager mg = new Manager();
	private List<Capitals> list = new ArrayList<>();
	
	public QuizFrame() {
		
		
		JFrame fr = new JFrame("퀴즈 프로그램");
		JPanel pnlMain = new JPanel();
		JPanel pnlLEFT = new JPanel();
		JPanel pnlRight = new JPanel();

		// 문제와 정답을 맞출 텍스트 필드들
		JTextArea ja = new JTextArea(15, 20);
		ja.setBounds(7, 17, 561, 593);
		JTextField tf = new JTextField(20);
		tf.setBounds(140, 23, 226, 21);

		// 왼쪽 객관식 버튼
//		JButton[] bt = new JButton[4];
		JButton[] bt2 = new JButton[4];

		// 왼,오 하위 Panel
		JPanel pnlL1 = new JPanel();
		pnlL1.setBounds(7, 724, 561, 57);
		//JPanel pnlL2 = new JPanel();
		JPanel pnlL3 = new JPanel();
		JPanel pnlR2 = new JPanel();
		pnlR2.setBounds(24, 24, 551, 757);

		// panel 레이아웃
		pnlMain.setLayout(new BoxLayout(pnlMain, BoxLayout.X_AXIS));

		// 테두리
		pnlLEFT.setBorder(new TitledBorder(new LineBorder(Color.pink, 3), "문제"));
		pnlRight.setBorder(new TitledBorder(new LineBorder(Color.pink, 3), "기타"));

		pnlL1.setBorder(new TitledBorder(new LineBorder(Color.green, 3), "주관식 "));
		pnlR2.setBorder(new TitledBorder(new LineBorder(Color.yellow, 3), "기능"));

		// 메인
		fr.setContentPane(pnlMain);
		pnlMain.add(pnlLEFT);
		pnlMain.add(pnlRight);
		pnlLEFT.setLayout(null);

		// 왼쪽
		pnlLEFT.add(ja);
		pnlLEFT.add(pnlL1);
		pnlL1.setLayout(null);
		//pnlLEFT.add(pnlL2);

		// --왼쪽[1]
		pnlL1.add(tf);
		pnlRight.setLayout(null);
		// 체르박스
		JCheckBox checkBox = new JCheckBox("즐겨찾기");
		checkBox.setBounds(460, 22, 93, 23);
		checkBox.setAlignmentX(Component.RIGHT_ALIGNMENT);
		pnlL1.add(checkBox);
		
		

		// --왼쪽[2]
//		for (int i = 0; i < bt.length; i++) {
//			bt[i] = new JButton((i+1) + "번");
//			pnlL2.add(bt[i]);
//		}

		// 오른쪽
		//pnlRight.add(pnlR1);
		pnlRight.add(pnlR2);
				pnlR2.setLayout(null);
				
				JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
				tabbedPane.setBounds(12, 20, 527, 727);
				pnlR2.add(tabbedPane);
				
				JPanel panel2 = new JPanel();
				tabbedPane.addTab("전체문제", null, panel2, null);
				panel2.setLayout(null);
				
				JPanel panel3 = new JPanel();
				tabbedPane.addTab("맞춘 문제", null, panel3, null);
				
				JPanel panel1 = new JPanel();
				tabbedPane.addTab("즐겨찾기", null, panel1, null);

		// --오른쪽[3]

		OK_button(pnlL1);

		JTextArea ja2 = new JTextArea(20, 30);
		ja2.setBounds(12, 16, 537, 60);
		JPanel pnlhint = new JPanel();
		pnlhint.setBounds(7, 620, 561, 86);
		pnlLEFT.add(pnlhint);
		//pnlL2.setBorder(new TitledBorder(new LineBorder(Color.green, 3), "객관식 "));
//		pnlL3.setBorder(new TitledBorder(new LineBorder(Color.PINK, 3), "문제 보기"));

		pnlhint.setBorder(new TitledBorder(new LineBorder(Color.CYAN, 3), "힌트"));
		pnlhint.setLayout(null);
		
				// --힌트[1]
				pnlhint.add(ja2);
				
				
				// 라벨이미지
				URL ImageUrl = QuizFrame.class.getClassLoader().getResource("number1.png");
				JLabel imagelbl = new JLabel(new ImageIcon(ImageUrl));
				
				imagelbl.setBounds(12, 39, 544, 550);
				pnlLEFT.add(imagelbl);

		fr.setSize(1180, 820);

		fr.setLocationRelativeTo(null);
		fr.setResizable(false);
		fr.setVisible(true);
		fr.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	// 관리자 (user = 1 일경우 관리자 켜짐)
	public void manager(JPanel p, int user) {

		if (user == 0) {
			p.setVisible(true);
		}
	}

	// 확인버튼
	public void OK_button(JPanel p) {
		JButton btn0 = new JButton("확인");
		btn0.setBounds(378, 22, 74, 23);
		p.add(btn0);
	}

	// 객관식보기 버튼
//	public static void multipleChoice(JPanel p) {
//		GridLayout grid = new GridLayout(2, 2);
//		JButton[] bt = new JButton[4];
//
//		for (int i = 0; i < bt.length; i++) {
//			bt[i] = new JButton((i + 1) + "번");
//			p.add(bt[i]);
//			p.add(new JTextField(""));
//		}
//		grid.setVgap(5); // 격자 사이 수직 간격 5 픽셀
//		p.setLayout(grid);
//		p.add(new JLabel(" 이름"));
//		
//		p.add(new JLabel(" 학번"));
//		p.add(new JTextField(""));
//		p.add(new JLabel(" 학과"));
//		p.add(new JTextField(""));
//		p.add(new JLabel(" 과목"));
//		p.add(new JTextField(""));
//}

	public static void main(String[] args) {
		new QuizFrame();
	}
}