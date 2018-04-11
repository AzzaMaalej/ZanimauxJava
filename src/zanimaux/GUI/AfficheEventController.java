/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package zanimaux.GUI;

import com.restfb.DefaultFacebookClient;
import com.restfb.FacebookClient;
import com.restfb.Parameter;
import com.restfb.types.FacebookType;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
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
import javafx.scene.control.Pagination;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
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
import zanimaux.Service.ParcService;
import zanimaux.Service.ParticipationService;
import zanimaux.Service.RefugeService;
import zanimaux.entities.Animal;
import zanimaux.entities.Evenement;
import zanimaux.entities.Parc;
import zanimaux.entities.Participation;
import zanimaux.Service.RefugeService;
import zanimaux.entities.Animal;
import zanimaux.entities.Evenement;
import zanimaux.Service.RefugeService;
import zanimaux.entities.Animal;
import zanimaux.entities.Evenement;
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
    int nbPages = 0;
    
    Pagination pagination = new Pagination();
    ArrayList<Evenement> listeEvt;
    @FXML
    private Button backBtn;
    @FXML
    private Button btnAnnonce;
    @FXML
    private Button btnMagasin;
    @FXML
    private Button btnVet;
    @FXML
    private Button btnPetSitter;
    @FXML
    private Button btnRefuges;
    @FXML
    private Button btnAccueil;
    @FXML
    private TextField rechercheBtn;
    @FXML
    private Button addEventBtn;
    @FXML
    private Button parc;

    /**
     * Initializes the controller class.
     */
   @Override
    public void initialize(URL url, ResourceBundle rb) {
       
       
       User usr= Session.getLoggedInUser();
        
        userName.setText(usr.getUsername());
         afficher();}
       
           User usr = Session.getLoggedInUser();
           
              
    // TODO

    //public ScrollPane consulterEvent(int pageIndex) {
      //  User usr = Session.getLoggedInUser();
         //ScrollPane sp = new ScrollPane();
      //rechercheBtn.setOnKeyPressed(new EventHandler<KeyEvent>() {
 
   /* @Override
    public void handle(KeyEvent event) {
        if(event.getCode().equals(KeyCode.ENTER)) {
             // do something
        EvenementService es=null;
        try {
            es = new EvenementService();
        } catch (SQLException ex) {
            Logger.getLogger(ParcController.class.getName()).log(Level.SEVERE, null, ex);
        }
       
        String cin=usr.getCin();
        Evenement e1=new Evenement();
        ResultSet r =es.RechercheEvent(rechercheBtn.getText());        
        sp.setPrefSize(900, 650);
        //sp.setMaxSize(Control.USE_PREF_SIZE, Control.USE_PREF_SIZE);
//         sp.setMinSize(Control.USE_COMPUTED_SIZE, Control.USE_COMPUTED_SIZE);
        VBox vb = new VBox();
        HBox hb = null;
        
        vb.setPadding(new Insets(100, 30, 0, 30));
        vb.setSpacing(70);
        int i = 0;
        
        for (int j = pageIndex * 2; j < listeEvt.size(); j++) {
            i++;
            if (j <= (pageIndex * 2 + (2 - 1))) {
                
               // Evenement e1 = new Evenement();
                e1 = listeEvt.get(j);
                ImageView im = new ImageView();
                Image image = new Image("zanimaux/ImageUtile/" + e1.getImageEvt(), 150, 120, false, false); //("zanimaux/ImageUtile/"+e1.getImageEvt(),150,120,false,false) ;
                im.setImage(image);
                Text t1 = new Text(e1.getTitre());
                t1.setFont(Font.font("Verdana", 10));
                Text t = new Text(e1.getLieu());
                t.setFont(Font.font("Verdana", 15));
                ImageView im1 = new ImageView();
                Image image2 = new Image("zanimaux/Image/supprimer.png", 150, 120, false, false); //("zanimaux/ImageUtile/"+e1.getImageEvt(),150,120,false,false) ;
                im1.setImage(image2);
                Button consulter = new Button();
                // consulter.setStyle("-fx-background-image:im1");
                consulter.setStyle("-fx-background-color:transparent;-fx-text-fill:white;-fx-border-width: 0px 0px 2px 0px;-fx-border-color:white;");
                Button modifier = new Button();
                modifier.setStyle("-fx-background-color:transparent;-fx-text-fill:white;-fx-border-width: 0px 0px 2px 0px;-fx-border-color:white;");
                modifier.setId(String.valueOf(e1.getIdEvt()));
                
                modifier.setOnAction(x -> {
                    try {
                        goToModif(x);
                    } catch (SQLException ex) {
                        Logger.getLogger(AfficheEventController.class.getName()).log(Level.SEVERE, null, ex);
                    }
                });
                Button supprimer = new Button();
                supprimer.setStyle("-fx-background-color:transparent;-fx-text-fill:white;-fx-border-width: 0px 0px 2px 0px;-fx-border-color:white;");
                supprimer.setId(String.valueOf(e1.getIdEvt()));
                supprimer.setOnAction(x -> {
                    try {
                        supprimerEvent(x);
                    } catch (SQLException ex) {
                        Logger.getLogger(AfficheEventController.class.getName()).log(Level.SEVERE, null, ex);
                    }
                });
                
                HBox BtnBox = new HBox(consulter, modifier, supprimer);
                BtnBox.setSpacing(50);
                consulter.setText("consulter evenement");
                modifier.setText("modifier");
                modifier.setVisible(false);
                supprimer.setText("Supprimer");
                supprimer.setVisible(false);
                System.out.println(usr.getCin());
                System.out.println(e1.getCinUser());
                
                if (usr.getCin().equals(e1.getCinUser())) {
                    modifier.setVisible(true);
                    supprimer.setVisible(true);
                    
                }
                
                consulter.setId(String.valueOf(e1.getIdEvt()));
                consulter.setOnAction(s -> {
                    try {
                        consulterDetailsEvent(s);
                    } catch (SQLException ex) {
                        Logger.getLogger(accueilOumaimaController.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    
                });
                
                VBox vbEvent = new VBox();
                vbEvent.setPadding(new Insets(-60, 0, 30, 30));
                vbEvent.setSpacing(50);

                // vbEvent.setStyle("-fx-background-color:#E3F9FE;-fx-background-radius:20px;");
                // vbEvent.setPrefSize(200, 150);
                vbEvent.getChildren().add(im);
                vbEvent.getChildren().add(t1);
                vbEvent.getChildren().add(t);
                vbEvent.getChildren().add(BtnBox);
                
                System.out.println(e1.getImageEvt());
                Button f = new Button();
                f.setStyle("\"-fx-background-color:transparent;-fx-text-fill:white;-fx-border-width: 0px 0px 2px 0px;-fx-border-color:white;");
                f.setText("Facebook");
                f.setOnAction(new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent event) {
                        
                String token = "EAACEdEose0cBABToIztl8BsETQpMKddF41aIcZBxEqjREXR0TyjVuOP4GAkrvAe878STh1ZC2EZBq72qMhUgUZBQtDC75fIJVhLumNG4RlSvbLDe8m6xECiizXciZCWgnwfZB2fiZBC3x5kfQ6yjZCr3aOPK7l3DtQTIF4TmZBAIU0xcDKtbmvg6TzoPeKCrAIlzpVjmorTtHj71SmmaH1TZB2YGGq5sZAPM4dV8E2AVOO74AE11j2GJ9fcbw4kDyZCbriAZD";
                FacebookClient fb = new DefaultFacebookClient(token);
                FacebookType r = fb.publish("me/feed", FacebookType.class, Parameter.with("message", " " ));
                            
                    }
                });
               ;
                VBox vp = new VBox(f);
                vp.setSpacing(60);
                
                hb = new HBox();
                hb.setPadding(new Insets(0, 0, 0, 0));
                hb.setPrefSize(500, 200);
                hb.setMaxSize(Control.USE_PREF_SIZE, Control.USE_COMPUTED_SIZE);
                hb.setMinSize(Control.USE_PREF_SIZE, Control.USE_COMPUTED_SIZE);
                
                hb.setSpacing(50);
                
                if (i % 2 != 0) {
                    hb.setStyle("-fx-background-color:#128FAD;-fx-background-radius:20px;");
                    
                } else {
                    hb.setStyle("-fx-background-color:#128FAD;-fx-background-radius:20px;");                    
                }
                hb.getChildren().add(vbEvent);
                hb.getChildren().add(vp);
                vb.getChildren().add(hb);
                
            }
        }
        
        sp.setContent(vb);
        // anchorEvent.getChildren().setAll(sp);
        
               
    }
        }
    });
    return sp;  
   }    */
     
       
    
    
    public ScrollPane consulterEventBase(int pageIndex) {
        System.out.println(pageIndex);
        
        User usr = Session.getLoggedInUser();
        /* EvenementService es = null;
        try {
            es = new EvenementService();
        } catch (SQLException ex) {
            Logger.getLogger(AfficheEventController.class.getName()).log(Level.SEVERE, null, ex);
        }
    
        Evenement e1=new Evenement();
        ResultSet r= es.rechercheEvent();*/
        
        ScrollPane sp = new ScrollPane();
       
        
        sp.setPrefSize(900, 650);
        //sp.setMaxSize(Control.USE_PREF_SIZE, Control.USE_PREF_SIZE);
//         sp.setMinSize(Control.USE_COMPUTED_SIZE, Control.USE_COMPUTED_SIZE);
        VBox vb = new VBox();
        HBox hb = null;
        
        vb.setPadding(new Insets(100, 30, 0, 30));
        vb.setSpacing(70);
        int i = 0;
        
        for (int j = pageIndex * 2; j < listeEvt.size(); j++) {
            i++;
            if (j <= (pageIndex * 2 + (2 - 1))) {
                
                Evenement e1 = new Evenement();
                e1 = listeEvt.get(j);
                ImageView im = new ImageView();
                Image image = new Image("zanimaux/ImageUtile/" + e1.getImageEvt(), 150, 120, false, false); //("zanimaux/ImageUtile/"+e1.getImageEvt(),150,120,false,false) ;
                im.setImage(image);
                Text t1 = new Text(e1.getTitre());
                t1.setFont(Font.font("Comic Sans MS", 13));
                t1.setStyle("-fx-text-fill:white;");
                Text t = new Text(e1.getLieu());
                t.setStyle("-fx-text-fill:white;");
                t.setFont(Font.font("Comic Sans MS", 15));
                ImageView im1 = new ImageView();
                Image image2 = new Image("zanimaux/Image/supprimer.png", 150, 120, false, false); //("zanimaux/ImageUtile/"+e1.getImageEvt(),150,120,false,false) ;
                im1.setImage(image2);
                Button consulter = new Button();
                consulter.setPrefSize(150, 20);
                consulter.setStyle("-fx-background-color:transparent;-fx-text-fill:white;-fx-border-width: 0px 0px 2px 0px;-fx-border-color:white;");
                Button modifier = new Button();
                modifier.setPrefSize(150, 20);
                modifier.setStyle("-fx-background-color:transparent;-fx-text-fill:white;-fx-border-width: 0px 0px 2px 0px;-fx-border-color:white;");
                modifier.setId(String.valueOf(e1.getIdEvt()));
                
                modifier.setOnAction(x -> {
                    try {
                        goToModif(x);
                    } catch (SQLException ex) {
                        Logger.getLogger(AfficheEventController.class.getName()).log(Level.SEVERE, null, ex);
                    }
                });
                Button supprimer = new Button();
                supprimer.setPrefSize(150, 20);
                supprimer.setStyle("-fx-background-color:transparent;-fx-text-fill:white;-fx-border-width: 0px 0px 2px 0px;-fx-border-color:white;");
                supprimer.setId(String.valueOf(e1.getIdEvt()));
                supprimer.setOnAction(x -> {
                    try {
                        supprimerEvent(x);
                    } catch (SQLException ex) {
                        Logger.getLogger(AfficheEventController.class.getName()).log(Level.SEVERE, null, ex);
                    }
                });
                
                HBox BtnBox = new HBox(consulter, modifier, supprimer);
                BtnBox.setSpacing(50);
                consulter.setText("consulter");
                modifier.setText("modifier");
                modifier.setVisible(false);
                supprimer.setText("Supprimer");
                supprimer.setVisible(false);
                System.out.println(usr.getCin());
                System.out.println(e1.getCinUser());
                
                if (usr.getCin().equals(e1.getCinUser())) {
                    modifier.setVisible(true);
                    supprimer.setVisible(true);
                    
                }
                
                consulter.setId(String.valueOf(e1.getIdEvt()));
                consulter.setOnAction(s -> {
                    try {
                        consulterDetailsEvent(s);
                    } catch (SQLException ex) {
                        Logger.getLogger(accueilOumaimaController.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    
                });
                
                VBox vbEvent = new VBox();
                vbEvent.setPadding(new Insets(-60, 0, 30, 30));
                vbEvent.setSpacing(50);

                // vbEvent.setStyle("-fx-background-color:#E3F9FE;-fx-background-radius:20px;");
                // vbEvent.setPrefSize(200, 150);
                vbEvent.getChildren().add(im);
                vbEvent.getChildren().add(t1);
                vbEvent.getChildren().add(t);
                vbEvent.getChildren().add(BtnBox);
                
                System.out.println(e1.getImageEvt());
                           
                Button f = new Button();
                f.setPrefWidth(400);
               f.setText("Facebook");
               f.setStyle("-fx-background-color:transparent;-fx-text-fill:white;-fx-border-width: 0px 0px 2px 0px;-fx-border-color:white;");
                f.setOnAction(new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent event) {
                        
               String token = "EAACEdEose0cBAKoqJJ0FDC7Y7jjHS3N0ImVgxzZA9GWS5KSczZCJMxU6N204WuQD3LJG0v8fMuZA0AWWhzHkDDGkU5awY2kkBrLYYUJWDZCp2R2C2SqBSKzuhHloVW7SDCRa24fBPtPXeDeLUZCpdZAflE9lTK86lzHiDlQKroiqroQVXZCiC09tQ1NvZAFFc8hvp8EZB0DtKQVDOE6ta1zVM67AZCp1N8iOnsz6yDFMheCDzHJvJbl8OX4kX7gDOfI4cZD";
               FacebookClient fb = new DefaultFacebookClient(token);
                FacebookType r = fb.publish("me/feed", FacebookType.class, Parameter.with("message", "Event"));
               // FacebookType r = fb.publish("me/feed", FacebookType.class, Parameter.with("message", e1.getImageEvt() + "Event" + e1.getLieu() + " aura lieu le " + e1.getDateDebut() + " jusqu'à " + e1.getDateFin() + " " + e1.getDescription()));
                            
                    }
                });
                
                VBox vp = new VBox(f);
                vp.setSpacing(60);
                
                hb = new HBox();
                hb.setPadding(new Insets(0, 0, 0, 0));
                hb.setPrefSize(500, 200);
                hb.setMaxSize(Control.USE_PREF_SIZE, Control.USE_COMPUTED_SIZE);
                hb.setMinSize(Control.USE_PREF_SIZE, Control.USE_COMPUTED_SIZE);
                
                hb.setSpacing(50);
                
                if (i % 2 != 0) {
                    hb.setStyle("-fx-background-color:#128FAD;-fx-background-radius:20px;");
                    
                } else {
                    hb.setStyle("-fx-background-color:#128FAD;-fx-background-radius:20px;");                    
                }
                hb.getChildren().add(vbEvent);
                hb.getChildren().add(vp);
                vb.getChildren().add(hb);
                
            }
        }
        
        sp.setContent(vb);
        // anchorEvent.getChildren().setAll(sp);
        
        return sp;        
    }

    
    void remplir() throws SQLException {
        EvenementService es = new EvenementService();
        ResultSet r = es.rechercheEvent();
        listeEvt = new ArrayList<Evenement>();
        while (r.next()) {            
            Evenement e1 = new Evenement();
            
            e1.setIdEvt(r.getInt("idEvt"));
            e1.setCinUser(r.getString("cin"));
            e1.setLieu(r.getString("lieu"));
            e1.setDateDebut(r.getDate("dateDebut"));
            e1.setDateFin(r.getDate("dateFin"));
            e1.setType(r.getString("type"));
            e1.setTitre(r.getString("titre"));
            e1.setDescription(r.getString("description"));
            e1.setNbPlace(r.getInt("nb_place"));
            e1.setImageEvt(r.getString("image_evt"));
            
            listeEvt.add(e1);
            
        }
        int nb = listeEvt.size();
        if ((nb % 2) == 0) {
            nbPages = nb / 2;            
        } else {
            nbPages = ((nb / 2) + 1);            
            
        }
    }
    
    void afficher() {
        try {
            remplir();
        } catch (SQLException ex) {
            Logger.getLogger(AfficheEventController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        pagination.setPageCount(nbPages);
        pagination.setPageFactory((Integer indexPage) -> consulterEventBase(indexPage));
        AnchorPane.setTopAnchor(pagination, 10.0);
        AnchorPane.setLeftAnchor(pagination, 10.0);
        AnchorPane.setRightAnchor(pagination, 10.0);
        AnchorPane.setBottomAnchor(pagination, 10.0);
        anchorEvent.getChildren().add(pagination);
        
    }
    
    void consulterDetailsEvent(ActionEvent e) throws SQLException {
        a = Integer.parseInt(((Node) e.getSource()).getId());
        EvenementService es = new EvenementService();
        Evenement e1 = new Evenement();
        e1 = es.rechercheEvent(a);
        anchorEvent.getChildren().setAll(anchorDetailsEvent);
        anchorDetailsEvent.setVisible(true);
        lieuEvent.setText(e1.getLieu());
        dateDebutEvent.setText(e1.getDateDebut().toString());
        dateFinEvent.setText(e1.getDateFin().toString());
        typeEvent.setText(e1.getType());
        titreEvent.setText(e1.getTitre());
        descriptionEvent.setText(e1.getDescription());
        nbPlaceEvent.setText(Integer.toString(e1.getNbPlace()));
        Image image = new Image("zanimaux/ImageUtile/" + e1.getImageEvt(), 150, 120, false, false); //("zanimaux/ImageUtile/"+e1.getImageEvt(),150,120,false,false) ;
        imageDetails.setImage(image);
        
        nbp = e1.getNbParticipants();
        nbpMax = e1.getNbPlace();
        
        ParticipationService ps = new ParticipationService();
        boolean verif = ps.exist(a);
        System.out.println(verif);
        btnParticiper.setVisible(false);
        btnAnnuler.setVisible(false);
        plein.setVisible(false);
        
        if (nbp == nbpMax) {
            plein.setVisible(true);
            if (verif == true) {
                btnAnnuler.setVisible(true);
            }
            
        } else if (verif == true) {
            btnAnnuler.setVisible(true);
            
        } else if (verif == false) {
            btnParticiper.setVisible(true);
            
        }
        
    }
    
    @FXML
    void participer() throws SQLException {
        User usr = Session.getLoggedInUser();
        
        ParticipationService ps = new ParticipationService();
        EvenementService es = new EvenementService();
        Participation p = new Participation(a, usr.getCin());
        
        ps.ajouterParticipation(p);
        es.updateNbParticipants(a);
        //consulterEvent();
        afficher();
        
    }
    
    @FXML
    void annuler() throws SQLException {
        User usr = Session.getLoggedInUser();
        
        ParticipationService ps = new ParticipationService();
        EvenementService es = new EvenementService();
        
        ps.supprimerParticipation(a);
        es.updateNbParticipantsAfterAnnulation(a);
        //consulterEvent();
        afficher();
        
    }
    
    void goToModif(ActionEvent e) throws SQLException {
        a = Integer.parseInt(((Node) e.getSource()).getId());
        EvenementService es = new EvenementService();
        Evenement e1 = new Evenement();
        e1 = es.rechercheEvent(a);
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
    void modifier(ActionEvent e) throws SQLException, IOException {
        
        User usr = Session.getLoggedInUser();
        
        System.out.println(a);
        EvenementService es = new EvenementService();
        LocalDate d = dateDebut.getValue();
        Date dated = Date.from(d.atStartOfDay(ZoneId.systemDefault()).toInstant());
        
        LocalDate d2 = dateFin.getValue();
        Date datef = Date.from(d2.atStartOfDay(ZoneId.systemDefault()).toInstant());
        
        Evenement e1 = new Evenement(usr.getCin(), lieu.getText(), dated, datef, type.getText(), titre.getText(), description.getText(), Integer.parseInt(nbPlace.getText()), BtnChoixImage.getText());
        
        es.modifierEvenement(a, e1);
        //consulterEvent();
        afficher();
        
    }
    
    void supprimerEvent(ActionEvent e) throws SQLException {
        a = Integer.parseInt(((Node) e.getSource()).getId());
        
        EvenementService es = new EvenementService();
        es.supprimerEvenement(a);
        //consulterEvent();
        afficher();
        
    }

    @FXML
    private void uploadImage(ActionEvent event) {
        BtnChoixImage.setText(handle());
    }
    
    @FXML
    private void retour(ActionEvent event) {
        try {
            Stage stage = (Stage) backBtn.getScene().getWindow();            
            stage.setTitle("affiche");
            Parent root = FXMLLoader.load(getClass().getResource("afficheEvent.fxml"));
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        } catch (IOException ex) {
            Logger.getLogger(AfficheEventController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    @FXML
    private void goToAnnonce(ActionEvent event) {
        
        try {
            Stage stage = (Stage) btnAnnonce.getScene().getWindow();            
            stage.setTitle("annonce");
            Parent root = FXMLLoader.load(getClass().getResource("afficheAnnonce.fxml"));
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        } catch (IOException ex) {
            Logger.getLogger(AddAnnonceController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    @FXML
    private void showPane(MouseEvent event) {
    }
    
    @FXML
    private void connexionAction(ActionEvent event) {
    }
    
    @FXML
    private void goToMagasin(ActionEvent event) {
        try {
            Stage stage = (Stage) btnMagasin.getScene().getWindow();            
            stage.setTitle("magasin");
            Parent root = FXMLLoader.load(getClass().getResource("magasin.fxml"));
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        } catch (IOException ex) {
            Logger.getLogger(AddAnnonceController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
    @FXML
    private void goToVet(ActionEvent event) {
        try {
            Stage stage = (Stage) btnVet.getScene().getWindow();            
            stage.setTitle("vet");
            Parent root = FXMLLoader.load(getClass().getResource("VetFront.fxml"));
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        } catch (IOException ex) {
            Logger.getLogger(AddAnnonceController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    @FXML
    private void goToPetSitter(ActionEvent event) {
        try {
            Stage stage = (Stage) btnPetSitter.getScene().getWindow();            
            stage.setTitle("petSitter");
            Parent root = FXMLLoader.load(getClass().getResource("ListePromenade.fxml"));
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        } catch (IOException ex) {
            Logger.getLogger(AddAnnonceController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    @FXML
    private void goToRefuge(ActionEvent event) {
        try {
            Stage stage = (Stage) btnRefuges.getScene().getWindow();            
            stage.setTitle("refuge");
            Parent root = FXMLLoader.load(getClass().getResource("RefugeClient.fxml"));
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        } catch (IOException ex) {
            Logger.getLogger(AddAnnonceController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
    @FXML
    private void goToEvent(ActionEvent event) {
        try {
            Stage stage = (Stage) btnEvenement.getScene().getWindow();            
            stage.setTitle("evenement");
            Parent root = FXMLLoader.load(getClass().getResource("afficheEvent.fxml"));
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        } catch (IOException ex) {
            Logger.getLogger(AddAnnonceController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    @FXML
    private void goToAccueil(ActionEvent event) {
        try {
            Stage stage = (Stage) btnAccueil.getScene().getWindow();            
            stage.setTitle("accueil");
            Parent root = FXMLLoader.load(getClass().getResource("Quiz.fxml"));
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        } catch (IOException ex) {
            Logger.getLogger(AddAnnonceController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void rechercher(KeyEvent event) {
         if(event.getCode().equals(KeyCode.ENTER)) {
             // do something
        EvenementService es =null;
        try {
            es = new EvenementService();
        } catch (SQLException ex) {
            Logger.getLogger(ParcController.class.getName()).log(Level.SEVERE, null, ex);
        }
        User user=Session.getLoggedInUser();
        String cin=user.getCin();
        Evenement m1=new Evenement();
        ResultSet r =es.RechercheEvent(rechercheBtn.getText());
        

    }


    }

    @FXML
    private void goToAddEvent(ActionEvent event) {
         try {
            Stage stage = (Stage) addEventBtn.getScene().getWindow();            
            stage.setTitle("accueil");
            Parent root = FXMLLoader.load(getClass().getResource("addEvent.fxml"));
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        } catch (IOException ex) {
            Logger.getLogger(AddAnnonceController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void AfficherParc(ActionEvent event) {
        try {
        Stage stage=(Stage) parc.getScene().getWindow(); 
        stage.setTitle("Annonce");
        Parent root = FXMLLoader.load(getClass().getResource("ListeParc.fxml"));
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
        } catch (IOException ex) {
           Logger.getLogger(AddAnnonceController.class.getName()).log(Level.SEVERE, null, ex);
       }
    }

    
    
    
}
