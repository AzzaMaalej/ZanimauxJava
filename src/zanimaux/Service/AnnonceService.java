/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package zanimaux.Service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import zanimaux.Technique.DataSource;
import zanimaux.entities.Annonce;
import zanimaux.entities.User;
import zanimaux.util.Session;

/**
 *
 * @author Maroua
 */
public class AnnonceService {
    
    public Connection con = DataSource.getInstance().getCon();
    
     public boolean ajouterAnnonce(Annonce a)
    {
       String requete = "INSERT INTO Annonce (cin,type,titre, description, photoAnimal) VALUES (?,?,?,?,?) ";
        User usr = Session.getLoggedInUser();
       
       try {
            PreparedStatement pst = con.prepareStatement(requete);
            pst.setObject(1,usr.getCin());
            pst.setString(2,a.getType());
            pst.setString(3,a.getTitre());
            pst.setString(4,a.getDescription());
            pst.setString(5,a.getPieceJointe());
            pst.executeUpdate();
            System.out.println("ajouté avec succés");
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
            return false;
    }
     
     
     public boolean modifierAnnonce(int id,Annonce a)
    { 
    String requete="UPDATE Annonce SET type=?, titre=?, description=?, pieceJointe=? where idAnnonce='"+id+"'";
        try {
            PreparedStatement pst =con.prepareStatement(requete);
            pst.setString(1,a.getType());
            pst.setString(2,a.getTitre());
            pst.setString(3,a.getDescription());
            pst.setString(4,a.getPieceJointe());
            pst.executeUpdate();
            System.out.println("modifié avec succés");
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
            return false;
    }
     
     
      public void supprimerAnnonce(int id)
         {
                
             String requete="DELETE FROM `Annonce` WHERE idAnnonce='"+id+"' ";     
             Statement st;
             try {
              st = con.createStatement(); 
              st.executeUpdate(requete);
              System.out.println("Supprimé avec succés");

            } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
     }
    
}
