/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package zanimaux.GUI;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.sql.Date;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;
import javax.imageio.ImageIO;
import zanimaux.Service.CabinetDao;
import zanimaux.Service.EvenementService;
import zanimaux.Service.Userservice;
import zanimaux.entities.Cabinet;
import zanimaux.entities.Evenement;
import zanimaux.entities.User;
import zanimaux.util.Session;

/**
 * FXML Controller class
 *
 * @author Maroua
 */
public class ModifierEventController implements Initializable {

    @FXML
    private AnchorPane AnchorPaneEvent;
    @FXML
    private TextField lieu;
    @FXML
    private DatePicker dateDebut;
    @FXML
    private DatePicker dateFin;
    @FXML
    private TextField type;
    @FXML
    private TextField titre;
    @FXML
    private TextArea description;
    @FXML
    private Button BtnChoixImage;
    @FXML
    private TextField nbPlace;
    @FXML
    private Button buttonEventCreate;
    @FXML
    private ImageView iv;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
       User user = Session.getLoggedInUser();
      
       // String z= user.getRoles();
           // String vet= "a:1:{i:0;s:16:\"ROLE_VETERINAIRE\";}";
           //java.util.Date dateDeb = new Date(e.getDateDebut().getTime());
           //java.util.Date dateFin = new Date(e.getDateFin().getTime());
          EvenementService se = null;
        try {
            se = new EvenementService();
        } catch (SQLException ex) {
            Logger.getLogger(ModifierEventController.class.getName()).log(Level.SEVERE, null, ex);
        }

        Evenement e = se.getByUser(user.getCin());
            // Evenement e= es.getByuser(user.getCin());
           
           
         if(e != null){
            lieu.setText(e.getLieu());
           // dateDebut.(e.getDateDebut().getDate());
            //dateFin.setTime(e.getDateFin().getTime());
            type.setText(e.getType());
            titre.setText(e.getTitre());
            description.setText(e.getDescription());
            nbPlace.setText(Integer.toString(e.getNbPlace()));
            BtnChoixImage.setText(e.getImageEvt());
           
           
        /* if(z.equals(vet)){
             lbinf.setVisible(true);
             anchorPanecab.setVisible(true);
             CabinetDao CDao = new CabinetDao();
             Cabinet cab= CDao.getByVet(user.getCin());
             telcab.setText(Integer.toString(cab.getTelephoneCabinet()));
             faxcab.setText(Integer.toString(cab.getFaxCabinet()));
             emailcab.setText(cab.getEmailCabinet());
             adressecab.setText(cab.getAdresseCabinet());
             villecab.setText(cab.getVilleCabinet());
             codepostcab.setText(Integer.toString(cab.getCodePostaleCabinet()));
             path.setText(cab.getPhotovet());
             
            */
            
           /* try {
//                final Pane root = new Pane();
                 Image image;
                image = new Image(new File(e.getImageEvt()).toURI().toURL().toExternalForm());
                iv.setImage(image);
//                final Circle clip = new Circle(300, 200, 200);
//                    imageuser.setClip(clip);
//                    
//                    root.getChildren().add(imageuser);
                    
            } catch (MalformedURLException ex) {
                Logger.getLogger(ModifierEventController.class.getName()).log(Level.SEVERE, null, ex);
            }*/
                    
                         
                    
                    
                
            }
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
    private void validerinfopesronnel(ActionEvent event) throws IOException, SQLException {
         Userservice a = new Userservice();
        User user = Session.getLoggedInUser();
        Evenement e = new Evenement ();
        EvenementService p = new EvenementService();
//        if (pwdId.getText().equals(confpwdId.getText()))
//          { 
              
//              System.out.println("mdp changé");
//          } 
//         
       
        
        e.setLieu(lieu.getText());
        e.setType(type.getText());
        e.setTitre(titre.getText());
        e.setDescription(description.getText());
        e.setNbPlace(Integer.parseInt(nbPlace.getText()));
        e.setImageEvt(BtnChoixImage.getText());
      
         
        
        System.out.println("modifié !");}
           
    
 
    @FXML
    private void uploadImage(ActionEvent event) {
        BtnChoixImage.setText(handle());

    }
        


    
}
