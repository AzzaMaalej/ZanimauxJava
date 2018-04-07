/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package zanimaux.entities;

import java.util.Objects;

/**
 *
 * @author Azza
 */
public class Avis {
    private String id;
    private String idParc;
    private double avis;
    private String cinDresseur;

    public Avis() {
    }

    public Avis(String id, String idParc, double avis, String cinDresseur) {
        this.id = id;
        this.idParc = idParc;
        this.avis = avis;
        this.cinDresseur = cinDresseur;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 89 * hash + Objects.hashCode(this.id);
        hash = 89 * hash + Objects.hashCode(this.idParc);
        hash = 89 * hash + (int) (Double.doubleToLongBits(this.avis) ^ (Double.doubleToLongBits(this.avis) >>> 32));
        hash = 89 * hash + Objects.hashCode(this.cinDresseur);
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
        final Avis other = (Avis) obj;
        if (Double.doubleToLongBits(this.avis) != Double.doubleToLongBits(other.avis)) {
            return false;
        }
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        if (!Objects.equals(this.idParc, other.idParc)) {
            return false;
        }
        if (!Objects.equals(this.cinDresseur, other.cinDresseur)) {
            return false;
        }
        return true;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getIdParc() {
        return idParc;
    }

    public void setIdParc(String idParc) {
        this.idParc = idParc;
    }

    public double getAvis() {
        return avis;
    }

    public void setAvis(double avis) {
        this.avis = avis;
    }

    public String getCinDresseur() {
        return cinDresseur;
    }

    public void setCinDresseur(String cinDresseur) {
        this.cinDresseur = cinDresseur;
    }

    @Override
    public String toString() {
        return "Avis{" + "id=" + id + ", idParc=" + idParc + ", avis=" + avis + ", cinDresseur=" + cinDresseur + '}';
    }

    
    
}
