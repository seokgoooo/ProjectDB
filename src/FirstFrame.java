import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.UIManager;

public class FirstFrame extends JFrame {
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
	private SignUpPage sup = new SignUpPage();

	public FirstFrame() {
		super();
		makeFrame();
		makeGui();
	}

	public void makeFrame() {
		setSize(1180, 820);
		setVisible(true);
		getContentPane().setBackground(UIManager.getColor("window"));
		getContentPane().setLayout(null);
		setResizable(false);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setTitle("그대가 들어5조");
		setLocationRelativeTo(null);
	}

	public void makeGui() {
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
		idTf.setFont(new Font("Berlin Sans FB", Font.PLAIN, 30));
		idTf.setForeground(new Color(255, 255, 255));
		idTf.setBackground(new Color(68, 148, 148));
		idTf.addKeyListener(tl);

		bottomPnl.add(pwPf);
		pwPf.setBounds(250, 75, 400, 50);
		pwPf.setFont(new Font("Berlin Sans FB", Font.PLAIN, 30));
		pwPf.setForeground(new Color(255, 255, 255));
		pwPf.setBackground(new Color(68, 148, 148));
		pwPf.addKeyListener(tl);

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
				sup.setVisible(true);
			}
		});

		signInBtn = new JButton("로그인");
		bottomPnl.add(signInBtn);
		signInBtn.setBounds(450, 150, 200, 50);
		signInBtn.setBackground(new Color(255, 255, 255));
		signInBtn.setForeground(new Color(0, 102, 102));
		signInBtn.setFont(new Font("한컴산뜻돋움", Font.PLAIN, 30));

		signUpBtn.addMouseListener(mc);
		signInBtn.addMouseListener(mc);
	}

	public static void main(String[] args) {
		new FirstFrame();
	}
}