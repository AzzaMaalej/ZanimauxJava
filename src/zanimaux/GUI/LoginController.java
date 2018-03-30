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
import zanimaux.Service.Userservice;
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
                    
                    FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("ProfilManager.fxml"));
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
                    
                    FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("magasin.fxml"));
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
                    
                    FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("GestionRefuges.fxml"));
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
                    // TODO: Proceed to other page
                    

                   // FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("accueilOumaima.fxml"));
                  FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("afficheEvent.fxml"));


                   // FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("magasin.fxml"));

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
                     else if (z.equals(dres))
                         
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
