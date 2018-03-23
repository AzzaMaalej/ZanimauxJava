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
    private User user;

    public String getPiecejointe() {
        return piecejointe;
    }

    public void setPiecejointe(String piecejointe) {
        this.piecejointe = piecejointe;
    }
    private String piecejointe;

    @Override
    public String toString() {
        return "Articles{" + "id=" + id+ ", description=" + description + ", titre=" + titre + ", user=" + user + ", piecejointe=" + piecejointe + '}';
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 97 * hash + this.id;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Articles other = (Articles) obj;
        if (this.id != other.id) {
            return false;
        }
        return true;
    }

    public String getTitre() {
        return titre;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    public static Articles getArticles() {
        return articles;
    }

    public static void setArticles(Articles articles) {
        Articles.articles = articles;
    }


    
    private static Articles articles;

    public Articles() {
    }

    public Articles(int id, String description, String titre, String piecejointe) {
        this.id= id;
        this.description = description;
        this.titre = titre;
        this.piecejointe = piecejointe;
    }

    public Articles(String description, String titre) {
        this.description = description;
        this.titre = titre;
    }
    
   
    
    

    public int getId() {
        return id;
    }

    public User getUser() {
        return user;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setUser(User user) {
        this.user = user;
    }
    


    public String getDescription() {
        return description;
    }

    //public int getFk_role() {
     //   return fk_role;
   // }

   

    public void setDescription(String description) {
        this.description = description;
    }

    //public void setFk_role(int fk_role) {
   //     this.fk_role = fk_role;
    //}

  
    

   
}
