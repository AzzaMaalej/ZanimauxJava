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
public class Questionnaire_chien {
     private int id;
    private int race;
    private String tolererChien ;
    private String calme;
    private String affectueux;
    private String chutePoils;
    private String intelligent;
    private String tolererChat;

    public Questionnaire_chien() {
    }

    public Questionnaire_chien(int id, int race, String tolererChien, String calme, String affectueux, String chutePoils, String intelligent, String tolererChat) {
        this.id = id;
        this.race = race;
        this.tolererChien = tolererChien;
        this.calme = calme;
        this.affectueux = affectueux;
        this.chutePoils = chutePoils;
        this.intelligent = intelligent;
        this.tolererChat = tolererChat;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getRace() {
        return race;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 11 * hash + this.id;
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
        final Questionnaire_chien other = (Questionnaire_chien) obj;
        if (this.id != other.id) {
            return false;
        }
        return true;
    }

    public void setRace(int race) {
        this.race = race;
    }

    public String getTolererChien() {
        return tolererChien;
    }

    public void setTolererChien(String tolererChien) {
        this.tolererChien = tolererChien;
    }

    public String getCalme() {
        return calme;
    }

    public void setCalme(String calme) {
        this.calme = calme;
    }

    public String getAffectueux() {
        return affectueux;
    }

    public void setAffectueux(String affectueux) {
        this.affectueux = affectueux;
    }

    public String getChutePoils() {
        return chutePoils;
    }

    public void setChutePoils(String chutePoils) {
        this.chutePoils = chutePoils;
    }

    public String getIntelligent() {
        return intelligent;
    }

    public void setIntelligent(String intelligent) {
        this.intelligent = intelligent;
    }

    public String getTolererChat() {
        return tolererChat;
    }

    public void setTolererChat(String tolererChat) {
        this.tolererChat = tolererChat;
    }

    @Override
    public String toString() {
        return "Questionnaire_chien{" + "id=" + id + ", race=" + race + ", tolererChien=" + tolererChien + ", calme=" + calme + ", affectueux=" + affectueux + ", chutePoils=" + chutePoils + ", intelligent=" + intelligent + ", tolererChat=" + tolererChat + '}';
    }

    
    
}
