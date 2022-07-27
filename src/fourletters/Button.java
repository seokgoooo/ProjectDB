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
import java.util.Arrays;
import java.util.List;
import java.util.Random;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import kr.co.greenart.dbutil.QuizDBUtil;
import user.User;
//import music.Music;

public class Button {
	Dao dao = new FourlettersDaoImpl();
	private User user;
//	Font font = new Font("맑은 고딕", Font.BOLD, 50);
	Random ran = new Random();
//	List<fourletters> list = new ArrayList<fourletters>();
	List<Integer> listIn = new ArrayList<Integer>();	
	private ManagerMode mode = new ManagerMode(user);
	private Main main = new Main(user);
	private FourlettersDaoImpl fld = new FourlettersDaoImpl();

	static int result = 0;

	public void ListAdd(String id) {
		try {
			dao.read();
//			dao.favread(id);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void favListAdd(String id) {
		try {
			dao.favread(id);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

//	public void questionRead(JTextArea ta) {
//		try {
//			ta.append(String.valueOf(dao.read()) + "\n");
//
//		} catch (SQLException e) {
//			e.printStackTrace();
//		}
//	}

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
		String s = String.valueOf(fld.list.get(index).toString2());
		String[] array = s.split(",");
		ta.setText(array[1]);
	}

	// 확인버튼
	public void OK_button(JButton b, JTextArea ta, JTextField tf, String id, JPanel pnlR4, JCheckBox cb1) {
		ActionListener a = new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent a) {
				try {
					String s = String.valueOf(dao.readan(ta.getText()).toQuestion());
					int result = (dao.readst(ta.getText()).toNumber());
					if (s.equals(tf.getText())) {
						System.out.println("정답");
						dao.clearSave(id, result);
						randomQuestion(ta);
						tf.setText("");
					} else {
						System.out.println("오답");
						tf.setText("");
					}
					if (dao.favoriteSerch(result) == 1) {
						cb1.setSelected(true);
					} else {
						cb1.setSelected(false);
					}
				} catch (SQLException e) {
					e.printStackTrace();
				}
				// 즐겨찾기 체크 박스 체크 여부
			}
		};
		b.addActionListener(a);

	}

	// 문제보기 버튼
	public void next_button(JButton b, JTextArea ta, JCheckBox cb1 , JTextArea ta2) {
		ActionListener a = new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent a) {
				randomQuestion(ta);
				ta2.setText("");
				try {
					int result = (dao.readst(ta.getText()).toNumber());
					if (fld.favlist.contains(result)) {
						System.out.println(result);
						cb1.setSelected(true);
					} else {
						cb1.setSelected(false);
					}
				} catch (SQLException e) {
					e.printStackTrace();
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

	public void favoriteButton(JPanel p, JTextArea ta, JTextField tf) {
		FourlettersDaoImpl fld = new FourlettersDaoImpl();
		GridLayout grid = new GridLayout(5, 6);
		JButton[] bt = new JButton[fld.list.size()];

		for (int i = 0; i < fld.list.size(); i++) {
			String[] array = String.valueOf(fld.list.get(i)).split(",");
			bt[i] = new JButton(array[0] + "번");
			p.add(bt[i]);

			bt[i].addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent a) {
					try {
						tf.setText(dao.read(Integer.valueOf((array[0]))).toString2());
					} catch (SQLException e) {
						e.printStackTrace();
					}
				}
			});
		}
	}
	
	public void favoriteCheck(JPanel jp, JCheckBox cb, JTextArea ta, JTextField tf, String id) {
		cb.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (cb.isSelected()) {
					try {
						int result = dao.readst(ta.getText()).toNumber();
						System.out.println(id);
						System.out.println(result);
						dao.favoriteUpdate(id, result);
					} catch (SQLException e1) {
						e1.printStackTrace();
					}

				} else {
					try {
						int result = dao.readst(ta.getText()).toNumber();
						dao.favoriteDelete(id, result);
						System.out.println(result + " 즐겨찾기 해제");
					} catch (SQLException e1) {
						e1.printStackTrace();
					}
				}
				jp.removeAll();
				fld.favlist.removeAll(fld.favlist);
				favListAdd(id);
				main.multipleChoice(jp, ta, cb);
				jp.revalidate();
				jp.repaint();
			}
		});
	}

	///////////////////////////////////////////////////////////////////////////////////////
	/////////////////////////////////// 관리자모드///////////////////////////////////////////

	// 관리자 문제보기 버튼
	public void read_button(JRadioButton b, JTextArea ta, JTextArea ta2) {
		ActionListener a = new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent a) {
				try {
					ta.removeAll();
					ta.setText((dao.read().toString()));
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		};

		b.addActionListener(a);
	}

	public void MMOk_button(JButton jb, JTextArea ta2, JTextField tf, JRadioButton a, JRadioButton b, JRadioButton c,
			JPanel pnlR4, JTextArea ta, String id) {
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
					try {
						dao.favoriteDelete(id, i);
					} catch (SQLException e) {
						e.printStackTrace();
					}
					tf.setText("");
				}
				pnlR4.removeAll();
				fld.list.removeAll(fld.list);
				ListAdd(id);
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
