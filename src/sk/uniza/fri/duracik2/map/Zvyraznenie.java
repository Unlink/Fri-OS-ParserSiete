/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sk.uniza.fri.duracik2.map;

import java.awt.Color;
import java.awt.Polygon;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import sk.uniza.fri.duracik2.entity.Uzol;

/**
 *
 * @author Unlink
 */
public class Zvyraznenie implements Iterable<Uzol> {
	private Uzol aCentrum;
	private List<Uzol> aPriradenia;
	private Color aFarba;
	private boolean aPolygon;

	public Zvyraznenie(Uzol aCentrum, Color aFarba, boolean aPolygon) {
		this.aCentrum = aCentrum;
		this.aFarba = aFarba;
		this.aPolygon = aPolygon;
		this.aPriradenia = new LinkedList<>();
	}
	
	public Zvyraznenie(Uzol aCentrum, Color aFarba) {
		this(aCentrum, aFarba, true);
	}

	public Color getFarba() {
		return aFarba;
	}
	
	public void pridajUzol(Uzol paUzol) {
		aPriradenia.add(paUzol);
	}	

	@Override
	public Iterator<Uzol> iterator() {
		return aPriradenia.iterator();
	}

	public Uzol getCentrum() {
		return aCentrum;
	}

	public boolean isPolygon() {
		return aPolygon;
	}
	
	public Polygon getPolygon() {
		if (isPolygon()) {
			Polygon poly = new Polygon();
			ArrayList<Uzol> x = new ArrayList<>(aPriradenia);
			Collections.sort(x, new Comparator<Uzol>() {
				@Override
				public int compare(Uzol o1, Uzol o2) {
					double angle1 = ((o1.getX()-aCentrum.getX()) == 0) ? 0 : Math.atan2((o1.getY()-aCentrum.getY()),(o1.getX()-aCentrum.getX()));
					double angle2 = ((o2.getX()-aCentrum.getX()) == 0) ? 0 : Math.atan2((o2.getY()-aCentrum.getY()),(o2.getX()-aCentrum.getX()));
					return (angle1 == angle2) ? 0 : (angle1 < angle2) ? 1 : -1;
				}
			});
			for (Uzol x1 : x) {
				if (!x1.equals(aCentrum))
					poly.addPoint(x1.getX(), x1.getY());
			}
			poly.addPoint(poly.xpoints[0], poly.ypoints[0]);
			return poly;
		}
		return null;
	}
	
}
