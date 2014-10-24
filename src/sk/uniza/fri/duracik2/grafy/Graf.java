/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sk.uniza.fri.duracik2.grafy;

import java.util.Arrays;
import java.util.EventListener;
import java.util.HashMap;
import java.util.List;
import sk.uniza.fri.duracik2.entity.Hrana;
import sk.uniza.fri.duracik2.entity.Uzol;

/**
 *
 * @author Unlink
 */
public class Graf {

	private Uzol[] aUzly;
	private HashMap<Uzol, Integer> aMap;
	private double[][] aMatica;
	private List<Hrana> aHrany;

	public Graf(Uzol[] paUzly, HashMap<Uzol, Integer> paMap, double[][] paMatica, List<Hrana> paHrany) {
		aUzly = paUzly;
		aMap = paMap;
		aMatica = paMatica;
		aHrany = paHrany;
	}

	public void dopocitajMaticuSymetricky() {
		dopocitajMaticuSymetricky(null);
	}
	
	public void dopocitajMaticuSymetricky(ProgessListenerer evntlst) {
		boolean[] pouzite = new boolean[aMatica.length];
		for (int i = 0; i < aMatica.length - 1; i++) {
			if (evntlst!=null) evntlst.stateChanged(i/(double)aMatica.length);
			Arrays.fill(pouzite, false);
			pouzite[i] = true;
			for (int j = 1; j < aMatica.length; j++) {
				int x = najdiMinimum(i, pouzite);
				if (x == -1) {
					break;
				}
				pouzite[x] = true;

				for (int k = i + 1; k < aMatica.length; k++) {
					if (aMatica[x][k] + aMatica[i][x] < aMatica[i][k]) {
						aMatica[i][k] = aMatica[x][k] + aMatica[i][x];
						aMatica[k][i] = aMatica[x][k] + aMatica[i][x];
					}
				}

			}
		}
	}

	private int najdiMinimum(int paI, boolean[] paPouzite) {
		double min = Double.MAX_VALUE;
		int min_index = -1;
		for (int k = 0; k < aMatica.length; k++) {
			if (!paPouzite[k] && aMatica[paI][k] < min) {
				min = aMatica[paI][k];
				min_index = k;
			}
		}
		return (min == Double.MAX_VALUE) ? -1 : min_index;
	}

	public double[][] getMatica() {
		return aMatica;
	}

	public Uzol[] getUzly() {
		return aUzly;
	}


	public static interface ProgessListenerer extends EventListener {
		public void stateChanged(double percentage);
	}
}
