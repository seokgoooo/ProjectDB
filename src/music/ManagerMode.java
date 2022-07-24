package music;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;

public class ManagerMode extends JFrame implements ActionListener {
	private List<Music> list = new ArrayList<>();
	private MusicDao dao = new MusicDaoImpl();
	private Music m = null;
	private JButton[] quizNumberBtns;
	private JRadioButton createRB;
	private JRadioButton updateRB;
	private JRadioButton deleteRB;
	private JTextField titleTf;
	private JTextField singerTf;
	private JTextField genreTf;
	private JTextField yearTf;
	private JButton confirmBtn;
	private JPanel quizNumberPnl;

	public ManagerMode() {
		super("관리자 모드");

		try {
			list = dao.read();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		JPanel allPnl = new JPanel();

		JPanel topPnl = new JPanel();
		JPanel mainPnl = new JPanel();
		JPanel leftPnl = new JPanel();

		JPanel rightPnl = new JPanel();
		JPanel confirmPnl = new JPanel();
		quizNumberPnl = new JPanel();

		allPnl.setLayout(new BoxLayout(allPnl, BoxLayout.X_AXIS));
		leftPnl.setLayout(new BoxLayout(leftPnl, BoxLayout.Y_AXIS));

		topPnl.setBorder(new TitledBorder(new LineBorder(Color.red, 3), "기능"));

		mainPnl.setLayout(new BoxLayout(mainPnl, BoxLayout.Y_AXIS));
		mainPnl.setBorder(new TitledBorder(new LineBorder(Color.blue, 3), "내용"));

		rightPnl.setLayout(new BoxLayout(rightPnl, BoxLayout.Y_AXIS));
		rightPnl.setBorder(new TitledBorder(new LineBorder(Color.pink, 3), "right"));

		confirmPnl.setBorder(new TitledBorder(new LineBorder(Color.yellow, 3), "확인"));

		quizNumberPnl.setBorder(new TitledBorder(new LineBorder(Color.green, 3), "문제 번호"));
		quizNumberPnl.setLayout(new GridLayout(5, 6));

		//CRUD 라디오 버튼 
		createRB = new JRadioButton("추가");
		updateRB = new JRadioButton("수정");
		deleteRB = new JRadioButton("삭제");

		createRB.setSelected(true);

		topPnl.add(createRB);
		topPnl.add(updateRB);
		topPnl.add(deleteRB);

		ButtonGroup crudBG = new ButtonGroup();
		crudBG.add(createRB);
		crudBG.add(updateRB);
		crudBG.add(deleteRB);

		// 수정할 텍스트 부분 Panel
		JPanel titlePnl = new JPanel();
		JPanel singerPnl = new JPanel();
		JPanel genrePnl = new JPanel();
		JPanel yearPnl = new JPanel();

		JLabel titleLbl = new JLabel("제목");
		titleTf = new JTextField(20);
		titlePnl.add(titleLbl);
		titlePnl.add(titleTf);

		JLabel singerLbl = new JLabel("가수 ");
		singerTf = new JTextField(20);
		singerPnl.add(singerLbl);
		singerPnl.add(singerTf);

		JLabel genreLbl = new JLabel("장르");
		genreTf = new JTextField(20);
		genrePnl.add(genreLbl);
		genrePnl.add(genreTf);

		JLabel yearLbl = new JLabel("연도");
		yearTf = new JTextField(20);
		yearPnl.add(yearLbl);
		yearPnl.add(yearTf);

		mainPnl.add(titlePnl);
		mainPnl.add(singerPnl);
		mainPnl.add(genrePnl);
		mainPnl.add(yearPnl);

		// 확인 버튼
		confirmBtn = new JButton("확인");
		confirmBtn.addActionListener(this);
		confirmPnl.add(confirmBtn);

		// 문제 리스트
		quizNumberBtns = new JButton[list.size()];

		for (int i = 0; i < list.size(); i++) {
			quizNumberBtns[i] = new JButton(i + 1 + "");
			quizNumberBtns[i].addActionListener(this);
			quizNumberPnl.add(quizNumberBtns[i]);
		}

		leftPnl.add(topPnl);
		leftPnl.add(mainPnl);

		rightPnl.add(confirmPnl);
		rightPnl.add(quizNumberPnl);

		allPnl.add(leftPnl);
		allPnl.add(rightPnl);
		add(allPnl);

		setSize(700, 500);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
	}

	public static void main(String[] args) {
		new ManagerMode().setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == confirmBtn) {
			confirmBtnEvent();
		} else {
			for (int i = 0; i < quizNumberBtns.length; i++) {
				if (e.getSource() == quizNumberBtns[i]) {
					m = list.get(i);
					titleTf.setText(m.getTitle());
					singerTf.setText(m.getSinger());
					genreTf.setText(m.getGenre());
					yearTf.setText(String.valueOf(m.getYear()));
				}
			}
		}
	}

	// 확인 버튼 이벤트 메소드
	public void confirmBtnEvent() {
		if (createRB.isSelected()) {
			try {
				dao.create(titleTf.getText(), singerTf.getText(), genreTf.getText(), Integer.valueOf(yearTf.getText()));
				list = dao.read();

				quizNumberPnl.removeAll();
				quizNumberBtns = new JButton[list.size()];

				for (int i = 0; i < list.size(); i++) {
					quizNumberBtns[i] = new JButton(i + 1 + "");
					quizNumberBtns[i].addActionListener(this);
					quizNumberPnl.add(quizNumberBtns[i]);
				}

				quizNumberPnl.revalidate();
				quizNumberPnl.repaint();

			} catch (NumberFormatException e) {
				e.printStackTrace();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		} else if (updateRB.isSelected()) {
			try {
				dao.update(m.getNumber(), titleTf.getText(), singerTf.getText(), genreTf.getText(),
						Integer.valueOf(yearTf.getText()));
			} catch (NumberFormatException e) {
				e.printStackTrace();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		} else {
			try {
				dao.delete(m.getNumber());
				list = dao.read();

				quizNumberPnl.removeAll();
				quizNumberBtns = new JButton[list.size()];

				for (int i = 0; i < list.size(); i++) {
					quizNumberBtns[i] = new JButton(i + 1 + "");
					quizNumberBtns[i].addActionListener(this);
					quizNumberPnl.add(quizNumberBtns[i]);
				}

				quizNumberPnl.revalidate();
				quizNumberPnl.repaint();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
}
