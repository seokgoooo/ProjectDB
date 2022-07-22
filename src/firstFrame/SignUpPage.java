package firstFrame;
import javax.swing.JDialog;
import javax.swing.JLabel;
import java.awt.Color;
import java.awt.Font;
import java.util.LinkedHashMap;
import java.util.Map;

import javax.swing.SwingConstants;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JFormattedTextField;
import javax.swing.JButton;
import javax.swing.JCheckBox;

public class SignUpPage extends JDialog {
	private JTextField idField;
	private JPasswordField passwordField;
	private JFormattedTextField ageField;
	private MouseCursor mc = new MouseCursor();
	private TextLimit tl = new TextLimit();
	private TextFieldFocus tff = new TextFieldFocus();

	public SignUpPage() {
		super();
		// 회원가입 테스트용
		Map<String, String> server = new LinkedHashMap<>();
		server.put("sample", "value");

		setType(Type.POPUP);
		setResizable(false);
		setFont(new Font("Dialog", Font.PLAIN, 20));
		setBackground(new Color(255, 255, 255));
		getContentPane().setBackground(new Color(255, 255, 255));
		setTitle("회원가입");
		setVisible(false);
		setSize(700, 550);
		setLocationRelativeTo(null);
		setAlwaysOnTop(true);
		setModal(true);
		getContentPane().setLayout(null);

		JLabel lblId = new JLabel("ID");
		getContentPane().add(lblId);
		lblId.setBounds(12, 50, 200, 50);
		lblId.setHorizontalAlignment(SwingConstants.RIGHT);
		lblId.setForeground(new Color(0, 102, 102));
		lblId.setFont(new Font("Berlin Sans FB", Font.PLAIN, 30));

		JLabel lblPassword = new JLabel("PASSWORD");
		getContentPane().add(lblPassword);
		lblPassword.setBounds(12, 150, 200, 50);
		lblPassword.setHorizontalAlignment(SwingConstants.RIGHT);
		lblPassword.setForeground(new Color(0, 102, 102));
		lblPassword.setFont(new Font("Berlin Sans FB", Font.PLAIN, 30));

		JLabel lblAge = new JLabel("AGE");
		getContentPane().add(lblAge);
		lblAge.setBounds(12, 250, 200, 50);
		lblAge.setHorizontalAlignment(SwingConstants.RIGHT);
		lblAge.setForeground(new Color(0, 102, 102));
		lblAge.setFont(new Font("Berlin Sans FB", Font.PLAIN, 30));

		JLabel lblManager = new JLabel("MANAGER");
		getContentPane().add(lblManager);
		lblManager.setBounds(12, 350, 200, 50);
		lblManager.setHorizontalAlignment(SwingConstants.RIGHT);
		lblManager.setForeground(new Color(0, 102, 102));
		lblManager.setFont(new Font("Berlin Sans FB", Font.PLAIN, 30));

		idField = new JTextField();
		getContentPane().add(idField);
		idField.setBounds(250, 50, 300, 50);
		idField.setText("10글자 이내로 입력");
		idField.setForeground(new Color(0, 102, 102));
		idField.setBackground(new Color(255, 255, 255));
		idField.setFont(new Font("휴먼모음T", Font.PLAIN, 25));
		idField.setColumns(10);
		idField.addFocusListener(tff);
		idField.addKeyListener(tl);

		passwordField = new JPasswordField();
		getContentPane().add(passwordField);
		passwordField.setBounds(250, 150, 300, 50);
		passwordField.setForeground(new Color(0, 102, 102));
		passwordField.setBackground(Color.WHITE);
		passwordField.setFont(new Font("Berlin Sans FB", Font.PLAIN, 25));
		passwordField.setColumns(10);
		passwordField.addKeyListener(tl);

		JCheckBox managerCheckBox = new JCheckBox(" 관리자일 경우 누르세요");
		getContentPane().add(managerCheckBox);
		managerCheckBox.setBounds(250, 350, 300, 50);
		managerCheckBox.setForeground(new Color(255, 255, 255));
		managerCheckBox.setBackground(new Color(0, 0, 0));
		managerCheckBox.setFont(new Font("휴먼모음T", Font.PLAIN, 25));

		ageField = new JFormattedTextField();
		getContentPane().add(ageField);
		ageField.setBounds(250, 250, 300, 50);
		ageField.setText(" 정수만 입력 가능");
		ageField.setForeground(new Color(0, 102, 102));
		ageField.setBackground(Color.WHITE);
		ageField.setFont(new Font("휴먼모음T", Font.PLAIN, 25));
		ageField.setColumns(10);

		JButton signUpBtn = new JButton("회원가입");
		getContentPane().add(signUpBtn);
		signUpBtn.setBounds(250, 450, 300, 50);
		signUpBtn.setForeground(new Color(0, 102, 102));
		signUpBtn.setBackground(new Color(255, 255, 255));
		signUpBtn.setFont(new Font("휴먼모음T", Font.PLAIN, 25));

		idField.addKeyListener(tl);
		passwordField.addKeyListener(tl);
		signUpBtn.addMouseListener(mc);
	}
}
