/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sk.uniza.fri.duracik2.grafy;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.EventListener;
import java.util.HashMap;
import java.util.List;
import sk.uniza.fri.duracik2.FileParser;
import sk.uniza.fri.duracik2.entity.Hrana;
import sk.uniza.fri.duracik2.entity.Uzol;

/**
 *
 * @author Unlink
 */
public class MaticaVzdalenosti {

	private FileParser aFp;
	private double[][] aMatica;
	private HashMap<Uzol, Integer> aMap;

	private List<ProgessListenerer> aListnerers;

	//Pomocné premenne
	private boolean[] aControled;
	private int aControledCount;
	private boolean[] aUsed;

	public MaticaVzdalenosti(FileParser paFp) {
		this.aFp = paFp;
		this.aListnerers = new ArrayList<>();
		nacitajMaticu();
	}

	public void nacitajMaticu() {
		HashMap<Integer, Uzol> uzly = aFp.nahrajUzly();
		HashMap<Integer, Hrana> hrany = aFp.nahrajHrany();

		aControled = new boolean[uzly.size()];
		aUsed = new boolean[uzly.size()];
		
		//Namapovanie uzolov na maticu
		aMap = new HashMap<>(uzly.size());
		int i = 0;
		for (Uzol value : uzly.values()) {
			aMap.put(value, i);
			i++;
		}

		//Vyplnenie matice nekonečnami
		aMatica = new double[uzly.size()][uzly.size()];
		for (double[] m : aMatica) {
			Arrays.fill(m, Double.POSITIVE_INFINITY);
		}

		//Vylnenie vzdialenosti
		for (Hrana h : hrany.values()) {
			aMatica[aMap.get(h.getU1())][aMap.get(h.getU2())] = h.getDlzka();
			aMatica[aMap.get(h.getU2())][aMap.get(h.getU1())] = h.getDlzka();
		}
		for (int j = 0; j < aMatica.length; j++) {
			aMatica[j][j] = 0;
		}
	}

	public void riesDjikstraPre(List<Uzol> paList) {
		aControledCount = 0;
		for (Uzol uzol : paList) {
			aControled[aMap.get(uzol)] = true;
			aControledCount++;
		}
		int l = 0;
		for (Uzol uzol : paList) {
			int riadok = aMap.get(uzol);
			notifyListnerers(l++/(double)paList.size());
			riesPre(riadok);
		}
	}

	private void riesPre(int paRiadok) {
		Arrays.fill(aUsed, false);
		int pocKontrolovanychSolved = 0;
		aUsed[paRiadok] = true;
		for (int j = 1; j < aMatica.length; j++) {
			int x = najdiMinimum(paRiadok);
			if (x == -1) {
				break;
			}
			aUsed[x] = true;
			if (aControled[x]) {
				pocKontrolovanychSolved++;
				//Pokial sme našli najkratšiu k všetkým požadovaným uzlom tak končíme
				if (pocKontrolovanychSolved > aControledCount)
					break;
			}

			for (int k = 0; k < aMatica.length; k++) {
				if (aMatica[x][k] + aMatica[paRiadok][x] < aMatica[paRiadok][k]) {
					aMatica[paRiadok][k] = aMatica[x][k] + aMatica[paRiadok][x];
					aMatica[k][paRiadok] = aMatica[x][k] + aMatica[paRiadok][x];
				}
			}

		}
	}
	
	public double[][] precitajRiesenie(List<Uzol> paList) {
		double[][] riesenie = new double[paList.size()][paList.size()];
		for (int i = 0; i < paList.size(); i++) {
			for (int j = 0; j < paList.size(); j++) {
				riesenie[i][j] = aMatica[aMap.get(paList.get(i))][aMap.get(paList.get(j))];
				riesenie[j][i] = aMatica[aMap.get(paList.get(j))][aMap.get(paList.get(i))];
			}
		}
		return riesenie;
	}
	
	private int najdiMinimum(int paRiadok) {
		double min = Double.MAX_VALUE;
		int min_index = -1;
		for (int k = 0; k < aMatica.length; k++) {
			if (!aUsed[k] && aMatica[paRiadok][k] < min) {
				min = aMatica[paRiadok][k];
				min_index = k;
			}
		}
		return (min == Double.MAX_VALUE) ? -1 : min_index;
	}

	public void addProgressListnerer(ProgessListenerer l) {
		aListnerers.add(l);
	}

	public void removeProgressListnerer(ProgessListenerer l) {
		aListnerers.remove(l);
	}

	private void notifyListnerers(double percentage) {
		for (ProgessListenerer aListnerer : aListnerers) {
			aListnerer.stateChanged(percentage);
		}
	}

	public static interface ProgessListenerer extends EventListener {

		public void stateChanged(double percentage);
	}

}
