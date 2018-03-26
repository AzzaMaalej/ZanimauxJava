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
import zanimaux.entities.Parc;

/**
 *
 * @author BelhassenLimam
 */
public class ParcService {
     public Connection con = DataSource.getInstance().getCon();
    
     public boolean ajouterParc(Parc p)
    {
       String requete = "INSERT INTO parc (nomParc,CategorieDressage,adresseParc, villeParc, codePostaleParc, photoParc, cinUser) VALUES (?,?,?,?,?,?,?) ";
       
       try {
            PreparedStatement pst = con.prepareStatement(requete);
            pst.setString(1,p.getNomParc());
            pst.setString(2,p.getCategorieDressage());
            pst.setString(3,p.getAdresseParc());
            pst.setString(4,p.getVilleParc());
            pst.setInt(5,p.getCodePostaleParc());
            pst.setString(6,p.getPhotoParc());
            pst.setObject(7,p.getCinUser());
            pst.setString(8,null);
            
            pst.executeUpdate();
            System.out.println("ajouté avec succés");
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
            return false;
    }
     
     
      public boolean modifierParc(int id,Parc p)
    { 
    String requete="UPDATE parc SET nomParc=?, CategorieDressage=?, adresseParc=?, villeParc=? , codePostaleParc=? , photoParc=? where idParc='"+id+"'";
        try {
            PreparedStatement pst =con.prepareStatement(requete);
            pst.setString(1,p.getNomParc());
            pst.setString(2,p.getCategorieDressage());
            pst.setString(3,p.getAdresseParc());
            pst.setString(4,p.getVilleParc());
            pst.setInt(5,p.getCodePostaleParc()); 
            pst.setString(6,p.getPhotoParc());
            
             pst.executeUpdate();
            System.out.println("modifié avec succés");
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
            return false;
    }
     
     public void supprimerParc(int id)
         {
                
             String requete="DELETE FROM `parc` WHERE idParc='"+id+"' ";     
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
