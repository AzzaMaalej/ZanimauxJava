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
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import zanimaux.entities.Cabinet;
import zanimaux.util.Session;

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
    
 public boolean ajouterArticle(String titre, String description, String piecejointe){
        int nbr_ligne=0;
        try{
            String requete = "INSERT INTO article(cin,titre,description,piecejointe)  VALUES (?,?,?,?) ";
            Articles  a=new Articles();
            User user = Session.getLoggedInUser();
            PreparedStatement pst =con.prepareStatement(requete);
            pst.setString(1,user.getCin());
            pst.setString(2,titre);
            pst.setString(3,description);
            pst.setString(4,piecejointe);
           
                nbr_ligne=pst.executeUpdate();
        }
        catch (SQLException ex) {
            Logger.getLogger(Articleservice.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
        if(nbr_ligne == 0){
            return false;
        }else{
            return true;
        }
   }
   
    public List<Articles> getAllByVet(String cin)
   {
        List<Articles> listArticles = new ArrayList<>();
        try {
            String requete = "select * from article where cin='"+cin+"'";
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(requete);
            
            while(rs.next()){
                Articles article = new Articles();
               
                article.setCin(cin);
                article.setTitre(rs.getString(3));
                article.setDescription(rs.getString(4));
                article.setPiecejointe(rs.getString(5));
               
                listArticles.add(article);
                
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(Articleservice.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
        return listArticles;
    }
      
    public void supprimerArticle (int id) throws SQLException
    {
        
       
            String requete = "DELETE FROM article WHERE id='"+id+"'";
            PreparedStatement pst = con.prepareStatement(requete);
           
              pst.executeUpdate(requete);
              System.out.println("article supprimé");

           
    
    }
     public boolean ModifierArticle(Articles article)
    {
        int nbr_ligne;
        try{
            String requete="UPDATE article set titre=?,description=?,piecejointe=?,cin=?";
            PreparedStatement pst = con.prepareStatement(requete);
            pst.setString(1,article.getTitre());
            pst.setString(2,article.getDescription());
            pst.setString(3,article.getPiecejointe());
            pst.setString(4,article.getCin());
            
            
            nbr_ligne=pst.executeUpdate();
        }
        catch (SQLException ex) {
            Logger.getLogger(Articleservice.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
        if(nbr_ligne == 0){
            return false;
        }else{
            return true;
        }
    }
   
  
}
