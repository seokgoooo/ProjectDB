package fourletters;

import java.awt.Font;
import java.awt.GridLayout;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import kr.co.greenart.dbutil.QuizDBUtil;
import music.Music;

public class Button {
	Dao dao = new FourlettersDaoImpl();
//	Font font = new Font("맑은 고딕", Font.BOLD, 50);
	Random ran = new Random();
	List<fourletters> list = new ArrayList<fourletters>();
	static ManagerMode mode = new ManagerMode();
	static FourlettersDaoImpl fld = new FourlettersDaoImpl();

	static int result = 0;

	public void ListAdd() {
		try {
			dao.read();
			if (fld.list.size() > 0) {
				for (fourletters r : fld.list) {
					
				}
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		System.out.println(fld.list);
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

	public void questionUpdate(String s) {
		try {
			String[] array = s.split(",");

			dao.update(Integer.valueOf(array[0]), array[1], array[2], array[3]);
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
	public void randomQuestion(JTextArea ta) {
		Random random = new Random();
		int index = random.nextInt(fld.list.size());
		System.out.println(index);
		String s = String.valueOf(fld.list.get(index).toString2());
		String[] array = s.split(",");
		ta.setText(array[1]);
	}

	// 확인버튼
	public void OK_button(JButton b, JTextArea ta, JTextField tf, String id, JPanel pnlR4) {
		ActionListener a = new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent a) {
				try {
					String s = String.valueOf(dao.readan(ta.getText()).toQuestion());
					if (s.equals(tf.getText())) {
						System.out.println("정답");
						int result = (dao.readst(ta.getText()).toNumber());
						dao.clearSave(id, result);
						randomQuestion(ta);
						tf.setText("");
					} else {
						System.out.println("오답");
						tf.setText("");
					}
				} catch (SQLException e) {
					e.printStackTrace();
				}

			}
		};
		b.addActionListener(a);

	}

	// 문제보기 버튼
	public void next_button(JButton b, JTextArea ta) {
		ActionListener a = new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent a) {
				randomQuestion(ta);
			}
		};
		b.addActionListener(a);
	}

	// 힌트버튼
	public void hint_button(JButton b, JTextArea ta, JTextArea ta2) {
		Font font = new Font("맑은 고딕", Font.BOLD, 20);
		ta2.setFont(font);

		ActionListener a = new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent a) {
				try {
					System.out.println("1 " + dao.readhint(ta.getText()));
					System.out.println("2 " + dao.readhint(ta.getText()).toHint());
					ta2.setText((dao.readhint(ta.getText()).toHint()));

				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		};
		b.addActionListener(a);
	}

	///////////////////////////////////////////////////////////////////////////////////////
	/////////////////////////////////// 관리자모드///////////////////////////////////////////

	// 문제보기 버튼
	public void read_button(JRadioButton b, JTextArea ta, JTextArea ta2) {
		ActionListener a = new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent a) {
				try {
					ta.setText("");
					ta.setText((dao.read().toString()));
					ta2.setText("");
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		};

		b.addActionListener(a);
	}

	public void MMOk_button(JButton jb, JTextArea ta2, JTextField tf, JRadioButton a, JRadioButton b, JRadioButton c , JPanel pnlR4, JTextArea ta) {
		jb.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent ae) {
				if (a.isSelected()) {
					String s = tf.getText();
					questionCreate(s);
					tf.setText("");
				} else if (b.isSelected()) {
					String s = tf.getText();
					questionUpdate(s);
					tf.setText("");
					
				} else if (c.isSelected()) {
					int i = Integer.valueOf(tf.getText());
					questionDelete(i);
					tf.setText("");
				}

				pnlR4.removeAll();
				fld.list.removeAll(fld.list);
//				ListAdd();
				mode.multipleChoice(pnlR4, ta, tf);
				pnlR4.revalidate();
				pnlR4.repaint();					
			}
		});
		
		a.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				ta2.setText("");
				ta2.setText("추가할 사자성어의 '문제,정답,힌트'를 입력하고 확인을 누르세요.");
			}
		});
		b.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				ta2.setText("");
				ta2.setText("수정할 사자성어의 '번호,문제,정답,힌트'를 입력하고 확인을 누르세요.");
			}
		});
		c.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				ta2.setText("");
				ta2.setText("삭제할 문제의 번호를 입력하고 확인을 누르세요.");
			}
		});
	}
	
	
	public void MMDelete_button(JButton jb, JTextField tf) {
		jb.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent ae) {
				tf.setText("");
			}
		});
	}
	
	
	
}
