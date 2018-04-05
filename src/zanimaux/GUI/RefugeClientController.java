/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package zanimaux.GUI;

import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.Instant;
import java.time.LocalDate;
import java.util.List;
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
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Control;
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
import javafx.scene.paint.Paint;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import zanimaux.Service.AnimalService;
import zanimaux.Service.CommentairesService;

import zanimaux.Service.RefugeService;
import zanimaux.entities.Animal;
import zanimaux.entities.Commentaires;

import zanimaux.entities.Refuge;
import zanimaux.util.Session;


/**
 * FXML Controller class
 *
 * @author Azza
 */
public class RefugeClientController implements Initializable {

    @FXML
    private Button buttonRF;
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
    private Button btnMagasin;
    @FXML
    private Button btnevenement;
    @FXML
    private TextArea input_commentaire;
    @FXML
    private Button btnCommenter;
   
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
                t1.setFont(Font.font("Comic Sans MS", 20) );
                t1.setStyle("Bold");
                Text t =new Text(m1.getAdresseRefuge()+" "+m1.getGouvernementRefuge()+", "+m1.getCodePostaleRefuge());
                t.setFont(Font.font("Comic Sans MS", 15) );
                Button b = new Button();
                b.setText("consulter refuge");
                b.setId(String.valueOf(m1.getImmatriculation()));
                b.setOnAction(e->{
                    try {
                        consulterRefuge(e);
                       
                    } catch (SQLException ex) {
                        Logger.getLogger(RefugeClientController.class.getName()).log(Level.SEVERE, null, ex);
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
    void ajouterCommentaire(ActionEvent e,String a,String b) throws SQLException{
        Commentaires co =new Commentaires();
        CommentairesService cs= new CommentairesService();
        co.setCin(Session.getLoggedInUser().getCin());
        co.setContenant(a);
        co.setDate(Date.valueOf(LocalDate.now()));
        co.setRefuge(b);
        cs.ajouterCommentaire(co);
    }
    void SupprimerCom(ActionEvent e,int i)throws SQLException{
        CommentairesService cs= new CommentairesService();
        cs.SupprimerCommentaire(i);
    }
    void ModifierCom(ActionEvent e,int id,String msg,String refuge)throws SQLException{
        CommentairesService cs= new CommentairesService();
        Commentaires co =new Commentaires();
        co.setId(id);
        co.setCin(Session.getLoggedInUser().getCin());
        co.setContenant(msg);
        co.setDate(Date.valueOf(LocalDate.now()));
        co.setRefuge(refuge);
        cs.ModifierComm(co);
    }

    void consulterRefuge(ActionEvent e) throws SQLException {
    
        ResultSet rs =null;
       String a =((Node)e.getSource()).getId();
         VBox comm = new VBox();
           comm.setSpacing(5);
           Label erreur= new Label(); 
           erreur.setVisible(false);
           Text tex=new Text();
           tex.setText("Vos avis..");
           tex.setFont(Font.font("Comic Sans MS", 18) );
           tex.setStyle("Bold");
            comm.getChildren().add(tex);
        TextArea inputCom=new TextArea();
            inputCom.setPromptText("Ajouter un commentaire..");
            inputCom.setPrefHeight(40.0);
            inputCom.setPrefWidth(235.0);
            inputCom.layoutXProperty().add(63.0);
            inputCom.layoutYProperty().add(525.0);
            Button btnCom=new Button();
            btnCom.setText("Commenter");
            btnCom.layoutXProperty().add(311.0);
            btnCom.layoutYProperty().add(535.0);
             btnCom.setOnAction(k->{
                    try {
                        if("".equals(inputCom.getText())){
                          
                          erreur.setText("** D'abbord vous devez ecrire un commentaire");
                          erreur.setTextFill(Paint.valueOf("#ff0000"));
                          erreur.setVisible(true);
                          
                        }else{
                         erreur.setVisible(false);   
                        ajouterCommentaire(k,inputCom.getText(),a);
                        inputCom.setText("");
                        
                        }
                    } catch (SQLException ex) {
                        Logger.getLogger(RefugeClientController.class.getName()).log(Level.SEVERE, null, ex);
                    }
                });
          
           Commentaires co =new Commentaires();
        CommentairesService cs= new CommentairesService();
        ResultSet rc = cs.RechercherComByImm(a);
           if (rc==null){
               
           }
           while(rc.next()){
               HBox h= new HBox();
               co.setId(rc.getInt("id"));
           co.setContenant(rc.getString("contenant"));
           co.setDate(rc.getDate("date"));
           co.setCin(rc.getString("cin"));
           String format = "dd/MM/yy H:mm";
          java.text.SimpleDateFormat formater = new java.text.SimpleDateFormat( format ); 
           Label lbcom= new Label();
           lbcom.setText(co.getContenant()+"     "+formater.format(co.getDate()));
           lbcom.setFont(Font.font("Comic Sans MS", 14) );
           lbcom.setStyle("-fx-background-color:#E3F9FE;");
           lbcom.setPrefHeight(30.0);
           Button btneditCom=new Button();
           btneditCom.setText("Modifier");
           
           Button btndeleteCom=new Button();
           btndeleteCom.setText("Supprimer");
          

           if( co.getCin().equals(Session.getLoggedInUser().getCin())){
           btneditCom.setVisible(true);
           btndeleteCom.setVisible(true);
           }else{btneditCom.setVisible(false);
            btndeleteCom.setVisible(false);}
           h.getChildren().add(lbcom);
           h.getChildren().add(btneditCom);
           h.getChildren().add(btndeleteCom);
           comm.getChildren().add(h);
            btndeleteCom.setOnAction(n->{
                    try {
                        SupprimerCom(n,co.getId());
                        lbcom.setText("");
                        btneditCom.setVisible(false);
                        btndeleteCom.setVisible(false);
                       
                    } catch (SQLException ex) {
                        Logger.getLogger(RefugeClientController.class.getName()).log(Level.SEVERE, null, ex);
                    }
                });
             btneditCom.setOnAction(c->{
                 
                 inputCom.setText(co.getContenant());
                 btneditCom.setOnAction(d->{
                     try {
                         ModifierCom(d,co.getId(),inputCom.getText(),a);
                         lbcom.setText(inputCom.getText()+"     "+formater.format(co.getDate()));
                         inputCom.setText("");
                         
                         
                     } catch (SQLException ex) {
                         Logger.getLogger(RefugeClientController.class.getName()).log(Level.SEVERE, null, ex);
                     }
                 });
                });
           }
           comm.getChildren().add(inputCom);
           comm.getChildren().add(erreur);
           comm.getChildren().add(btnCom);
           AnimalService m= new AnimalService();
          
            
            
            rs=  m.RechercherAnimalByImm(a) ;
            if (rs==null){
               
            }
            int i=0;
            Animal listForm=new Animal();
            ScrollPane sp = new ScrollPane();
            sp.setPrefSize(900, 650);
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
                Text t1 =new Text("Race: "+listForm.getRace());
                t1.setFont(Font.font("Comic Sans MS", 20));
                
                Text t =new Text("Type: "+listForm.getType());
                t.setFont(Font.font("Comic Sans MS", 15) );
                t1.setStyle("Bold");
                 Text t2 =new Text("Etat: "+listForm.getEtat());
                t2.setFont(Font.font("Comic Sans MS", 15) );
          
                      VBox vbProduit = new VBox(); 
          vbProduit.setPadding(new Insets(-60,0,30,30));
          vbProduit.setSpacing(10);
          vbProduit.setStyle("-fx-background-color:#E3F9FE;-fx-background-radius:20px;");
         
          vbProduit.setPrefSize(200, 150);
          vbProduit.getChildren().add(im);
          vbProduit.getChildren().add(t1);
          vbProduit.getChildren().add(t);
          vbProduit.getChildren().add(t2);
          

          i++;
          
          if(i%3!=1)
          {
            hb.getChildren().add(vbProduit) ;
          }
          else
          {
            hb = new HBox();
            hb.setPadding(new Insets(0,0,0,0));
            hb.setSpacing(10);
            hb.getChildren().add(vbProduit) ;
            vb.getChildren().add(hb); 
            vb.getChildren().add(comm);
           
           }
                 
      }
        sp.setContent(vb);
         anchorEvent.getChildren().setAll(sp);
           
    }

    @FXML
    private void AfficherLesMagasins(ActionEvent event) {
        try {
        Stage stage=(Stage) btnMagasin.getScene().getWindow(); 
        stage.setTitle("NOS MAGASINS");
        Parent root = FXMLLoader.load(getClass().getResource("magasin.fxml"));
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
        } catch (IOException ex) {
           Logger.getLogger(accueilOumaimaController.class.getName()).log(Level.SEVERE, null, ex);
       }
    }

    @FXML
    private void addEvent(ActionEvent event) {
         try {
        Stage stage=(Stage) btnevenement.getScene().getWindow(); 
        stage.setTitle("Ajouter Evenement");
        Parent root = FXMLLoader.load(getClass().getResource("addEvent.fxml"));
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
        } catch (IOException ex) {
           Logger.getLogger(accueilOumaimaController.class.getName()).log(Level.SEVERE, null, ex);
       }
    }
    private void showPane(MouseEvent event) {
         pane.setVisible(true);
    }
    private void hidePane(MouseEvent event) {
         pane.setVisible(false);

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
    private void profil(ActionEvent event) {
         try {
        Stage stage=(Stage) btn1.getScene().getWindow(); 
        stage.setTitle("Profil");
        Parent root = FXMLLoader.load(getClass().getResource("ProfilManager.fxml"));
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
        } catch (IOException ex) {
           Logger.getLogger(accueilOumaimaController.class.getName()).log(Level.SEVERE, null, ex);
       }
    }

   

}
