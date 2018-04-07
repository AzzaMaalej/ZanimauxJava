/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package zanimaux.Service;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import zanimaux.Technique.DataSource;
import zanimaux.entities.Race;
import zanimaux.entities.Refuge;

/**
 *
 * @author Azza
 */
public class RaceService {
    private static RaceService instance;
    public static RaceService getInstance() throws SQLException {
       
         if (instance == null) {
            instance = new RaceService();
        }
        return instance;//To change body of generated methods, choose Tools | Templates.
    }
       public Connection con = DataSource.getInstance().getCon();
    public Statement ste;

    public RaceService() throws SQLException {
       ste = con.createStatement();
    }
     public Race RechercherRaceById(int i)
    { 

        Race listForm = new Race();
        try {  
            String requete = "SELECT * FROM race WHERE id="+i+"";

           
            ResultSet rs = ste.executeQuery(requete);

             while(rs.next()){
                 listForm.setId(rs.getInt("id"));
                 listForm.setInformations(rs.getString("informations"));
                 listForm.setRace(rs.getString("race"));
                 listForm.setType(rs.getString("type"));
                 listForm.setPhoto(rs.getString("photo"));
                 
            }
             
        } catch (SQLException ex) {
            Logger.getLogger(Articleservice.class.getName()).log(Level.SEVERE, null, ex);
        }
        return listForm;
    
    }
}
