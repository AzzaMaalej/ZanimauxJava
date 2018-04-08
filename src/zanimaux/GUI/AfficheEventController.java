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
import java.time.LocalDate;
import java.time.ZoneId;
<<<<<<< HEAD
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
=======
>>>>>>> a8f6f2541548020a1d5dc8a2987883cd2302c88d
import java.util.Date;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Control;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
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
import javax.imageio.ImageIO;
import zanimaux.Service.AnimalService;
import zanimaux.Service.EvenementService;
<<<<<<< HEAD
import zanimaux.Service.ParcService;
import zanimaux.Service.ParticipationService;
import zanimaux.Service.RefugeService;
import zanimaux.entities.Animal;
import zanimaux.entities.Evenement;
import zanimaux.entities.Parc;
import zanimaux.entities.Participation;
=======
import zanimaux.Service.RefugeService;
import zanimaux.entities.Animal;
import zanimaux.entities.Evenement;
>>>>>>> a8f6f2541548020a1d5dc8a2987883cd2302c88d
import zanimaux.entities.Refuge;
import zanimaux.entities.User;
import zanimaux.util.Session;

/**
 * FXML Controller class
 *
 * @author Maroua
 */
public class AfficheEventController implements Initializable {

    @FXML
    private Button button;
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
    private Button btnEvenement;
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
    private ImageView iv;
    @FXML
    private Button buttonModifierEvent;
<<<<<<< HEAD
    public String filePath;
    public int a;
    @FXML
    private AnchorPane anchorDetailsEvent;
    @FXML
    private ImageView imageDetails;
    @FXML
    private Label dateDebutEvent;
    @FXML
    private Label dateFinEvent;
    @FXML
    private Label lieuEvent;
    @FXML
    private Label typeEvent;
    @FXML
    private Label titreEvent;
    @FXML
    private Label descriptionEvent;
    @FXML
    private Label nbPlaceEvent;
    @FXML
    private Button btnParticiper;
    @FXML
    private Button btnAnnuler;
    public int nbp;
    public int nbpMax;
    @FXML
    private Label plein;
=======
>>>>>>> a8f6f2541548020a1d5dc8a2987883cd2302c88d

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            consulterEvent();
        } catch (SQLException ex) {
            Logger.getLogger(AfficheEventController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    } 
        // TODO
    void consulterEvent() throws SQLException {
    
        User usr = Session.getLoggedInUser();
        EvenementService es = null;
        try {
            es = new EvenementService();
        } catch (SQLException ex) {
            Logger.getLogger(AfficheEventController.class.getName()).log(Level.SEVERE, null, ex);
        }
    
        Evenement e1=new Evenement();
        ResultSet r= es.rechercheEvent();
        
         ScrollPane sp = new ScrollPane();
    
          sp.setPrefSize(900, 650);
        //sp.setMaxSize(Control.USE_PREF_SIZE, Control.USE_PREF_SIZE);
//         sp.setMinSize(Control.USE_COMPUTED_SIZE, Control.USE_COMPUTED_SIZE);
        VBox vb = new VBox();
        HBox hb =null;
        
        vb.setPadding(new Insets(100, 30, 0, 30));
        vb.setSpacing(70);
        int i=0;
        try {
            while(r.next()){
                i++;
                Label lbl = new Label();
                e1.setIdEvt(r.getInt("idEvt"));
                e1.setLieu(r.getString("lieu"));
                e1.setDateDebut(r.getDate("dateDebut"));
                e1.setDateFin(r.getDate("dateFin"));
                e1.setType(r.getString("type"));
                e1.setTitre(r.getString("titre"));
                e1.setDescription(r.getString("description"));
                e1.setNbPlace(r.getInt("nb_place"));
                e1.setImageEvt(r.getString("image_evt"));
               
                ImageView im= new ImageView();
                Image image= new Image("zanimaux/ImageUtile/"+e1.getImageEvt(),150,120,false,false); //("zanimaux/ImageUtile/"+e1.getImageEvt(),150,120,false,false) ;
                im.setImage(image);
                Text t1 =new Text(e1.getTitre());
                t1.setFont(Font.font("Verdana", 10) );
                Text t =new Text(e1.getLieu());
                t.setFont(Font.font("Verdana", 15) );
                
                Button consulter = new Button();
                Button modifier = new Button();
                             modifier.setId(String.valueOf(e1.getIdEvt()));
               
                modifier.setOnAction(x->{
                    try {
                        goToModif(x);
                    } catch (SQLException ex) {
                        Logger.getLogger(AfficheEventController.class.getName()).log(Level.SEVERE, null, ex);
                    }
});
                Button supprimer = new Button();
                supprimer.setId(String.valueOf(e1.getIdEvt()));
                 supprimer.setOnAction(x->{
                    try {
                        supprimerEvent(x);
                    } catch (SQLException ex) {
                        Logger.getLogger(AfficheEventController.class.getName()).log(Level.SEVERE, null, ex);
                    }
});
               
                HBox BtnBox=new HBox(consulter,modifier,supprimer);
                BtnBox.setSpacing(50);
                consulter.setText("consulter evenement");
                 modifier.setText("modifier");
                 modifier.setVisible(false);
                 supprimer.setText("Supprimer");
                 supprimer.setVisible(false);
                 System.out.println(usr.getCin());
                 System.out.println(r.getString("cin"));
                 if(usr.getCin().equals (r.getString("cin"))){
                     modifier.setVisible(true);
                      supprimer.setVisible(true);
                     
                 }
                 
                consulter.setId(String.valueOf(e1.getIdEvt()));
                consulter.setOnAction(s->{
                    try {
                        consulterDetailsEvent(s);
                    } catch (SQLException ex) {
                        Logger.getLogger(accueilOumaimaController.class.getName()).log(Level.SEVERE, null, ex);
                    }
                     
                });
   
                VBox vbEvent = new VBox();
                vbEvent.setPadding(new Insets(-60,0,30,30));
                vbEvent.setSpacing(50);
               
               // vbEvent.setStyle("-fx-background-color:#E3F9FE;-fx-background-radius:20px;");
               // vbEvent.setPrefSize(200, 150);
                vbEvent.getChildren().add(im);
                vbEvent.getChildren().add(t1);
                vbEvent.getChildren().add(t);
                vbEvent.getChildren().add(BtnBox);
                          

                System.out.println(e1.getImageEvt());
                    Button f=new Button();
                    f.setText("Facebook");
                      Button p=new Button();
                    p.setText("Twitter");
                      Button g=new Button();
                    g.setText("Gmail");
                  VBox  vp=new VBox(f,p,g);
                  vp.setSpacing(60);
                  
                    hb = new HBox();
                    hb.setPadding(new Insets(0,0,0,0));
                    hb.setPrefSize(500, 200);
                    hb.setMaxSize(Control.USE_PREF_SIZE, Control.USE_COMPUTED_SIZE);
                    hb.setMinSize(Control.USE_PREF_SIZE, Control.USE_COMPUTED_SIZE);
                   
                    hb.setSpacing(50);
                   
                   if (i%2!=0){
                       hb.setStyle("-fx-background-color:#0161DF;-fx-background-radius:20px;");
                       
                   }
                   else{
                      hb.setStyle("-fx-background-color:#4D62E6;-fx-background-radius:20px;"); 
                   }
                    hb.getChildren().add(vbEvent) ;
                    hb.getChildren().add(vp) ;
                    vb.getChildren().add(hb);
                
                       } } catch (SQLException ex) {
            Logger.getLogger(AfficheEventController.class.getName()).log(Level.SEVERE, null, ex);
        }
        

           
        sp.setContent(vb);
         anchorEvent.getChildren().setAll(sp);
        
           
    }
    
    void consulterDetailsEvent(ActionEvent e) throws SQLException{
            a=Integer.parseInt(((Node) e.getSource()).getId());
          EvenementService es = new EvenementService();
          Evenement e1 = new Evenement();
          e1=es.rechercheEvent(a);
          anchorEvent.getChildren().setAll(anchorDetailsEvent);
          anchorDetailsEvent.setVisible(true);
          lieuEvent.setText(e1.getLieu());
         dateDebutEvent.setText(e1.getDateDebut().toString());
         dateFinEvent.setText(e1.getDateFin().toString());
          typeEvent.setText(e1.getType());
          titreEvent.setText(e1.getTitre());
          descriptionEvent.setText(e1.getDescription());
          nbPlaceEvent.setText(Integer.toString(e1.getNbPlace()));
       Image image= new Image("zanimaux/ImageUtile/"+e1.getImageEvt(),150,120,false,false); //("zanimaux/ImageUtile/"+e1.getImageEvt(),150,120,false,false) ;
         imageDetails.setImage(image);
         
         nbp=e1.getNbParticipants();
         nbpMax=e1.getNbPlace();
         
         ParticipationService ps=new ParticipationService();
        boolean verif=ps.exist(a);
        System.out.println(verif);
         btnParticiper.setVisible(false);
         btnAnnuler.setVisible(false);
          plein.setVisible(false);
        
        if(nbp==nbpMax){
            plein.setVisible(true);
            if(verif==true){
                btnAnnuler.setVisible(true);
            }
            
        }
        
        else if(verif==true){
            btnAnnuler.setVisible(true);
            
        }
        else if( verif==false){
        btnParticiper.setVisible(true);

            
        }
        
    }
   
    @FXML
    void participer()throws SQLException{
                User usr = Session.getLoggedInUser();
                

        
        ParticipationService ps=new ParticipationService();
        EvenementService es=new EvenementService();
        Participation p =new Participation(a,usr.getCin());
      
       ps.ajouterParticipation(p);
       es.updateNbParticipants(a);
       consulterEvent();
  
    }
    
        
    @FXML
    void annuler()throws SQLException{
    User usr = Session.getLoggedInUser();
                       
        ParticipationService ps=new ParticipationService();
        EvenementService es=new EvenementService();
       
      ps.supprimerParticipation(a);
       es.updateNbParticipantsAfterAnnulation(a);
       consulterEvent();
  
    }
    
    
    
    void goToModif(ActionEvent e) throws SQLException {
          a=Integer.parseInt(((Node) e.getSource()).getId());
          EvenementService es = new EvenementService();
          Evenement e1 = new Evenement();
          e1=es.rechercheEvent(a);
          anchorEvent.getChildren().setAll(AnchorPaneEvent);
          AnchorPaneEvent.setVisible(true);
          lieu.setText(e1.getLieu());
         dateDebut.setValue(LocalDate.parse(e1.getDateDebut().toString()));
         dateFin.setValue(LocalDate.parse(e1.getDateFin().toString()));
         System.out.println(e1.getDateDebut().toString());
          type.setText(e1.getType());
          titre.setText(e1.getTitre());
          description.setText(e1.getDescription());
          nbPlace.setText(Integer.toString(e1.getNbPlace()));
          BtnChoixImage.setText(e1.getImageEvt());
          
          
    }
    
     public String handle() {
        FileChooser fileChooser = new FileChooser();

        //Set extension filter
        FileChooser.ExtensionFilter extFilterJPG = new FileChooser.ExtensionFilter("JPG files (*.jpg)", "*.JPG");
        FileChooser.ExtensionFilter extFilterPNG = new FileChooser.ExtensionFilter("PNG files (*.png)", "*.PNG");
        fileChooser.getExtensionFilters().addAll(extFilterJPG, extFilterPNG);

        //Show open file dialog
        File file = fileChooser.showOpenDialog(null);
        String filePath = file.getName();
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
    void modifier(ActionEvent e ) throws SQLException,IOException{
         
         
           
          System.out.println(a);
          EvenementService es = new EvenementService();
              LocalDate d = dateDebut.getValue();
         Date dated = Date.from(d.atStartOfDay(ZoneId.systemDefault()).toInstant());
         
             LocalDate d2 = dateFin.getValue();
         Date datef = Date.from(d2.atStartOfDay(ZoneId.systemDefault()).toInstant());
       
        Evenement e1=new Evenement(lieu.getText(),dated,datef,type.getText(),titre.getText(),description.getText(),Integer.parseInt(nbPlace.getText()),BtnChoixImage.getText());
             
         es.modifierEvenement(a, e1);
         consulterEvent();
               
    }

    void  supprimerEvent(ActionEvent e) throws SQLException{
         a=Integer.parseInt(((Node) e.getSource()).getId());
         
          EvenementService es = new EvenementService();
         es.supprimerEvenement(a);
         consulterEvent();
         
       
    }
    @FXML
    private void uploadImage(ActionEvent event) {
        BtnChoixImage.setText(handle());
    }
<<<<<<< HEAD

    @FXML
    private void handleButtonAction(ActionEvent event) {
    }

    @FXML
    private void onClickEvenementAction(ActionEvent event) {
    }

    @FXML
    private void showPane(MouseEvent event) {
    }

    @FXML
    private void connexionAction(ActionEvent event) {
    }
    
 
   
=======
    

    
    
    
>>>>>>> a8f6f2541548020a1d5dc8a2987883cd2302c88d
    
}
