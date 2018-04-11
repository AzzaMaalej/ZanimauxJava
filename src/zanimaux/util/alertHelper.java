/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package zanimaux.util;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.Window;
/**
 *
 * @author Mariam
 */
public class alertHelper {
  

public static  void afficher(String titre,String message){
        Stage window = new Stage();
        
       window.initModality(Modality.APPLICATION_MODAL);
       window.setTitle(titre);
       window.setMinWidth(250);
       
       Label label = new Label();
       label.setText(message);
        Button ferme = new Button("Fermer");
        ferme.setOnAction(e -> window.close());
        
        VBox layout = new VBox(10);
        layout.getChildren().addAll(label,ferme);
        layout.setAlignment(Pos.CENTER);
        //Session.getIdThisUser();
        Scene scene = new Scene(layout);
        window.setScene(scene);
        window.showAndWait();
        
    }

   
}

