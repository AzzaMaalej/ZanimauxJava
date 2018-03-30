/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package zanimaux.entities;

import zanimaux.entities.User;

/**
 *
 * @author Mariam
 */
public class Articles {
    private int id;
    private String description;
    private String titre;
    private String cin;

    
    private String piecejointe;

    public int getId() {
        return id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getTitre() {
        return titre;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    public String getCin() {
        return cin;
    }

    public void setCin(String cin) {
        this.cin = cin;
    }

    public String getPiecejointe() {
        return piecejointe;
    }

    public void setPiecejointe(String piecejointe) {
        this.piecejointe = piecejointe;
    }

    public Articles() {
    }

    public Articles(int id, String description, String titre, String cin, String piecejointe) {
        this.id = id;
        this.description = description;
        this.titre = titre;
        this.cin = cin;
        this.piecejointe = piecejointe;
    }

   

    

   
}
