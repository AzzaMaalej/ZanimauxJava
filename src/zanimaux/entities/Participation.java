/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package zanimaux.entities;

import java.util.Objects;

/**
 *
 * @author Maroua
 */
public class Participation {
    
    private int idParticipation;
    private int idEvt;
    private String cin;

    public Participation(int idParticipation, int idEvt, String cin) {
        this.idParticipation = idParticipation;
        this.idEvt = idEvt;
        this.cin = cin;
    }

    public Participation(int idEvt, String cin) {
        this.idEvt = idEvt;
        this.cin = cin;
    }
    
    

    public Participation() {
        
    }

    public int getIdParticipation() {
        return idParticipation;
    }

    public void setIdParticipation(int idParticipation) {
        this.idParticipation = idParticipation;
    }

    public int getIdEvt() {
        return idEvt;
    }

    public void setIdEvt(int idEvt) {
        this.idEvt = idEvt;
    }

    public String getCin() {
        return cin;
    }

    public void setCin(String cin) {
        this.cin = cin;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 67 * hash + this.idParticipation;
        hash = 67 * hash + this.idEvt;
        hash = 67 * hash + Objects.hashCode(this.cin);
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
        final Participation other = (Participation) obj;
        if (this.idParticipation != other.idParticipation) {
            return false;
        }
        if (this.idEvt != other.idEvt) {
            return false;
        }
        if (!Objects.equals(this.cin, other.cin)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "participation{" + "idParticipation=" + idParticipation + ", idEvt=" + idEvt + ", cin=" + cin + '}';
    }
    
    
    
}
