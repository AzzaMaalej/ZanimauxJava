/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package zanimaux.GUI;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.scene.control.Button;
import javafx.scene.control.Control;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.Border;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;
import zanimaux.GUI.accueilOumaimaController;
import zanimaux.Service.MagasinService;
import zanimaux.Service.ProduitService;
import zanimaux.entities.Magasin;
import zanimaux.entities.Produit;
/**
 * FXML Controller class
 *
 * @author macbookpro
 */
public class accueilOumaimaController implements Initializable {

    @FXML
    private Button button;
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
    void handleButtonAction(ActionEvent event) throws SQLException {
        
        MagasinService m= new MagasinService();
        ResultSet r =m.rechercheMagasin();
        Magasin m1=new Magasin();
        r= m.rechercheMagasin();
        
         ScrollPane sp = new ScrollPane();
    
         sp.setPrefSize(500, 500);
         sp.setMaxSize(Control.USE_COMPUTED_SIZE, Control.USE_COMPUTED_SIZE);
         sp.setMinSize(Control.USE_COMPUTED_SIZE, Control.USE_COMPUTED_SIZE);
        VBox vb = new VBox();
        HBox hb =null;
        vb.setPadding(new Insets(100, 30, 0, 30));
        vb.setSpacing(100);
        int i=0;
      while(r.next())
      {
          
          m1.setIdMagasin(r.getInt("idMagasin"));
          m1.setNumRC(r.getString("numRC"));
          m1.setNomMagasin(r.getString("nomMagasin"));
          m1.setAdresseMagasin(r.getString("adresseMagasin"));
          m1.setCodePostaleMagasin(r.getInt("codePostaleMagasin"));
          m1.setPhotoMagasin(r.getString("photoMagasin"));
          m1.setVilleMagasin(r.getNString("villeMagasin"));
          m1.setCinProprietaireMagasin(r.getString("cinProprietaireMagasin"));
          m1.setBestSellerMagasin(r.getInt("bestSellerMagasin")); 
          ImageView im= new ImageView();
          Image image= new Image("zanimaux/ImageUtile/"+m1.getPhotoMagasin(),150,120,false,false) ;
          im.setImage(image);
          Text t1 =new Text(m1.getNomMagasin());
          t1.setFont(Font.font("Verdana", 20));
          Text t =new Text(m1.getAdresseMagasin()+" "+m1.getVilleMagasin()+", "+m1.getCodePostaleMagasin());
          t.setFont(Font.font("Verdana", 15) );
          Button b = new Button();
          b.setText("consulter magasin"); 
          b.setId(String.valueOf(m1.getIdMagasin()));
          b.setOnAction(e->{
              try {
                  consulterMagasin(e);
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
          i++;
          System.out.println(m1.getIdMagasin()+" "+m1.getPhotoMagasin());
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
                 
      }
        sp.setContent(vb);
        
        try {
        Stage stage=(Stage) button.getScene().getWindow(); 
        Parent root = FXMLLoader.load(getClass().getResource("magasin.fxml"));
        Scene scene = new Scene(root);
        AnchorPane a= (AnchorPane)scene.lookup("#anchorEvent");
        a.getChildren().setAll(sp);
         
      stage.setScene(scene);
      stage.setTitle("NOS MAGASINS");
      stage.show();
        } catch (IOException ex) {
           Logger.getLogger(accueilOumaimaController.class.getName()).log(Level.SEVERE, null, ex);
       }

    }
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
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
    
void consulterMagasin(ActionEvent e) throws SQLException {
    
        ResultSet r =null;
        try {
            ProduitService m= new ProduitService();
          
            Stage stage=(Stage) ((Node)e.getSource()).getScene().getWindow();
            int a =Integer.parseInt(((Node)e.getSource()).getId());
            r=m.rechercheProduits(a) ;
            Parent root = FXMLLoader.load(getClass().getResource("magasin.fxml"));
            Scene scene = new Scene(root);
            AnchorPane an= (AnchorPane)scene.lookup("#anchorEvent");
            int i=0;
            Produit m1=new Produit();
            ScrollPane sp = new ScrollPane();
            sp.setPrefSize(500, 500);
            sp.setMaxSize(Control.USE_COMPUTED_SIZE, Control.USE_COMPUTED_SIZE);
            sp.setMinSize(Control.USE_COMPUTED_SIZE, Control.USE_COMPUTED_SIZE);
            VBox vb = new VBox();
            HBox hb =null;
            vb.setPadding(new Insets(100, 30, 0, 30));
            vb.setSpacing(100);
            
             while(r.next())
            {
                m1.setIdProduit(r.getInt("idProduit"));
                m1.setDescription(r.getString("description"));
                m1.setLibelle(r.getString("libelle"));
                m1.setMarque(r.getString("marque"));
                m1.setPhotoProduit(r.getString("photoProduit"));
                m1.setPrix(r.getFloat("prix"));
                m1.setIdMagasin(r.getInt("idMagasin"));
                m1.setQuantite(r.getInt("quantite"));
                m1.setNbFoisVendu(r.getInt("nbFoisVendu"));
                ImageView im= new ImageView();
                //Image image= new Image("zanimaux/ImageUtile/"+m1.getPhotoProduit(),150,120,false,false) ;
                //im.setImage(image);
                Text t1 =new Text(m1.getLibelle());
                t1.setFont(Font.font("Verdana", 20));
                Text t =new Text(m1.getDescription());
                t.setFont(Font.font("Verdana", 15) );
                Button b = new Button();
                b.setText("Ajouter au panier"); 
          
                      VBox vbMagasin = new VBox(); 
          vbMagasin.setPadding(new Insets(-60,0,30,30));
          vbMagasin.setSpacing(50);
          vbMagasin.setStyle("-fx-background-color:#E3F9FE;-fx-background-radius:20px;");
          vbMagasin.setPrefSize(200, 150);
          vbMagasin.getChildren().add(im);
          vbMagasin.getChildren().add(t1);
          vbMagasin.getChildren().add(t);
          vbMagasin.getChildren().add(b);
          i++;
          
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
                 
      }
        sp.setContent(vb);
            
            an.getChildren().setAll(sp);
            stage.setScene(scene);
            stage.show();
        }catch (IOException ex) {
           Logger.getLogger(accueilOumaimaController.class.getName()).log(Level.SEVERE, null, ex);
       }
    }
    
}
