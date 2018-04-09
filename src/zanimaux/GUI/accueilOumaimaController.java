/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package zanimaux.GUI;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.scene.control.Button;
import javafx.scene.control.Control;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.Border;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;
import zanimaux.GUI.accueilOumaimaController;
import zanimaux.Service.MagasinService;
import zanimaux.Service.PanierService;
import zanimaux.Service.ProduitService;
import zanimaux.entities.Magasin;
import zanimaux.entities.Panier;
import zanimaux.entities.Produit;
import zanimaux.entities.User;
import zanimaux.util.Session;
/**
 * FXML Controller class
 *
 * @author macbookpro
 */
public class accueilOumaimaController implements Initializable {

    @FXML
    private Button button;
    @FXML
    private Button userName;
    @FXML
    private Pane pane;
    @FXML
    private Button btn11;
    @FXML
    private Button btn1;
    @FXML
    private Button buttonRefuge;
    @FXML
    private Label sommePanier;

    @FXML
    private Button annonceBtn;
    @FXML
    private Button evenement;


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
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        User u= Session.getLoggedInUser();
        userName.setText(u.getUsername());
        
        PanierService pan= null;
        try {
            pan= new PanierService();
        } catch (SQLException ex) {
            Logger.getLogger(magasinController.class.getName()).log(Level.SEVERE, null, ex);
        }
        Panier p = pan.recherchePanier(u.getCin());
        if (p==null)
        {
        sommePanier.setText("0 DT");}
        else{
        sommePanier.setText(String.valueOf(p.getSomme())+" DT");}
        // TODO
    }    

    @FXML
    private void onClickEvenementAction(ActionEvent event) throws SQLException {
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
     void AfficherRefugeAction(ActionEvent event) throws SQLException {

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
    private void affichePanierAction(ActionEvent event) 
    {
        
        try {
        Stage stage=(Stage) buttonRefuge.getScene().getWindow(); 
        stage.setTitle("Mon Panier");
        Parent root = FXMLLoader.load(getClass().getResource("AffichePanier.fxml"));
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
        } catch (IOException ex) {
           Logger.getLogger(accueilOumaimaController.class.getName()).log(Level.SEVERE, null, ex);
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
