// 해야할것 : 
// 완료된것 : GUI 구현, 로그인 성공 후 secondFrame 실행

package firstFrame;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
<<<<<<< HEAD
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
=======
import java.io.File;
import java.net.URI;
import java.net.URISyntaxException;
>>>>>>> branch 'main' of https://github.com/seokgoooo/ProjectDB.git
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.KeyStroke;
import javax.swing.SwingConstants;
import javax.swing.UIManager;

import music.MusicPlayer;
import secondFrame.SecondFrame;
import user.User;
import user.UserDao;
import user.UserDaoImpl;

<<<<<<< HEAD
public class FirstFrame extends JFrame implements ActionListener {
	private User login = null;
=======
public class FirstFrame extends JFrame {
	private User user1;
>>>>>>> branch 'main' of https://github.com/seokgoooo/ProjectDB.git
	private UserDao user = new UserDaoImpl();
	private Map<String, User> server = new HashMap<>();
	private JPasswordField pwPf = new JPasswordField(10);
	private JTextField idTf = new JTextField(10);
	private JPanel bottomPnl;
	private JLabel lblId;
	private JLabel lblPassword;
	private JButton signUpBtn;
	private JButton signInBtn;
	private JPanel mainPnl;
	private JLabel titleLbl;
	private JLabel imgLbl01;
	private JLabel imgLbl02;
	private JLabel imgLbl03;
	private MouseCursor mc = new MouseCursor();
	private TextLimit tl = new TextLimit();
	private TextFieldFocus tff = new TextFieldFocus();
	private MusicPlayer player = new MusicPlayer();
	private URI uri = null;
	private SecondFrame sf = new SecondFrame();
	public FirstFrame() throws SQLException {
		super();
		setTitle("그대가 들어5조");
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		makeFrame();
		makeGui();
		
		try {
			uri = FirstFrame.class.getClassLoader().getResource("사랑해도 될까요.mp3").toURI();
		} catch (URISyntaxException e) {
			e.printStackTrace();
		}
		
		player.play(new File(uri));
	}

//      JFrame 기본틀
	public void makeFrame() {
		setSize(1180, 820);
		setVisible(true);
		getContentPane().setBackground(Color.WHITE);
		getContentPane().setLayout(null);
		setResizable(false);
		setLocationRelativeTo(null);
	}

//      JFrame 메인프레임 그리기
	public void makeGui() throws SQLException {
		mainPnl = new JPanel();
		getContentPane().add(mainPnl);
		mainPnl.setBounds(220, 0, 650, 450);
		mainPnl.setBackground(new Color(255, 255, 255));
		mainPnl.setLayout(null);

		bottomPnl = new JPanel();
		getContentPane().add(bottomPnl);
		bottomPnl.setBackground(new Color(255, 255, 255));
		bottomPnl.setBounds(220, 450, 650, 200);
		bottomPnl.setLayout(null);

		titleLbl = new JLabel("그대가 들어5조 Quiz");
		mainPnl.add(titleLbl);
		titleLbl.setBounds(0, 0, 650, 100);
		titleLbl.setHorizontalAlignment(SwingConstants.CENTER);
		titleLbl.setForeground(new Color(0, 102, 102));
		titleLbl.setFont(new Font("휴먼모음T", Font.PLAIN, 55));

		imgLbl01 = new JLabel();
		mainPnl.add(imgLbl01);
		imgLbl01.setBounds(0, 100, 200, 350);
		imgLbl01.setHorizontalAlignment(SwingConstants.CENTER);
		imgLbl01.setIcon(new ImageIcon(FirstFrame.class.getClassLoader().getResource("main1.png")));

		imgLbl02 = new JLabel();
		mainPnl.add(imgLbl02);
		imgLbl02.setBounds(200, 100, 200, 350);
		imgLbl02.setHorizontalAlignment(SwingConstants.CENTER);
		imgLbl02.setIcon(new ImageIcon(FirstFrame.class.getClassLoader().getResource("main2.png")));

		imgLbl03 = new JLabel();
		mainPnl.add(imgLbl03);
		imgLbl03.setBounds(400, 100, 200, 350);
		imgLbl03.setHorizontalAlignment(SwingConstants.CENTER);
		imgLbl03.setIcon(new ImageIcon(FirstFrame.class.getClassLoader().getResource("main3.jpg")));

		bottomPnl.add(idTf);
		idTf.setBounds(250, 0, 400, 50);
		idTf.setFont(new Font("휴먼모음T", Font.PLAIN, 30));
		idTf.setForeground(new Color(255, 255, 255));
		idTf.setBackground(new Color(68, 148, 148));
		idTf.setText("10글자 이내로 입력");
		idTf.addKeyListener(tl);
		idTf.addFocusListener(tff);
		idTf.registerKeyboardAction(this, "login", KeyStroke.getKeyStroke(KeyEvent.VK_ENTER, 0),
				JComponent.WHEN_FOCUSED);

		bottomPnl.add(pwPf);
		pwPf.setBounds(250, 75, 400, 50);
		pwPf.setFont(new Font("Berlin Sans FB", Font.PLAIN, 30));
		pwPf.setForeground(new Color(255, 255, 255));
		pwPf.setBackground(new Color(68, 148, 148));
		pwPf.addKeyListener(tl);
		pwPf.registerKeyboardAction(this, "login", KeyStroke.getKeyStroke(KeyEvent.VK_ENTER, 0),
				JComponent.WHEN_FOCUSED);

		lblId = new JLabel("ID");
		bottomPnl.add(lblId);
		lblId.setBounds(0, 0, 200, 50);
		lblId.setHorizontalAlignment(SwingConstants.RIGHT);
		lblId.setForeground(new Color(0, 102, 102));
		lblId.setFont(new Font("Berlin Sans FB", Font.PLAIN, 35));

		lblPassword = new JLabel("PASSWORD");
		bottomPnl.add(lblPassword);
		lblPassword.setBounds(0, 75, 200, 50);
		lblPassword.setHorizontalAlignment(SwingConstants.RIGHT);
		lblPassword.setForeground(new Color(0, 102, 102));
		lblPassword.setFont(new Font("Berlin Sans FB", Font.PLAIN, 35));

		signUpBtn = new JButton("회원가입");
		bottomPnl.add(signUpBtn);
		signUpBtn.setBounds(250, 150, 200, 50);
		signUpBtn.setBackground(new Color(255, 255, 255));
		signUpBtn.setForeground(new Color(0, 102, 102));
		signUpBtn.setFont(new Font("한컴산뜻돋움", Font.PLAIN, 30));

		signUpBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					new SignUpPage().setVisible(true);
				} catch (SQLException e1) {
				}
			}
		});

		signInBtn = new JButton("로그인");
		bottomPnl.add(signInBtn);
		signInBtn.setBounds(450, 150, 200, 50);
		signInBtn.setBackground(new Color(255, 255, 255));
		signInBtn.setForeground(new Color(0, 102, 102));
		signInBtn.setFont(new Font("한컴산뜻돋움", Font.PLAIN, 30));
<<<<<<< HEAD
		signInBtn.setActionCommand("login");
		signInBtn.addActionListener(this);
=======

		signInBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					userMapping();
				} catch (SQLException e1) {
					e1.printStackTrace();
				}

				String id = new String(idTf.getText());
				String pw = new String(pwPf.getPassword());

				if (server.containsKey(id)) {
					if (pw.equals(server.get(id).getPassword())) {
						showPopUp("로그인 성공");
						player.end();
						try {
							user1 = user.read(id);
						} catch (SQLException e1) {
							e1.printStackTrace();
						}
												
						sf.setUser(user1);
						sf.setVisible(true);
					} else {
						showPopUp("비밀번호가 달라요");
					}
				}

				if (!server.containsKey(id)) {
					showPopUp("회원가입을 해주세요");
				}
			}
		});
>>>>>>> branch 'main' of https://github.com/seokgoooo/ProjectDB.git

		signUpBtn.addMouseListener(mc);
		signInBtn.addMouseListener(mc);
	}

	private void showPopUp(String text) {
		JOptionPane.showMessageDialog(FirstFrame.this, text);
	}

	public void userMapping() throws SQLException {
		int i = 0;
		while (i < user.read().size()) {
			server.put(user.read().get(i).getId(), user.read().get(i));
			i++;
		}
	}

	public User getLoginUser(String id) {
		return server.get(id);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getActionCommand() == "login") {
			loginClick();
		}
	}

	private void loginClick() {
		try {
			userMapping();
		} catch (SQLException e1) {
			e1.printStackTrace();
		}

		String id = new String(idTf.getText());
		String pw = new String(pwPf.getPassword());

		if (server.containsKey(id)) {
			if (pw.equals(getLoginUser(id).getPassword())) {
				showPopUp("로그인 성공");
				try {
					login = user.read(id);
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
				SecondFrame sf = new SecondFrame();
				sf.setLogin(login);
				sf.setVisible(true);
			} else {
				showPopUp("비밀번호가 달라요");
			}
		}

		if (!server.containsKey(id)) {
			showPopUp("회원가입을 해주세요");
		}
	}
}