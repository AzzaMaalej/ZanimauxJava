/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package zanimaux.GUI;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;

/**
 * FXML Controller class
 *
 * @author macbookpro
 */
public class AccueilController implements Initializable {

    @FXML
    private Button userName;
    @FXML
    private Button btn11;
    @FXML
    private Button btn1;
    @FXML
    private Pane pane;
    @FXML
    private AnchorPane anchorP;

 

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        

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
 
    
}
