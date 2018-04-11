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
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javax.imageio.ImageIO;
import zanimaux.Service.AnnonceService;
import zanimaux.Service.EvenementService;
import zanimaux.entities.Annonce;
import zanimaux.entities.Evenement;
import zanimaux.entities.User;
import zanimaux.util.Session;

/**
 * FXML Controller class
 *
 * @author Maroua
 */
public class AddAnnonceController implements Initializable {

    @FXML
    private TextField typeA;
    @FXML
    private TextField titreA;
    @FXML
    private TextArea descriptionA;
    @FXML
    private Button BtnChoixImageA;
    @FXML
    private Button buttonAnnCreate;
    @FXML
    private ImageView iv;
    @FXML
    private AnchorPane AnchorPaneAnnonce;
    @FXML
    private Button btnBack;
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
        BtnChoixImageA.setText(handle());
    }
    
        private void goToAffiche() {
        try {
        Stage stage=(Stage) buttonAnnCreate.getScene().getWindow(); 
        stage.setTitle("affiche");
        Parent root = FXMLLoader.load(getClass().getResource("afficheAnnonce.fxml"));
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
        } catch (IOException ex) {
           Logger.getLogger(AddAnnonceController.class.getName()).log(Level.SEVERE, null, ex);
       }
        
    }

    @FXML
    private void CreateAnnonce(ActionEvent event) throws SQLException { 
          User usr = Session.getLoggedInUser();
        AnnonceService sa= new AnnonceService();
        int i=0;
                
        if (typeA.getText().isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.WARNING, "Veuillez Saisir un type", ButtonType.OK);
        alert.showAndWait();
             i++;
        }
        if (titreA.getText().isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.WARNING, "Veuillez Saisir un titre", ButtonType.OK);
        alert.showAndWait();
             i++;
        }
        if (descriptionA.getText().isEmpty()) {
             Alert alert = new Alert(Alert.AlertType.WARNING, "Veuillez Saisir une description", ButtonType.OK);
        alert.showAndWait();
             i++;
        }
       
      if(i==0)
      {
           
            
          Annonce a=new Annonce(usr.getCin(),typeA.getText(),titreA.getText(),descriptionA.getText(),BtnChoixImageA.getText());
           
          
           sa.ajouterAnnonce(a);
         System.out.println("annonce ajouté");
         goToAffiche();
    }
    }

    @FXML
    private void goBack(ActionEvent event) {
         try {
            Stage stage = (Stage) btnBack.getScene().getWindow();            
            stage.setTitle("affiche");
            Parent root = FXMLLoader.load(getClass().getResource("afficheAnnonce.fxml"));
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        } catch (IOException ex) {
            Logger.getLogger(AfficheEventController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
