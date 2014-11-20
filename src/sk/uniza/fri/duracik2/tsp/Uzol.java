/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sk.uniza.fri.duracik2.tsp;

/**
 *
 * @author Unlink
 */
public class Uzol {
	private Uzol next;
	private Uzol prev;
	private final int val;

	public Uzol(Uzol next, Uzol prev, Integer val) {
		this.next = next;
		this.prev = prev;
		this.val = val;
	}

	public Uzol(int val) {
		this.val = val;
		this.next = this;
		this.prev = this;
	}

	public Uzol getNext() {
		return next;
	}

	public void setNext(Uzol next) {
		this.next = next;
		next.prev = this;
	}

	public Uzol getPrev() {
		return prev;
	}

	public void setPrev(Uzol prev) {
		this.prev = prev;
		prev.next = this;
	}

	public int getVal() {
		return val;
	}
	
}
