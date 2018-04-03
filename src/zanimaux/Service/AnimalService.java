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
import zanimaux.entities.Animal;
import zanimaux.entities.Produit;
import zanimaux.entities.Refuge;

/**
 *
 * @author Azza
 */
public class AnimalService {
     private static AnimalService instance;
    public static AnimalService getInstance() throws SQLException {
       
         if (instance == null) {
            instance = new AnimalService();
        }
        return instance;//To change body of generated methods, choose Tools | Templates.
    }
       public Connection con = DataSource.getInstance().getCon();
public Statement ste;

    public AnimalService() throws SQLException {
       ste = con.createStatement();
    }
    public ResultSet RechercherAnimalByImm(String i)
    {
        ResultSet rs=null;
        try {  
            String requete = "SELECT * FROM animal WHERE refuge='"+i+"'";
            rs = ste.executeQuery(requete);
             }catch (SQLException ex) {
                 System.out.println(" erreur RechercheAnimalbyImm()");
        }
        return rs ;

       
    }
    public List<Animal> ListerAnimalRefuge(String i){
         List<Animal> listAnimal = new ArrayList<>();
        Animal listForm= new Animal();
        try {  
            String requete = "SELECT * FROM animal WHERE refuge='"+i+"'";

           
            ResultSet rs = ste.executeQuery(requete);

             while(rs.next()){
                 listForm.setRefuge(rs.getString("refuge"));
                 listForm.setIdAnimal(rs.getInt("idAnimal"));
                 listForm.setType(rs.getString("type"));
                 listForm.setEtat(rs.getString("etat"));
                 listForm.setNomAnimal(rs.getString("nomAnimal"));
                 listForm.setPhotoAnimal(rs.getString("photoAnimal"));
                 listForm.setAge(rs.getInt("age"));               
                 listForm.setRace(rs.getString("race"));
                 listAnimal.add(listForm);
                 
            }
             
        } catch (SQLException ex) {
            Logger.getLogger(AnimalService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return listAnimal;
    
    }
     public boolean ModifierAnimal(Animal r)
    {
        int nbr_ligne;
        try{
            
            String requete="UPDATE animal set refuge=?,idAnimal=?, type=?, etat=?, nomAnimal=?, photoanimal=?, age=?,  race=?  WHERE idAnimal="+r.getIdAnimal()+"";
            PreparedStatement pst = con.prepareStatement(requete);
            pst.setString(1, r.getRefuge());
            pst.setInt(2,r.getIdAnimal());
            pst.setString(3,r.getType());
            pst.setString(4,r.getEtat());
            pst.setString(5,r.getNomAnimal());
            pst.setString(6,r.getPhotoAnimal());
            pst.setInt(7,r.getAge());
            pst.setString(8, r.getRace());
          
            
            nbr_ligne=pst.executeUpdate();
        }
        catch (SQLException ex) {
            Logger.getLogger(AnimalService.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
        if(nbr_ligne == 0){
            return false;
        }else{
            return true;
        }
        
    }
     public void SupprimerAnimal(int idAnimal)
{
  String requete="DELETE FROM animal WHERE idAnimal='"+idAnimal+"' ";     
        Statement st;
        try {
            st = con.createStatement(); 
            st.executeUpdate(requete);
      System.out.println("animal supprimé");

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
}
     public boolean ajouterAnimal(Animal r) throws SQLException{
       String requete = "INSERT INTO animal (refuge, type, etat, nomAnimal, photoanimal, age,  race) VALUES (?,?,?,?,?,?,?) ";
        
       try {
           
            PreparedStatement pst =con.prepareStatement(requete);
            pst.setString(1, r.getRefuge());
            
            pst.setString(2,r.getType());

            pst.setString(3,r.getEtat());
            pst.setString(4,r.getNomAnimal());
            pst.setString(5,r.getPhotoAnimal());
            pst.setInt(6,r.getAge());
            pst.setString(7,r.getRace());
            
             pst.executeUpdate();
            System.out.println("ajout animal reussit");
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
            return false;
    }
     
    
}
