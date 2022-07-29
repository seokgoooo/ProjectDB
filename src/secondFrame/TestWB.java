package secondFrame;

import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.BorderLayout;
import java.awt.Font;

public class TestWB extends JFrame {
	public TestWB() {
		
		JLabel lblNewLabel = new JLabel("36회");
		lblNewLabel.setFont(new Font("휴먼모음T", Font.PLAIN, 18));
		getContentPane().add(lblNewLabel, BorderLayout.CENTER);
	}
	public static void main(String[] args) {

	}
}
