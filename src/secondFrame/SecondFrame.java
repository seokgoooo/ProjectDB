// 할일 : 버튼 누르면 기능 실행되게 연결
//       랭킹 기능 쪽 통계 활용, 표현 방법 고민
// 완료 : GUI 구현

package secondFrame;

import javax.swing.JFrame;
import javax.swing.UIManager;

import firstFrame.FirstFrame;
import fourletters.Main;
import music.MusicManagerMode;
import music.MusicQuiz;
import user.User;
<<<<<<< HEAD
import user.UserDao;
import user.UserDaoImpl;
=======
>>>>>>> branch 'main' of https://github.com/seokgoooo/ProjectDB.git

import javax.swing.JButton;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.awt.Color;
import java.awt.Font;

public class SecondFrame extends JFrame implements ActionListener {
	private JButton[] btn = new JButton[6];
<<<<<<< HEAD
	private User login;
	private UserDao user = new UserDaoImpl();
	private Map<String, User> server = new HashMap<>();

	public User getLogin() {
		return login;
	}

	public void setLogin(User login) {
		this.login = login;
=======
	private User user;
	
	MusicManagerMode musicManager = new MusicManagerMode();
	
	
	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
>>>>>>> branch 'main' of https://github.com/seokgoooo/ProjectDB.git
	}

	public SecondFrame() {
		super();
		setTitle("선택");
		setSize(1180, 820);
		getContentPane().setBackground(UIManager.getColor("window"));
		getContentPane().setLayout(new GridLayout(2, 3, 0, 0));

		makeGUI();
		setResizable(false);
		setLocationRelativeTo(null);
	}

	private void makeGUI() {
		btn[0] = new JButton("사자성어 퀴즈");
		btn[1] = new JButton("국가-수도 퀴즈");
		btn[2] = new JButton("퀴즈 랭킹");
		btn[3] = new JButton("음악 퀴즈");
		btn[4] = new JButton("월드컵");
		btn[5] = new JButton("월드컵 랭킹");

		for (int i = 0; i < btn.length; i++) {
			getContentPane().add(btn[i]);
			btn[i].setBackground(new Color(255, 255, 255));
			btn[i].setForeground(new Color(0, 102, 102));
			btn[i].setFont(new Font("한컴산뜻돋움", Font.PLAIN, 30));
			btn[i].addActionListener(this);

			if (i % 2 == 1) {
				btn[i].setBackground(new Color(0, 102, 102));
				btn[i].setForeground(new Color(255, 255, 255));
			}
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Object click = e.getSource();
		if (click == btn[0]) {
			// 사자성어 퀴즈 열기
<<<<<<< HEAD
			if (login.isManager()) {
				// 관리자모드
			} else {
				// 사용자모드
				new Main().setVisible(true);
			}
=======
			new Main(user).setVisible(true);
>>>>>>> branch 'main' of https://github.com/seokgoooo/ProjectDB.git
		} else if (click == btn[1]) {
			// 국가-수도 퀴즈 열기
			if (login.isManager()) {

			} else {

			}
		} else if (click == btn[2]) {
			// 퀴즈 랭킹 열기

		} else if (click == btn[3]) {
			// 음악 퀴즈 열기
<<<<<<< HEAD
			if (login.isManager()) {

			} else {
				new MusicQuiz().setVisible(true);
			}
=======
			if(user.isManager()) {
				musicManager.setVisible(true);
			} else {
				MusicQuiz musicQuiz = new MusicQuiz(user);
				musicQuiz.setVisible(true);
			}
				
>>>>>>> branch 'main' of https://github.com/seokgoooo/ProjectDB.git
		} else if (click == btn[4]) {
			// 월드컵 열기

		} else if (click == btn[5]) {
			// 월드컵 랭킹 창 열기

		}
	}
<<<<<<< HEAD

	public void userMapping() throws SQLException {
		int i = 0;
		while (i < user.read().size()) {
			server.put(user.read().get(i).getId(), user.read().get(i));
			i++;
		}
	}
}
=======
}
>>>>>>> branch 'main' of https://github.com/seokgoooo/ProjectDB.git
