/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package zanimaux.GUI;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import zanimaux.Service.MagasinService;
import zanimaux.Service.PanierService;
import zanimaux.Service.ProduitService;
import zanimaux.entities.ContenuPanier;
import zanimaux.entities.Magasin;
import zanimaux.entities.Produit;
import zanimaux.entities.User;
import zanimaux.util.Session;

/**
 * FXML Controller class
 *
 * @author macbookpro
 */
public class AfficherCommandeController implements Initializable {

    @FXML
    private AnchorPane bigAnchor;
    @FXML
    private AnchorPane sidePane;
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
    private TableView TableViewCommande;
    @FXML
    private TableColumn ID;
    @FXML
    private TableColumn Libelle;
    @FXML
    private TableColumn Quantite;
    @FXML
    private TableColumn PrixU;
    @FXML
    private TableColumn dateCommande;
    @FXML
    private TableView TableViewProduit;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
              
        try {
    
            PanierService ms = new PanierService();
            ProduitService prodSer= new ProduitService();
            User a = Session.getLoggedInUser();
            List<ContenuPanier> l= new ArrayList<>();
            List<Produit> lp = new ArrayList<>();
            l = ms.rechercheCommande(a.getCin());
            Produit p = new Produit();
           for(int i=0;i<l.size();i++)
           {
            p=prodSer.rechercheProduitMagasin(l.get(i).getIdProduit());
            lp.add(p);
           }
            ObservableList<ContenuPanier> data = FXCollections.observableArrayList(l); 
            ObservableList<Produit> data2 = FXCollections.observableArrayList(lp); 
            ID.setCellValueFactory(
                    new PropertyValueFactory<ContenuPanier,Integer>("idContenuPanier")
            );
          
            Quantite.setCellValueFactory(
                    new PropertyValueFactory<ContenuPanier,String>("quantite")
            );
          
            dateCommande.setCellValueFactory(
                    new PropertyValueFactory<ContenuPanier,Date>("dateCommande")
            );    
            Libelle.setCellValueFactory(
                    new PropertyValueFactory<Produit,String>("libelle")
            );
            PrixU.setCellValueFactory(
                    new PropertyValueFactory<Produit,Double>("prix")
            );
            TableViewProduit.setItems(data2);
            TableViewCommande.setItems(data);
            } catch (SQLException ex) {
            Logger.getLogger(DashboardMagasinController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }    

    @FXML
    private void afficherMagasin(ActionEvent event) {
    }

    @FXML
    private void AfficherRefugeAction(ActionEvent event) {
    }

    @FXML
    private void onClickEvenementAction(ActionEvent event) {
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
    private void annulerCommandeAction(ActionEvent event) throws SQLException {
            PanierService ms = new PanierService();
            User a = Session.getLoggedInUser();
            List<ContenuPanier> l= new ArrayList<>();
            l= ms.rechercheCommande(a.getCin());
            boolean test=false;
            int i=0;
            while((test!=true)&&(i<l.size())){
                
                long diff = java.sql.Date.valueOf(LocalDate.now()).getTime() - l.get(i).getDateCommande().getTime();
                TimeUnit.DAYS.convert(diff, TimeUnit.MILLISECONDS);
                float days = (diff / (1000*60*60*24));
                if(days>3){
                    test=true;
                }
                i++;
            }
            if(test==true){
                javafx.scene.control.Alert alert = new  javafx.scene.control.Alert(javafx.scene.control.Alert.AlertType.WARNING , "Vous ne puvez plus annulez votre commande");
                    alert.setHeaderText("Annulation Commande");
                    alert.showAndWait();
            }
            else{
                ms.annulerCommande(a.getCin());
                 try {
        Stage stage=(Stage) buttonRefuge.getScene().getWindow(); 
        stage.setTitle("NOS MAGASINS");
        Parent root = FXMLLoader.load(getClass().getResource("magasin.fxml"));
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
        } catch (IOException ex) {
           Logger.getLogger(accueilOumaimaController.class.getName()).log(Level.SEVERE, null, ex);
       }
            }
    }

    @FXML
    private void hidePaneProfil(MouseEvent event) {
    }
    
}
