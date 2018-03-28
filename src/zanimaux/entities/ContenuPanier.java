/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package zanimaux.entities;

import java.sql.Date;

/**
 *
 * @author macbookpro
 */
public class ContenuPanier {
    private int idContenuPanier;
    private String cin;
    private int quantite;
    private int idProduit;
    private int commande;
    private Date dateCommande;

    public ContenuPanier() {
    }

    public int getIdContenuPanier() {
        return idContenuPanier;
    }

    public void setIdContenuPanier(int idContenuPanier) {
        this.idContenuPanier = idContenuPanier;
    }

    public String getCin() {
        return cin;
    }

    public void setCin(String cin) {
        this.cin = cin;
    }

    public int getQuantite() {
        return quantite;
    }

    public void setQuantite(int quantite) {
        this.quantite = quantite;
    }

    public int getIdProduit() {
        return idProduit;
    }

    public void setIdProduit(int idProduit) {
        this.idProduit = idProduit;
    }

    public int getCommande() {
        return commande;
    }

    public void setCommande(int commande) {
        this.commande = commande;
    }

    public Date getDateCommande() {
        return dateCommande;
    }

    public void setDateCommande(Date dateCommande) {
        this.dateCommande = dateCommande;
    }
}
