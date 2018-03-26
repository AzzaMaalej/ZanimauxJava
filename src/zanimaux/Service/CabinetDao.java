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
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import zanimaux.Technique.DataSource;
import zanimaux.entities.Cabinet;

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
            String requete = "INSERT INTO  cabinet(immatriculecabinet,emailCabinet,photovet,telephoneCabinet,faxCabinet,adresseCabinet,villeCabinet,codePostaleCabinet)  VALUES (?,?,?,?,?,?,?,?) ";
            Cabinet p=new Cabinet();
            
            PreparedStatement pst =cnx.prepareStatement(requete);
            pst.setString(1,immatriculeCabinet);
            pst.setString(2,emailCabinet);
            pst.setString(3,photovet);
            pst.setInt(4,telephoneCabinet);
            pst.setInt(5,FaxCabinet);
            pst.setString(6,AdresseCabinet);
            pst.setString(7, VilleCabinet);
            pst.setInt(8, CodePostaleCabinet);
            
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






}