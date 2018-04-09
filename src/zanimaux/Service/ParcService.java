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
    private static ParcService instance;
    public static ParcService getInstance() throws SQLException {
       
         if (instance == null) {
            instance = new ParcService();
        }
        return instance;//To change body of generated methods, choose Tools | Templates.
    }
     public Connection con = DataSource.getInstance().getCon();
     public Statement ste;
     public ParcService() throws SQLException 
    {
        ste=con.createStatement();
    }
     
   public boolean ajouterParc(Parc g) throws SQLException {
       String req="INSERT INTO parc (nomParc,CategorieDressage, adresseParc ,villeParc ,codePostaleParc ,photoParc,cinDresseur) VALUES (?,?,?,?,?,?,?)";
        Parc a =new Parc();
        User user=Session.getLoggedInUser();
        Userservice us= new Userservice();
       try {
       
       PreparedStatement pre= con.prepareStatement(req);
        
        
        pre.setString(1, g.getNomParc());
        pre.setString(2, g.getCategorieDressage());
        pre.setString(3, g.getAdresseParc());
        pre.setString(4, g.getVilleParc());
        pre.setInt(5, g.getCodePostaleParc());
        pre.setString(6, g.getPhotoParc());
        pre.setString(7, user.getCin());
        pre.executeUpdate();   
     System.out.println("ajout reussit");
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
            return false;
    }
   
   public Parc AfficherParc(String i)
    { 

        Parc listForm = new Parc();
        try {  
            String requete = "SELECT * FROM parc WHERE `cinDresseur`="+i;

           
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
   public ResultSet AfficherParcByCin(String c)
    { 
        ResultSet rs=null;
        try {  
            String requete = "SELECT * FROM parc WHERE cinDresseur='"+c+"' " ;
            
            rs = ste.executeQuery(requete);
            
             }catch (SQLException ex) {
                 System.out.println(" erreur AfficherParc()");
        }
        return rs ;
    
    }
   public ResultSet AfficherParcById(String c)
    { 
        ResultSet rs=null;
        try {  
            String requete = "SELECT * FROM parc WHERE id='"+c+"' " ;
            
            rs = ste.executeQuery(requete);
            
             }catch (SQLException ex) {
                 System.out.println(" erreur AfficherParc()");
        }
        return rs ;
    
    }
   
   public void supprimerParc(String id)
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

   
    public List<Parc> AfficherParcsByCin(String c){
         List<Parc>  listParcs = new ArrayList<>();
        try {  
            String requete = "SELECT * FROM parc WHERE cinDresseur='"+c+"'";
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(requete);
            while(rs.next()){
                Parc listForm=new Parc();
                 listForm.setId(rs.getString("id"));
                 listForm.setNomParc(rs.getString("nomParc"));
                 listForm.setCategorieDressage(rs.getString("CategorieDressage"));
                 listForm.setAdresseParc(rs.getString("adresseParc"));
                 listForm.setVilleParc(rs.getString("villeParc"));
                 listForm.setCodePostaleParc(rs.getInt("codePostaleParc"));
                 listForm.setPhotoParc(rs.getString("photoParc"));
                 listForm.setCinDresseur(rs.getString("cinDresseur"));
                 listParcs.add(listForm);
            }
             }catch (SQLException ex) {
                 System.out.println(" erreur AfficherRefugeByCin()");
        }
        return listParcs ;
    }
    
    public boolean ModifierParc(Parc r)
    {
        int nbr_ligne;
        try{
            
            String requete="UPDATE parc set id=?, nomParc=?, CategorieDressage=?, adresseParc=?, villeParc=?, codePostaleParc=?, photoParc=? WHERE id='"+r.getId()+"'";
            PreparedStatement pst = con.prepareStatement(requete);
            pst.setString(1, r.getId());
            pst.setString(2,r.getNomParc());
            pst.setString(3,r.getCategorieDressage());
            pst.setString(4,r.getAdresseParc());
            pst.setString(5,r.getVilleParc());
            pst.setInt(6,r.getCodePostaleParc());
            pst.setString(7,r.getPhotoParc());
            
            
            
            nbr_ligne=pst.executeUpdate();
        }
        catch (SQLException ex) {
            Logger.getLogger(ParcService.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
        if(nbr_ligne == 0){
            return false;
        }else{
            return true;
        }
    }
    
    
    
    
    
    
 public int CountParcByCateg(String c) throws SQLException {  
     
     int count=0;
    String requete="SELECT count(*) from parc where CategorieDressage='"+c+"'";
    Statement st;
    st = con.createStatement();
try {
              st = con.createStatement(); 
              ResultSet rs = st.executeQuery(requete);
              while (rs.next()){
            count = rs.getInt("count(*)");
              }
            } catch (SQLException ex) {
            System.err.println(ex.getMessage());
            
        }
        return count;
        

      
 }
 public int CountParc() throws SQLException {  
     
     int count=0;
    String requete="SELECT count(*) from parc ";
    Statement st;
    st = con.createStatement();
try {
              st = con.createStatement(); 
              ResultSet rs = st.executeQuery(requete);
              while (rs.next()){
            count = rs.getInt("count(*)");
              }
            } catch (SQLException ex) {
            System.err.println(ex.getMessage());
            
        }
        return count;
        

      
 }
}



