/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package zanimaux.GUI;

import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Control;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import org.controlsfx.control.Rating;
import zanimaux.Service.AvisService;
import zanimaux.Service.ParcService;
import zanimaux.entities.Avis;
import zanimaux.entities.Parc;
import zanimaux.entities.User;
import javafx.scene.chart.PieChart;

import javafx.scene.control.Label;
import javafx.scene.control.Tooltip;
import zanimaux.util.Session;

/**
 * FXML Controller class
 *
 * @author BelhassenLimam
 */
public class AccueilDresseurController implements Initializable {

    @FXML
    private Button button;
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
    private Button parc;
    @FXML
    private AnchorPane anchorEvent;
    @FXML
    private Button logOut;
    
    @FXML
    private PieChart chart;
    @FXML
    private Text nombret;
    @FXML
    private Text nombre;
    @FXML
    private Button editp;
    @FXML
    private Button listp;
    @FXML
    private Button vet;
    @FXML
    private Button refuge;
    @FXML
    private Button annonce;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
            

                try {

                    
                    
                    User u= Session.getLoggedInUser();
                    userName.setText(u.getUsername());
                    ParcService m=null;
                    try {
                        m = new ParcService();
                    } catch (SQLException ex) {
                        Logger.getLogger(ParcController.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    
                    
                    ObservableList<PieChart.Data> pieChartData =
                            FXCollections.observableArrayList(
                                    new PieChart.Data("Total", m.CountParc()),
                                    new PieChart.Data("Chien", m.CountParcByCateg("Chien")),
                                    new PieChart.Data("Chevaux", m.CountParcByCateg("Chevaux")),
                                    new PieChart.Data("Autre", m.CountParcByCateg("Autre")));
                    
                    
                    chart.getData().addAll(pieChartData);
                    
                    
                    final Label caption = new Label("");
                    caption.setTextFill(Color.DARKORANGE);
                    caption.setStyle("-fx-font: 24 arial;");
                    Tooltip container = new Tooltip();
                    container.setGraphic(caption);
                    
                    chart.getData().forEach((data) ->
                    {
                        data.getNode().
                                
                                addEventHandler(MouseEvent.MOUSE_ENTERED, e ->
                                {  Stage stage=(Stage) button.getScene().getWindow();
                                if (container.isShowing())
                                {
                                    container.hide();
                                }
                                
                                caption.setText(String.valueOf((int)data.getPieValue()));
                                container.show(stage, e.getScreenX(), e.getScreenY());
                                });
                    });
                    chart.getData().forEach((data) ->
                    {
                        data.getNode().
                                
                                addEventHandler(MouseEvent.MOUSE_EXITED, e ->
                                {  Stage stage=(Stage) button.getScene().getWindow();
                                if (container.isHideOnEscape())
                                {
                                    container.hide();
                                }
                                
                           
                                });
                    });
                } catch (SQLException ex) {
                    Logger.getLogger(AccueilDresseurController.class.getName()).log(Level.SEVERE, null, ex);
                }
    }
    
    
   //redirection page magasin
    @FXML
    void handleButtonAction(ActionEvent event) throws SQLException {

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

    
//redirection page afficheEvent
     @FXML
    private void onClickEvenementAction(ActionEvent event) throws SQLException {
        try {
        Stage stage=(Stage) button.getScene().getWindow(); 
        stage.setTitle("Afficher Evenement");
        Parent root = FXMLLoader.load(getClass().getResource("afficheEvent.fxml"));
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
        } catch (IOException ex) {
           Logger.getLogger(accueilOumaimaController.class.getName()).log(Level.SEVERE, null, ex);
       }
    }
    
 //redirection page VetAffiche   
 @FXML
    private void vetAffiche(ActionEvent event) {
         try {
        Stage stage=(Stage) button.getScene().getWindow(); 
        stage.setTitle("Vet");
        Parent root = FXMLLoader.load(getClass().getResource("VetFront.fxml"));
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
        } catch (IOException ex) {
           Logger.getLogger(accueilOumaimaController.class.getName()).log(Level.SEVERE, null, ex);
       }
    }
    
    
    //redirection page annonce
    @FXML
    private void Annonce(ActionEvent event) {
         try {
        Stage stage=(Stage) button.getScene().getWindow(); 
        stage.setTitle("Annonces");
        Parent root = FXMLLoader.load(getClass().getResource("afficheAnnonce.fxml"));
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
        } catch (IOException ex) {
           Logger.getLogger(accueilOumaimaController.class.getName()).log(Level.SEVERE, null, ex);
       }
    }
    
    //redirection page refugeClient
    @FXML
    private void AfficherRefugeAction(ActionEvent event) {
        try {
        Stage stage=(Stage) button.getScene().getWindow(); 
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
    void RedirectPromAction(ActionEvent event) throws SQLException {

        try {
        Stage stage=(Stage) button.getScene().getWindow(); 
        stage.setTitle("Liste des promenades");
        Parent root = FXMLLoader.load(getClass().getResource("ListePromenade.fxml"));
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
        } catch (IOException ex) {
           Logger.getLogger(accueilOumaimaController.class.getName()).log(Level.SEVERE, null, ex);
       }

    }

 
    
     @FXML
    private void parcAction(ActionEvent event) throws SQLException {
        try {
        Stage stage=(Stage) button.getScene().getWindow(); 
        stage.setTitle("Ajouter Parc");
        Parent root = FXMLLoader.load(getClass().getResource("AjoutParc.fxml"));
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
        } catch (IOException ex) {
           Logger.getLogger(accueilOumaimaController.class.getName()).log(Level.SEVERE, null, ex);
       }
    }


    @FXML
    private void showPane(MouseEvent event) {
    }

    @FXML
    private void connexionAction(ActionEvent event) {
    }
    private void reinit(ActionEvent event) {
        
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("AccueilDresseur.fxml"));
            Parent root = (Parent) fxmlLoader.load();
            Stage secondStage = new Stage();
            secondStage.setScene(new Scene(root));
            Stage stage = (Stage) btn1.getScene().getWindow();
            // do what you have to do
            stage.hide();
            secondStage.show();
        } catch (IOException ex) {
            Logger.getLogger(AjoutCabinetController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
  

    @FXML
    private void Deconnexion(ActionEvent event) {
        Session.setLoggedInUser(null);
        Parent root;
             try {
                 root = FXMLLoader.load(getClass().getResource("login.fxml"));
                 Stage myWindow = (Stage) logOut.getScene().getWindow();
                 Scene sc = new Scene(root);
                 myWindow.setScene(sc);
                 myWindow.setTitle("Login");
                 myWindow.show();
             } catch (IOException ex) {
                 Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
             }
    }

    @FXML
    private void mouseClick(MouseEvent event) {
        
  
    
     
   
    }      

    @FXML
    private void listerParc(ActionEvent event) {
        try {
        Stage stage=(Stage) button.getScene().getWindow(); 
        stage.setTitle("Liste des parcs");
        Parent root = FXMLLoader.load(getClass().getResource("ListeParc.fxml"));
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
        } catch (IOException ex) {
           Logger.getLogger(accueilOumaimaController.class.getName()).log(Level.SEVERE, null, ex);
       }
    }
}
