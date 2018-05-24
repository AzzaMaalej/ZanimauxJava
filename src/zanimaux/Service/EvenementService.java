/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package zanimaux.Service;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import zanimaux.entities.Evenement;
import zanimaux.Technique.DataSource;
import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import zanimaux.entities.Articles;
import zanimaux.entities.Magasin;
import zanimaux.entities.User;
import zanimaux.util.Session;

/**
 *
 * @author Maroua
 */
public class EvenementService {
    public Connection con = DataSource.getInstance().getCon();
     public Statement ste;

    public EvenementService() throws SQLException {
        ste=con.createStatement();
    }
     
      
     public boolean ajouterEvenement(Evenement e)
    {
       String requete = "INSERT INTO evenement (cin,lieu,dateDebut, dateFin, type, titre, description,nb_place,nbParticipants,image_evt) VALUES (?,?,?,?,?,?,?,?,0,?) ";
        User usr = Session.getLoggedInUser();
       
       try {
           java.util.Date dateDeb = new Date(e.getDateDebut().getTime());
           java.util.Date dateFin = new Date(e.getDateFin().getTime());
            PreparedStatement pst = con.prepareStatement(requete);
            pst.setObject(1,usr.getCin());
            pst.setString(2,e.getLieu());
            pst.setDate(3, (Date) dateDeb);
            pst.setDate(4, (Date) dateFin);
             pst.setString(5,e.getType());
            pst.setString(6,e.getTitre());
            pst.setString(7,e.getDescription());
            pst.setInt(8,e.getNbPlace());
           // pst.setInt(9,0);
            pst.setString(9,e.getImageEvt());
            
            pst.executeUpdate();
            System.out.println("ajouté avec succés");
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
            return false;
    }
     
     
     public boolean modifierEvenement(int id,Evenement e)
    { 
    String requete="UPDATE evenement SET lieu=?, dateDebut=?, dateFin=?, type=? , titre=? , description=?,image_evt=?, nb_place=? where idEvt='"+id+"'";
        try {
           java.util.Date dateDeb = new Date(e.getDateDebut().getTime());
           java.util.Date dateFin = new Date(e.getDateFin().getTime());
            PreparedStatement pst =con.prepareStatement(requete);
            pst.setString(1,e.getLieu());
            pst.setDate(2, (Date) dateDeb);
            pst.setDate(3, (Date) dateFin);
            pst.setString(4,e.getType());
            pst.setString(5,e.getTitre()); 
            pst.setString(6,e.getDescription());
            pst.setString(7,e.getImageEvt());
            pst.setInt(8,e.getNbPlace());
             pst.executeUpdate();
            System.out.println("modifié avec succés");
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
            return false;
    }
     
     
      public void supprimerEvenement(int id)
         {
                
             String requete="DELETE FROM `Evenement` WHERE idEvt='"+id+"' ";     
             Statement st;
             try {
              st = con.createStatement(); 
              st.executeUpdate(requete);
              System.out.println("Supprimé avec succés");

            } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
     }
      
       public Evenement rechercheEvent(int i)
    { 

        Evenement listeEvent = new Evenement();
        try {  
            String requete = "SELECT * FROM evenement WHERE `idEvt`="+i;

           
            ResultSet rs = ste.executeQuery(requete);

             while(rs.next()){
                 listeEvent.setLieu(rs.getString("lieu"));
                 listeEvent.setDateDebut(rs.getDate("dateDebut"));
                 listeEvent.setDateFin(rs.getDate("dateFin"));
                 listeEvent.setType(rs.getString("type"));
                 listeEvent.setTitre(rs.getString("titre"));
                 listeEvent.setDescription(rs.getString("description"));
                 listeEvent.setNbPlace(rs.getInt("nb_place"));
                 listeEvent.setImageEvt(rs.getString("image_evt"));
            }
             
        } catch (SQLException ex) {
            Logger.getLogger(EvenementService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return listeEvent;
    
    }
       
        public ResultSet rechercheEvent()
    { 
        ResultSet rs=null;
        try {  
            String requete = "SELECT * FROM Evenement";
            rs = ste.executeQuery(requete);
             }catch (SQLException ex) {
                 System.out.println(" erreur rechercheEvent()");
        }
        return rs ;
    
    }
    public Evenement getByUser(String cin)
   {
       User usr = Session.getLoggedInUser();
       
        Evenement ev = new Evenement();
        try {
            String requete = "select * from evenement where cin='"+cin+"'";
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(requete);
            
            while(rs.next()){
                
               
               usr.setCin(cin);
               ev.setLieu(rs.getString("lieu"));
               ev.setDateDebut(rs.getDate("dateDebut"));
               ev.setDateFin (rs.getDate("dateFin"));
               ev.setType(rs.getString("type"));
               ev.setTitre(rs.getString("titre"));
               ev.setDescription(rs.getString("description"));
               ev.setNbPlace(rs.getInt("nb_place"));
               ev.setImageEvt(rs.getString("image_evt"));
                
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(EvenementService.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
        return ev;
    }
       
        public ResultSet AfficherEventById(int id)
    { 
        ResultSet rs=null;
        try {  
            String requete = "SELECT * FROM evenement WHERE idEvt='"+id+"' " ;
            
            rs = ste.executeQuery(requete);
            
             }catch (SQLException ex) {
                 System.out.println(" erreur");
        }

        return rs ;
    
    }
        
         public boolean updateNbParticipants(int id)
    { 
    String requete="UPDATE evenement SET  nbParticipants=nbParticipants+1 where idEvt='"+id+"'";
        try {
          
            PreparedStatement pst =con.prepareStatement(requete);
          
             pst.executeUpdate();
            System.out.println("modifié avec succés");
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
            return false;
    }
         
         public boolean updateNbParticipantsAfterAnnulation(int id)
    { 
    String requete="UPDATE evenement SET  nbParticipants=nbParticipants-1 where idEvt='"+id+"'";
        try {
          
            PreparedStatement pst =con.prepareStatement(requete);
          
             pst.executeUpdate();
            System.out.println("modifié avec succés");
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
            return false;
    }
         
         
        public ResultSet RechercheEvent(String motcle){
        ResultSet rs=null;
        try {
        String requete = "SELECT * FROM evenement WHERE lieu LIKE'"+'%'+motcle+'%'+"'OR type LIKE'"+'%'+motcle+'%'+"'" ;
         rs = ste.executeQuery(requete);
         }catch (SQLException ex) {
        System.out.println(" erreur");
        }
        
        return rs ;
}
                public ArrayList<Evenement> RechercheEvent1(String motcle){
                   ArrayList<Evenement> l= new ArrayList<>();
        ResultSet rs=null;
        try {
        String requete = "SELECT * FROM evenement WHERE lieu LIKE'"+'%'+motcle+'%'+"'OR type LIKE'"+'%'+motcle+'%'+"'" ;
         rs = ste.executeQuery(requete);
          Evenement listeEvent=new Evenement();

         while(rs.next()){
               listeEvent.setLieu(rs.getString("lieu"));
                 listeEvent.setDateDebut(rs.getDate("dateDebut"));
                 listeEvent.setDateFin(rs.getDate("dateFin"));
                 listeEvent.setType(rs.getString("type"));
                 listeEvent.setTitre(rs.getString("titre"));
                 listeEvent.setDescription(rs.getString("description"));
                 listeEvent.setNbPlace(rs.getInt("nb_place"));
                 listeEvent.setImageEvt(rs.getString("image_evt"));
                 l.add(listeEvent);
         }
        
         }catch (SQLException ex) {
        System.out.println(" erreur");
        }
        
        return l;
}
                 
         public List<Evenement> getAllEvent()
   {
        List<Evenement> listEvenement = new ArrayList<>();
        try {
            String requete = "select * from evenement ";
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(requete);
            
            while(rs.next()){
                Evenement evt = new Evenement();
                
               
                evt.setLieu(rs.getString("lieu"));
               evt.setDateDebut(rs.getDate("dateDebut"));
               evt.setDateFin (rs.getDate("dateFin"));
               evt.setType(rs.getString("type"));
               evt.setTitre(rs.getString("titre"));
               evt.setDescription(rs.getString("description"));
               evt.setNbPlace(rs.getInt("nb_place"));
               evt.setImageEvt(rs.getString("image_evt"));
               
                listEvenement.add(evt);
                
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(EvenementService.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
        return listEvenement;
    }

}
    


        
    

