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
import java.util.ArrayList;
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
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
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
import zanimaux.Service.ParcService;
import zanimaux.entities.Parc;
import zanimaux.entities.User;
import zanimaux.util.Session;





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
   public String filePath;
    /**
     * Initializes the controller class.
     */
    @Override

    public void initialize(URL url, ResourceBundle rb)  {
        
        ParcService m=null;
        try {
            m = new ParcService();
        } catch (SQLException ex) {
            Logger.getLogger(ParcController.class.getName()).log(Level.SEVERE, null, ex);
        }
        User user=Session.getLoggedInUser();
            String cin=user.getCin();
        ResultSet r =m.AfficherParcByCin(cin);
        Parc m1=new Parc();
        r= m.AfficherParcByCin(cin);
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
          Image image= new Image("zanimaux/ImageUtile/"+m1.getPhotoParc(),150,120,false,false) ;
          im.setImage(image);
          Text t1 =new Text(m1.getNomParc());
          t1.setFont(Font.font("Verdana", 16));
          Text t3 = new Text("Dressage de : ");
          t3.setFont(Font.font("Verdana", 15));
          t3.setFill(Color.web("#0076a3"));
          Text t2 = new Text(m1.getCategorieDressage());
          t2.setFont(Font.font("Verdana", 14));
          Text t4 = new Text("Adresse : ");
          t4.setFont(Font.font("Verdana", 15));
          t4.setFill(Color.web("#0076a3"));
          Text t =new Text(m1.getAdresseParc()+" "+m1.getVilleParc()+", "+m1.getCodePostaleParc());
          t.setFont(Font.font("Verdana", 14) );
          
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
                        Logger.getLogger(ParcController.class.getName()).log(Level.SEVERE, null, ex);
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
                        Logger.getLogger(ParcController.class.getName()).log(Level.SEVERE, null, ex);
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
          vbParc.getChildren().add(h);
         

          
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
    private void onClickEvenementAction(ActionEvent event) throws SQLException {
       
        ParcService m= new ParcService();
          
            String a =((Node)event.getSource()).getId();
            javafx.scene.control.Alert alert = new  javafx.scene.control.Alert(javafx.scene.control.Alert.AlertType.CONFIRMATION, "", ButtonType.YES, ButtonType.NO);
        alert.setHeaderText("voulez-vous vraiment supprimer ce parc ?");
        alert.showAndWait();

     if (alert.getResult() == ButtonType.YES) {
            m.supprimerParc(a);
         resetPageData();
     }
    }
     @FXML
    private void showPane(MouseEvent event) {
    }
    @FXML
    private void retourner(ActionEvent event) {
        
        try {
        Stage stage=(Stage) ajou.getScene().getWindow(); 
        stage.setTitle("Ajouter Parc");
        Parent root = FXMLLoader.load(getClass().getResource("AjoutParc.fxml"));
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
        } catch (IOException ex) {
           Logger.getLogger(accueilOumaimaController.class.getName()).log(Level.SEVERE, null, ex);
       }


       
        
    }
    
   public void resetPageData() throws SQLException
    {
        
          ParcService m=null;
        try {
            m = new ParcService();
        } catch (SQLException ex) {
            Logger.getLogger(ParcController.class.getName()).log(Level.SEVERE, null, ex);
        }
        User user=Session.getLoggedInUser();
            String cin=user.getCin();
        ResultSet r =m.AfficherParcByCin(cin);
        Parc m1=new Parc();
        r= m.AfficherParcByCin(cin);
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
          Image image= new Image("zanimaux/ImageUtile/"+m1.getPhotoParc(),150,120,false,false) ;
          im.setImage(image);
          Text t1 =new Text(m1.getNomParc());
          t1.setFont(Font.font("Verdana", 16));
          Text t3 = new Text("Dressage de : ");
          t3.setFont(Font.font("Verdana", 15));
          t3.setFill(Color.web("#0076a3"));
          Text t2 = new Text(m1.getCategorieDressage());
          t2.setFont(Font.font("Verdana", 14));
          Text t4 = new Text("Adresse : ");
          t4.setFont(Font.font("Verdana", 15));
          t4.setFill(Color.web("#0076a3"));
          Text t =new Text(m1.getAdresseParc()+" "+m1.getVilleParc()+", "+m1.getCodePostaleParc());
          t.setFont(Font.font("Verdana", 14) );
          
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
                        Logger.getLogger(ParcController.class.getName()).log(Level.SEVERE, null, ex);
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
                        Logger.getLogger(ParcController.class.getName()).log(Level.SEVERE, null, ex);
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
          vbParc.getChildren().add(h);
         

          
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
   
   
   void editParc(ActionEvent e) throws SQLException {
    
        ResultSet rs =null;
        
           ParcService m= new ParcService();
          
            String a =((Node)e.getSource()).getId();
            
            rs=  m.AfficherParcById(a) ;
            if (rs==null){
               
            }
            int i=0;
            Parc m1=new Parc();
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
                m1.setNomParc(rs.getString("nomParc"));
                m1.setCategorieDressage(rs.getString("CategorieDressage"));
                m1.setAdresseParc(rs.getString("adresseParc"));
                m1.setVilleParc(rs.getString("villeParc"));
                m1.setCodePostaleParc(rs.getInt("codePostaleParc"));
                m1.setPhotoParc(rs.getString("photoParc"));
                
                
                 
                ImageView im= new ImageView();
                Image image= new Image("zanimaux/ImageUtile/"+m1.getPhotoParc(),150,120,false,false) ;
                im.setImage(image);
                
                Text n1 = new Text("Nom du parc:");
                n1.setFont(Font.font("Comic Sans MS", 19) );
                n1.setFill(Color.web("#0076a3"));
                TextField nom =new TextField(""+m1.getNomParc());
                nom.setFont(Font.font("Comic Sans MS", 17));
                
                
                
                
                Text n2 = new Text("Adresse du parc:");
                n2.setFont(Font.font("Comic Sans MS", 19) );
                n2.setFill(Color.web("#0076a3"));
                TextField adr =new TextField(""+m1.getAdresseParc());
                adr.setFont(Font.font("Comic Sans MS", 17));
                
                
                
                
                Text n3 = new Text("Ville du parc:");
                n3.setFont(Font.font("Comic Sans MS", 19) );
                n3.setFill(Color.web("#0076a3"));
                TextField ville =new TextField(""+m1.getVilleParc());
                ville.setFont(Font.font("Comic Sans MS", 17));
                
                
                
                
                Text n4 = new Text("Code postale:");
                n4.setFont(Font.font("Comic Sans MS", 19) );
                n4.setFill(Color.web("#0076a3"));
                TextField codep =new TextField(""+m1.getCodePostaleParc());
                codep.setFont(Font.font("Comic Sans MS", 17));
                
                
                
                ChoiceBox<String> catg = new ChoiceBox ();
                catg.setTooltip(new Tooltip("Choisir la catégorie"));
                
                List<String> list = new ArrayList();
                list.add("Chien");
                list.add("Chevaux");
                list.add("Autre");
             ObservableList<String> ob = FXCollections.observableArrayList();
        ob.addAll(list);
        catg.setItems(ob);
        catg.getSelectionModel().select(m1.getCategorieDressage());
        String z;
        int r = 0;
        z = catg.getValue();
        if ("Chien".equals(z)) {
            r = 1;
        } else if ("Chevaux".equals(z)) {
            r = 2;
        } else if ("Autre".equals(z)) {
            r = 3;
        }
        Label chooseFile = new Label();
        
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
                     Logger.getLogger(ParcController.class.getName()).log(Level.SEVERE, null, ex);
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
                    ParcService m= new ParcService();
                    
                    String z;
                    String r="Autre";
                    z = catg.getValue();
                    if ("Chien".equals(z)) {
                        r = "Chien";
                    } else if ("Chevaux".equals(z)) {
                        r = "Chevaux";
                    } else if ("Autre".equals(z)) {
                        r = "Autre";
                    }
                    User user=Session.getLoggedInUser();
                    String cin=user.getCin();
                    String e1 =((Node)event.getSource()).getId();
                    Parc u=new Parc(e1,nom.getText(),r,adr.getText(),ville.getText(),Integer.parseInt(codep.getText()),chooseFile.getText(),cin);
                    javafx.scene.control.Alert alert = new  javafx.scene.control.Alert(javafx.scene.control.Alert.AlertType.CONFIRMATION, "", ButtonType.YES, ButtonType.NO);
                    alert.setHeaderText("voulez-vous vraiment modifié ce parc ?");
                    alert.showAndWait();

     if (alert.getResult() == ButtonType.YES) {
                    m.ModifierParc(u);
                    System.out.println("Parc Modifié");
                    resetPageData();
                } }catch (SQLException ex) {
                    Logger.getLogger(ParcController.class.getName()).log(Level.SEVERE, null, ex);
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
          vbParc.getChildren().add(adr);
          vbParc.getChildren().add(n3);
          vbParc.getChildren().add(ville);
          vbParc.getChildren().add(n4);
          vbParc.getChildren().add(codep);
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
   
   
}



    

