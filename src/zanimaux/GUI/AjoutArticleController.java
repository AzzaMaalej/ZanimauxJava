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
import javafx.geometry.Point2D;
import javafx.scene.Parent;
import javafx.scene.Scene;
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
import javax.imageio.ImageIO;
import zanimaux.Service.Articleservice;
import zanimaux.entities.Articles;
import zanimaux.util.Session;

/**
 * FXML Controller class
 *
 * @author Mariam
 */
public class AjoutArticleController implements Initializable {
    @FXML
    private TextField input_titre;
    @FXML
    private TextArea input_description;
    @FXML
    private ImageView iv;
    @FXML
    private TextField picturepath;
     @FXML Label lb;
    @FXML
    private TableView table_list_article;
    @FXML
    private TableColumn column_id;
    @FXML
    private TableColumn column_titre;
    @FXML
    private TableColumn column_desc;
    @FXML
    private TableColumn column_url;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        try {
            List<Articles> listArticles = new ArrayList<>();
            Articleservice as = Articleservice.getInstance();
            listArticles = as.getAllByVet(Session.getLoggedInUser().getCin());//TODO : SET USER FROM SESSION
            
            ObservableList<Articles> data = FXCollections.observableArrayList(listArticles);
            
            column_id.setCellValueFactory(
                    new PropertyValueFactory<Articles,Integer>("id")
            );
            column_titre.setCellValueFactory(
                    new PropertyValueFactory<Articles,String>("titre")
            );
            column_desc.setCellValueFactory(
                    new PropertyValueFactory<Articles,String>("description")
            );
            column_url.setCellValueFactory(
                    new PropertyValueFactory<Articles,String>("piecejointe")
            );
            
            
            table_list_article.setItems(data);
            
            /*************Modification*************/
            //modifier titre
            
            column_titre.setCellFactory(TextFieldTableCell.forTableColumn());
            column_titre.setOnEditCommit(
                    new EventHandler<TableColumn.CellEditEvent<Articles, String>>() {
                        
                        @Override
                        public void handle(TableColumn.CellEditEvent<Articles, String> t) {
                            try {
                                ((Articles) t.getTableView().getItems().get(
                                        t.getTablePosition().getRow())
                                        ).setTitre(t.getNewValue());
                                Articles a = (Articles) t.getTableView().getItems().get(t.getTablePosition().getRow());
                                a.setTitre(t.getNewValue());
                                
                                Articleservice.getInstance().ModifierArticle(a);
                            } catch (SQLException ex) {
                                Logger.getLogger(AjoutArticleController.class.getName()).log(Level.SEVERE, null, ex);
                            }
                        };
                        
                        
                        
                    });
            
            //modifier desc
            column_desc.setCellFactory(TextFieldTableCell.forTableColumn());
            column_desc.setOnEditCommit(
                    new EventHandler<TableColumn.CellEditEvent<Articles, String>>() {
                        
                        @Override
                        public void handle(TableColumn.CellEditEvent<Articles, String> t) {
                            try {
                                ((Articles) t.getTableView().getItems().get(
                                        t.getTablePosition().getRow())
                                        ).setDescription(t.getNewValue());
                                Articles a = (Articles) t.getTableView().getItems().get(t.getTablePosition().getRow());
                                a.setDescription(t.getNewValue());
                                
                                
                                Articleservice.getInstance().ModifierArticle(a);
                            } catch (SQLException ex) {
                                Logger.getLogger(AjoutArticleController.class.getName()).log(Level.SEVERE, null, ex);
                            }
                        };
                        
                        
                        
                    });
            //
        } catch (SQLException ex) {
            Logger.getLogger(AjoutArticleController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }    

    @FXML
    private void ajouterArticle(ActionEvent event) throws SQLException {
         if(input_titre.getText().equals("") ||  input_description.getText().equals("") || picturepath.getText().equals("") ){
            lb.setText("Fill in the blanks");
            lb.setVisible(true);
        }else{
               Articleservice ase= new Articleservice();
               ase.ajouterArticle(input_titre.getText(), input_description.getText(), picturepath.getText());
               System.out.println("Ajout réussi");
               resetTableData();
             
    }}
    
    public void resetTableData() throws SQLException
    {
        List<Articles> listArticles = new ArrayList<>();
        Articleservice as = new Articleservice();
        listArticles = as.getAllByVet(Session.getLoggedInUser().getCin());
        ObservableList<Articles> data = FXCollections.observableArrayList(listArticles);
        table_list_article.setItems(data);
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
    private void uploadpic(MouseEvent event) {

        picturepath.setText(handle());

    }

    @FXML
    private void logOut(MouseEvent event) {
        
        Session.setLoggedInUser(null);
        Parent root;
             try {
                 root = FXMLLoader.load(getClass().getResource("login.fxml"));
                 Stage myWindow = (Stage) table_list_article.getScene().getWindow();
                 Scene sc = new Scene(root);
                 myWindow.setScene(sc);
                 myWindow.setTitle("Login");
                 myWindow.show();
             } catch (IOException ex) {
                 Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
             }
    }

    @FXML
    private void supprimerArticle(ActionEvent event) throws SQLException {
        Articles a = (Articles) table_list_article.getSelectionModel().getSelectedItem();
        if(a == null){
            System.out.println("Choisir un de vos articles");
                   
        }else{
            Articleservice.getInstance().supprimerArticle(a.getId());
            System.out.println("SUCCESS");
                    resetTableData();
                    resetTableData();
           
        }
    }
    
}
