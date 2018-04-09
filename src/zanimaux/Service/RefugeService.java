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
import zanimaux.entities.Articles;
import zanimaux.entities.Refuge;
import zanimaux.entities.User;
import zanimaux.util.Session;

/**
 *
 * @author Azza
 */
public class RefugeService {
     private static RefugeService instance;
    public static RefugeService getInstance() throws SQLException {
       
         if (instance == null) {
            instance = new RefugeService();
        }
        return instance;//To change body of generated methods, choose Tools | Templates.
    }
       public Connection con = DataSource.getInstance().getCon();
public Statement ste;

    public RefugeService() throws SQLException {
       ste = con.createStatement();
    }
    public boolean ajouterRefuge(Refuge r) throws SQLException{
       String requete = "INSERT INTO refuge(immatriculation, cin, nomRefuge, emailRefuge, telephoneRefuge, faxRefuge, adresseRefuge, codePostaleRefuge, gouvernementRefuge, photoRefuge, chat, chien, rongeur, autre) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?) ";
        Refuge a =new Refuge();
        User user=Session.getLoggedInUser();
        Userservice us= new Userservice();
       try {
           
            PreparedStatement pst =con.prepareStatement(requete);
            pst.setString(1, r.getImmatriculation());
            pst.setString(2,user.getCin());
            pst.setString(3,r.getNomRefuge());

            pst.setString(4,r.getEmailRefuge());
            pst.setInt(5,r.getTelephoneRefuge());
            pst.setInt(6,r.getFaxRefuge());
            pst.setString(7,r.getAdresseRefuge());
            pst.setInt(8,r.getCodePostaleRefuge());
            pst.setString(9, r.getGouvernementRefuge());
            pst.setString(10,r.getPhotoRefuge());
            pst.setString(11,r.getChat());
            pst.setString(12,r.getChien());
            pst.setString(13,r.getRongeur());
            pst.setString(14, r.getAutre());
            
           
           // pst.setObject(3, user);
            //pst.setString(3,path);
           
           
             pst.executeUpdate();
            System.out.println("ajout reussit");
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
            return false;
    }
    public ResultSet AfficherTousRefuge()
    { 
        ResultSet rs=null;
        try {  
            String requete = "SELECT * FROM refuge";
            rs = ste.executeQuery(requete);
             }catch (SQLException ex) {
                 System.out.println(" erreur AfficherTousRefuge()");
        }
        return rs ;
    
    }
    
    public Refuge RechercherRefugeByImm(String i)
    { 

        Refuge listForm = new Refuge();
        try {  
            String requete = "SELECT * FROM refuge WHERE immatriculation='"+i+"'";

           
            ResultSet rs = ste.executeQuery(requete);

             while(rs.next()){
                 listForm.setImmatriculation(rs.getString("immatriculation"));
                 listForm.setCin(rs.getString("cin"));
                 listForm.setNomRefuge(rs.getString("nomRefuge"));
                 listForm.setEmailRefuge(rs.getString("emailRefuge"));
                 listForm.setTelephoneRefuge(rs.getInt("telephoneRefuge"));
                 listForm.setFaxRefuge(rs.getInt("faxRefuge"));
                 listForm.setAdresseRefuge(rs.getString("adresseRefuge"));
                 listForm.setCodePostaleRefuge(rs.getInt("codePostaleRefuge"));
                 listForm.setGouvernementRefuge(rs.getString("gouvernementRefuge"));
                 listForm.setPhotoRefuge(rs.getString("photorefuge"));
                 listForm.setChat(rs.getString("chat"));
                 listForm.setChien(rs.getString("chien"));
                 listForm.setRongeur(rs.getString("rongeur"));
                 listForm.setAutre(rs.getString("autre"));
            }
             
        } catch (SQLException ex) {
            Logger.getLogger(Articleservice.class.getName()).log(Level.SEVERE, null, ex);
        }
        return listForm;
    
    }
    public void SupprimerRefuge(String immatriculation)
{
  String requete="DELETE FROM refuge WHERE immatriculation='"+immatriculation+"' ";     
        Statement st;
        try {
            st = con.createStatement(); 
            st.executeUpdate(requete);
      System.out.println("refuge supprimé");

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
}
    public List<Refuge> AfficherRefugeByCin(String c){
         List<Refuge>  listRefuges = new ArrayList<>();
        try {  
            String requete = "SELECT * FROM refuge WHERE cin='"+c+"'";
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(requete);
            while(rs.next()){
                Refuge listForm=new Refuge();
                 listForm.setImmatriculation(rs.getString("immatriculation"));
                 listForm.setCin(rs.getString("cin"));
                 listForm.setNomRefuge(rs.getString("nomRefuge"));
                 listForm.setEmailRefuge(rs.getString("emailRefuge"));
                 listForm.setTelephoneRefuge(rs.getInt("telephoneRefuge"));
                 listForm.setFaxRefuge(rs.getInt("faxRefuge"));
                 listForm.setAdresseRefuge(rs.getString("adresseRefuge"));
                 listForm.setCodePostaleRefuge(rs.getInt("codePostaleRefuge"));
                 listForm.setGouvernementRefuge(rs.getString("gouvernementRefuge"));
                 listForm.setPhotoRefuge(rs.getString("photorefuge"));
                 listForm.setChat(rs.getString("chat"));
                 listForm.setChien(rs.getString("chien"));
                 listForm.setRongeur(rs.getString("rongeur"));
                 listForm.setAutre(rs.getString("autre"));
                 listRefuges.add(listForm);
            }
             }catch (SQLException ex) {
                 System.out.println(" erreur AfficherRefugeByCin()");
        }
        return listRefuges ;
    }
     public boolean ModifierRefuge(Refuge r)
    {
        int nbr_ligne;
        try{
            
            String requete="UPDATE refuge set immatriculation=?, nomRefuge=?, emailRefuge=?, telephoneRefuge=?, faxRefuge=?, adresseRefuge=?, codePostaleRefuge=?, gouvernementRefuge=?, photoRefuge=?, chat=?, chien=?, rongeur=?, autre=? WHERE immatriculation='"+r.getImmatriculation()+"'";
            PreparedStatement pst = con.prepareStatement(requete);
            pst.setString(1, r.getImmatriculation());
            pst.setString(2,r.getNomRefuge());

            pst.setString(3,r.getEmailRefuge());
            pst.setInt(4,r.getTelephoneRefuge());
            pst.setInt(5,r.getFaxRefuge());
            pst.setString(6,r.getAdresseRefuge());
            pst.setInt(7,r.getCodePostaleRefuge());
            pst.setString(8, r.getGouvernementRefuge());
            pst.setString(9,r.getPhotoRefuge());
            pst.setString(10,r.getChat());
            pst.setString(11,r.getChien());
            pst.setString(12,r.getRongeur());
            pst.setString(13, r.getAutre());
            
            
            nbr_ligne=pst.executeUpdate();
        }
        catch (SQLException ex) {
            Logger.getLogger(RefugeService.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
        if(nbr_ligne == 0){
            return false;
        }else{
            return true;
        }
    }
   
}
