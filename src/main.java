import java.awt.Polygon;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Ferko
 */
public class main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws FileNotFoundException, IOException {
        Polygon polyPoprad = nacitajHraniceOkresu("6649");
        Polygon polyLevoca = nacitajHraniceOkresu("6306");
        ArrayList<Integer> nacitaneUzly = nacitajUzly(polyLevoca, polyPoprad);
        ArrayList<Usek> nacitaneUseky = nacitajIDusekovAjSbodmi(nacitaneUzly);
        pridelUsekomDlzky(nacitaneUseky);
        ArrayList<Uzol> spravUzly = spravUzly(nacitaneUzly);
        System.out.println("");
        
//        String g = "10774  9830  9838";
//        String[] split = g.split(" ");
//        System.err.println("");
        
    }

    public static Polygon nacitajHraniceOkresu(String IDokresu) throws FileNotFoundException, IOException {
        FileReader f = new FileReader("C:\\Users\\Unlink\\Documents\\NetBeansProjects\\OSParser\\Siet\\SR_Okresy.VEC");
        BufferedReader br = new BufferedReader(f);

        String readLine = br.readLine();
        while (readLine != null) {
            if (readLine.startsWith(" ")) {
                readLine = br.readLine();
                continue;
            } else {
                String[] split = readLine.split(" ");
                if (split[0].equalsIgnoreCase(IDokresu)) {
                    int pocetBodovLomenejCiary = Integer.parseInt(split[1]);
                    //double[][] hraniceOkresu = new double[pocetBodovLomenejCiary][2];
                    int[] surX = new int[pocetBodovLomenejCiary];
                    int[] surY = new int[pocetBodovLomenejCiary];
                    for (int i = 0; i < pocetBodovLomenejCiary; i++) {
                        readLine = br.readLine();
                        String[] split1 = readLine.split(" ");
                        surX[i] = (int)Double.parseDouble(split1[1]);
                        surY[i] = (int)Double.parseDouble(split1[3]);
                    }
                    System.out.println("Horray");
                    Polygon hranice = new Polygon(surX, surY, pocetBodovLomenejCiary);
                    return hranice;
                }
            }
            readLine = br.readLine();
        }
        System.err.println("Zle ID okresu.");
        return null;
    }
    
    public static ArrayList<Integer> nacitajUzly(Polygon poly1, Polygon poly2) throws FileNotFoundException, IOException{
        FileReader f = new FileReader("C:\\Users\\Unlink\\Documents\\NetBeansProjects\\OSParser\\Siet\\SR_uzly.vec");
        BufferedReader br = new BufferedReader(f);
        
        ArrayList<Integer> IDuzlov = new ArrayList<Integer>();
        
        String readLine = br.readLine();
        while(!readLine.equalsIgnoreCase("0 0")){
            String[] split = readLine.split(" ");
            int IDuzlu = Integer.parseInt(split[0]);
            readLine = br.readLine();
            String[] split1 = readLine.split(" ");
            double x  = Double.parseDouble(split1[1]);
            double y  = Double.parseDouble(split1[3]);
            if(poly1.contains(x, y) || poly2.contains(x, y) ){
                IDuzlov.add(IDuzlu);
            }
            readLine = br.readLine();
            
        }
        return IDuzlov;
    }
    
    public static ArrayList<Usek> nacitajIDusekovAjSbodmi(ArrayList<Integer> nacitaneUzly) throws FileNotFoundException, IOException{
        FileReader f = new FileReader("C:\\Users\\Unlink\\Documents\\NetBeansProjects\\OSParser\\Siet\\SR_incid.txt");
        BufferedReader br = new BufferedReader(f);
        ArrayList<Usek> useky = new ArrayList<>();
        int count=0;
        int count2=0;
        String readLine = br.readLine();
        while(readLine !=null){
            String[] split = readLine.split("\\s+");
            for (Integer en : nacitaneUzly) {
                if(split[1].equals(""+en) || split[2].equals(""+en) ){
                    useky.add(new Usek(Integer.parseInt(split[0]), Integer.parseInt(split[1]), Integer.parseInt(split[2]), 0));
                }
                count2++;
            }
            readLine = br.readLine();
            count++;
        }
        System.out.println(""+count);
        return useky;
    }
    public static void pridelUsekomDlzky(ArrayList<Usek> paUseky) throws FileNotFoundException, IOException{
        FileReader f = new FileReader("C:\\Users\\Unlink\\Documents\\NetBeansProjects\\OSParser\\Siet\\SR_cesty.ATR");
        BufferedReader br = new BufferedReader(f);
        String readLine = br.readLine();
        
        while(readLine!=null){
            String[] split = readLine.split("\\s+");
            for(Usek u:paUseky){
                if(split[0].equals(""+u.getID())){
                    u.setDlzka(Double.parseDouble(split[1]));
                    //break;
                }
            }
            readLine = br.readLine();
        }
    }
    
    public static ArrayList<Uzol> spravUzly(ArrayList<Integer> paIDckaUzlov) throws FileNotFoundException, IOException{
        FileReader f = new FileReader("C:\\Users\\Unlink\\Documents\\NetBeansProjects\\OSParser\\Siet\\SR_uzly.ATR");
        BufferedReader br = new BufferedReader(f);
        String readLine = br.readLine();
        
        ArrayList<Uzol> uzly = new ArrayList<>();
        
        while(readLine!=null){
            String[] split = readLine.split("\\s+");
            for(Integer id: paIDckaUzlov){
                if(split[0].equals(""+id)){
                    if(split.length>1){
                        uzly.add(new Uzol(id, split[1], false));
                    }else{
                        uzly.add(new Uzol(id, "krizovatka "+id, true));
                    }
                }
            }
            readLine = br.readLine();
        }
        return uzly;
    }
}


