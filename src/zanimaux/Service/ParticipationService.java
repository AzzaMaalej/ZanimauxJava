/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package zanimaux.Service;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import zanimaux.Technique.DataSource;
import zanimaux.entities.Evenement;
import zanimaux.entities.Participation;
import zanimaux.entities.User;
import zanimaux.util.Session;

/**
 *
 * @author Maroua
 */
public class ParticipationService {
    
     public Connection con = DataSource.getInstance().getCon();
     public Statement ste;

    public ParticipationService()throws SQLException  {
        ste=con.createStatement();
    }

     
      public boolean exist(int i)
    { 
         User usr = Session.getLoggedInUser();
         String idUser = usr.getCin();
         ResultSet rs =null;
         boolean v = false;
        try {  
            String requete = "SELECT * FROM participation WHERE `idEvt`="+i+" and `cin` ='"+idUser+"'";

           
             rs = ste.executeQuery(requete);

             while(rs.next()){
                v=true;              
            }
             
        } catch (SQLException ex) {
            Logger.getLogger(EvenementService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return v;
    
    }
      
        
     public boolean ajouterParticipation(Participation p)
    {
       String requete = "INSERT INTO participation (cin,idEvt) VALUES (?,?) ";
        User usr = Session.getLoggedInUser();
       
       try {
          
            PreparedStatement pst = con.prepareStatement(requete);
            pst.setString(1,usr.getCin());
            pst.setInt(2,p.getIdEvt());
          
            
            pst.executeUpdate();
            System.out.println("ajouté avec succés");
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
            return false;
    }
     
     public void supprimerParticipation(int id)
         {
                User usr = Session.getLoggedInUser();
             String requete="DELETE FROM `participation` WHERE idEvt="+id+" and cin='"+usr.getCin()+"' ";     
             Statement st;
             try {
              st = con.createStatement(); 
              st.executeUpdate(requete);
              System.out.println("Supprimé avec succés");

            } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
     }
      
    
    
}
