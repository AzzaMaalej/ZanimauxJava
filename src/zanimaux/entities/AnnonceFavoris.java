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
public class AnnonceFavoris {
    
    private int id;
    private int idA;
    private String cin;

    public AnnonceFavoris(int id, int idA, String cin) {
        this.id = id;
        this.idA = idA;
        this.cin = cin;
    }

    public AnnonceFavoris(int idA, String cin) {
        this.idA = idA;
        this.cin = cin;
    }

    public AnnonceFavoris() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdA() {
        return idA;
    }

    public void setIdA(int idA) {
        this.idA = idA;
    }

    public String getCin() {
        return cin;
    }

    public void setCin(String cin) {
        this.cin = cin;
    }

    @Override
    public String toString() {
        return "AnnonceFavoris{" + "id=" + id + ", idA=" + idA + ", cin=" + cin + '}';
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 71 * hash + this.id;
        hash = 71 * hash + this.idA;
        hash = 71 * hash + Objects.hashCode(this.cin);
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
        final AnnonceFavoris other = (AnnonceFavoris) obj;
        if (this.id != other.id) {
            return false;
        }
        if (this.idA != other.idA) {
            return false;
        }
        if (!Objects.equals(this.cin, other.cin)) {
            return false;
        }
        return true;
    }
    
    
    
}
