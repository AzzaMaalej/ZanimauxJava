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
import zanimaux.Service.RefugeService;
import zanimaux.entities.Refuge;
import zanimaux.entities.User;
import zanimaux.util.Session;

/**
 * FXML Controller class
 *
 * @author Azza
 */
public class GestionRefugesController implements Initializable {

    @FXML
    private Button GestRef;
    @FXML
    private Button GestAnim;
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
         RefugeService m = null;
        try {
            
            m = new RefugeService();
        } catch (SQLException ex) {
            Logger.getLogger(RefugeClientController.class.getName()).log(Level.SEVERE, null, ex);
        }
        User user=Session.getLoggedInUser();
        String cin=user.getCin();
        ResultSet r ;
        Refuge m1=new Refuge();
        r= m.AfficherRefugeByCin(cin);
        
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
                
                m1.setImmatriculation(r.getString("immatriculation"));
                m1.setCin(r.getString("cin"));
                m1.setNomRefuge(r.getString("nomRefuge"));
                m1.setEmailRefuge(r.getString("emailRefuge"));
                m1.setTelephoneRefuge(r.getInt("telephoneRefuge"));
                m1.setFaxRefuge(r.getInt("faxRefuge"));
                m1.setAdresseRefuge(r.getString("adresseRefuge"));
                m1.setCodePostaleRefuge(r.getInt("codePostaleRefuge"));
                m1.setGouvernementRefuge(r.getString("gouvernementRefuge"));
                m1.setPhotoRefuge(r.getString("photorefuge"));
                m1.setChat(r.getString("chat"));
                m1.setChien(r.getString("chien"));
                m1.setRongeur(r.getString("rongeur"));
                m1.setAutre(r.getString("autre"));
                ImageView im= new ImageView();
                Image image= new Image("zanimaux/ImageUtile/"+m1.getPhotoRefuge(),150,120,false,false) ;
                im.setImage(image);
                Text t1 =new Text(m1.getNomRefuge());
                t1.setFont(Font.font("Verdana", 20) );
                Text t =new Text(m1.getAdresseRefuge()+" "+m1.getGouvernementRefuge()+", "+m1.getCodePostaleRefuge());
                t.setFont(Font.font("Verdana", 15) );
                Button b = new Button();
                b.setText("Effacer refuge");
                b.setId(String.valueOf(m1.getImmatriculation()));
                b.setOnAction(e->{
                    try {
                        EffacerRefuge(e);
                    } catch (SQLException ex) {
                        Logger.getLogger(GestionRefugesController.class.getName()).log(Level.SEVERE, null, ex);
                    }
                });
                VBox vbMagasin = new VBox();
                vbMagasin.setPadding(new Insets(-60,0,30,30));
                vbMagasin.setSpacing(50);
                vbMagasin.setStyle("-fx-background-color:#E3F9FE;-fx-background-radius:20px;");
                vbMagasin.setPrefSize(200, 150);
                vbMagasin.getChildren().add(im);
                vbMagasin.getChildren().add(t1);
                vbMagasin.getChildren().add(t);
                vbMagasin.getChildren().add(b);
                System.out.println(m1.getPhotoRefuge());
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
        
    }    
   public void EffacerRefuge(ActionEvent e) throws SQLException {
       
   }
}
