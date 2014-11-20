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
	private String aNazov;
	private int aX;
	private int aY;
	private Okres aOkres;
	private int aPocObv;
	
	private static final String KRIZOVATKA = "križovatka";

	public Uzol(int paId, String paNazov) {
		this.aId = paId;
		this.aNazov = (paNazov.isEmpty()) ? KRIZOVATKA : paNazov;
	}

	public int getId() {
		return aId;
	}

	public void setId(int id) {
		this.aId = id;
	}

	public String getNazov() {
		return aNazov;
	}

	public void setNazov(String nazov) {
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

	public Okres getOkres() {
		return aOkres;
	}

	public void setOkres(Okres okres) {
		this.aOkres = okres;
	}
	
	public boolean isKrizovatka() {
		return aNazov == KRIZOVATKA;
	}
	
	

	@Override
	public String toString() {
		return aNazov;
	}

	@Override
	public int hashCode() {
		int hash = 7;
		hash = 83 * hash + this.aId;
		return hash;
	}

	@Override
	public boolean equals(Object obj) {
		if (obj == null) {
			return false;
		}
		if (getClass() != obj.getClass()) {
			return false;
		}
		final Uzol other = (Uzol) obj;
		if (this.aId != other.aId) {
			return false;
		}
		return true;
	}

	public int getPocObv() {
		return aPocObv;
	}

	public void setPocObv(int pocObv) {
		this.aPocObv = pocObv;
	}
	
	
	
}
