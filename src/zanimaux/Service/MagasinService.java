/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package zanimaux.Service;

import zanimaux.entities.Magasin;
import zanimaux.entities.Produit;
import zanimaux.entities.User;
import zanimaux.Technique.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import zanimaux.entities.Articles;
import zanimaux.util.Session;

/**
 *
 * @author macbookpro
 */
public class MagasinService {
    public Connection con = DataSource.getInstance().getCon();
    public Statement ste;
    
    public MagasinService() throws SQLException 
    {
        ste=con.createStatement();
    }
    
    public boolean ajouterMagasin(Magasin m)
    {
       String requete = "INSERT INTO Magasin (numRC,nomMagasin,adresseMagasin, codePostaleMagasin, photoMagasin, cinProprietaireMagasin) VALUES (?,?,?,?,?,) ";
       User u= Session.getLoggedInUser();
       try {
            PreparedStatement pst =con.prepareStatement(requete);
            pst.setString(1,m.getNumRC());
            pst.setString(2,m.getNomMagasin());
            pst.setString(3,m.getAdresseMagasin());
            pst.setInt(4,m.getCodePostaleMagasin());
            pst.setString(5,m.getPhotoMagasin());
            pst.setString(6, u.getCin());
            
            pst.executeUpdate();
            System.out.println("ajout reussit");
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
            return false;
    }
    
    
    public boolean modifMagasin(int i,Magasin m)
    { 
    String requete="UPDATE Magasin SET nomMagasin=?, adresseMagasin=?, codePostaleMagasin=?, photoMagasin=? where `idMagasin`="+i;
        try {
            PreparedStatement pst =con.prepareStatement(requete);
            pst.setString(1,m.getNomMagasin());
            pst.setString(2,m.getAdresseMagasin());
            pst.setInt(3,m.getCodePostaleMagasin());
            pst.setString(4,m.getPhotoMagasin());            
             pst.executeUpdate();
            System.out.println("modification reussite");
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
            return false;
    }
    
    public Magasin rechercheMagasin(int i)
    { 

        Magasin listForm = new Magasin();
        try {  
            String requete = "SELECT * FROM Magasin WHERE `idMagasin`="+i;

           
            ResultSet rs = ste.executeQuery(requete);

             while(rs.next()){
                 listForm.setNumRC(rs.getString("numRC"));
                 listForm.setNomMagasin(rs.getString("nomMagasin"));
                 listForm.setAdresseMagasin(rs.getString("adresseMagasin"));
                 listForm.setCodePostaleMagasin(rs.getInt("codePostaleMagasin"));
                 listForm.setPhotoMagasin(rs.getString("photoMagasin"));
                 listForm.setCinProprietaireMagasin(rs.getString("cinProprietaireMagasin"));
                 listForm.setBestSellerMagasin(rs.getInt("bestSellerMagasin"));              
            }
             
        } catch (SQLException ex) {
            Logger.getLogger(Articleservice.class.getName()).log(Level.SEVERE, null, ex);
        }
        return listForm;
    
    }
    
    
      public List<Magasin> Magasinier(String cin)
   {
        List<Magasin> listMagasins = new ArrayList<>();
        try {
            String requete = "select * from Magasin where cinProprietaireMagasin='"+cin+"'";
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(requete);
            
            while(rs.next()){
                Magasin m= new Magasin();
                m.setNumRC(rs.getString("numRC"));
                m.setNomMagasin(rs.getString("nomMagasin"));
                m.setNbProduit(rs.getInt("nbProduit"));
                m.setAdresseMagasin(rs.getString("adresseMagasin"));
                m.setCinProprietaireMagasin(cin);
                m.setBestSellerMagasin(rs.getInt("bestSellerMagasin"));
                m.setCodePostaleMagasin(rs.getInt("codePostaleMagasin"));
                m.setIdMagasin(rs.getInt("idMagasin"));
                m.setPhotoMagasin(rs.getString("photoMagasin"));
                              
                listMagasins.add(m);
                
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(Articleservice.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
        return listMagasins;
    
    }
    
    public void supprimerMagasin(int id)
         {
                
             String requete="DELETE FROM `Magasin` WHERE idMagasin='"+id+"' ";     
             Statement st;
             try {
              st = con.createStatement(); 
              st.executeUpdate(requete);
              System.out.println("article supprimé");

            } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
      

}
    
        
    public ResultSet rechercheMagasin()
    { 

        ResultSet rs=null;
        try {  
            String requete = "SELECT * FROM Magasin" ;
            rs = ste.executeQuery(requete);
             }catch (SQLException ex) {
                 System.out.println(" erreur rechercheMagasin()");
        }
        return rs ;
    
    }
}
    

