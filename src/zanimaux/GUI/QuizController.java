/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package zanimaux.GUI;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
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
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javax.xml.bind.Marshaller.Listener;
import zanimaux.Service.ChatService;
import zanimaux.Service.ChienService;
import zanimaux.Service.RaceService;
import zanimaux.entities.Race;
import zanimaux.entities.User;
import zanimaux.util.Session;

/**
 * FXML Controller class
 *
 * @author Azza
 */
public class QuizController implements Initializable {

    @FXML
    private Button button;
    @FXML
    private Button buttonRefuge;
    @FXML
    private Button evenement;
    @FXML
    private Button userName;
    @FXML
    private Pane pane;
    @FXML
    private Button btn11;
    @FXML
    private Button btn1;
    @FXML
    private Pane PanePrincipale;
    @FXML
    private Pane pane_questionnaire_chat;
    @FXML
    private CheckBox autre_animal_1;
    @FXML
    private CheckBox autre_animal_2;
    @FXML
    private CheckBox autre_animal_3;
    @FXML
    private Button btnresultatchat;
    @FXML
    private Pane pane_questionnaire_chien;
    @FXML
    private Pane pane_resultat;
    @FXML
    private ImageView imageanimal;
    @FXML
    private Label race_resultat;
    @FXML
    private Label description;
    @FXML
    private CheckBox dynamique1;
    @FXML
    private CheckBox affectueux1;
    @FXML
    private CheckBox poils1;
    @FXML
    private CheckBox intelligent1;
    @FXML
    private CheckBox accept1;
    @FXML
    private CheckBox dynamique2;
    @FXML
    private CheckBox affectueux2;
    @FXML
    private CheckBox poils2;
    @FXML
    private CheckBox intelligent2;
    @FXML
    private CheckBox accept2;
    @FXML
    private CheckBox dynamique3;
    @FXML
    private CheckBox affectueux3;
    @FXML
    private CheckBox poils3;
    @FXML
    private CheckBox intelligent3;
    @FXML
    private CheckBox accept3;
    @FXML
    private CheckBox autre_chien1;
    @FXML
    private CheckBox autre_chien2;
    @FXML
    private CheckBox autre_chien3;
    @FXML
    private CheckBox tolerer_chat1;
    @FXML
    private CheckBox chien_affectueux1;
    @FXML
    private CheckBox calme1;
    @FXML
    private CheckBox chien_intelligent1;
    @FXML
    private CheckBox poils_chien1;
    @FXML
    private CheckBox tolerer_chat2;
    @FXML
    private CheckBox chien_affectueux2;
    @FXML
    private CheckBox calme2;
    @FXML
    private CheckBox chien_intelligent2;
    @FXML
    private CheckBox poils_chien2;
    @FXML
    private CheckBox tolerer_chat3;
    @FXML
    private CheckBox chien_affectueux3;
    @FXML
    private CheckBox calme3;
    @FXML
    private CheckBox chien_intelligent3;
    @FXML
    private CheckBox poils_chien3;
    @FXML
    private Button btnresultatchien;
    String autre_animal;
    String chatdynamique;
    String chataffectueux;
    String chatchute;
    String chatintelligent;
    String chataccept;
    String autre_chien;
    String tolerer;
    String chienaffectueux;
    String chienchute;
    String chienintelligent;
    String calme;
     
    @FXML
    private Label lb;
    @FXML
    private Label lb1;
    @FXML
    private Button annonceBtn;
    
    

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
       User u= Session.getLoggedInUser();
        userName.setText(u.getUsername());
    }    

    @FXML
    private void MagasinButtonAction(ActionEvent event) {
        
        try {
        Stage stage=(Stage) button.getScene().getWindow(); 
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
    private void AfficherRefugeAction(ActionEvent event) {
        try {
        Stage stage=(Stage) buttonRefuge.getScene().getWindow(); 
        stage.setTitle("NOS Refuges");
        Parent root = FXMLLoader.load(getClass().getResource("RefugeClient.fxml"));
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
        } catch (IOException ex) {
           Logger.getLogger(accueilOumaimaController.class.getName()).log(Level.SEVERE, null, ex);
       }
    }

    @FXML
    private void onClickEvenementAction(ActionEvent event) {
         try {
        Stage stage=(Stage) button.getScene().getWindow(); 
        stage.setTitle("Ajouter Evenement");
        Parent root = FXMLLoader.load(getClass().getResource("addEvent.fxml"));
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
    private void hidePane(MouseEvent event) {
         pane.setVisible(false);

    }

   

    @FXML
    private void questionnaire_chat(MouseEvent event) {
        PanePrincipale.setVisible(false);
        pane_questionnaire_chat.setVisible(true);
       
        
    }

    @FXML
    private void questionnaire_chien(MouseEvent event) {
        PanePrincipale.setVisible(false);
        pane_questionnaire_chien.setVisible(true);
    }

    @FXML
    private void autre_animal_1Action(ActionEvent event) {
            autre_animal_2.setSelected(false);
            autre_animal_3.setSelected(false);
             
    }

    @FXML
    private void autre_animal_2Action(ActionEvent event) {
         autre_animal_1.setSelected(false);
            autre_animal_3.setSelected(false);
            
    }

    @FXML
    private void autre_animal_3Action(ActionEvent event) {
            autre_animal_2.setSelected(false);
            autre_animal_1.setSelected(false);
            
    }

    @FXML
    private void dynamique1Action(ActionEvent event) {
        dynamique2.setSelected(false);
            dynamique3.setSelected(false);
    }

    @FXML
    private void affectueux1Action(ActionEvent event) {
         affectueux2.setSelected(false);
            affectueux3.setSelected(false);
    }

    @FXML
    private void poils1Action(ActionEvent event) {
       poils2.setSelected(false);
            poils3.setSelected(false);
    }

    @FXML
    private void intelligent1Action(ActionEvent event) {
        intelligent2.setSelected(false);
            intelligent3.setSelected(false);
    }

    @FXML
    private void accept1Action(ActionEvent event) {
        accept2.setSelected(false);
            accept3.setSelected(false);
    }

    @FXML
    private void dynamique2Action(ActionEvent event) {
        dynamique1.setSelected(false);
            dynamique3.setSelected(false);
    }

    @FXML
    private void affectueux2Action(ActionEvent event) {
         affectueux1.setSelected(false);
           affectueux3.setSelected(false);
    }

    @FXML
    private void poils2Action(ActionEvent event) {
         poils1.setSelected(false);
           poils3.setSelected(false);
    }

    @FXML
    private void intelligent2Action(ActionEvent event) {
        intelligent1.setSelected(false);
           intelligent3.setSelected(false);
    }

    @FXML
    private void accept2Action(ActionEvent event) {
         accept1.setSelected(false);
           accept3.setSelected(false);
    }

    @FXML
    private void dynamique3Action(ActionEvent event) {
        dynamique2.setSelected(false);
            dynamique1.setSelected(false);
    }

    @FXML
    private void affectueux3Action(ActionEvent event) {
        affectueux2.setSelected(false);
            affectueux1.setSelected(false);
    }

    @FXML
    private void poils3Action(ActionEvent event) {
        poils2.setSelected(false);
       poils1.setSelected(false);
    }

    @FXML
    private void intelligent3Action(ActionEvent event) {
         intelligent2.setSelected(false);
         intelligent1.setSelected(false);
    }

    @FXML
    private void accept3Action(ActionEvent event) {
        accept2.setSelected(false);
        accept1.setSelected(false);
    }

    @FXML
    private void btnresultatchatAction(ActionEvent event) throws SQLException {
         Boolean Saisie=true;
        if(autre_animal_1.isSelected()){
            autre_animal="1";
        }else if(autre_animal_2.isSelected()){
            autre_animal="2";
        }else if(autre_animal_3.isSelected()){
            autre_animal="3";
        }else
        {lb.setText("vous devez choisir tous les lignes");  Saisie=false;}
    if(dynamique1.isSelected()){
            chatdynamique="1";
        }else if(dynamique2.isSelected()){
            chatdynamique="2";
        }else if(dynamique3.isSelected()){
            chatdynamique="3";
        }else
        { lb.setText("vous devez choisir tous les lignes");Saisie=false;}
    if(affectueux1.isSelected()){
            chataffectueux="1";
        }else if(affectueux2.isSelected()){
            chataffectueux="2";
        }else if(affectueux3.isSelected()){
           chataffectueux="3";
        }else
        { lb.setText("vous devez choisir tous les lignes"); Saisie=false;}
    if(poils1.isSelected()){
            chatchute="1";
        }else if(poils2.isSelected()){
            chatchute="2";
        }else if(poils3.isSelected()){
           chatchute="3";
        }else
        { lb.setText("vous devez choisir tous les lignes"); Saisie=false;}
    if(intelligent1.isSelected()){
            chatintelligent="1";
        }else if(intelligent2.isSelected()){
            chatintelligent="2";
        }else if(intelligent3.isSelected()){
           chatintelligent="3";
        }else
        { lb.setText("vous devez choisir tous les lignes");Saisie=false;}
    if(accept1.isSelected()){
            chataccept="1";
        }else if(accept2.isSelected()){
            chataccept="2";
        }else if(accept3.isSelected()){
           chataccept="3";
        }else
        { lb.setText("vous devez choisir tous les lignes");Saisie=false;}
   if(Saisie){
    ChatService chats=new ChatService();
    int ra= chats.AfficherIdChatRace(autre_animal, chatdynamique, chataffectueux, chatchute, chatintelligent, chataccept);
        RaceService races= new RaceService();
        Race racechat=new Race();
        racechat=races.RechercherRaceById(ra);
        Image image= new Image("zanimaux/ImageUtile/"+racechat.getPhoto());
        imageanimal.setImage(image);
        race_resultat.setText(racechat.getRace());
        description.setText(racechat.getInformations());
        pane_questionnaire_chat.setVisible(false);
        pane_resultat.setVisible(true);
    }}

    @FXML
    private void autre_chien1Action(ActionEvent event) {
        autre_chien2.setSelected(false);
            autre_chien3.setSelected(false);
    }

    @FXML
    private void autre_chien2Action(ActionEvent event) {
        autre_chien1.setSelected(false);
            autre_chien3.setSelected(false);
    }

    @FXML
    private void autre_chien3Action(ActionEvent event) {
        autre_chien2.setSelected(false);
            autre_chien1.setSelected(false);
    }

    @FXML
    private void tolerer_chat1Action(ActionEvent event) {
        tolerer_chat2.setSelected(false);
           tolerer_chat3.setSelected(false);
    }

    @FXML
    private void chien_affectueux1Action(ActionEvent event) {
        chien_affectueux2.setSelected(false);
           chien_affectueux3.setSelected(false);
    }

    @FXML
    private void calme1Action(ActionEvent event) {
        calme2.setSelected(false);
           calme3.setSelected(false);
    }

    @FXML
    private void chien_intelligent1Action(ActionEvent event) {
         chien_intelligent2.setSelected(false);
           chien_intelligent3.setSelected(false);
    }

    @FXML
    private void poils_chien1Action(ActionEvent event) {
        poils_chien2.setSelected(false);
           poils_chien3.setSelected(false);
    }

    @FXML
    private void tolerer_chat2Action(ActionEvent event) {
        tolerer_chat1.setSelected(false);
           tolerer_chat3.setSelected(false);
    }

    @FXML
    private void chien_affectueux2Action(ActionEvent event) {
        chien_affectueux1.setSelected(false);
           chien_affectueux3.setSelected(false);
    }

    @FXML
    private void calme2Action(ActionEvent event) {
         calme1.setSelected(false);
           calme3.setSelected(false);
    }

    @FXML
    private void chien_intelligent2Action(ActionEvent event) {
        chien_intelligent1.setSelected(false);
           chien_intelligent3.setSelected(false);
    }

    @FXML
    private void poils_chien2Action(ActionEvent event) {
        poils_chien1.setSelected(false);
           poils_chien3.setSelected(false);
    }

    @FXML
    private void tolerer_chat3Action(ActionEvent event) {
        tolerer_chat2.setSelected(false);
           tolerer_chat1.setSelected(false);
    }

    @FXML
    private void chien_affectueux3Action(ActionEvent event) {
        chien_affectueux2.setSelected(false);
           chien_affectueux1.setSelected(false);
    }

    @FXML
    private void calme3Action(ActionEvent event) {
         calme2.setSelected(false);
           calme1.setSelected(false);
    }

    @FXML
    private void chien_intelligent3Action(ActionEvent event) {
        chien_intelligent2.setSelected(false);
           chien_intelligent1.setSelected(false);
    }

    @FXML
    private void poils_chien3Action(ActionEvent event) {
        poils_chien2.setSelected(false);
           poils_chien1.setSelected(false);
    }

    @FXML
    private void btnresultatchienAction(ActionEvent event) throws SQLException {
       Boolean Saisie=true;
          if(autre_chien1.isSelected()){
            autre_chien="1";
        }else if(autre_chien2.isSelected()){
            autre_chien="2";
        }else if(autre_chien3.isSelected()){
            autre_chien="3";
        }else
        { lb1.setText("vous devez choisir toutes les suggestions");
           Saisie=false; 
        }
    if(tolerer_chat1.isSelected()){
            tolerer="1";
        }else if(tolerer_chat2.isSelected()){
            tolerer="2";
        }else if(tolerer_chat3.isSelected()){
            tolerer="3";
        }else{
            lb1.setText("vous devez choisi toutes les suggestions");
        Saisie=false; }
    if(chien_affectueux1.isSelected()){
            chienaffectueux="1";
        }else if(chien_affectueux2.isSelected()){
            chienaffectueux="2";
        }else if(chien_affectueux3.isSelected()){
           chienaffectueux="3";
        }else{
            lb1.setText("vous devez choisir toutes les suggestions");Saisie=false; }
    if(poils_chien1.isSelected()){
            chienchute="1";
        }else if(poils_chien2.isSelected()){
            chienchute="2";
        }else if(poils_chien3.isSelected()){
           chienchute="3";
        }else
        {lb1.setText("vous devez choisir toutes les suggestions");Saisie=false; }
    if(chien_intelligent1.isSelected()){
            chienintelligent="1";
        }else if(chien_intelligent2.isSelected()){
            chienintelligent="2";
        }else if(chien_intelligent3.isSelected()){
           chienintelligent="3";
        }else
        { lb1.setText("vous devez choisir toutes les suggestions");Saisie=false; }
    if(calme1.isSelected()){
            calme="1";
        }else if(calme2.isSelected()){
            calme="2";
        }else if(calme3.isSelected()){
           calme="3";
        }else
        { lb1.setText("vous devez choisir toutes les suggestions");Saisie=false; }
    ChienService chiens=new ChienService();
     if(Saisie){
    int ra= chiens.AfficherIdChienRace(autre_chien,calme, chienaffectueux, chienchute, chienintelligent, tolerer);
        RaceService races= new RaceService();
        Race racechien=new Race();
        racechien=races.RechercherRaceById(ra);
        Image image= new Image("zanimaux/ImageUtile/"+racechien.getPhoto());
        imageanimal.setImage(image);
        imageanimal.setFitHeight(150.0);
        imageanimal.setFitWidth(200.0);
        race_resultat.setText(racechien.getRace());
        description.setText(racechien.getInformations());
        pane_questionnaire_chien.setVisible(false);
        pane_resultat.setVisible(true);
    }}

    @FXML
    private void goToAnn(ActionEvent event) {
         try {
        Stage stage=(Stage) annonceBtn.getScene().getWindow(); 
        stage.setTitle("Deposez votre annonce");
        Parent root = FXMLLoader.load(getClass().getResource("addAnnonce.fxml"));
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
        } catch (IOException ex) {
           Logger.getLogger(accueilOumaimaController.class.getName()).log(Level.SEVERE, null, ex);
       }
    }
    
}
