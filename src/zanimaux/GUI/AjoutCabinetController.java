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
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javax.imageio.ImageIO;
import zanimaux.Service.CabinetDao;


/**
 * FXML Controller class
 *
 * @author Mariam
 */
public class AjoutCabinetController implements Initializable {

    @FXML
    private ImageView iv;
    @FXML
    private TextField imm;
    @FXML
    private TextField mail;
    @FXML
    private TextField tel;
    @FXML
    private TextField fax;
    @FXML
    private TextField rue;
    @FXML
    private TextField ville;
    @FXML
    private TextField codep;
    @FXML
    private Button saveb;
    @FXML
    private Button returnb;
    @FXML
    private TextField picturepath;
    @FXML
    private Label enre;
    @FXML
    private Label obg;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    public String handle() {
        FileChooser fileChooser = new FileChooser();

        //Set extension filter
        FileChooser.ExtensionFilter extFilterJPG = new FileChooser.ExtensionFilter("JPG files (*.jpg)", "*.JPG");
        FileChooser.ExtensionFilter extFilterPNG = new FileChooser.ExtensionFilter("PNG files (*.png)", "*.PNG");
        fileChooser.getExtensionFilters().addAll(extFilterJPG, extFilterPNG);

        //Show open file dialog
        File file = fileChooser.showOpenDialog(null);
        String filePath = file.getAbsolutePath();
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
    private void uploadpic(MouseEvent event) {

        picturepath.setText(handle());

    }

    @FXML
    private void save(ActionEvent event) throws IOException {
        CabinetDao cb = new CabinetDao();
        int telp = Integer.parseInt(tel.getText());
        int faxp = Integer.parseInt(fax.getText());
        int code = Integer.parseInt(codep.getText());
        int i=0;
        if (imm.getText().isEmpty()) {
            imm.setStyle("-fx-text-inner-color: red;");
            i++;
        }
        if (mail.getText().isEmpty()) {
            mail.setStyle("-fx-text-inner-color: red;");
            i++;
        }
        if (tel.getText().isEmpty()) {
            tel.setStyle("-fx-text-inner-color: red;");
            i++;
        }
        if (fax.getText().isEmpty()) {
            fax.setStyle("-fx-text-inner-color: red;");
             i++;
        }
        if (rue.getText().isEmpty()) {
            rue.setStyle("-fx-text-inner-color: red;");
             i++;
        }
        if (ville.getText().isEmpty()) {
            ville.setStyle("-fx-text-inner-color: red;");
             i++;
        }
        if (codep.getText().isEmpty()) {
            codep.setStyle("-fx-text-inner-color: red;");
             i++;
        }
      if(i==0)
      { cb.ajouterCabinet(imm.getText(), mail.getText(), telp, faxp, rue.getText(), ville.getText(), code, picturepath.getText());
        
        enre.setText("réussi");}
      else
      {
          obg.setVisible(true);
      }

    }

    @FXML
    private void retourner(ActionEvent event) {
        
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("AffichageCabinets.fxml"));
            Parent root = (Parent) fxmlLoader.load();
            Stage secondStage = new Stage();
            secondStage.setScene(new Scene(root));
            Stage stage = (Stage) imm.getScene().getWindow();
            // do what you have to do
            stage.hide();
            secondStage.show();
        } catch (IOException ex) {
            Logger.getLogger(AjoutCabinetController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
    //////////////////////////////////////////////

    @FXML
    @SuppressWarnings("empty-statement")
    private void testint(KeyEvent event) {
        if(tel.getText().trim().length()>0)
        {
        try {
           int i=Integer.parseInt(tel.getText());;
        }
         catch (NumberFormatException e)
         { obg.setText("veuillez entrer une valeur valide");
         obg.setVisible(true);
          saveb.setDisable(true);
            }
  }
        else
        {
          obg.setVisible(false);
           saveb.setDisable(false);
        }
        }
       

    

    @FXML
    private void testint1(KeyEvent event) {
         if(fax.getText().trim().length()>0)
        {
       try {
           int i=Integer.parseInt(fax.getText());;
        }
         catch (NumberFormatException e)
         { obg.setText("veuillez entrer une valeur valide");
         obg.setVisible(true);
          saveb.setDisable(true);
            }
            }
          else
        {
          obg.setVisible(false);
           saveb.setDisable(false);
        }
        }
    

    @FXML
    private void testint2(KeyEvent event) {
        if(codep.getText().trim().length()>0)
        {
        try {
           int i=Integer.parseInt(codep.getText());;
        }
         catch (NumberFormatException e)
         { obg.setText("veuillez entrer une valeur valide");
         obg.setVisible(true);
          saveb.setDisable(true);
            }
            }
         else
        {
          obg.setVisible(false);
            saveb.setDisable(false);
        }
    }

}
