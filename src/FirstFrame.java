import javax.swing.JFrame;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JButton;
import java.awt.Color;
import javax.swing.JPanel;
import javax.swing.UIManager;
import javax.swing.ImageIcon;

public class FirstFrame extends JFrame {
	private JPasswordField pwPf;
	private JTextField idTf;

	public FirstFrame() {
		super();
		getContentPane().setBackground(UIManager.getColor("window"));
		getContentPane().setLayout(null);
		setVisible(true);
		setSize(1180, 820);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setResizable(false);
		setLocationRelativeTo(null);
		setTitle("그대가 들어5조");

		JPanel bottomPnl = new JPanel();
		getContentPane().add(bottomPnl);
		bottomPnl.setBackground(new Color(255, 255, 255));
		bottomPnl.setBounds(220, 450, 650, 200);
		bottomPnl.setLayout(null);

		idTf = new JTextField();
		bottomPnl.add(idTf);
		idTf.setBounds(250, 0, 400, 50);
		idTf.setFont(new Font("Berlin Sans FB", Font.PLAIN, 30));
		idTf.setForeground(new Color(255, 255, 255));
		idTf.setBackground(new Color(68, 148, 148));
		idTf.setColumns(10);

		pwPf = new JPasswordField();
		bottomPnl.add(pwPf);
		pwPf.setBounds(250, 75, 400, 50);
		pwPf.setFont(new Font("Berlin Sans FB", Font.PLAIN, 30));
		pwPf.setForeground(new Color(255, 255, 255));
		pwPf.setBackground(new Color(68, 148, 148));

		JLabel lblId = new JLabel("ID");
		bottomPnl.add(lblId);
		lblId.setBounds(0, 0, 200, 50);
		lblId.setHorizontalAlignment(SwingConstants.RIGHT);
		lblId.setForeground(new Color(0, 102, 102));
		lblId.setFont(new Font("Berlin Sans FB", Font.PLAIN, 35));

		JLabel lblPassword = new JLabel("PASSWORD");
		bottomPnl.add(lblPassword);
		lblPassword.setBounds(0, 75, 200, 50);
		lblPassword.setHorizontalAlignment(SwingConstants.RIGHT);
		lblPassword.setForeground(new Color(0, 102, 102));
		lblPassword.setFont(new Font("Berlin Sans FB", Font.PLAIN, 35));

		JButton signUpBtn = new JButton("회원가입");
		bottomPnl.add(signUpBtn);
		signUpBtn.setBounds(250, 150, 200, 50);
		signUpBtn.setBackground(new Color(255, 255, 255));
		signUpBtn.setForeground(new Color(0, 102, 102));
		signUpBtn.setFont(new Font("한컴산뜻돋움", Font.PLAIN, 30));

		JButton signInBtn = new JButton("로그인");
		bottomPnl.add(signInBtn);
		signInBtn.setBounds(450, 150, 200, 50);
		signInBtn.setBackground(new Color(255, 255, 255));
		signInBtn.setForeground(new Color(0, 102, 102));
		signInBtn.setFont(new Font("한컴산뜻돋움", Font.PLAIN, 30));

		JPanel mainPnl = new JPanel();
		getContentPane().add(mainPnl);
		mainPnl.setBounds(220, 0, 650, 450);
		mainPnl.setBackground(new Color(255, 255, 255));
		mainPnl.setLayout(null);

		JLabel titleLbl = new JLabel("그대가 들어5조 Quiz");
		mainPnl.add(titleLbl);
		titleLbl.setBounds(0, 0, 650, 100);
		titleLbl.setHorizontalAlignment(SwingConstants.CENTER);
		titleLbl.setForeground(new Color(0, 102, 102));
		titleLbl.setFont(new Font("휴먼모음T", Font.PLAIN, 55));

		JLabel imgLbl_1 = new JLabel("");
		mainPnl.add(imgLbl_1);
		imgLbl_1.setBounds(0, 100, 200, 350);
		imgLbl_1.setHorizontalAlignment(SwingConstants.CENTER);

		imgLbl_1.setIcon(new ImageIcon("D:\\SG\\ProjectDBGit\\resources\\main1.png"));

		JLabel imgLbl_2 = new JLabel("");
		mainPnl.add(imgLbl_2);
		imgLbl_2.setBounds(200, 100, 200, 350);
		imgLbl_2.setHorizontalAlignment(SwingConstants.CENTER);
		imgLbl_2.setIcon(new ImageIcon("D:\\SG\\ProjectDBGit\\resources\\main2.png"));

		JLabel imgLbl_3 = new JLabel("");
		mainPnl.add(imgLbl_3);
		imgLbl_3.setBounds(400, 100, 200, 350);
		imgLbl_3.setHorizontalAlignment(SwingConstants.CENTER);
		imgLbl_3.setIcon(new ImageIcon("D:\\SG\\ProjectDBGit\\resources\\main3.jpg"));
	}

	public void makeGui() {

	}

	public static void main(String[] args) {
		new FirstFrame();
	}
}
