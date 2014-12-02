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
public class Tsp implements Runnable {

	private double[][] matica;
	private String[] uzly;
	private TspCallback callback;
	
	public Tsp(double[][] matica, String[] uzly, TspCallback callback) {
		this.matica = matica;
		this.uzly = uzly;
		this.callback = callback;
	}
	
	@Override
	public void run() {
		NajblizsiUzol prvaFaza = new NajblizsiUzol(matica);
		prvaFaza.run();
		Trasa trasa = prvaFaza.dajTrasu();
		InverziaRetazcov druhaFaza = new InverziaRetazcov(trasa.toArray(), 50, 9, 9, matica, callback);
		callback.log("Vychodzia trasa:"+druhaFaza.dajHodnotuNajRiesenia());
		StringBuilder sb = new StringBuilder();
		for (int i : druhaFaza.dajNajlepsieRiesenie()) {
			sb.append(uzly[i]).append(", ");
		}
		sb.delete(sb.length() - 2, sb.length());
		callback.log(sb.toString());
		
		druhaFaza.ries();
		
		
		callback.log("\nNajlepsie riesenie: "+druhaFaza.dajHodnotuNajRiesenia());
		sb = new StringBuilder();
		for (int i : druhaFaza.dajNajlepsieRiesenie()) {
			sb.append(uzly[i]).append(", ");
		}
		sb.delete(sb.length() - 2, sb.length());
		callback.log(sb.toString());
	}

	
	public interface TspCallback {
		public void log(String message);
	}
}
