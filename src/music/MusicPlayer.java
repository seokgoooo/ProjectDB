package music;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

import javazoom.jl.decoder.JavaLayerException;
import javazoom.jl.player.Player;

public class MusicPlayer {
	private Player player;

	public void play(File file) {
		try {
			FileInputStream fis = new FileInputStream(file);
			player = new Player(fis);

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (JavaLayerException e) {
			e.printStackTrace();
		}
	
		MusicPlayerThread playThread = new MusicPlayerThread(player);
		
		playThread.start();
		
	}

	public void stop() {
		if (player != null) {
			System.out.println("음악 종료");
			player.close();
		}
	}
}
