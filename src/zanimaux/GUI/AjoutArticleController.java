/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package zanimaux.GUI;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
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
    private Label lb;
    @FXML
    private TextField input_titre;
    @FXML
    private TextArea input_description;
    @FXML
    private ImageView iv;
    @FXML
    private TextField picturepath;
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
    @FXML
    private ChoiceBox choice;
    
   
    public String fiilePath;
    private static final int BUFFER_SIZE = 4096;



    /**
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
            choice.setItems(FXCollections.observableArrayList("Photo", "Video"));
            
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
               System.out.println("clicked on1");
        
//               Notifications.create()
//                .title("Upload complete")
//                .text("Saved").position(Pos.TOP_RIGHT)
//                .showWarning();
//        
//                
                
                
             
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
      public String handle2() throws MalformedURLException, IOException {
           Stage stage = new Stage();
        FileChooser fil = new FileChooser();
        fil.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("MP4", "*.mp4"));
        File selectedFile = fil.showOpenDialog(stage);

        String p = selectedFile.getPath();
        String ftpUrl = "ftp://%s:%s@%s/%s;type=i";
        String host = Session.getIp();
        String user = "Mariam";
        
        String uploadPath = "file:///C:/Users/Mariam/Desktop/";

        ftpUrl = String.format(ftpUrl, user, host, uploadPath);
        System.out.println("Upload URL: " + ftpUrl);

       
            URL url = new URL(ftpUrl);
            URLConnection conn = url.openConnection();
            OutputStream outputStream = conn.getOutputStream();
            FileInputStream inputStream = new FileInputStream(p);

            byte[] buffer = new byte[BUFFER_SIZE];
            int bytesRead = -1;
            while ((bytesRead = inputStream.read(buffer)) != -1) {
                outputStream.write(buffer, 0, bytesRead);
            }

            inputStream.close();
            outputStream.close();

            System.out.println("File uploaded");
          
         return url.toString();
      
      }
    @FXML
    private void uploadpic(MouseEvent event) throws IOException {
        String type;
            System.out.println("c bon0");
    if(choice.getSelectionModel().getSelectedItem() == null){
           type="";
        }else{
        picturepath.setText("");
            type= (String) choice.getSelectionModel().getSelectedItem();
        }
        System.out.println("c bon");
    if (type.equalsIgnoreCase("photo")){
     picturepath.setText(handle());}
    else if (type.equalsIgnoreCase("video")){
     picturepath.setText(handle2());}
    else{};
        
       
        
            
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
