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
import java.util.logging.Level;
import java.util.logging.Logger;
import zanimaux.Technique.DataSource;
import zanimaux.entities.Animal;

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
    { ResultSet rs=null;
        try {  
            String requete = "SELECT * FROM animal WHERE refuge='"+i+"'";
            rs = ste.executeQuery(requete);
             }catch (SQLException ex) {
                 System.out.println(" erreur RechercheAnimalbyImm()");
        }
        return rs ;

//        Animal listForm = new Animal();
//        try {  
//            String requete = "SELECT * FROM animal WHERE refuge='"+i+"'";
//
//           
//            ResultSet rs = ste.executeQuery(requete);
//
//             while(rs.next()){
//                 listForm.setRefuge(rs.getString("refuge"));
//                 listForm.setIdAnimal(rs.getInt("idAnimal"));
//                 listForm.setType(rs.getString("type"));
//                 listForm.setEtat(rs.getString("etat"));
//                 listForm.setNomAnimal(rs.getString("nomAnimal"));
//                 listForm.setPhotoAnimal(rs.getString("photoAnimal"));
//                 listForm.setAge(rs.getInt("age"));               
//                 listForm.setRace(rs.getString("race"));
//                 
//            }
//             
//        } catch (SQLException ex) {
//            Logger.getLogger(AnimalService.class.getName()).log(Level.SEVERE, null, ex);
//        }
//        return listForm;
    
    }
     
    
}
