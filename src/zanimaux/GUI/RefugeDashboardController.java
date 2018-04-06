/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package zanimaux.GUI;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import zanimaux.Service.Userservice;
import zanimaux.entities.User;
import zanimaux.util.Session;

/**
 * FXML Controller class
 *
 * @author Azza
 */
public class RefugeDashboardController implements Initializable {

    @FXML
    private Button GestRef;
    @FXML
    private Button GestAnim;
    @FXML
    private Button btnprofil;
    @FXML
    private Button btnClient;
    @FXML
    
    private Label LogOut;
   
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
         
            // TODO
        
    }  
        @FXML
     void GestRefuges(ActionEvent event) throws SQLException {
        try {
            
            Stage stage=(Stage) btnprofil.getScene().getWindow(); 
        stage.setTitle("Profil");
        Parent root = FXMLLoader.load(getClass().getResource("GestionRefuges.fxml"));
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
        } catch (IOException ex) {
           Logger.getLogger(RefugeDashboardController.class.getName()).log(Level.SEVERE, null, ex);
       }
     }
        @FXML
     void GestAnimaux(ActionEvent event) throws SQLException {

        try {
         Stage stage=(Stage) btnprofil.getScene().getWindow(); 
        stage.setTitle("Profil");
        Parent root = FXMLLoader.load(getClass().getResource("GestionAnimaux.fxml"));
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
        } catch (IOException ex) {
           Logger.getLogger(RefugeDashboardController.class.getName()).log(Level.SEVERE, null, ex);
       }
        

    }

    @FXML
    private void VoirProfil(ActionEvent event) {
         try {
                         
        Stage stage=(Stage) btnprofil.getScene().getWindow(); 
        stage.setTitle("Profil");
        Parent root = FXMLLoader.load(getClass().getResource("ProfilManager.fxml"));
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
        } catch (IOException ex) {
           Logger.getLogger(accueilOumaimaController.class.getName()).log(Level.SEVERE, null, ex);
       }
    }

    @FXML
    private void VoirCoteClient(ActionEvent event) {
         try {
        Stage stage=(Stage) btnClient.getScene().getWindow(); 
        stage.setTitle("Les refuges");
        Parent root = FXMLLoader.load(getClass().getResource("RefugeClient.fxml"));
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
        } catch (IOException ex) {
           Logger.getLogger(RefugeDashboardController.class.getName()).log(Level.SEVERE, null, ex);
       }
    }

    @FXML
    private void Deconnexion(MouseEvent event) {
          Session.setLoggedInUser(null);
          Parent root;
             try {
                 root = FXMLLoader.load(getClass().getResource("login.fxml"));
                 Stage myWindow = (Stage) LogOut.getScene().getWindow();
                 Scene sc = new Scene(root);
                 myWindow.setScene(sc);
                 myWindow.setTitle("Login");
                 myWindow.show();
             } catch (IOException ex) {
                 Logger.getLogger(RefugeDashboardController.class.getName()).log(Level.SEVERE, null, ex);
             }
    }

    
}
