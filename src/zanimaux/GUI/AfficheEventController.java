/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package zanimaux.GUI;

import java.awt.image.BufferedImage;
import java.io.File;
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
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javax.imageio.ImageIO;
import zanimaux.Service.AnimalService;
import zanimaux.Service.EvenementService;
import zanimaux.Service.ParcService;
import zanimaux.Service.RefugeService;
import zanimaux.entities.Animal;
import zanimaux.entities.Evenement;
import zanimaux.entities.Parc;
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
    @FXML
    private AnchorPane AnchorPaneEvent;
    @FXML
    private TextField lieu;
    @FXML
    private DatePicker dateDebut;
    @FXML
    private DatePicker dateFin;
    @FXML
    private TextField type;
    @FXML
    private TextField titre;
    @FXML
    private TextArea description;
    @FXML
    private Button BtnChoixImage;
    @FXML
    private TextField nbPlace;
    @FXML
    private ImageView iv;
    @FXML
    private Button buttonModifierEvent;
    public String filePath;

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
                e1.setIdEvt(r.getInt("idEvt"));
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
                             modifier.setId(String.valueOf(e1.getIdEvt()));
               
                modifier.setOnAction(x->{
                    try {
                        editEvent(x);
                    } catch (SQLException ex) {
                        Logger.getLogger(AfficheEventController.class.getName()).log(Level.SEVERE, null, ex);
                    }
});
                Button supprimer = new Button();
                 supprimer.setOnAction(x->{
                    try {
                        supprimerEvent(x);
                    } catch (SQLException ex) {
                        Logger.getLogger(AfficheEventController.class.getName()).log(Level.SEVERE, null, ex);
                    }
});
               
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
                     
                });
   
                VBox vbEvent = new VBox();
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
    
    /*private void goToModif(ActionEvent e) {
          Session.setLoggedInUser(null);
        Parent root;
             try {
                 root = FXMLLoader.load(getClass().getResource("modifierEvent.fxml"));
                 Stage myWindow = (Stage) button.getScene().getWindow();
                 Scene sc = new Scene(root);
                 myWindow.setScene(sc);
                 myWindow.setTitle("modifier");
                 myWindow.show();
             } catch (IOException ex) {
                 Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
             }

    }*/
    
    void goToModif(ActionEvent e) throws SQLException {
          int a=Integer.parseInt(((Node) e.getSource()).getId());
          EvenementService es = new EvenementService();
          Evenement e1 = new Evenement();
          e1=es.rechercheEvent(a);
          anchorEvent.getChildren().setAll(AnchorPaneEvent);
          AnchorPaneEvent.setVisible(true);
          lieu.setText(e1.getLieu());
          System.out.println(e1.getLieu());
          type.setText(e1.getType());
          titre.setText(e1.getTitre());
          description.setText(e1.getDescription());
          nbPlace.setText(Integer.toString(e1.getNbPlace()));
          BtnChoixImage.setText(e1.getImageEvt());
    }
    
     public String handle() {
        FileChooser fileChooser = new FileChooser();

        //Set extension filter
        FileChooser.ExtensionFilter extFilterJPG = new FileChooser.ExtensionFilter("JPG files (*.jpg)", "*.JPG");
        FileChooser.ExtensionFilter extFilterPNG = new FileChooser.ExtensionFilter("PNG files (*.png)", "*.PNG");
        fileChooser.getExtensionFilters().addAll(extFilterJPG, extFilterPNG);

        //Show open file dialog
        File file = fileChooser.showOpenDialog(null);
        String filePath = file.getAbsolutePath();
        try {
            BufferedImage bufferedImage = ImageIO.read(file);
            Image image = SwingFXUtils.toFXImage(bufferedImage, null);
            iv.setImage(image);

        } catch (IOException ex) {
            System.err.println(ex);
        }

        return filePath;
    }
     @FXML
    void modifier(ActionEvent e) throws SQLException{
           int a=Integer.parseInt(((Node) e.getSource()).getId());
          EvenementService es = new EvenementService();
          Evenement e1 = new Evenement();
          e1=es.rechercheEvent(a);
         
          
          lieu.setText(e1.getLieu());
          System.out.println(e1.getLieu());
          type.setText(e1.getType());
          titre.setText(e1.getTitre());
          description.setText(e1.getDescription());
          nbPlace.setText(Integer.toString(e1.getNbPlace()));
          
         es.modifierEvenement(a, e1);
               
    }

    void  supprimerEvent(ActionEvent e) throws SQLException{
         
          EvenementService es = new EvenementService();
          Evenement e1 = new Evenement();
        int s = e1.getIdEvt();
          es.supprimerEvenement(s);
        
    }
    @FXML
    private void uploadImage(ActionEvent event) {
        BtnChoixImage.setText(handle());
    }
    
   void editEvent(ActionEvent e) throws SQLException {
//    
//       
//        
//           EvenementService es= new EvenementService();
//          
//            int a=Integer.parseInt(((Node) e.getSource()).getId());            
//           ResultSet rs=  es.rechercheEvent(a) ;
//            if (rs==null){
//               
//            }
//            int i=0;
//            Evenement e1=new Evenement();
//            ScrollPane sp = new ScrollPane();
//            sp.setPrefSize(900, 650);
//            sp.setMaxSize(Control.USE_COMPUTED_SIZE, Control.USE_COMPUTED_SIZE);
//            sp.setMinSize(Control.USE_COMPUTED_SIZE, Control.USE_COMPUTED_SIZE);
//            VBox vb = new VBox();
//            HBox hb =null;
//            vb.setPadding(new Insets(100, 30, 0, 30));
//            vb.setSpacing(100);
//            
//             while(rs.next())
//            {
//                e1.setIdEvt(rs.getInt("idEvt"));
//                e1.setLieu(rs.getString("lieu"));
//                e1.setDateDebut(rs.getDate("dateDebut"));
//                e1.setDateFin(rs.getDate("dateFin"));
//                e1.setType(rs.getString("type"));
//                e1.setTitre(rs.getString("titre"));
//                e1.setDescription(rs.getString("description"));
//                e1.setNbPlace(rs.getInt("nb_place"));
//                e1.setImageEvt(rs.getString("image_evt"));
//                
//                
//                 
//                ImageView im= new ImageView();
//                Image image= new Image("zanimaux/ImageUtile/"+e1.getImageEvt(),150,120,false,false) ;
//                im.setImage(image);
//                
//                Text n1 = new Text("lieu:");
//                n1.setFont(Font.font("Comic Sans MS", 19) );
//                n1.setFill(Color.web("#0076a3"));
//                TextField lieu =new TextField(""+e1.getLieu());
//                lieu.setFont(Font.font("Comic Sans MS", 17));
//                
//                
//                
//                
//                Text n2 = new Text("date debut");
//                n2.setFont(Font.font("Comic Sans MS", 19) );
//                n2.setFill(Color.web("#0076a3"));
//                TextField dated =new TextField(""+e1.getDateDebut());
//                dated.setFont(Font.font("Comic Sans MS", 17));
//                
//                    Text n3 = new Text("date fin");
//                n3.setFont(Font.font("Comic Sans MS", 19) );
//                n3.setFill(Color.web("#0076a3"));
//                TextField datef =new TextField(""+e1.getDateFin());
//                datef.setFont(Font.font("Comic Sans MS", 17));
//                
//                
//                
//                
//                
//                Text n4 = new Text("Type:");
//                n4.setFont(Font.font("Comic Sans MS", 19) );
//                n4.setFill(Color.web("#0076a3"));
//                TextField type =new TextField(""+e1.getType());
//                type.setFont(Font.font("Comic Sans MS", 17));
//                
//                
//                
//                
//                Text n5 = new Text("Titre:");
//                n4.setFont(Font.font("Comic Sans MS", 19) );
//                n4.setFill(Color.web("#0076a3"));
//                TextField titre =new TextField(""+e1.getTitre());
//                titre.setFont(Font.font("Comic Sans MS", 17));
//                
//                Text n6 = new Text("description:");
//                n6.setFont(Font.font("Comic Sans MS", 19) );
//                n6.setFill(Color.web("#0076a3"));
//                TextField description =new TextField(""+e1.getDescription());
//                description.setFont(Font.font("Comic Sans MS", 17));
//                
//                 Text n7 = new Text("nb plce:");
//                n7.setFont(Font.font("Comic Sans MS", 19) );
//                n7.setFill(Color.web("#0076a3"));
//                TextField nb =new TextField(""+e1.getNbPlace());
//                nb.setFont(Font.font("Comic Sans MS", 17));
//                
//                
//                
//                /*ChoiceBox<String> catg = new ChoiceBox ();
//                catg.setTooltip(new Tooltip("Choisir la catégorie"));
//                
//                List<String> list = new ArrayList();
//                list.add("Chien");
//                list.add("Chevaux");
//                list.add("Autre");
//             ObservableList<String> ob = FXCollections.observableArrayList();
//        ob.addAll(list);
//        catg.setItems(ob);
//        catg.getSelectionModel().select(m1.getCategorieDressage());
//        String z;
//        int r = 0;
//        z = catg.getValue();
//        if ("Chien".equals(z)) {
//            r = 1;
//        } else if ("Chevaux".equals(z)) {
//            r = 2;
//        } else if ("Autre".equals(z)) {
//            r = 3;
//        }*/
//        Label chooseFile = new Label();
//        chooseFile.setText(e1.getImageEvt());
//        Image imagePhoto = new Image("zanimaux/ImageUtile/photo.png",26,26,false,false);
//        
//        Button pho = new Button();
//        pho.setBackground(Background.EMPTY);
//        pho.setGraphic(new ImageView(imagePhoto));
//        pho.setId(String.valueOf(e1.getIdEvt()));
//        pho.setOnAction((ActionEvent event) -> {
//            FileChooser fileChooser = new FileChooser();
//            
//            //Set extension filter
//            FileChooser.ExtensionFilter extFilterJPG = new FileChooser.ExtensionFilter("JPG files (*.jpg)", "*.JPG");
//            FileChooser.ExtensionFilter extFilterPNG = new FileChooser.ExtensionFilter("PNG files (*.png)", "*.PNG");
//            fileChooser.getExtensionFilters().addAll(extFilterJPG, extFilterPNG);
//            
//            //Show open file dialog
//            File file = fileChooser.showOpenDialog(null);
//            chooseFile.setText(file.getName());
//            filePath = file.getAbsolutePath();
//            try {
//                BufferedImage bufferedImage = ImageIO.read(file);
//                Image images = SwingFXUtils.toFXImage(bufferedImage, null);
//                im.setImage(images);
//                im.setFitHeight(120);
//                im.setFitWidth(150);
//                
//            } catch (IOException ex) {
//                System.err.println(ex);
//            }
//                });
//        
//        
//        
//        
//        Image imageCheck = new Image("zanimaux/ImageUtile/check.png",26,26,false,false);
//          Image imageBack = new Image("zanimaux/ImageUtile/left.png",26,26,false,false);
//          
//          
//          Button ret = new Button();
//        ret.setBackground(Background.EMPTY);
//        ret.setGraphic(new ImageView(imageBack));
//        ret.setOnAction(new EventHandler<ActionEvent>(){
//             @Override 
//            public void handle(ActionEvent event) {
//                 try {
//                     consulterEvent(event);
//                 } catch (SQLException ex) {
//                     Logger.getLogger(AfficheEventController.class.getName()).log(Level.SEVERE, null, ex);
//                 }
//            }
//        });
//        
//        Button val = new Button();
//        val.setBackground(Background.EMPTY);
//            val.setGraphic(new ImageView(imageCheck));
//        val.setId(String.valueOf(e1.getIdEvt()));
//            val.setOnAction(new EventHandler<ActionEvent>() {  
//            @Override 
//            public void handle(ActionEvent event) {
//               
//                try {
//                    EvenementService se= new EvenementService();
//                    
//                   /* String z;
//                    String r="Autre";
//                    z = catg.getValue();
//                    if ("Chien".equals(z)) {
//                        r = "Chien";
//                    } else if ("Chevaux".equals(z)) {
//                        r = "Chevaux";
//                    } else if ("Autre".equals(z)) {
//                        r = "Autre";
//                    }*/
//                    User user=Session.getLoggedInUser();
//                    String cin=user.getCin();
//                    String e1 =((Node)event.getSource()).getId();
//                    Evenement e=new Evenement(e1,lieu.getText(),dated.getText(),datef.getText(),type.getText(),titre.getText(),description.getText(),Integer.parseInt(nbPlace.getText()),chooseFile.getText(),cin);
//                    javafx.scene.control.Alert alert = new  javafx.scene.control.Alert(javafx.scene.control.Alert.AlertType.CONFIRMATION, "", ButtonType.YES, ButtonType.NO);
//                    alert.setHeaderText("voulez-vous vraiment modifié ce parc ?");
//                    alert.showAndWait();
//
//     if (alert.getResult() == ButtonType.YES) {
//                    se.modifierEvenement(e);
//                    System.out.println("evenet Modifié");
//                    consulterEvent(event);
//                } }catch (SQLException ex) {
//                    Logger.getLogger(ParcController.class.getName()).log(Level.SEVERE, null, ex);
//                }
//            }
//        });
//            HBox t = new HBox (pho);
//            HBox bt = new HBox (ret,val);
//            bt.setSpacing(100);
//            
//                      VBox vbParc = new VBox(); 
//          vbParc.setPadding(new Insets(-60,0,30,30));
//          vbParc.setSpacing(10);
//          vbParc.setStyle("-fx-background-color:#E3F9FE;-fx-background-radius:20px;");
//         
//          vbParc.setPrefSize(250, 200);
//          vbParc.getChildren().add(im);
//          vbParc.getChildren().add(n1);
//          vbParc.getChildren().add(lieu);
//          vbParc.getChildren().add(n2);
//          vbParc.getChildren().add(dated);
//          vbParc.getChildren().add(n3);
//          vbParc.getChildren().add(datef);
//          vbParc.getChildren().add(n4);
//          vbParc.getChildren().add(type);
//              vbParc.getChildren().add(n5);
//          vbParc.getChildren().add(titre);
//              vbParc.getChildren().add(n6);
//          vbParc.getChildren().add(description);
//           vbParc.getChildren().add(n7);
//          vbParc.getChildren().add(nb);
//          vbParc.getChildren().add(t);
//          vbParc.getChildren().add(bt);
//          
//          i++;
//          
//          if(i%3!=1)
//          {
//            hb.getChildren().add(vbParc) ;
//          }
//          else
//          {
//            hb = new HBox();
//            hb.setPadding(new Insets(0,0,0,0));
//            hb.setSpacing(10);
//            hb.getChildren().add(vbParc) ;
//            vb.getChildren().add(hb); 
//           }
//                 
//      }
//        sp.setContent(vb);
//         anchorEvent.getChildren().setAll(sp);
           
    }
   
    
}
