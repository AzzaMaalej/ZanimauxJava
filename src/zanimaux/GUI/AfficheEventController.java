/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package zanimaux.GUI;

import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Control;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import zanimaux.Service.AnimalService;
import zanimaux.Service.EvenementService;
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

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        User usr = Session.getLoggedInUser();
        EvenementService e = null;
        try {
            e = new EvenementService();
        } catch (SQLException ex) {
            Logger.getLogger(AfficheEventController.class.getName()).log(Level.SEVERE, null, ex);
        }
    
        Evenement e1=new Evenement();
        ResultSet r= e.rechercheEvent();
        
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
                e1.setLieu(r.getString("lieu"));
                e1.setDateDebut(r.getDate("dateDebut"));
                e1.setDateFin(r.getDate("dateFin"));
                e1.setType(r.getString("type"));
                e1.setTitre(r.getString("titre"));
                e1.setDescription(r.getString("description"));
                e1.setNbPlace(r.getInt("nb_place"));
                e1.setImageEvt(r.getString("image_evt"));
               
                ImageView im= new ImageView();
                Image image= new Image("zanimaux/ImageUtile/chatLapin.jpeg",150,120,false,false); //("zanimaux/ImageUtile/"+e1.getImageEvt(),150,120,false,false) ;
                im.setImage(image);
                Text t1 =new Text(e1.getTitre());
                t1.setFont(Font.font("Verdana", 10) );
                Text t =new Text(e1.getLieu());
                t.setFont(Font.font("Verdana", 15) );
                
                Button b = new Button();
                Button modifier = new Button();
                Button supprimer = new Button();
                HBox BtnBox=new HBox(b,modifier,supprimer);
                BtnBox.setSpacing(50);
                b.setText("consulter evenement");
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
                 
                b.setId(String.valueOf(e1.getIdEvt()));
                b.setOnAction(s->{
                    try {
                        consulterEvent(s);
                    } catch (SQLException ex) {
                        Logger.getLogger(accueilOumaimaController.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }); VBox vbEvent = new VBox();
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
        // TODO
    void consulterEvent(ActionEvent e) throws SQLException {
    
        ResultSet rs =null;
        
           EvenementService m= new EvenementService();
          
            String a =((Node)e.getSource()).getId();
            rs=  m.rechercheEvent() ;
            int i=0;
            Evenement listeEvenement=new Evenement();
            ScrollPane sp = new ScrollPane();
            sp.setPrefSize(500, 500);
            sp.setMaxSize(Control.USE_COMPUTED_SIZE, Control.USE_COMPUTED_SIZE);
            sp.setMinSize(Control.USE_COMPUTED_SIZE, Control.USE_COMPUTED_SIZE);
            VBox vb = new VBox();
            HBox hb =null;
            vb.setPadding(new Insets(100, 30, 0, 30));
            vb.setSpacing(100);
            
             while(rs.next())
            {
                 listeEvenement.setLieu(rs.getString("lieu"));
                listeEvenement.setDateDebut(rs.getDate("dateDebut"));
                listeEvenement.setDateFin(rs.getDate("dateFin"));
                listeEvenement.setType(rs.getString("type"));
                listeEvenement.setTitre(rs.getString("titre"));
                listeEvenement.setDescription(rs.getString("description"));
                listeEvenement.setNbPlace(rs.getInt("nb_place"));
                listeEvenement.setImageEvt(rs.getString("image_evt"));
                ImageView im= new ImageView();
                Image image= new Image("C:\\Users\\Marwa\\Pictures"+listeEvenement.getImageEvt(),150,120,false,false) ;
                im.setImage(image);
                Text t1 =new Text(listeEvenement.getTitre());
               // t1.setFont(Font.font("Verdana", 12));
                Text t =new Text(listeEvenement.getType());
               // t.setFont(Font.font("Verdana", 12) );
                
          
                      VBox vbProduit = new VBox(); 
          vbProduit.setPadding(new Insets(-60,0,30,30));
          vbProduit.setSpacing(50);
          vbProduit.setStyle("-fx-background-color:#E3F9FE;-fx-background-radius:20px;");
         
          vbProduit.setPrefSize(200, 150);
          vbProduit.getChildren().add(im);
          vbProduit.getChildren().add(t1);
          vbProduit.getChildren().add(t);
          i++;
          
          if(i%3!=1)
          {
            hb.getChildren().add(vbProduit) ;
          }
          else
          {
            hb = new HBox();
            hb.setPadding(new Insets(0,0,0,0));
            hb.setSpacing(50);
            hb.getChildren().add(vbProduit) ;
            vb.getChildren().add(hb); 
           }
                 
      }
        sp.setContent(vb);
         anchorEvent.getChildren().setAll(sp);
           
    }
   

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
    
}
