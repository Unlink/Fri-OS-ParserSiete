/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Ferko
 */
public class Uzol {
    private int ID;
    private String nazov;
    private boolean krizovatka;

    public Uzol(int ID, String nazov, boolean krizovatka) {
        this.ID = ID;
        this.nazov = nazov;
        this.krizovatka=krizovatka;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getNazov() {
        return nazov;
    }

    public void setNazov(String nazov) {
        this.nazov = nazov;
    }

    public boolean isKrizovatka() {
        return krizovatka;
    }

    public void setKrizovatka(boolean krizovatka) {
        this.krizovatka = krizovatka;
    }
    
    
    
    
}
