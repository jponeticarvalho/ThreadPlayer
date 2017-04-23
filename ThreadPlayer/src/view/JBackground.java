package view;

import java.awt.Graphics;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

public class JBackground extends JPanel{

	private static final long serialVersionUID = 1L;
	private Image img;
	
	public JBackground(String path) { //caminho da sua imagem.
		img = new ImageIcon(this.getClass().getResource(path)).getImage();
	}

	@Override
	protected void paintComponent(Graphics g){
		super.paintComponent(g);
		g.drawImage(img, 0, 0, getWidth(), getHeight(),this);
	}
	
}
