/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package zanimaux.entities;

/**
 *
 * @author macbookpro
 */
public class Magasin {
    
    private int idMagasin;
    private String numRC;
    private String nomMagasin;
    private String adresseMagasin;
    private String villeMagasin;

    public String getVilleMagasin() {
        return villeMagasin;
    }

    public void setVilleMagasin(String villeMagasin) {
        this.villeMagasin = villeMagasin;
    }
    private int codePostaleMagasin;
    private String photoMagasin;
    private String cinProprietaireMagasin;
    private int bestSellerMagasin;

    public Magasin() {
        
    }

    public int getIdMagasin() {
        return idMagasin;
    }

    public String getNumRC() {
        return numRC;
    }

    public void setNumRC(String numRC) {
        this.numRC = numRC;
    }

    public String getNomMagasin() {
        return nomMagasin;
    }

    public void setNomMagasin(String nomMagasin) {
        this.nomMagasin = nomMagasin;
    }

    public String getAdresseMagasin() {
        return adresseMagasin;
    }

    public void setAdresseMagasin(String adresseMagasin) {
        this.adresseMagasin = adresseMagasin;
    }

  public int getCodePostaleMagasin() {
        return codePostaleMagasin;
    }

    public void setCodePostaleMagasin(int codePostaleMagasin) {
        this.codePostaleMagasin = codePostaleMagasin;
    }
    public String getPhotoMagasin() {
        return photoMagasin;
    }

    public void setPhotoMagasin(String photoMagasin) {
        this.photoMagasin = photoMagasin;
    }

    public String getCinProprietaireMagasin() {
        return cinProprietaireMagasin;
    }

    public void setCinProprietaireMagasin(String cinProprietaireMagasin) {
        this.cinProprietaireMagasin = cinProprietaireMagasin;
    }


    public int getBestSellerMagasin() {
        return bestSellerMagasin;
    }

    public void setBestSellerMagasin(int bestSellerMagasin) {
        this.bestSellerMagasin = bestSellerMagasin;
    }

    @Override
    public String toString() {
        return "Magasin{" + "numRC=" + numRC + ", nomMagasin=" + nomMagasin + ", adresseMagasin=" + adresseMagasin + ", codePostaleMagasin=" + codePostaleMagasin + ", photoMagasin=" + photoMagasin + ", cinProprietaireMagasin=" + cinProprietaireMagasin + ", bestSellerMagasin=" + bestSellerMagasin + '}';
    }

    @Override
    public int hashCode() {
        int hash = 7;
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
        final Magasin other = (Magasin) obj;
        if (this.idMagasin != other.idMagasin) {
            return false;
        }
        return true;
    }

    public Magasin(int idMagasin, String numRC, String nomMagasin, String adresseMagasin, int codePostaleMagasin) {
        this.idMagasin = idMagasin;
        this.numRC = numRC;
        this.nomMagasin = nomMagasin;
        this.adresseMagasin = adresseMagasin;
        this.codePostaleMagasin = codePostaleMagasin;
    }

    public Magasin(int idMagasin, String numRC, String nomMagasin, String adresseMagasin, int codePostaleMagasin, String photoMagasin, String cinProprietaireMagasin, int bestSellerMagasin) {
        this.idMagasin = idMagasin;
        this.numRC = numRC;
        this.nomMagasin = nomMagasin;
        this.adresseMagasin = adresseMagasin;
        this.codePostaleMagasin = codePostaleMagasin;
        this.photoMagasin = photoMagasin;
        this.cinProprietaireMagasin = cinProprietaireMagasin;
        this.bestSellerMagasin = bestSellerMagasin;
    }

    public Magasin(int idMagasin, String numRC, String nomMagasin, int codePostaleMagasin, String photoMagasin, int bestSellerMagasin) {
        this.idMagasin = idMagasin;
        this.numRC = numRC;
        this.nomMagasin = nomMagasin;
        this.codePostaleMagasin = codePostaleMagasin;
        this.photoMagasin = photoMagasin;
        this.bestSellerMagasin = bestSellerMagasin;
    }

    public void setIdMagasin(int idMagasin) {
        this.idMagasin = idMagasin;
    }   
}
