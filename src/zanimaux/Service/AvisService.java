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
import zanimaux.entities.Avis;
import zanimaux.entities.User;
import zanimaux.util.Session;

/**
 *
 * @author Azza
 */
public class AvisService {
    private static AvisService instance;
    public static AvisService getInstance() throws SQLException {
       
         if (instance == null) {
            instance = new AvisService();
        }
        return instance;//To change body of generated methods, choose Tools | Templates.
    }
     public Connection con = DataSource.getInstance().getCon();
     public Statement ste;
     public AvisService() throws SQLException 
    {
        ste=con.createStatement();
    }
     
   public boolean ajouterAvis(Avis g) throws SQLException {
       String req="INSERT INTO avis (idParc,avis,cinUser) VALUES (?,?,?)";
        Avis a =new Avis();
        User user=Session.getLoggedInUser();
        Userservice us= new Userservice();
       try {
       
       PreparedStatement pre= con.prepareStatement(req);
        
        
        pre.setString(1, g.getIdParc());
        pre.setDouble(2, g.getAvis());
        pre.setString(3, user.getCin());
        pre.executeUpdate();   
     System.out.println("ajout reussit");
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
            return false;
    }
   public ResultSet AfficherAvis(String i, String e)
    { 
        ResultSet rs=null;
        try {  
            String requete = "SELECT * FROM avis WHERE cinUser='"+e+"'AND idParc='"+i+"'"  ;

            rs = ste.executeQuery(requete);
            
             }catch (SQLException ex) {
                 System.out.println(" erreur AfficherAvis()");
        }
        return rs ;
    
    }
   
    public boolean VerifierAvis(String i, String e)
    { 
        
        try {  
            String requete = "SELECT * FROM avis WHERE cinUser='"+e+"'AND idParc='"+i+"'"  ;
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(requete);
            if(rs.next()){
                return true;
            }
            
             }catch (SQLException ex) {
                 System.out.println(" erreur VerifierAvis()");
        }
        return false;
    
    }
    
     public double moyenne(String i){
         double average = 0;
        try {
          String requete = "SELECT avis from avis where idParc=" + i;
          Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(requete);
                double rating = 0;
                int nbr = 0;
                
                while (rs.next()) {
                    rating += Double.parseDouble(rs.getString("avis"));
                    nbr++;
                    average=(rating/nbr);
                }
               
        }catch(Exception e){
            e.printStackTrace();
        }
        return average;
    }
   
             
        
    
    
   
   
    
  
    
}
