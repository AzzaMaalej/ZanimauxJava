/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package zanimaux.GUI;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;

import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import zanimaux.Service.Userservice;
import zanimaux.entities.User;
import zanimaux.util.Validation;

/**
 * FXML Controller class
 *
 * @author macbookpro
 */
public class InscriptionController implements Initializable {
    
    @FXML
    private TextField nomId;
    @FXML
    private TextField prenomId;
    @FXML
    private TextField telId;
    @FXML
    private TextField adresseId;
    @FXML
    private TextField villeId;
    @FXML
    private TextField codePostaleId;
    @FXML
    private Button btnInscrit;
    @FXML
    private TextField usernameId;
    @FXML
    private TextField emailId;
    @FXML
    private PasswordField pwdId;
    @FXML
    private PasswordField confpwdId;
    @FXML
    private TextField cinId;
    @FXML
    private ChoiceBox<String> roleId;
    
    
    ObservableList<String> list=FXCollections.observableArrayList("val1","val2");
    @FXML
    private Label nomLabel;
    @FXML
    private Label usernameLabel;
    @FXML
    private Label prenomLabel;
    @FXML
    private Label emailLabel;
    @FXML
    private Label pwdLabel;
    @FXML
    private Label confpwdLabel;
    @FXML
    private Label cinLabel;
    @FXML
    private Label telLabel;
    @FXML
    private Label adresseLabel;
    @FXML
    private Label villeLabel;
    @FXML
    private Label codePostaleLabel;
    @FXML
    private Label roleLabel;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
    
     List<String> list = new ArrayList();
        list.add("Client");
        list.add("Magasinier");
        list.add("Vétérinaire");
	list.add("Dresseur");
        list.add("PetSitter");
        list.add("Proprietaire de refuge");
        
        ObservableList<String> ob = FXCollections.observableArrayList();
        ob.addAll(list);
        roleId.setItems(ob);
       

        // TODO
    }    
   

         public boolean controleSaisie2() throws IOException, SQLException {
        boolean saisie = true;
        String z;
        int r = 0;
        z = roleId.getValue();
        if ("Client".equals(z)) {
            r = 1;
        } else if ("Magasinier".equals(z)) {
            r = 2;
        } else if ("Proprietaire De Refuge".equals(z)) {
            r = 3;
        }else if ("Dresseur".equals(z)) {
            r = 4;
        }else if ("PetSitter".equals(z)) {
            r = 5;
        }else if ("Vétérinaire".equals(z)) {
            r = 6;
        }
        if((r!=1)&&(r!=2)&&(r!=3)&&(r!=4)&&(r!=5)&&(r!=6)){
            roleLabel.setText("* vous devez choisir votre rôle");
            saisie = false;
        }

        if (!Validation.textalphabet(nomId, nomLabel, "* le nom doit contenir que des lettre")) {
            saisie = false;
        }

        if (!Validation.textalphabet(prenomId, prenomLabel, "* le prenom doit contenir que des lettre")) {
            saisie = false;
        }
        if (!Validation.texMail(emailId, emailLabel, "* verifier votre mail")) {
            saisie = false;
        }

        if (!Validation.textValidation(nomId, nomLabel, "* tout les champs doivent etre remplis")) {
            saisie = false;
        }
        if (!Validation.textValidation(prenomId, prenomLabel, "* tout les champs doivent etre remplis")) {
            saisie = false;
        }

        if (!Validation.textValidation(emailId, emailLabel, "* tout les champs doivent etre remplis")) {
            saisie = false;
        }
        if (!Validation.textValidation(adresseId, adresseLabel, "* tout les champs doivent etre remplis")) {
            saisie = false;
        }
        if (!Validation.textValidation(villeId, villeLabel, "* tout les champs doivent etre remplis")) {
            saisie = false;
        }
        if (!Validation.textValidation(codePostaleId, codePostaleLabel, "* tout les champs doivent etre remplis")) {
            saisie = false;
        }
        if (!Validation.textValidation(usernameId, usernameLabel, "* tout les champs doivent etre remplis")) {
            saisie = false;
        }
        if (!Validation.textValidation(telId, telLabel, "* tout les champs doivent etre remplis")) {
            saisie = false;
        }
            if (!Validation.textValidation(cinId, cinLabel, "* tout les champs doivent etre remplis")) {
                saisie = false;
                
            }
        if (!Validation.texNum(cinId, cinLabel, "* le CIN doit contenir que des numero")) {
                    saisie = false;
                }
        if (!Validation.texNum(codePostaleId, codePostaleLabel, "* le code postale doit contenir que des numero")) {
                    saisie = false;
                }
        if (!Validation.texNum(telId, telLabel, "* le code postale doit contenir que des numero")) {
                    saisie = false;
                }

        if (!Validation.textValidation(pwdId, pwdLabel, "* tout les champs doivent etre remplis")) {
            saisie = false;
        }

        if (!Validation.textValidation(confpwdId, confpwdLabel, "* tout les champs doivent etre remplis")) {
            saisie = false;
        }

        if (!pwdId.getText().equals(confpwdId.getText())) {
            confpwdLabel.setText("* vous devez confirmer le mot de passe");
            saisie = false;
        }
        if (Validation.texMail(emailId,emailLabel, "* la forme de mail est invalide")) {

            return saisie;
        }
        return saisie;

    }

   @FXML
    private void valider(ActionEvent event) throws SQLException, IOException  {
        Userservice a= new Userservice();
        if ((this.controleSaisie2()) ) {

        
             String z;
            String r="a:1:{i:0;s:16:\"ROLE_SUPER_ADMIN\";}";
            z = roleId.getValue();
             if ("Client".equals(z)) {
            r = "a:1:{i:0;s:11:\"ROLE_CLIENT\";}";
        } else if ("Magasinier".equals(z)) {
            r = "a:1:{i:0;s:25:\"ROLE_PROPRIETAIRE_MAGASIN\";}";
        } else if ("Proprietaire De Refuge".equals(z)) {
            r = "a:1:{i:0;s:24:\"ROLE_PROPRIETAIRE_REFUGE\";}";
        }else if ("Dresseur".equals(z)) {
            r = "a:1:{i:0;s:13:\"ROLE_DRESSEUR\";}";
        }else if ("PetSitter".equals(z)) {
            r = "a:1:{i:0;s:14:\"ROLE_PETSITTER\";}";    
        }else if ("Vétérinaire".equals(z)) {
            r ="a:1:{i:0;s:15:\"ROLE_VETRINAIRE\";}";
        }
     
              try {
          User u=new User(cinId.getText(),usernameId.getText(),emailId.getText(),pwdId.getText(),r,nomId.getText(),prenomId.getText(),telId.getText(),villeId.getText(),adresseId.getText(),Integer.parseInt(codePostaleId.getText()));
           
          
           a.ajouterUser(u);
         System.out.println("user ajouté");
        } catch (SQLException ex) {
           Logger.getLogger(Zanimaux.class.getName()).log(Level.SEVERE, null, ex);
       }
 
          
        }

    }
    @FXML
    private void goToLogin(ActionEvent event) throws IOException {

            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("login.fxml"));
            Parent root = (Parent) fxmlLoader.load();
            Stage secondStage = new Stage();
            secondStage.setScene(new Scene(root));
            Stage stage = (Stage) btnInscrit.getScene().getWindow();
            // do what you have to do
            stage.hide();
            secondStage.show();
        
    }
    
}
