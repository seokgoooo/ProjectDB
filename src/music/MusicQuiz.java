package music;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.net.URI;
import java.net.URISyntaxException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;
import javax.swing.JToggleButton;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;

import attempts.AttemptsDAO;
import attempts.AttemptsDAOImpl;

public class MusicQuiz extends JFrame implements ActionListener {
	private MusicDao musicDao = new MusicDaoImpl();
	private MusicPlayer player = new MusicPlayer();
	private Music currentMusic = null;
	private Music prevMusic = null;
	private Map<Music, Music> map = new HashMap<>();

	private JButton confirmBtn;
	private JTextField answerTf;
	private List<Music> list = new ArrayList<>();
	private JButton prevBtn;
	private JButton pauseBtn;
	private JButton playBtn;
	private JButton nextBtn;
	private JButton replayBtn;
	private JButton[] quizList;
	private JLabel timeLbl;
	private Timer timer;
	private JLabel quizNumberLbl;
	private JToggleButton favoriteTBtn;

	private final int timeOut = 60;
	private boolean play = false;
	private boolean first = true;
	private boolean prev = false;

	public MusicQuiz() {
		JPanel pnlMain = new JPanel();
		JPanel pnlLEFT = new JPanel();
		JPanel pnlRight = new JPanel();

		// 왼쪽 하위 Panel
		JPanel leftTopPnl = new JPanel();
		JPanel answerPnl = new JPanel();
		JPanel questionPnl = new JPanel();

		questionPnl.setPreferredSize(new Dimension(700, 600));

		// 오른쪽 하위 Panel
		JPanel showQuizPnl = new JPanel();
		JPanel functionPnl = new JPanel();

		try {
			list = musicDao.read();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		// 문제와 정답을 맞출 텍스트 필드들
		answerTf = new JTextField(20);
		confirmBtn = new JButton("확인");
		confirmBtn.addActionListener(this);

		answerPnl.add(answerTf);
		answerPnl.add(confirmBtn);

		// tap 메뉴
		JTabbedPane showQuizTab = new JTabbedPane();
		JPanel quizAllPnl = new JPanel();
		JPanel quizFavoritePnl = new JPanel();
		JPanel quizClearPnl = new JPanel();

		showQuizTab.addTab("전체 문제", quizAllPnl);
		showQuizTab.addTab("즐겨 찾기", quizFavoritePnl);
		showQuizTab.addTab("해결 문제", quizClearPnl);

		showQuizPnl.add(showQuizTab);

		showQuizPnl.setPreferredSize(new Dimension(400, 500));
		showQuizTab.setPreferredSize(new Dimension(380, 500));

		// 전체 문제 panel
		quizAllPnl.setLayout(new GridLayout(5, 5));

		quizList = new JButton[list.size()];

		for (int i = 0; i < quizList.length; i++) {
			quizList[i] = new JButton(String.valueOf(i + 1));
			quizList[i].addActionListener(this);
			quizAllPnl.add(quizList[i]);
		}

		// 왼쪽 버튼
		leftTopPnl.setLayout(new BorderLayout());

		quizNumberLbl = new JLabel("문제 번호");
		timeLbl = new JLabel("" + timeOut);
		favoriteTBtn = new JToggleButton("즐겨찾기");

		leftTopPnl.add(quizNumberLbl, "West");
		leftTopPnl.add(timeLbl, "Center");
		timeLbl.setHorizontalAlignment((int) CENTER_ALIGNMENT);
		leftTopPnl.add(favoriteTBtn, "East");

		prevBtn = new JButton("이전");
		prevBtn.addActionListener(this);
		pauseBtn = new JButton("일시정지");
		pauseBtn.addActionListener(this);
		playBtn = new JButton("재생");
		playBtn.addActionListener(this);
		nextBtn = new JButton("다음");
		nextBtn.addActionListener(this);
		replayBtn = new JButton("다시 재생");
		replayBtn.addActionListener(this);

		pauseBtn.setVisible(false);
		replayBtn.setVisible(false);

		functionPnl.add(prevBtn);
		functionPnl.add(pauseBtn);
		functionPnl.add(playBtn);
		functionPnl.add(replayBtn);
		functionPnl.add(nextBtn);

		// panel 레이아웃
		pnlMain.setLayout(new BoxLayout(pnlMain, BoxLayout.X_AXIS));
		pnlLEFT.setLayout(new BoxLayout(pnlLEFT, BoxLayout.Y_AXIS));
		pnlRight.setLayout(new BoxLayout(pnlRight, BoxLayout.Y_AXIS));

		// 테두리
		pnlLEFT.setBorder(new TitledBorder(new LineBorder(Color.pink, 3), "문제"));
		pnlRight.setBorder(new TitledBorder(new LineBorder(Color.pink, 3), "기타"));

		leftTopPnl.setBorder(new TitledBorder(new LineBorder(Color.green, 3), "시간과 즐겨찾기"));
		questionPnl.setBorder(new TitledBorder(new LineBorder(Color.green, 3), "lp사진 및 힌트"));
		answerPnl.setBorder(new TitledBorder(new LineBorder(Color.green, 3), "주관식 "));

		showQuizPnl.setBorder(new TitledBorder(new LineBorder(Color.CYAN, 3), "문제리스트"));
		functionPnl.setBorder(new TitledBorder(new LineBorder(Color.yellow, 3), "기능"));

		// 메인
		add(pnlMain);
		pnlMain.add(pnlLEFT);
		pnlMain.add(pnlRight);

		// 왼쪽 panel
		pnlLEFT.add(leftTopPnl);

		pnlLEFT.add(questionPnl);
		pnlLEFT.add(answerPnl);

		// 오른쪽 panel
		pnlRight.add(showQuizPnl);
		pnlRight.add(functionPnl);

		getMusic(list);
		setSize(1180, 820);
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	public static void main(String[] args) {
		new MusicQuiz().setVisible(true);
	}

	// 버튼 이벤트
	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == confirmBtn) {
			confirmBtnEvent();
		} else if (e.getSource() == pauseBtn) {
			pauseBtnEvent();
		} else if (e.getSource() == playBtn) {
			playBtnEvent();
		} else if (e.getSource() == prevBtn) {
			prevBtnEvent();
		} else if (e.getSource() == nextBtn) {
			nextBtnEvent();
		} else if (e.getSource() == replayBtn) {
			replayBtnEvent();
		} else {
			for (int i = 0; i < quizList.length; i++) {
				if (e.getSource() == quizList[i]) {
					if (play) {
						player.end();
						timer.cancel();
					}

					pauseBtn.setVisible(false);
					playBtn.setVisible(true);
					replayBtn.setVisible(false);

					prevMusic = currentMusic;
					currentMusic = list.get(i);
					quizNumberLbl.setText(String.valueOf(i + 1));
					map.put(currentMusic, prevMusic);

					timeLbl.setText("" + 60);

					first = true;
				}
			}
		}
	}

	// 다시 재생 버튼
	private void replayBtnEvent() {
		replayBtn.setVisible(false);
		pauseBtn.setVisible(true);
		player.play(new File(getURI(currentMusic.getTitle())));

		try {
			Music music = musicDao.read(currentMusic.getNumber());
			musicDao.playCountPlus(music.getNumber(), music.getPlayCount());
		} catch (SQLException e) {
			e.printStackTrace();
		}

		countDown();
	}

	// 확인 버튼 이벤트
	public void confirmBtnEvent() {
		if (answerTf.getText().equals(currentMusic.getTitle())) {
			player.stop();
			System.out.println("정답");
		} else {
			System.out.println("오답");
		}
	}

	// 일시 정지 버튼 이벤트
	public void pauseBtnEvent() {
		player.stop();
		pauseBtn.setVisible(false);
		playBtn.setVisible(true);
	}

	// 재생 버튼 이벤트
	public void playBtnEvent() {
		if (prev) {
			currentMusic = map.get(currentMusic);
			prevMusic = map.get(currentMusic);

			if (prevMusic == null) {
				prevBtn.setEnabled(false);
			}

			URI uri = getURI(currentMusic.getTitle());
			player.play(new File(uri));
			prev = false;

		} else if (first) {
			URI uri = getURI(currentMusic.getTitle());
			player.play(new File(uri));
			try {
				Music music = musicDao.read(currentMusic.getNumber());
				musicDao.playCountPlus(music.getNumber(), music.getPlayCount());
			} catch (SQLException e) {
				e.printStackTrace();
			}

			first = false;
		} else {
			player.musicRun();
		}

		countDown();
		play = true;
		playBtn.setVisible(false);
		pauseBtn.setVisible(true);
	}

	// 카운트 다운
	public void countDown() {
		timer = new Timer();
		timer.scheduleAtFixedRate(new TimerTask() {
			int count = timeOut;

			@Override
			public void run() {
				timeLbl.setText("" + count);
				count--;

				if (count < 0) {
					timer.cancel();
					timeLbl.setText("시간 초과");
					pauseBtn.setVisible(false);
					playBtn.setVisible(false);
					replayBtn.setVisible(true);
					player.end();
				}
			}
		}, 0, 1000);
	}

	// 이전 버튼 이벤트
	public void prevBtnEvent() {
		player.end();
		timer.cancel();
		timeLbl.setText("" + timeOut);
		pauseBtn.setVisible(false);
		playBtn.setVisible(true);
		replayBtn.setVisible(false);
		answerTf.setText("");
		prev = true;
		first = true;
	}

	// 다음 버튼 이벤트
	public void nextBtnEvent() {
		if (play) {
			player.end();
			timer.cancel();
		}
		timeLbl.setText("" + timeOut);
		getMusic(list);
		prevBtn.setEnabled(true);
		pauseBtn.setVisible(false);
		playBtn.setVisible(true);
		replayBtn.setVisible(false);
		answerTf.setText("");
		first = true;
	}

	// URI 가져오는 메소드
	public URI getURI(String title) {
		title += ".mp3";
		URI uri = null;
		try {
			uri = MusicQuiz.class.getClassLoader().getResource(title).toURI();
		} catch (URISyntaxException e) {
			e.printStackTrace();
			System.out.println("뭘까");
		}

		return uri;
	}

	// 음악 가져오는 메소드
	public Music getMusic(List<Music> list) {
		Random random = new Random();
		int index = random.nextInt(list.size());
		Music music = list.get(index);
		quizNumberLbl.setText(String.valueOf(index + 1));

		while (music.equals(currentMusic)) {
			index = random.nextInt(list.size());
			music = list.get(index);
			quizNumberLbl.setText(String.valueOf(index + 1));
		}

		prevMusic = currentMusic;
		currentMusic = music;

//		System.out.println("이전" + prevMusic);
//		System.out.println("현재" + currentMusic);

		if (prevMusic != null) {
			map.put(currentMusic, prevMusic);
		}

		return music;
	}

}