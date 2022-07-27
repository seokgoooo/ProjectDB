package capitals;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.text.TabableView;

import java.util.List;
import java.util.Vector;
import javax.swing.JPanel;

public class CrudFrame extends JFrame {
	public void setTable() {

	}

	public CrudFrame() {
		CapitalsDao dao = new Manager();

		Dimension dim = new Dimension(600, 520); // 단순 2차원값 입력을 위한 클래스

		JFrame frame = new JFrame("관리자 창");
		frame.setLocation(0, 0);
		frame.setPreferredSize(dim);

		List<Capitals> list = new ArrayList<>();

		Manager mg = (Manager) dao;

		try {
			list = dao.read();
			System.out.println("되냐???");
			System.out.println(list);
		} catch (SQLException e) {
			e.printStackTrace();
		}

		String header[] = { "number", "힌트(나라)", "정답", "대륙" };

		String body[][] = new String[list.size()][4];
		for (int i = 0; i < list.size(); i++) {
			String[] bodyS = new String[header.length];
			bodyS[0] = "" + list.get(i).getNumber();
			bodyS[1] = list.get(i).getQuestion();
			bodyS[2] = list.get(i).getAnswer();
			bodyS[3] = list.get(i).getContinent();

			body[i] = bodyS;
		}

		frame.getContentPane().setLayout(null);

		JPanel panel = new JPanel();
		panel.setBounds(0, 22, 402, 250);
		frame.getContentPane().add(panel);
		panel.setLayout(null);

		JTable table = new JTable(body, header);
		table.setBackground(new Color(255, 222, 173));

		JScrollPane sp = new JScrollPane(table);
		sp.setBounds(0, 0, 402, 250);
		panel.add(sp);
		sp.setBorder(new TitledBorder(new LineBorder(new Color(255, 215, 0), 4), "\uBB38\uC81C \uBAA9\uB85D",
				TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));

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

		JTextField jt1 = new JTextField();
		jt1.setBounds(176, 372, 226, 21);
		frame.getContentPane().add(jt1);
		jt1.setColumns(10);

		JTextField jt2 = new JTextField();
		jt2.setBounds(176, 414, 226, 21);
		frame.getContentPane().add(jt2);
		jt2.setColumns(10);

		JTextField jt3 = new JTextField();
		jt3.setBounds(176, 450, 226, 21);
		frame.getContentPane().add(jt3);
		jt3.setColumns(10);

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

		// 버튼 구현
		// 문제 추가 버튼

		btn1.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (e.getSource() == btn1) {
					try {
						mg.create(Integer.valueOf(jf.getText()), jt1.getText(), jt2.getText(), jt3.getText());
						JOptionPane.showMessageDialog(CrudFrame.this, "추가 되었습니다.");
						table.revalidate();
						table.repaint();

					} catch (NumberFormatException e1) {
						System.out.println(e.getSource());
					} catch (SQLException e1) {
						System.out.println(e1.getErrorCode());
						e1.printStackTrace();
					}

				} else {
					JOptionPane.showMessageDialog(CrudFrame.this, "제대로해라");
				}
			}
		});

		frame.pack();
		frame.setVisible(true);
		setSize(1180, 820);
		frame.setDefaultCloseOperation(EXIT_ON_CLOSE);
	}

	public static void main(String[] args) {
		new CrudFrame();

	}
}
