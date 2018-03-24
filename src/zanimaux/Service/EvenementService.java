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
import java.sql.Statement;

/**
 *
 * @author Maroua
 */
public class EvenementService {
    public Connection con = DataSource.getInstance().getCon();
    
     public boolean ajouterEvenement(Evenement e)
    {
       String requete = "INSERT INTO Evenement (cinUser,lieu,dateDebut, dateFin, type, titre, description,nbPlace,imageEvt) VALUES (?,?,?,?,?,?,?,?,?) ";
       
       try {
            PreparedStatement pst = con.prepareStatement(requete);
            pst.setObject(1,e.getCinUser());
            pst.setString(2,e.getLieu());
            pst.setDate(3,e.getDateDebut());
            pst.setDate(4,e.getDateFin());
            pst.setString(5,e.getTitre());
            pst.setString(6,e.getDescription());
            pst.setInt(7,e.getNbPlace());
            pst.setString(8,null);
            
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
            pst.setDate(2,e.getDateDebut());
            pst.setDate(3,e.getDateFin());
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
