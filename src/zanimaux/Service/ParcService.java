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
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import zanimaux.Technique.DataSource;
import zanimaux.entities.Parc;
import zanimaux.entities.User;
import zanimaux.util.Session;

/**
 *
 * @author Azza
 */
public class ParcService {
     public Connection con = DataSource.getInstance().getCon();
     public Statement ste;
     public ParcService() throws SQLException 
    {
        ste=con.createStatement();
    }
     
   public void ajouterParc(Parc g) throws SQLException {
       String req="INSERT INTO parc (id,nomParc,CategorieDressage, adresseParc ,villeParc ,codePostaleParc ,photoParc,cinDresseur) VALUES (?,?,?,?,?,?,?,?)";
        PreparedStatement pre= con.prepareStatement(req);
        
        pre.setString(1, g.getId());
        pre.setString(2, g.getNomParc());
        pre.setString(3, g.getCategorieDressage());
        pre.setString(4, g.getAdresseParc());
        pre.setString(5, g.getVilleParc());
        pre.setInt(6, g.getCodePostaleParc());
        pre.setString(7, g.getPhotoParc());
        pre.setString(8, g.getCinDresseur());
        pre.executeUpdate();   
    }
   
   public Parc AfficherParc(int i)
    { 

        Parc listForm = new Parc();
        try {  
            String requete = "SELECT * FROM parc WHERE `id`="+i;

           
            ResultSet rs = ste.executeQuery(requete);

             while(rs.next()){
                 listForm.setId(rs.getString("id"));
                 listForm.setNomParc(rs.getString("nomParc"));
                 listForm.setCategorieDressage(rs.getString("CategorieDressage"));
                 listForm.setAdresseParc(rs.getString("adresseParc"));
                 listForm.setVilleParc(rs.getString("villeParc"));
                 listForm.setCodePostaleParc(rs.getInt("codePostaleParc"));
                 listForm.setPhotoParc(rs.getString("photoParc"));
                 listForm.setCinDresseur(rs.getString("cinDresseur"));             
            }
             
        } catch (SQLException ex) {
            Logger.getLogger(ParcService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return listForm;
    
    }
   
   
   
   public ResultSet AfficherParc()
    { 
        ResultSet rs=null;
        try {  
            String requete = "SELECT * FROM parc" ;
            rs = ste.executeQuery(requete);
             }catch (SQLException ex) {
                 System.out.println(" erreur AfficherParc()");
        }
        return rs ;
    
    }
   public void supprimerParc(int id)
         {
                
             String requete="DELETE FROM `parc` WHERE id='"+id+"' ";     
             Statement st;
             try {
              st = con.createStatement(); 
              st.executeUpdate(requete);
              System.out.println("Parc supprimé");

            } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
}

    public void supprimerParc(String b) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
