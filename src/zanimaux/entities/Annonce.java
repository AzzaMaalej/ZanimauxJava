/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package zanimaux.entities;

import java.util.Objects;

/**
 *
 * @author Maroua
 */
public class Annonce {
    private int idAnnonce;
    private String cinUser;
    private String type;
    private String titre;
    private String description;
    private String pieceJointe;

    public Annonce(int idAnnonce, String cinUser, String type, String titre, String description, String pieceJointe) {
        this.idAnnonce = idAnnonce;
        this.cinUser = cinUser;
        this.type = type;
        this.titre = titre;
        this.description = description;
        this.pieceJointe = pieceJointe;
    }

    public Annonce(String cinUser,String type, String titre, String description, String pieceJointe) {
        this.cinUser = cinUser;
        this.type = type;
        this.titre = titre;
        this.description = description;
        this.pieceJointe = pieceJointe;
    }

    public Annonce() {
    }

   
    public int getIdAnnonce() {
        return idAnnonce;
    }

    public void setIdAnnonce(int idAnnonce) {
        this.idAnnonce = idAnnonce;
    }

    public String getCinUser() {
        return cinUser;
    }

    public void setCinUser(String cinUser) {
        this.cinUser = cinUser;
    }

  

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getTitre() {
        return titre;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPieceJointe() {
        return pieceJointe;
    }

    public void setPieceJointe(String pieceJointe) {
        this.pieceJointe = pieceJointe;
    }

    @Override
    public String toString() {
        return "Annonce{" + "idAnnonce=" + idAnnonce + ", cinUser=" + cinUser + ", type=" + type + ", titre=" + titre + ", description=" + description + ", pieceJointe=" + pieceJointe + '}';
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 29 * hash + this.idAnnonce;
        hash = 29 * hash + Objects.hashCode(this.cinUser);
        hash = 29 * hash + Objects.hashCode(this.type);
        hash = 29 * hash + Objects.hashCode(this.titre);
        hash = 29 * hash + Objects.hashCode(this.description);
        hash = 29 * hash + Objects.hashCode(this.pieceJointe);
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
        final Annonce other = (Annonce) obj;
        if (this.idAnnonce != other.idAnnonce) {
            return false;
        }
        if (!Objects.equals(this.type, other.type)) {
            return false;
        }
        if (!Objects.equals(this.titre, other.titre)) {
            return false;
        }
        if (!Objects.equals(this.description, other.description)) {
            return false;
        }
        if (!Objects.equals(this.pieceJointe, other.pieceJointe)) {
            return false;
        }
        if (!Objects.equals(this.cinUser, other.cinUser)) {
            return false;
        }
        return true;
    }
    
    

  
    
    
    
    
    
}
