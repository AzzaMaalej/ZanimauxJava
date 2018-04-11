/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package zanimaux.entities;

import java.util.Date;
import org.joda.time.DateTime;

/**
 *
 * @author Mariam
 */
public class Rendezvs {
   private int idrdv;
    private String cin;
    private String immatriculecabinet;
    private Date heurerdv;

    public int getIdrdv() {
        return idrdv;
    }

    public void setIdrdv(int idrdv) {
        this.idrdv = idrdv;
    }

    public String getCin() {
        return cin;
    }

    public void setCin(String cin) {
        this.cin = cin;
    }

    public String getImmatriculecabinet() {
        return immatriculecabinet;
    }

    public void setImmatriculecabinet(String immatriculecabinet) {
        this.immatriculecabinet = immatriculecabinet;
    }

    public Date getHeurerdv() {
        return heurerdv;
    }

    public void setHeurerdv(Date heurerdv) {
        this.heurerdv = heurerdv;
    }

    public Rendezvs(int idrdv, String cin, String immatriculecabinet, Date heurerdv) {
        this.idrdv = idrdv;
        this.cin = cin;
        this.immatriculecabinet = immatriculecabinet;
        this.heurerdv = heurerdv;
    }

    public Rendezvs(String cin, String immatriculecabinet, Date heurerdv) {
        this.cin = cin;
        this.immatriculecabinet = immatriculecabinet;
        this.heurerdv = heurerdv;
    }

    

    public Rendezvs() {
    }
    
    
    
    
}
