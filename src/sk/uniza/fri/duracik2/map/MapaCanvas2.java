/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sk.uniza.fri.duracik2.map;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Polygon;
import java.awt.RenderingHints;
import java.awt.TexturePaint;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.geom.AffineTransform;
import java.awt.geom.Line2D;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Collection;
import java.util.HashSet;
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
public class MapaCanvas2 extends JComponent {
	private BufferedImage bi;
	private Collection<Okres> aOkresy;
	private Collection<Uzol> aUzly;
	private Collection<Hrana> aHrany;
	private Collection<Zvyraznenie> aZvyraznene;

	public MapaCanvas2() {
		this.bi = new BufferedImage(1, 1, BufferedImage.TYPE_INT_ARGB);
		this.addComponentListener(new ComponentAdapter() {

			@Override
			public void componentResized(ComponentEvent e) {
				if (aUzly != null) {
					vykresli();
				}
			}

		});
	}

	protected void paintComponent(Graphics paG) {
		paG.drawImage(bi, 0, 0, null);
		//this.setSize(bi.getWidth(), bi.getHeight());
	}

	private void vykresli() {
		//Vypočet hranic
		Point min = new Point(Integer.MAX_VALUE, Integer.MAX_VALUE);
		Point max = new Point(Integer.MIN_VALUE, Integer.MIN_VALUE);
		for (Okres o : aOkresy) {
			for (int i = 0; i < o.getHranica().npoints; i++) {
				min.x = Math.min(min.x, o.getHranica().xpoints[i]);
				min.y = Math.min(min.y, o.getHranica().ypoints[i]);
				max.x = Math.max(max.x, o.getHranica().xpoints[i]);
				max.y = Math.max(max.y, o.getHranica().ypoints[i]);
			}
		}
		Dimension rozmer = new Dimension(max.x - min.x, max.y - min.y);
		double scaleFactor = 1;
		if (rozmer.width < getWidth() && rozmer.height < getHeight()) {
			if (getWidth() / (double) rozmer.width < getHeight() / (double) rozmer.height) {
				scaleFactor = getWidth() / (double) rozmer.width;
			}
			else {
				scaleFactor = getHeight() / (double) rozmer.height;
			}
		}
		rozmer = new Dimension((int) (rozmer.width * scaleFactor), (int) (rozmer.height * scaleFactor));

		Dimension border = new Dimension(10, 10);
		bi = new BufferedImage(rozmer.width + border.width * 2, rozmer.height + border.height * 2, BufferedImage.TYPE_INT_ARGB);
		Graphics2D g = (Graphics2D) bi.getGraphics();
		g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		AffineTransform tx = new AffineTransform();
		tx.translate(
			-(min.x * scaleFactor) + border.width + (bi.getWidth() - (max.x - min.x) * scaleFactor) / 2,
			(max.y * scaleFactor) - border.height + (bi.getHeight() - (max.y - min.y) * scaleFactor) / 2
		);
		/*tx.scale(scaleFactor, -scaleFactor);
		 */
		/*tx.translate(
		 -(min.x)+border.width,
		 (max.y)+border.height
		 );*/
		tx.scale(1.0, -1.0);
		g.setColor(Color.WHITE);
		g.fillRect(0, 0, bi.getWidth(), bi.getHeight());
		g.setTransform(tx);
		g.setColor(Color.BLACK);
		g.setStroke(new BasicStroke(1.5f));
		for (Okres o : aOkresy) {
			int count = o.getHranica().npoints;
			int x[] = new int[count];
			int y[] = new int[count];
			for (int i = 0; i < o.getHranica().npoints; i++) {
				x[i] = (int) (o.getHranica().xpoints[i] * scaleFactor);
				y[i] = (int) (o.getHranica().ypoints[i] * scaleFactor);
			}
			g.draw(new Polygon(x, y, count));
		}
		g.setColor(Color.GRAY);
		g.setStroke(new BasicStroke(1f));
		for (Hrana h : aHrany) {
			g.draw(new Line2D.Double(h.getU1().getX() * scaleFactor, h.getU1().getY() * scaleFactor, h.getU2().getX() * scaleFactor, h.getU2().getY() * scaleFactor));
		}
		double pointWidth = Math.max(4, (2 * FileParser.MAGIC_CONSTANTA) * scaleFactor);
		double halfWidth = pointWidth / 2;
		for (Uzol u : aUzly) {
			if (!u.isKrizovatka()) {
				if (aZvyraznene.size() == 0) {
					g.setColor(Color.GREEN.darker());
				}
				else {
					g.setColor(Color.BLACK.darker());
				}
				for (Zvyraznenie z : aZvyraznene) {
					if (z.getCentrum().equals(u)) {
						g.setColor(z.getFarba());
					}
				}
				g.fill(new Rectangle2D.Double(u.getX() * scaleFactor - halfWidth, u.getY() * scaleFactor - halfWidth, pointWidth, pointWidth));
				//g.fillOval(u.getX()-2/scaleFactor, u.getY()-2*FileParser.MAGIC_CONSTANTA, 4*FileParser.MAGIC_CONSTANTA, 4*FileParser.MAGIC_CONSTANTA);
			}
			/*else if (u.isKrizovatka() && u.getOkres() == null) {
			 g.setColor(Color.YELLOW.darker());
			 g.fillOval(u.getX()-2*FileParser.MAGIC_CONSTANTA, u.getY()-2*FileParser.MAGIC_CONSTANTA, 4*FileParser.MAGIC_CONSTANTA, 4*FileParser.MAGIC_CONSTANTA);
			 }*/
		}

		//int y = (int) ((min.y*scaleFactor))-50;
		for (Zvyraznenie z : aZvyraznene) {
			g.setColor(z.getFarba());
			/*g.drawString(z.getCentrum().getNazov(), (int) (max.x*scaleFactor)-200, y);
			 y+=20;*/
			/*BufferedImage bufferedImage
				= new BufferedImage(5, 5, BufferedImage.TYPE_INT_ARGB);

			Graphics2D g2 = bufferedImage.createGraphics();
			g2.setColor(Color.GRAY);
			g2.fillRect(0, 0, 5, 5);
			g2.setColor(new Color(z.getFarba().getRed(), z.getFarba().getGreen(), z.getFarba().getBlue(), 70));
			g2.drawLine(0, 0, 5, 5); // \
			g2.drawLine(0, 5, 5, 0); // /

			// paint with the texturing brush
			Rectangle2D rect = new Rectangle2D.Double(0, 0, 5, 5);
			*/
			
			if (z.isPolygon()) {
				Polygon p = new Polygon();
				Polygon origo = z.getPolygon();
				for (int i = 0; i < origo.npoints; i++) {
					p.addPoint((int) (origo.xpoints[i] * scaleFactor), (int) (origo.ypoints[i] * scaleFactor));
				}
				g.draw(p);

				//g.setPaint(new TexturePaint(bufferedImage, rect));
				g.setColor(new Color(z.getFarba().getRed(), z.getFarba().getGreen(), z.getFarba().getBlue(), 70));
				g.fill(p);
			}
		}
		
		g.setColor(Color.BLACK);
		g.setFont(new java.awt.Font("Arial", Font.BOLD, 11));
		int ks = 0;
		for (Uzol u : aUzly) {
			if (!u.isKrizovatka()) {
				FontMetrics fontMetrics = g.getFontMetrics();
				BufferedImage bx = new BufferedImage(fontMetrics.stringWidth(u.getNazov()), fontMetrics.getHeight(), BufferedImage.TYPE_INT_ARGB);
				Graphics2D gx = (Graphics2D) bx.getGraphics();
				AffineTransform txx = new AffineTransform();
				txx.translate(0,0);
				txx.scale(1.0, -1.0);
				gx.setTransform(txx);
				
				gx.setColor(Color.black);
				for (Zvyraznenie zv : aZvyraznene) {
					if (zv.getCentrum() == u) {
						gx.setColor(zv.getFarba());
						break;
					}
				}
				gx.setFont(g.getFont());
				gx.drawString(u.getNazov(), 0, 0);
				
				g.drawImage(bx, ((int)(u.getX()*scaleFactor))-(fontMetrics.stringWidth(u.getNazov())/2), (int)(u.getY()*scaleFactor)+fontMetrics.getHeight()/2, null);
				
				//g.drawString(u.getNazov(), (float)(u.getX()*scaleFactor), (float)(u.getY()*scaleFactor));
				//g.fill(new Rectangle2D.Double(u.getX() * scaleFactor - halfWidth, u.getY() * scaleFactor - halfWidth, pointWidth, pointWidth));
				if (ks++>6) {
					break;
				}
			}
		}

		this.repaint();
	}

	public void vykresli(Collection<Okres> okresy, Collection<Uzol> uzly, Collection<Hrana> hrany) {
		vykresli(okresy, uzly, hrany, new HashSet<Zvyraznenie>(0));
	}

	public void vykresli(Collection<Okres> okresy, Collection<Uzol> uzly, Collection<Hrana> hrany, Collection<Zvyraznenie> zvyrazneneMesta) {
		aOkresy = okresy;
		aUzly = uzly;
		aHrany = hrany;
		aZvyraznene = zvyrazneneMesta;
		vykresli();
	}

	public void saveToImage(File path) {
		try {
			ImageIO.write(bi, "png", path);
		}
		catch (IOException ex) {
			JOptionPane.showMessageDialog(this, "Súbor sa nepodarilo uložiť\n" + ex.getMessage());
		}
	}

}
