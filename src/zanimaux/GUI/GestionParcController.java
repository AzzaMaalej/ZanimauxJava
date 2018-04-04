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
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import zanimaux.Service.AnimalService;
import zanimaux.Service.ParcService;
import zanimaux.entities.Animal;
import zanimaux.entities.Parc;
import zanimaux.entities.User;
import zanimaux.util.Session;

/**
 * FXML Controller class
 *
 * @author BelhassenLimam
 */
public class GestionParcController implements Initializable {

    @FXML
    private Button ajou;
    @FXML
    private Button aff;
    @FXML
    private Button evenement;
    @FXML
    private Button userName;
    @FXML
    private AnchorPane anchorEvent;
    @FXML
    private TableView table_list_parc;
    @FXML
    private TableColumn<Parc, String> column_nom;
    
    @FXML
    private TableColumn column_adresse;
  
    @FXML
    private Button logOut;
    @FXML
    private TableColumn column_id;
    @FXML
    private TableColumn column_catg;
    @FXML
    private TableColumn column_ville;
    @FXML
    private TableColumn column_codep;
    @FXML
    private TableColumn column_photo;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
    
            
        try {
            List<Parc> listParcs = new ArrayList<>();
            ParcService as;
        
            as = ParcService.getInstance();
        
            User user=Session.getLoggedInUser();
            String cin=user.getCin();
            listParcs = as.AfficherParcsByCin(cin);//TODO : SET USER FROM SESSION
            
            ObservableList<Parc> data = FXCollections.observableArrayList(listParcs);
            
            column_id.setCellValueFactory(
                    new PropertyValueFactory<Parc,String>("id")
            );
            column_nom.setCellValueFactory(
                    new PropertyValueFactory<Parc,String>("nomParc")
            );
            column_catg.setCellValueFactory(
                    new PropertyValueFactory<Parc,String>("CategorieDressage")
            );
            column_adresse.setCellValueFactory(
                    new PropertyValueFactory<Parc,String>("adresseParc")
            );
            column_ville.setCellValueFactory(
                    new PropertyValueFactory<Parc,String>("villeParc")
            );
            column_codep.setCellValueFactory(
                    new PropertyValueFactory<Parc,Integer>("codePostaleParc")
            );
            column_photo.setCellValueFactory(
                    new PropertyValueFactory<Parc,String>("photoParc")
            );
            
            
            table_list_parc.setItems(data);
            
            
            
            
            
        } catch (SQLException ex) {
            Logger.getLogger(GestionParcController.class.getName()).log(Level.SEVERE, null, ex);
        }
    
        
        
        
        
    } 
    
    
    

    @FXML
    private void retourner(ActionEvent event) {
    }

    @FXML
    private void onClickEvenementAction(ActionEvent event) {
    }

    @FXML
    private void showPane(MouseEvent event) {
    }

    @FXML
    private void supprimerParc(ActionEvent event) throws SQLException {
        Parc a = (Parc) table_list_parc.getSelectionModel().getSelectedItem();
        if(a == null){
            System.out.println("Choisir un de vos parcs");
                   
        }else{
            ParcService.getInstance().supprimerParc(a.getId());
            System.out.println("SUCCESS");
                    resetTableData();
        }
    }
    public void resetTableData() throws SQLException
    {
        List<Parc> listParcs = new ArrayList<>();
        ParcService as = new ParcService();
        listParcs = (List<Parc>) as.AfficherParcsByCin(Session.getLoggedInUser().getCin());
        ObservableList<Parc> data = FXCollections.observableArrayList(listParcs);
        table_list_parc.setItems(data);
    }

    @FXML
    private void logOut(ActionEvent event) {
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

    
}
