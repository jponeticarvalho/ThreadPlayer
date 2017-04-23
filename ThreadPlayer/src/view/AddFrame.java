package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import control.InputListenerMenu;

public class AddFrame extends JFrame{
	
	private static final long serialVersionUID = -2674537767031686094L;
	
	private String path = "/background1.jpg";
	private String pathMusic = null;
	private String nameMusic = null;
	private InputListenerMenu listener;
	private JPanel contentPane;
	private JPanel panel;
	private JTextField textFieldName;
	private JTextField textFieldSource;
	private JButton btnSearch;
	private JButton btnAdicionar;
	private JButton btnExit;
	private JLabel lblThreadPlayer;
	private JLabel lblAdicionarMusica;
	
	public void setMusic(String pathMusic,String nameMusic) {
		this.pathMusic = pathMusic;
		this.nameMusic = nameMusic;
		getTextFieldSource().setText(this.pathMusic);
		getTextFieldName().setText(this.nameMusic);
	}
	
	public AddFrame() {
		initialize();
		listener = InputListenerMenu.getInstance(this);
		listenerInitialize();
	}
	
	private void initialize() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setResizable(false);
		contentPane = new JBackground(path);
		setBounds(100,100,790,480);
		setLocationRelativeTo(null);
		contentPane.setLayout(new BorderLayout());
		setContentPane(contentPane);
		
		contentPane.add(getPanel(), BorderLayout.CENTER);
	}
	
	private void listenerInitialize(){
		getBtnAdicionar().addActionListener(listener);
		getBtnExit().addActionListener(listener);
		getBtnSearch().addActionListener(listener);
	}

	private JPanel getPanel() {
		if (panel == null) {
			panel = new JPanel();
			panel.setOpaque(false);
			panel.setLayout(null);
			panel.add(getTextFieldName());
			panel.add(getTextFieldSource());
			panel.add(getBtnSearch());
			panel.add(getBtnAdicionar());
			panel.add(getBtnExit());
			panel.add(getLblThreadPlayer());
			panel.add(getLblAdicionarMusica());
		}
		return panel;
	}
	private JTextField getTextFieldName() {
		if (textFieldName == null) {
			textFieldName = new JTextField();
			textFieldName.setText("Nome da Musica");
			textFieldName.setBounds(21, 203, 645, 40);
			textFieldName.setColumns(10);
		}
		return textFieldName;
	}
	private JTextField getTextFieldSource() {
		if (textFieldSource == null) {
			textFieldSource = new JTextField();
			textFieldSource.setText("Caminho da Musica");
			textFieldSource.setBounds(21, 252, 645, 40);
			textFieldSource.setColumns(10);
		}
		return textFieldSource;
	}
	private JButton getBtnSearch() {
		if (btnSearch == null) {
			btnSearch = new JButton("");
			btnSearch.setActionCommand("SEARCH");
			btnSearch.setBorder(null);
			btnSearch.setContentAreaFilled(false);
			btnSearch.setBounds(676, 203, 64, 64);
			btnSearch.setIcon(new ImageIcon("src/Imgs/musica-searcher.png"));
		}
		return btnSearch;
	}
	private JButton getBtnAdicionar() {
		if (btnAdicionar == null) {
			btnAdicionar = new JButton("");
			btnAdicionar.setActionCommand("ADD1");
			btnAdicionar.setBorder(null);
			btnAdicionar.setContentAreaFilled(false);
			btnAdicionar.setBounds(346, 361, 64, 64);
			btnAdicionar.setIcon(new ImageIcon("src/Imgs/success.png"));
		}
		return btnAdicionar;
	}
	private JButton getBtnExit() {
		if (btnExit == null) {
			btnExit = new JButton("");
			btnExit.setActionCommand("EXIT");
			btnExit.setBorder(null);
			btnExit.setContentAreaFilled(false);
			btnExit.setBounds(10, 11, 64, 64);
			btnExit.setIcon(new ImageIcon("src/Imgs/reply.png"));
		}
		return btnExit;
	}
	
	public String getPath(){
		String path = getTextFieldSource().getText();
		return path;
	}
	
	private JLabel getLblThreadPlayer() {
		if (lblThreadPlayer == null) {
			lblThreadPlayer = new JLabel("Thread Player");
			lblThreadPlayer.setBounds(117, 11, 579, 49);
			lblThreadPlayer.setFont(new Font("Game Music Love", Font.PLAIN, 38));
			lblThreadPlayer.setForeground(Color.lightGray);
		}
		return lblThreadPlayer;
	}
	private JLabel getLblAdicionarMusica() {
		if (lblAdicionarMusica == null) {
			lblAdicionarMusica = new JLabel("Adicionar Musica");
			lblAdicionarMusica.setBounds(127, 71, 303, 40);
			lblAdicionarMusica.setFont(new Font("Game Music Love", Font.PLAIN, 18));
			lblAdicionarMusica.setForeground(Color.white);
		}
		return lblAdicionarMusica;
	}
}
