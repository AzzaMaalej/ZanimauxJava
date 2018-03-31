/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package zanimaux.GUI;

import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
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
import zanimaux.Service.ParcService;
import zanimaux.entities.Parc;





/**
 * FXML Controller class
 *
 * @author BelhassenLimam
 */
public class ParcController implements Initializable {

    @FXML
    private Button evenement;
    @FXML
    private Button userName;
    @FXML
    private AnchorPane anchorEvent;
    @FXML
    private Button ajou;
    @FXML
    private Button aff;
   
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
     ParcService m=null;
        try {
            m = new ParcService();
        } catch (SQLException ex) {
            Logger.getLogger(ParcController.class.getName()).log(Level.SEVERE, null, ex);
        }
        ResultSet r =m.AfficherParc();
        Parc m1=new Parc();
        r= m.AfficherParc();
        ScrollPane sp = new ScrollPane();
    
        sp.setPrefSize(900, 650);
//         sp.setMaxSize(Control.USE_COMPUTED_SIZE, Control.USE_COMPUTED_SIZE);
//         sp.setMinSize(Control.USE_COMPUTED_SIZE, Control.USE_COMPUTED_SIZE);
        VBox vb = new VBox();
        HBox hb =null;
        vb.setPadding(new Insets(100, 30, 0, 30));
        vb.setSpacing(100);
        int i=0;
         try{
      while(r.next())
      { 
          m1.setId(r.getString("id"));
          m1.setNomParc(r.getString("nomParc"));
          m1.setCategorieDressage(r.getString("CategorieDressage"));
          m1.setAdresseParc(r.getString("adresseParc"));
          m1.setVilleParc(r.getString("villeParc"));
          m1.setCodePostaleParc(r.getInt("codePostaleParc"));
          m1.setPhotoParc(r.getString("photoParc"));
          m1.setCinDresseur(r.getString("cinDresseur"));
          ImageView im= new ImageView();
          Image image= new Image("zanimaux/ImageUtile/Amazonie.jpg",150,120,false,false) ;
          im.setImage(image);
          Text t1 =new Text(m1.getNomParc());
          t1.setFont(Font.font("Verdana", 20));
          Text t2 = new Text(m1.getCategorieDressage());
          t2.setFont(Font.font("Verdana", 18));
          Text t =new Text(m1.getAdresseParc()+" "+m1.getVilleParc()+", "+m1.getCodePostaleParc());
          t.setFont(Font.font("Verdana", 15) );
          VBox vbParc = new VBox(); 
          vbParc.setPadding(new Insets(-60,0,30,30));
          vbParc.setSpacing(50);
          vbParc.setStyle("-fx-background-color:#E3F9FE;-fx-background-radius:20px;");
          vbParc.setPrefSize(200, 150);
          vbParc.getChildren().add(im);
          vbParc.getChildren().add(t1);
          vbParc.getChildren().add(t2);
          vbParc.getChildren().add(t);
          i++;
          System.out.println(m1.getId()+" "+m1.getPhotoParc());
          if(i%3!=1)
          {
            hb.getChildren().add(vbParc) ;
          }
          else
          {
            hb = new HBox();
            hb.setPadding(new Insets(0,0,0,0));
            hb.setSpacing(50);
            hb.getChildren().add(vbParc) ;
            vb.getChildren().add(hb); 
           }
                 
      }
      }catch( SQLException e){}
        sp.setContent(vb);
               
        
        anchorEvent.getChildren().setAll(sp);
        
    }  
    @FXML
    private void onClickEvenementAction(ActionEvent event) {
    }
     @FXML
    private void showPane(MouseEvent event) {
    }
    @FXML
    private void retourner(ActionEvent event) {
        
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("AjoutParc.fxml"));
            Parent root = (Parent) fxmlLoader.load();
            Stage secondStage = new Stage();
            secondStage.setScene(new Scene(root));
            Stage stage = (Stage) ajou.getScene().getWindow();
            // do what you have to do
            stage.hide();
            secondStage.show();
        } catch (IOException ex) {
            Logger.getLogger(AjoutCabinetController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

 }