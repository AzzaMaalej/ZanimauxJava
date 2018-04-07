/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package zanimaux.Service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import zanimaux.Technique.DataSource;
import zanimaux.entities.Commentaires;
import zanimaux.entities.User;
import zanimaux.util.Session;

/**
 *
 * @author Azza
 */
    public class CommentairesService {
        
    private static CommentairesService instance;
    public static CommentairesService getInstance() throws SQLException {
       
         if (instance == null) {
            instance = new CommentairesService();
        }
        return instance;//To change body of generated methods, choose Tools | Templates.
    }
    public Connection con = DataSource.getInstance().getCon();
    public Statement ste;

    public CommentairesService() throws SQLException {
       ste = con.createStatement();
    }
    
    
     public boolean ajouterCommentaire(Commentaires r) throws SQLException{
       String requete = "INSERT INTO commentaires( cin, contenant,date, refuge) VALUES (?,?,?,?) ";
        Commentaires a =new Commentaires();
        User user=Session.getLoggedInUser();
        Userservice us= new Userservice();
     
           try {
            PreparedStatement pst =con.prepareStatement(requete);
            pst.setString(1,user.getCin());
            pst.setString(2, r.getContenant());

            pst.setDate(3,r.getDate());

            pst.setString(4,r.getRefuge());
                 
             pst.executeUpdate();
            System.out.println("ajout commentaires reussit");
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
            return false;
    }
       public void SupprimerCommentaire(int i)
{
  String requete="DELETE FROM commentaires WHERE id='"+i+"' ";     
        Statement st;
        try {
            st = con.createStatement(); 
            st.executeUpdate(requete);
      System.out.println("comm supprimé");

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
}
       public boolean ModifierComm(Commentaires r)
    {
        int nbr_ligne;
        try{
            
            String requete="UPDATE commentaires set cin=?, contenant=?,date=?, refuge=?  WHERE id="+r.getId()+"";
            PreparedStatement pst = con.prepareStatement(requete);
            pst.setString(1, r.getCin());
            pst.setString(2,r.getContenant());
            pst.setDate(3,r.getDate());
            pst.setString(4,r.getRefuge());
            
          
            
            nbr_ligne=pst.executeUpdate();
        }
        catch (SQLException ex) {
            Logger.getLogger(CommentairesService.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
        if(nbr_ligne == 0){
            return false;
        }else{
            return true;
        }
        
    }
      public List<Commentaires> ListerCommentairesRefuge(String i){
         List<Commentaires> listCom = new ArrayList<>();
        Commentaires listForm= new Commentaires();
        try {  
            String requete = "SELECT * FROM commentaire WHERE refuge='"+i+"'";

           
            ResultSet rs = ste.executeQuery(requete);

             while(rs.next()){
                 listForm.setRefuge(rs.getString("refuge"));
                 listForm.setId(rs.getInt("id"));
                 listForm.setCin(rs.getString("cin"));
                 listForm.setDate(rs.getDate("date"));
                 listForm.setContenant(rs.getString("contenant"));
                 
                 listCom.add(listForm);
                 
            }
             
        } catch (SQLException ex) {
            Logger.getLogger(CommentairesService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return listCom;
    
    }
       public ResultSet RechercherComByImm(String i)
    {
        ResultSet rs=null;
        try {  
            String requete = "SELECT * FROM commentaires WHERE refuge='"+i+"'";
            rs = ste.executeQuery(requete);
             }catch (SQLException ex) {
                 System.out.println(" erreur RechercheCommentairebyImm()");
        }
        return rs ;

       
    }
     
     }