/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package zanimaux.Service;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import zanimaux.Technique.DataSource;

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
}
