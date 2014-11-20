/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sk.uniza.fri.duracik2.tsp;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 *
 * @author Unlink
 */
public class NajblizsiUzol {
	
	private double[][] matica;
	private Trasa trasa;
	private Queue<Integer> nezaradene;

	public NajblizsiUzol(double[][] matica) {
		this.nezaradene = new LinkedList<>();
		for (int i=0; i<matica.length; i++) this.nezaradene.add(i);
		this.matica = matica;
		this.trasa = new Trasa(0, matica.length);
		this.nezaradene.remove(0);
		int i2 = dajNajblizsiNezaradeny(0);
		this.trasa.pridaj(i2);
		this.nezaradene.remove(i2);
		int i3 = dajNajblizsiNezaradeny(i2);
		this.trasa.pridaj(i3);
		this.nezaradene.remove(i3);
	}
	
	public void run() {
		while (trasa.dlzka() < matica.length) {
			int uzol = this.nezaradene.remove();
			Uzol aktual = trasa.zaciatok();
			double min = Double.MAX_VALUE;
			int minNode = -1;
			do {
				double zhorsenie = zaradMedzi(aktual.getVal(), aktual.getNext().getVal(), uzol);
				if (zhorsenie < min) {
					min = zhorsenie;
					minNode = aktual.getVal();
				}
				aktual = aktual.getNext();
			} while (aktual != trasa.zaciatok());
			trasa.pridaj(minNode, uzol);
		}
	}
	
	public Trasa dajTrasu() {
		return trasa;
	}
	
	public double zaradMedzi(int i, int j, int x) {
		return -matica[i][j]+matica[i][x]+matica[x][j];
	}
	
	private int dajNajblizsiNezaradeny(int uzol) {
		double min = Double.MAX_VALUE;
		int mini = -1;
		for (int i = 0; i < matica[uzol].length; i++) {
			if (matica[uzol][i] < min && !trasa.obsahuje(i)) {
				min = matica[uzol][i];
				mini = i;
			}
		}
		return mini;
	}
}
