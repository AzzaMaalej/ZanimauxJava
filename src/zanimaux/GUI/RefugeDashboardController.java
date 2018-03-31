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
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Azza
 */
public class RefugeDashboardController implements Initializable {

    @FXML
    private Button userName;
    @FXML
    private Pane pane;
    @FXML
    private Button btn11;
    @FXML
    private Button btn1;
    @FXML
    private AnchorPane anchorEvent;
    @FXML
    private Button GestRef;
    @FXML
    private Button GestAnim;

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
        Stage stage=(Stage) GestRef.getScene().getWindow(); 
        stage.setTitle("Gestion des refuges");
        Parent root = FXMLLoader.load(getClass().getResource("GestionRefuges.fxml"));
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
        } catch (IOException ex) {
           Logger.getLogger(RefugeController.class.getName()).log(Level.SEVERE, null, ex);
       }
     }
        @FXML
     void GestAnimaux(ActionEvent event) throws SQLException {

        try {
        Stage stage=(Stage) GestAnim.getScene().getWindow(); 
        stage.setTitle("Vos Refuges");
        Parent root = FXMLLoader.load(getClass().getResource("GestionAnimaux.fxml"));
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
        } catch (IOException ex) {
           Logger.getLogger(RefugeController.class.getName()).log(Level.SEVERE, null, ex);
       }
        

    }

    
}
