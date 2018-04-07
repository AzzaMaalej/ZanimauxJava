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
    private int tolererChien ;
    private int calme;
    private int affectueux;
    private int chutePoils;
    private int intelligent;
    private int tolererChat;

    public Questionnaire_chien(int race, int tolererChien, int calme, int affectueux, int chutePoils, int intelligent, int tolererChat) {
        this.race = race;
        this.tolererChien = tolererChien;
        this.calme = calme;
        this.affectueux = affectueux;
        this.chutePoils = chutePoils;
        this.intelligent = intelligent;
        this.tolererChat = tolererChat;
    }

    public Questionnaire_chien() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public int getId() {
        return id;
    }

   

    public int getRace() {
        return race;
    }

    public void setRace(int race) {
        this.race = race;
    }

    public int getTolererChien() {
        return tolererChien;
    }

    public void setTolererChien(int tolererChien) {
        this.tolererChien = tolererChien;
    }

    public int getCalme() {
        return calme;
    }

    public void setCalme(int calme) {
        this.calme = calme;
    }

    public int getAffectueux() {
        return affectueux;
    }

    public void setAffectueux(int affectueux) {
        this.affectueux = affectueux;
    }

    public int getChutePoils() {
        return chutePoils;
    }

    public void setChutePoils(int chutePoils) {
        this.chutePoils = chutePoils;
    }

    public int getIntelligent() {
        return intelligent;
    }

    public void setIntelligent(int intelligent) {
        this.intelligent = intelligent;
    }

    public int getTolererChat() {
        return tolererChat;
    }

    public void setTolererChat(int tolererChat) {
        this.tolererChat = tolererChat;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 23 * hash + this.id;
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

    @Override
    public String toString() {
        return "Questionnaire_chien{" + "id=" + id + ", race=" + race + ", tolererChien=" + tolererChien + ", calme=" + calme + ", affectueux=" + affectueux + ", chutePoils=" + chutePoils + ", intelligent=" + intelligent + ", tolererChat=" + tolererChat + '}';
    }
    
}
