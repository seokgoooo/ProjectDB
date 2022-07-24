package music;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.sql.SQLException;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;

public class MusicQuiz implements ActionListener {
	private MusicDao dao = new MusicDaoImpl();
	private MusicPlayer player = new MusicPlayer();
	private Music music = null;
	
	private final String path = "src\\music\\resource\\";
	
	private JButton confirmBtn;
	private JTextField answerTf;
	
	public MusicQuiz() {
		JFrame fr = new JFrame("음악 퀴즈");
		JPanel pnlMain = new JPanel();
		JPanel pnlLEFT = new JPanel();
		JPanel pnlRight = new JPanel();

		
		try {
			music = dao.read(3001);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		String title = music.getTitle();
		
		player.play(new File(path + title + ".mp3"));
		
		// 문제와 정답을 맞출 텍스트 필드들
		JTextArea ja = new JTextArea(15, 20);
		answerTf = new JTextField(20);
		JTextArea ja2 = new JTextArea(20, 30);

		// 오른쪽 버튼
		JButton btn2 = new JButton("즐겨찾기");

		btn2.setBounds(50, 50, 100, 100);

		// 왼,오 하위 Panel
		JPanel pnlL1 = new JPanel();
		JPanel pnlL2 = new JPanel();
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

		pnlR1.setBorder(new TitledBorder(new LineBorder(Color.CYAN, 3), "힌트"));
		pnlR2.setBorder(new TitledBorder(new LineBorder(Color.yellow, 3), "기능"));
		pnlR3.setBorder(new TitledBorder(new LineBorder(Color.MAGENTA, 3), "관리자"));

		// 메인
		fr.setContentPane(pnlMain);
		pnlMain.add(pnlLEFT);
		pnlMain.add(pnlRight);

		// 왼쪽
		pnlLEFT.add(ja);
		pnlLEFT.add(pnlL1);

		// --왼쪽[1]
		pnlL1.add(answerTf);

		// 오른쪽
		pnlRight.add(pnlR1);
		pnlRight.add(pnlR2);
		pnlRight.add(pnlR3);

		// --오른쪽[1]
		pnlR1.add(ja2);

		pnlR2.add(btn2);

		OK_button(pnlL1);

		pnlR3.setVisible(false);

		fr.setSize(1180, 820);

		fr.setLocationRelativeTo(null);
		fr.setResizable(false);
		fr.setVisible(true);
		fr.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	// 확인버튼
	public void OK_button(JPanel p) {
		confirmBtn = new JButton("확인");
		confirmBtn.addActionListener(this);
		p.add(confirmBtn);
	}

	public static void main(String[] args) {
		new MusicQuiz();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == confirmBtn) {
			if(answerTf.getText().equals(music.getTitle())) {
				player.stop();
				System.out.println("정답");
			}
		}
	}
}