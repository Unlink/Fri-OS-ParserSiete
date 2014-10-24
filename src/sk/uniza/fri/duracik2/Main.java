package sk.uniza.fri.duracik2;

import sk.uniza.fri.duracik2.grafy.GrafBuilder;
import java.io.File;
import java.util.Set;
import sk.uniza.fri.duracik2.entity.Hrana;
import sk.uniza.fri.duracik2.entity.Okres;
import sk.uniza.fri.duracik2.entity.Uzol;
import sk.uniza.fri.duracik2.grafy.Graf;

/**
 *
 * @author Unlink
 */
public class Main {
	
	public static void main(String[] args) {
		String pathToData = "C:\\Users\\Unlink\\Desktop\\opts";
		FileParser fp = new FileParser(new File(pathToData));
		
		GrafBuilder b = new GrafBuilder();
		Set<Uzol> uzly = fp.najdiUzly(fp.najdiOkresy("žilina"));
		Set<Hrana> hrany = fp.najdiHrany(uzly);
		for (Uzol uzly1 : uzly) {
			b.pridajUzol(uzly1);
		}
		for (Hrana hrany1 : hrany) {
			b.pridajHranu(hrany1);
		}
		/*Uzol u1 = new Uzol(1, "A");
		Uzol u2 = new Uzol(2, "B");
		Uzol u3 = new Uzol(3, "C");
		Uzol u4 = new Uzol(4, "D");
		
		Hrana h1 = new Hrana(1, u1, u2);
		h1.setDlzka(4);
		Hrana h2 = new Hrana(1, u1, u3);
		h2.setDlzka(2);
		Hrana h3 = new Hrana(1, u2, u3);
		h3.setDlzka(1);
		Hrana h4 = new Hrana(1, u2, u4);
		h4.setDlzka(3);
		Hrana h5 = new Hrana(1, u3, u4);
		h5.setDlzka(5);
		
		b.pridajUzol(u1).pridajUzol(u2).pridajUzol(u3).pridajUzol(u4);
		b.pridajHranu(h1).pridajHranu(h2).pridajHranu(h3).pridajHranu(h4).pridajHranu(h5);
		*/
		Graf g = b.build();
		g.dopocitajMaticuSymetricky();
		double[][] matica = g.getMatica();
		for (double[] matica1 : matica) {
			for (double n : matica1) {
				System.out.printf("%f\t", n);
			}
			System.out.println("");
		}
		
		/*for (Okres o:fp.nahrajOkresy().values()) {
		System.out.println(o.toString());
		}
		int a = 0,b = 0;
		for (Uzol u : fp.nahrajUzly().values()) {
		if (u.getOkres() == null) b++; else a++;
		System.out.println(u);
		}
		System.out.println("Má okres "+a+" a nemá okres "+b);*/
	}
	
}
