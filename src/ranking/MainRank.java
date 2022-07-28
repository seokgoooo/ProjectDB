package ranking;

import javax.swing.JFrame;
import javax.swing.JTabbedPane;
import javax.swing.UIManager;
import javax.swing.JLabel;

import java.awt.Color;
import java.awt.Panel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Font;

import javax.swing.SwingConstants;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JPanel;
import javax.swing.JProgressBar;

public class MainRank extends JFrame implements ActionListener {
	private JTabbedPane tabbedPane = null;

	private Panel fourlettersPnl = new Panel();
	private Panel capitalsPnl = new Panel();
	private Panel musicsPnl = new Panel();

	private JLabel titleLbl = new JLabel();
	private JLabel[] questionLbl = new JLabel[5];
	private JProgressBar[] progressBar = new JProgressBar[5];
	private JLabel[] percentLbl = new JLabel[5];

	private JPanel bottomPnl = new JPanel();
	private JComboBox<String> comboBox = null;
	private JButton openBtn = null;

	public MainRank() {
		super();
		makeMain();

		test(fourlettersPnl);
		test(capitalsPnl);
		test(musicsPnl);

		int test = 30;
		int y = 150;
		int length = 120;
		for (int i = 0; i < questionLbl.length; i++) {
			questionLbl[i] = new JLabel(i + 1 + "번 문제");
			questionLbl[i].setBounds(6, y, 250, 50);
			questionLbl[i].setForeground(new Color(0, 102, 102));
			questionLbl[i].setFont(new Font("HY얕은샘물M", Font.PLAIN, 40));
			questionLbl[i].setHorizontalAlignment(SwingConstants.CENTER);
			fourlettersPnl.add(questionLbl[i]);

			progressBar[i] = new JProgressBar();
			progressBar[i].setBounds(200, y, 800, 50);
			progressBar[i].setUI(new MyProgressUI());
			progressBar[i].setForeground(new Color(0, 102, 102));
			progressBar[i].setBackground(new Color(255, 255, 255));
			fourlettersPnl.add(progressBar[i]);

			percentLbl[i] = new JLabel(test + "%");
			String[] value = percentLbl[i].getText().split("%");
			progressBar[i].setValue(Integer.parseInt(value[0]));
			percentLbl[i].setBounds(950, y, 200, 50);
			percentLbl[i].setForeground(new Color(0, 102, 102));
			percentLbl[i].setFont(new Font("GOST Common", Font.PLAIN, 45));
			percentLbl[i].setHorizontalAlignment(SwingConstants.CENTER);
			fourlettersPnl.add(percentLbl[i]);
			y += length;
			test += 10;
		}

		makeBottomGUI();
	}

	private void test(Panel pnl) {
		pnl.setBackground(Color.WHITE);
		pnl.setLayout(null);
		titleLbl = new JLabel();
		titleLbl.setText("정답률 TOP 5");
		titleLbl.setForeground(new Color(0, 102, 102));
		titleLbl.setHorizontalAlignment(SwingConstants.CENTER);
		titleLbl.setFont(new Font("Adobe 고딕 Std B", Font.BOLD | Font.ITALIC, 60));
		titleLbl.setBounds(6, 30, 1138, 60);
		pnl.add(titleLbl);
	}

	private void makeMain() {
		setTitle("랭킹");
		setSize(1180, 820);
		getContentPane().setLayout(null);
		setResizable(false);
		setLocationRelativeTo(null);
		dispose();

		tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		getContentPane().add(tabbedPane);
		tabbedPane.setBounds(0, 0, 1180, 800);
		tabbedPane.addTab("사자성어", null, fourlettersPnl, null);
		tabbedPane.addTab("국가수도", null, capitalsPnl, null);
		tabbedPane.addTab("음악", null, musicsPnl, null);
	}

	private void makeBottomGUI() {
		bottomPnl.setBackground(Color.WHITE);
		bottomPnl.setBounds(750, 700, 390, 35);
		fourlettersPnl.add(bottomPnl);
		bottomPnl.setLayout(null);

		comboBox = new JComboBox<>();
		comboBox.setBackground(new Color(0, 102, 102));
		comboBox.setBounds(0, 0, 300, 35);
		bottomPnl.add(comboBox);
		comboBox.setModel(new DefaultComboBoxModel<String>(
				new String[] { "정답률 TOP 5", "오답률 TOP 5", "즐겨찾기한 문제 TOP 5", "힌트본 문제 TOP 5" }));
		comboBox.setForeground(new Color(255, 255, 255));
		comboBox.setFont(new Font("휴먼모음T", Font.PLAIN, 22));

		openBtn = new JButton("보기");
		openBtn.setForeground(new Color(255, 255, 255));
		openBtn.setBackground(new Color(0, 102, 102));
		openBtn.setFont(new Font("휴먼모음T", Font.PLAIN, 22));
		openBtn.setBounds(300, 0, 90, 35);
		bottomPnl.add(openBtn);
		openBtn.addActionListener(this);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		int select = comboBox.getSelectedIndex();
		switch (select) {
		case 0:
			titleLbl.setText(comboBox.getSelectedItem().toString());
			break;

		case 1:
			titleLbl.setText(comboBox.getSelectedItem().toString());
			break;

		case 2:
			titleLbl.setText(comboBox.getSelectedItem().toString());
			break;

		case 3:
			titleLbl.setText(comboBox.getSelectedItem().toString());
			break;

		default:
			break;
		}
	}

	public static void main(String[] args) {
		try {
			UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
		} catch (Exception e) {
		}
		new MainRank().setVisible(true);
	}
}