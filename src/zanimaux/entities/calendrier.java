/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package zanimaux.entities;

import java.sql.Date;

/**
 *
 * @author Mariam
 */
public class calendrier {
    private int id;
    private Date dateDebut;
    private Date dateFin;
    private String cin;

    public int getId() {
        return id;
    }

   

    public Date getDateDebut() {
        return dateDebut;
    }

    public void setDateDebut(Date dateDebut) {
        this.dateDebut = dateDebut;
    }

    public Date getDateFin() {
        return dateFin;
    }

    public void setDateFin(Date dateFin) {
        this.dateFin = dateFin;
    }

    public String getCin() {
        return cin;
    }

    public void setCin(String cin) {
        this.cin = cin;
    }

    public calendrier(int id, Date dateDebut, Date dateFin, String cin) {
        this.id = id;
        this.dateDebut = dateDebut;
        this.dateFin = dateFin;
        this.cin = cin;
    }

    public calendrier() {
    }
    
    
    
}
