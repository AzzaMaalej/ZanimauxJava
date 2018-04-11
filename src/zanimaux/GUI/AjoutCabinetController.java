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
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javax.imageio.ImageIO;
import zanimaux.Service.CabinetDao;
import zanimaux.util.Session;

/**
 * FXML Controller class
 *
 * @author Mariam
 */
public class AjoutCabinetController implements Initializable {
    @FXML
    private TextField telcab;
    @FXML
    private TextField adressecab;
    @FXML
    private TextField villecab;
    @FXML
    private TextField codepostcab;
    @FXML
    private TextField faxcab;
    @FXML
    private TextField emailcab;
    @FXML
    private TextField path;
    @FXML
    private Button btnvalid2;
    @FXML
    private ImageView imageuser;
    @FXML
    private Label emaillabelcab;
    @FXML
    private Label telLabel1;
    @FXML
    private Label adresseLabel1;
    @FXML
    private Label villeLabel1;
    @FXML
    private Label codePostaleLabel1;
    @FXML
    private Label faxlabel;
    @FXML
    private TextField immcab;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void uploadImage(ActionEvent event) {
    }

    @FXML
    private void validerinfocabinet(ActionEvent event) {
        CabinetDao cb = new CabinetDao();
        int telp = Integer.parseInt(telcab.getText());
        int faxp = Integer.parseInt(faxcab.getText());
        int code = Integer.parseInt(codepostcab.getText());
        int i=0;
        if (imageuser.getImage().isBackgroundLoading()) {
            imageuser.setStyle("-fx-text-inner-color: red;");
            i++;
        }
         if (immcab.getText().isEmpty()) {
            immcab.setStyle("-fx-text-inner-color: red;");
            i++;
        }
        if (emailcab.getText().isEmpty()) {
            emailcab.setStyle("-fx-text-inner-color: red;");
            i++;
        }
        if (telcab.getText().isEmpty()) {
            telcab.setStyle("-fx-text-inner-color: red;");
            i++;
        }
        if (faxcab.getText().isEmpty()) {
            faxcab.setStyle("-fx-text-inner-color: red;");
             i++;
        }
        if (adressecab.getText().isEmpty()) {
            adressecab.setStyle("-fx-text-inner-color: red;");
             i++;
        }
        if (villecab.getText().isEmpty()) {
            villecab.setStyle("-fx-text-inner-color: red;");
             i++;
        }
        if (codepostcab.getText().isEmpty()) {
            codepostcab.setStyle("-fx-text-inner-color: red;");
             i++;
        }
      if(i==0)
      { cb.ajouterCabinet(path.getText(), emailcab.getText(), telp, faxp, adressecab.getText(), villecab.getText(), code, path.getText());
        
       System.out.println("Ajout réussi");}
      

    }
    
      public String handle() {
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
            imageuser.setImage(image);

        } catch (IOException ex) {
            System.err.println(ex);
        }

        return filePath;
    }

    

    @FXML
    private void uploadpic(MouseEvent event) {
          path.setText(handle());
    }

    @FXML
    private void logOut(MouseEvent event) {
          Session.setLoggedInUser(null);
        Parent root;
             try {
                 root = FXMLLoader.load(getClass().getResource("login.fxml"));
                 Stage myWindow = (Stage) immcab.getScene().getWindow();
                 Scene sc = new Scene(root);
                 myWindow.setScene(sc);
                 myWindow.setTitle("Login");
                 myWindow.show();
             } catch (IOException ex) {
                 Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
             }
    }
    
}
