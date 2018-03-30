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
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import zanimaux.Service.ParcService;
import zanimaux.Service.RefugeService;
import zanimaux.entities.Parc;
import zanimaux.entities.Refuge;

/**
 * FXML Controller class
 *
 * @author BelhassenLimam
 */
public class ParcController implements Initializable {

    @FXML
    private Button buttonRF;
    @FXML
    private Button evenement;
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

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
          ParcService m = null;
        try {
            m = new ParcService();
        } catch (SQLException ex) {
            Logger.getLogger(RefugeClientController.class.getName()).log(Level.SEVERE, null, ex);
        }
        ResultSet r =m.AfficherTousParc();
        Parc m1=new Parc();
        r= m.AfficherTousParc();
        
         ScrollPane sp = new ScrollPane();
    
          sp.setPrefSize(900, 650);
//         sp.setMaxSize(Control.USE_COMPUTED_SIZE, Control.USE_COMPUTED_SIZE);
//         sp.setMinSize(Control.USE_COMPUTED_SIZE, Control.USE_COMPUTED_SIZE);
        VBox vb = new VBox();
        HBox hb =null;
        vb.setPadding(new Insets(100, 30, 0, 30));
        vb.setSpacing(100);
        int i=0;
        try {
            while(r.next()){
                i++;
                Label lbl = new Label();
                m1.setIdParc(r.getInt("idParc"));
                 m1.setNomParc(r.getString("nomParc"));
                 m1.setCategorieDressage(r.getString("CategorieDressage"));
                 m1.setAdresseParc(r.getString("adresseParc"));
                 m1.setVilleParc(r.getString("villeParc"));
                 m1.setCodePostaleParc(r.getInt("codePostaleParc"));
                 m1.setPhotoParc(r.getString("photoParc"));
                 m1.setCinDresseur(r.getString("cinDresseur")); 
                ImageView im= new ImageView();
                Image image= new Image("zanimaux/ImageUtile/"+m1.getPhotoParc(),150,120,false,false) ;
                im.setImage(image);
                Text t1 =new Text(m1.getNomParc());
                t1.setFont(Font.font("Verdana", 20) );
                Text t =new Text(m1.getAdresseParc()+" "+m1.getVilleParc()+", "+m1.getCodePostaleParc());
                t.setFont(Font.font("Verdana", 15) );
                
                VBox vbMagasin = new VBox();
                vbMagasin.setPadding(new Insets(-60,0,30,30));
                vbMagasin.setSpacing(50);
                vbMagasin.setStyle("-fx-background-color:#E3F9FE;-fx-background-radius:20px;");
                vbMagasin.setPrefSize(200, 150);
                vbMagasin.getChildren().add(im);
                vbMagasin.getChildren().add(t1);
                vbMagasin.getChildren().add(t);
                
                System.out.println(m1.getPhotoParc());
                if(i%3!=1)
                {
                    hb.getChildren().add(vbMagasin) ;
                }
                else
                {
                    hb = new HBox();
                    hb.setPadding(new Insets(0,0,0,0));
                    hb.setSpacing(50);
                    hb.getChildren().add(vbMagasin) ;
                    vb.getChildren().add(hb);
                }
                
            } } catch (SQLException ex) {
            Logger.getLogger(RefugeClientController.class.getName()).log(Level.SEVERE, null, ex);
        }

           
        sp.setContent(vb);
         anchorEvent.getChildren().setAll(sp);
        
    }}    
