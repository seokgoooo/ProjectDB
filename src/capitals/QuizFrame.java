package capitals;

import java.awt.Color;
import java.awt.GridLayout;
import java.util.List;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;

import attempts.AttemptsDAO;
import attempts.AttemptsDAOImpl;
import attempts.AttemptsQuiz;
import favorite.FavoritesDAO;
import favorite.FavoritesDAOImpl;
import sun.tools.jar.resources.jar;
import user.User;

import javax.swing.JCheckBox;
import java.awt.Component;

import javax.swing.JTabbedPane;
import javax.swing.JOptionPane;

public class QuizFrame extends JFrame implements ActionListener {

	private User user;
	private AttemptsDAO attemptsDao = new AttemptsDAOImpl();
	private Manager mg = new Manager();
	private List<Capitals> list = new ArrayList<>();
	private JButton[] allQuiz;
	private List<Integer> clearList = new ArrayList<Integer>();
	private List<Integer> favoriteList = new ArrayList<Integer>();
	private FavoritesDAO favoriteDao = new FavoritesDAOImpl();
	private JCheckBox favorite;
	private Capitals capitals;
	private JTextField answertf;
	private AttemptsQuiz attemptsQuiz;
	private JPanel clearPnl;
	private JButton[] clearQuiz;
	private JButton[] favoriteQuiz;
	private CapitalsDao capitalsDao;
    private int currentNumber;
	public QuizFrame(User user) {
		this.user = user;
		setTitle("수도퀴즈");
		JPanel pnlMain = new JPanel();
		JPanel pnlLEFT = new JPanel();
		JPanel pnlRight = new JPanel();

		// list에 넣기
		try {
			list = mg.read();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		ja = new JTextArea(15, 20);
		ja.setBounds(7, 17, 561, 593);
		answertf = new JTextField(20);
		answertf.setBounds(140, 23, 226, 21);

		// 왼쪽 객관식 버튼
//		JButton[] bt = new JButton[4];
		JButton[] bt2 = new JButton[4];

		// 왼,오 하위 Panel
		JPanel pnlL1 = new JPanel();
		pnlL1.setBounds(7, 724, 561, 57);
		// JPanel pnlL2 = new JPanel();
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
		setContentPane(pnlMain);
		pnlMain.add(pnlLEFT);
		pnlMain.add(pnlRight);
		pnlLEFT.setLayout(null);

		// 왼쪽
		pnlLEFT.add(ja);
		pnlLEFT.add(pnlL1);
		pnlL1.setLayout(null);
		// pnlLEFT.add(pnlL2);

		// --왼쪽[1]
		pnlL1.add(answertf);
		pnlRight.setLayout(null);
		favorite = new JCheckBox("즐겨찾기");
		favorite.setBounds(460, 22, 93, 23);
		favorite.setAlignmentX(Component.RIGHT_ALIGNMENT);
		pnlL1.add(favorite);

		// 오른쪽 탭
		pnlRight.add(pnlR2);
		pnlR2.setLayout(null);

		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBounds(12, 20, 527, 727);
		pnlR2.add(tabbedPane);

		// 전체 문제 panel

		JPanel allPanel = new JPanel();
		tabbedPane.addTab("전체문제", null, allPanel, null);
		allPanel.setLayout(new GridLayout(5, 5));

		allQuiz = new JButton[list.size()];

		for (int i = 0; i < allQuiz.length; i++) {
			allQuiz[i] = new JButton(String.valueOf(i + 1));
			allQuiz[i].addActionListener(this);
			allPanel.add(allQuiz[i]);
		}
//		//
//		allQuiz[0].addActionListener(new ActionListener() {
//			
//			@Override
//			public void actionPerformed(ActionEvent e) {
//				ja.setText(String.valueOf(list.get(0).getContinent()));
//				
//			}
//		});

		// 맞춘문제

		clearPnl = new JPanel();
		tabbedPane.addTab("맞춘 문제", null, clearPnl, null);
		clearPnl.setLayout(new GridLayout(5, 5));

		try {
			clearList = attemptsDao.capitalClearRead(user.getId(), true);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		if (clearList.size() != 0) {
			clearPnl.repaint();
		}

		// 즐겨찾기
		favoritePnl = new JPanel();
		tabbedPane.addTab("즐겨찾기", null, favoritePnl, null);
		favoritePnl.setLayout(new GridLayout(5, 5));

		try {
			favoriteList = favoriteDao.capitalRead(user.getId());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if (favoriteList.size() != 0) {
			favoritePnl.repaint();
		}

		// 왼쪽 확인 버튼 및 텍스트

		confirmBtn = new JButton("확인");
		confirmBtn.setBounds(379, 24, 76, 21);
		confirmBtn.addActionListener(this);
		pnlL1.add(confirmBtn);

		JTextArea ja2 = new JTextArea(20, 30);
		ja2.setBounds(12, 16, 458, 60);
		JPanel pnlhint = new JPanel();
		pnlhint.setBounds(7, 620, 561, 86);
		pnlLEFT.add(pnlhint);

		pnlhint.setBorder(new TitledBorder(new LineBorder(Color.CYAN, 3), "힌트"));
		pnlhint.setLayout(null);

		// --힌트[1]
		pnlhint.add(ja2);
		
		JButton btnNewButton = new JButton("힌트");
		btnNewButton.setBounds(482, 36, 67, 23);
		pnlhint.add(btnNewButton);
		setSize(1180, 820);
		setLocationRelativeTo(null);
		setResizable(false);
		favorite.addActionListener(this);
	}

	// 버튼 이벤트
	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == favorite) {
			//favoriteEvent();
		}

		for (int i = 0; i < allQuiz.length; i++) {
			if (e.getSource() == allQuiz[i]) {
//				allQuizEvent();
				allQuiz[i].addActionListener(this);
				ja.setText(String.valueOf(list.get(i).getContinent()));
				currentNumber = i;
			}
		}

		if (e.getSource() == confirmBtn) {
			System.out.println(currentNumber);
			if (answertf.getText().equals(list.get(currentNumber).getAnswer())) {

				try {
					attemptsDao.updateClear(attemptsQuiz.getId(), attemptsQuiz.getQuizNumber(), true);
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
				JOptionPane.showMessageDialog(QuizFrame.this, "정답입니다.");
				confirmBtn.setEnabled(false);
				clearPnl.repaint();
			} else {
				JOptionPane.showMessageDialog(QuizFrame.this, "오답입니다.");
			}
			
//			if(ja.getText().equals(list.get(0).getContinent())) {
//				answertf.getText().equals(list.get(0).getAnswer());
//				answertf.addActionListener(this);
//				JOptionPane.showMessageDialog(QuizFrame.this, "정답");
//				
//			}
		}
		
		confirmBtn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				if(ja.getText().equals(list.get(0).getContinent())) {
					answertf.getText().equals(list.get(0).getAnswer());
					JOptionPane.showMessageDialog(QuizFrame.this, "정답");
				}
			}
			
		});
		

	}
	
	

//	private void allQuizEvent() {
//		for (int i = 0; i < allQuiz.length; i++) {
//
//		}
//	}

	// 즐겨 찾기
	public void favoriteEvent() {
		if (favorite.isSelected()) {
			try {
				favoriteDao.create(user.getFavoriteID(), list.get(0).getNumber());
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} else {
			try {
				favoriteDao.delete(user.getFavoriteID(), capitals.getNumber());
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		favorite.repaint();
	}

	// 즐겨찾기 표시버튼

	public void favoriteCheck(Capitals capitals) {
		if (favoriteList.contains(capitals.getNumber())) {
			favorite.setSelected(true);
		} else {
			favorite.setSelected(false);
		}
	}

	// 확인버튼
	public void OK_button(JPanel p) {
	}

	public void confirmBtnEvent() {
		if (true) {

			if (answertf.getText().equals(capitals.getAnswer())) {

				try {
					attemptsDao.updateClear(attemptsQuiz.getId(), attemptsQuiz.getQuizNumber(), true);
				} catch (SQLException e) {
					e.printStackTrace();
				}
				JOptionPane.showMessageDialog(QuizFrame.this, "정답입니다.");
				confirmBtn.setEnabled(false);
				clearPnl.repaint();
			} else {
				JOptionPane.showMessageDialog(QuizFrame.this, "오답입니다.");
			}
		}
	}

	// 즐겨찾기에 있는 번호 누를때
	private MouseAdapter mouseAdapter = new MouseAdapter() {

		@Override
		public void mousePressed(MouseEvent e) {
			for (int i = 0; i < favoriteQuiz.length; i++) {
				if (e.getSource() == favoriteQuiz[i]) {
				}
			}
		}
	};

	// 해결 문제에 있는 번호 누를때
	private MouseAdapter mouseAdapter2 = new MouseAdapter() {
		@Override
		public void mouseReleased(MouseEvent e) {
			try {
				for (int i = 0; i < clearQuiz.length; i++) {
					if (e.getSource() == clearQuiz[i]) {
					}
				}

			} catch (NullPointerException e1) {
			}
		}
	};
	private JPanel favoritePnl;
	private JTextArea ja;
	private JButton confirmBtn;

	// 해결 문제 그리기
	public void clearPnlRepaint() {
		clearPnl.removeAll();

		try {
			clearList = attemptsDao.MusicClearRead(user.getClearID(), true);
		} catch (SQLException e) {
			e.printStackTrace();
		}

		clearQuiz = new JButton[clearList.size()];
		for (int i = 0; i < clearQuiz.length; i++) {
			Capitals c = null;

			try {
				c = capitalsDao.read(clearList.get(i));
			} catch (SQLException e) {
				e.printStackTrace();
			}
			clearQuiz[i] = new JButton(String.format("%02d", (list.indexOf(c) + 1)));
			clearQuiz[i].addMouseListener(mouseAdapter2);
			clearPnl.add(clearQuiz[i]);
		}

		clearPnl.revalidate();
		clearPnl.repaint();
	}

	// 즐찾 문제 그리기
	public void favoritePnlRepaint() {
		favorite.removeAll();
		try {
			favoriteList = favoriteDao.musicRead(user.getFavoriteID());
		} catch (SQLException e) {
			e.printStackTrace();
		}

		favoriteQuiz = new JButton[favoriteList.size()];

		for (int i = 0; i < favoriteQuiz.length; i++) {
			Capitals c = null;

			try {
				c = capitalsDao.read(favoriteList.get(i));
			} catch (SQLException e) {
				e.printStackTrace();
			}

			favoriteQuiz[i] = new JButton(String.format("%02d", (list.indexOf(c) + 1)));
			favoriteQuiz[i].addMouseListener(mouseAdapter);
			favorite.add(favoriteQuiz[i]);
		}

		favoritePnl.revalidate();
		favoritePnl.repaint();
	}
}
