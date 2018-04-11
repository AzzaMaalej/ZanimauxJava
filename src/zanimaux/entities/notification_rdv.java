/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package zanimaux.entities;

/**
 *
 * @author Mariam
 */
public class notification_rdv {
    private int idrdv;
    private int vu;
    private int id;

    public int getIdrdv() {
        return idrdv;
    }

    public void setIdrdv(int idrdv) {
        this.idrdv = idrdv;
    }

    public int getVu() {
        return vu;
    }

    public void setVu(int vu) {
        this.vu = vu;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public notification_rdv(int idrdv, int vu, int id) {
        this.idrdv = idrdv;
        this.vu = vu;
        this.id = id;
    }

    public notification_rdv(int idrdv, int vu) {
        this.idrdv = idrdv;
        this.vu = vu;
    }

    public notification_rdv() {
    }
    
}
