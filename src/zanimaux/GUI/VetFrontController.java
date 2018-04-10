/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package zanimaux.GUI;

import com.calendarfx.model.Calendar;
import com.calendarfx.model.CalendarSource;
import com.calendarfx.model.Entry;
import com.calendarfx.view.CalendarView;
import java.io.File;
import java.io.IOException;
import static java.lang.Thread.sleep;
import java.net.MalformedURLException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.Month;
import java.time.ZoneId;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
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
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import zanimaux.Service.Articleservice;
import zanimaux.Service.CabinetDao;
import zanimaux.Service.CalendarService;
import zanimaux.entities.Articles;
import zanimaux.entities.Cabinet;
import zanimaux.util.Session;

/**
 * FXML Controller class
 *
 * @author Mariam
 */
public class VetFrontController implements Initializable {
    @FXML
    private Button button;
    @FXML
    private Button vetb;
    @FXML
    private Button buttonRefuge;
    @FXML
    private Button evenement;
    @FXML
    private Button annonceBtn;
    @FXML
    private Button userName;
    @FXML
    private Label sommePanier;
    @FXML
    private Pane paneProfil;
    @FXML
    private Button btn11;
    @FXML
    private Button btn1;
    @FXML
    private AnchorPane anchorEvent;
      private ScrollPane sp = new ScrollPane();


    

   
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
         CabinetDao c = null;
       
            c = new CabinetDao();
       
        ResultSet r = c.AfficherTousCabinets();
        Cabinet c1=new Cabinet();
        //r= m.AfficherTousRefuge();
        userName.setText((Session.getLoggedInUser()).getUsername());
         ScrollPane sp = new ScrollPane();
    
          sp.setPrefSize(900, 650);
          

        VBox vb1 = new VBox();
        HBox hb1 =null;
        vb1.setPadding(new Insets(100, 30, 0, 30));
        vb1.setSpacing(100);
        int i=0;
        try {
            while(r.next()){
                i++;
                Label lbl = new Label();
//                c1.setImmatriculation(r.getString("immatriculation"));
                c1.setCin(r.getString("cin"));
//                c1.setNomRefuge(r.getString("nomRefuge"));
                c1.setEmailCabinet(r.getString("emailCabinet"));
                c1.setTelephoneCabinet(r.getInt("telephoneCabinet"));
                c1.setFaxCabinet(r.getInt("faxCabinet"));
                c1.setAdresseCabinet(r.getString("adresseCabinet"));
                c1.setCodePostaleCabinet(r.getInt("codePostaleCabinet"));
                c1.setVilleCabinet(r.getString("villeCabinet"));
                
                c1.setPhotovet(r.getString("photovet"));
                
                 Image image = new Image(new File(c1.getPhotovet()).toURI().toURL().toExternalForm(),150,120,false,false);
//                imageuser.setImage(image);
                ImageView im= new ImageView();
//                Image image= new Image("zanimaux/ImageUtile/"+c1.getPhotovet(),150,120,false,false) ;
                im.setImage(image);
                Text t1 =new Text(c1.getEmailCabinet());
                t1.setFont(Font.font("Comic Sans MS", 20) );
                t1.setStyle("Bold");
                Text t =new Text(c1.getAdresseCabinet()+" "+c1.getVilleCabinet()+", "+c1.getCodePostaleCabinet());
                t.setFont(Font.font("Comic Sans MS", 15) );
                Button b = new Button();
                Button bb = new Button();
                bb.setText("Voir Articles");
                
                
                b.setText("Consulter Disponibilité");
                b.setId(String.valueOf(c1.getCin()));
                 bb.setId(String.valueOf(c1.getCin()));
                bb.setOnAction(e->{
                    try {
                        VoirArticles(e);
                       
                    } catch (SQLException ex) {
                        Logger.getLogger(RefugeClientController.class.getName()).log(Level.SEVERE, null, ex);
                    }
                });
               
                b.setOnAction(j->{
                    try {
                        VoirDispo(j);
                       
                    } catch (SQLException ex) {
                        Logger.getLogger(RefugeClientController.class.getName()).log(Level.SEVERE, null, ex);
                    }
                });
                
                VBox vbcab = new VBox();
                HBox hbbouton = new HBox();
                hbbouton.setSpacing(5);
                hbbouton.getChildren().add(b);
                hbbouton.getChildren().add(bb);
                vbcab.setPadding(new Insets(-60,0,30,30));
                vbcab.setSpacing(50);
               vbcab.setStyle("-fx-background-color:#E3F9FE;-fx-background-radius:20px;");
                vbcab.setPrefSize(200, 150);
                vbcab.getChildren().add(im);
                vbcab.getChildren().add(t1);
                vbcab.getChildren().add(t);
                vbcab.getChildren().add(hbbouton);
                System.out.println(c1.getPhotovet());
                if(i%3!=1)
                {
                    hb1.getChildren().add(vbcab) ;
                }
                else
                {
                    hb1 = new HBox();
                    hb1.setPadding(new Insets(0,0,0,0));
                    hb1.setSpacing(50);
                    hb1.getChildren().add(vbcab) ;
                    vb1.getChildren().add(hb1);
                }
                
            } } catch (SQLException ex) {
            Logger.getLogger(RefugeClientController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (MalformedURLException ex) {
            Logger.getLogger(VetFrontController.class.getName()).log(Level.SEVERE, null, ex);
        }

           
        sp.setContent(vb1);
         anchorEvent.getChildren().setAll(sp);
          anchorEvent.getChildren().add(paneProfil);
    }    

    @FXML
    private void afficherMagasin(ActionEvent event) {
            try {
        Stage stage=(Stage) button.getScene().getWindow(); 
        stage.setTitle("NOS MAGSINS");
        Parent root = FXMLLoader.load(getClass().getResource("magasin.fxml"));
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
        } catch (IOException ex) {
           Logger.getLogger(accueilOumaimaController.class.getName()).log(Level.SEVERE, null, ex);
       }
    }

    @FXML
    private void AfficherRefugeAction(ActionEvent event) {
        
        try {
        Stage stage=(Stage) buttonRefuge.getScene().getWindow(); 
        stage.setTitle("NOS Refuges");
        Parent root = FXMLLoader.load(getClass().getResource("RefugeClient.fxml"));
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
        } catch (IOException ex) {
           Logger.getLogger(accueilOumaimaController.class.getName()).log(Level.SEVERE, null, ex);
       }
    }

    @FXML
    private void onClickEvenementAction(ActionEvent event) {
          try {
        Stage stage=(Stage) buttonRefuge.getScene().getWindow(); 
        stage.setTitle("Evénement");
        Parent root = FXMLLoader.load(getClass().getResource("afficheEvent.fxml"));
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
        } catch (IOException ex) {
           Logger.getLogger(accueilOumaimaController.class.getName()).log(Level.SEVERE, null, ex);
       }
    }

    @FXML
    private void affichePanierAction(ActionEvent event) {
        
             try {
        Stage stage=(Stage) buttonRefuge.getScene().getWindow(); 
        stage.setTitle("Mon Panier");
        Parent root = FXMLLoader.load(getClass().getResource("AffichePanier.fxml"));
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
        } catch (IOException ex) {
           Logger.getLogger(accueilOumaimaController.class.getName()).log(Level.SEVERE, null, ex);
       }
    }

    @FXML
    private void showPaneProfil(MouseEvent event) {
        paneProfil.setVisible(true);
    }

    @FXML
    private void connexionAction(ActionEvent event) {
            Session.setLoggedInUser(null);
        Parent root;
             try {
                 root = FXMLLoader.load(getClass().getResource("login.fxml"));
                 Stage myWindow = (Stage) btn11.getScene().getWindow();
                 Scene sc = new Scene(root);
                 myWindow.setScene(sc);
                 myWindow.setTitle("Login");
                 myWindow.show();
             } catch (IOException ex) {
                 Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
             }
    }

    @FXML
    private void hidePaneProfil(MouseEvent event) {
           paneProfil.setVisible(false);
    }
    @FXML
    private void goToAnn(ActionEvent event) {
         try {
        Stage stage=(Stage) annonceBtn.getScene().getWindow(); 
        stage.setTitle("Deposez votre annonce");
        Parent root = FXMLLoader.load(getClass().getResource("addAnnonce.fxml"));
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
        } catch (IOException ex) {
           Logger.getLogger(accueilOumaimaController.class.getName()).log(Level.SEVERE, null, ex);
       }
    } 
    void VoirArticles (ActionEvent e) throws SQLException{
      ResultSet r =null;
        
            Articleservice m= new Articleservice();
          
              String a =((Node)e.getSource()).getId();
            r= m.RechercherArticleByVet(a);
            int i=0;
            Articles m1=new Articles();
            ScrollPane sp = new ScrollPane();
            sp.setPrefSize(900, 650);
            sp.setMaxSize(Control.USE_COMPUTED_SIZE, Control.USE_COMPUTED_SIZE);
            sp.setMinSize(Control.USE_COMPUTED_SIZE, Control.USE_COMPUTED_SIZE);
            VBox vb2 = new VBox();
            HBox hb2 =null;
            vb2.setPadding(new Insets(100, 30, 30, 30));
            vb2.setSpacing(100);
            
             while(r.next())
            {
                //m1.(r.getInt("id"));
                m1.setDescription(r.getString("description"));
                m1.setTitre(r.getString("titre"));
              
                m1.setPiecejointe(r.getString("piecejointe"));
                
                ImageView im= new ImageView();
                 Image image=null;
          try {
              image = new Image(new File(m1.getPiecejointe()).toURI().toURL().toExternalForm(),150,120,false,false);
          } catch (MalformedURLException ex) {
              Logger.getLogger(VetFrontController.class.getName()).log(Level.SEVERE, null, ex);
          }
                im.setImage(image);
                  Text t1 =new Text(m1.getTitre());
                    System.out.println("article ");
                t1.setFont(Font.font("Comic Sans MS", 20));
                 t1.setStyle("Bold");
                  TextArea input=new TextArea(m1.getDescription());
                  input.setPrefHeight(65.0);
                  input.setPrefWidth(235.0);
              
               
               
          
                      VBox vbarticle = new VBox(); 
          vbarticle.setPadding(new Insets(-60,0,30,30));
          vbarticle.setSpacing(50);
          vbarticle.setStyle("-fx-background-color:#E3F9FE;-fx-background-radius:20px;");
         
          vbarticle.setPrefSize(200, 150);
          vbarticle.getChildren().add(im);
          vbarticle.getChildren().add(t1);
          vbarticle.getChildren().add(input);
          
          i++;
          
          if(i%3!=1)
          {
            hb2.getChildren().add( vbarticle) ;
          }
          else
          {
            hb2 = new HBox();
            hb2.setPadding(new Insets(0,0,0,0));
            hb2.setSpacing(50);
            hb2.getChildren().add(vbarticle) ;
            vb2.getChildren().add(hb2); 
            //vb2.getChildren().add(paneProfil);
           }
                 
      }
        sp.setContent(vb2);
         anchorEvent.getChildren().setAll(sp);
           System.out.println("fin ");
    
       
    }
    void VoirDispo(ActionEvent e) throws SQLException {
        String a =((Node)e.getSource()).getId();
          CalendarService service = new CalendarService();
          Calendar c = service.FindCalByVet(a);
           Entry <String> dentistAppointment = new Entry<>("Dentist");
     dentistAppointment.changeStartDate(LocalDate.now());
     dentistAppointment.changeEndDate(LocalDate.of(2018, Month.APRIL, 11));
//     c.addEntry(dentistAppointment);
     dentistAppointment.setCalendar(c);
          CalendarView cv = new CalendarView(); 
          CalendarSource cs = service.getCalendarSource();
          cs.getCalendars().add(c);
          cv.getCalendarSources().add(cs);
    
     
        
          
               cv.setRequestedTime(LocalTime.now());
               System.out.println("calendar ");

                Thread updateTimeThread = new Thread("Calendar: Update Time Thread") {
                        @Override
                        public void run() {
                                while (true) {
                                        Platform.runLater(() -> {
                                                cv.setToday(LocalDate.now());
                                                cv.setTime(LocalTime.now());
                                        });

                                        try {
                                                // update every 10 seconds
                                                sleep(10000);
                                        } catch (InterruptedException e) {
                                                e.printStackTrace();
                                        }

                                }
                        };
                };
                System.out.println("ok6");

                updateTimeThread.setPriority(Thread.MIN_PRIORITY);
                updateTimeThread.setDaemon(true);
                updateTimeThread.start();
                Stage stage=(Stage) button.getScene().getWindow(); 

                Scene scene = new Scene(cv);
                stage.setTitle("Calendar");
                stage.setScene(scene);
                stage.setWidth(1300);
                stage.setHeight(1000);
                stage.centerOnScreen();
                stage.show();
                System.out.println("ok7");
      
    }
     @FXML
    private void goTovet(ActionEvent event) {
        
          try {
        Stage stage=(Stage) button.getScene().getWindow(); 
        stage.setTitle("Vétérinaire");
        Parent root = FXMLLoader.load(getClass().getResource("VetFront.fxml"));
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
        } catch (IOException ex) {
           Logger.getLogger(accueilOumaimaController.class.getName()).log(Level.SEVERE, null, ex);
       }
    }

 
}
