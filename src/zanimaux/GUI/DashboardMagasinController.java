/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package zanimaux.GUI;

import java.net.URL;
import java.sql.ResultSet;
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
import javafx.fxml.Initializable;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import zanimaux.Service.MagasinService;
import zanimaux.Service.ProduitService;
import zanimaux.entities.Magasin;
import zanimaux.entities.Produit;
import zanimaux.entities.User;
import zanimaux.util.Session;

/**
 * FXML Controller class
 *
 * @author macbookpro
 */
public class DashboardMagasinController implements Initializable {

    @FXML
    private TextField libelle;
    @FXML
    private ImageView imgProduit;
    @FXML
    private TextField picturepath;
    @FXML
    private TextField marque;
    @FXML
    private TextField type;
    @FXML
    private TextField qte;
    @FXML
    private TextField prix;
    @FXML
    private TextField desc;
    @FXML
    private TableColumn column_id;
    @FXML
    private TableColumn column_idMagasin;
    @FXML
    private TableColumn column_lib;
    @FXML
    private TableColumn column_marque;
    @FXML
    private TableColumn column_type;
    @FXML
    private TableColumn column_qte;
    @FXML
    private TableColumn column_prix;
    @FXML
    private TableColumn column_desc;
    @FXML
    private TableColumn column_nbfoisvendu;
    @FXML
    private TableColumn column_url;
    @FXML
    private TableView table_list_produit;
    @FXML
    private ChoiceBox<String>Magasins;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        //ObservableList<String> nomsMagasin = FXCollections.observableArrayList();
        
        
        try {
            /*MagasinService ms = new MagasinService();
            User a = Session.getLoggedInUser();
            List<Magasin> listMagasin= new ArrayList<>();
            listMagasin = ms.Magasinier(a.getCin());
            for(int i =0; i<=listMagasin.size();i++){
                nomsMagasin.add(listMagasin.get(i).getNomMagasin());
            }*/
            MagasinService ms = new MagasinService();
            User a = Session.getLoggedInUser();
            List<Magasin> listMagasin= new ArrayList<>();
            listMagasin = ms.Magasinier(a.getCin());
            List<Produit> listProduit = new ArrayList<>();
            ProduitService sp = new ProduitService();
            List<Produit> list = new ArrayList<>();
            for(int i =0; i<listMagasin.size();i++){
                list=sp.rechercheProduitsMagasin(listMagasin.get(i).getIdMagasin());
                listProduit.addAll(list);
            }
            ObservableList<Produit> data = FXCollections.observableArrayList(listProduit); 
            
            column_id.setCellValueFactory(
                    new PropertyValueFactory<Produit,Integer>("idProduit")
            );
            column_idMagasin.setCellValueFactory(
                    new PropertyValueFactory<Produit,Integer>("idMagasin")
            );
            column_desc.setCellValueFactory(
                    new PropertyValueFactory<Produit,String>("description")
            );
            column_url.setCellValueFactory(
                    new PropertyValueFactory<Produit,String>("photoProduit")
            );
            column_lib.setCellValueFactory(
                    new PropertyValueFactory<Produit,String>("libelle")
            );
            column_marque.setCellValueFactory(
                    new PropertyValueFactory<Produit,String>("marque")
            );
            column_prix.setCellValueFactory(
                    new PropertyValueFactory<Produit,Double>("prix")
            );
            column_nbfoisvendu.setCellValueFactory(
                    new PropertyValueFactory<Produit,Integer>("nbFoisVendu")
            );
             column_qte.setCellValueFactory(
                    new PropertyValueFactory<Produit,Integer>("quantite")
            );           
             column_type.setCellValueFactory(
                    new PropertyValueFactory<Produit,String>("type")
            );              
            table_list_produit.setItems(data);
            
            //modification libelle
            column_lib.setCellFactory(TextFieldTableCell.forTableColumn());
            column_lib.setOnEditCommit(
                    new EventHandler<TableColumn.CellEditEvent<Produit, String>>() {
                        
                        @Override
                        public void handle(TableColumn.CellEditEvent<Produit, String> t) {
                            ((Produit) t.getTableView().getItems().get(
                                    t.getTablePosition().getRow())
                                    ).setLibelle(t.getNewValue());
                            Produit p = (Produit) t.getTableView().getItems().get(t.getTablePosition().getRow());
                            p.setLibelle(t.getNewValue());
                            sp.ModifProduit(p,p.getIdProduit());
                        };
                        
                        
                        
                    });
            
            //modifier marque
            column_marque.setCellFactory(TextFieldTableCell.forTableColumn());
            column_marque.setOnEditCommit(
                    new EventHandler<TableColumn.CellEditEvent<Produit, String>>() {
                        
                        @Override
                        public void handle(TableColumn.CellEditEvent<Produit, String> t) {
                            ((Produit) t.getTableView().getItems().get(
                                    t.getTablePosition().getRow())
                                    ).setMarque(t.getNewValue());
                            Produit p = (Produit) t.getTableView().getItems().get(t.getTablePosition().getRow());
                            p.setMarque(t.getNewValue());
                            sp.ModifProduit(p,p.getIdProduit());
                        };
                    });
            
            //modifier type
            column_type.setCellFactory(TextFieldTableCell.forTableColumn());
            column_type.setOnEditCommit(
                    new EventHandler<TableColumn.CellEditEvent<Produit, String>>() {
                        
                        @Override
                        public void handle(TableColumn.CellEditEvent<Produit, String> t) {
                            ((Produit) t.getTableView().getItems().get(
                                    t.getTablePosition().getRow())
                                    ).setType(t.getNewValue());
                            Produit p = (Produit) t.getTableView().getItems().get(t.getTablePosition().getRow());
                            p.setType(t.getNewValue());
                            sp.ModifProduit(p,p.getIdProduit());
                        };
                       
                    }); 
            /*
            //modifier quantité
            column_qte.setCellFactory(TextFieldTableCell.forTableColumn());
            column_qte.setOnEditCommit(
                    new EventHandler<TableColumn.CellEditEvent<Produit, Integer>>() {
                        
                        @Override
                        public void handle(TableColumn.CellEditEvent<Produit, Integer> t) {
                            ((Produit) t.getTableView().getItems().get(
                                    t.getTablePosition().getRow())
                                    ).setQuantite(t.getNewValue());
                            Produit p = (Produit) t.getTableView().getItems().get(t.getTablePosition().getRow());
                            p.setQuantite(t.getNewValue());
                            sp.ModifProduit(p,p.getIdProduit());
                        };
                       
                    }); 
            */
             //modifier prix
            column_prix.setCellFactory(TextFieldTableCell.forTableColumn());
            column_prix.setOnEditCommit(
                    new EventHandler<TableColumn.CellEditEvent<Produit, String>>() {
                        
                        @Override
                        public void handle(TableColumn.CellEditEvent<Produit, String> t) {
                            ((Produit) t.getTableView().getItems().get(
                                    t.getTablePosition().getRow())
                                    ).setPrix(Double.parseDouble(t.getNewValue()));
                            Produit p = (Produit) t.getTableView().getItems().get(t.getTablePosition().getRow());
                            p.setPrix(Double.parseDouble(t.getNewValue()));
                            sp.ModifProduit(p,p.getIdProduit());
                        };
                       
                    });    
              //modifier description
            column_desc.setCellFactory(TextFieldTableCell.forTableColumn());
            column_desc.setOnEditCommit(
                    new EventHandler<TableColumn.CellEditEvent<Produit, String>>() {
                        
                        @Override
                        public void handle(TableColumn.CellEditEvent<Produit, String> t) {
                            ((Produit) t.getTableView().getItems().get(
                                    t.getTablePosition().getRow())
                                    ).setDescription(t.getNewValue());
                            Produit p = (Produit) t.getTableView().getItems().get(t.getTablePosition().getRow());
                            p.setDescription(t.getNewValue());
                            sp.ModifProduit(p,p.getIdProduit());
                        };
                       
                    });
            /*
              //modfier nbfoisvendu
            column_nbfoisvendu.setCellFactory(TextFieldTableCell.forTableColumn());
            column_nbfoisvendu.setOnEditCommit(
                    new EventHandler<TableColumn.CellEditEvent<Produit, Integer>>() {
                        
                        @Override
                        public void handle(TableColumn.CellEditEvent<Produit, Integer> t) {
                            ((Produit) t.getTableView().getItems().get(
                                    t.getTablePosition().getRow())
                                    ).setNbFoisVendu(t.getNewValue());
                            Produit p = (Produit) t.getTableView().getItems().get(t.getTablePosition().getRow());
                            p.setNbFoisVendu(t.getNewValue());
                            sp.ModifProduit(p,p.getIdProduit());
                        };
                       
                    });  */            
              //modfier url photo
            column_url.setCellFactory(TextFieldTableCell.forTableColumn());
            column_url.setOnEditCommit(
                    new EventHandler<TableColumn.CellEditEvent<Produit,String>>() {
                        
                        @Override
                        public void handle(TableColumn.CellEditEvent<Produit, String> t) {
                            ((Produit) t.getTableView().getItems().get(
                                    t.getTablePosition().getRow())
                                    ).setPhotoProduit(t.getNewValue());
                            Produit p = (Produit) t.getTableView().getItems().get(t.getTablePosition().getRow());
                            p.setPhotoProduit(t.getNewValue());
                            sp.ModifProduit(p,p.getIdProduit());
                        };
                       
                    });             
        } catch (SQLException ex) {
            Logger.getLogger(DashboardMagasinController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }    

    @FXML
    private void ajouterProduit(ActionEvent event) {
    }

    @FXML
    private void uploadpic(MouseEvent event) {
    }

    @FXML
    private void logOut(MouseEvent event) {
    }

    @FXML
    private void supprimerProduit(ActionEvent event) {
    }

    @FXML
    private void ajouterMagasin(ActionEvent event) {
    }
    
}
