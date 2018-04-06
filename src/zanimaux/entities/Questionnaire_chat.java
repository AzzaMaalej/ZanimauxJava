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
public class Questionnaire_chat {
     private int id;
    private int race;
    private int tolererChien ;
    private int dynamique;
    private int affectueux;
    private int chutePoils;
    private int intelligent;
    private int acceptationEtranger;

    public Questionnaire_chat(int race, int tolererChien, int dynamique, int affectueux, int chutePoils, int intelligent, int acceptationEtranger) {
        this.race = race;
        this.tolererChien = tolererChien;
        this.dynamique = dynamique;
        this.affectueux = affectueux;
        this.chutePoils = chutePoils;
        this.intelligent = intelligent;
        this.acceptationEtranger = acceptationEtranger;
    }

    public Questionnaire_chat() {
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

    public int getDynamique() {
        return dynamique;
    }

    public void setDynamique(int dynamique) {
        this.dynamique = dynamique;
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

    public int getAcceptationEtranger() {
        return acceptationEtranger;
    }

    public void setAcceptationEtranger(int acceptationEtranger) {
        this.acceptationEtranger = acceptationEtranger;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 41 * hash + this.id;
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
        final Questionnaire_chat other = (Questionnaire_chat) obj;
        if (this.id != other.id) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Questionnaire_chat{" + "id=" + id + ", race=" + race + ", tolererChien=" + tolererChien + ", dynamique=" + dynamique + ", affectueux=" + affectueux + ", chutePoils=" + chutePoils + ", intelligent=" + intelligent + ", acceptationEtranger=" + acceptationEtranger + '}';
    }
    
}
