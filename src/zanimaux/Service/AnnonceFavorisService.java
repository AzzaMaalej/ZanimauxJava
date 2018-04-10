/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package zanimaux.Service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import zanimaux.Technique.DataSource;
import zanimaux.entities.Annonce;
import zanimaux.entities.AnnonceFavoris;
import zanimaux.entities.User;
import zanimaux.util.Session;

/**
 *
 * @author Maroua
 */
public class AnnonceFavorisService {
     public Connection con = DataSource.getInstance().getCon();
     public Statement ste;

    public AnnonceFavorisService() throws SQLException {
        ste=con.createStatement();    }
    
    
     public boolean existf(int i)
    { 
         User usr = Session.getLoggedInUser();
         String idUser = usr.getCin();
         ResultSet rs =null;
         boolean v = false;
        try {  
            String requete = "SELECT * FROM annonce_favoris WHERE `idA`="+i+" and `cin` ='"+idUser+"'";

           
             rs = ste.executeQuery(requete);

             while(rs.next()){
                v=true;              
            }
             
        } catch (SQLException ex) {
            Logger.getLogger(AnnonceFavorisService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return v;
    
    }
      
        
     public boolean ajouterFavoris(AnnonceFavoris f)
    {
       String requete = "INSERT INTO annonce_favoris (cin,idA) VALUES (?,?) ";
        User usr = Session.getLoggedInUser();
       
       try {
          
            PreparedStatement pst = con.prepareStatement(requete);
            pst.setString(1,usr.getCin());
            pst.setInt(2,f.getIdA());
          
            
            pst.executeUpdate();
            System.out.println("ajouté avec succés");
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
            return false;
    }
     
     public void supprimerFavoris(int id)
         {
                User usr = Session.getLoggedInUser();
             String requete="DELETE FROM `annonce_favoris` WHERE idA="+id+" and cin='"+usr.getCin()+"' ";     
             Statement st;
             try {
              st = con.createStatement(); 
              st.executeUpdate(requete);
              System.out.println("Supprimé avec succés");

            } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
     }
     
      public Annonce mesAnnonceFavoris(String cin)
    { 

        Annonce listeAnnonce = new Annonce();
        try {  
            String requete = "SELECT * FROM annonce WHERE `idAnnonce`in (SELECT idA from annonce_favoris where cin= '"+cin+"')";

           
            ResultSet rs = ste.executeQuery(requete);

             while(rs.next()){
                 listeAnnonce.setType(rs.getString("type"));
                 listeAnnonce.setTitre(rs.getString("titre"));
                 listeAnnonce.setDescription(rs.getString("description"));
                 
                 listeAnnonce.setPieceJointe(rs.getString("photoAnimal"));
            }
             
        } catch (SQLException ex) {
            Logger.getLogger(AnnonceFavorisService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return listeAnnonce;
    
    }
    

    
}
