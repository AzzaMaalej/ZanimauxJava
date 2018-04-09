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
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.Pane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javax.imageio.ImageIO;
import zanimaux.Service.PromenadeService;
import zanimaux.entities.Promenade;
import zanimaux.entities.User;
import zanimaux.util.Session;
import zanimaux.util.Validation;

/**
 * FXML Controller class
 *
 * @author BelhassenLimam
 */
public class AjoutPromenadeController implements Initializable {

    @FXML
    private Button userName;
    @FXML
    private Pane pane;
    @FXML
    private Button btn11;
    @FXML
    private Button btn1;
    @FXML
    private AnchorPane anchorEvent;
    @FXML
    private TextField nom;
    private TextField adr;
    private TextField ville;
    private TextField codep;
    @FXML
    private ChoiceBox<String> catg;
    @FXML
    private Button btn;
    @FXML
    private Button photo;
    
    
    
    ObservableList<String> list=FXCollections.observableArrayList("val1","val2");
    @FXML
    private Label nomLabel;
    
    @FXML
    private Label villeLabel;
    
    @FXML
    private Label photoLabel;
    @FXML
    private Label catgLabel;
    @FXML
    private ImageView iv;
    @FXML
    private Button ajou;
    @FXML
    private Button aff;
    @FXML
    private Label chooseFile;
    public String filePath;
    @FXML
    private Button acc;
    @FXML
    private Button reinit;
    @FXML
    private Button logOut;
    @FXML
    private DatePicker datedeb;
    @FXML
    private DatePicker datef;
    @FXML
    private TextField lieu;
    @FXML
    private TextArea desc;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        User u= Session.getLoggedInUser();
        userName.setText(u.getUsername());
        // TODO
        List<String> list = new ArrayList();
        list.add("Promenade");
        list.add("Garde");
        
        ObservableList<String> ob = FXCollections.observableArrayList();
        ob.addAll(list);
        catg.setItems(ob);
        Image imageAjout = new Image("zanimaux/ImageUtile/checked.png",40,40,false,false);
        btn.setBackground(Background.EMPTY);
            btn.setGraphic(new ImageView(imageAjout));
            Image imagePhoto = new Image("zanimaux/ImageUtile/photo.png",26,26,false,false);
        photo.setBackground(Background.EMPTY);
        photo.setGraphic(new ImageView(imagePhoto));
        Image Ref = new Image("zanimaux/ImageUtile/refresh.png",26,26,false,false);
        reinit.setBackground(Background.EMPTY);
            reinit.setGraphic(new ImageView(Ref));
    }    
    
    //Action pour récuper la photo et son emplacement
     public String handle(){
        FileChooser fileChooser = new FileChooser();

        //Set extension filter
        FileChooser.ExtensionFilter extFilterJPG = new FileChooser.ExtensionFilter("JPG files (*.jpg)", "*.JPG");
        FileChooser.ExtensionFilter extFilterPNG = new FileChooser.ExtensionFilter("PNG files (*.png)", "*.PNG");
        fileChooser.getExtensionFilters().addAll(extFilterJPG, extFilterPNG);

        //Show open file dialog
        File file = fileChooser.showOpenDialog(null);
        chooseFile.setText(file.getName());
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
    private void chooseAction(ActionEvent event) {
        photo.setText(handle());
    
    }
    
         public boolean controleSaisie2() throws IOException, SQLException {
        boolean saisie = true;
        String z;
        int r = 0;
        z = catg.getValue();
        if ("Promenade".equals(z)) {
            r = 1;
        } else if ("Garde".equals(z)) {
            r = 2;
        
        }
        if((r!=1)&&(r!=2)){
            catgLabel.setText("* vous devez choisir votre rôle");
            saisie = false;
        }

        if (!Validation.textalphabet(nom, nomLabel, "* le nom doit contenir que des lettres")) {
            saisie = false;
        }
       
        
        if (!Validation.textValidation(lieu, villeLabel, "* tout les champs doivent etre remplis")) {
            saisie = false;
        }
        
       
        
        return saisie;

        }
         
         
         //Valider l'ajout
         @FXML
    private void valider(ActionEvent event) throws SQLException, IOException  {
        PromenadeService a= new PromenadeService();
             copyFileUsingStream(new File(filePath), new File ("src/ImageUtile"+chooseFile.getText()));
        if ((this.controleSaisie2()) ) {

        
             String z;
            String r="";
            z = catg.getValue();
             if ("Promenade".equals(z)) {
            r = "Promenade";
        } else if ("Garde".equals(z)) {
            r = "Garde";
        } 
              LocalDate d = datedeb.getValue();
         Date dated = Date.from(d.atStartOfDay(ZoneId.systemDefault()).toInstant());
         
             LocalDate d2 = datef.getValue();
         Date datef2 = Date.from(d2.atStartOfDay(ZoneId.systemDefault()).toInstant());
       
             User user=Session.getLoggedInUser();
              String cin=user.getCin();
     
              try {
          Promenade u=new Promenade(nom.getText(),r,lieu.getText(),desc.getText(),dated,datef2,chooseFile.getText(),cin);
           
          
           a.ajouterPromenade(u);
         System.out.println("Promenade ajouté avec succés");
        } catch (SQLException ex) {
           Logger.getLogger(Zanimaux.class.getName()).log(Level.SEVERE, null, ex);
       }
         redirect();

    }}
    
    //Redirection vers la page promenade
    @FXML
    private void retourner(ActionEvent event) {
        
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("Promenade.fxml"));
            Parent root = (Parent) fxmlLoader.load();
            Stage secondStage = new Stage();
            secondStage.setScene(new Scene(root));
            Stage stage = (Stage) nom.getScene().getWindow();
            // do what you have to do
            stage.hide();
            secondStage.show();
        } catch (IOException ex) {
            Logger.getLogger(AjoutCabinetController.class.getName()).log(Level.SEVERE, null, ex);
        }

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
     
     //Redirection vers la page promenade aprés validation
    private void redirect() throws IOException {
        
        
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("Promenade.fxml"));
                Parent root = (Parent) fxmlLoader.load();
            Stage secondStage = new Stage();
            secondStage.setScene(new Scene(root));
            Stage stage = (Stage) nom.getScene().getWindow();
            // do what you have to do
            stage.hide();
            secondStage.show();
        

    }
    
    @FXML
    private void retourAcc(ActionEvent event) {
        
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("Quiz.fxml"));
            Parent root = (Parent) fxmlLoader.load();
            Stage secondStage = new Stage();
            secondStage.setScene(new Scene(root));
            Stage stage = (Stage) nom.getScene().getWindow();
            // do what you have to do
            stage.hide();
            secondStage.show();
        } catch (IOException ex) {
            Logger.getLogger(AjoutCabinetController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
    
    //Bouton rafraîchir page
    @FXML
    private void reinit(ActionEvent event) {
        
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("AjoutPromenade.fxml"));
            Parent root = (Parent) fxmlLoader.load();
            Stage secondStage = new Stage();
            secondStage.setScene(new Scene(root));
            Stage stage = (Stage) nom.getScene().getWindow();
            // do what you have to do
            stage.hide();
            secondStage.show();
        } catch (IOException ex) {
            Logger.getLogger(AjoutCabinetController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
    
    //deconnexion
    @FXML
    private void Deconnexion(ActionEvent event) {
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
         
         
         


