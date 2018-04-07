/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package zanimaux.GUI;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import zanimaux.Service.PanierService;
import zanimaux.Service.ProduitService;
import zanimaux.entities.ContenuPanier;
import zanimaux.entities.Produit;
import zanimaux.entities.User;
import zanimaux.util.Session;

/**
 * FXML Controller class
 *
 * @author macbookpro
 */
public class AffichePanierController implements Initializable {

    @FXML
    private Button button;
    @FXML
    private Button buttonRefuge;
    @FXML
    private Button evenement;
    @FXML
    private Button userName;
    @FXML
    private Label sommePanier;
    @FXML
    private Pane paneProfil;
    @FXML
    private Button btn11;
    @FXML
    private Button btn1;
    @FXML
    private AnchorPane anchorEvent;
    @FXML
    private VBox vbPanier;
    @FXML
    private VBox vbProduit;
    @FXML
    private VBox vbQuantite;
    @FXML
    private VBox vbPrix;
    @FXML
    private VBox vbTotal;
    @FXML
    private VBox vbBoutton;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            User u = Session.getLoggedInUser();
            PanierService p = new PanierService();
            ProduitService ps = new ProduitService();
            List<ContenuPanier> cp = p.rechercheContenuPanier(u.getCin());
            for(int i =0;i<cp.size();i++)
            {
                Button plus = new Button();
                Button minus = new Button();
                plus.setText("+");
                minus.setText("-");
                HBox hbQuantite = new HBox();
                Produit prod = ps.rechercheProduitMagasin(cp.get(i).getIdProduit());
                Label libelle = new Label();
                Label prix = new Label();
                Label total = new Label();
                TextField qt = new TextField();
                Button sup = new Button();
                qt.setMaxSize(30, 10);
                qt.setAlignment(Pos.CENTER);
                hbQuantite.getChildren().add(minus);
                hbQuantite.getChildren().add(qt);
                hbQuantite.getChildren().add(plus);
                libelle.setText(prod.getLibelle());
                prix.setText(String.valueOf(prod.getPrix()));
                total.setText(String.valueOf(prod.getPrix()*cp.get(i).getQuantite()));
                qt.setText(String.valueOf(cp.get(i).getQuantite()));
                vbPrix.getChildren().add(prix);
                vbQuantite.getChildren().add(hbQuantite);
                vbTotal.getChildren().add(total);
                vbProduit.getChildren().add(libelle);
            }
            vbPrix.setSpacing(50);
            vbProduit.setSpacing(50);
            vbQuantite.setSpacing(30);
            vbTotal.setSpacing(50);
            vbPanier.setSpacing(50);
            anchorEvent.getChildren().setAll(vbPanier);
            anchorEvent.setMaxSize(300,300);
            vbPanier.setVisible(true);
        } catch (SQLException ex) {
            Logger.getLogger(AffichePanierController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }    

    @FXML
    private void afficherMagasin(ActionEvent event) {
             try {
        Stage stage=(Stage) buttonRefuge.getScene().getWindow(); 
        stage.setTitle("NOS Magasin");
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
        Stage stage=(Stage) buttonRefuge.getScene().getWindow(); 
        stage.setTitle("NOS Refuges");
        Parent root = FXMLLoader.load(getClass().getResource("afficheEvent.fxml"));
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
        } catch (IOException ex) {
           Logger.getLogger(accueilOumaimaController.class.getName()).log(Level.SEVERE, null, ex);
       }
    }

    @FXML
    private void affichePanierAction(ActionEvent event) {
    }

    @FXML
    private void showPaneProfil(MouseEvent event) {
    }

    @FXML
    private void connexionAction(ActionEvent event) {
    }

    @FXML
    private void hidePaneProfil(MouseEvent event) {
    }
    
}
