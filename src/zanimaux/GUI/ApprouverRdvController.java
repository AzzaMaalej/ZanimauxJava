/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package zanimaux.GUI;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import zanimaux.Service.Articleservice;
import zanimaux.Service.CabinetDao;
import zanimaux.Service.RendezvsService;
import zanimaux.Service.Userservice;
import zanimaux.entities.Articles;
import zanimaux.entities.Cabinet;
import zanimaux.entities.Rendezvs;
import zanimaux.entities.User;
import zanimaux.util.Session;
import zanimaux.util.alertHelper;
import zanimaux.util.sendSMS;

/**
 * FXML Controller class
 *
 * @author Mariam
 */
public class ApprouverRdvController implements Initializable {
    @FXML
    private TableView Liste_Demande;
    @FXML
    private TableColumn column_Client;
    @FXML
    private TableColumn column_DateDebut;
    @FXML
    private Button button_approuverDemande;
    @FXML
    private Label btnRetour;
    @FXML
    private Label LogOut;
    @FXML
    private Button button_supprimerDemande;
    

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        List<Rendezvs> listDemandes = new ArrayList<>();
        RendezvsService daorv = RendezvsService.getInstance();
        System.out.println(Session.getLoggedInUser().getCin());
        Cabinet cb = new Cabinet();
        CabinetDao cd=  new CabinetDao();
        String imm = cd.getByVet(Session.getLoggedInUser().getCin()).getImmatriculeCabinet();
        listDemandes = daorv.getAllByVet(imm);
         System.out.println(listDemandes);
        ObservableList<Rendezvs> data = FXCollections.observableArrayList(listDemandes);
        
        column_Client.setCellValueFactory(
            new PropertyValueFactory<Rendezvs,String>("cin")
        );
        
        column_DateDebut.setCellValueFactory(
            new PropertyValueFactory<Rendezvs,String>("heurerdv")
        );
        
       
        
        Liste_Demande.setItems(data);
        // TODO
    }    
   
      
       

    @FXML
    private void ApprouverDemande(ActionEvent event) throws SQLException {
          Rendezvs dem = (Rendezvs) Liste_Demande.getSelectionModel().getSelectedItem();
    
         if(dem == null){
            alertHelper.afficher("Erreur", "Choisir une demande");
            }else{
       
        Userservice uc = new Userservice();
        User client = new User();
        client =  uc.UserByCin(dem.getCin());
        
        
       String num = client.getTelephone();
        System.out.println(num);
        
        sendSMS  send = new sendSMS() ;
        
        send.sendSms("216"+num ,"Votre Rendez vs est accepté");
       
    }}

    @FXML
    private void logOut(MouseEvent event) {
         Session.setLoggedInUser(null);
        Parent root;
             try {
                 root = FXMLLoader.load(getClass().getResource("login.fxml"));
                 Stage myWindow = (Stage) Liste_Demande.getScene().getWindow();
                 Scene sc = new Scene(root);
                 myWindow.setScene(sc);
                 myWindow.setTitle("Login");
                 myWindow.show();
             } catch (IOException ex) {
                 Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
             }
    }

    @FXML
    private void Retour(MouseEvent event) {
         Parent root;
             try {
                 root = FXMLLoader.load(getClass().getResource("VetDashboard.fxml"));
                 Stage myWindow = (Stage) Liste_Demande.getScene().getWindow();
                 Scene sc = new Scene(root);
                 myWindow.setScene(sc);
                 myWindow.setTitle("Vétérinaire");
                 myWindow.show();
             } catch (IOException ex) {
                 Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
             }
    }

   
    public void resetTable()
    {
        List<Rendezvs> listDemandes = new ArrayList<>();
        RendezvsService daorv = RendezvsService.getInstance();
        System.out.println(Session.getLoggedInUser().getCin());
        Cabinet cb = new Cabinet();
        CabinetDao cd=  new CabinetDao();
        String imm = cd.getByVet(Session.getLoggedInUser().getCin()).getImmatriculeCabinet();
        listDemandes = daorv.getAllByVet(imm);
        
       
        ObservableList<Rendezvs> data = FXCollections.observableArrayList(listDemandes);
        Liste_Demande.setItems(data);
    }

    @FXML
    private void SupprimerDemande(ActionEvent event) throws SQLException {
          Rendezvs dem = (Rendezvs) Liste_Demande.getSelectionModel().getSelectedItem();
    
         if(dem == null){
            alertHelper.afficher("Erreur", "Choisir une demande");
            }else{
       
        Userservice uc = new Userservice();
        User client = new User();
        client =  uc.UserByCin(dem.getCin());
        

           
        
//       String num = client.getTelephone();
//        System.out.println(num);
//        
//        sendSMS  send = new sendSMS() ;
//        
//        send.sendSms("216"+num ,"Votre Rendez vs est annulé");
       
             RendezvsService service= new RendezvsService();
             service.createNotificationRdv(dem.getIdrdv());
            service.supprimerrdv(dem.getIdrdv());
            System.out.println("SUCCESS");
                    resetTable();
                    //resetTableData();
           
             }

    }
    
}
