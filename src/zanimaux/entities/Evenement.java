/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package zanimaux.entities;


import java.time.LocalDate;
import java.util.Objects;
import java.util.Date;
import zanimaux.util.Session;

/**
 *
 * @author Maroua
 */
public class Evenement {
    private int idEvt;
    private String cinUser;
    private String lieu;
    private Date dateDebut;
    private Date dateFin;
    private String type;
    private String titre;
    private String description;
    private String imageEvt;
    private int nbPlace=0;
    private int nbParticipants=0;

    public Evenement(int idEvt, String cinUser, String lieu, Date dateDebut, Date dateFin, String type, String titre, String description, String imageEvt,int nbPlace) {
        this.idEvt = idEvt;
        this.cinUser = cinUser;
        this.lieu = lieu;
        this.dateDebut = dateDebut;
        this.dateFin = dateFin;
        this.type = type;
        this.titre = titre;
        this.description = description;
        this.imageEvt = imageEvt;
        this.nbPlace = nbPlace;
    }

    public Evenement(String lieu, Date dateDebut, Date dateFin, String type, String titre, String description, int nbPlace,String imageEvt) {
        this.lieu = lieu;
        this.dateDebut = dateDebut;
        this.dateFin = dateFin;
        this.type = type;
        this.titre = titre;
        this.description = description;
        this.nbPlace = nbPlace;
        this.imageEvt = imageEvt;
    }

    public Evenement() {
    }

<<<<<<< HEAD
   

    public Evenement(String lieu, Date dateDebut, Date dateFin, String type, String titre, String description,int nbPlace,String imageEvt,String cinUser) {
        
        this.lieu = lieu;
        this.dateDebut = dateDebut;
        this.dateFin = dateFin;
        this.type = type;
        this.titre = titre;
        this.description = description;
        this.nbPlace=nbPlace;
        this.imageEvt = imageEvt;
        this.cinUser = cinUser;
    }

=======
>>>>>>> a8f6f2541548020a1d5dc8a2987883cd2302c88d
    

 

  
    

    public int getIdEvt() {
        return idEvt;
    }

    public void setIdEvt(int idEvt) {
        this.idEvt = idEvt;
    }

    public String getCinUser() {
        return cinUser;
    }

    public void setCinUser(String cinUser) {
        this.cinUser = cinUser;
    }

    public String getLieu() {
        return lieu;
    }

    public void setLieu(String lieu) {
        this.lieu = lieu;
    }

    public Date getDateDebut() {
        return dateDebut;
    }

    public void setDateDebut(Date dateDebut) {
        this.dateDebut = dateDebut;
    }

    public Date getDateFin() {
        return dateFin;
    }

    public void setDateFin(Date dateFin) {
        this.dateFin = dateFin;
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

    public String getImageEvt() {
        return imageEvt;
    }

    public void setImageEvt(String imageEvt) {
        this.imageEvt = imageEvt;
    }

    public int getNbPlace() {
        return nbPlace;
    }

    public void setNbPlace(int nbPlace) {
        this.nbPlace = nbPlace;
    }

    public int getNbParticipants() {
        return nbParticipants;
    }

    public void setNbParticipants(int nbParticipants) {
        this.nbParticipants = nbParticipants;
    }

    @Override
    public String toString() {
        return "Evenement{" + "idEvt=" + idEvt + ", cinUser=" + cinUser + ", lieu=" + lieu + ", dateDebut=" + dateDebut + ", dateFin=" + dateFin + ", type=" + type + ", titre=" + titre + ", description=" + description + ", imageEvt=" + imageEvt + ", nbPlace=" + nbPlace + ", nbParticipants=" + nbParticipants + '}';
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 11 * hash + this.idEvt;
        hash = 11 * hash + Objects.hashCode(this.cinUser);
        hash = 11 * hash + Objects.hashCode(this.lieu);
        hash = 11 * hash + Objects.hashCode(this.dateDebut);
        hash = 11 * hash + Objects.hashCode(this.dateFin);
        hash = 11 * hash + Objects.hashCode(this.type);
        hash = 11 * hash + Objects.hashCode(this.titre);
        hash = 11 * hash + Objects.hashCode(this.description);
        hash = 11 * hash + Objects.hashCode(this.imageEvt);
        hash = 11 * hash + this.nbPlace;
        hash = 11 * hash + this.nbParticipants;
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
        final Evenement other = (Evenement) obj;
        if (this.idEvt != other.idEvt) {
            return false;
        }
        if (this.nbPlace != other.nbPlace) {
            return false;
        }
        if (this.nbParticipants != other.nbParticipants) {
            return false;
        }
        if (!Objects.equals(this.lieu, other.lieu)) {
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
        if (!Objects.equals(this.imageEvt, other.imageEvt)) {
            return false;
        }
        if (!Objects.equals(this.cinUser, other.cinUser)) {
            return false;
        }
        if (!Objects.equals(this.dateDebut, other.dateDebut)) {
            return false;
        }
        if (!Objects.equals(this.dateFin, other.dateFin)) {
            return false;
        }
        return true;
    }
    
    
  



}