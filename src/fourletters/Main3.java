package fourletters;


import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;

import gg.DBUtil; 

public class Main3 extends JFrame {
	int user = 1;
	
	
	
	public Main3 () {
		Button bt = new Button();
		
		dummyUser du = new dummyUser("abc");
		JPanel pnlMain = new JPanel();
		JPanel pnlLEFT = new JPanel();
		JPanel pnlRight = new JPanel();
		
		// 문제와 정답을 맞출 텍스트 필드들
		JTextArea ta = new JTextArea(50, 20);
		JTextField tf = new JTextField(20);
		JTextArea ta2 = new JTextArea(20, 30);
		
		//폰트
		Font font = new Font("맑은 고딕", Font.BOLD, 32);
		Font font2 = new Font("맑은 고딕", Font.BOLD, 24);
		ta.setFont(font);
		ta2.setFont(font2);
		
		//왼쪽 버튼
		JButton btn0 = new JButton("시작");
		JButton btn1 = new JButton("확인");
		JButton btn2 = new JButton("다음 문제");
		JButton btn3 = new JButton("힌트");
		
		// 오른쪽 버튼
		JButton btn4 = new JButton("힌트");
		JButton btn5 = new JButton("즐겨찾기");
		JButton btn6 = new JButton("랭킹보기");
		
		// 왼쪽 객관식 버튼
//		JButton[] bt = new JButton[4];
		JButton[] bt2 = new JButton[4];

		btn1.setBounds(50, 50, 100, 100);
		btn2.setBounds(50, 50, 100, 100);
		btn3.setBounds(150, 50, 100, 100);

		// 왼,오 하위 Panel
		JPanel pnlL1 = new JPanel();
		JPanel pnlL2 = new JPanel();
		JPanel pnlL3 = new JPanel();
		JPanel pnlR1 = new JPanel();
		JPanel pnlR2 = new JPanel();
		JPanel pnlR3 = new JPanel();

		// panel 레이아웃
		pnlMain.setLayout(new BoxLayout(pnlMain, BoxLayout.X_AXIS));
		pnlLEFT.setLayout(new BoxLayout(pnlLEFT, BoxLayout.Y_AXIS));
		pnlRight.setLayout(new BoxLayout(pnlRight, BoxLayout.Y_AXIS));

		// 테두리
		pnlLEFT.setBorder(new TitledBorder(new LineBorder(Color.pink, 3), "문제"));
		pnlRight.setBorder(new TitledBorder(new LineBorder(Color.pink, 3), "기타"));

		pnlL1.setBorder(new TitledBorder(new LineBorder(Color.green, 3), "주관식 "));
		pnlL2.setBorder(new TitledBorder(new LineBorder(Color.green, 3), "객관식 "));
//		pnlL3.setBorder(new TitledBorder(new LineBorder(Color.PINK, 3), "문제 보기"));

		pnlR1.setBorder(new TitledBorder(new LineBorder(Color.CYAN, 3), "힌트"));
		pnlR2.setBorder(new TitledBorder(new LineBorder(Color.yellow, 3), "기능"));
		pnlR3.setBorder(new TitledBorder(new LineBorder(Color.MAGENTA, 3), "관리자"));

		// 메인
		setContentPane(pnlMain);
		pnlMain.add(pnlLEFT);
		pnlMain.add(pnlRight);

		// 왼쪽
		pnlLEFT.add(ta);
		pnlLEFT.add(pnlL1);
		pnlLEFT.add(pnlL2);

		// --왼쪽[1]
		pnlL1.add(btn0);
		pnlL1.add(tf);
		pnlL1.add(btn1);
		pnlL1.add(btn2);
		pnlL1.add(btn3);
		bt.start_button(btn0, ta);
		bt.OK_button(btn1, ta, tf, du.getId(), pnlL2);
		bt.next_button(btn2, ta);
		bt.hint_button(btn3, ta, ta2);

		// --왼쪽[2]
//		for (int i = 0; i < bt.length; i++) {
//			bt[i] = new JButton((i+1) + "번");
//			pnlL2.add(bt[i]);
//		}

		// 오른쪽
		pnlRight.add(pnlR1);
		pnlRight.add(pnlR2);
		pnlRight.add(pnlR3);

		// --오른쪽[1]
		pnlR1.add(ta2);

		// --오른쪽[2]
		pnlR2.add(btn4);
		pnlR2.add(btn5);
		pnlR2.add(btn6);

		// --오른쪽[3]
		
		
		multipleChoice(pnlL2);
		
		//생성
//		bt.questionCreate("새로,만든,사자성어");
		//읽기
//		bt.questionRead(ja);
		//삭제
//		bt.questionDelete(1);
		//수정
//		bt.questionUpdate(2,"인생,무상,아 인생이여");
		bt.ListAdd();
		pnlR3.setVisible(false);
		manager(pnlR3, user);
		
		setSize(1180, 820);

		setLocationRelativeTo(null);
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
//	// 관리자 (user = 1 일경우 관리자 켜짐)
	public void manager(JPanel p, int user) {
		JButton btn4 = new JButton("문제추가");
		JButton btn5 = new JButton("문제삭제");
		JButton btn6 = new JButton("문제수정");
		JButton btn7 = new JButton("문제보기");
		
		p.add(btn4);
		p.add(btn5);
		p.add(btn6);
		p.add(btn7);
		
		if (user == 1) {
			p.setVisible(true);
		}
	}
	
	//객관식보기 버튼
	public void multipleChoice(JPanel p) {
		GridLayout grid = new GridLayout(2, 2);
		JButton[] bt = new JButton[4];
		
		for (int i = 0; i < bt.length; i++) {
			bt[i] = new JButton((i + 1) + "번");
			p.add(bt[i]);
			p.add(new JTextField(""));
		}
		grid.setVgap(5); // 격자 사이 수직 간격 5 픽셀
		p.setLayout(grid);
	}

	public static void main(String[] args) {
		new Main3().setVisible(true);;
	}
}