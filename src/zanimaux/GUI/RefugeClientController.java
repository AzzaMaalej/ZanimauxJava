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
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import zanimaux.Service.AnimalService;

import zanimaux.Service.RefugeService;
import zanimaux.entities.Animal;

import zanimaux.entities.Refuge;


/**
 * FXML Controller class
 *
 * @author Azza
 */
public class RefugeClientController implements Initializable {

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
         RefugeService m = null;
        try {
            m = new RefugeService();
        } catch (SQLException ex) {
            Logger.getLogger(RefugeClientController.class.getName()).log(Level.SEVERE, null, ex);
        }
        ResultSet r =m.AfficherTousRefuge();
        Refuge m1=new Refuge();
        r= m.AfficherTousRefuge();
        
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
                b.setText("consulter refuge");
                b.setId(String.valueOf(m1.getImmatriculation()));
                b.setOnAction(e->{
                    try {
                        consulterRefuge(e);
                    } catch (SQLException ex) {
                        Logger.getLogger(accueilOumaimaController.class.getName()).log(Level.SEVERE, null, ex);
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

    void consulterRefuge(ActionEvent e) throws SQLException {
    
        ResultSet rs =null;
        
           AnimalService m= new AnimalService();
          
            String a =((Node)e.getSource()).getId();
            rs=  m.RechercherAnimalByImm(a) ;
            int i=0;
            Animal listForm=new Animal();
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
                listForm.setRefuge(rs.getString("refuge"));
                 listForm.setIdAnimal(rs.getInt("idAnimal"));
                 listForm.setType(rs.getString("type"));
                 listForm.setEtat(rs.getString("etat"));
                 listForm.setNomAnimal(rs.getString("nomAnimal"));
                 listForm.setPhotoAnimal(rs.getString("photoAnimal"));
                 listForm.setAge(rs.getInt("age"));               
                 listForm.setRace(rs.getString("race"));
                ImageView im= new ImageView();
                Image image= new Image("zanimaux/ImageUtile/"+listForm.getPhotoAnimal(),150,120,false,false) ;
                im.setImage(image);
                Text t1 =new Text(listForm.getRace());
                t1.setFont(Font.font("Verdana", 20));
                Text t =new Text(listForm.getType());
                t.setFont(Font.font("Verdana", 15) );
                Button b = new Button();
                b.setText("Ajouter au panier"); 
          
                      VBox vbProduit = new VBox(); 
          vbProduit.setPadding(new Insets(-60,0,30,30));
          vbProduit.setSpacing(50);
          vbProduit.setStyle("-fx-background-color:#E3F9FE;-fx-background-radius:20px;");
         
          vbProduit.setPrefSize(200, 150);
          vbProduit.getChildren().add(im);
          vbProduit.getChildren().add(t1);
          vbProduit.getChildren().add(t);
          vbProduit.getChildren().add(b);
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

   

}
