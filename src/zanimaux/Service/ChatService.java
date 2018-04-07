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
import zanimaux.entities.Questionnaire_chat;

/**
 *
 * @author Azza
 */
public class ChatService {
    private static ChatService instance;
    public static ChatService getInstance() throws SQLException {
       
         if (instance == null) {
            instance = new ChatService();
        }
        return instance;//To change body of generated methods, choose Tools | Templates.
    }
       public Connection con = DataSource.getInstance().getCon();
    public Statement ste;

    public ChatService() throws SQLException {
       ste = con.createStatement();
    }
    
     public int AfficherIdChatRace(String a,String b,String c,String d,String e,String f) throws SQLException{
        Questionnaire_chat chat=new Questionnaire_chat();
        String requete = "SELECT race FROM questionnaire_chat WHERE tolererChien="+a+" AND dynamique="+b+" AND affectueux="+c+" AND chutePoils="+d+" AND intelligent="+e+" AND acceptationEtranger="+f;
        ResultSet rs = ste.executeQuery(requete);
         while(rs.next()){
             chat.setRace(rs.getInt("race"));
         }
        
        return chat.getRace();
    }
}
