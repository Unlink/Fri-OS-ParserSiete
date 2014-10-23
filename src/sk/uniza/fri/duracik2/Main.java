package sk.uniza.fri.duracik2;

import java.io.File;
import sk.uniza.fri.duracik2.entity.Okres;
import sk.uniza.fri.duracik2.entity.Uzol;

/**
 *
 * @author Unlink
 */
public class Main {
	
	public static void main(String[] args) {
		String pathToData = "C:\\Users\\Unlink\\Desktop\\opts";
		FileParser fp = new FileParser(new File(pathToData));
		for (Okres o:fp.nahrajOkresy().values()) {
			System.out.println(o.toString());
		}
		
		int a = 0,b = 0;
		for (Uzol u : fp.nahrajUzly().values()) {
			if (u.getOkres() == null) b++; else a++;
			System.out.println(u);
		}
		System.out.println("Má okres "+a+" a nemá okres "+b);
	}
	
}
