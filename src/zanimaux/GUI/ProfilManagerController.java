/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package zanimaux.GUI;

import com.jfoenix.controls.JFXPasswordField;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.sql.SQLException;
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
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javax.imageio.ImageIO;
import zanimaux.Service.CabinetDao;
import zanimaux.Service.Userservice;
import zanimaux.entities.Cabinet;
import zanimaux.entities.User;
import zanimaux.util.Session;
import zanimaux.util.Validation;

/**
 * FXML Controller class
 *
 * @author Mariam
 */
public class ProfilManagerController implements Initializable {
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
    private Button btnvalid1;
    @FXML
    private TextField emailId;
    @FXML
    private PasswordField pwdId;
    @FXML
    private PasswordField confpwdId;
    @FXML
    private Label nomLabel;
    @FXML
    private Label prenomLabel;
    @FXML
    private Label emailLabel;
    @FXML
    private Label pwdLabel;
    @FXML
    private Label confpwdLabel;
    @FXML
    private Label telLabel;
    @FXML
    private Label adresseLabel;
    @FXML
    private Label villeLabel;
    @FXML
    private Label codePostaleLabel;
    @FXML
    private AnchorPane anchorPanecab;
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
    private Label lbinf;
    @FXML
    private Label btnRetour;

    /**
     * Initializes the controller class.
     */
   @Override
    public void initialize(URL url, ResourceBundle rb) {
        User user = Session.getLoggedInUser();
        String z= user.getRoles();
            String vet= "a:1:{i:0;s:16:\"ROLE_VETERINAIRE\";}";
         if(user != null){
            nomId.setText(user.getNom());
            prenomId.setText(user.getPrenom());
            telId.setText(Integer.toString(user.getTelephone()));
            emailId.setText(user.getEmail());
            adresseId.setText(user.getAdresse());
            villeId.setText(user.getVille());
            codePostaleId.setText(Integer.toString(user.getCodePostale()));
           }
         if(z.equals(vet)){
             lbinf.setVisible(true);
             anchorPanecab.setVisible(true);
             CabinetDao CDao = new CabinetDao();
             Cabinet cab= CDao.getByVet(user.getCin());
             telcab.setText(Integer.toString(cab.getTelephoneCabinet()));
             faxcab.setText(Integer.toString(cab.getFaxCabinet()));
             emailcab.setText(cab.getEmailCabinet());
             adressecab.setText(cab.getAdresseCabinet());
             villecab.setText(cab.getVilleCabinet());
             codepostcab.setText(Integer.toString(cab.getCodePostaleCabinet()));
             path.setText(cab.getPhotovet());
             
            
            
            try {
//                final Pane root = new Pane();
                 Image image;
                image = new Image(new File(cab.getPhotovet()).toURI().toURL().toExternalForm());
                imageuser.setImage(image);
//                final Circle clip = new Circle(300, 200, 200);
//                    imageuser.setClip(clip);
//                    
//                    root.getChildren().add(imageuser);
                    
            } catch (MalformedURLException ex) {
                Logger.getLogger(ProfilManagerController.class.getName()).log(Level.SEVERE, null, ex);
            }
                    
                         
                    
                    
                
            }
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
            imageuser.setImage(image);

        } catch (IOException ex) {
            System.err.println(ex);
        }

        return filePath;
    }
     
         public boolean controleSaisie3() throws IOException, SQLException {
        boolean saisie = true;
        
       

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
       
        if (!Validation.textValidation(telId, telLabel, "* tout les champs doivent etre remplis")) {
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
//        else
//          if (pwdId.getText().equals(confpwdId.getText()))
//          { Userservice u =new Userservice();
//              u.changerMDP(pwdId.getText(),Session.getLoggedInUser().getCin());
//              System.out.println("mdp changé");
//          } 
//       
        return saisie;

    }

    
    @FXML
    private void validerinfopesronnel(ActionEvent event) throws IOException, SQLException {
         Userservice a = new Userservice();
        User user = Session.getLoggedInUser();
//        if (pwdId.getText().equals(confpwdId.getText()))
//          { 
              
//              System.out.println("mdp changé");
//          } 
//         
        if ((this.controleSaisie3()) ) {
        
        user.setNom(nomId.getText());
        user.setPrenom(prenomId.getText());
        user.setEmail(emailId.getText());
        user.setAdresse(adresseId.getText());
        user.setVille(villeId.getText());
        user.setCodePostale(Integer.parseInt(codePostaleId.getText()));
        user.setTelephone(Integer.parseInt(telId.getText()));
        user.setPassword(pwdId.getText());
       // a.changerMDP(pwdId.getText(),user.getCin());
        a.modifierUser(user);
         
        }
        System.out.println("modifié !");
           
    }

    @FXML
    private void uploadImage(ActionEvent event) {
        path.setText(handle());

    }
    
    
    
    public boolean controleSaisie4() throws IOException, SQLException {
        boolean saisie = true;
        
       

       
        if (!Validation.texMail(emailcab, emaillabelcab, "* verifier votre mail")) {
            saisie = false;
        }

       

        if (!Validation.textValidation(emailcab, emaillabelcab, "* tout les champs doivent etre remplis")) {
            saisie = false;
        }
        if (!Validation.textValidation(adressecab, adresseLabel1, "* tout les champs doivent etre remplis")) {
            saisie = false;
        }
        if (!Validation.textValidation(villecab, villeLabel1, "* tout les champs doivent etre remplis")) {
            saisie = false;
        }
        if (!Validation.textValidation(codepostcab, codePostaleLabel1, "* tout les champs doivent etre remplis")) {
            saisie = false;
        }
       
        if (!Validation.textValidation(telcab, telLabel1, "* tout les champs doivent etre remplis")) {
            saisie = false;
        }
        if (!Validation.textValidation(faxcab, faxlabel, "* tout les champs doivent etre remplis")) {
            saisie = false;
        }
            
        
        if (!Validation.texNum(codepostcab, codePostaleLabel1, "* le code postale doit contenir que des numero")) {
                    saisie = false;
                }
        if (!Validation.texNum(telcab, telLabel1, "* le code postale doit contenir que des numero")) {
                    saisie = false;
                }
        if (!Validation.texNum(faxcab, faxlabel, "* Fax doit contenir que des numero")) {
                    saisie = false;
                }

       return saisie;

    }
    

    
    @FXML
    private void validerinfocabinet(ActionEvent event) throws IOException, SQLException {
        CabinetDao c = new CabinetDao();
        User user = Session.getLoggedInUser();
          Cabinet cab= c.getByVet(user.getCin());
        if ((this.controleSaisie4()) ) {
        cab.setTelephoneCabinet(Integer.parseInt(telcab.getText()));
        cab.setEmailCabinet(emailcab.getText());
        cab.setAdresseCabinet(adressecab.getText());
        cab.setVilleCabinet(villecab.getText());
        cab.setCodePostaleCabinet(Integer.parseInt(codepostcab.getText()));
        cab.setFaxCabinet(Integer.parseInt(faxcab.getText()));
        cab.setPhotovet(path.getText());
        c.modifierCabinet(cab);
         
        }
        System.out.println("modifié !");
    }

    @FXML
    private void logOut(MouseEvent event) {
           Session.setLoggedInUser(null);
        Parent root;
             try {
                 root = FXMLLoader.load(getClass().getResource("login.fxml"));
                 Stage myWindow = (Stage) nomId.getScene().getWindow();
                 Scene sc = new Scene(root);
                 myWindow.setScene(sc);
                 myWindow.setTitle("Login");
                 myWindow.show();
             } catch (IOException ex) {
                 Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
             }
    }
    @FXML
    private void Retour(MouseEvent event) {
        Parent root;
        String z;
       User a = Session.getLoggedInUser();
        z = a.getRoles();
        String vet= "a:1:{i:0;s:16:\"ROLE_VETERINAIRE\";}";
        String admin= "a:1:{i:0;s:16:\"ROLE_SUPER_ADMIN\";}";
        String propmag= "a:1:{i:0;s:25:\"ROLE_PROPRIETAIRE_MAGASIN\";}";
        String propref= "a:1:{i:0;s:24:\"ROLE_PROPRIETAIRE_REFUGE\";}";
        String clt= "a:1:{i:0;s:11:\"ROLE_CLIENT\";}";
        String dres="a:1:{i:0;s:13:\"ROLE_DRESSEUR\";}";
        String pet="a:1:{i:0;s:14:\"ROLE_PETSITTER\";}";
       if (z.equals(propref))
                         
                {try{
                    root = FXMLLoader.load(getClass().getResource("RefugeDashboard.fxml"));
                 Stage myWindow = (Stage) btnRetour.getScene().getWindow();
                 Scene sc = new Scene(root);
                 myWindow.setScene(sc);
                 myWindow.setTitle("Proprietaire refuge");
                 myWindow.show();}
                catch (IOException ex) {
                 Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
             }
                }
       else{
             try {
                 root = FXMLLoader.load(getClass().getResource("Quiz.fxml"));
                 Stage myWindow = (Stage) btnRetour.getScene().getWindow();
                 Scene sc = new Scene(root);
                 myWindow.setScene(sc);
                 myWindow.setTitle("Login");
                 myWindow.show();
             } catch (IOException ex) {
                 Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
             }
    }
    }
}
