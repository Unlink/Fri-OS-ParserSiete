/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sk.uniza.fri.duracik2.tsp;

import java.util.Iterator;

/**
 *
 * @author Unlink
 */
public class Trasa {

	private final Uzol zaciatok;
	private final Uzol[] mapovanie;
	private int dlzka;

	public Trasa(int zaciatok, int pocetMiest) {
		this.zaciatok = new Uzol(zaciatok);
		mapovanie = new Uzol[pocetMiest];
		mapovanie[zaciatok] = this.zaciatok;
		dlzka = 1;
	}

	/**
	 * Dlzka trasy;
	 * @return 
	 */
	public int dlzka() {
		return dlzka;
	}
	
	/**
	 * Pridá uzol na koniec
	 *
	 * @param uzol
	 */
	public void pridaj(int uzol) {
		pridaj(zaciatok.getPrev(), uzol);
	}

	/**
	 * Vloží nový uzol za konkrétny uzol
	 *
	 * @param predchodca
	 * @param val
	 */
	public void pridaj(Uzol predchodca, int val) {
		if (mapovanie[val] != null) {
			throw new IllegalArgumentException("Daný uzol sa už v ceste nacháza");
		}
		Uzol novy = new Uzol(val);
		mapovanie[val] = novy;
		novy.setNext(predchodca.getNext());
		novy.setPrev(predchodca);
		dlzka++;
	}

	/**
	 * Vloží nový uzol za konkretny uzol
	 *
	 * @param predchodca
	 * @param val
	 */
	public void pridaj(int predchodca, int val) {
		if (mapovanie[predchodca] == null) {
			throw new IllegalArgumentException("Predchodca nebol najdeny");
		}
		pridaj(mapovanie[predchodca], val);
	}

	/**
	 * Overí či trasa obsahuje zadaný uzol
	 * @param uzol
	 * @return 
	 */
	public boolean obsahuje(int uzol) {
		return mapovanie[uzol] != null;
	}
	
	/**
	 * Vráti zaciatok trasy
	 * @return 
	 */
	public Uzol zaciatok() {
		return zaciatok;
	}

	/**
	 * Vypiše trasu
	 * @param uzly
	 * @return 
	 */
	public String vypis(String[] uzly) {
		StringBuilder sb = new StringBuilder();
		Uzol aktual = zaciatok;
		do {
			sb.append(uzly[aktual.getVal()]).append(", ");
			aktual = aktual.getNext();
		} while (aktual != zaciatok);
		
		sb.delete(sb.length()-2, sb.length());
		return sb.toString();
	}

}
