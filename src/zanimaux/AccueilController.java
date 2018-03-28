
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package zanimaux;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Maroua
 */
public class AccueilController implements Initializable {

    @FXML
    private AnchorPane anchorP;
    @FXML
    private Button userName;
    @FXML
    private Pane pane;
    @FXML
    private Button btn11;
    @FXML
    private Button btn1;
    private AnchorPane AnchorPaneEvent;
    @FXML
    private Button evenement;
    @FXML
    private AnchorPane anchorEvent;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void showPane(MouseEvent event) {
        pane.setVisible(true);
    }

    @FXML
    private void connexionAction(ActionEvent event) {
    }

    @FXML
    private void hidePane(MouseEvent event) {
         pane.setVisible(false);

    }



    private void showEventAdd(ActionEvent event) throws IOException {
      
        Parent  add_event_parent = FXMLLoader.load(getClass().getResource("addEvent.fxml"));
        Scene add_event_scene = new Scene(add_event_parent);
        Stage app_stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        app_stage.setScene(add_event_scene);
        app_stage.show();
        
        }

    @FXML
    private void onClickEvenementAction(ActionEvent event) {
    }
       
    }
    

