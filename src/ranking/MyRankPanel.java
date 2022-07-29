package ranking;

import java.awt.Color;
import java.awt.Font;
import java.awt.Panel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.SwingConstants;

public class MyRankPanel implements ActionListener {
	private Panel pnl = new Panel();

	private JLabel titleLbl = new JLabel();
	private JLabel[] questionLbl = new JLabel[5];
	private JProgressBar[] progressBar = new JProgressBar[5];
	private JLabel[] percentLbl = new JLabel[5];

	private JPanel bottomPnl = new JPanel();
	private JComboBox<String> comboBox = new JComboBox<String>();
	private JButton openBtn = new JButton();

	private RankingDao dao = new RankingDaoImpl();

	public MyRankPanel(String table) throws SQLException {
		System.out.println(table);
		pnl.setBackground(Color.WHITE);
		pnl.setLayout(null);

		titleLbl.setText("정답률 TOP 5");
		titleLbl.setForeground(new Color(0, 102, 102));
		titleLbl.setHorizontalAlignment(SwingConstants.CENTER);
		titleLbl.setFont(new Font("Adobe 고딕 Std B", Font.BOLD | Font.ITALIC, 60));
		titleLbl.setBounds(6, 30, 1138, 60);
		pnl.add(titleLbl);

		bottomPnl.setBackground(Color.WHITE);
		bottomPnl.setBounds(750, 700, 390, 35);
		bottomPnl.setLayout(null);
		pnl.add(bottomPnl);

		comboBox.setBounds(0, 0, 300, 35);
		comboBox.setBackground(new Color(0, 102, 102));
		comboBox.setForeground(new Color(255, 255, 255));
		comboBox.setFont(new Font("휴먼모음T", Font.PLAIN, 22));
		comboBox.setModel(new DefaultComboBoxModel<String>(
				new String[] { "정답률 TOP 5", "오답률 TOP 5", "즐겨찾기한 문제 TOP 5", "힌트본 문제 TOP 5" }));
		bottomPnl.add(comboBox);

		openBtn = new JButton("보기");
		openBtn.setBounds(300, 0, 90, 35);
		openBtn.setBackground(new Color(0, 102, 102));
		openBtn.setForeground(new Color(255, 255, 255));
		openBtn.setFont(new Font("휴먼모음T", Font.PLAIN, 22));
		openBtn.addActionListener(this);
		bottomPnl.add(openBtn);

		paintMain();
	}

	private void paintMain() throws SQLException {
		int y = 150;
		int length = 120;
		for (int i = 0; i < questionLbl.length; i++) {
			questionLbl[i] = new JLabel(dao.correctRatio("music").get(i).getTitle());
			questionLbl[i].setBounds(6, y, 250, 50);
			questionLbl[i].setForeground(new Color(0, 102, 102));
			questionLbl[i].setFont(new Font("HY얕은샘물M", Font.PLAIN, 40));
			questionLbl[i].setHorizontalAlignment(SwingConstants.CENTER);
			pnl.add(questionLbl[i]);

			progressBar[i] = new JProgressBar();
			progressBar[i].setBounds(200, y, 800, 50);
			progressBar[i].setUI(new MyProgressUI());
			progressBar[i].setForeground(new Color(0, 102, 102));
			progressBar[i].setBackground(new Color(255, 255, 255));
			pnl.add(progressBar[i]);

			percentLbl[i] = new JLabel(dao.correctRatio("music").get(i).getRatio() + "%");
			String[] value = percentLbl[i].getText().split("%");
			progressBar[i].setValue(Integer.parseInt(value[0]));
			percentLbl[i].setBounds(950, y, 200, 50);
			percentLbl[i].setForeground(new Color(0, 102, 102));
			percentLbl[i].setFont(new Font("GOST Common", Font.PLAIN, 45));
			percentLbl[i].setHorizontalAlignment(SwingConstants.CENTER);
			pnl.add(percentLbl[i]);
			y += length;
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		int select = comboBox.getSelectedIndex();
		switch (select) {
		case 0:
			for (int i = 0; i < questionLbl.length; i++) {
				try {
					questionLbl[i].setText(dao.correctRatio("music").get(i).getTitle());
					percentLbl[i].setText(dao.correctRatio("music").get(i).getRatio() + "%");
				} catch (SQLException e1) {
					e1.printStackTrace();
				}

				String[] value = percentLbl[i].getText().split("%");
				progressBar[i].setValue(Integer.parseInt(value[0]));
			}
			pnl.repaint();
			titleLbl.setText(comboBox.getSelectedItem().toString());
			break;

		case 1:
			for (int i = 0; i < questionLbl.length; i++) {
				try {
					questionLbl[i].setText(dao.incorrectRatio("music").get(i).getTitle());
					percentLbl[i].setText(dao.incorrectRatio("music").get(i).getRatio() + "%");
				} catch (SQLException e1) {
					e1.printStackTrace();
				}

				String[] value = percentLbl[i].getText().split("%");
				progressBar[i].setValue(Integer.parseInt(value[0]));
				pnl.add(percentLbl[i]);
			}
			pnl.repaint();
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

	public Panel getPnl() {
		return pnl;
	}
}