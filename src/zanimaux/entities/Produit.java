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
public class Produit {

    public Produit() {
    }
    
    private int idProduit;
    private int idMagasin;
    private String libelle;
    private String photoProduit;
    private String marque;
    private String type;
    private String description;
    private int nbFoisVendu;
    private int quantite;
    private double prix;

    public int getIdProduit() {
        return idProduit;
    }



    public String getLibelle() {
        return libelle;
    }

    public void setLibelle(String libelle) {
        this.libelle = libelle;
    }

    public String getPhotoProduit() {
        return photoProduit;
    }

    public void setPhotoProduit(String photoProduit) {
        this.photoProduit = photoProduit;
    }

    public String getMarque() {
        return marque;
    }

    public void setMarque(String marque) {
        this.marque = marque;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getNbFoisVendu() {
        return nbFoisVendu;
    }

    public void setNbFoisVendu(int nbFoisVendu) {
        this.nbFoisVendu = nbFoisVendu;
    }

    public int getQuantite() {
        return quantite;
    }

    public void setQuantite(int quantite) {
        this.quantite = quantite;
    }

    public double getPrix() {
        return prix;
    }

    public void setPrix(double prix) {
        this.prix = prix;
    }

    public Produit(int idProduit, int idMagasin, String libelle, String photoProduit, String marque, String type, String description, int nbFoisVendu, int quantite, double prix) {
        this.idProduit = idProduit;
        this.idMagasin = idMagasin;
        this.libelle = libelle;
        this.photoProduit = photoProduit;
        this.marque = marque;
        this.type = type;
        this.description = description;
        this.nbFoisVendu = nbFoisVendu;
        this.quantite = quantite;
        this.prix = prix;
    }

    public int getIdMagasin() {
        return idMagasin;
    }

    public void setIdMagasin(int idMagasin) {
        this.idMagasin = idMagasin;
    }

    public void setIdProduit(int idProduit) {
        this.idProduit = idProduit;
    }

  

    @Override
    public String toString() {
        return "Produit{" + "idMagasin=" + idMagasin + ", libelle=" + libelle + ", photoProduit=" + photoProduit + ", marque=" + marque + ", type=" + type + ", description=" + description + ", nbFoisVendu=" + nbFoisVendu + ", quantite=" + quantite + ", prix=" + prix + '}';
    }

    @Override
    public int hashCode() {
        int hash = 5;
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
        final Produit other = (Produit) obj;
        if (this.idProduit != other.idProduit) {
            return false;
        }
        return true;
    }
    
}
