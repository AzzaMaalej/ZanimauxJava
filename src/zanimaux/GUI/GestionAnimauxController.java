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
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.util.converter.DoubleStringConverter;
import javafx.util.converter.IntegerStringConverter;
import javax.imageio.ImageIO;
import zanimaux.Service.AnimalService;
import zanimaux.Service.RefugeService;
import zanimaux.entities.Animal;
import zanimaux.entities.Produit;
import zanimaux.entities.Refuge;
import zanimaux.entities.User;
import zanimaux.util.Session;

/**
 * FXML Controller class
 *
 * @author Azza
 */
public class GestionAnimauxController implements Initializable {

    @FXML
    private Label lb;
    @FXML
    private ImageView iv;
    @FXML
    private TextField picturepath;
    @FXML
    private TableColumn column_id;
    @FXML
    private TextField input_id_animal;
    @FXML
    private TextField input_nom_animal;
    @FXML
    private TextField input_race;
    @FXML
    private TextField input_type;
    @FXML
    private TextField input_age;
    @FXML
    private ChoiceBox<String> choice_refuge;
    @FXML
    private ChoiceBox<String> choice_etat;
    @FXML
    private Button BtnChoixImage;
    @FXML
    private TableColumn column_nom;
    @FXML
    private TableColumn column_race;
    @FXML
    private TableColumn column_type;
    @FXML
    private TableColumn column_age;
    @FXML
    private TableColumn column_etat;
    @FXML
    private TableColumn column_refuge;
    @FXML
    private TableColumn column_photo;
    @FXML
    private Button btn_goBack;
    @FXML
    private Label logOut;
    @FXML
    private TableView table_list_animal;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            ObservableList<String> Immatriculation = FXCollections.observableArrayList();
            RefugeService ms;
            
            ms = new RefugeService();
            List<String> listetat = new ArrayList();
            listetat.add("à adopter");
            ObservableList<String> ob = FXCollections.observableArrayList();
            ob.addAll(listetat);
            choice_etat.setItems(ob);
        
            User a = Session.getLoggedInUser();
            List<Refuge> listRefuge= new ArrayList<>();
            listRefuge = ms.AfficherRefugeByCin(a.getCin());
            List<Animal> listAnimal = new ArrayList<>();
            AnimalService sp = new AnimalService();
            List<Animal> list = new ArrayList<>();
            for(int i =0; i<listRefuge.size();i++){
                list=sp.ListerAnimalRefuge(listRefuge.get(i).getImmatriculation());
                listAnimal.addAll(list);
                Immatriculation.add(listRefuge.get(i).getImmatriculation());
            }
            choice_refuge.setItems(Immatriculation);
            ObservableList<Animal> data = FXCollections.observableArrayList(listAnimal); 
             column_id.setCellValueFactory(
                    new PropertyValueFactory<Animal,Integer>("idAnimal")
            );
            column_nom.setCellValueFactory(
                    new PropertyValueFactory<Animal,String>("nomAnimal")
            );
            column_race.setCellValueFactory(
                    new PropertyValueFactory<Animal,String>("race")
            );
            column_type.setCellValueFactory(
                    new PropertyValueFactory<Animal,String>("type")
            );
            column_age.setCellValueFactory(
                    new PropertyValueFactory<Animal,Integer>("age")
            );
            column_etat.setCellValueFactory(
                    new PropertyValueFactory<Animal,String>("etat")
            );
             column_refuge.setCellValueFactory(
                    new PropertyValueFactory<Animal,String>("refuge")
            );           
             column_photo.setCellValueFactory(
                    new PropertyValueFactory<Animal,String>("photoanimal")
            );              
            table_list_animal.setItems(data);
            //modification idAnimal
            column_id.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));
            column_id.setOnEditCommit(
                    new EventHandler<TableColumn.CellEditEvent<Animal, Integer>>() {
                        
                        @Override
                        public void handle(TableColumn.CellEditEvent<Animal, Integer> t) {
                            ((Animal) t.getTableView().getItems().get(
                                    t.getTablePosition().getRow())
                                    ).setIdAnimal(t.getNewValue());
                            Animal p = (Animal) t.getTableView().getItems().get(t.getTablePosition().getRow());
                            p.setIdAnimal(t.getNewValue());
                            sp.ModifierAnimal(p);
                        };
                        
                        
                        
                    });
            
            //modifier nom animal
            column_nom.setCellFactory(TextFieldTableCell.forTableColumn());
            column_nom.setOnEditCommit(
                    new EventHandler<TableColumn.CellEditEvent<Animal, String>>() {
                        
                        @Override
                        public void handle(TableColumn.CellEditEvent<Animal, String> t) {
                            ((Animal) t.getTableView().getItems().get(
                                    t.getTablePosition().getRow())
                                    ).setNomAnimal(t.getNewValue());
                            Animal p = (Animal) t.getTableView().getItems().get(t.getTablePosition().getRow());
                            p.setNomAnimal(t.getNewValue());
                            sp.ModifierAnimal(p);
                        };
                    });
            
           
          
              //modifier race
            column_race.setCellFactory(TextFieldTableCell.forTableColumn());
            column_race.setOnEditCommit(
                    new EventHandler<TableColumn.CellEditEvent<Animal, String>>() {
                        
                        @Override
                        public void handle(TableColumn.CellEditEvent<Animal, String> t) {
                            ((Animal) t.getTableView().getItems().get(
                                    t.getTablePosition().getRow())
                                    ).setRace(t.getNewValue());
                            Animal p = (Animal) t.getTableView().getItems().get(t.getTablePosition().getRow());
                            p.setRace(t.getNewValue());
                            sp.ModifierAnimal(p);
                        };
                       
                    });
             //modifier type
            column_type.setCellFactory(TextFieldTableCell.forTableColumn());
            column_type.setOnEditCommit(
                    new EventHandler<TableColumn.CellEditEvent<Animal, String>>() {
                        
                        @Override
                        public void handle(TableColumn.CellEditEvent<Animal, String> t) {
                            ((Animal) t.getTableView().getItems().get(
                                    t.getTablePosition().getRow())
                                    ).setType(t.getNewValue());
                            Animal p = (Animal) t.getTableView().getItems().get(t.getTablePosition().getRow());
                            p.setType(t.getNewValue());
                            sp.ModifierAnimal(p);
                        };
                       
                    }); 
           
              //modfier age
            column_age.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));
            column_age.setOnEditCommit(
                    new EventHandler<TableColumn.CellEditEvent<Animal, Integer>>() {
                        
                        @Override
                        public void handle(TableColumn.CellEditEvent<Animal, Integer> t) {
                            ((Animal) t.getTableView().getItems().get(
                                    t.getTablePosition().getRow())
                                    ).setAge(t.getNewValue());
                            Animal p = (Animal) t.getTableView().getItems().get(t.getTablePosition().getRow());
                            p.setAge(t.getNewValue());
                            sp.ModifierAnimal(p);
                        };
                       
                    });          
              //modfier etat
            column_etat.setCellFactory(TextFieldTableCell.forTableColumn());
            column_etat.setOnEditCommit(
                    new EventHandler<TableColumn.CellEditEvent<Animal,String>>() {
                        
                        @Override
                        public void handle(TableColumn.CellEditEvent<Animal, String> t) {
                            ((Animal) t.getTableView().getItems().get(
                                    t.getTablePosition().getRow())
                                    ).setEtat(t.getNewValue());
                            Animal p = (Animal) t.getTableView().getItems().get(t.getTablePosition().getRow());
                            p.setEtat(t.getNewValue());
                            sp.ModifierAnimal(p);
                        };
                       
                    });   
               //modfier photo
            column_photo.setCellFactory(TextFieldTableCell.forTableColumn());
            column_photo.setOnEditCommit(
                    new EventHandler<TableColumn.CellEditEvent<Animal,String>>() {
                        
                        @Override
                        public void handle(TableColumn.CellEditEvent<Animal, String> t) {
                            ((Animal) t.getTableView().getItems().get(
                                    t.getTablePosition().getRow())
                                    ).setPhotoAnimal(t.getNewValue());
                            Animal p = (Animal) t.getTableView().getItems().get(t.getTablePosition().getRow());
                            p.setPhotoAnimal(t.getNewValue());
                            sp.ModifierAnimal(p);
                        };
                       
                    });   
        } catch (SQLException ex) {
            Logger.getLogger(GestionAnimauxController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }    

    @FXML
    private void ajouterAnimal(ActionEvent event) throws SQLException {
         String z,zz;
        int r = 0;
        int q=0;
        z = choice_etat.getValue();
        zz=choice_refuge.getValue();
        if ("à adopter".equals(z)) {
            r = 1;
        }
       
         if(input_id_animal.getText().equals("") ||  input_nom_animal.getText().equals("")||  input_age.getText().equals("") ||  input_race.getText().equals("")|| picturepath.getText().equals("")||  input_type.getText().equals("") ||  ((r!=1))||  zz.equals("")){
            lb.setText("Remplir tous les champs");
            lb.setVisible(true);
        }else
         {
               AnimalService ase= new AnimalService();
               Animal listForm=new Animal();
               listForm.setIdAnimal(Integer.parseInt(input_id_animal.getText()));
                 listForm.setNomAnimal(input_nom_animal.getText());
                 listForm.setRefuge(choice_refuge.getValue());
                 listForm.setEtat(choice_etat.getValue());
                 listForm.setType(input_type.getText());
                 listForm.setAge(Integer.parseInt(input_age.getText()));
                 listForm.setPhotoAnimal(picturepath.getText());
                 listForm.setRace(input_race.getText());              
                 ase.ajouterAnimal(listForm);
                 resetTableData();
             
    }
    }
           public void resetTableData() throws SQLException
    {   RefugeService ms = new RefugeService();
        User a = Session.getLoggedInUser();
        List<Refuge> listRefuge;
        listRefuge = ms.AfficherRefugeByCin(a.getCin());
        List<Animal> listAnimal = new ArrayList<>();
        AnimalService ps = new AnimalService();
        List<Animal> list;
            for(int i =0; i<listRefuge.size();i++){
                list=ps.ListerAnimalRefuge(listRefuge.get(i).getImmatriculation());
                listAnimal.addAll(list);
            }
            ObservableList<Animal> data = FXCollections.observableArrayList(listAnimal); 
            table_list_animal.setItems(data);
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
    private void uploadpic(ActionEvent event) {
        
          picturepath.setText(handle());
    }

    @FXML
    private void logOut(MouseEvent event) {
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
    private void supprimerAnimal(ActionEvent event) throws SQLException {
         Animal a = (Animal) table_list_animal.getSelectionModel().getSelectedItem();
        if(a == null){
            System.out.println("Choisir un de vos animaux");
                   
        }else{
            AnimalService.getInstance().SupprimerAnimal(a.getIdAnimal());
            System.out.println("SUCCESS");
                    resetTableData();
                    
           
        }
    }

    @FXML
    private void GoBack(ActionEvent event) {
         Session.setLoggedInUser(null);
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
