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
public class notif_user_rdv {
    
   private String cin;
    private int vu;
    private int id;

    public notif_user_rdv() {
    }

    public notif_user_rdv(String cin, int vu, int id) {
        this.cin = cin;
        this.vu = vu;
        this.id = id;
    }

    public String getCin() {
        return cin;
    }

    public void setCin(String cin) {
        this.cin = cin;
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
    
           
}
