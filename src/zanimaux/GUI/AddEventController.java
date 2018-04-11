/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package zanimaux.GUI;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.HashSet;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
<<<<<<< HEAD
=======
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
>>>>>>> d361910e9a9b039362e0e66344f8e815ae638cfb
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javax.imageio.ImageIO;
import zanimaux.Service.EvenementService;
import zanimaux.Service.Userservice;
import zanimaux.entities.Evenement;
import zanimaux.entities.User;
import zanimaux.util.Session;
import zanimaux.util.Validation;

/**
 * FXML Controller class
 *
 * @author Maroua
 */
public class AddEventController implements Initializable {
    
    @FXML
    private ImageView iv;
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
    private Label lbl;
    @FXML
    private Button btnBackevnt;
    @FXML
    private ImageView iv1;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }
    
    public String handle(){
        FileChooser fileChooser = new FileChooser();

        //Set extension filter
        FileChooser.ExtensionFilter extFilterJPG = new FileChooser.ExtensionFilter("JPG files (*.jpg)", "*.JPG");
        FileChooser.ExtensionFilter extFilterPNG = new FileChooser.ExtensionFilter("PNG files (*.png)", "*.PNG");
        fileChooser.getExtensionFilters().addAll(extFilterJPG, extFilterPNG);

        //Show open file dialog
        File file = fileChooser.showOpenDialog(null);
        String filePath = file.getName();
        try {
            BufferedImage bufferedImage = ImageIO.read(file);
            Image image = SwingFXUtils.toFXImage(bufferedImage, null);
            iv.setImage(image);

        } catch (IOException ex) {
            System.err.println(ex);
        }

        return filePath;
    }    

    @FXML
    private void chooseAction(ActionEvent event) {
        BtnChoixImage.setText(handle());
    
    }
    
   
    private void goToAffiche() {
        try {
        Stage stage=(Stage) buttonEventCreate.getScene().getWindow(); 
        stage.setTitle("affiche");
        Parent root = FXMLLoader.load(getClass().getResource("afficheEvent.fxml"));
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
        } catch (IOException ex) {
           Logger.getLogger(accueilOumaimaController.class.getName()).log(Level.SEVERE, null, ex);
       }
        
    }


    @FXML
    private void CreateEvent(ActionEvent event)throws SQLException, IOException  {
         User usr = Session.getLoggedInUser();
        
        EvenementService se= new EvenementService();
           LocalDate d = dateDebut.getValue();
         Date dated = Date.from(d.atStartOfDay(ZoneId.systemDefault()).toInstant());
  
    
   
             LocalDate d2 = dateFin.getValue();
         Date datef = Date.from(d2.atStartOfDay(ZoneId.systemDefault()).toInstant());
<<<<<<< HEAD
=======
         
        String nbP =  nbPlace.getText().toString();
        int i=0;
         if (lieu.getText().isEmpty()) {
             Alert alert = new Alert(AlertType.WARNING, "Veuillez Saisir un lieu", ButtonType.OK);
        alert.showAndWait();
            i++;
        }
      
         Date date = new Date();
         if(dated.compareTo(date) <= 0 ||(dated.equals("")))  
       {
           i++;
       Alert alert = new Alert(AlertType.WARNING, "Date invalide: Un évenement doit être créer avant au moins d'un jour", ButtonType.OK);
        alert.showAndWait();}
         else if(datef.compareTo(dated) <= 0 || (datef.equals("")))  
       { 
           i++;
       Alert alert = new Alert(AlertType.WARNING, "Veuillez choisir des dates cohérentes", ButtonType.OK);
        alert.showAndWait();
       }
       
        if (type.getText().isEmpty()) {
            Alert alert = new Alert(AlertType.WARNING, "Veuillez Saisir un type", ButtonType.OK);
        alert.showAndWait();
             i++;
        }
        if (titre.getText().isEmpty()) {
            Alert alert = new Alert(AlertType.WARNING, "Veuillez Saisir un titre", ButtonType.OK);
        alert.showAndWait();
             i++;
        }
        if (description.getText().isEmpty()) {
             Alert alert = new Alert(AlertType.WARNING, "Veuillez Saisir une description", ButtonType.OK);
        alert.showAndWait();
             i++;
        }
>>>>>>> d361910e9a9b039362e0e66344f8e815ae638cfb
       
      if(i==0)
      { 
       Evenement e=new Evenement(usr.getCin(),lieu.getText(),dated,datef,type.getText(),titre.getText(),description.getText(),Integer.parseInt(nbPlace.getText()),BtnChoixImage.getText());
           
           se.ajouterEvenement(e);
         System.out.println("evenement ajouté");
         goToAffiche();
        
       }

    
    
 
          
        }

    @FXML
    private void retour(ActionEvent event) {
         try {
            Stage stage = (Stage) btnBackevnt.getScene().getWindow();            
            stage.setTitle("affiche");
            Parent root = FXMLLoader.load(getClass().getResource("afficheEvent.fxml"));
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        } catch (IOException ex) {
            Logger.getLogger(AfficheEventController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}   
