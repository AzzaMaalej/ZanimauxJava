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
public class Panier {
    private String cin;
    private double somme;
    private double sommeCommande;

    public String getCin() {
        return cin;
    }

    public void setCin(String cin) {
        this.cin = cin;
    }

    
    public Panier() {
    }

    public double getSomme() {
        return somme;
    }

    public void setSomme(double somme) {
        this.somme = somme;
    }

    public double getSommeCommande() {
        return sommeCommande;
    }

    public void setSommeCommande(double sommeCommande) {
        this.sommeCommande = sommeCommande;
    }

    public Panier(String cin, float somme) {
        this.cin = cin;
        this.somme = somme;
    }

    public Panier(String cin) {
        this.cin = cin;
    }

    public Panier(String cin, float somme, float sommeCommande) {
        this.cin = cin;
        this.somme = somme;
        this.sommeCommande = sommeCommande;
    }
    
    
}
