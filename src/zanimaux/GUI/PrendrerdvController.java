/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package zanimaux.GUI;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.time.Instant;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import zanimaux.Service.RendezvsService;
import zanimaux.entities.Rendezvs;
import zanimaux.entities.User;
import zanimaux.util.Session;
import zanimaux.util.alertHelper;

/**
 * FXML Controller class
 *
 * @author Mariam
 */
public class PrendrerdvController implements Initializable {
    @FXML
    private Button accueilBTN;
    @FXML
    private Button btnMagasin;
    @FXML
    private Button buttonRF;
    @FXML
    private Button btnevenement;
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
    private ImageView imagevet;
    @FXML
    private Button btnvalid1;
    @FXML
    private DatePicker picker_debut;
    @FXML
    private Label label1;
    @FXML
    private Label label2;
    @FXML
    private Label label3;
    @FXML
    private Label label_error;
    @FXML
    private Label notifLabel;

        @Override
    public void initialize(URL url, ResourceBundle rb) {
         userName.setText((Session.getLoggedInUser()).getUsername());
         label1.setText(VetFrontController.cb.getEmailCabinet());
         System.out.println("cbon");
         label2.setText(VetFrontController.cb.getAdresseCabinet()+" "+VetFrontController.cb.getVilleCabinet()+" "+VetFrontController.cb.getCodePostaleCabinet());
        label3.setText(" "+VetFrontController.cb.getTelephoneCabinet()+" ");
        Image image= new Image("zanimaux/ImageUtile/"+VetFrontController.cb.getPhotovet(),150,120,false,false) ;
               
        imagevet.setImage(image);
        
        
      notifLabel.setVisible(false);
            ArrayList<User> userNotifList =new RendezvsService().getUserNotifNotVu();
            for(User u : userNotifList){
                
                if(u.getCin().equals(Session.getLoggedInUser().getCin())){
                    System.out.println(u.getCin());
                    notifLabel.setText("Un rendez vs est supprimé");
//                    
                    
                 }
             try {
                 new RendezvsService().setUserNotifVu(Session.getLoggedInUser().getCin());
             } catch (SQLException ex) {
                 Logger.getLogger(PrendrerdvController.class.getName()).log(Level.SEVERE, null, ex);
             }
            }
            notifLabel.setVisible(true);    }    
    

    private void goToAnn(ActionEvent event) {
         try {
        Stage stage=(Stage) pane.getScene().getWindow(); 
        stage.setTitle("Deposez votre annonce");
        Parent root = FXMLLoader.load(getClass().getResource("afficheAnnonce.fxml"));
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
        } catch (IOException ex) {
           Logger.getLogger(accueilOumaimaController.class.getName()).log(Level.SEVERE, null, ex);
       }
    } 
    @FXML
    private void goTovet(ActionEvent event) {
        
          try {
        Stage stage=(Stage) pane.getScene().getWindow(); 
        stage.setTitle("Vétérinaire");
        Parent root = FXMLLoader.load(getClass().getResource("VetFront.fxml"));
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
        } catch (IOException ex) {
           Logger.getLogger(accueilOumaimaController.class.getName()).log(Level.SEVERE, null, ex);
       }
    }
    

    @FXML
    private void AfficherLesMagasins(ActionEvent event) {
         try {
        Stage stage=(Stage) btnMagasin.getScene().getWindow(); 
        stage.setTitle("NOS MAGASINS");
        Parent root = FXMLLoader.load(getClass().getResource("magasin.fxml"));
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
        } catch (IOException ex) {
           Logger.getLogger(accueilOumaimaController.class.getName()).log(Level.SEVERE, null, ex);
       }
    }

    @FXML
    private void affichePanierAction(ActionEvent event) {
        
             try {
        Stage stage=(Stage) accueilBTN.getScene().getWindow(); 
        stage.setTitle("Mon Panier");
        Parent root = FXMLLoader.load(getClass().getResource("AffichePanier.fxml"));
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
        } catch (IOException ex) {
           Logger.getLogger(accueilOumaimaController.class.getName()).log(Level.SEVERE, null, ex);
       }
    }
    

    @FXML
    private void afficherLesRefuges(ActionEvent event) {
         try {
        Stage stage=(Stage) accueilBTN.getScene().getWindow(); 
        stage.setTitle("Les refuges");
        Parent root = FXMLLoader.load(getClass().getResource("RefugeClient.fxml"));
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
        } catch (IOException ex) {
           Logger.getLogger(accueilOumaimaController.class.getName()).log(Level.SEVERE, null, ex);
       }
    }


    @FXML
    private void validerrdv(ActionEvent event) {
        if(picker_debut.getValue() == null){
            label_error.setText("entrez une date");
        }else{
            label_error.setText("");
            
            java.util.Date dateDebut = java.util.Date.from(picker_debut.getValue().atStartOfDay(ZoneId.systemDefault()).toInstant());
            java.sql.Date sqlDateDebut = new java.sql.Date(dateDebut.getTime());
            
            
            
            int compareDates = sqlDateDebut.compareTo(Date.from(Instant.now()));
            System.out.println(compareDates);
            if(compareDates < 1 || compareDates < 0){
                alertHelper.afficher("Erreur", "Donnez une date future");
            }else{
                
                Rendezvs rdv = new Rendezvs(Session.getLoggedInUser().getCin(), VetFrontController.cb.getImmatriculeCabinet(), dateDebut);
                if(RendezvsService.getInstance().ajouterrdv(rdv)){
                     alertHelper.afficher("Success", "ajout avec succés ! attendre un sms de votre vet");
                }else{
                      alertHelper.afficher("Erreur", "Somwhere");
                }
            }
        }
    }
    

    @FXML
    private void addEvent(ActionEvent event) {
         try {
        Stage stage=(Stage) btnevenement.getScene().getWindow(); 
        stage.setTitle("Ajouter Evenement");
        Parent root = FXMLLoader.load(getClass().getResource("afficheEvent.fxml"));
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
        } catch (IOException ex) {
           Logger.getLogger(accueilOumaimaController.class.getName()).log(Level.SEVERE, null, ex);
       }
    }
    @FXML
    private void showPane(MouseEvent event) {
         pane.setVisible(true);
    }
    @FXML
    private void hidePane(MouseEvent event) {
         pane.setVisible(false);

    }

    @FXML
    private void connexionAction(ActionEvent event) {
          Session.setLoggedInUser(null);
        Parent root;
             try {
                 root = FXMLLoader.load(getClass().getResource("login.fxml"));
                 Stage myWindow = (Stage) btn11.getScene().getWindow();
                 Scene sc = new Scene(root);
                 myWindow.setScene(sc);
                 myWindow.setTitle("Login");
                 myWindow.show();
             } catch (IOException ex) {
                 Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
             }
    }

    @FXML
    private void profil(ActionEvent event) {
         try {
        Stage stage=(Stage) btn1.getScene().getWindow(); 
        stage.setTitle("Profil");
        Parent root = FXMLLoader.load(getClass().getResource("ProfilManager.fxml"));
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
        } catch (IOException ex) {
           Logger.getLogger(accueilOumaimaController.class.getName()).log(Level.SEVERE, null, ex);
       }
    }

    @FXML
    private void Accueil(ActionEvent event) {
        try {
      User user=Session.getLoggedInUser();
        String role=user.getRoles();
            String dres="a:1:{i:0;s:13:\"ROLE_DRESSEUR\";}";
        Stage stage=(Stage) label1.getScene().getWindow(); 
        if(role.equals(dres)){
        stage.setTitle("Accueil");
        Parent root = FXMLLoader.load(getClass().getResource("AccueilDresseur.fxml"));
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();}else{
            stage.setTitle("Accueil");
        Parent root = FXMLLoader.load(getClass().getResource("Quiz.fxml"));
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
        }
        } catch (IOException ex) {
           Logger.getLogger(accueilOumaimaController.class.getName()).log(Level.SEVERE, null, ex);
       }
    }

    @FXML
    private void setVu(MouseEvent event) throws SQLException {
         //new RendezvsService().setUserNotifVu(Session.getLoggedInUser().getCin());
    }
    
}
