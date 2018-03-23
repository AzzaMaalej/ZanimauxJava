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
    private int codePostaleMagasin;
    private String photoMagasin;
    private User cinProprietaireMagasin;
    private Produit bestSellerMagasin;

    public Magasin() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
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

    public User getCinProprietaireMagasin() {
        return cinProprietaireMagasin;
    }

    public void setCinProprietaireMagasin(User cinProprietaireMagasin) {
        this.cinProprietaireMagasin = cinProprietaireMagasin;
    }

    public Produit getBestSellerMagasin() {
        return bestSellerMagasin;
    }

    public void setBestSellerMagasin(Produit bestSellerMagasin) {
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

    public Magasin(int idMagasin, String numRC, String nomMagasin, String adresseMagasin, int codePostaleMagasin, String photoMagasin, User cinProprietaireMagasin, Produit bestSellerMagasin) {
        this.idMagasin = idMagasin;
        this.numRC = numRC;
        this.nomMagasin = nomMagasin;
        this.adresseMagasin = adresseMagasin;
        this.codePostaleMagasin = codePostaleMagasin;
        this.photoMagasin = photoMagasin;
        this.cinProprietaireMagasin = cinProprietaireMagasin;
        this.bestSellerMagasin = bestSellerMagasin;
    }

    public void setIdMagasin(int idMagasin) {
        this.idMagasin = idMagasin;
    }





   
}
