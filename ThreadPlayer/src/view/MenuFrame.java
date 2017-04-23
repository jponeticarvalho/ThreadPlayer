package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.util.ArrayList;

import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import control.InputListenerMenu;
import model.Music;
import model.MusicDAO;
import javax.swing.JLabel;

public class MenuFrame extends JFrame {
	
	private static final long serialVersionUID = 1L;
	private JPanel panel;
	private InputListenerMenu listener;
	private JPanel contentPane;
	private String pathBackground = "/background1.jpg";
	private JButton btnPlay;
	private JList<Object> list;
	private JScrollPane scrollPane;
	private MusicDAO musicDAO = new MusicDAO();
	private JButton btnAdd;
	private JButton btnPrevious;
	private JButton btnNext;
	private JButton btnRandom;
	private JButton btnStop;
	private JLabel lblThreadPlayer;
	private JLabel lblCurrentmusic;
	private JLabel lblTocandoAgora;
	private JLabel lblProximaMusica;
	private JLabel lblNextmusic;

	public MenuFrame() {
		setResizable(false);
		setTitle("Thread Player");
		listener = InputListenerMenu.getInstance(this);
		initialize();
		listenerInitialize();
	}
	
	private void initialize() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		contentPane = new JBackground(pathBackground);
		setBounds(100,100,790,480);
		setLocationRelativeTo(null);
		contentPane.setLayout(new BorderLayout());
		setContentPane(contentPane);
		
		contentPane.add(getPanel(), BorderLayout.CENTER);
	}
	
	private void listenerInitialize(){
		getBtnPlay().addActionListener(listener);
		getBtnAdd().addActionListener(listener);
		getBtnNext().addActionListener(listener);
		getBtnPrevious().addActionListener(listener);
		getBtnRandom().addActionListener(listener);
		getBtnStop().addActionListener(listener);
	}

	private JPanel getPanel() {
		if (panel == null) {
			panel = new JPanel();
			panel.setLayout(null);
			panel.add(getBtnPlay());
			panel.add(getScrollPane());
			panel.setOpaque(false);
			panel.add(getBtnAdd());
			panel.add(getBtnPrevious());
			panel.add(getBtnNext());
			panel.add(getBtnRandom());
			panel.add(getBtnStop());
			panel.add(getLblThreadPlayer());
			panel.add(getLblCurrentmusic());
			panel.add(getLblTocandoAgora());
			panel.add(getLblProximaMusica());
			panel.add(getLblNextmusic());
		}
		return panel;
	}
	private JButton getBtnPlay() {
		if (btnPlay == null) {
			btnPlay = new JButton("");
			btnPlay.setBorder(null);
			btnPlay.setContentAreaFilled(false);
			btnPlay.setIcon(new ImageIcon("src/Imgs/play-button.png"));
			btnPlay.setActionCommand("PLAY");
			btnPlay.setBounds(161, 374, 64, 64);
		}
		return btnPlay;
	}
	public JList<Object> getList() {
		if(list == null){
			list = new JList<Object>();
			list.setModel(getMusics());
		}
		return list;
	}
	
	private DefaultListModel<Object> getMusics(){
		
		DefaultListModel<Object> listModel = new DefaultListModel<>();
		
		ArrayList<Music> musics = null;
		
		musics = musicDAO.listarMusic();
		
		for (Music music : musics) {
			listModel.addElement(music.getName());
		}
		
		return listModel;
		
	}
	private JScrollPane getScrollPane() {
		if (scrollPane == null) {
			scrollPane = new JScrollPane();
			scrollPane.setBounds(32, 179, 726, 184);
			scrollPane.setViewportView(getList());
		}
		return scrollPane;
	}
	private JButton getBtnAdd() {
		if (btnAdd == null) {
			btnAdd = new JButton("");
			btnAdd.setActionCommand("ADD");
			btnAdd.setBorder(null);
			btnAdd.setContentAreaFilled(false);
			btnAdd.setIcon(new ImageIcon("src/Imgs/plus-button.png"));
			btnAdd.setBounds(694, 374, 64, 64);
		}
		return btnAdd;
	}
	private JButton getBtnPrevious() {
		if (btnPrevious == null) {
			btnPrevious = new JButton("");
			btnPrevious.setActionCommand("PREV");
			btnPrevious.setBorder(null);
			btnPrevious.setContentAreaFilled(false);
			btnPrevious.setIcon(new ImageIcon("src/Imgs/back.png"));
			btnPrevious.setBounds(42, 374, 64, 64);
		}
		return btnPrevious;
	}
	private JButton getBtnNext() {
		if (btnNext == null) {
			btnNext = new JButton("");
			btnNext.setActionCommand("NEXT");
			btnNext.setBorder(null);
			btnNext.setContentAreaFilled(false);
			btnNext.setIcon(new ImageIcon("src/Imgs/next.png"));
			btnNext.setBounds(383, 374, 64, 64);
		}
		return btnNext;
	}
	public JButton getBtnRandom() {
		if (btnRandom == null) {
			btnRandom = new JButton("");
			btnRandom.setBorder(null);
			btnRandom.setContentAreaFilled(false);
			if(listener.getRandomize()){
				btnRandom.setIcon(new ImageIcon("src/Imgs/randomB.png"));
			}
			else{
				btnRandom.setIcon(new ImageIcon("src/Imgs/randomW.png"));
			}
			btnRandom.setActionCommand("RANDOM");
			btnRandom.setBounds(540, 374, 64, 64);
		}
		return btnRandom;
	}
	private JButton getBtnStop() {
		if (btnStop == null) {
			btnStop = new JButton("");
			btnStop.setBorder(null);
			btnStop.setContentAreaFilled(false);
			btnStop.setIcon(new ImageIcon("src/Imgs/stop.png"));
			btnStop.setActionCommand("STOP");
			btnStop.setBounds(262, 374, 64, 64);
		}
		return btnStop;
	}
	private JLabel getLblThreadPlayer() {
		if (lblThreadPlayer == null) {
			lblThreadPlayer = new JLabel("Thread Player");
			lblThreadPlayer.setBounds(51, 11, 330, 84);
			lblThreadPlayer.setFont(new Font("Game Music Love", Font.PLAIN, 38));
			lblThreadPlayer.setForeground(Color.lightGray);
		}
		return lblThreadPlayer;
	}
	public JLabel getLblCurrentmusic() {
		if (lblCurrentmusic == null) {
			lblCurrentmusic = new JLabel(listener.getCurrentMusicName());
			lblCurrentmusic.setBounds(49, 113, 499, 55);
			lblCurrentmusic.setFont(new Font("Game Music Love", Font.PLAIN, 16));
			lblCurrentmusic.setForeground(Color.WHITE);
		}
		return lblCurrentmusic;
	}
	private JLabel getLblTocandoAgora() {
		if (lblTocandoAgora == null) {
			lblTocandoAgora = new JLabel("Tocando Agora");
			lblTocandoAgora.setBounds(42, 100, 129, 14);
			lblTocandoAgora.setFont(new Font("Game Music Love", Font.PLAIN, 14));
			lblTocandoAgora.setForeground(Color.white);
		}
		return lblTocandoAgora;
	}
	private JLabel getLblProximaMusica() {
		if (lblProximaMusica == null) {
			lblProximaMusica = new JLabel("Proxima Musica");
			lblProximaMusica.setBounds(567, 26, 188, 40);
			lblProximaMusica.setFont(new Font("Game Music Love", Font.PLAIN, 14));
			lblProximaMusica.setForeground(Color.white);
		}
		return lblProximaMusica;
	}
	public JLabel getLblNextmusic() {
		if (lblNextmusic == null) {
			lblNextmusic = new JLabel("");
			lblNextmusic.setBounds(567, 56, 191, 39);
			lblNextmusic.setFont(new Font("Game Music Love", Font.PLAIN, 14));
			lblNextmusic.setForeground(Color.white);
		}
		return lblNextmusic;
	}
}
