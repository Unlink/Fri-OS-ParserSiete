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
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;
import sk.uniza.fri.duracik2.entity.Hrana;
import sk.uniza.fri.duracik2.entity.Okres;
import sk.uniza.fri.duracik2.entity.Uzol;

import static java.lang.Double.parseDouble;
import static java.lang.Integer.parseInt;

/**
 *
 * @author Unlink
 */
public class FileParser {

	private File aDirectory;
	private static final String OKRESY = "SR_okresy.ATR";
	private static final String OKRESY_HRANICE = "SR_okresy.VEC";
	
	private static final String UZLY = "SR_uzly.ATR";
	private static final String UZLY_SURADNICE = "SR_uzly.VEC";
	private static final String UZLY_POC_OBY = "obyvatelia.txt";
	
	private static final String HRANY = "SR_incid.txt";
	private static final String HRANY_DLZKA = "SR_cesty.ATR";
	
	public static final int MAGIC_CONSTANTA = 3;

	private HashMap<Integer, Okres> aOkresy;
	private HashMap<Integer, Uzol> aUzly;
	private HashMap<Integer, Hrana> aHrany;
	

	public FileParser(File paDirectory) {
		this.aDirectory = paDirectory;
		this.aOkresy = null;
		this.aUzly = null;
		this.aHrany = null;
	}

	public HashMap<Integer, Okres> nahrajOkresy() {
		if (aOkresy == null) {
			aOkresy = _NahrajOkresy();
		}
		return aOkresy;
	}

	public HashMap<Integer, Uzol> nahrajUzly() {
		if (aUzly == null) {
			aUzly = _NahrajUzly();
		}
		return aUzly;
	}
	
	public HashMap<Integer, Hrana> nahrajHrany() {
		if (aHrany == null) {
			aHrany = _NahrajHrany();
		}
		return aHrany;
	}
	
	public Okres najdiOkres(String paName) {
		for (Okres o : nahrajOkresy().values()) {
			if (o.getName().equalsIgnoreCase(paName))
				return o;
		}
		return null;
	}
	
	public Okres najdiOkres(int paX, int paY) {
		for (Okres value : aOkresy.values()) {
			if (value.getHranica().contains(paX, paY))
				return value;
		}
		return null;
	}
	
	public Set<Okres> najdiOkresy(String paStr) {
		HashSet<Okres> okresy = new HashSet<>();
		for (String s:paStr.split(",")) {
			Okres o = najdiOkres(s.trim());
			if (o == null) {
				System.err.println("Nepodarilo sa nájsť okres "+s.trim());
			}
			else {
				okresy.add(o);
			}
		}
		return okresy;
	}
	
	public Set<Uzol> najdiUzly(Set<Okres> paOkresy) {
		return najdiUzly(paOkresy, true);
	}
	
	public Set<Uzol> najdiUzly(Set<Okres> paOkresy, boolean paKrizovatky) {
		HashSet<Uzol> uzly = new HashSet<>();
		for (Uzol u : nahrajUzly().values()) {
			if (paOkresy.contains(u.getOkres()) && (!u.isKrizovatka() || paKrizovatky)) {
				uzly.add(u);
			}
		}
		return uzly;
	}
	
	public Set<Hrana> najdiHrany(Set<Uzol> paUzly) {
		HashSet<Hrana> hrany = new HashSet<>();
		for (Hrana h : nahrajHrany().values()) {
			if (paUzly.contains(h.getU1()) && paUzly.contains(h.getU2())) {
				hrany.add(h);
			}
		}
		return hrany;
	}

	private HashMap<Integer, Okres> _NahrajOkresy() {
		HashMap<Integer, Okres> okresy = new HashMap<>();
		try (
				BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(new File(aDirectory, OKRESY)), "cp1250"));
				BufferedReader br1 = new BufferedReader(new InputStreamReader(new FileInputStream(new File(aDirectory, OKRESY_HRANICE))));) {
			String line;
			while ((line = br.readLine()) != null) {
				Scanner s = new Scanner(line);
				Okres o = new Okres(s.nextInt(), s.nextLine().trim());
				okresy.put(o.getId(), o);
			}

			int count;
			int[] surX;
			int[] surY;

			while ((line = br1.readLine()) != null) {
				Scanner s = new Scanner(line);
				int id = s.nextInt();
				if (id == 0) {
					break;
				}
				count = s.nextInt();
				surX = new int[count];
				surY = new int[count];
				for (int i = 0; i < count; i++) {
					String[] split = br1.readLine().split(" ");
					surX[i] = (int) (parseDouble(split[1]) * MAGIC_CONSTANTA);
					surY[i] = (int) (parseDouble(split[3]) * MAGIC_CONSTANTA);
				}
				okresy.get(id).setHranica(new Polygon(surX, surY, count));
			}

		} catch (IOException ex) {
			System.err.println(ex.getMessage());
		}
		return okresy;

	}

	private HashMap<Integer, Uzol> _NahrajUzly() {
		if (aOkresy == null) {
			nahrajOkresy();
		}
		HashMap<Integer, Uzol> uzly = new HashMap<>();
		try (
				BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(new File(aDirectory, UZLY)), "cp1250"));
				BufferedReader br1 = new BufferedReader(new InputStreamReader(new FileInputStream(new File(aDirectory, UZLY_SURADNICE))));
				BufferedReader br2 = new BufferedReader(new InputStreamReader(new FileInputStream(new File(aDirectory, UZLY_POC_OBY))));
		) {
			String line;
			while ((line = br.readLine()) != null) {
				Scanner s = new Scanner(line);
				Uzol u = new Uzol(s.nextInt(), (s.hasNextLine() ? s.nextLine().trim() : ""));
				uzly.put(u.getId(), u);
			}
			
			while ((line = br1.readLine()) != null) {
				String[] split = line.split(" ");
				int id = parseInt(split[0]);
				if (id == 0) break;
				line = br1.readLine();
				split = line.split(" ");
				int x = (int) (parseDouble(split[1])*MAGIC_CONSTANTA);
				int y = (int) (parseDouble(split[3])*MAGIC_CONSTANTA);
				Uzol u = uzly.get(id);
				u.setX(x);
				u.setY(y);
				u.setOkres(najdiOkres(x, y));
			}
			
			while ((line = br2.readLine()) != null) {
				String[] split = line.split("\t");
				int id = parseInt(split[0]);
				int obyv = parseInt(split[1]);
				if (uzly.containsKey(id)) uzly.get(id).setPocObv(obyv);
			}
			
		} catch (IOException ex) {
			System.err.println(ex.getMessage());
		}
		return uzly;
	}

	private HashMap<Integer, Hrana> _NahrajHrany() {
		if (aUzly == null) {
			nahrajUzly();
		}
		HashMap<Integer, Hrana> hrany = new HashMap<>();
		try (
				BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(new File(aDirectory, HRANY))));
				BufferedReader br1 = new BufferedReader(new InputStreamReader(new FileInputStream(new File(aDirectory, HRANY_DLZKA))));
		) {
			String line;
			while ((line = br.readLine()) != null) {
				Scanner s = new Scanner(line);
				Hrana h = new Hrana(s.nextInt(), aUzly.get(s.nextInt()), aUzly.get(s.nextInt()));
				hrany.put(h.getId(), h);
			}
			
			while ((line = br1.readLine()) != null) {
				String[] split = line.split(" ");
				int id = parseInt(split[0]);
				if (id == 0) break;
				double dlzka = parseDouble(split[1]);
				hrany.get(id).setDlzka(dlzka);
			}
			
		} catch (IOException ex) {
			System.err.println(ex.getMessage());
		}
		return hrany;
	}

}
