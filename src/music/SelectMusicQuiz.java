package music;

import javax.swing.JFrame;

public class SelectMusicQuiz extends JFrame {
	public SelectMusicQuiz() {
		setSize(500,500);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
	}
	
	public static void main(String[] args) {
		new SelectMusicQuiz().setVisible(true);
	}
	
}
