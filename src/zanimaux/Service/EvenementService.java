/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package zanimaux.Service;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import zanimaux.entities.Evenement;
import zanimaux.Technique.DataSource;
import java.sql.Connection;
import java.sql.Date;
import java.sql.Statement;
import zanimaux.entities.User;
import zanimaux.util.Session;

/**
 *
 * @author Maroua
 */
public class EvenementService {
    public Connection con = DataSource.getInstance().getCon();
      
     public boolean ajouterEvenement(Evenement e)
    {
       String requete = "INSERT INTO Evenement (cin,lieu,dateDebut, dateFin, type, titre, description,nb_place,image_evt) VALUES (?,?,?,?,?,?,?,?,?) ";
        User usr = Session.getLoggedInUser();
       
       try {
           java.util.Date dateDeb = new Date(e.getDateDebut().getTime());
           java.util.Date dateFin = new Date(e.getDateFin().getTime());
            PreparedStatement pst = con.prepareStatement(requete);
            pst.setObject(1,usr.getCin());
            pst.setString(2,e.getLieu());
            pst.setDate(3, (Date) dateDeb);
            pst.setDate(4, (Date) dateFin);
             pst.setString(5,e.getType());
            pst.setString(6,e.getTitre());
            pst.setString(7,e.getDescription());
            pst.setInt(8,e.getNbPlace());
            pst.setString(9,e.getImageEvt());
            
            pst.executeUpdate();
            System.out.println("ajouté avec succés");
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
            return false;
    }
     
     
     public boolean modifierEvenement(int id,Evenement e)
    { 
    String requete="UPDATE Evenement SET lieu=?, dateDebut=?, dateFin=?, type=? , titre=? , description=?,imageEvt=?, nbPlace=? where idEvt='"+id+"'";
        try {
            PreparedStatement pst =con.prepareStatement(requete);
            pst.setString(1,e.getLieu());
            pst.setDate(2, (Date) e.getDateDebut());
            pst.setDate(3, (Date) e.getDateFin());
            pst.setString(4,e.getType());
            pst.setString(5,e.getTitre()); 
            pst.setString(6,e.getDescription());
            pst.setString(7,e.getImageEvt());
            pst.setInt(8,e.getNbPlace());
             pst.executeUpdate();
            System.out.println("modifié avec succés");
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
            return false;
    }
     
     
      public void supprimerEvenement(int id)
         {
                
             String requete="DELETE FROM `Evenement` WHERE idEvt='"+id+"' ";     
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
