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
import java.awt.Point;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Collection;
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
public class MapaCanvas2 extends MapaCanvas {
	private BufferedImage bi;

	public MapaCanvas2() {
		this.bi = new BufferedImage(1, 1, BufferedImage.TYPE_INT_ARGB);
	}
	
	@Override
	protected void paintComponent(Graphics paG) {
		paG.drawImage(bi, 0, 0, null);
		//this.setSize(bi.getWidth(), bi.getHeight());
	}
	
	public void vykresli(Collection<Okres> okresy, Collection<Uzol> uzly, Collection<Hrana> hrany) {
		//Vypočet hranic
		Point min = new Point(Integer.MAX_VALUE, Integer.MAX_VALUE);
		Point max = new Point(Integer.MIN_VALUE, Integer.MIN_VALUE);
		for (Okres o : okresy) {
			for (int i = 0; i < o.getHranica().npoints; i++) {
				min.x = Math.min(min.x, o.getHranica().xpoints[i]);
				min.y = Math.min(min.y, o.getHranica().ypoints[i]);
				max.x = Math.max(max.x, o.getHranica().xpoints[i]);
				max.y = Math.max(max.y, o.getHranica().ypoints[i]);
			}
		}
		Dimension rozmer = new Dimension(max.x - min.x, max.y - min.y);
		/*double scaleFactor = 1;
		if (rozmer.width < getWidth() && rozmer.height < getHeight()) {
			if (getWidth() / (double)rozmer.width < getHeight() / (double)rozmer.height) {
				scaleFactor = getWidth() / (double)rozmer.width;
			}
			else {
				scaleFactor = getHeight() / (double)rozmer.height;
			}
		}
		rozmer = new Dimension((int)(rozmer.width*scaleFactor), (int)(rozmer.height*scaleFactor));*/
		
		Dimension border = new Dimension(10, 10);
		bi = new BufferedImage(rozmer.width+border.width*2, rozmer.height+border.height*2, BufferedImage.TYPE_INT_ARGB);
		Graphics2D g = (Graphics2D) bi.getGraphics();
		AffineTransform tx = new AffineTransform();
		tx.translate(-min.x+border.width,min.y-border.height+bi.getHeight());
		tx.scale(1, -1.0);
		g.setColor(Color.WHITE);
		g.fillRect(0, 0, bi.getWidth(), bi.getHeight());
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
