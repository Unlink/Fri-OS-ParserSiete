/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sk.uniza.fri.duracik2.entity;

import java.awt.Polygon;

/**
 *
 * @author Unlink
 */
public class Okres {
	private int aId;
	private String aName;
	private Polygon aHranica;

	public Okres(int paId, String paName) {
		this.aId = paId;
		this.aName = paName;
		this.aHranica = null;
	}

	public int getId() {
		return aId;
	}

	public String getName() {
		return aName;
	}

	public Polygon getHranica() {
		return aHranica;
	}

	public void setHranica(Polygon hranica) {
		this.aHranica = hranica;
	}

	@Override
	public String toString() {
		return "Okres{" + "aId=" + aId + ", aName=" + aName + '}';
	}
	
	
	
}
