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
public class Animal {
      private String refuge;
    private int idAnimal;
    private String type;
    private String etat;
    private String nomAnimal;
    private String photoAnimal;
    private int age;
    private String race;

    public Animal(String refuge, String type, String etat, String nomAnimal, String photoAnimal, int age, String race) {
        this.refuge = refuge;
        this.type = type;
        this.etat = etat;
        this.nomAnimal = nomAnimal;
        this.photoAnimal = photoAnimal;
        this.age = age;
        this.race = race;
    }

    public Animal() {
    }

    public String getRefuge() {
        return refuge;
    }

    public void setRefuge(String refuge) {
        this.refuge = refuge;
    }

    public int getIdAnimal() {
        return idAnimal;
    }
   

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getEtat() {
        return etat;
    }

    public void setEtat(String etat) {
        this.etat = etat;
    }

    public String getNomAnimal() {
        return nomAnimal;
    }

    public void setIdAnimal(int idAnimal) {
        this.idAnimal = idAnimal;
    }

    public void setNomAnimal(String nomAnimal) {
        this.nomAnimal = nomAnimal;
    }

    public String getPhotoAnimal() {
        return photoAnimal;
    }

    public void setPhotoAnimal(String photoAnimal) {
        this.photoAnimal = photoAnimal;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getRace() {
        return race;
    }

    public void setRace(String race) {
        this.race = race;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 61 * hash + this.idAnimal;
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
        final Animal other = (Animal) obj;
        if (this.idAnimal != other.idAnimal) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Animal{" + "refuge=" + refuge + ", type=" + type + ", etat=" + etat + ", nomAnimal=" + nomAnimal + ", photoAnimal=" + photoAnimal + ", age=" + age + ", race=" + race + '}';
    }
    
    
}
