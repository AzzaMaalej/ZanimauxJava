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
import zanimaux.entities.Cabinet;
import zanimaux.entities.User;
import zanimaux.util.Session;

/**
 *
 * @author Mariam
 */
public class CabinetDao {

    public Connection cnx;

    public CabinetDao() {
        cnx = DataSource.getInstance().getCon();
    }



public boolean ajouterCabinet(String immatriculeCabinet, String emailCabinet, int telephoneCabinet, int FaxCabinet, String AdresseCabinet, String VilleCabinet, int CodePostaleCabinet, String photovet){
        try {
            String requete = "INSERT INTO  cabinet(cin,immatriculecabinet,emailCabinet,photovet,telephoneCabinet,faxCabinet,adresseCabinet,villeCabinet,codePostaleCabinet)  VALUES (?,?,?,?,?,?,?,?,?) ";
            Cabinet p=new Cabinet();
            User user = Session.getLoggedInUser();
            PreparedStatement pst =cnx.prepareStatement(requete);
            pst.setString(1,user.getCin());
            pst.setString(2,immatriculeCabinet);
            pst.setString(3,emailCabinet);
            pst.setString(4,photovet);
            pst.setInt(5,telephoneCabinet);
            pst.setInt(6,FaxCabinet);
            pst.setString(7,AdresseCabinet);
            pst.setString(8, VilleCabinet);
            pst.setInt(9, CodePostaleCabinet);
            
            pst.executeUpdate();
            System.out.println("ajout reussit");
            
            
        } catch (SQLException ex) {
          
        }
return false;   }



    public List<Cabinet> getAllInfo(int cabinet) {
        List<Cabinet> list = new ArrayList<>();
        try {
            String req = "SELECT * FROM Cabinet WHERE immatriculecabinet= ?";
             PreparedStatement ste = null;
            ste = cnx.prepareStatement(req);
            ste.setInt(1 , cabinet);
            ResultSet rs = ste.executeQuery();
            
            while(rs.next()){
                Cabinet p = new Cabinet();
                p.setImmatriculeCabinet(rs.getString("immatriculecabinet"));
                 p.setAdresseCabinet(rs.getString("adresseCabinet"));
               p.setVilleCabinet(rs.getString("villeCabinet"));
               p.setTelephoneCabinet(rs.getInt("telephoneCabinet"));
               p.setEmailCabinet(rs.getString("emailCabinet"));
               p.setPhotovet(rs.getString("photovet"));
           
                list.add(p);
            }
        } catch (SQLException ex) {
                  Logger.getLogger(CabinetDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }

public Cabinet getByVet(String cin)
   {
        Cabinet cabinet = new Cabinet();
        try {
            String requete = "select * from cabinet where cin='"+cin+"'";
            Statement st = cnx.createStatement();
            ResultSet rs = st.executeQuery(requete);
            
            while(rs.next()){
                
               
                cabinet.setCin(cin);
               cabinet.setImmatriculeCabinet(rs.getString("immatriculecabinet"));
                 cabinet.setAdresseCabinet(rs.getString("adresseCabinet"));
                cabinet.setVilleCabinet(rs.getString("villeCabinet"));
                cabinet.setFaxCabinet(rs.getInt("faxCabinet"));
                cabinet.setTelephoneCabinet(rs.getInt("telephoneCabinet"));
                 cabinet.setCodePostaleCabinet(rs.getInt("codePostaleCabinet"));
               cabinet.setEmailCabinet(rs.getString("emailCabinet"));
               cabinet.setPhotovet(rs.getString("photovet"));
                
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(Articleservice.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
        return cabinet;
    }

 public boolean modifierCabinet(Cabinet u)
    {  System.out.println("modifié avec succés");
    String requete="UPDATE Cabinet SET emailCabinet=?,faxCabinet=?,telephoneCabinet=?,adresseCabinet=?,villeCabinet=?,codePostaleCabinet=?,photovet=? WHERE immatriculecabinet=?";
         System.out.println("modifié avec succés1");
    try {
            PreparedStatement pst =cnx.prepareStatement(requete);
            System.out.println("modifié avec succés");
            pst.setString(1,u.getEmailCabinet());
            pst.setInt(2,u.getFaxCabinet());
            pst.setInt(3,u.getTelephoneCabinet());
            pst.setString(4,u.getAdresseCabinet()); 
            pst.setString(5,u.getVilleCabinet());
            pst.setInt(6,u.getCodePostaleCabinet()); 
            pst.setString(7,u.getPhotovet()); 
            pst.setString(8,u.getImmatriculeCabinet());
          
             System.out.println("modifié avec succés");
             pst.executeUpdate();
           
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
            return false;
    }

  public ResultSet AfficherTousCabinets()
    { 
        ResultSet rs=null;
        try {  
            String requete = "SELECT * FROM cabinet";
           
             PreparedStatement pst =cnx.prepareStatement(requete);
              rs = pst.executeQuery(requete);
             }catch (SQLException ex) {
                 System.out.println(" erreur AfficherTousCabinets()");
        }
        return rs ;
    
    }

}