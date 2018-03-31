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
public class Parc {
    private int id;
    private String nomParc;
    private String CategorieDressage;
    private String adresseParc;
    private String villeParc;
    private int   codePostaleParc;
    private String photoParc;
    private String cinDresseur;

    public Parc() {
    }

    public Parc(int id, String nomParc, String CategorieDressage, String adresseParc, String villeParc, int codePostaleParc, String photoParc, String cinDresseur) {
        this.id = id;
        this.nomParc = nomParc;
        this.CategorieDressage = CategorieDressage;
        this.adresseParc = adresseParc;
        this.villeParc = villeParc;
        this.codePostaleParc = codePostaleParc;
        this.photoParc = photoParc;
        this.cinDresseur = cinDresseur;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 53 * hash + this.id;
        hash = 53 * hash + Objects.hashCode(this.nomParc);
        hash = 53 * hash + Objects.hashCode(this.CategorieDressage);
        hash = 53 * hash + Objects.hashCode(this.adresseParc);
        hash = 53 * hash + Objects.hashCode(this.villeParc);
        hash = 53 * hash + this.codePostaleParc;
        hash = 53 * hash + Objects.hashCode(this.photoParc);
        hash = 53 * hash + Objects.hashCode(this.cinDresseur);
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
        if (this.id != other.id) {
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
        if (!Objects.equals(this.cinDresseur, other.cinDresseur)) {
            return false;
        }
        return true;
    }
    
    

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public String getCinDresseur() {
        return cinDresseur;
    }

    public void setCinDresseur(String cinDresseur) {
        this.cinDresseur = cinDresseur;
    }


    
}
