/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sk.uniza.fri.duracik2.grafy;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import sk.uniza.fri.duracik2.entity.Hrana;
import sk.uniza.fri.duracik2.entity.Uzol;

/**
 *
 * @author Unlink
 */
public class GrafBuilder {
	private List<Uzol> aUzly;
	private List<Hrana> aHrany;

	public GrafBuilder() {
		aUzly = new LinkedList<>();
		aHrany = new LinkedList<>();
	}
	
	public GrafBuilder pridajUzol(Uzol paUzol) {
		aUzly.add(paUzol);
		return this;
	}
	
	public GrafBuilder pridajHranu(Hrana paHrana) {
		aHrany.add(paHrana);
		return this;
	}
	
	public Graf build() {
		Uzol[] uzly = new Uzol[aUzly.size()];
		HashMap<Uzol, Integer> map = new HashMap<>();
		double[][] matica = new double[uzly.length][uzly.length];
		inicializujMaticu(matica);
		for (int i = 0; i < aUzly.size(); i++) {
			uzly[i] = aUzly.get(i);
			map.put(aUzly.get(i), i);
		}
		for (Hrana h : aHrany) {
			int u1 = map.get(h.getU1());
			int u2 = map.get(h.getU2());
			matica[u1][u2] = h.getDlzka();
			matica[u2][u1] = h.getDlzka();
		}
		return new Graf(uzly, map, matica, aHrany);
	}
	
	private void inicializujMaticu(double[][] matica) {
		for (int i = 0; i < matica.length; i++) {
			for (int j = 0; j < matica[0].length; j++) {
				matica[i][j] = Double.MAX_VALUE;
			}
		}
	}
	
}
