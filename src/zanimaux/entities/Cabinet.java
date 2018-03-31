/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package zanimaux.entities;

/**
 *
 * @author Mariam
 */
public class Cabinet {
    private  String immatriculeCabinet;
    private String emailCabinet;
    private String cin;

    public String getCin() {
        return cin;
    }

    public Cabinet(String immatriculeCabinet, String emailCabinet, String cin, int telephoneCabinet, int FaxCabinet, String AdresseCabinet, String VilleCabinet, int CodePostaleCabinet, String photovet) {
        this.immatriculeCabinet = immatriculeCabinet;
        this.emailCabinet = emailCabinet;
        this.cin = cin;
        this.telephoneCabinet = telephoneCabinet;
        this.FaxCabinet = FaxCabinet;
        this.AdresseCabinet = AdresseCabinet;
        this.VilleCabinet = VilleCabinet;
        this.CodePostaleCabinet = CodePostaleCabinet;
        this.photovet = photovet;
    }

    public void setCin(String cin) {
        this.cin = cin;
    }
    private int telephoneCabinet;
    private int FaxCabinet;
     private String AdresseCabinet;
     private String VilleCabinet;
     private int CodePostaleCabinet;
      private String photovet;

    public String getImmatriculeCabinet() {
        return immatriculeCabinet;
    }

    public void setImmatriculeCabinet(String immatriculeCabinet) {
        this.immatriculeCabinet = immatriculeCabinet;
    }

    public String getEmailCabinet() {
        return emailCabinet;
    }

    public void setEmailCabinet(String emailCabinet) {
        this.emailCabinet = emailCabinet;
    }

    public int getTelephoneCabinet() {
        return telephoneCabinet;
    }

    public void setTelephoneCabinet(int telephoneCabinet) {
        this.telephoneCabinet = telephoneCabinet;
    }

    public int getFaxCabinet() {
        return FaxCabinet;
    }

    public void setFaxCabinet(int FaxCabinet) {
        this.FaxCabinet = FaxCabinet;
    }

    public String getAdresseCabinet() {
        return AdresseCabinet;
    }

    public void setAdresseCabinet(String AdresseCabinet) {
        this.AdresseCabinet = AdresseCabinet;
    }

    public String getVilleCabinet() {
        return VilleCabinet;
    }

    public void setVilleCabinet(String VilleCabinet) {
        this.VilleCabinet = VilleCabinet;
    }

    public int getCodePostaleCabinet() {
        return CodePostaleCabinet;
    }

    public void setCodePostaleCabinet(int CodePostaleCabinet) {
        this.CodePostaleCabinet = CodePostaleCabinet;
    }

  

    public String getPhotovet() {
        return photovet;
    }

    public void setPhotovet(String photovet) {
        this.photovet = photovet;
    }

    public Cabinet() {
    }

    public Cabinet(String immatriculeCabinet, String emailCabinet, int telephoneCabinet, int FaxCabinet, String AdresseCabinet, String VilleCabinet, int CodePostaleCabinet, String photovet) {
        this.immatriculeCabinet = immatriculeCabinet;
        this.emailCabinet = emailCabinet;
        this.telephoneCabinet = telephoneCabinet;
        this.FaxCabinet = FaxCabinet;
        this.AdresseCabinet = AdresseCabinet;
        this.VilleCabinet = VilleCabinet;
        this.CodePostaleCabinet = CodePostaleCabinet;
       
        this.photovet = photovet;
    }

    
     
    
    
    
}
