/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package zanimaux.GUI;

import java.io.IOException;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import zanimaux.Service.Userservice;
import zanimaux.entities.User; 

/**
 *
 * @author macbookpro
 */
public class Zanimaux extends Application {
    
    @Override
    public void start(Stage stage) throws IOException {

        Parent root = FXMLLoader.load(getClass().getResource("login.fxml"));        
        Scene scene = new Scene(root);
       
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
       /* try {
            User u=new User("1234567","azza3","asdaz@hotmail.fr","esprit","PROPRIETAIRE_REFUGE","maalej","azza",123456,"asd","assd",1234);
            
            
            Userservice su= new Userservice();
            su.ajouterUser(u);
            System.out.println("user ajouté");
        } catch (SQLException ex) {
            Logger.getLogger(Zanimaux.class.getName()).log(Level.SEVERE, null, ex);
        }
         */
       launch(args);
    }
    
}
