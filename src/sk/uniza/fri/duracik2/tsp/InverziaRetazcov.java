/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sk.uniza.fri.duracik2.tsp;

import java.util.Arrays;
import java.util.Random;
import sk.uniza.fri.duracik2.tsp.Tsp.TspCallback;

/**
 *
 * @author Unlink
 */
public class InverziaRetazcov {
	
	private int[] aNajlepsieRies;
	private double aNajlepsiaHodnota;
	private int[] aAktualna;
	private double aAktualnaHodnota;
	private double aTeplota;
	
	private int aPocPreskumanych;
	private int aQ;
	private int aU;
	
	private double[][] aMatica;
	
	private TspCallback aCallback;

	public InverziaRetazcov(int[] paNajlepsieRies, double paTeplota, int paU, int paQ, double[][] paMatica, TspCallback paCallback) {
		this.aCallback = paCallback;
		this.aNajlepsieRies = paNajlepsieRies;
		this.aTeplota = paTeplota;
		this.aU = paU;
		this.aQ = paQ;
		this.aMatica = paMatica;
		aAktualna = Arrays.copyOf(aNajlepsieRies, aNajlepsieRies.length);
		aPocPreskumanych = 0;
		aNajlepsiaHodnota = spocitajUf(paNajlepsieRies);
		aAktualnaHodnota = aNajlepsiaHodnota;
	}
	
	public double spocitajUf(int[] paRiesenie) {
		double sum = 0;
		for (int i=1; i<paRiesenie.length; i++) {
			sum+=aMatica[i-1][i];
		}
		sum+=aMatica[paRiesenie[paRiesenie.length-1]][0];
		return sum;
	}
	
	
	public void ries() {
		int[] pom;
		int uzol = 0;
		double uf;
		int r = 0;
		int w = 0;
		Random rg = new Random();
		while (r != aU) {
			pom = Arrays.copyOf(aAktualna, aAktualna.length);
			urobInverziu(pom, uzol);
			w++;
			r++;
			if (w == aQ) {
				w = 0;
				aTeplota /= 2.0d;
			}
			
			uf = spocitajUf(pom);
			if (uf < aAktualnaHodnota) {
				aAktualna = Arrays.copyOf(pom, pom.length);
				aAktualnaHodnota = uf;
				r= 0;
				if (aAktualnaHodnota < aNajlepsiaHodnota) {
					aNajlepsieRies = Arrays.copyOf(aAktualna, aAktualna.length);
					aNajlepsiaHodnota = aAktualnaHodnota;
					aCallback.log("Nájdené lepšie riešenie");
					aCallback.log(vypisRiesenie(aNajlepsieRies, aNajlepsiaHodnota));
				}
			}
			else {
				double p = Math.pow(Math.E, -((uf-aAktualnaHodnota)/aTeplota));
				if (rg.nextDouble() <= p) {
					aAktualna = Arrays.copyOf(pom, pom.length);
					aAktualnaHodnota = uf;
					r = 0;
				}
			}
			uzol = (uzol+1)%aNajlepsieRies.length;
		}
	}
	
	public void urobInverziu(int[] paRiesenie, int paIndex) {
		int pom = paRiesenie[i(paIndex+3)];
		paRiesenie[i(paIndex+3)] = paRiesenie[i(paIndex)];
		paRiesenie[i(paIndex)] = pom;
		pom = paRiesenie[i(paIndex+2)];
		paRiesenie[i(paIndex+2)] = paRiesenie[i(paIndex+1)];
		paRiesenie[i(paIndex+1)] = pom;
	}
	
	public int[] dajNajlepsieRiesenie() {
		return aNajlepsieRies;
	}
	
	public double dajHodnotuNajRiesenia() {
		return aNajlepsiaHodnota;
	}
	
	private int i(int i) {
		return i%aNajlepsieRies.length;
	}
	
	public String vypisRiesenie(int[] paRiesenie, double paHodnota) {
		StringBuilder sb = new StringBuilder("Hodnota: "+paHodnota+"\n");
		for (int i : paRiesenie) {
			sb.append(i).append(", ");
		}
		sb.delete(sb.length() - 2, sb.length());
		return sb.toString();
	}
	
}
