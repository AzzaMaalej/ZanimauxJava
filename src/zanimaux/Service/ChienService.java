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
import zanimaux.Technique.DataSource;
import zanimaux.entities.Questionnaire_chien;

/**
 *
 * @author Azza
 */
public class ChienService {
    private static ChienService instance;
    public static ChienService getInstance() throws SQLException {
       
         if (instance == null) {
            instance = new ChienService();
        }
        return instance;//To change body of generated methods, choose Tools | Templates.
    }
       public Connection con = DataSource.getInstance().getCon();
    public Statement ste;

    public ChienService() throws SQLException {
       ste = con.createStatement();
    }
    public int AfficherIdChienRace(String a,String b,String c,String d,String e,String f) throws SQLException{
        Questionnaire_chien chien=new Questionnaire_chien();
        String requete = "SELECT race FROM questionnaire_chien WHERE tolererChien="+a+" AND calme="+b+" AND affectueux="+c+" AND chutePoils="+d+" AND intelligent="+e+" AND tolererChat="+f;
        ResultSet rs = ste.executeQuery(requete);
         while(rs.next()){
             chien.setRace(rs.getInt("race"));
         }
        
        return chien.getRace();
    }
}
