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
    public Connection con;
     public ParcService() throws SQLException{
        // st=con.createStatement();
        con = DataSource.getInstance().getCon();
    }
   public void ajouterParc(Parc g) throws SQLException {
       String req="INSERT INTO parc (id,nomParc,CategorieDressage, adresseParc ,villeParc ,codePostaleParc ,photoParc,cinDresseur) VALUES (?,?,?,?,?,?,?,?)";
        PreparedStatement pre= con.prepareStatement(req);
        
        pre.setInt(1, g.getId());
        pre.setString(2, g.getNomParc());
        pre.setString(3, g.getCategorieDressage());
        pre.setString(4, g.getAdresseParc());
        pre.setString(5, g.getVilleParc());
        pre.setInt(6, g.getCodePostaleParc());
        pre.setString(7, g.getPhotoParc());
        pre.setString(8, g.getCinDresseur());
        pre.executeUpdate();   
    }
}
