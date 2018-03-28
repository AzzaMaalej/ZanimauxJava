/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package zanimaux.GUI;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;
import zanimaux.Service.EvenementService;
import zanimaux.Service.Userservice;
import zanimaux.entities.Evenement;
import zanimaux.entities.User;

/**
 * FXML Controller class
 *
 * @author Maroua
 */
public class AddEventController implements Initializable {

    @FXML
    private AnchorPane AnchorPaneEvent;
    @FXML
    private Button BtnChoixImage;
    @FXML
    private Button buttonEventCreate;
    @FXML
    private TextField lieu;
    @FXML
    private DatePicker dateDebut;
    @FXML
    private DatePicker dateFin;
    @FXML
    private TextField type;
    @FXML
    private TextField titre;
    @FXML
    private TextArea description;
    @FXML
    private TextField nbPlace;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void chooseAction(ActionEvent event) {
         FileChooser fc = new FileChooser();
        File selectedFile = fc.showOpenDialog(null);
        if(selectedFile != null){
            selectedFile.getAbsolutePath();
            
        }else{
            System.out.println("fichier invalide"); 
       }
    
    }

    @FXML
    private void CreateEvent(ActionEvent event)throws SQLException, IOException  {
        
        EvenementService se= new EvenementService();
            LocalDate d = dateDebut.getValue();
         Date dated = Date.from(d.atStartOfDay(ZoneId.systemDefault()).toInstant());
         
             LocalDate d2 = dateFin.getValue();
         Date datef = Date.from(d.atStartOfDay(ZoneId.systemDefault()).toInstant());
       
     
            
          Evenement e=new Evenement(lieu.getText(),dated,datef,type.getText(),titre.getText(),description.getText(),Integer.parseInt(nbPlace.getText()));
           
          
           se.ajouterEvenement(e);
         System.out.println("evenement ajouté");
        
       }
 
          
        }
   
