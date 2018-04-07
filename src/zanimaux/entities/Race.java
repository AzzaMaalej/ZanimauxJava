/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package zanimaux.entities;

import java.util.Objects;


/**
 *
 * @author Azza
 */
public class Race {
     private int id;
    private String race;
    private String informations;
    private String photo;
    private String type;

    public Race(String race, String informations, String photo, String type) {
        this.race = race;
        this.informations = informations;
        this.photo = photo;
        this.type = type;
    }

    public Race() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }


    public String getRace() {
        return race;
    }

    public void setRace(String race) {
        this.race = race;
    }

    public String getInformations() {
        return informations;
    }

    public void setInformations(String informations) {
        this.informations = informations;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 17 * hash + this.id;
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
        final Race other = (Race) obj;
        if (this.id != other.id) {
            return false;
        }
        if (!Objects.equals(this.race, other.race)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Race{" + "id=" + id + ", race=" + race + ", informations=" + informations + ", type=" + type + '}';
    }
    
}
