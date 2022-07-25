package capitals;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import java.awt.Dimension;
import java.awt.Rectangle;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTextPane;
import javax.swing.JLabel;

public class CrudFrame extends JFrame{
	public TestFrame() {
	}
	private JTextField jf2;
	private JTextField textField_1;
	private JTextField textField_2;
	public TestFrame() {
		Manager ma = new Manager();
		
		

		Dimension dim = new Dimension(1180, 520);  //단순 2차원값 입력을 위한 클래스
		
		JFrame frame = new JFrame("관리자 창");
		frame.setLocation(0, 0);
		frame.setPreferredSize(dim);
		
		String header[] = {"number", "힌트(나라)", "정답", "대륙"};
		String body[][] = {
				{"1000", "핀란드", "헬싱키", "유럽"},
				{"1000", "핀란드", "헬싱키", "유럽"},
				
		};
		frame.getContentPane().setLayout(null);
		
		JTable table = new JTable(body, header);
		
		JScrollPane sp = new JScrollPane(table);
		sp.setBounds(0, 22, 402, 250);
		frame.getContentPane().add(sp);
		
		JTextField jf = new JTextField(20);
		jf.setBounds(176, 329, 226, 23);
		frame.getContentPane().add(jf);
		
		JButton btn1 = new JButton("문제 추가");
		btn1.setBounds(10, 282, 111, 23);
		frame.getContentPane().add(btn1);
		
		JButton btn2 = new JButton("문제 수정");
		btn2.setBounds(156, 282, 111, 23);
		frame.getContentPane().add(btn2);
		
		JButton btn3 = new JButton("문제 삭제");
		btn3.setBounds(291, 282, 111, 23);
		frame.getContentPane().add(btn3);
		
		jf2 = new JTextField();
		jf2.setBounds(176, 372, 226, 21);
		frame.getContentPane().add(jf2);
		jf2.setColumns(10);
		
		textField_1 = new JTextField();
		textField_1.setBounds(176, 414, 226, 21);
		frame.getContentPane().add(textField_1);
		textField_1.setColumns(10);
		
		textField_2 = new JTextField();
		textField_2.setBounds(176, 450, 226, 21);
		frame.getContentPane().add(textField_2);
		textField_2.setColumns(10);
		
		JLabel lbl1 = new JLabel("number");
		lbl1.setBounds(75, 333, 85, 15);
		frame.getContentPane().add(lbl1);
		
		JLabel lbl2 = new JLabel("힌트(나라)");
		lbl2.setBounds(75, 375, 85, 15);
		frame.getContentPane().add(lbl2);
		
		JLabel lbl3 = new JLabel("정답");
		lbl3.setBounds(75, 417, 85, 15);
		frame.getContentPane().add(lbl3);
		
		JLabel lbl4 = new JLabel("대륙");
		lbl4.setBounds(75, 453, 85, 15);
		frame.getContentPane().add(lbl4);
		

		
		
		
		
		frame.pack();
		frame.setVisible(true);
		setSize(1180, 820);
		frame.setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		
		
	}

	public static void main(String[] args) {
		new TestFrame();

	}
}
