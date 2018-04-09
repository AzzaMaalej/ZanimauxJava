/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package zanimaux.util;

import zanimaux.entities.User;




public class Session {
    
    private static User loggedInUser = null;
    private static String ip = "127.0.0.1";
    private static int IdThisSujet=0;
    public static String addImage = "file:///C:/Users/Mariam/Desktop/";
   

    public static User getLoggedInUser() {
        return loggedInUser;
    }

    public static void setLoggedInUser(User loggedInUser) {
        Session.loggedInUser = loggedInUser;
    }

    public static String getIp() {
        return ip;
    }

   

    
    
    

   
    
    
    
    
    
}
