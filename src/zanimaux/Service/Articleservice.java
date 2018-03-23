/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package zanimaux.Service;

import zanimaux.entities.Articles;
import zanimaux.entities.User;
import zanimaux.Technique.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Mariam
 */
public class Articleservice {
    
      private static Articleservice instance;
      public Connection con = DataSource.getInstance().getCon();
      public Statement ste;
    public static Articleservice getInstance() throws SQLException {
       
         if (instance == null) {
            instance = new Articleservice();
        }
        return instance;//To change body of generated methods, choose Tools | Templates.
    }
    
    public Articleservice() throws SQLException {
       ste = con.createStatement();
    }
    
    
    public boolean ajouterArticle(String desc,String tit,int cin){
       String requete = "INSERT INTO article(titre,description,cin) VALUES (?,?,?) ";
       Articles a =new Articles();
       try {
            PreparedStatement pst =con.prepareStatement(requete);
            pst.setString(1,desc);
            pst.setString(2,tit);
           // pst.setObject(3, user);
            pst.setInt(3,cin);
            //pst.setString(3,path);
           
           
             pst.executeUpdate();
            System.out.println("ajout reussit");
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
            return false;
    }
    
    public boolean updateArticle(int i)
    { 
        Articles p=new Articles();
    String requete="UPDATE article SET titre=?,description=? where id='"+i+"'";
        try {
            PreparedStatement pst =con.prepareStatement(requete);
            pst.setString(1,p.getTitre());
         
            pst.setString(2,p.getDescription());
           // pst.setString(3,p.getPiecejointe());
            
             pst.executeUpdate();
            System.out.println("modification reussite");
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
            return false;
    }

    
   
    public Articles recherchearticle(int i)
    { System.out.println("awel e recherche");
     Articles listForm = new Articles();
        try {  
            String requete = "select * from articles WHERE id=?";
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(requete); 
            System.out.println(rs.next());
             while(rs.next()){
         Articles ff = new Articles(i,rs.getString("titre"),rs.getString("description"),rs.getString("piecejointe"));
               listForm.setId(i);
               listForm.setTitre(rs.getString("titre"));
       
               listForm.setDescription(rs.getString("description"));
               listForm.setPiecejointe(rs.getString("piecejointe"));
        
              
            }
             
        } catch (SQLException ex) {
            Logger.getLogger(Articleservice.class.getName()).log(Level.SEVERE, null, ex);
        }
        return listForm;
    
    }
    
    
  
   
     public void supprimer(int id)
{
  String requete="DELETE FROM `article` WHERE id='"+id+"' ";     
        Statement st;
        try {
            st = con.createStatement(); 
            st.executeUpdate(requete);
      System.out.println("article supprimé");

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
      

}
}
