/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package zanimaux.Technique;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;


/**
 *
 * @author Mariam
 */
public class DataSource {
    private static DataSource data;
    private Connection con;
    public String login="root";

    public String password="";
    public String url="jdbc:mysql://localhost:3306/zanimo";


    private DataSource(){
       
        try {
            con = DriverManager.getConnection(url,login,password);
        } catch (SQLException ex) {
            Logger.getLogger(DataSource.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        System.out.println("Connexion établie");
    }
    
    public static DataSource getInstance()
    { if(data==null)
        data = new DataSource();
        return data;
    }

    public Connection getCon() {
        return con;
    }
    
    //assurer le singleton
}
