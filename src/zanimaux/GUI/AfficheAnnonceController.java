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
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
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
import javafx.scene.control.Pagination;
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
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import zanimaux.Service.AnnonceFavorisService;
import zanimaux.Service.AnnonceService;
import zanimaux.Service.EvenementService;
import zanimaux.entities.Annonce;
import zanimaux.entities.AnnonceFavoris;
import zanimaux.entities.Evenement;
import zanimaux.entities.User;
import zanimaux.util.Session;

/**
 * FXML Controller class
 *
 * @author Maroua
 */
public class AfficheAnnonceController implements Initializable {

    @FXML
    private Button button;
    @FXML
    private Button btnEvenement;
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
    private TextField typeA;
    @FXML
    private TextField titreA;
    @FXML
    private TextArea descriptionA;
    @FXML
    private Button BtnChoixImageA;
    @FXML
    private Button buttonAnnCreate;
    @FXML
    private ImageView imageDetails;
  
    @FXML
    private ImageView iv1;
    @FXML
    private AnchorPane AnchorPaneAnnonce;
    @FXML
    private AnchorPane anchorDetailsAnnonce;
    public int a;
    @FXML
    private Label typeAnn;
    @FXML
    private Label titreAnn;
    @FXML
    private Label descriptionAnn;
    @FXML
    private Button likeBtn;
    @FXML
    private Button dislikeBtn;
     int nbPages =0;
    
    Pagination pagination=new Pagination();
        ArrayList<Annonce> listeAnn ;
    @FXML
    private Button annFav;
    @FXML
    private ImageView annFavo;


    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
       afficher();
    }    
 ScrollPane consulterAnnonce(int pageIndex) {
    
        User usr = Session.getLoggedInUser();
      /*  AnnonceService as = null;
        as = new AnnonceService();
    
        Annonce a1=new Annonce();
        ResultSet r= as.rechercheAnnonce;*/
        
         ScrollPane sp = new ScrollPane();
    
          sp.setPrefSize(900, 650);
        //sp.setMaxSize(Control.USE_PREF_SIZE, Control.USE_PREF_SIZE);
//         sp.setMinSize(Control.USE_COMPUTED_SIZE, Control.USE_COMPUTED_SIZE);
        VBox vb = new VBox();
        HBox hb =null;
        
        vb.setPadding(new Insets(100, 30, 0, 30));
        vb.setSpacing(70);
        int i=0;
       
             for(int j=pageIndex*2;j<listeAnn.size();j++){
                i++;
                if(j<=(pageIndex*2+(2-1))){
                    
              Annonce a1 = new Annonce();
                a1= listeAnn.get(j);
              
                ImageView im= new ImageView();
                Image image= new Image("zanimaux/ImageUtile/"+a1.getPieceJointe(),150,120,false,false); //("zanimaux/ImageUtile/"+e1.getImageEvt(),150,120,false,false) ;
                im.setImage(image);
                Text t1 =new Text(a1.getTitre());
                t1.setFont(Font.font("Verdana", 10) );
                Text t =new Text(a1.getType());
                t.setFont(Font.font("Verdana", 15) );
                
                Button consulter = new Button();
                Button modifier = new Button();
                             modifier.setId(String.valueOf(a1.getIdAnnonce()));
               
                modifier.setOnAction(x->{
                    try {
                        goToModif(x);
                    } catch (SQLException ex) {
                        Logger.getLogger(AfficheAnnonceController.class.getName()).log(Level.SEVERE, null, ex);
                    }
});
                Button supprimer = new Button();
                supprimer.setId(String.valueOf(a1.getIdAnnonce()));
                 supprimer.setOnAction(x->{
                    try {
                        supprimerAnnonce(x);
                    } catch (SQLException ex) {
                        Logger.getLogger(AfficheAnnonceController.class.getName()).log(Level.SEVERE, null, ex);
                    }
});
               
                HBox BtnBox=new HBox(consulter,modifier,supprimer);
                BtnBox.setSpacing(50);
                consulter.setText("consulter annonce");
                 modifier.setText("modifier");
                 modifier.setVisible(false);
                 supprimer.setText("Supprimer");
                 supprimer.setVisible(false);
                
                 if(usr.getCin().equals (a1.getCinUser())){
                     modifier.setVisible(true);
                      supprimer.setVisible(true);
                     
                 }
                 
                consulter.setId(String.valueOf(a1.getIdAnnonce()));
                consulter.setOnAction(s->{
                    try {
                        consulterDetailsAnnonce(s);
                    } catch (SQLException ex) {
                        Logger.getLogger(AfficheAnnonceController.class.getName()).log(Level.SEVERE, null, ex);
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
                    vb.getChildren().add(hb);}}
                
                      
                 

           
        sp.setContent(vb);
        return sp;
         //anchorEvent.getChildren().setAll(sp);
        
           
    }
 
 void remplir()throws SQLException{
        AnnonceService as= new AnnonceService();
        ResultSet r= as.rechercheAnnonce();
        listeAnn= new ArrayList<Annonce>();
        while(r.next()){ 
                    Annonce a1=new Annonce();

            a1.setIdAnnonce(r.getInt("idAnnonce"));
            a1.setCinUser(r.getString("cin"));            
                a1.setType(r.getString("type"));
                a1.setTitre(r.getString("titre"));
                a1.setDescription(r.getString("description"));
                
                a1.setPieceJointe(r.getString("photoAnimal"));
               
            listeAnn.add(a1);
            
            
        }
       int nb=listeAnn.size();
        if ((nb%2)==0){
            nbPages=nb/2; 
        }
        else{
        nbPages=((nb/2)+1); 
  
        }
    }
    
    void afficher(){
          try {
            remplir();
        } catch (SQLException ex) {
            Logger.getLogger(AfficheEventController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
      
        pagination.setPageCount(nbPages);
        pagination.setPageFactory((Integer indexPage)->consulterAnnonce(indexPage));
        AnchorPane.setTopAnchor(pagination, 10.0);
        AnchorPane.setLeftAnchor(pagination, 10.0);
        AnchorPane.setRightAnchor(pagination, 10.0);
        AnchorPane.setBottomAnchor(pagination, 10.0);
        anchorEvent.getChildren().add(pagination);
        
        
    }
 
  void goToModif(ActionEvent e) throws SQLException {
          a=Integer.parseInt(((Node) e.getSource()).getId());
          AnnonceService as = new AnnonceService();
          Annonce a1 = new Annonce();
          a1=as.rechercheAnnoncce(a);
          anchorEvent.getChildren().setAll(AnchorPaneAnnonce);
          AnchorPaneAnnonce.setVisible(true);
          typeA.setText(a1.getType());
        
          titreA.setText(a1.getTitre());
          descriptionA.setText(a1.getDescription());
         
          BtnChoixImageA.setText(a1.getPieceJointe());
}
   
    @FXML
    void modifier(ActionEvent e ) throws SQLException,IOException{
         
         User usr = Session.getLoggedInUser();
           
          System.out.println(a);
          AnnonceService es = new AnnonceService();
             
        Annonce a1=new Annonce(usr.getCin(),typeA.getText(),titreA.getText(),descriptionA.getText(),BtnChoixImageA.getText());
             
        es.modifierAnnonce(a, a1);
         afficher();
         
               
    }
  
 void  supprimerAnnonce(ActionEvent e) throws SQLException{
         a=Integer.parseInt(((Node) e.getSource()).getId());
         
          AnnonceService as = new AnnonceService();
         as.supprimerAnnonce(a);
         afficher();


}
 

  void consulterDetailsAnnonce(ActionEvent e) throws SQLException{
            a=Integer.parseInt(((Node) e.getSource()).getId());
        AnnonceService as = new AnnonceService();
        Annonce a1 = new Annonce();
          a1=as.rechercheAnnoncce(a);
          anchorEvent.getChildren().setAll(anchorDetailsAnnonce);
          anchorDetailsAnnonce.setVisible(true);
          titreAnn.setText(a1.getTitre());
       
          typeAnn.setText(a1.getType());
        
          descriptionAnn.setText(a1.getDescription());
        
       Image image= new Image("zanimaux/ImageUtile/"+a1.getPieceJointe(),150,120,false,false); //("zanimaux/ImageUtile/"+e1.getImageEvt(),150,120,false,false) ;
         imageDetails.setImage(image);
         
         AnnonceFavorisService ps=new AnnonceFavorisService();
        boolean verif=ps.existf(a);
        System.out.println(verif);
         likeBtn.setVisible(false);
         dislikeBtn.setVisible(false);
         
        
              
        
        
         if(verif==true){
            dislikeBtn.setVisible(true);
            
        }
        else if( verif==false){
        likeBtn.setVisible(true);

         
                    
        }
        
    }
  
  
    @FXML
  void like() throws SQLException{
       User usr = Session.getLoggedInUser();
        Annonce a1 = new Annonce();
       
        
        AnnonceFavorisService afs=new AnnonceFavorisService();
        
        AnnonceFavoris af =new AnnonceFavoris(a,usr.getCin());
      
       afs.ajouterFavoris(af);       
       afficher();
       
      
  }
  
    @FXML
    void dislike() throws SQLException{
       User usr = Session.getLoggedInUser();
       
        
        AnnonceFavorisService afs=new AnnonceFavorisService();
        
        AnnonceFavoris af =new AnnonceFavoris(a,usr.getCin());
      
       afs.supprimerFavoris(a);
       
       afficher();
       
      
  }
    
    @FXML
   void afficherFavoris() throws SQLException{
        User usr = Session.getLoggedInUser();
         AnnonceFavorisService afs=new AnnonceFavorisService();
          AnnonceFavoris af =new AnnonceFavoris(a,usr.getCin());
          afs.mesAnnonceFavoris(usr.getCin());
          remplir();
       
        
    }


}

