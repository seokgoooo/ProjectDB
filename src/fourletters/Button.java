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

public class Button {
	Dao dao = new FourlettersDaoImpl();
//	Font font = new Font("맑은 고딕", Font.BOLD, 50);
	Random ran = new Random();
	static List<fourletters> list = new ArrayList<fourletters>();
	static ManagerMode mode = new ManagerMode();

	static int result = 0;

	public void ListAdd() {
		try {
			list = dao.read();
			if (list.size() > 0) {
				for (fourletters r : list) {
					System.out.println(r);
//					count++;
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

	// 시작 버튼
	public void start_button(JButton b, JTextArea ta) {

		ActionListener a = new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent a) {
				int r = (int) (Math.random() * 20);
				try {
					System.out.println(r);
					ta.setText((dao.read(r).toQuestion()));

				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		};
		b.addActionListener(a);
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

	// 다음문제 버튼
	public void next_button(JButton b, JTextArea ta) {
		ActionListener a = new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent a) {
				int r = (int) (Math.random() * list.size());
				try {
					ta.setText(String.valueOf(dao.read(r).toQuestion()));
				} catch (SQLException e) {
					e.printStackTrace();
					System.out.println("문제가없음");
				}
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
	public void read_button(JRadioButton b, JTextArea ta) {
		ActionListener a = new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent a) {
				try {
					ta.setText("");
					ta.setText((dao.read().toString()));
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		};

		b.addActionListener(a);
	}

	public void radioButton(JButton jb, JTextField tf, JRadioButton a, JRadioButton b, JRadioButton c) {
		a.addItemListener(new ItemListener() {
			@Override
			public void itemStateChanged(ItemEvent e) {
				if (e.getStateChange() == ItemEvent.SELECTED) {
					ActionListener create = new ActionListener() {
						@Override
						public void actionPerformed(ActionEvent a) {
							String s = tf.getText();
							questionCreate(s);
							String[] array = s.split(",");

							try {
								int result = (dao.readst(array[0]).toNumber());
								list.add(dao.read(result));
							} catch (SQLException e) {
								e.printStackTrace();
							}
							tf.setText("");
						}
					};
					jb.addActionListener(create);
				}
			}
		});

		b.addItemListener(new ItemListener() {
			@Override
			public void itemStateChanged(ItemEvent e) {
				if (e.getStateChange() == ItemEvent.SELECTED) {
					ActionListener update = new ActionListener() {
						@Override
						public void actionPerformed(ActionEvent a) {
							String s = tf.getText();
							questionUpdate(s);
							String[] array = s.split(",");
							try {
								int result = (dao.readst(array[1]).toNumber());
								list.add(dao.read(result));
							} catch (SQLException e) {
								e.printStackTrace();
							}
							System.out.println("니는또 왜?" + tf.getText());
							tf.setText("");
						}
					};
					jb.addActionListener(update);
				}
			}
		});

		c.addItemListener(new ItemListener() {
			@Override
			public void itemStateChanged(ItemEvent e) {
				if (e.getStateChange() == ItemEvent.SELECTED) {
					ActionListener delete = new ActionListener() {
						@Override
						public void actionPerformed(ActionEvent e) {
							int i = Integer.valueOf(tf.getText());
							questionDelete(i);
							try {
								list.remove(dao.read(i));
							} catch (SQLException q) {
								q.printStackTrace();
							}
							tf.setText("");
						}
					};
					jb.addActionListener(delete);
				}
			}
		});
	}
	public void MMOk_button(JButton jb, JPanel pnlR4, JTextArea ta) {
		jb.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				System.out.println("와 안되너");
				
//				Button b = new Button();
//				GridLayout grid = new GridLayout(4, 6);
//				JButton[] bt = new JButton[b.list.size()];
//
//				for (int i = 0; i < b.list.size(); i++) {
//					String[] array = String.valueOf(b.list.get(i)).split(",");
//					bt[i] = new JButton(array[0] + "번");
//					pnlR4.add(bt[i]);
//				}
//				grid.setVgap(5); // 격자 사이 수직 간격 5 픽셀
//				pnlR4.setLayout(grid);
			}
		});
	}
}
