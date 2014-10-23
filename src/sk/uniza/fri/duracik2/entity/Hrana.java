/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sk.uniza.fri.duracik2.entity;

/**
 *
 * @author Unlink
 */
public class Hrana {
	private int aId;
	private double aDlzka;
	private Uzol aU1;
	private Uzol aU2;

	public Hrana(int paId, Uzol paU1, Uzol paU2) {
		this.aId = paId;
		this.aU1 = paU1;
		this.aU2 = paU2;
	}

	public void setDlzka(double dlzka) {
		this.aDlzka = dlzka;
	}

	public int getId() {
		return aId;
	}

	public double getDlzka() {
		return aDlzka;
	}

	public Uzol getU1() {
		return aU1;
	}

	public Uzol getU2() {
		return aU2;
	}
	
	
}
