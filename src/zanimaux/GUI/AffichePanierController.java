/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package zanimaux.GUI;

import com.itextpdf.text.DocumentException;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import zanimaux.util.SendMail;
import zanimaux.Service.PanierService;
import zanimaux.Service.ProduitService;
import zanimaux.entities.ContenuPanier;
import zanimaux.entities.Panier;
import zanimaux.entities.Produit;
import zanimaux.entities.User;
import zanimaux.util.CreatePDF;
import zanimaux.util.Session;

/**
 * FXML Controller class
 *
 * @author macbookpro
 */
public class AffichePanierController implements Initializable {

    @FXML
    private Button button;
    @FXML
    private Button buttonRefuge;
    @FXML
    private Button evenement;
    @FXML
    private Button userName;
    @FXML
    private Pane paneProfil;
    @FXML
    private Button btn11;
    @FXML
    private Button btn1;
    @FXML
    private AnchorPane anchorEvent;
    @FXML
    private VBox vbPanier;
    @FXML
    private VBox vbProduit;
    @FXML
    private VBox vbQuantite;
    @FXML
    private VBox vbPrix;
    @FXML
    private VBox vbTotal;
    @FXML
    private VBox vbBoutton;
    public static  String DEST = "file:///C:/simple_table6.pdf";
    @FXML
    private Button Petsitter;
    @FXML
    private Button annonceBtn;
    @FXML
    private Button parc;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            User u = Session.getLoggedInUser();
            PanierService p = new PanierService();
            ProduitService ps = new ProduitService();
            userName.setText(u.getUsername());
            List<ContenuPanier> cp = p.rechercheContenuPanier(u.getCin());
            for(int i =0;i<cp.size();i++)
            {
                Image image = new Image("zanimaux/Image/waste.png",30,30,false,false);
                Button supprimer = new Button("",new ImageView(image));
                supprimer.setId(String.valueOf(cp.get(i).getIdProduit()));
                supprimer.setOnAction(e->{
                    try {
                        supprimerProduitPanier(e);
                    } catch (SQLException ex) {
                        Logger.getLogger(AffichePanierController.class.getName()).log(Level.SEVERE, null, ex);
                    }
                });
                supprimer.setStyle("-fx-background-color:Transparent");
                Button plus = new Button();
                Button minus = new Button();
                plus.setText("+");
                minus.setText("-");
               
                HBox hbQuantite = new HBox();
                Produit prod = ps.rechercheProduitMagasin(cp.get(i).getIdProduit());
                Label libelle = new Label();
                Label prix = new Label();
                Label total = new Label();
                TextField qt = new TextField();
               
                qt.setMaxSize(30, 10);
                qt.setAlignment(Pos.CENTER);
                hbQuantite.getChildren().add(minus);
                hbQuantite.getChildren().add(qt);
                hbQuantite.getChildren().add(plus);
                libelle.setText(prod.getLibelle());
                prix.setText(String.valueOf(prod.getPrix()));
                total.setText(String.valueOf(prod.getPrix()*cp.get(i).getQuantite()));
                qt.setText(String.valueOf(cp.get(i).getQuantite()));
                vbPrix.getChildren().add(prix);
                vbQuantite.getChildren().add(hbQuantite);
                vbTotal.getChildren().add(total);
                vbProduit.getChildren().add(libelle);
                vbBoutton.getChildren().add(supprimer);
                 plus.setOnAction(new EventHandler<ActionEvent>() {
                    @Override public void handle(ActionEvent e) {
                    qt.setText(String.valueOf(Integer.parseInt(qt.getText())+1));
                    total.setText(String.valueOf(Double.parseDouble(total.getText())+Double.parseDouble(prix.getText())));
                    prod.setQuantite(prod.getQuantite()-1);
                    ps.ModifProduit(prod, prod.getIdProduit());
                    ContenuPanier c = p.rechercheProduitContenuPanier(u.getCin(), prod);
                    c.setQuantite(c.getQuantite()+1);
                    p.modifContenuPanier(prod.getIdProduit(), c);
                    Panier panier= p.recherchePanier(u.getCin());
                    panier.setSomme(panier.getSomme()+prod.getPrix());
                    p.modifPanier(u.getCin(), panier);
                    
                    }
                });
                 
                 minus.setOnAction(new EventHandler<ActionEvent>() {
                    @Override public void handle(ActionEvent e) {
                    qt.setText(String.valueOf(Integer.parseInt(qt.getText())-1));
                    total.setText(String.valueOf(Double.parseDouble(total.getText())-Double.parseDouble(prix.getText())));
                    prod.setQuantite(prod.getQuantite()+11);
                    ps.ModifProduit(prod, prod.getIdProduit());
                    ContenuPanier c = p.rechercheProduitContenuPanier(u.getCin(), prod);
                    c.setQuantite(c.getQuantite()-1);
                    p.modifContenuPanier(prod.getIdProduit(), c);
                    Panier panier= p.recherchePanier(u.getCin());
                    panier.setSomme(panier.getSomme()-prod.getPrix());
                    p.modifPanier(u.getCin(), panier);
                    }
                });
                if(qt.getText()=="1"){
                     minus.setDisable(true);
                 }
                 
            }
            vbPrix.setSpacing(50);
            vbProduit.setSpacing(50);
            vbQuantite.setSpacing(30);
            vbTotal.setSpacing(50);
            vbBoutton.setSpacing(30);
            vbPanier.setSpacing(50);
            Button passerCommande = new Button("Passer commande");
            passerCommande.setOnAction(e->{
                try {
                    passerCommande(e);
                } catch (SQLException ex) {
                    Logger.getLogger(AffichePanierController.class.getName()).log(Level.SEVERE, null, ex);
                }
            });
            
            Button consulterCommande = new Button("Consulter Commande");
            consulterCommande.setOnAction(e->{
                
                    consulterCommande(e);
               
            });
            consulterCommande.setStyle("-fx-background-color:transparent;-fx-text-fill:#1d7e9e;-fx-border-width: 0px 0px 2px 0px;-fx-border-color:#1d7e9e;");
            passerCommande.setStyle("-fx-background-color:transparent;-fx-text-fill:#1d7e9e;-fx-border-width: 0px 0px 2px 0px;-fx-border-color:#1d7e9e;");
            consulterCommande.setFont(Font.font("TIMES NEW ROMAN",15));
            passerCommande.setFont(Font.font("TIMES NEW ROMAN",15));
            consulterCommande.setPrefSize(300, 20);
            passerCommande.setPrefSize(300, 20);
            HBox hbutton = new HBox();
            hbutton.setSpacing(100);
            hbutton.getChildren().add(consulterCommande);
            hbutton.getChildren().add(passerCommande);
            vbPanier.setPadding(new Insets(30,30,0,0));
            vbPanier.getChildren().add(hbutton);
            anchorEvent.getChildren().setAll(vbPanier);
            anchorEvent.setMaxSize(300,300);
            anchorEvent.getChildren().add(paneProfil);
            vbPanier.setVisible(true);
        } catch (SQLException ex) {
            Logger.getLogger(AffichePanierController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }    


    @FXML
    private void AfficherRefugeAction(ActionEvent event) {
        try {
        Stage stage=(Stage) buttonRefuge.getScene().getWindow(); 
        stage.setTitle("NOS REFUGES");
        Parent root = FXMLLoader.load(getClass().getResource("RefugeClient.fxml"));
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
        } catch (IOException ex) {
           Logger.getLogger(accueilOumaimaController.class.getName()).log(Level.SEVERE, null, ex);
       }
    }

    @FXML
    private void onClickEvenementAction(ActionEvent event) {
             try {
        Stage stage=(Stage) buttonRefuge.getScene().getWindow(); 
        stage.setTitle("NOS EVENEMENTS");
        Parent root = FXMLLoader.load(getClass().getResource("afficheEvent.fxml"));
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
        } catch (IOException ex) {
           Logger.getLogger(accueilOumaimaController.class.getName()).log(Level.SEVERE, null, ex);
       }
    }


    @FXML
    private void showPaneProfil(MouseEvent event) {
        paneProfil.setVisible(true);
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


    private void supprimerProduitPanier(ActionEvent e) throws SQLException {
        User u = Session.getLoggedInUser();
        int a = Integer.parseInt(((Node)e.getSource()).getId());
        PanierService ps = new PanierService();
        Panier panier= ps.recherchePanier(u.getCin());
        ProduitService prodServ= new ProduitService();
        Produit p = prodServ.rechercheProduitMagasin(a);
        ContenuPanier cp = ps.rechercheProduitContenuPanier(u.getCin(), p);
        ps.SupprimerProduitPanier(cp.getIdContenuPanier());
        panier.setSomme(panier.getSomme()-(cp.getQuantite()*p.getPrix()));
        ps.modifPanier(u.getCin(), panier);
        p.setQuantite(p.getQuantite()+cp.getQuantite());
        prodServ.ModifProduit(p, p.getIdProduit());
          try {
        Stage stage=(Stage) buttonRefuge.getScene().getWindow(); 
        stage.setTitle("MON PANIER");
        Parent root = FXMLLoader.load(getClass().getResource("AffichePanier.fxml"));
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
        } catch (IOException ex) {
           Logger.getLogger(accueilOumaimaController.class.getName()).log(Level.SEVERE, null, ex);
       }
        
    }

    private void passerCommande(ActionEvent e) throws SQLException 
    {
                PanierService ps = new PanierService();
                List<ContenuPanier> c = new ArrayList(); 
                Panier p = new Panier();
                User u = Session.getLoggedInUser();
                c= ps.rechercheContenuPanier(u.getCin());
                p= ps.recherchePanier(u.getCin());
                p.setSommeCommande(p.getSommeCommande()+p.getSomme());
                p.setSomme(0);
                ps.modifPanier(u.getCin(), p);
                for (int i =0;i<c.size();i++)
                {   
                    c.get(i).setCommande(1);
                    c.get(i).setDateCommande(java.sql.Date.valueOf(LocalDate.now()));
                    ps.modifContenuPanier(c.get(i).getIdProduit(), c.get(i));
                }
                String to = u.getEmail();
                String subject = "Votre Commande Zanimaux";
                String message =  "Bonjour "+u.getPrenom()+" Vous trouverez ci-joint votre facture";
                String user = "zanimo.esprit@gmail.com";
                String pass = "esprit2018";
                File file = new File(DEST);
                file.getParentFile().mkdirs();
        try {
            new CreatePDF().createPdf(DEST);
        } catch (IOException ex) {
            Logger.getLogger(AffichePanierController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (DocumentException ex) {
            Logger.getLogger(AffichePanierController.class.getName()).log(Level.SEVERE, null, ex);
        }

                SendMail.send(to,subject, message,DEST, user, pass);
        try {
       Stage stage=(Stage) buttonRefuge.getScene().getWindow(); 
        stage.setTitle("MES COMMANDES");
        Parent root = FXMLLoader.load(getClass().getResource("AfficherCommande.fxml"));
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
        } catch (IOException ex) {
           Logger.getLogger(accueilOumaimaController.class.getName()).log(Level.SEVERE, null, ex);
       }
    }


    @FXML
    private void AfficherPromenade(ActionEvent event) {
   
        try {
            User user=Session.getLoggedInUser();
        String role=user.getRoles();
            String pet="a:1:{i:0;s:14:\"ROLE_PETSITTER\";}";
        Stage stage=(Stage) buttonRefuge.getScene().getWindow(); 
        stage.setTitle("Gestion des promenades");
        if(role.equals(pet)){
        Parent root = FXMLLoader.load(getClass().getResource("Promenade.fxml"));
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();}else{
            Parent root = FXMLLoader.load(getClass().getResource("ListePromenade.fxml"));
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
        }
        } catch (IOException ex) {
           Logger.getLogger(accueilOumaimaController.class.getName()).log(Level.SEVERE, null, ex);
       }
    }
    

    @FXML
    private void goToAnn(ActionEvent event) {
         try {
        Stage stage=(Stage) annonceBtn.getScene().getWindow(); 
        stage.setTitle("Deposez votre annonce");
        Parent root = FXMLLoader.load(getClass().getResource("afficheAnnonce.fxml"));
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
        } catch (IOException ex) {
           Logger.getLogger(accueilOumaimaController.class.getName()).log(Level.SEVERE, null, ex);
       }
    }
    @FXML
    private void goToVet(ActionEvent event) {
        
          try {
        Stage stage=(Stage) button.getScene().getWindow(); 
        stage.setTitle("Vétérinaire");
        Parent root = FXMLLoader.load(getClass().getResource("VetFront.fxml"));
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
        } catch (IOException ex) {
           Logger.getLogger(accueilOumaimaController.class.getName()).log(Level.SEVERE, null, ex);
       }
    }

    @FXML
    private void AfficherParc(ActionEvent event) {
      try {
        Stage stage=(Stage) buttonRefuge.getScene().getWindow(); 
        stage.setTitle("Liste des parcs");
        Parent root = FXMLLoader.load(getClass().getResource("ListeParc.fxml"));
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
        } catch (IOException ex) {
           Logger.getLogger(accueilOumaimaController.class.getName()).log(Level.SEVERE, null, ex);
       }
    }

    @FXML
    private void MagasinButtonAction(ActionEvent event) {
        
        try {
        Stage stage=(Stage) buttonRefuge.getScene().getWindow(); 
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
    private void afficherAccueil(ActionEvent event) {
            
       try {
      User user=Session.getLoggedInUser();
        String role=user.getRoles();
            String dres="a:1:{i:0;s:13:\"ROLE_DRESSEUR\";}";
        Stage stage=(Stage) button.getScene().getWindow(); 
        if(role.equals(dres)){
        stage.setTitle("Accueil");
        Parent root = FXMLLoader.load(getClass().getResource("AccueilDresseur.fxml"));
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();}else{
            stage.setTitle("Accueil");
        Parent root = FXMLLoader.load(getClass().getResource("Quiz.fxml"));
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
        }
        } catch (IOException ex) {
           Logger.getLogger(accueilOumaimaController.class.getName()).log(Level.SEVERE, null, ex);
       }
    }
    

    @FXML
    private void hidePane(MouseEvent event) {
        paneProfil.setVisible(false);
    }

    private void consulterCommande(ActionEvent e) 
    {
        try {
        Stage stage=(Stage) buttonRefuge.getScene().getWindow(); 
        stage.setTitle("MES COMMANDES");
        Parent root = FXMLLoader.load(getClass().getResource("AfficherCommande.fxml"));
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
        } catch (IOException ex) {
           Logger.getLogger(accueilOumaimaController.class.getName()).log(Level.SEVERE, null, ex);
       }
        
    }


    
}
