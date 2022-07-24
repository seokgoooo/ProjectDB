package fourletters;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;

public class ManagerMode {
	int user = 1;

	public ManagerMode() {

		JFrame fr = new JFrame("관리자 모드");
		JPanel pnlMain = new JPanel();
		JPanel pnlLEFT = new JPanel();
		JPanel pnlRight = new JPanel();

		GridLayout grid = new GridLayout(2, 1);
		fr.setLayout(grid);
		// 문제와 정답을 맞출 텍스트 필드들
		JTextArea ta = new JTextArea();
		JTextArea ta2 = new JTextArea();
		JTextArea ta3 = new JTextArea();
		JTextField tf = new JTextField(40);

		// 폰트
		Font font = new Font("맑은 고딕", Font.BOLD, 32);
		Font font2 = new Font("맑은 고딕", Font.BOLD, 20);
		Font font3 = new Font("맑은 고딕", Font.BOLD, 20);
		ta.setFont(font);
		ta2.setFont(font2);
		tf.setFont(font3);

		// 오른쪽 버튼
//		JButton btn1 = new JButton("힌트");
//		JButton btn2 = new JButton("즐겨찾기");
//		JButton btn3 = new JButton("랭킹보기");

		// 왼쪽 객관식 버튼
//			JButton[] bt = new JButton[4];
		JButton[] bt2 = new JButton[4];

//		btn1.setBounds(50, 50, 100, 100);
//		btn2.setBounds(50, 50, 100, 100);
//		btn3.setBounds(150, 50, 100, 100);

		// 왼,오 하위 Panel
		JPanel pnlL1 = new JPanel();
		JPanel pnlL2 = new JPanel();
		JPanel pnlL3 = new JPanel();
		JPanel pnlR1 = new JPanel();
		JPanel pnlR2 = new JPanel();
		JPanel pnlR3 = new JPanel();
		JPanel pnlR4 = new JPanel();

//		JScrollPane scrollPane = new JScrollPane(ta);
		pnlL1.setLayout(new BorderLayout());
//		pnlL1.add(scrollPane, BorderLayout.CENTER); // JTextArea를 추가하여 전체화면으로 설정
		pnlL1.setPreferredSize(new Dimension(pnlL1.getWidth(), 600));
		pnlL2.setLayout(new BorderLayout());
		pnlL2.setPreferredSize(new Dimension(pnlL2.getWidth(), 200));
		pnlR1.setLayout(new BorderLayout());
		pnlR1.setPreferredSize(new Dimension(pnlR1.getWidth(), 100));
		pnlR1.setPreferredSize(new Dimension(pnlR1.getHeight(), 400));
//		pnlR1.setLayout(new BorderLayout());
//		pnlR1.setPreferredSize(new Dimension(pnlR1.getWidth(), 100));

		// panel 레이아웃
		pnlMain.setLayout(new BoxLayout(pnlMain, BoxLayout.X_AXIS));
		pnlLEFT.setLayout(new BoxLayout(pnlLEFT, BoxLayout.Y_AXIS));
		pnlRight.setLayout(new BoxLayout(pnlRight, BoxLayout.Y_AXIS));
		pnlR1.setLayout(new BoxLayout(pnlR1, BoxLayout.Y_AXIS));

		// 테두리
		pnlLEFT.setBorder(new TitledBorder(new LineBorder(Color.pink, 3), " "));
		pnlRight.setBorder(new TitledBorder(new LineBorder(Color.pink, 3), " "));

		pnlL1.setBorder(new TitledBorder(new LineBorder(Color.green, 3), "문제"));
		pnlL2.setBorder(new TitledBorder(new LineBorder(Color.green, 3), "설명"));
//			pnlL3.setBorder(new TitledBorder(new LineBorder(Color.PINK, 3), "문제 보기"));

		pnlR1.setBorder(new TitledBorder(new LineBorder(Color.CYAN, 3), "입력"));
//		pnlR2.setBorder(new TitledBorder(new LineBorder(Color.CYAN, 3), "버튼"));
		pnlR3.setBorder(new TitledBorder(new LineBorder(Color.yellow, 3), "기능"));
		pnlR4.setBorder(new TitledBorder(new LineBorder(Color.MAGENTA, 3), "번호"));

		// 메인
		fr.setContentPane(pnlMain);
		pnlMain.add(pnlLEFT);
		pnlMain.add(pnlRight);

		// 왼쪽
		pnlLEFT.add(pnlL1);
		pnlLEFT.add(pnlL2);

		// --왼쪽[1]
		pnlL1.add(ta);
		pnlL2.add(ta2);
		// --왼쪽[2]
//			for (int i = 0; i < bt.length; i++) {
//				bt[i] = new JButton((i+1) + "번");
//				pnlL2.add(bt[i]);
//			}

		// 오른쪽
		pnlRight.add(pnlR1);
		
		pnlRight.add(pnlR3);
		pnlRight.add(pnlR4);

		// --오른쪽[1]
		pnlR1.add(tf);
		pnlR1.add(pnlR2);

		// --오른쪽[2]
//		pnlR2.add(btn1);
//		pnlR2.add(btn2);
//		pnlR2.add(btn3);

		// --오른쪽[3]

		multipleChoice(pnlR4);
		inputButton(pnlR2, user);
		manager(pnlR3, user);

		fr.setSize(1180, 820);
		pnlR1.setPreferredSize(new Dimension(100, 100));

		fr.setLocationRelativeTo(null);
		fr.setResizable(false);
		fr.setVisible(true);
		fr.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	public void inputButton(JPanel p, int user) {
		GridLayout grid = new GridLayout(1, 2);
//		p.setLayout(new BoxLayout(p, BoxLayout.X_AXIS));
		JButton btn4 = new JButton(" 확   인 ");
		JButton btn5 = new JButton("전체삭제");
//		JButton btn6 = new JButton("문제수정");
//		JButton btn7 = new JButton("문제삭제");

		p.add(btn4);
		p.add(btn5);
//		p.add(btn6);
//		p.add(btn7);

		if (user == 1) {
			p.setVisible(true);
		}

		grid.setVgap(5); // 격자 사이 수직 간격 5 픽셀
		p.setLayout(grid);
	}
	
	
//		// 관리자 (user = 1 일경우 관리자 켜짐)
	public void manager(JPanel p, int user) {
		GridLayout grid = new GridLayout(1, 4);
		JButton btn4 = new JButton("문제보기");
		JButton btn5 = new JButton("문제추가");
		JButton btn6 = new JButton("문제수정");
		JButton btn7 = new JButton("문제삭제");

		p.add(btn4);
		p.add(btn5);
		p.add(btn6);
		p.add(btn7);

		if (user == 1) {
			p.setVisible(true);
		}
		grid.setVgap(5); // 격자 사이 수직 간격 5 픽셀
		p.setLayout(grid);
	}

	// 객관식보기 버튼
	public void multipleChoice(JPanel p) {
		GridLayout grid = new GridLayout(6, 5);
		JButton[] bt = new JButton[31];

		for (int i = 0; i < bt.length; i++) {
			bt[i] = new JButton((i + 1) + "번");
			p.add(bt[i]);
		}
		grid.setVgap(5); // 격자 사이 수직 간격 5 픽셀
		p.setLayout(grid);
	}

	public static void main(String[] args) {
		new ManagerMode();
	}
}
