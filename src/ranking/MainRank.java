package ranking;

import javax.swing.JFrame;
import javax.swing.JTabbedPane;
import javax.swing.UIManager;
import javax.swing.JLabel;

import java.awt.Color;
import java.awt.Canvas;
import java.awt.Panel;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.Font;
import javax.swing.SwingConstants;
import java.awt.Choice;

public class MainRank extends JFrame {
	private JTabbedPane tabbedPane = null;

	private Panel fourlettersPnl = new Panel();
	private Panel capitalsPnl = new Panel();
	private Panel musicsPnl = new Panel();

	private JLabel titleLbl = new JLabel();
	private JLabel[] questionLbl = new JLabel[5];
	private Canvas[] canvas = new Canvas[10];
	private JLabel[] percentLbl = new JLabel[5];

	private Choice choice;

	public MainRank() {
		super();
		setTitle("랭킹");
		makeFrame();
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				super.windowClosing(e);
				dispose();
			}
		});

	}

	public void makeFrame() {
		setSize(1180, 820);
		getContentPane().setLayout(null);

		tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBounds(0, 0, 1180, 800);
		getContentPane().add(tabbedPane);

		makeTabInPnl(fourlettersPnl);
		tabbedPane.addTab("사자성어", null, fourlettersPnl, null);

		makeTabInPnl(capitalsPnl);
		tabbedPane.addTab("국가수도", null, capitalsPnl, null);

		makeTabInPnl(musicsPnl);
		tabbedPane.addTab("음악", null, musicsPnl, null);

		setResizable(false);
		setLocationRelativeTo(null);
	}

	private void makeTabInPnl(Panel pnl) {
		pnl.setBackground(Color.WHITE);
		pnl.setLayout(null);

		choice = new Choice();
		choice.setForeground(new Color(0, 102, 102));
		choice.setFont(new Font("휴먼모음T", Font.PLAIN, 25));
		choice.setBounds(860, 5, 300, 50);
		choice.add("정답률 TOP 5");
		choice.add("오답률 TOP 5");
		choice.add("즐겨찾기한 문제 TOP 5");
		choice.add("힌트본 문제 TOP 5");
		pnl.add(choice);

		choice.addItemListener(new ItemListener() {

			@Override
			public void itemStateChanged(ItemEvent e) {
				

				titleLbl.setText(choice.getSelectedItem());

			}
		});

		titleLbl.setText("정답률 TOP 5");
		titleLbl.setForeground(new Color(0, 102, 102));
		titleLbl.setHorizontalAlignment(SwingConstants.CENTER);
		titleLbl.setFont(new Font("Adobe 고딕 Std B", Font.BOLD | Font.ITALIC, 60));
		titleLbl.setBounds(6, 30, 1138, 60);
		pnl.add(titleLbl);

		int x = 250;
		int width = 70;
		for (int i = 0; i < canvas.length; i++) {
			canvas[i] = new Canvas();
			canvas[i].setBackground(Color.RED);
			canvas[i].setBounds(x, 150, width, 50);
			pnl.add(canvas[i]);
			x += width;
		}

		int y = 150;
		int length = 120;
		for (int i = 0; i < questionLbl.length; i++) {
			questionLbl[i] = new JLabel(i + 1 + "번 문제");
			questionLbl[i].setBounds(6, y, 250, 50);
			questionLbl[i].setForeground(new Color(0, 102, 102));
			questionLbl[i].setFont(new Font("HY얕은샘물M", Font.PLAIN, 40));
			questionLbl[i].setHorizontalAlignment(SwingConstants.CENTER);
			pnl.add(questionLbl[i]);

			percentLbl[i] = new JLabel("75%");
			percentLbl[i].setBounds(canvas[9].getX() + 20, y, 200, 50);
			percentLbl[i].setForeground(new Color(0, 102, 102));
			percentLbl[i].setFont(new Font("GOST Common", Font.PLAIN, 40));
			percentLbl[i].setHorizontalAlignment(SwingConstants.CENTER);
			pnl.add(percentLbl[i]);
			y += length;
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