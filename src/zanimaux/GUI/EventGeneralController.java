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
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Maroua
 */
public class EventGeneralController implements Initializable {

    @FXML
    private Label LogOut;
    @FXML
    private Button addEventBtn;
    @FXML
    private Button listEvent;
   

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void Deconnexion(MouseEvent event) {
    }

    @FXML
    private void goToAddEvent(ActionEvent event)throws SQLException, IOException  {
         try {
        Stage stage=(Stage) addEventBtn.getScene().getWindow(); 
        stage.setTitle("Ajouter Evenement");
        Parent root = FXMLLoader.load(getClass().getResource("addEvent.fxml"));
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
        } catch (IOException ex) {
           Logger.getLogger(EventGeneralController.class.getName()).log(Level.SEVERE, null, ex);
       }

    }
    

    @FXML
    public void goToAfficherEvent(ActionEvent event) {
        try {
        Stage stage=(Stage) listEvent.getScene().getWindow(); 
        stage.setTitle("consulter Evenement");
        Parent root = FXMLLoader.load(getClass().getResource("afficheEvent.fxml"));
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
        } catch (IOException ex) {
           Logger.getLogger(EventGeneralController.class.getName()).log(Level.SEVERE, null, ex);
       }
    }
    
}
