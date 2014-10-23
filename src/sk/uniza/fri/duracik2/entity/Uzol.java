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
public class Uzol {
	private int aId;
	private int aNazov;
	private int aX;
	private int aY;

	public Uzol(int paId, int paNazov) {
		this.aId = paId;
		this.aNazov = paNazov;
	}

	public int getId() {
		return aId;
	}

	public void setId(int id) {
		this.aId = id;
	}

	public int getNazov() {
		return aNazov;
	}

	public void setNazov(int nazov) {
		this.aNazov = nazov;
	}

	public int getX() {
		return aX;
	}

	public void setX(int x) {
		this.aX = x;
	}

	public int getY() {
		return aY;
	}

	public void setY(int y) {
		this.aY = y;
	}
	
	
	
}
