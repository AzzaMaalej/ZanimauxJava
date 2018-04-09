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
    private String cinUser;

    public Avis() {
    }

    public Avis(String id, String idParc, double avis, String cinUser) {
        this.id = id;
        this.idParc = idParc;
        this.avis = avis;
        this.cinUser = cinUser;
    }

    public Avis(String idParc, double avis, String cinUser) {
        this.idParc = idParc;
        this.avis = avis;
        this.cinUser = cinUser;
    }
    

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 89 * hash + Objects.hashCode(this.id);
        hash = 89 * hash + Objects.hashCode(this.idParc);
        hash = 89 * hash + (int) (Double.doubleToLongBits(this.avis) ^ (Double.doubleToLongBits(this.avis) >>> 32));
        hash = 89 * hash + Objects.hashCode(this.cinUser);
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
        if (!Objects.equals(this.cinUser, other.cinUser)) {
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

    public String getCinUser() {
        return cinUser;
    }

    public void setCinUser(String cinUser) {
        this.cinUser = cinUser;
    }

    @Override
    public String toString() {
        return "Avis{" + "id=" + id + ", idParc=" + idParc + ", avis=" + avis + ", cinUser=" + cinUser + '}';
    }

    
    
}
