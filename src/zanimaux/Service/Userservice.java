/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package zanimaux.Service;

import zanimaux.entities.User;
import zanimaux.Technique.DataSource;
import java.sql.Connection;

/**
 *
 * @author Mariam
 */
public class Userservice {
     public Connection con;

    public Userservice() {
        con = DataSource.getInstance().getCon();
    }
    
    public User findUserById(int id){
        return null;
    }
    
}
