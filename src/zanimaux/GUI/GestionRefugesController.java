/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package zanimaux.GUI;

import com.jfoenix.controls.JFXTextField;
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
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.util.converter.IntegerStringConverter;
import javafx.util.converter.NumberStringConverter;
import javax.imageio.ImageIO;
import zanimaux.Service.Articleservice;
import zanimaux.Service.RefugeService;
import zanimaux.entities.Articles;
import zanimaux.entities.Refuge;
import zanimaux.entities.User;
import zanimaux.util.Session;

/**
 * FXML Controller class
 *
 * @author Azza
 */
public class GestionRefugesController implements Initializable {

    private AnchorPane anchorEvent;
    @FXML
    private Label lb;
    @FXML
    private ImageView iv;
    @FXML
    private TextField picturepath;
    private TableView<?> table_list_article;
    @FXML
    private TextField input_Immatriculation;
    @FXML
    private Button button_ajouter_refuge;
    @FXML
    private TextField input_nom;
    @FXML
    private TextField input_email;
    @FXML
    private TextField input_telephone;
    @FXML
    private TextField input_fax;
    @FXML
    private TextField input_adresse;
    @FXML
    private TextField input_gouvernement;
    @FXML
    private TextField input_codePostale;
    @FXML
    private ChoiceBox<String> choiceBox_chat;
    @FXML
    private ChoiceBox<String> choiceBox_chien;
    @FXML
    private ChoiceBox<String> choiceBox_rongeur;
    @FXML
    private TextField input_autre;
    @FXML
    private TableColumn column_immatriculation;
    @FXML
    private TableColumn column_nom;
    @FXML
    private TableColumn column_email;
    @FXML
    private TableColumn column_tel;
    @FXML
    private TableColumn column_fax;
    @FXML
    private TableColumn column_adresse;
    @FXML
    private TableColumn column_gouvernement;
    @FXML
    private TableColumn column_codePostale;
    @FXML
    private TableColumn column_chat;
    @FXML
    private TableColumn column_chien;
    @FXML
    private TableColumn column_rongeur;
    @FXML
    private TableColumn column_autre;
    @FXML
    private TableColumn column_photo;
    @FXML
    private Button btnSupprimerRefuge;
    @FXML
    private Button logOut;
     @FXML
    private TableView table_list_refuge;
    @FXML
    private Button btn_goBack;
   public String filePath;
    @FXML
    private Button btnPhoto;
    

    /**
     * Initializes the controller class.
     */
    @Override
    
    public void initialize(URL url, ResourceBundle rb) {
        List<String> list = new ArrayList();
        list.add("Oui");
        list.add("Non");
       
        
        ObservableList<String> ob = FXCollections.observableArrayList();
        ob.addAll(list);
        choiceBox_chat.setItems(ob);
        choiceBox_chien.setItems(ob);
        choiceBox_rongeur.setItems(ob);
         try {
            List<Refuge> listRefuges = new ArrayList<>();
            RefugeService as = RefugeService.getInstance();
            User user=Session.getLoggedInUser();
            String cin=user.getCin();
            listRefuges = as.AfficherRefugeByCin(cin);//TODO : SET USER FROM SESSION
            
            ObservableList<Refuge> data = FXCollections.observableArrayList(listRefuges);
            
            column_immatriculation.setCellValueFactory(
                    new PropertyValueFactory<Refuge,String>("immatriculation")
            );
            column_nom.setCellValueFactory(
                    new PropertyValueFactory<Refuge,String>("nomRefuge")
            );
            column_email.setCellValueFactory(
                    new PropertyValueFactory<Refuge,String>("emailRefuge")
            );
            column_tel.setCellValueFactory(
                    new PropertyValueFactory<Refuge,Integer>("telephoneRefuge")
            );
            column_fax.setCellValueFactory(
                    new PropertyValueFactory<Refuge,Integer>("faxRefuge")
            );
            column_adresse.setCellValueFactory(
                    new PropertyValueFactory<Refuge,String>("adresseRefuge")
            );
            column_codePostale.setCellValueFactory(
                    new PropertyValueFactory<Refuge,Integer>("codePostaleRefuge")
            );
            column_gouvernement.setCellValueFactory(
                    new PropertyValueFactory<Refuge,String>("gouvernementRefuge")
            );
            
            column_chat.setCellValueFactory(
                    new PropertyValueFactory<Refuge,String>("chat")
            );
            column_chien.setCellValueFactory(
                    new PropertyValueFactory<Refuge,String>("chien")
            );
            column_rongeur.setCellValueFactory(
                    new PropertyValueFactory<Refuge,String>("rongeur")
            );
            column_autre.setCellValueFactory(
                    new PropertyValueFactory<Refuge,String>("autre")
            );
            column_photo.setCellValueFactory(
                    new PropertyValueFactory<Refuge,String>("photorefuge")
            );
            
            
            table_list_refuge.setItems(data);
            
            /*************Modification*************/
            //modifier immatriculation
            
            column_immatriculation.setCellFactory(TextFieldTableCell.forTableColumn());
            column_immatriculation.setOnEditCommit(
                    new EventHandler<TableColumn.CellEditEvent<Refuge, String>>() {
                        
                        @Override
                        public void handle(TableColumn.CellEditEvent<Refuge, String> t) {
                            try {
                                ((Refuge) t.getTableView().getItems().get(
                                        t.getTablePosition().getRow())
                                        ).setImmatriculation(t.getNewValue());
                                Refuge a = (Refuge) t.getTableView().getItems().get(t.getTablePosition().getRow());
                                a.setImmatriculation(t.getNewValue());
                                
                                RefugeService.getInstance().ModifierRefuge(a);
                            } catch (SQLException ex) {
                                Logger.getLogger(GestionRefugesController.class.getName()).log(Level.SEVERE, null, ex);
                            }
                        };
                        
                        
                        
                    });
            
            //modifier nom refuge
            column_nom.setCellFactory(TextFieldTableCell.forTableColumn());
            column_nom.setOnEditCommit(
                    new EventHandler<TableColumn.CellEditEvent<Refuge, String>>() {
                        
                        @Override
                        public void handle(TableColumn.CellEditEvent<Refuge, String> t) {
                            try {
                                ((Refuge) t.getTableView().getItems().get(
                                        t.getTablePosition().getRow())
                                        ).setNomRefuge(t.getNewValue());
                               Refuge a = (Refuge) t.getTableView().getItems().get(t.getTablePosition().getRow());
                                a.setNomRefuge(t.getNewValue());
                                
                                
                                RefugeService.getInstance().ModifierRefuge(a);
                            } catch (SQLException ex) {
                                Logger.getLogger(GestionRefugesController.class.getName()).log(Level.SEVERE, null, ex);
                            }
                        };
                        
                        
                        
                    });
            //modifier email refuge
            column_email.setCellFactory(TextFieldTableCell.forTableColumn());
            column_email.setOnEditCommit(
                    new EventHandler<TableColumn.CellEditEvent<Refuge, String>>() {
                        
                        @Override
                        public void handle(TableColumn.CellEditEvent<Refuge, String> t) {
                            try {
                                ((Refuge) t.getTableView().getItems().get(
                                        t.getTablePosition().getRow())
                                        ).setEmailRefuge(t.getNewValue());
                               Refuge a = (Refuge) t.getTableView().getItems().get(t.getTablePosition().getRow());
                                a.setEmailRefuge(t.getNewValue());
                                
                                
                                RefugeService.getInstance().ModifierRefuge(a);
                            } catch (SQLException ex) {
                                Logger.getLogger(GestionRefugesController.class.getName()).log(Level.SEVERE, null, ex);
                            }
                        };
                        
                        
                        
                    });
            //modifier telephone refuge
            column_tel.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));
            column_tel.setOnEditCommit(
                    new EventHandler<TableColumn.CellEditEvent<Refuge, Integer>>() {
                        
                        @Override
                        public void handle(TableColumn.CellEditEvent<Refuge, Integer> t) {
                            try {
                                ((Refuge) t.getTableView().getItems().get(
                                        t.getTablePosition().getRow())
                                        ).setTelephoneRefuge(t.getNewValue().intValue());
                               Refuge a = (Refuge) t.getTableView().getItems().get(t.getTablePosition().getRow());
                                a.setTelephoneRefuge(t.getNewValue().intValue());
                                
                                
                                RefugeService.getInstance().ModifierRefuge(a);
                            } catch (SQLException ex) {
                                Logger.getLogger(GestionRefugesController.class.getName()).log(Level.SEVERE, null, ex);
                            }
                        };
                        
                        
                        
                    });
            //modifier fax refuge
            column_fax.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));
            column_fax.setOnEditCommit(
                    new EventHandler<TableColumn.CellEditEvent<Refuge, Integer>>() {
                        
                        @Override
                        public void handle(TableColumn.CellEditEvent<Refuge, Integer> t) {
                            try {
                                ((Refuge) t.getTableView().getItems().get(
                                        t.getTablePosition().getRow())
                                        ).setFaxRefuge(t.getNewValue().intValue());
                               Refuge a = (Refuge) t.getTableView().getItems().get(t.getTablePosition().getRow());
                                a.setFaxRefuge(t.getNewValue().intValue());
                                
                                
                                RefugeService.getInstance().ModifierRefuge(a);
                            } catch (SQLException ex) {
                                Logger.getLogger(GestionRefugesController.class.getName()).log(Level.SEVERE, null, ex);
                            }
                        };
                        
                        
                        
                    });
            //modifier adresse refuge
            column_adresse.setCellFactory(TextFieldTableCell.forTableColumn());
            column_adresse.setOnEditCommit(
                    new EventHandler<TableColumn.CellEditEvent<Refuge, String>>() {
                        
                        @Override
                        public void handle(TableColumn.CellEditEvent<Refuge, String> t) {
                            try {
                                ((Refuge) t.getTableView().getItems().get(
                                        t.getTablePosition().getRow())
                                        ).setAdresseRefuge(t.getNewValue());
                               Refuge a = (Refuge) t.getTableView().getItems().get(t.getTablePosition().getRow());
                                a.setAdresseRefuge(t.getNewValue());
                                
                                
                                RefugeService.getInstance().ModifierRefuge(a);
                            } catch (SQLException ex) {
                                Logger.getLogger(GestionRefugesController.class.getName()).log(Level.SEVERE, null, ex);
                            }
                        };
                        
                        
                        
                    });
            //modifier gouvernemtn refuge
            column_gouvernement.setCellFactory(TextFieldTableCell.forTableColumn());
            column_gouvernement.setOnEditCommit(
                    new EventHandler<TableColumn.CellEditEvent<Refuge, String>>() {
                        
                        @Override
                        public void handle(TableColumn.CellEditEvent<Refuge, String> t) {
                            try {
                                ((Refuge) t.getTableView().getItems().get(
                                        t.getTablePosition().getRow())
                                        ).setGouvernementRefuge(t.getNewValue());
                               Refuge a = (Refuge) t.getTableView().getItems().get(t.getTablePosition().getRow());
                                a.setGouvernementRefuge(t.getNewValue());
                                
                                
                                RefugeService.getInstance().ModifierRefuge(a);
                            } catch (SQLException ex) {
                                Logger.getLogger(GestionRefugesController.class.getName()).log(Level.SEVERE, null, ex);
                            }
                        };
                        
                        
                        
                    });
            //modifier code postale refuge
            column_codePostale.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));
            column_codePostale.setOnEditCommit(
                    new EventHandler<TableColumn.CellEditEvent<Refuge, Integer>>() {
                        
                        @Override
                        public void handle(TableColumn.CellEditEvent<Refuge, Integer> t) {
                            try {
                                ((Refuge) t.getTableView().getItems().get(
                                        t.getTablePosition().getRow())
                                        ).setCodePostaleRefuge(t.getNewValue().intValue());
                               Refuge a = (Refuge) t.getTableView().getItems().get(t.getTablePosition().getRow());
                                a.setCodePostaleRefuge(t.getNewValue().intValue());
                                
                                
                                RefugeService.getInstance().ModifierRefuge(a);
                            } catch (SQLException ex) {
                                Logger.getLogger(GestionRefugesController.class.getName()).log(Level.SEVERE, null, ex);
                            }
                        };
                        
                        
                        
                    });
            //modifier chat refuge
            column_chat.setCellFactory(TextFieldTableCell.forTableColumn());
            column_chat.setOnEditCommit(
                    new EventHandler<TableColumn.CellEditEvent<Refuge, String>>() {
                        
                        @Override
                        public void handle(TableColumn.CellEditEvent<Refuge, String> t) {
                            try {
                                ((Refuge) t.getTableView().getItems().get(
                                        t.getTablePosition().getRow())
                                        ).setChat(t.getNewValue());
                               Refuge a = (Refuge) t.getTableView().getItems().get(t.getTablePosition().getRow());
                                a.setChat(t.getNewValue());
                                
                                
                                RefugeService.getInstance().ModifierRefuge(a);
                            } catch (SQLException ex) {
                                Logger.getLogger(GestionRefugesController.class.getName()).log(Level.SEVERE, null, ex);
                            }
                        };
                        
                        
                        
                    });
            //modifier chien refuge
            column_chien.setCellFactory(TextFieldTableCell.forTableColumn());
            column_chien.setOnEditCommit(
                    new EventHandler<TableColumn.CellEditEvent<Refuge, String>>() {
                        
                        @Override
                        public void handle(TableColumn.CellEditEvent<Refuge, String> t) {
                            try {
                                ((Refuge) t.getTableView().getItems().get(
                                        t.getTablePosition().getRow())
                                        ).setChien(t.getNewValue());
                               Refuge a = (Refuge) t.getTableView().getItems().get(t.getTablePosition().getRow());
                                a.setChien(t.getNewValue());
                                
                                
                                RefugeService.getInstance().ModifierRefuge(a);
                            } catch (SQLException ex) {
                                Logger.getLogger(GestionRefugesController.class.getName()).log(Level.SEVERE, null, ex);
                            }
                        };
                        
                        
                        
                    });
            //modifier rongeur refuge
            column_rongeur.setCellFactory(TextFieldTableCell.forTableColumn());
            column_rongeur.setOnEditCommit(
                    new EventHandler<TableColumn.CellEditEvent<Refuge, String>>() {
                        
                        @Override
                        public void handle(TableColumn.CellEditEvent<Refuge, String> t) {
                            try {
                                ((Refuge) t.getTableView().getItems().get(
                                        t.getTablePosition().getRow())
                                        ).setRongeur(t.getNewValue());
                               Refuge a = (Refuge) t.getTableView().getItems().get(t.getTablePosition().getRow());
                                a.setRongeur(t.getNewValue());
                                
                                
                                RefugeService.getInstance().ModifierRefuge(a);
                            } catch (SQLException ex) {
                                Logger.getLogger(GestionRefugesController.class.getName()).log(Level.SEVERE, null, ex);
                            }
                        };
                        
                        
                        
                    });
            //modifier autre refuge
            column_autre.setCellFactory(TextFieldTableCell.forTableColumn());
            column_autre.setOnEditCommit(
                    new EventHandler<TableColumn.CellEditEvent<Refuge, String>>() {
                        
                        @Override
                        public void handle(TableColumn.CellEditEvent<Refuge, String> t) {
                            try {
                                ((Refuge) t.getTableView().getItems().get(
                                        t.getTablePosition().getRow())
                                        ).setAutre(t.getNewValue());
                               Refuge a = (Refuge) t.getTableView().getItems().get(t.getTablePosition().getRow());
                                a.setAutre(t.getNewValue());
                                
                                
                                RefugeService.getInstance().ModifierRefuge(a);
                            } catch (SQLException ex) {
                                Logger.getLogger(GestionRefugesController.class.getName()).log(Level.SEVERE, null, ex);
                            }
                        };
                        
                        
                        
                    });
            //modifier photo refuge
            column_photo.setCellFactory(TextFieldTableCell.forTableColumn());
            column_photo.setOnEditCommit(
                    new EventHandler<TableColumn.CellEditEvent<Refuge, String>>() {
                        
                        @Override
                        public void handle(TableColumn.CellEditEvent<Refuge, String> t) {
                            try {
                                ((Refuge) t.getTableView().getItems().get(
                                        t.getTablePosition().getRow())
                                        ).setPhotoRefuge(t.getNewValue());
                               Refuge a = (Refuge) t.getTableView().getItems().get(t.getTablePosition().getRow());
                                a.setPhotoRefuge(t.getNewValue());
                                
                                
                                RefugeService.getInstance().ModifierRefuge(a);
                            } catch (SQLException ex) {
                                Logger.getLogger(GestionRefugesController.class.getName()).log(Level.SEVERE, null, ex);
                            }
                        };
                        
                        
                        
                    });
            //
        } catch (SQLException ex) {
            Logger.getLogger(AjoutArticleController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }    
 

    @FXML
    private void ajouterRefuge(ActionEvent event) throws SQLException, IOException {
        copyFileUsingStream(new File(filePath), new File ("src/ImageUtile"+picturepath.getText()));
        String z;
        int r = 0;
        z = choiceBox_chat.getValue();
        if ("Oui".equals(z)) {
            r = 1;
        } else if ("Non".equals(z)) {
            r = 2;}
       
         if(input_Immatriculation.getText().equals("") ||  input_nom.getText().equals("")||  input_telephone.getText().equals("") ||  input_email.getText().equals("")|| picturepath.getText().equals("")||  input_fax.getText().equals("") ||  input_adresse.getText().equals("")||  input_gouvernement.getText().equals("")||  input_codePostale.getText().equals("")||((r!=1)&&(r!=2))||  input_autre.getText().equals("")){
            lb.setText("Remplir tous les champs");
            lb.setVisible(true);
        }else{
              User user=Session.getLoggedInUser();
              String cin=user.getCin();
               RefugeService ase= new RefugeService();
               Refuge listForm=new Refuge();
               listForm.setImmatriculation(input_Immatriculation.getText());
                 listForm.setCin(cin);
                 listForm.setNomRefuge(input_nom.getText());
                 listForm.setEmailRefuge(input_email.getText());
                 listForm.setTelephoneRefuge(Integer.parseInt(input_telephone.getText()));
                 listForm.setFaxRefuge(Integer.parseInt(input_fax.getText()));
                 listForm.setAdresseRefuge(input_adresse.getText());
                 listForm.setCodePostaleRefuge(Integer.parseInt(input_codePostale.getText()));
                 listForm.setGouvernementRefuge(input_gouvernement.getText());
                 listForm.setPhotoRefuge(picturepath.getText());
                 listForm.setChat(choiceBox_chat.getValue());
                 listForm.setChien(choiceBox_chien.getValue());
                 listForm.setRongeur(choiceBox_rongeur.getValue());
                 listForm.setAutre(input_autre.getText());
               ase.ajouterRefuge(listForm);
               System.out.println("Ajout réussi");
               resetTableData();
             
    }
    }
     public void resetTableData() throws SQLException
    {
        List<Refuge> listRefuges ;
        RefugeService as = new RefugeService();
        listRefuges = (List<Refuge>) as.AfficherRefugeByCin(Session.getLoggedInUser().getCin());
        ObservableList<Refuge> data = FXCollections.observableArrayList(listRefuges);
        table_list_refuge.setItems(data);
    }

      public String handle(){
        FileChooser fileChooser = new FileChooser();

        //Set extension filter
        FileChooser.ExtensionFilter extFilterJPG = new FileChooser.ExtensionFilter("JPG files (*.jpg)", "*.JPG");
        FileChooser.ExtensionFilter extFilterPNG = new FileChooser.ExtensionFilter("PNG files (*.png)", "*.PNG");
        fileChooser.getExtensionFilters().addAll(extFilterJPG, extFilterPNG);

        //Show open file dialog
        File file = fileChooser.showOpenDialog(null);
        picturepath.setText(file.getName());
        filePath = file.getAbsolutePath();
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
    private void uploadpic(ActionEvent event) {
        Text text=new Text(); 
        text.setText(handle());
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
    private void supprimerRefuge(ActionEvent event) throws SQLException {
         Refuge a = (Refuge) table_list_refuge.getSelectionModel().getSelectedItem();
        if(a == null){
            System.out.println("Choisir un de vos refuges");
                   
        }else{
            RefugeService.getInstance().SupprimerRefuge(a.getImmatriculation());
            System.out.println("SUCCESS");
                    resetTableData();
                    resetTableData();
           
        }
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
    @FXML
    private void GoBack(ActionEvent event) {
        
        Parent root;
             try {
                 root = FXMLLoader.load(getClass().getResource("RefugeDashboard.fxml"));
                 Stage myWindow = (Stage) btn_goBack.getScene().getWindow();
                 Scene sc = new Scene(root);
                 myWindow.setScene(sc);
                 myWindow.setTitle("Proprietaire refuge");
                 myWindow.show();
             } catch (IOException ex) {
                 Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
             }
    }

   
    
   
}
