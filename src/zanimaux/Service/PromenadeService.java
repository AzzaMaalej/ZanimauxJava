/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package zanimaux.Service;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import zanimaux.Technique.DataSource;
import zanimaux.entities.Promenade;
import zanimaux.entities.User;
import zanimaux.util.Session;

/**
 *
 * @author Azza
 */
public class PromenadeService {
    private static PromenadeService instance;
    public static PromenadeService getInstance() throws SQLException {
       
         if (instance == null) {
            instance = new PromenadeService();
        }
        return instance;//To change body of generated methods, choose Tools | Templates.
    }
     public Connection con = DataSource.getInstance().getCon();
     public Statement ste;
     public PromenadeService() throws SQLException 
    {
        ste=con.createStatement();
    }
     
   public boolean ajouterPromenade(Promenade g) throws SQLException {
       String req="INSERT INTO promenade (nomPromenade,typePromenade, lieuPromenade ,descriptionPromenade ,datedebutPromenade ,datefinPromenade,photoPromenade,cinPetsitter) VALUES (?,?,?,?,?,?,?,?)";
        Promenade a =new Promenade();
        User user=Session.getLoggedInUser();
        Userservice us= new Userservice();
       try {
       java.util.Date datedebutPromenade = new Date(g.getDatedebutPromenade().getTime());
       java.util.Date datefinPromenade = new Date(g.getDatefinPromenade().getTime());
       PreparedStatement pre= con.prepareStatement(req);
        
        
        pre.setString(1, g.getNomPromenade());
        pre.setString(2, g.getTypePromenade());
        pre.setString(3, g.getLieuPromenade());
        pre.setString(4, g.getDescriptionPromenade());
        pre.setDate(5, (Date) datedebutPromenade);
        pre.setDate(6, (Date) datefinPromenade);
        pre.setString(7, g.getPhotoPromenade());
        pre.setString(8, user.getCin());
        pre.executeUpdate();   
     System.out.println("ajout reussit");
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
            return false;
    }
   
   public Promenade AfficherPromenade(String i)
    { 

        Promenade listForm = new Promenade();
        try {  
            String requete = "SELECT * FROM promenade WHERE `cinPetsitter`="+i;

           
            ResultSet rs = ste.executeQuery(requete);

             while(rs.next()){
                 listForm.setId(rs.getString("id"));
                 listForm.setNomPromenade(rs.getString("nomPromenade"));
                 listForm.setTypePromenade(rs.getString("typePromenade"));
                 listForm.setLieuPromenade(rs.getString("lieuPromenade"));
                 listForm.setDescriptionPromenade(rs.getString("descriptionPromenade"));
                 listForm.setDatedebutPromenade(rs.getDate("datedebutPromenade"));
                 listForm.setDatefinPromenade(rs.getDate("datefinPromenade"));
                 listForm.setPhotoPromenade(rs.getString("photoPromenade"));
                 listForm.setCinPetsitter(rs.getString("cinPetsitter"));             
            }
             
        } catch (SQLException ex) {
            Logger.getLogger(PromenadeService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return listForm;
    
    }
   
   
   
   public ResultSet AfficherPromenade()
    { 
        ResultSet rs=null;
        try {  
            String requete = "SELECT * FROM promenade" ;
            rs = ste.executeQuery(requete);
             }catch (SQLException ex) {
                 System.out.println(" erreur AfficherPromenade()");
        }
        return rs ;
    
    }
   
   public ResultSet RecherchePromenade(String i)
    { 
        ResultSet rs=null;
        try {  
            String requete = "SELECT * FROM promenade WHERE `nomPromenade`="+i+"OR `typePromenade`="+i ;
            rs = ste.executeQuery(requete);
             }catch (SQLException ex) {
                 System.out.println(" erreur AfficherPromenade()");
        }
        return rs ;
    
    }
   public ResultSet AfficherPromenadeByCin(String c)
    { 
        ResultSet rs=null;
        try {  
            String requete = "SELECT * FROM promenade WHERE cinPetsitter='"+c+"' " ;
            
            rs = ste.executeQuery(requete);
            
             }catch (SQLException ex) {
                 System.out.println(" erreur AfficherPromenade()");
        }
        return rs ;
    
    }
   public ResultSet AfficherPromenadeById(String c)
    { 
        ResultSet rs=null;
        try {  
            String requete = "SELECT * FROM promenade WHERE id='"+c+"' " ;
            
            rs = ste.executeQuery(requete);
            
             }catch (SQLException ex) {
                 System.out.println(" erreur AfficherPromenade()");
        }
        return rs ;
    
    }
   
   public void supprimerPromenade(String id)
         {
                
             String requete="DELETE FROM `promenade` WHERE id='"+id+"' ";     
             Statement st;
             try {
              st = con.createStatement(); 
              st.executeUpdate(requete);
              System.out.println("Promenade supprimé");

            } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
}

   
    public List<Promenade> AfficherParcsByCin(String c){
         List<Promenade>  listPromenades = new ArrayList<>();
        try {  
            String requete = "SELECT * FROM promenade WHERE cinPetsitter='"+c+"'";
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(requete);
            while(rs.next()){
                Promenade listForm=new Promenade();
                 listForm.setId(rs.getString("id"));
                 listForm.setNomPromenade(rs.getString("nomPromenade"));
                 listForm.setTypePromenade(rs.getString("typePromenade"));
                 listForm.setLieuPromenade(rs.getString("lieuPromenade"));
                 listForm.setDescriptionPromenade(rs.getString("descriptionPromenade"));
                 listForm.setDatedebutPromenade(rs.getDate("datedebutPromenade"));
                 listForm.setDatefinPromenade(rs.getDate("datefinPromenade"));
                 listForm.setPhotoPromenade(rs.getString("photoPromenade"));
                 listForm.setCinPetsitter(rs.getString("cinPetsitter"));    
                 listPromenades.add(listForm);
            }
             }catch (SQLException ex) {
                 System.out.println(" erreur AfficherPromenadeByCin()");
        }
        return listPromenades ;
    }
    
    public boolean ModifierPromenade(Promenade r)
    {
        int nbr_ligne;
        
        try{
            
            String requete="UPDATE promenade set id=?, nompromenade=?, typePromenade=?, lieuPromenade=?, descriptionPromenade=?, datedebutPromenade=?, datefinPromenade=?, photoPromenade=? WHERE id='"+r.getId()+"'";
            PreparedStatement pst = con.prepareStatement(requete);
            java.util.Date dateDeb = new Date(r.getDatedebutPromenade().getTime());
            java.util.Date dateFin = new Date(r.getDatefinPromenade().getTime());
            pst.setString(1, r.getId());
            pst.setString(2, r.getNomPromenade());
            pst.setString(3, r.getTypePromenade());
            pst.setString(4, r.getLieuPromenade());
            pst.setString(5, r.getDescriptionPromenade());
            pst.setDate(6, (Date) dateDeb);
            pst.setDate(7, (Date) dateFin);
            pst.setString(8, r.getPhotoPromenade());
          
            nbr_ligne=pst.executeUpdate();
        }
        catch (SQLException ex) {
            Logger.getLogger(PromenadeService.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
        if(nbr_ligne == 0){
            return false;
        }else{
            return true;
        }
    }
}
