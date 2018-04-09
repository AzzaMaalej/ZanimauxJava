/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package zanimaux.GUI;


import java.awt.image.BufferedImage;
import java.io.File;
import javafx.scene.paint.Color;
import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
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
import javafx.scene.control.ButtonType;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Control;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.Tooltip;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javax.imageio.ImageIO;
import zanimaux.Service.PromenadeService;
import zanimaux.entities.Promenade;
import zanimaux.entities.User;
import zanimaux.util.Session;





/**
 * FXML Controller class
 *
 * @author BelhassenLimam
 */
public class PromenadeController implements Initializable {

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
   public String filePath;
    @FXML
    private Button Acc;
    @FXML
    private Button logOut;
    /**
     * Initializes the controller class.
     */
    @Override

    public void initialize(URL url, ResourceBundle rb)  {
        User u= Session.getLoggedInUser();
        userName.setText(u.getUsername());
        
        PromenadeService m=null;
        try {
            m = new PromenadeService();
        } catch (SQLException ex) {
            Logger.getLogger(PromenadeController.class.getName()).log(Level.SEVERE, null, ex);
        }
        User user=Session.getLoggedInUser();
            String cin=user.getCin();
        ResultSet r =m.AfficherPromenadeByCin(cin);
        Promenade m1=new Promenade();
        r= m.AfficherPromenadeByCin(cin);
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
          m1.setNomPromenade(r.getString("nomPromenade"));
          m1.setTypePromenade(r.getString("typePromenade"));
          m1.setLieuPromenade(r.getString("lieuPromenade"));
          m1.setDescriptionPromenade(r.getString("descriptionPromenade"));
          m1.setDatedebutPromenade(r.getDate("datedebutPromenade"));
          m1.setDatefinPromenade(r.getDate("datefinPromenade"));
          m1.setPhotoPromenade(r.getString("photoPromenade"));
          m1.setCinPetsitter(r.getString("cinPetsitter"));
          ImageView im= new ImageView();
          Image image= new Image("zanimaux/ImageUtile/"+m1.getPhotoPromenade(),150,120,false,false) ;
          im.setImage(image);
          Text t1 =new Text(m1.getNomPromenade());
          t1.setFont(Font.font("Verdana", 16));
          Text t3 = new Text("Type : ");
          t3.setFont(Font.font("Verdana", 15));
          t3.setFill(Color.web("#0076a3"));
          Text t2 = new Text(m1.getTypePromenade());
          t2.setFont(Font.font("Verdana", 14));
          Text t4 = new Text("Lieu : ");
          t4.setFont(Font.font("Verdana", 15));
          t4.setFill(Color.web("#0076a3"));
          Text t =new Text(m1.getLieuPromenade());
          t.setFont(Font.font("Verdana", 14) );
          Text t5 = new Text("Durée : ");
          t5.setFont(Font.font("Verdana", 15));
          t5.setFill(Color.web("#0076a3"));
          Text t6 =new Text("Du"+m1.getDatedebutPromenade()+"Au"+m1.getDatefinPromenade());
          t6.setFont(Font.font("Verdana", 14) );
          
          Image imageDecline = new Image("zanimaux/ImageUtile/delete.png",26,26,false,false);
          Image imageModif = new Image("zanimaux/ImageUtile/pencil.png",26,26,false,false);
            Button b = new Button();
            b.setBackground(Background.EMPTY);
            b.setGraphic(new ImageView(imageDecline));
            b.setId(String.valueOf(m1.getId()));
            b.setOnAction(e->{
                    try {
                        onClickEvenementAction(e);
                    } catch (SQLException ex) {
                        Logger.getLogger(PromenadeController.class.getName()).log(Level.SEVERE, null, ex);
                    }
                });
             Button b1 = new Button();
             b1.setBackground(Background.EMPTY);
            b1.setGraphic(new ImageView(imageModif));
            b1.setId(String.valueOf(m1.getId()));
            b1.setOnAction(e->{
                    try {
                        editParc(e);
                    } catch (SQLException ex) {
                        Logger.getLogger(PromenadeController.class.getName()).log(Level.SEVERE, null, ex);
                    }
                });
         
         HBox h = new HBox(b, b1);
         h.setSpacing(50);
          VBox vbParc = new VBox(); 
          vbParc.setPadding(new Insets(-60,0,30,30));
          vbParc.setSpacing(20);
          vbParc.setStyle("-fx-background-color:#E3F9FE;-fx-background-radius:20px;");
          vbParc.setPrefSize(200, 150);
          vbParc.getChildren().add(im);
          vbParc.getChildren().add(t1);
          vbParc.getChildren().add(t3);
          vbParc.getChildren().add(t2);
          vbParc.getChildren().add(t4);
          vbParc.getChildren().add(t);
          vbParc.getChildren().add(t5);
          vbParc.getChildren().add(t6);
          vbParc.getChildren().add(h);
         

          
          i++;
          System.out.println(m1.getId()+" "+m1.getPhotoPromenade());
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
    //Supprimer promenade
    @FXML
    private void onClickEvenementAction(ActionEvent event) throws SQLException {
       
        PromenadeService m= new PromenadeService();
          
            String a =((Node)event.getSource()).getId();
            javafx.scene.control.Alert alert = new  javafx.scene.control.Alert(javafx.scene.control.Alert.AlertType.CONFIRMATION, "", ButtonType.YES, ButtonType.NO);
        alert.setHeaderText("voulez-vous vraiment supprimer cet événement ?");
        alert.showAndWait();

     if (alert.getResult() == ButtonType.YES) {
            m.supprimerPromenade(a);
         resetPageData();
     }
    }
    @FXML
    private void retourAcc(ActionEvent event) {
        
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("Quiz.fxml"));
            Parent root = (Parent) fxmlLoader.load();
            Stage secondStage = new Stage();
            secondStage.setScene(new Scene(root));
            Stage stage = (Stage) Acc.getScene().getWindow();
            // do what you have to do
            stage.hide();
            secondStage.show();
        } catch (IOException ex) {
            Logger.getLogger(AjoutCabinetController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
   
     @FXML
    private void showPane(MouseEvent event) {
    }
    @FXML
    private void retourner(ActionEvent event) {
        
        try {
        Stage stage=(Stage) ajou.getScene().getWindow(); 
        stage.setTitle("Ajouter Promenade");
        Parent root = FXMLLoader.load(getClass().getResource("AjoutPromenade.fxml"));
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
        } catch (IOException ex) {
           Logger.getLogger(accueilOumaimaController.class.getName()).log(Level.SEVERE, null, ex);
       }


       
        
    }
    
   public void resetPageData() throws SQLException
    {
        PromenadeService m=null;
        try {
            m = new PromenadeService();
        } catch (SQLException ex) {
            Logger.getLogger(PromenadeController.class.getName()).log(Level.SEVERE, null, ex);
        }
        User user=Session.getLoggedInUser();
            String cin=user.getCin();
        ResultSet r =m.AfficherPromenadeByCin(cin);
        Promenade m1=new Promenade();
        r= m.AfficherPromenadeByCin(cin);
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
          m1.setNomPromenade(r.getString("nomPromenade"));
          m1.setTypePromenade(r.getString("typePromenade"));
          m1.setLieuPromenade(r.getString("lieuPromenade"));
          m1.setDescriptionPromenade(r.getString("descriptionPromenade"));
          m1.setDatedebutPromenade(r.getDate("datedebutPromenade"));
          m1.setDatefinPromenade(r.getDate("datefinPromenade"));
          m1.setPhotoPromenade(r.getString("photoPromenade"));
          m1.setCinPetsitter(r.getString("cinPetsitter"));
          ImageView im= new ImageView();
          Image image= new Image("zanimaux/ImageUtile/"+m1.getPhotoPromenade(),150,120,false,false) ;
          im.setImage(image);
          Text t1 =new Text(m1.getNomPromenade());
          t1.setFont(Font.font("Verdana", 16));
          Text t3 = new Text("Type : ");
          t3.setFont(Font.font("Verdana", 15));
          t3.setFill(Color.web("#0076a3"));
          Text t2 = new Text(m1.getTypePromenade());
          t2.setFont(Font.font("Verdana", 14));
          Text t4 = new Text("Lieu : ");
          t4.setFont(Font.font("Verdana", 15));
          t4.setFill(Color.web("#0076a3"));
          Text t =new Text(m1.getLieuPromenade());
          t.setFont(Font.font("Verdana", 14) );
          Text t5 = new Text("Durée : ");
          t5.setFont(Font.font("Verdana", 15));
          t5.setFill(Color.web("#0076a3"));
          Text t6 =new Text("Du"+m1.getDatedebutPromenade()+"Au"+m1.getDatefinPromenade());
          t6.setFont(Font.font("Verdana", 14) );
          
          Image imageDecline = new Image("zanimaux/ImageUtile/delete.png",26,26,false,false);
          Image imageModif = new Image("zanimaux/ImageUtile/pencil.png",26,26,false,false);
            Button b = new Button();
            b.setBackground(Background.EMPTY);
            b.setGraphic(new ImageView(imageDecline));
            b.setId(String.valueOf(m1.getId()));
            b.setOnAction(e->{
                    try {
                        onClickEvenementAction(e);
                    } catch (SQLException ex) {
                        Logger.getLogger(PromenadeController.class.getName()).log(Level.SEVERE, null, ex);
                    }
                });
             Button b1 = new Button();
             b1.setBackground(Background.EMPTY);
            b1.setGraphic(new ImageView(imageModif));
            b1.setId(String.valueOf(m1.getId()));
            b1.setOnAction(e->{
                    try {
                        editParc(e);
                    } catch (SQLException ex) {
                        Logger.getLogger(PromenadeController.class.getName()).log(Level.SEVERE, null, ex);
                    }
                });
         
         HBox h = new HBox(b, b1);
         h.setSpacing(50);
          VBox vbParc = new VBox(); 
          vbParc.setPadding(new Insets(-60,0,30,30));
          vbParc.setSpacing(20);
          vbParc.setStyle("-fx-background-color:#E3F9FE;-fx-background-radius:20px;");
          vbParc.setPrefSize(200, 150);
          vbParc.getChildren().add(im);
          vbParc.getChildren().add(t1);
          vbParc.getChildren().add(t3);
          vbParc.getChildren().add(t2);
          vbParc.getChildren().add(t4);
          vbParc.getChildren().add(t);
          vbParc.getChildren().add(t5);
          vbParc.getChildren().add(t6);
          vbParc.getChildren().add(h);
         

          
          i++;
          System.out.println(m1.getId()+" "+m1.getPhotoPromenade());
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
   
   
   void editParc(ActionEvent e) throws SQLException {
    
        ResultSet rs =null;
        
           PromenadeService m= new PromenadeService();
          
            String a =((Node)e.getSource()).getId();
            
            rs=  m.AfficherPromenadeById(a) ;
            if (rs==null){
               
            }
            int i=0;
            Promenade m1=new Promenade();
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
               m1.setId(rs.getString("id"));
          m1.setNomPromenade(rs.getString("nomPromenade"));
          m1.setTypePromenade(rs.getString("typePromenade"));
          m1.setLieuPromenade(rs.getString("lieuPromenade"));
          m1.setDescriptionPromenade(rs.getString("descriptionPromenade"));
          m1.setDatedebutPromenade(rs.getDate("datedebutPromenade"));
          m1.setDatefinPromenade(rs.getDate("datefinPromenade"));
          m1.setPhotoPromenade(rs.getString("photoPromenade"));
                
                
                 
                ImageView im= new ImageView();
                Image image= new Image("zanimaux/ImageUtile/"+m1.getPhotoPromenade(),150,120,false,false) ;
                im.setImage(image);
                
                Text n1 = new Text("Nom de l'événement:");
                n1.setFont(Font.font("Comic Sans MS", 19) );
                n1.setFill(Color.web("#0076a3"));
                TextField nom =new TextField(""+m1.getNomPromenade());
                nom.setFont(Font.font("Comic Sans MS", 17));
                
                
                
                
                Text n2 = new Text("Lieu :");
                n2.setFont(Font.font("Comic Sans MS", 19) );
                n2.setFill(Color.web("#0076a3"));
                TextField lieu =new TextField(""+m1.getLieuPromenade());
                lieu.setFont(Font.font("Comic Sans MS", 17));
                
                
                
                
                Text n3 = new Text("Description:");
                n3.setFont(Font.font("Comic Sans MS", 19) );
                n3.setFill(Color.web("#0076a3"));
                TextArea desc =new TextArea(""+m1.getDescriptionPromenade());
                desc.setFont(Font.font("Comic Sans MS", 17));
                
                
                
                
                Text n4 = new Text("Date debut:");
                n4.setFont(Font.font("Comic Sans MS", 19) );
                n4.setFill(Color.web("#0076a3"));
                DatePicker dated =new DatePicker(LocalDate.parse(m1.getDatedebutPromenade().toString()));
                
                Text n5 = new Text("Date fin:");
                n5.setFont(Font.font("Comic Sans MS", 19) );
                n5.setFill(Color.web("#0076a3"));
                DatePicker datef =new DatePicker(LocalDate.parse(m1.getDatefinPromenade().toString()));
                
                
                
                
                ChoiceBox<String> catg = new ChoiceBox ();
                catg.setTooltip(new Tooltip("Choisir le type"));
                
                List<String> list = new ArrayList();
                list.add("Promenade");
                list.add("Garde");
                
             ObservableList<String> ob = FXCollections.observableArrayList();
        ob.addAll(list);
        catg.setItems(ob);
        catg.getSelectionModel().select(m1.getTypePromenade());
        String z;
        int r = 0;
        z = catg.getValue();
        if ("Promenade".equals(z)) {
            r = 1;
        } else if ("Garde".equals(z)) {
            r = 2;
        } 
        Label chooseFile = new Label();
        chooseFile.setText(m1.getPhotoPromenade());
        Image imagePhoto = new Image("zanimaux/ImageUtile/photo.png",26,26,false,false);
        
        Button pho = new Button();
        pho.setBackground(Background.EMPTY);
        pho.setGraphic(new ImageView(imagePhoto));
        pho.setId(String.valueOf(m1.getId()));
        pho.setOnAction((ActionEvent event) -> {
            FileChooser fileChooser = new FileChooser();
            
            //Set extension filter
            FileChooser.ExtensionFilter extFilterJPG = new FileChooser.ExtensionFilter("JPG files (*.jpg)", "*.JPG");
            FileChooser.ExtensionFilter extFilterPNG = new FileChooser.ExtensionFilter("PNG files (*.png)", "*.PNG");
            fileChooser.getExtensionFilters().addAll(extFilterJPG, extFilterPNG);
            
            //Show open file dialog
            File file = fileChooser.showOpenDialog(null);
            chooseFile.setText(file.getName());
            filePath = file.getAbsolutePath();
            try {
                BufferedImage bufferedImage = ImageIO.read(file);
                Image images = SwingFXUtils.toFXImage(bufferedImage, null);
                im.setImage(images);
                im.setFitHeight(120);
                im.setFitWidth(150);
                
            } catch (IOException ex) {
                System.err.println(ex);
            }
                });
        
        
        
        
        Image imageCheck = new Image("zanimaux/ImageUtile/check.png",26,26,false,false);
          Image imageBack = new Image("zanimaux/ImageUtile/left.png",26,26,false,false);
          
          
          Button ret = new Button();
        ret.setBackground(Background.EMPTY);
        ret.setGraphic(new ImageView(imageBack));
        ret.setOnAction(new EventHandler<ActionEvent>(){
             @Override 
            public void handle(ActionEvent event) {
                 try {
                     resetPageData();
                 } catch (SQLException ex) {
                     Logger.getLogger(PromenadeController.class.getName()).log(Level.SEVERE, null, ex);
                 }
            }
        });
        
        Button val = new Button();
        val.setBackground(Background.EMPTY);
            val.setGraphic(new ImageView(imageCheck));
        val.setId(String.valueOf(m1.getId()));
            val.setOnAction(new EventHandler<ActionEvent>() {  
            @Override 
            public void handle(ActionEvent event) {
               
                try {
                    PromenadeService m= new PromenadeService();
                    
                     LocalDate d = dated.getValue();
         Date dated2 = Date.from(d.atStartOfDay(ZoneId.systemDefault()).toInstant());
         
             LocalDate d2 = datef.getValue();
         Date datef2 = Date.from(d2.atStartOfDay(ZoneId.systemDefault()).toInstant());
                    String z;
                    String r="Autre";
                    z = catg.getValue();
                    if ("Promenade".equals(z)) {
                        r = "Promenade";
                    } else if ("Garde".equals(z)) {
                        r = "Garde";
                    } 
                    User user=Session.getLoggedInUser();
                    String cin=user.getCin();
                    String e1 =((Node)event.getSource()).getId();
                    Promenade u=new Promenade(e1,nom.getText(),r,lieu.getText(),desc.getText(),dated2,datef2,chooseFile.getText(),cin);
                    javafx.scene.control.Alert alert = new  javafx.scene.control.Alert(javafx.scene.control.Alert.AlertType.CONFIRMATION, "", ButtonType.YES, ButtonType.NO);
                    alert.setHeaderText("voulez-vous vraiment modifié ce parc ?");
                    alert.showAndWait();

     if (alert.getResult() == ButtonType.YES) {
                    m.ModifierPromenade(u);
                    System.out.println("Promenade Modifié");
                    resetPageData();
                } }catch (SQLException ex) {
                    Logger.getLogger(PromenadeController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
            HBox t = new HBox (catg,pho);
            HBox bt = new HBox (ret,val);
            bt.setSpacing(100);
            
                      VBox vbParc = new VBox(); 
          vbParc.setPadding(new Insets(-60,0,30,30));
          vbParc.setSpacing(10);
          vbParc.setStyle("-fx-background-color:#E3F9FE;-fx-background-radius:20px;");
         
          vbParc.setPrefSize(250, 200);
          vbParc.getChildren().add(im);
          vbParc.getChildren().add(n1);
          vbParc.getChildren().add(nom);
          vbParc.getChildren().add(n2);
          vbParc.getChildren().add(lieu);
          vbParc.getChildren().add(n3);
          vbParc.getChildren().add(desc);
          vbParc.getChildren().add(n4);
          vbParc.getChildren().add(dated);
          vbParc.getChildren().add(n5);
          vbParc.getChildren().add(datef);
          vbParc.getChildren().add(t);
          vbParc.getChildren().add(bt);
          
          i++;
          
          if(i%3!=1)
          {
            hb.getChildren().add(vbParc) ;
          }
          else
          {
            hb = new HBox();
            hb.setPadding(new Insets(0,0,0,0));
            hb.setSpacing(10);
            hb.getChildren().add(vbParc) ;
            vb.getChildren().add(hb); 
           }
                 
      }
        sp.setContent(vb);
         anchorEvent.getChildren().setAll(sp);
           
    }
   

    @FXML
    private void Deconnexion(ActionEvent event) {
        Session.setLoggedInUser(null);
        Parent root;
             try {
                 root = FXMLLoader.load(getClass().getResource("login.fxml"));
                 Stage myWindow = (Stage) logOut.getScene().getWindow();
                 Scene sc = new Scene(root);
                 myWindow.setScene(sc);
                 myWindow.setTitle("Login");
                 myWindow.show();
             } catch (IOException ex) {
                 Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
             }
    }
   
   
}



    

