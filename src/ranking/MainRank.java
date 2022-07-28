package ranking;

import java.sql.SQLException;

import javax.swing.JFrame;
import javax.swing.JTabbedPane;
import javax.swing.UIManager;

public class MainRank extends JFrame {
	private JTabbedPane tabbedPane = null;

	private MyRankPanel fourlettersPnl = new MyRankPanel("fourletters");
	private MyRankPanel capitalsPnl = new MyRankPanel("capitals");
	private MyRankPanel musicsPnl = new MyRankPanel("music");

	public MainRank() throws SQLException {
		super();
		setTitle("랭킹");
		setSize(1180, 820);
		getContentPane().setLayout(null);
		setResizable(false);
		setLocationRelativeTo(null);
		dispose();

		tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		getContentPane().add(tabbedPane);
		tabbedPane.setBounds(0, 0, 1180, 800);
		tabbedPane.addTab("사자성어", null, fourlettersPnl.getPnl(), null);
		tabbedPane.addTab("국가수도", null, capitalsPnl.getPnl(), null);
		tabbedPane.addTab("음악", null, musicsPnl.getPnl(), null);

	}

	public static void main(String[] args) throws SQLException {
		try {
			UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
		} catch (Exception e) {
		}
		new MainRank().setVisible(true);
	}
}