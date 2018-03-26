/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package zanimaux.entities;

import java.util.Objects;

/**
 *
 * @author BelhassenLimam
 */
public class Parc {
     private int idParc;
    private String nomParc;
    private String CategorieDressage;
    private String adresseParc;
    private String villeParc;
    private int   codePostaleParc;
    private String photoParc;
    private User cinUser;

    public Parc(int idParc, String nomParc, String CategorieDressage, String adresseParc, String villeParc, int codePostaleParc, String photoParc, User cinUser) {
        this.idParc = idParc;
        this.nomParc = nomParc;
        this.CategorieDressage = CategorieDressage;
        this.adresseParc = adresseParc;
        this.villeParc = villeParc;
        this.codePostaleParc = codePostaleParc;
        this.photoParc = photoParc;
        this.cinUser = cinUser;
    }

    public Parc(int idParc, String nomParc, String CategorieDressage, String adresseParc, String villeParc, int codePostaleParc, User cinUser) {
        this.idParc = idParc;
        this.nomParc = nomParc;
        this.CategorieDressage = CategorieDressage;
        this.adresseParc = adresseParc;
        this.villeParc = villeParc;
        this.codePostaleParc = codePostaleParc;
        this.cinUser = cinUser;
    }

    public int getIdParc() {
        return idParc;
    }

    public void setIdParc(int idParc) {
        this.idParc = idParc;
    }

    public String getNomParc() {
        return nomParc;
    }

    public void setNomParc(String nomParc) {
        this.nomParc = nomParc;
    }

    public String getCategorieDressage() {
        return CategorieDressage;
    }

    public void setCategorieDressage(String CategorieDressage) {
        this.CategorieDressage = CategorieDressage;
    }

    public String getAdresseParc() {
        return adresseParc;
    }

    public void setAdresseParc(String adresseParc) {
        this.adresseParc = adresseParc;
    }

    public String getVilleParc() {
        return villeParc;
    }

    public void setVilleParc(String villeParc) {
        this.villeParc = villeParc;
    }

    public int getCodePostaleParc() {
        return codePostaleParc;
    }

    public void setCodePostaleParc(int codePostaleParc) {
        this.codePostaleParc = codePostaleParc;
    }

    public String getPhotoParc() {
        return photoParc;
    }

    public void setPhotoParc(String photoParc) {
        this.photoParc = photoParc;
    }

    public User getCinUser() {
        return cinUser;
    }

    public void setCinUser(User cinUser) {
        this.cinUser = cinUser;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 59 * hash + this.idParc;
        hash = 59 * hash + Objects.hashCode(this.nomParc);
        hash = 59 * hash + Objects.hashCode(this.CategorieDressage);
        hash = 59 * hash + Objects.hashCode(this.adresseParc);
        hash = 59 * hash + Objects.hashCode(this.villeParc);
        hash = 59 * hash + this.codePostaleParc;
        hash = 59 * hash + Objects.hashCode(this.photoParc);
        hash = 59 * hash + Objects.hashCode(this.cinUser);
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
        final Parc other = (Parc) obj;
        if (this.idParc != other.idParc) {
            return false;
        }
        if (this.codePostaleParc != other.codePostaleParc) {
            return false;
        }
        if (!Objects.equals(this.nomParc, other.nomParc)) {
            return false;
        }
        if (!Objects.equals(this.CategorieDressage, other.CategorieDressage)) {
            return false;
        }
        if (!Objects.equals(this.adresseParc, other.adresseParc)) {
            return false;
        }
        if (!Objects.equals(this.villeParc, other.villeParc)) {
            return false;
        }
        if (!Objects.equals(this.cinUser, other.cinUser)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Parc{" + "idParc=" + idParc + ", nomParc=" + nomParc + ", CategorieDressage=" + CategorieDressage + ", adresseParc=" + adresseParc + ", villeParc=" + villeParc + ", codePostaleParc=" + codePostaleParc + ", photoParc=" + photoParc + ", cinUser=" + cinUser + '}';
    }
    
    
  
}
