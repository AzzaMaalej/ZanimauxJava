/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package zanimaux.GUI;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import zanimaux.Service.CabinetDao;
import zanimaux.Service.Userservice;
import zanimaux.entities.Cabinet;
import zanimaux.entities.User;
import zanimaux.util.Session;

/**
 * FXML Controller class
 *
 * @author Maroua
 */
public class LoginController implements Initializable {

    @FXML
    private Button cx;
     
    @FXML
    private TextField cin;
    @FXML
    private PasswordField pass;
    @FXML
    private Label lb;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
         System.out.println(Session.getLoggedInUser());
    } 
    
    @FXML
    private void connexionAction(ActionEvent event) throws IOException, SQLException  {
      
  
//        if(cin.getText().equals("") || pass.getText().equals("")){
//            lb.setText("fill in the blanks");
//            return;
//        }else{
            //lb.setText("");
            Userservice usr = new Userservice();
            User result = usr.login(cin.getText());
            
            System.out.println("bienvenue");
             String z;
     
        z = result.getRoles();
        String vet= "a:1:{i:0;s:16:\"ROLE_VETERINAIRE\";}";
        String admin= "a:1:{i:0;s:16:\"ROLE_SUPER_ADMIN\";}";
        String propmag= "a:1:{i:0;s:25:\"ROLE_PROPRIETAIRE_MAGASIN\";}";
        String propref= "a:1:{i:0;s:24:\"ROLE_PROPRIETAIRE_REFUGE\";}";
        String clt= "a:1:{i:0;s:11:\"ROLE_CLIENT\";}";
        String dres="a:1:{i:0;s:13:\"ROLE_DRESSEUR\";}";
        String pet="a:1:{i:0;s:14:\"ROLE_PETSITTER\";}";
            if(result == null){
                lb.setText("Bad credentials");
                lb.setVisible(true);
            }else{ 
         if ( Userservice.checkPassword(pass.getText(),result.getPassword())) {
           
             
             if(z.equals(vet))
                {
                     try{
                    Session.setLoggedInUser(result);
                    // TODO: Proceed to other page
                    CabinetDao c= new CabinetDao();
                    Cabinet cab= c.getByVet(result.getCin());
                    if (cab==null){
                    FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("ajoutArticle.fxml"));
                     Parent root = (Parent) fxmlLoader.load();
                    Stage secondStage = new Stage();
                    secondStage.setScene(new Scene(root));
                    Stage stage = (Stage) cin.getScene().getWindow();
                    // do what you have to do
                    stage.hide();
                    secondStage.show();}
                    else { 
                    FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("VetDashboard.fxml"));
                    Parent root = (Parent) fxmlLoader.load();
                    Stage secondStage = new Stage();
                    secondStage.setScene(new Scene(root));
                    Stage stage = (Stage) cin.getScene().getWindow();
                    // do what you have to do
                    stage.hide();
                    secondStage.show();}
                   
                     } catch (IOException ex) {
                            Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
                        }}
                     else if (z.equals(admin))
                         
                {
                     try{
                    Session.setLoggedInUser(result);
                    // TODO: Proceed to other page
                    
                    FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("acceuil.fxml"));
                    Parent root = (Parent) fxmlLoader.load();
                    Stage secondStage = new Stage();
                    secondStage.setScene(new Scene(root));
                    Stage stage = (Stage) cin.getScene().getWindow();
                    // do what you have to do
                    stage.hide();
                    secondStage.show();
                     } catch (IOException ex) {
                            Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
                     }}
              else if (z.equals(propmag))
                         
                {
                     try{
                    Session.setLoggedInUser(result);
                    // TODO: Proceed to other page
                    
                    FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("DashboardMagasin.fxml"));
                    Parent root = (Parent) fxmlLoader.load();
                    Stage secondStage = new Stage();
                    secondStage.setScene(new Scene(root));
                    Stage stage = (Stage) cin.getScene().getWindow();
                    // do what you have to do
                    stage.hide();
                    secondStage.show();
                     } catch (IOException ex) {
                            Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
                        }}
              else if (z.equals(propref))
                         
                {
                     try{
                    Session.setLoggedInUser(result);
                    // TODO: Proceed to other page
                    
                    FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("RefugeDashboard.fxml"));
                    Parent root = (Parent) fxmlLoader.load();
                    Stage secondStage = new Stage();
                    secondStage.setScene(new Scene(root));
                    Stage stage = (Stage) cin.getScene().getWindow();
                    // do what you have to do
                    stage.hide();
                    secondStage.show();
                     } catch (IOException ex) {
                            Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
                        }}
              else if (z.equals(clt))
                         
                {
                     try{
                    Session.setLoggedInUser(result);
<<<<<<< HEAD
                //FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("afficheEvent.fxml"));
                //FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("accueilOumaima.fxml"));
                //FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("afficheEvent.fxml"));
                //FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("ProfilManager.fxml"));          
                //FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("magasin.fxml"));
               // FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("afficheAnnonce.fxml"));
                //FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("afficheEvent.fxml"));
             FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("afficheAnnonce.fxml"));


                   //FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("accueilOumaima.fxml"));
=======
                    // TODO: Proceed to other page

                  //FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("accueilOumaima.fxml"));
                 // FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("afficheEvent.fxml"));

<<<<<<< HEAD
                   //FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("accueilOumaima.fxml"));
=======
//                   FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("accueilOumaima.fxml"));
>>>>>>> a8f1c266bc59f9549703b1752ccc80e15088f268
>>>>>>> a24fe45eb748432966ed69bf230aa20cb263247c
                  //FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("afficheEvent.fxml"));

                  //FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("ProfilManager.fxml"));          
                   // FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("magasin.fxml"));
<<<<<<< HEAD
=======

                //FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("accueilOumaima.fxml"));
                //FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("afficheEvent.fxml"));
                //FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("ProfilManager.fxml"));          
                 FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("magasin.fxml"));
>>>>>>> a8f1c266bc59f9549703b1752ccc80e15088f268

                    Parent root = (Parent) fxmlLoader.load();
                    Stage secondStage = new Stage();
                    secondStage.setScene(new Scene(root));
                    Stage stage = (Stage) cin.getScene().getWindow();
                    secondStage.setMaximized(true);
                    // do what you have to do
                    stage.hide();
                    secondStage.show();
                     } catch (IOException ex) {
                            Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
                        }}
                     else if (z.equals(dres))
                         
                {
                     try{
                    Session.setLoggedInUser(result);
                    // TODO: Proceed to other page
                    
                    FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("AccueilDresseur.fxml"));
                    Parent root = (Parent) fxmlLoader.load();
                    Stage secondStage = new Stage();
                    secondStage.setScene(new Scene(root));
                    Stage stage = (Stage) cin.getScene().getWindow();
                    // do what you have to do
                    stage.hide();
                    secondStage.show();
                     } catch (IOException ex) {
                            Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
                        }}
                   else if (z.equals(pet))
                         
                {
                     try{
                    Session.setLoggedInUser(result);
                    // TODO: Proceed to other page
                    
                    FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("AccueilPetSitter.fxml"));
                    Parent root = (Parent) fxmlLoader.load();
                    Stage secondStage = new Stage();
                    secondStage.setScene(new Scene(root));
                    Stage stage = (Stage) cin.getScene().getWindow();
                    // do what you have to do
                    stage.hide();
                    secondStage.show();
                     } catch (IOException ex) {
                            Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
                        }}
//              
                
                }else{ lb.setText("Tu n'es pas approuve encore");
          lb.setVisible(true);}
                 }}

    @FXML
    private void goToRegister(ActionEvent event) throws IOException {

            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("Inscription.fxml"));
            Parent root = (Parent) fxmlLoader.load();
            Stage secondStage = new Stage();
            secondStage.setScene(new Scene(root));
            Stage stage = (Stage) cin.getScene().getWindow();
            // do what you have to do
            stage.hide();
            secondStage.show();
        
    }}
