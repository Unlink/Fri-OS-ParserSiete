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
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.util.Collection;
import javax.swing.JComponent;
import sk.uniza.fri.duracik2.entity.Okres;
import sk.uniza.fri.duracik2.entity.Uzol;

/**
 *
 * @author Unlink
 */
public class MapaCanvas extends JComponent {
	private BufferedImage bi;

	public MapaCanvas() {
		bi = new BufferedImage(1350, 900, BufferedImage.TYPE_INT_ARGB);
		bi.getGraphics().setColor(Color.WHITE);
		bi.getGraphics().fillRect(0, 0, bi.getWidth(), bi.getHeight());
		this.setPreferredSize(new Dimension(bi.getWidth(), bi.getHeight()));
	}

	@Override
	protected void paintComponent(Graphics paG) {
		paG.drawImage(bi, 0, 0, null);
	}
	
	public void vykresliOkresy(Collection<Okres> okresy) {
		Graphics2D g = (Graphics2D) bi.getGraphics();
		AffineTransform tx = new AffineTransform();
		tx.translate(0,bi.getHeight());
		tx.scale( 1.0, -1.0 );
		g.setTransform(tx);
		g.setColor(Color.BLACK);
		for (Okres o : okresy) {
			g.draw(o.getHranica());
		}
		this.repaint();
	}
	
}
