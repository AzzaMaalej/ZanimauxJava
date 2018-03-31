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
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javax.imageio.ImageIO;
import zanimaux.Service.ParcService;
import zanimaux.entities.Parc;
import zanimaux.entities.User;
import zanimaux.util.Validation;

/**
 * FXML Controller class
 *
 * @author BelhassenLimam
 */
public class AjoutParcController implements Initializable {

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
    private TextField idp;
    @FXML
    private TextField nom;
    @FXML
    private TextField adr;
    @FXML
    private TextField ville;
    @FXML
    private TextField codep;
    @FXML
    private TextField cin;
    @FXML
    private ChoiceBox<String> catg;
    @FXML
    private Button btn;
    @FXML
    private Button photo;
    
    
    
    ObservableList<String> list=FXCollections.observableArrayList("val1","val2");
    @FXML
    private Label idpLabel;
    @FXML
    private Label nomLabel;
    @FXML
    private Label adrLabel;
    @FXML
    private Label villeLabel;
    @FXML
    private Label codepLabel;
    @FXML
    private Label cinLabel;
    @FXML
    private Label photoLabel;
    @FXML
    private Label catgLabel;
    @FXML
    private ImageView iv;
    

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        List<String> list = new ArrayList();
        list.add("Chien");
        list.add("Chevaux");
        list.add("Autre");
        ObservableList<String> ob = FXCollections.observableArrayList();
        ob.addAll(list);
        catg.setItems(ob);
    }    
    
    
     public String handle(){
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
    private void chooseAction(ActionEvent event) {
        photo.setText(handle());
    
    }
    
         public boolean controleSaisie2() throws IOException, SQLException {
        boolean saisie = true;
        String z;
        int r = 0;
        z = catg.getValue();
        if ("Chien".equals(z)) {
            r = 1;
        } else if ("Chevaux".equals(z)) {
            r = 2;
        } else if ("Autre".equals(z)) {
            r = 3;
        }
        if((r!=1)&&(r!=2)&&(r!=3)){
            catgLabel.setText("* vous devez choisir votre rôle");
            saisie = false;
        }

        if (!Validation.texNum(idp, idpLabel, "* le numéro doit contenir que des chiffres")) {
            saisie = false;
        }

        if (!Validation.textalphabet(nom, nomLabel, "* le nom doit contenir que des lettres")) {
            saisie = false;
        }
       
        if (!Validation.textValidation(adr, adrLabel, "* tout les champs doivent etre remplis")) {
            saisie = false;
        }
        if (!Validation.textValidation(ville, villeLabel, "* tout les champs doivent etre remplis")) {
            saisie = false;
        }
        if (!Validation.textValidation(codep, codepLabel, "* tout les champs doivent etre remplis")) {
            saisie = false;
        }
       
        if (!Validation.textValidation(cin, cinLabel, "* tout les champs doivent etre remplis")) {
                saisie = false;
                
         }
        return saisie;

        }
         
         @FXML
    private void valider(ActionEvent event) throws SQLException, IOException  {
        ParcService a= new ParcService();
        if ((this.controleSaisie2()) ) {

        
             String z;
            String r="Autre";
            z = catg.getValue();
             if ("Chien".equals(z)) {
            r = "Chien";
        } else if ("Chevaux".equals(z)) {
            r = "Chevaux";
        } else if ("Autre".equals(z)) {
            r = "Autre";
        }
     
              try {
          Parc u=new Parc(idp.getText(),nom.getText(),r,adr.getText(),ville.getText(),Integer.parseInt(codep.getText()),photo.getText(),cin.getText());
           
          
           a.ajouterParc(u);
         System.out.println("Parc ajouté");
        } catch (SQLException ex) {
           Logger.getLogger(Zanimaux.class.getName()).log(Level.SEVERE, null, ex);
       }
 
         try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("Parc.fxml"));
            Parent root = (Parent) fxmlLoader.load();
            Stage secondStage = new Stage();
            secondStage.setScene(new Scene(root));
            Stage stage = (Stage) nom.getScene().getWindow();
            // do what you have to do
            stage.hide();
            secondStage.show();
        } catch (IOException ex) {
            Logger.getLogger(AjoutCabinetController.class.getName()).log(Level.SEVERE, null, ex);
        } 
        }

    }
    @FXML
    private void retourner(ActionEvent event) {
        
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("Parc.fxml"));
            Parent root = (Parent) fxmlLoader.load();
            Stage secondStage = new Stage();
            secondStage.setScene(new Scene(root));
            Stage stage = (Stage) nom.getScene().getWindow();
            // do what you have to do
            stage.hide();
            secondStage.show();
        } catch (IOException ex) {
            Logger.getLogger(AjoutCabinetController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
    
         
         
         
} 

