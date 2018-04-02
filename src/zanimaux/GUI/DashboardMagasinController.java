/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package zanimaux.GUI;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
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
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.util.converter.DoubleStringConverter;
import javafx.util.converter.IntegerStringConverter;
import javax.imageio.ImageIO;
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
    private ChoiceBox<Integer>idMagasin;
    private Button BtnChoixImage;
    @FXML
    private ImageView iv;
    @FXML
    private Pane ajoutProduit;
    @FXML
    private Label lb;
    @FXML
    private Button picturepath;
    @FXML
    private Button btnsuppression;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
       
         
       ajoutProduit.setVisible(false);
       table_list_produit.setVisible(false);
       btnsuppression.setVisible(false);
    }    

    @FXML
    private void ajouterProduit(ActionEvent event) throws SQLException {
        
        if((libelle.getText().equals(""))||(marque.getText().equals(""))|| (type.getText().equals(""))|| (qte.getText().equals(""))|| (prix.getText().equals("")) || (picturepath.getText().equals("")) ){
            lb.setText("champ vide");
            lb.setVisible(true);
        }
        else
        {
               Produit p= new Produit();
               p.setLibelle(libelle.getText());
               p.setDescription(desc.getText());
               p.setMarque(marque.getText());
               p.setPhotoProduit(picturepath.getText());
               p.setIdMagasin(idMagasin.getValue());
               p.setPrix(Double.parseDouble(prix.getText()));
               p.setQuantite(Integer.parseInt(qte.getText()));
               p.setType(type.getText());
               ProduitService ps= new ProduitService();
               ps.ajouterProduit(p);
               System.out.println("Ajout réussi");
               resetTableData();
    }
    }

    @FXML
    private void uploadpic(MouseEvent event) {
         picturepath.setText(handle());
    }

    @FXML
    private void logOut(MouseEvent event) {
                
        Session.setLoggedInUser(null);
        Parent root;
             try {
                 root = FXMLLoader.load(getClass().getResource("login.fxml"));
                 Stage myWindow = (Stage) table_list_produit.getScene().getWindow();
                 Scene sc = new Scene(root);
                 myWindow.setScene(sc);
                 myWindow.setTitle("Login");
                 myWindow.show();
             } catch (IOException ex) {
                 Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
             }
    }

    

    @FXML
    private void supprimerProduit(ActionEvent event) throws SQLException 
    {
        Produit a = (Produit) table_list_produit.getSelectionModel().getSelectedItem();
        if(a == null){
            System.out.println("Choisir un de vos Produit");
                   
        }else{
            ProduitService ps = new ProduitService();
            ps.SupprimerProduit(a.getIdProduit());
            System.out.println("SUCCESS");
                    resetTableData();
                    resetTableData();
           
        }}
       public void resetTableData() throws SQLException
    {
        MagasinService ms = new MagasinService();
        User a = Session.getLoggedInUser();
        List<Magasin> listMagasin= new ArrayList<>();
        listMagasin = ms.Magasinier(a.getCin());
        List<Produit> listProduit = new ArrayList<>();
        ProduitService ps = new ProduitService();
        List<Produit> list = new ArrayList<>();
            for(int i =0; i<listMagasin.size();i++){
                list=ps.rechercheProduitsMagasin(listMagasin.get(i).getIdMagasin());
                listProduit.addAll(list);
            }
            ObservableList<Produit> data = FXCollections.observableArrayList(listProduit); 
            table_list_produit.setItems(data);
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
            iv.setImage(image);

        } catch (IOException ex) {
            System.err.println(ex);
        }

        return filePath;
        }

    @FXML
    private void afficherMagasin(ActionEvent event) {
    }

    @FXML
    private void afficherProduit(ActionEvent event) {
        ObservableList<Integer> idsMagasin = FXCollections.observableArrayList();
        
        
        try {
    
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
                idsMagasin.add(listMagasin.get(i).getIdMagasin());
            }
            idMagasin.setItems(idsMagasin);
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
            
            //modifier quantité
            column_qte.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));
            column_qte.setOnEditCommit(
                    new EventHandler<TableColumn.CellEditEvent<Produit, Integer>>() {
                        
                        @Override
                        public void handle(TableColumn.CellEditEvent<Produit, Integer> t) {
                            ((Produit) t.getTableView().getItems().get(
                                    t.getTablePosition().getRow())
                                    ).setQuantite(t.getNewValue().intValue());
                            Produit p = (Produit) t.getTableView().getItems().get(t.getTablePosition().getRow());
                            p.setQuantite(t.getNewValue().intValue());
                            sp.ModifProduit(p,p.getIdProduit());
                        };
                       
                    }); 
            
             //modifier prix
            column_prix.setCellFactory(TextFieldTableCell.forTableColumn(new DoubleStringConverter()));
            column_prix.setOnEditCommit(
                    new EventHandler<TableColumn.CellEditEvent<Produit, Double>>() {
                        
                        @Override
                        public void handle(TableColumn.CellEditEvent<Produit, Double> t) {
                            ((Produit) t.getTableView().getItems().get(
                                    t.getTablePosition().getRow())
                                    ).setPrix(t.getNewValue().doubleValue() );
                            Produit p = (Produit) t.getTableView().getItems().get(t.getTablePosition().getRow());
                            p.setPrix(t.getNewValue().doubleValue());
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
            
              //modfier nbfoisvendu
            column_nbfoisvendu.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));
            column_nbfoisvendu.setOnEditCommit(
                    new EventHandler<TableColumn.CellEditEvent<Produit, Integer>>() {
                        
                        @Override
                        public void handle(TableColumn.CellEditEvent<Produit, Integer> t) {
                            ((Produit) t.getTableView().getItems().get(
                                    t.getTablePosition().getRow())
                                    ).setNbFoisVendu(t.getNewValue().intValue());
                            Produit p = (Produit) t.getTableView().getItems().get(t.getTablePosition().getRow());
                            p.setNbFoisVendu(t.getNewValue().intValue());
                            sp.ModifProduit(p,p.getIdProduit());
                        };
                       
                    });          
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
        ajoutProduit.setVisible(false);
        table_list_produit.setVisible(true);
        btnsuppression.setVisible(true);
    }

    @FXML
    private void afficherFormMagasin(ActionEvent event) {
    }

    @FXML
    private void afficherFormProduit(ActionEvent event) {
       ajoutProduit.setVisible(true);
       table_list_produit.setVisible(false);
       btnsuppression.setVisible(false);
    }




    
}
