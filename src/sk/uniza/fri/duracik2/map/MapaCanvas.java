/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sk.uniza.fri.duracik2.map;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseWheelEvent;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Collection;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.JComponent;
import javax.swing.JOptionPane;
import sk.uniza.fri.duracik2.FileParser;
import sk.uniza.fri.duracik2.entity.Hrana;
import sk.uniza.fri.duracik2.entity.Okres;
import sk.uniza.fri.duracik2.entity.Uzol;

/**
 *
 * @author Unlink
 */
public class MapaCanvas extends JComponent {
	private BufferedImage bi;
	private double scaleFactor = 1.0;

	public MapaCanvas() {
		bi = new BufferedImage(1350*FileParser.MAGIC_CONSTANTA, 900*FileParser.MAGIC_CONSTANTA, BufferedImage.TYPE_INT_ARGB);
		bi.getGraphics().setColor(Color.WHITE);
		bi.getGraphics().fillRect(0, 0, bi.getWidth(), bi.getHeight());
		this.setPreferredSize(new Dimension(bi.getWidth(), bi.getHeight()));
		
		this.addMouseWheelListener(new MouseAdapter() {

			@Override
			public void mouseWheelMoved(MouseWheelEvent paE) {
				if (paE.getWheelRotation() > 0){
					scaleFactor *= 1.1;
				}
				else {
					scaleFactor *= 0.9;
				}
				repaint();
			}
			
		});
	}

	@Override
	protected void paintComponent(Graphics paG) {
		/*AffineTransform t = new AffineTransform();
		t.scale(scaleFactor, scaleFactor);
		((Graphics2D) paG).setTransform(t);*/
		paG.drawImage(bi, 0, 0, null);
		/*this.setSize((int)(1350*FileParser.MAGIC_CONSTANTA*scaleFactor), (int)(900*FileParser.MAGIC_CONSTANTA*scaleFactor));
		scrooler.validate();*/
	}
	
	public void vykresli(Collection<Okres> okresy, Collection<Uzol> uzly, Collection<Hrana> hrany) {
		Graphics2D g = (Graphics2D) bi.getGraphics();
		AffineTransform tx = new AffineTransform();
		tx.translate(0,bi.getHeight());
		tx.scale( 1.0, -1.0 );
		g.setTransform(tx);
		g.setColor(Color.BLACK);
		for (Okres o : okresy) {
			g.draw(o.getHranica());
		}
		for (Uzol u : uzly) {
			if (!u.isKrizovatka()) {
				if (u.getOkres() != null) g.setColor(Color.GREEN.darker());
				else g.setColor(Color.RED);
				g.fillOval(u.getX()-2*FileParser.MAGIC_CONSTANTA, u.getY()-2*FileParser.MAGIC_CONSTANTA, 4*FileParser.MAGIC_CONSTANTA, 4*FileParser.MAGIC_CONSTANTA);
			}
			else if (u.isKrizovatka() && u.getOkres() == null) {
				g.setColor(Color.YELLOW.darker());
				g.fillOval(u.getX()-2*FileParser.MAGIC_CONSTANTA, u.getY()-2*FileParser.MAGIC_CONSTANTA, 4*FileParser.MAGIC_CONSTANTA, 4*FileParser.MAGIC_CONSTANTA);
			}
		}
		
		g.setColor(Color.GRAY);
		for (Hrana h : hrany) {
			g.drawLine(h.getU1().getX(), h.getU1().getY(), h.getU2().getX(), h.getU2().getY());
		}
		
		this.repaint();
	}
	
	public void saveToImage(File path) {
		try {
			ImageIO.write(bi, "png", path);
		} catch (IOException ex) {
			JOptionPane.showMessageDialog(this, "Súbor sa nepodarilo uložiť\n"+ex.getMessage());
		}
	}
	
}
