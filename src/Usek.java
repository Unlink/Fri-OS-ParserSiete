/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Ferko
 */
public class Usek {
    private int ID;
    private int xID;
    private int yID;
    private double dlzka;

    public Usek(int ID, int xID, int yID, double dlzka) {
        this.ID = ID;
        this.xID = xID;
        this.yID = yID;
        this.dlzka = dlzka;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public int getxID() {
        return xID;
    }

    public void setxID(int xID) {
        this.xID = xID;
    }

    public int getyID() {
        return yID;
    }

    public void setyID(int yID) {
        this.yID = yID;
    }

    public double getDlzka() {
        return dlzka;
    }

    public void setDlzka(double dlzka) {
        this.dlzka = dlzka;
    }
    
    
    
}
