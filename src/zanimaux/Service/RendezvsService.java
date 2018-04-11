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
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import zanimaux.Technique.DataSource;
import zanimaux.entities.Articles;
import zanimaux.entities.Rendezvs;
import zanimaux.entities.User;
import zanimaux.entities.notification_rdv;

/**
 *
 * @author Mariam
 */
public class RendezvsService {
    private final Connection cnx;
    private static RendezvsService instance;
    
    public RendezvsService() {
        cnx = DataSource.getInstance().getCon();
    }
    
    public static RendezvsService getInstance()
    {
        if (instance == null) {
            instance = new RendezvsService();
        }
        return instance; 
    }
    
    public boolean ajouterrdv (Rendezvs rdv)
    {
        int numberRows;
        try {
            String requete = "INSERT INTO rendezvs (cin,immatriculecabinet,heurerdv)  VALUES (?,?,?)";
            PreparedStatement pst = cnx.prepareStatement(requete);
            java.util.Date Heurerdv = new Date(rdv.getHeurerdv().getTime());
        
            pst.setString(1,rdv.getCin());
          
            pst.setString(2,rdv.getImmatriculecabinet());
             
            pst.setDate(3, (Date) Heurerdv);
            //pst.setDate(3,rdv.getHeurerdv());
            
            
            numberRows = pst.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(RendezvsService.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
        if(numberRows == 0){
            return false;
        }else{
            return true;
        }
    }
     public void supprimerrdv(int id)
         {
                
             String requete="DELETE FROM rendezvs WHERE idrdv='"+id+"' ";     
             Statement st;
             try {
              st = cnx.createStatement(); 
              st.executeUpdate(requete);
              System.out.println("Supprimé avec succés");

            } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
     }
      public List<Rendezvs> getAllByVet(String imm)
   {
        List<Rendezvs> listrdv = new ArrayList<>();
        try {
            String requete = "select * from rendezvs where immatriculecabinet='"+imm+"'";
            Statement st = cnx.createStatement();
            ResultSet rs = st.executeQuery(requete);
            
            while(rs.next()){
               Rendezvs rdv = new Rendezvs();
                rdv.setIdrdv(rs.getInt("idrdv"));
                rdv.setCin(rs.getString("cin"));
                rdv.setImmatriculecabinet(imm);
                rdv.setHeurerdv(rs.getDate("heurerdv"));
                
                listrdv.add(rdv);
                
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(Articleservice.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
        return listrdv;
    }
       public List<Rendezvs> getAllByuser(String cin)
   {
        List<Rendezvs> listrdvu = new ArrayList<>();
        try {
            String requete = "select * from rendezvs where cin='"+cin+"'";
            Statement st = cnx.createStatement();
            ResultSet rs = st.executeQuery(requete);
            
            while(rs.next()){
               Rendezvs rdv = new Rendezvs();
                rdv.setIdrdv(rs.getInt("idrdv"));
                rdv.setCin(cin);
                rdv.setImmatriculecabinet(rs.getString("immatriculecabinet"));
                rdv.setHeurerdv(rs.getDate("heurerdv"));
                
                listrdvu.add(rdv);
                
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(Articleservice.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
        return listrdvu;
    }
       
       
       
        public Rendezvs findrdvById(int id)
    {
        Rendezvs rdv = new Rendezvs();
        int count = 0;
           
        String requete=" select * from rendezvs where idrdv='"+id+"'" ;
        try{
            Statement st = cnx.createStatement();
            ResultSet rsl = st.executeQuery(requete);
            while(rsl.next())
            {
               rdv.setCin(rsl.getString("cin"));
                rdv.setImmatriculecabinet(rsl.getString("immatriculecabinet"));
               rdv.setHeurerdv(rsl.getDate("heurerdv"));
              
                
                count++;
            }
           if(count == 0){
                return null;
           }else{
               return rdv;
           }  
        }
        catch (SQLException ex) {
            Logger.getLogger(RendezvsService.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
   }
        
        
        public void createNotificationRdv(int id) throws SQLException {
        
        String req="INSERT INTO notification_rdv (idrdv,vu) VALUES (?,?)";
        PreparedStatement pre = cnx.prepareStatement(req);
        try {
            pre= cnx.prepareStatement(req);
        } catch (SQLException ex) {
            Logger.getLogger(RendezvsService.class.getName()).log(Level.SEVERE, null, ex);
        }
        pre.setInt(1,id); 
        pre.setInt(2,0);
        pre.executeUpdate();
       String sql="select id from notification_rdv ORDER BY id DESC LIMIT 1";
       PreparedStatement pr=cnx.prepareStatement(sql);
       ResultSet rs= pr.executeQuery();
       rs.next();
       int idNotif=rs.getInt("id");
        
        System.out.println("Notification rdv Ajoutee");
        RendezvsService service = new RendezvsService();
        ArrayList<Rendezvs> listr = new ArrayList<Rendezvs>();
        Userservice us = new Userservice(); 
        Rendezvs v = service.findrdvById(id);
       
        User u = us.UserByCin(v.getCin());
        
            String sql1="insert into notif_user_rdv (cin,id_notif,vu) values (?,?,?)";
            PreparedStatement pr1;
            try {
            pr1= cnx.prepareStatement(sql1);
            pr1.setString(1, u.getCin());
            pr1.setInt(2, idNotif);
            pr1.setInt(3, 0);
            pr1.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(RendezvsService.class.getName()).log(Level.SEVERE, null, ex);
        }
            
        }
        
    public ArrayList<User> getUserNotifNotVu(){
        String sql="select * from notification_rdv";
        ArrayList<User> userList=new ArrayList<>();
        try {
            Statement st=cnx.createStatement();
            ResultSet rs=st.executeQuery(sql);
            ArrayList<notification_rdv> notifList=new ArrayList<>();
            while(rs.next()){
                notification_rdv n=new notification_rdv();
                n.setId(rs.getInt("id"));
                n.setIdrdv(rs.getInt("idrdv"));
                notifList.add(n);
                
            }
            for(notification_rdv notif : notifList){
                String sql1="select cin from notif_user_rdv where vu=0 and id_notif="+notif.getId();
                Statement st1=cnx.createStatement();
                ResultSet rs1=st1.executeQuery(sql1);
                while(rs1.next()){
                    Userservice u = new Userservice();
                    User user=new User();
                    user= u.UserByCin(rs1.getString("cin"));
                    userList.add(user);
//                    System.out.println(userList);
//                     System.out.println(user);
                }
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(RendezvsService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return userList;
    }

    
    public void setUserNotifVu(String idUser) throws SQLException{
        String sql="UPDATE notif_user_rdv set vu = 1 where cin="+idUser;
        Statement pre=cnx.createStatement();
        pre.executeUpdate(sql);
    }


        
        
        
        
        
    }

        
        
        
        

