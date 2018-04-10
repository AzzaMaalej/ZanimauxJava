/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package zanimaux.GUI;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
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
import javafx.scene.text.Text;
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
    @FXML
    private Pane ajoutMagasin;
    @FXML
    private TextField numRC;
    @FXML
    private TextField nomMagasin;
    @FXML
    private TextField adresseMagsin;
    @FXML
    private TextField villeMagasin;
    @FXML
    private TextField cdpstMagasin;
    @FXML
    private Label lb1;
    @FXML
    private Button picturepathMagasin;
    @FXML
    private TableView table_list_magasin;
    @FXML
    private TableColumn column_numRC;
    @FXML
    private TableColumn column_nom;
    @FXML
    private Button btnsuppressionMagasin;
    @FXML
    private TableColumn column_nbProduit;
    @FXML
    private TableColumn column_adresse;
    @FXML
    private TableColumn column_ville;
    @FXML
    private TableColumn column_cdPstMagasin;
    @FXML
    private TableColumn column_urlMagasin;
    @FXML
    private TableColumn column_idMagasinM;
    @FXML
    private TextField nbProduitMagasin;
    public String filePath;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
       
        try {
    
            MagasinService ms = new MagasinService();
            User a = Session.getLoggedInUser();
            List<Magasin> listMagasin= new ArrayList<>();
            listMagasin = ms.Magasinier(a.getCin());
           
            ObservableList<Magasin> data = FXCollections.observableArrayList(listMagasin); 
            
            column_idMagasinM.setCellValueFactory(
                    new PropertyValueFactory<Magasin,Integer>("idMagasin")
            );
            column_numRC.setCellValueFactory(
                    new PropertyValueFactory<Magasin,String>("numRC")
            );
            column_nom.setCellValueFactory(
                    new PropertyValueFactory<Magasin,String>("nomMagasin")
            );
            column_nbProduit.setCellValueFactory(
                    new PropertyValueFactory<Magasin,Integer>("nbProduit")
            );
            column_adresse.setCellValueFactory(
                    new PropertyValueFactory<Magasin,String>("adresseMagasin")
            );
            column_ville.setCellValueFactory(
                    new PropertyValueFactory<Magasin,String>("villeMagasin")
            );
            column_cdPstMagasin.setCellValueFactory(
                    new PropertyValueFactory<Magasin,Integer>("codePostaleMagasin")
            );
                 
             column_urlMagasin.setCellValueFactory(
                    new PropertyValueFactory<Magasin,String>("photoMagasin")
            );              
            table_list_magasin.setItems(data);
            
            //modification nom magasin
            column_nom.setCellFactory(TextFieldTableCell.forTableColumn());
            column_nom.setOnEditCommit(
                    new EventHandler<TableColumn.CellEditEvent<Magasin, String>>() {
                        
                        @Override
                        public void handle(TableColumn.CellEditEvent<Magasin, String> t) {
                            ((Magasin) t.getTableView().getItems().get(
                                    t.getTablePosition().getRow())
                                    ).setNomMagasin(t.getNewValue());
                            Magasin m = (Magasin) t.getTableView().getItems().get(t.getTablePosition().getRow());
                            m.setNomMagasin(t.getNewValue());
                            ms.modifMagasin(m.getIdMagasin(), m) ;
                        };
                        
                        
                        
                    });
              //modification nbProduit magasin
            column_nbProduit.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));
            column_nbProduit.setOnEditCommit(
                    new EventHandler<TableColumn.CellEditEvent<Magasin, Integer>>() {
                        
                        @Override
                        public void handle(TableColumn.CellEditEvent<Magasin, Integer> t) {
                            ((Magasin) t.getTableView().getItems().get(
                                    t.getTablePosition().getRow())
                                    ).setNbProduit(t.getNewValue().intValue());
                            Magasin m = (Magasin) t.getTableView().getItems().get(t.getTablePosition().getRow());
                            m.setNbProduit(t.getNewValue().intValue());
                            ms.modifMagasin(m.getIdMagasin(), m) ;
                        };
                        
                        
                        
                    });          
                          //modification adresse magasin
            column_adresse.setCellFactory(TextFieldTableCell.forTableColumn());
            column_adresse.setOnEditCommit(
                    new EventHandler<TableColumn.CellEditEvent<Magasin, String>>() {
                        
                        @Override
                        public void handle(TableColumn.CellEditEvent<Magasin, String> t) {
                            ((Magasin) t.getTableView().getItems().get(
                                    t.getTablePosition().getRow())
                                    ).setAdresseMagasin(t.getNewValue());
                            Magasin m = (Magasin) t.getTableView().getItems().get(t.getTablePosition().getRow());
                            m.setAdresseMagasin(t.getNewValue());
                            ms.modifMagasin(m.getIdMagasin(), m) ;
                        };
                    });    
            column_ville.setCellFactory(TextFieldTableCell.forTableColumn());
            column_ville.setOnEditCommit(
                    new EventHandler<TableColumn.CellEditEvent<Magasin, String>>() {
                        
                        @Override
                        public void handle(TableColumn.CellEditEvent<Magasin, String> t) {
                            ((Magasin) t.getTableView().getItems().get(
                                    t.getTablePosition().getRow())
                                    ).setVilleMagasin(t.getNewValue());
                            Magasin m = (Magasin) t.getTableView().getItems().get(t.getTablePosition().getRow());
                            m.setVilleMagasin(t.getNewValue());
                            ms.modifMagasin(m.getIdMagasin(), m) ;
                        };
                    });  
            column_cdPstMagasin.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));
            column_cdPstMagasin.setOnEditCommit(
                    new EventHandler<TableColumn.CellEditEvent<Magasin,Integer>>() {
                        
                        @Override
                        public void handle(TableColumn.CellEditEvent<Magasin, Integer> t) {
                            ((Magasin) t.getTableView().getItems().get(
                                    t.getTablePosition().getRow())
                                    ).setCodePostaleMagasin(t.getNewValue().intValue());
                            Magasin m = (Magasin) t.getTableView().getItems().get(t.getTablePosition().getRow());
                            m.setCodePostaleMagasin(t.getNewValue().intValue());
                            ms.modifMagasin(m.getIdMagasin(), m) ;
                        };
                    });           
                      column_urlMagasin.setCellFactory(TextFieldTableCell.forTableColumn());
            column_urlMagasin.setOnEditCommit(
                    new EventHandler<TableColumn.CellEditEvent<Magasin,String>>() {
                        
                        @Override
                        public void handle(TableColumn.CellEditEvent<Magasin, String> t) {
                            ((Magasin) t.getTableView().getItems().get(
                                    t.getTablePosition().getRow())
                                    ).setPhotoMagasin(t.getNewValue());
                            Magasin m = (Magasin) t.getTableView().getItems().get(t.getTablePosition().getRow());
                            m.setPhotoMagasin(t.getNewValue());
                            ms.modifMagasin(m.getIdMagasin(),m);
                        };
                       
                    }); 
                 
        } catch (SQLException ex) {
            Logger.getLogger(DashboardMagasinController.class.getName()).log(Level.SEVERE, null, ex);
        }
       
          ajoutProduit.setVisible(false);
        ajoutMagasin.setVisible(true);
        table_list_produit.setVisible(false);
        btnsuppression.setVisible(false);
        table_list_magasin.setVisible(true);
        btnsuppressionMagasin.setVisible(true);
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
        filePath = file.getName();
        try {
            BufferedImage bufferedImage = ImageIO.read(file);
            Image image = SwingFXUtils.toFXImage(bufferedImage, null);
            iv.setImage(image);

        } catch (IOException ex) {
            System.err.println(ex);
        }

        return filePath;
        }
        private static void copyFileUsingStream(File source, File dest) throws IOException {
        InputStream is = null;
        OutputStream os = null;
        if(!dest.exists())
            dest.createNewFile();
        try {
            is = new FileInputStream(source);
            os = new FileOutputStream(dest);
            byte[] buffer = new byte[1024];
            int length;
            while ((length = is.read(buffer)) > 0) {
                os.write(buffer, 0, length);
            }
        } catch(Exception e){
            System.out.println(e.getMessage());
        } finally{
            if(is!=null)
            is.close();
            if(os!=null)
            os.close();
        }
    }

    @FXML
    private void afficherMagasin(ActionEvent event) {
        ajoutProduit.setVisible(false);
        ajoutMagasin.setVisible(true);
        table_list_produit.setVisible(false);
        btnsuppression.setVisible(false);
        table_list_magasin.setVisible(true);
        btnsuppressionMagasin.setVisible(true);
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
        ajoutProduit.setVisible(true);
        ajoutMagasin.setVisible(false);
        table_list_produit.setVisible(true);
        btnsuppression.setVisible(true);
        table_list_magasin.setVisible(false);
        btnsuppressionMagasin.setVisible(false);

    }

    @FXML
    private void uploadpicMagasin(MouseEvent event) {
       picturepathMagasin.setText(handle());

    }

    @FXML
    private void ajouterMagasin(ActionEvent event) throws SQLException, IOException {
             if((nomMagasin.getText().equals(""))||(numRC.getText().equals(""))|| (adresseMagsin.getText().equals(""))|| (villeMagasin.getText().equals(""))|| (cdpstMagasin.getText().equals("")) || (picturepathMagasin.getText().equals("")) ){
            lb.setText("champ vide");
            lb.setVisible(true);
        }
        else
        {       copyFileUsingStream(new File(filePath), new File ("src/ImageUtile"+picturepathMagasin.getText()));
               Magasin m= new Magasin();
               m.setNomMagasin(nomMagasin.getText());
               m.setNumRC(numRC.getText());
               m.setAdresseMagasin(adresseMagsin.getText());
               m.setVilleMagasin(villeMagasin.getText());
               m.setCodePostaleMagasin(Integer.parseInt(cdpstMagasin.getText()));
               m.setPhotoMagasin(picturepathMagasin.getText());
               m.setNbProduit(Integer.parseInt(nbProduitMagasin.getText()));
               MagasinService ms= new MagasinService();
               ms.ajouterMagasin(m);
               System.out.println("Ajout réussi");
               resetTableDataMagasin();
    }
    }

    @FXML
    private void supprimerMag(ActionEvent event) throws SQLException {
        Magasin a = (Magasin) table_list_magasin.getSelectionModel().getSelectedItem();
        if(a == null){
            System.out.println("Choisir une de vos magasins");
                   
        }else{
            MagasinService ms = new MagasinService();
            ms.supprimerMagasin(a.getIdMagasin());
            System.out.println("SUCCESS");
                    resetTableDataMagasin();
                    resetTableDataMagasin();
           
        }
    }

    private void resetTableDataMagasin() throws SQLException {

        MagasinService ms = new MagasinService();
        User a = Session.getLoggedInUser();
        List<Magasin> listMagasin= new ArrayList<>();
        listMagasin = ms.Magasinier(a.getCin());
        ObservableList<Magasin> data = FXCollections.observableArrayList(listMagasin); 
        table_list_magasin.setItems(data);    
    }

    @FXML
    private void consulterClient(MouseEvent event) {
        try {
        Stage stage=(Stage) desc.getScene().getWindow(); 
        stage.setTitle("ACCUEIL");
        Parent root = FXMLLoader.load(getClass().getResource("Quiz.fxml"));
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
        } catch (IOException ex) {
           Logger.getLogger(accueilOumaimaController.class.getName()).log(Level.SEVERE, null, ex);
       }
    }

}
