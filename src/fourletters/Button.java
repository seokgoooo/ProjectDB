package fourletters;

import java.awt.Font;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class Button {
	Dao dao = new FourlettersDaoImpl();
	Font font = new Font("맑은 고딕", Font.BOLD, 100);
	Random ran = new Random();
	int r = (int) (Math.random() * 5);

	public void ListAdd() {
		try {
			List<fourletters> list = dao.read();
			if (list.size() > 0) {
				for (fourletters r : list) {
					System.out.println(r);
				}
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void questionRead(JTextArea ta) {

		try {
			ta.append(String.valueOf(dao.read()) + "\n");

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void questionDelete(int number) {
		try {
			dao.delete(number);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void questionUpdate(int number, String s) {
		try {
			String[] array = s.split(",");

			dao.update(number, array[0], array[1], array[2]);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void questionCreate(String s) {
		try {
			String[] array = s.split(",");

			dao.create(array[0], array[1], array[2]);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void start_button(JPanel p, JTextArea ta) {
		JButton btn0 = new JButton("시작");
		ta.setFont(font);
		ActionListener a = new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent a) {
				try {
					ta.setText(String.valueOf(dao.read(r).toQuestion()));

				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		};

		btn0.addActionListener(a);
		p.add(btn0);
	}

	public void next_button(JPanel p, JTextArea ta) {
		JButton btn0 = new JButton("다음 문제");
		ta.setFont(font);
		int number = 2;
		ActionListener a = new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent a) {
				try {
					ta.setText(String.valueOf(dao.read(r).toQuestion()));

				} catch (SQLException e) {
					e.printStackTrace();
					System.out.println("문제가없음");
				}
			}
		};

		btn0.addActionListener(a);

		p.add(btn0);
	}

	// 확인버튼
	public void OK_button(JPanel p, JTextArea ta, JTextField tf, String id) {
		JButton btn0 = new JButton("확인");
		ActionListener a = new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent a) {
				try {
					String s = String.valueOf(dao.readan(ta.getText()).toQuestion());
					if (s.equals(tf.getText())) {
						System.out.println("정답");
						int result = (dao.readst(ta.getText()).toNumber());
						dao.clearSave(id, result);
					}
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		};
		btn0.addActionListener(a);

		p.add(btn0);
	}
	
	public void hint_button(JPanel p, JTextArea ta, JTextArea ta2) {
		JButton btn0 = new JButton("확인");
		ActionListener a = new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent a) {
				try {
					String s = String.valueOf(dao.readan(ta.getText()).toQuestion());
					int result = (dao.readst(ta.getText()).toNumber());
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		};
		btn0.addActionListener(a);

		p.add(btn0);
	}

}
