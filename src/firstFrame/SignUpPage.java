package firstFrame;

import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.LinkedHashMap;
import java.util.Map;

import javax.swing.SwingConstants;
import javax.swing.text.NumberFormatter;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JFormattedTextField;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JPanel;

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

		JPanel panel = new JPanel();
		panel.setBackground(new Color(255, 255, 255));
		panel.setBounds(12, 50, 538, 450);
		getContentPane().add(panel);
		panel.setLayout(null);

		JLabel lblId = new JLabel("ID");
		lblId.setBounds(0, 0, 200, 50);
		panel.add(lblId);
		lblId.setHorizontalAlignment(SwingConstants.RIGHT);
		lblId.setForeground(new Color(0, 102, 102));
		lblId.setFont(new Font("Berlin Sans FB", Font.PLAIN, 30));

		JLabel lblPassword = new JLabel("PASSWORD");
		lblPassword.setBounds(0, 100, 200, 50);
		panel.add(lblPassword);
		lblPassword.setHorizontalAlignment(SwingConstants.RIGHT);
		lblPassword.setForeground(new Color(0, 102, 102));
		lblPassword.setFont(new Font("Berlin Sans FB", Font.PLAIN, 30));

		JLabel lblAge = new JLabel("AGE");
		lblAge.setBounds(0, 200, 200, 50);
		panel.add(lblAge);
		lblAge.setHorizontalAlignment(SwingConstants.RIGHT);
		lblAge.setForeground(new Color(0, 102, 102));
		lblAge.setFont(new Font("Berlin Sans FB", Font.PLAIN, 30));

		JLabel lblManager = new JLabel("MANAGER");
		lblManager.setBounds(0, 300, 200, 50);
		panel.add(lblManager);
		lblManager.setHorizontalAlignment(SwingConstants.RIGHT);
		lblManager.setForeground(new Color(0, 102, 102));
		lblManager.setFont(new Font("Berlin Sans FB", Font.PLAIN, 30));

		idField = new JTextField();
		idField.setBounds(238, 0, 300, 50);
		panel.add(idField);
		idField.setText("10글자 이내로 입력");
		idField.setForeground(new Color(0, 102, 102));
		idField.setBackground(new Color(255, 255, 255));
		idField.setFont(new Font("휴먼모음T", Font.PLAIN, 25));
		idField.setColumns(10);

		passwordField = new JPasswordField();
		passwordField.setBounds(238, 100, 300, 50);
		panel.add(passwordField);
		passwordField.setForeground(new Color(0, 102, 102));
		passwordField.setBackground(Color.WHITE);
		passwordField.setFont(new Font("Berlin Sans FB", Font.PLAIN, 25));
		passwordField.setColumns(10);

		JCheckBox managerCheckBox = new JCheckBox(" 관리자일 경우 누르세요");
		managerCheckBox.setBounds(238, 300, 300, 50);
		panel.add(managerCheckBox);
		managerCheckBox.setForeground(new Color(255, 255, 255));
		managerCheckBox.setBackground(new Color(0, 0, 0));
		managerCheckBox.setFont(new Font("휴먼모음T", Font.PLAIN, 25));

		NumberFormatter range = new NumberFormatter();
		range.setValueClass(Integer.class);
		range.setMinimum(new Integer(1));
		range.setMaximum(new Integer(150));

		ageField = new JFormattedTextField(range);
		ageField.setBounds(238, 200, 300, 50);
		panel.add(ageField);
		ageField.setText("정수만 입력 가능");
		ageField.setForeground(new Color(0, 102, 102));
		ageField.setBackground(Color.WHITE);
		ageField.setFont(new Font("휴먼모음T", Font.PLAIN, 25));
		ageField.setColumns(10);

		JButton signUpBtn = new JButton("회원가입");
		signUpBtn.setBounds(238, 400, 300, 50);
		panel.add(signUpBtn);
		signUpBtn.setForeground(new Color(0, 102, 102));
		signUpBtn.setBackground(new Color(255, 255, 255));
		signUpBtn.setFont(new Font("휴먼모음T", Font.PLAIN, 25));

		JLabel lblAge_1 = new JLabel("ㅁㄴㅇㄻㄴㅇㄹ");
		panel.add(lblAge_1);
		lblAge_1.setBounds(238, 250, 300, 30);
		lblAge_1.setHorizontalAlignment(SwingConstants.LEFT);
		lblAge_1.setForeground(new Color(255, 0, 51));
		lblAge_1.setFont(new Font("휴먼모음T", Font.PLAIN, 20));

		JLabel lblAge_1_1 = new JLabel("ㅁㄴㅇㄻㄴㅇㄹ");
		lblAge_1_1.setBounds(238, 50, 300, 30);
		panel.add(lblAge_1_1);
		lblAge_1_1.setHorizontalAlignment(SwingConstants.LEFT);
		lblAge_1_1.setForeground(new Color(255, 0, 51));
		lblAge_1_1.setFont(new Font("휴먼모음T", Font.PLAIN, 20));

		signUpBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					Integer.parseInt(ageField.getText());
					lblAge_1.setText("");
				} catch (NumberFormatException f) {
					lblAge_1.setText("양수를 입력하세요");
				}
			}
		});

		signUpBtn.addMouseListener(mc);
		ageField.addFocusListener(new FocusListener() {
			@Override
			public void focusLost(FocusEvent e) {
				JTextField src = (JTextField) e.getSource();
				if (src.getText().equals("")) {
					src.setText("정수만 입력 가능");
				}
			}

			@Override
			public void focusGained(FocusEvent e) {
				JTextField src = (JTextField) e.getSource();
				if (src.getText().equals("정수만 입력 가능")) {
					src.setText("");
				}
			}
		});
		passwordField.addKeyListener(tl);
		passwordField.addKeyListener(tl);
		idField.addFocusListener(tff);
		idField.addKeyListener(tl);

	}
}
