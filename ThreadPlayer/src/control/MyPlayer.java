package control;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;

import javazoom.jl.player.Player;

public class MyPlayer extends Thread {

	private File mp3;
	private Player player;

	public void tocar(File mp3) {
		this.mp3 = mp3;
	}

	public void run() {
		try {
			FileInputStream fis = new FileInputStream(mp3);
			BufferedInputStream bis = new BufferedInputStream(fis);
			this.player = new Player(bis);

			this.player.play();
			
			if(InputListenerMenu.getInstance(null).getBtnClicked() == false){
				InputListenerMenu.getInstance(null).nextMusic();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void stopPlayer() {
		player.close();
	}

	public int currentPosition() {
		return player.getPosition();
	}

	public int totalPosition() {
		return 0;
	}

}
