/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package zanimaux.entities;

/**
 *
 * @author Azza
 */
public class Refuge {
    private String emailRefuge;
    private int telephoneRefuge;
    private int faxRefuge;
    private String adresseRefuge;
    private int codePostaleRefuge;
    private String gouvernementRefuge;
    private String photoRefuge;
    private String chat;
    private String chien;
    private String rongeur;
    private String autre;
    private String immatriculation;
    private String cin;
    private String nomRefuge;


    public Refuge(String immatriculation, String cin, String nomRefuge, String emailRefuge, int telephoneRefuge, int faxRefuge, String adresseRefuge, int codePostaleRefuge, String gouvernementRefuge, String photoRefuge, String chat, String chien, String rongeur, String autre) {
        this.immatriculation = immatriculation;
        this.cin = cin;
        this.nomRefuge = nomRefuge;
        this.emailRefuge = emailRefuge;
        this.telephoneRefuge = telephoneRefuge;
        this.faxRefuge = faxRefuge;
        this.adresseRefuge = adresseRefuge;
        this.codePostaleRefuge = codePostaleRefuge;
        this.gouvernementRefuge = gouvernementRefuge;
        this.photoRefuge = photoRefuge;
        this.chat = chat;
        this.chien = chien;
        this.rongeur = rongeur;
        this.autre = autre;
    }

   
    public Refuge() {
    }

    public String getImmatriculation() {
        return immatriculation;
    }

    public void setImmatriculation(String immatriculation) {
        this.immatriculation = immatriculation;
    }

    public String getCin() {
        return cin;
    }

    public void setCin(String cin) {
        this.cin = cin;
    }

    public String getNomRefuge() {
        return nomRefuge;
    }

    public void setNomRefuge(String nomRefuge) {
        this.nomRefuge = nomRefuge;
    }

    public String getEmailRefuge() {
        return emailRefuge;
    }

    public void setEmailRefuge(String emailRefuge) {
        this.emailRefuge = emailRefuge;
    }

    public int getTelephoneRefuge() {
        return telephoneRefuge;
    }

    public void setTelephoneRefuge(int telephoneRefuge) {
        this.telephoneRefuge = telephoneRefuge;
    }

    public int getFaxRefuge() {
        return faxRefuge;
    }

    public void setFaxRefuge(int faxRefuge) {
        this.faxRefuge = faxRefuge;
    }

    public String getAdresseRefuge() {
        return adresseRefuge;
    }

    public void setAdresseRefuge(String adresseRefuge) {
        this.adresseRefuge = adresseRefuge;
    }

    public int getCodePostaleRefuge() {
        return codePostaleRefuge;
    }

    public void setCodePostaleRefuge(int codePostaleRefuge) {
        this.codePostaleRefuge = codePostaleRefuge;
    }

    public String getGouvernementRefuge() {
        return gouvernementRefuge;
    }

    public void setGouvernementRefuge(String gouvernementRefuge) {
        this.gouvernementRefuge = gouvernementRefuge;
    }

    public String getPhotoRefuge() {
        return photoRefuge;
    }

    public void setPhotoRefuge(String photoRefuge) {
        this.photoRefuge = photoRefuge;
    }

    public String getChat() {
        return chat;
    }

    public void setChat(String chat) {
        this.chat = chat;
    }

    public String getChien() {
        return chien;
    }

    public void setChien(String chien) {
        this.chien = chien;
    }

    public String getRongeur() {
        return rongeur;
    }

    public void setRongeur(String rongeur) {
        this.rongeur = rongeur;
    }

    public String getAutre() {
        return autre;
    }

    public void setAutre(String autre) {
        this.autre = autre;
    }
    
}
