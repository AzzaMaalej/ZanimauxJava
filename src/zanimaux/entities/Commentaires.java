/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package zanimaux.entities;

import java.sql.Date;
import java.util.Objects;

/**
 *
 * @author Azza
 */
public class Commentaires {
    private int id;
    private String cin;
    private String contenant;
    private Date date;
    private String refuge;

    public Commentaires() {
    }

    public Commentaires(int id, String cin, String contenant, Date date, String refuge) {
        this.id = id;
        this.cin = cin;
        this.contenant = contenant;
        this.date = date;
        this.refuge = refuge;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCin() {
        return cin;
    }

    public void setCin(String cin) {
        this.cin = cin;
    }

    public String getContenant() {
        return contenant;
    }

    public void setContenant(String contenant) {
        this.contenant = contenant;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getRefuge() {
        return refuge;
    }

    public void setRefuge(String refuge) {
        this.refuge = refuge;
    }

    @Override
    public String toString() {
        return "Commentaires{" + "id=" + id + ", cin=" + cin + ", contenant=" + contenant + ", date=" + date + ", refuge=" + refuge + '}';
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 53 * hash + this.id;
        hash = 53 * hash + Objects.hashCode(this.cin);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Commentaires other = (Commentaires) obj;
        if (this.id != other.id) {
            return false;
        }
        if (!Objects.equals(this.cin, other.cin)) {
            return false;
        }
        return true;
    }
    
    
}
