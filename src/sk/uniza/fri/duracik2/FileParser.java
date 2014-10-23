/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sk.uniza.fri.duracik2;

import java.awt.Polygon;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StreamTokenizer;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;
import java.util.TreeMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import sk.uniza.fri.duracik2.entity.Okres;

/**
 *
 * @author Unlink
 */
public class FileParser {
	private File aDirectory;
	private static final String OKRESY = "SR_okresy.ATR";
	private static final String OKRESY_HRANICE = "SR_okresy.VEC";
	
	private HashMap<Integer, Okres> aOkresy;

	public FileParser(File paDirectory) {
		this.aDirectory = paDirectory;
		this.aOkresy = null;
	}
	
	public HashMap<Integer, Okres> nahrajOkresy() {
		if (aOkresy == null) aOkresy = _NahrajOkresy();
		return aOkresy;
	}
	
	private HashMap<Integer, Okres> _NahrajOkresy() {
		HashMap<Integer, Okres> okresy = new HashMap<>();
		try (
			BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(new File(aDirectory, OKRESY)), "cp1250"));
			BufferedReader br1 = new BufferedReader(new InputStreamReader(new FileInputStream(new File(aDirectory, OKRESY_HRANICE))));
		) {
			String line;
			while ((line = br.readLine()) != null) {
				Scanner s = new Scanner(line);
				Okres o = new Okres(s.nextInt(), s.nextLine());
				okresy.put(o.getId(), o);
			}
			
			int count;
			int[] surX;
			int[] surY;
			
			while ((line = br1.readLine()) != null) {
				Scanner s = new Scanner(line);
				int id = s.nextInt();
				if (id == 0) break;
				count = s.nextInt();
				surX = new int[count];
				surY = new int[count];
				for (int i=0; i<count; i++) {
					String[] split = br1.readLine().split(" ");
					surX[i] = (int) Double.parseDouble(split[1]);
					surY[i] = (int) Double.parseDouble(split[3]);
				}
				okresy.get(id).setHranica(new Polygon(surX, surY, count));
			}
			
		} catch (IOException ex) {
			System.err.println(ex.getMessage());
		}
		return okresy;
		
	}
	
}
