package sk.uniza.fri.duracik2;

import java.io.File;
import sk.uniza.fri.duracik2.entity.Okres;

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
	}
	
}
