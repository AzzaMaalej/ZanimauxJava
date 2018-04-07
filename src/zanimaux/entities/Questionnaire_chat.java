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
    private String tolererChien ;
    private String dynamique;
    private String affectueux;
    private String chutePoils;
    private String intelligent;
    private String acceptationEtranger;

    public Questionnaire_chat() {
    }

    public Questionnaire_chat(int id, int race, String tolererChien, String dynamique, String affectueux, String chutePoils, String intelligent, String acceptationEtranger) {
        this.id = id;
        this.race = race;
        this.tolererChien = tolererChien;
        this.dynamique = dynamique;
        this.affectueux = affectueux;
        this.chutePoils = chutePoils;
        this.intelligent = intelligent;
        this.acceptationEtranger = acceptationEtranger;
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

    public void setRace(int race) {
        this.race = race;
    }

    public String getTolererChien() {
        return tolererChien;
    }

    public void setTolererChien(String tolererChien) {
        this.tolererChien = tolererChien;
    }

    public String getDynamique() {
        return dynamique;
    }

    public void setDynamique(String dynamique) {
        this.dynamique = dynamique;
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

    public String getAcceptationEtranger() {
        return acceptationEtranger;
    }

    public void setAcceptationEtranger(String acceptationEtranger) {
        this.acceptationEtranger = acceptationEtranger;
    }

    @Override
    public String toString() {
        return "Questionnaire_chat{" + "id=" + id + ", race=" + race + ", tolererChien=" + tolererChien + ", dynamique=" + dynamique + ", affectueux=" + affectueux + ", chutePoils=" + chutePoils + ", intelligent=" + intelligent + ", acceptationEtranger=" + acceptationEtranger + '}';
    }

    @Override
    public int hashCode() {
        int hash = 3;
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
        final Questionnaire_chat other = (Questionnaire_chat) obj;
        if (this.id != other.id) {
            return false;
        }
        return true;
    }

   
}
