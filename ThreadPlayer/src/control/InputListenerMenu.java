package control;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

import model.Music;
import model.MusicDAO;
import view.AddFrame;
import view.MenuFrame;

public class InputListenerMenu implements ActionListener {

	private static MenuFrame menuFrame;
	private static AddFrame addFrame;
	private MusicDAO musicDAO = new MusicDAO();
	private MyPlayer musica;
	private int currentIndexMusic;
	private static InputListenerMenu sInstance;
	private boolean randomize = false;
	private boolean btnClicked = false;
	private ArrayList<Music> randMusic = null;
	private Music music;
	private static int maxTime = 10000;
	
	public static InputListenerMenu getInstance(JFrame tela) {
		if (sInstance == null) {
			sInstance = new InputListenerMenu();
		}

		if (tela instanceof MenuFrame) {
			menuFrame = (MenuFrame) tela;
		}
		else if (tela instanceof AddFrame) {
			addFrame = (AddFrame) tela;
		}

		return sInstance;
	}

	public void actionPerformed(ActionEvent event) {

		if (event.getActionCommand() == "SEARCH") {

			String pathMusic = null;
			String nameMusic = null;

			JFileChooser abrir = new JFileChooser();
			abrir.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
			int retorno = abrir.showOpenDialog(addFrame);
			if (retorno == JFileChooser.APPROVE_OPTION) {
				pathMusic = abrir.getSelectedFile().getAbsolutePath();
				nameMusic = abrir.getSelectedFile().getName();
			}
			addFrame.setMusic(pathMusic, nameMusic);
			
		}
		else if (event.getActionCommand() == "PLAY") {

			btnClicked = true;
			
			ArrayList<Music> musics = musicDAO.listarMusic();

			int selectedIndex = menuFrame.getList().getSelectedIndex();

			currentIndexMusic = selectedIndex;
			music = musics.get(selectedIndex);

			File mp3File = new File(music.getPath());

			if (musica == null) {
				musica = new MyPlayer();
			}
			else {
				musica.stopPlayer();
				for (int i = 0; i < maxTime; i++);
				musica = new MyPlayer();
			}
			musica.tocar(mp3File);
			musica.start();
			
			menuFrame.getLblNextmusic().setText(music.getName());
			
			menuFrame.getLblCurrentmusic().setText(music.getName());
			
			btnClicked = false;
			
		}
		else if (event.getActionCommand() == "NEXT") {

			btnClicked = true;
			
			ArrayList<Music> musics = musicDAO.listarMusic();
			Music nextMusic;

			currentIndexMusic++;
			currentIndexMusic = currentIndexMusic%musics.size();
			
			if (randomize) {
				if (randMusic == null) {
					long seed = System.nanoTime();
					randMusic = musics;
					Collections.shuffle(randMusic, new Random(seed));
				}
				music = randMusic.get(currentIndexMusic);
				nextMusic = (randMusic.get((currentIndexMusic+1)%randMusic.size()));
			}
			else {
				if (randMusic != null) {
					music = randMusic.get(currentIndexMusic);
					nextMusic = (musics.get((currentIndexMusic+1)%musics.size()));
					currentIndexMusic = musics.indexOf(randMusic.get(currentIndexMusic))%musics.size();
					randMusic = null;
				}
				else{
					music = musics.get(currentIndexMusic);
					nextMusic = (musics.get((currentIndexMusic+1)%musics.size()));
				}
			}

			menuFrame.getList().setSelectedIndex(musics.indexOf(music));
			
			File mp3File = new File(music.getPath());

			if (musica == null) {
				musica = new MyPlayer();
			}
			else {
				musica.stopPlayer();
				for (int i = 0; i < maxTime; i++);
				musica = new MyPlayer();
			}
			musica.tocar(mp3File);
			musica.start();
			
			String parts[] = nextMusic.getName().split("-");
			menuFrame.getLblNextmusic().setText(parts[1]);
			
			menuFrame.getLblCurrentmusic().setText(music.getName());
			
			btnClicked = false;

		}
		else if (event.getActionCommand() == "PREV") {

			btnClicked = true;
			
			currentIndexMusic--;

			ArrayList<Music> musics = musicDAO.listarMusic();

			if (currentIndexMusic < 0)
				currentIndexMusic = musics.size() - 1;

			music = musics.get(currentIndexMusic);
			
			menuFrame.getList().setSelectedIndex(currentIndexMusic);

			File mp3File = new File(music.getPath());

			if (musica == null) {
				musica = new MyPlayer();
			}
			else {
				musica.stopPlayer();
				for (int i = 0; i < maxTime; i++);
				musica = new MyPlayer();
			}
			musica.tocar(mp3File);
			musica.start();
			
			String parts[] = (musics.get((currentIndexMusic+1)%musics.size())).getName().split("-");
			menuFrame.getLblNextmusic().setText(parts[1]);
			
			menuFrame.getLblCurrentmusic().setText(music.getName());

			btnClicked = false;
		}
		else if (event.getActionCommand() == "ADD") {

			menuFrame.setVisible(false);
			new AddFrame().setVisible(true);

		}
		else if (event.getActionCommand() == "ADD1") {

			listaDiretorios(new File(addFrame.getPath()), 0);
			
			JOptionPane.showMessageDialog(null, "Adicionado com sucesso!");

		}
		else if (event.getActionCommand() == "RANDOM") {
			randomize = !randomize;
			if (randomize) {
				menuFrame.getBtnRandom().setIcon(new ImageIcon("src/Imgs/randomB.png"));
			}
			else {
				menuFrame.getBtnRandom().setIcon(new ImageIcon("src/Imgs/randomW.png"));
			}
		}
		else if (event.getActionCommand() == "STOP") {
			if(musica != null){
				btnClicked = true;
				musica.stopPlayer();
				for (int i = 0; i < maxTime; i++);
				btnClicked = false;
			}
		}
		else if (event.getActionCommand() == "EXIT") {
			addFrame.dispose();
			new MenuFrame().setVisible(true);
		}

	}

	public void nextMusic() {

		if(!btnClicked){
			ArrayList<Music> musics = musicDAO.listarMusic();
			Music nextMusic;
	
			currentIndexMusic++;
			currentIndexMusic = currentIndexMusic%musics.size();
			
			if (randomize) {
				if (randMusic == null) {
					long seed = System.nanoTime();
					randMusic = musics;
					Collections.shuffle(randMusic, new Random(seed));
				}
				music = randMusic.get(currentIndexMusic);
				nextMusic = (randMusic.get((currentIndexMusic+1)%randMusic.size()));
			}
			else {
				if (randMusic != null) {
					music = randMusic.get(currentIndexMusic);
					nextMusic = (musics.get((currentIndexMusic+1)%musics.size()));
					currentIndexMusic = musics.indexOf(randMusic.get(currentIndexMusic))%musics.size();
					randMusic = null;
				}
				else{
					music = musics.get(currentIndexMusic);
					nextMusic = (musics.get((currentIndexMusic+1)%musics.size()));
				}
			}
	
			menuFrame.getList().setSelectedIndex(musics.indexOf(music));
			
			File mp3File = new File(music.getPath());
	
			if (musica == null) {
				musica = new MyPlayer();
			}
			else {
				musica.stopPlayer();
				for (int i = 0; i < maxTime; i++);
				musica = new MyPlayer();
			}
			musica.tocar(mp3File);
			musica.start();
			
			String parts[] = nextMusic.getName().split("-");
			menuFrame.getLblNextmusic().setText(parts[1]);
			
			menuFrame.getLblCurrentmusic().setText(music.getName());
		}
		
	}

	public boolean getRandomize() {
		return randomize;
	}
	
	public boolean getBtnClicked(){
		return btnClicked;
	}

	public String getCurrentMusicName() {
		if(music != null){
			return music.getName();
		}
		else{
			return "";
		}
	}
	
	private void listaDiretorios(File path, int jumps) {
	    File[] files = path.listFiles();
	    if(path.isDirectory()){
		    for (int i = 0; i < files.length; i++) {
		    	File arq = files[i];
		    	if (arq.isFile()) {
		    		String pathMusic = null;
					String nameMusic = null;
	
					pathMusic = arq.getAbsolutePath();
					nameMusic = arq.getName();
					
					music = new Music(nameMusic, pathMusic);
					
					musicDAO.addMusic(music);
					
		    	}
		    	else if(jumps <= 2){
		    		listaDiretorios(arq, jumps+1);
		    	}
		    
		    }
	    }
	    else {
	    	String pathMusic = null;
			String nameMusic = null;

			pathMusic = path.getAbsolutePath();
			nameMusic = path.getName();
			
			music = new Music(nameMusic, pathMusic);
			
			musicDAO.addMusic(music);
	    }
	}

}
