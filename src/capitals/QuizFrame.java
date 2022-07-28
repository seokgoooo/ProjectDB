package capitals;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.HeadlessException;
import java.util.List;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URL;
import java.sql.SQLException;
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
import attempts.AttemptsQuiz;
import favorite.FavoritesDAO;
import favorite.FavoritesDAOImpl;
import fourletters.favorites;
import music.Music;
import user.User;

import javax.swing.JCheckBox;
import java.awt.Component;
import java.awt.Dimension;

import javax.swing.JTabbedPane;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

public class QuizFrame extends JFrame implements ActionListener  {
	
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
	private JButton confirmBtn;
	private AttemptsQuiz attemptsQuiz;
	private JPanel clearPnl;
		

	
	public QuizFrame(User user) {
		this.user = user;
		
		
		
		JFrame fr = new JFrame("퀴즈 프로그램");
		JPanel pnlMain = new JPanel();
		JPanel pnlLEFT = new JPanel();
		JPanel pnlRight = new JPanel();
		
		//list에 넣기
		try {
			list = mg.read();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		// 문제와 정답을 맞출 텍스트 필드들
		JTextArea ja = new JTextArea(15, 20);
		ja.setBounds(7, 17, 561, 593);
		answertf = new JTextField(20);
		answertf.setBounds(140, 23, 226, 21);

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
				
				//전체 문제 panel
				
				JPanel allPanel = new JPanel();
				tabbedPane.addTab("전체문제", null, allPanel, null);
				allPanel.setLayout(new GridLayout(5, 5));
				
				allQuiz = new JButton[list.size()];

				for (int i = 0; i < allQuiz.length; i++) {
					allQuiz[i] = new JButton(String.valueOf(i + 1));
					allQuiz[i].addActionListener(this);
					allPanel.add(allQuiz[i]);
				}
				
				clearPnl = new JPanel();
				tabbedPane.addTab("맞춘 문제", null, clearPnl, null);
				clearPnl.setLayout(new GridLayout(5, 5));
				
				try {
					clearList = attemptsDao.capitalClearRead(user.getId(), true);
				} catch (SQLException e) {
					e.printStackTrace();
				} if(clearList.size() != 0) {
					clearPnl.repaint();
				}
				
				// 즐겨 찾기 panel
				JPanel favoritePnl = new JPanel();
				tabbedPane.addTab("즐겨찾기", null, favoritePnl, null);
				favoritePnl.setLayout(new GridLayout(5, 5));
				
				try {
					favoriteList = favoriteDao.capitalRead(user.getId());
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} if(favoriteList.size() != 0) {
					favoritePnl.repaint();
				}

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
				
				
//				// 라벨이미지
//				URL ImageUrl = QuizFrame.class.getClassLoader().getResource("number1.png");
//				JLabel imagelbl = new JLabel(new ImageIcon(ImageUrl));
//				
//				imagelbl.setBounds(12, 39, 544, 550);
//				pnlLEFT.add(imagelbl);
//				


		setSize(1180, 820);
		

		fr.setLocationRelativeTo(null);
		fr.setResizable(false);
		fr.setVisible(true);
		fr.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		
		favorite.addActionListener(this);
		
	
		
	}
	
	// 버튼 이벤트
		@Override
		public void actionPerformed(ActionEvent e) {
			if (e.getSource() == favorite) {
				favoriteEvent();
//			} else if (e.getSource() == pauseBtn) {
//				pauseBtnEvent();
//			} else if (e.getSource() == playBtn) {
//				playBtnEvent();
//			} else if (e.getSource() == prevBtn) {
//				prevBtnEvent();
//			} else if (e.getSource() == nextBtn) {
//				nextBtnEvent();
//			} else if (e.getSource() == replayBtn) {
//				replayBtnEvent();
//			} else if (e.getSource() == favoriteCb) {
//				favoriteCbEvent();
//			} else {
//				for (int i = 0; i < allQuiz.length; i++) {
//					if (e.getSource() == allQuiz[i]) {
//						clickEvent(i);
//					}
//				}
			}
		}
		
		//즐겨 찾기
		public void favoriteEvent() {
			if(favorite.isSelected()) {
				try {
					favoriteDao.create(user.getFavoriteID(), capitals.getNumber());
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
		
		//즐겨찾기 표시버튼
	    
		public void favoriteCheck(Capitals capitals) {
			if (favoriteList.contains(capitals.getNumber())) {
				favorite.setSelected(true);
			} else {
				favorite.setSelected(false);
			}
		}
	
	
	
	




	// 확인버튼
	public void OK_button(JPanel p) {
		confirmBtn = new JButton("확인");
		confirmBtn.setBounds(378, 22, 74, 23);
		p.add(confirmBtn);
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
}

//				try {
//					attemptsDao.updateCount(attemptsQuiz.getId(), attemptsQuiz.getQuizNumber(),
//							attemptsQuiz.getAttemptsCount());
//				} catch (SQLException e) {
//					e.printStackTrace();
//				}
//			} else {
//				JOptionPane.showMessageDialog(pnlMain, "노래를 먼저 재생해 주세요.");
//			}

	



//	public static void main(String[] args) {
//		new QuizFrame();
//	}


