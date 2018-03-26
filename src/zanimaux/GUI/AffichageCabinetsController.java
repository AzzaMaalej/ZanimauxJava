/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package zanimaux.GUI;

import com.jfoenix.controls.JFXMasonryPane;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Random;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.animation.TranslateTransition;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;
import javafx.util.Duration;
import zanimaux.Service.CabinetDao;
import zanimaux.entities.Cabinet;

/**
 * FXML Controller class
 *
 * @author Mariam
 */
public class AffichageCabinetsController implements Initializable {
    @FXML
    private ScrollPane scroll;
    @FXML
    private JFXMasonryPane mypane;
    @FXML
    private Pane navpane;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
      
        try {
            panepopulize();
        } catch (MalformedURLException ex) {
            Logger.getLogger(AffichageCabinetsController.class.getName()).log(Level.SEVERE, null, ex);
        }
       

    }    

    @FXML
    private void populize(MouseEvent event) {
    }

    
    

    private void panepopulize() throws MalformedURLException {

        Random r = new Random();
        Cabinet p = new Cabinet();
        CabinetDao pd = new CabinetDao();
        for (int i = 0; i < 100; i++) {

            {
                VBox vbox = new VBox(4);
                for (Cabinet t : pd.getAllInfo(i)) {
                    Image image = new Image(new File(t.getPhotovet()).toURI().toURL().toExternalForm());

                    ImageView iv = new ImageView();
                    Label medium = new Label(t.getImmatriculeCabinet());
                    System.out.println("button achat" + medium.getText());
                    medium.setVisible(false);
                    iv.setImage(image);
                    iv.setFitHeight(200);
                    iv.setFitWidth(150);
                    Button btn = new Button("Consulter");

                    Random r1 = new Random();
                    int r2;
                    r2 = r1.nextInt(256 * 256 * 256);
                    String colorCode = String.format("#%06x", r2);

                    Label l = new Label();
                    Label l2 = new Label();
                    l2.setText(t.getAdresseCabinet());
                    Label l3 = new Label();
                    l3.setText(Integer.toString(t.getTelephoneCabinet()));
                    btn.setPrefSize(45, 20);
                    btn.setLayoutY(75);
                    btn.setLayoutX(100);

                    btn.setStyle("-postion: absolute;");
                    l.setText(t.getEmailCabinet());
                    l.setTextFill(Color.web(colorCode));
                    l2.setFont(Font.font("Verdana", FontWeight.BOLD, 18));
                    l3.setFont(Font.font("Verdana", FontWeight.BOLD, 18));
                    l.setFont(Font.font("Verdana", FontWeight.BOLD, 18));
                    Label l4 = new Label();
                    l.setTranslateX(50);
                    l2.setTranslateX(100);
                    l4.setTranslateX(0);
                    l3.setTranslateX(100);
                    Label l5 = new Label();
                    l5.setText( "Addresse"+l2.getText());
                    l4.setText( l3.getText());
                    l5.setTranslateX(0);
                    btn.setTranslateX(100);
                    btn.setOnAction(new EventHandler<ActionEvent>() {
                        @Override
                        public void handle(ActionEvent e) {
                           

//                                FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("achatdialogbox.fxml"));
//                                Parent root = (Parent) fxmlLoader.load();
//                                Stage secondStage = new Stage();
//                                secondStage.setScene(new Scene(root));
//                                AchatdialogboxController c = fxmlLoader.<AchatdialogboxController>getController();
//                                c.getidprod(t.getId_prod());
//                                System.out.println(t.getId_prod());
//
//                                secondStage.show();
                            
                        }
                    }
                    );

                    vbox.setPrefSize(150, 250);
                    vbox.setEffect(new DropShadow(30, Color.BLACK));

                    vbox.getChildren().addAll(iv, l, l5, l4, btn, medium);
                    vbox.setStyle("-fx-background-color:rgb(" + r.nextInt(255) + "," + r.nextInt(255) + "," + r.nextInt(255) + ");");

                    mypane.getChildren().add(vbox);

                }
            }

        }
    }

    @FXML
    private void gotoajout(MouseEvent event) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("FXMLDocument.fxml"));
            Parent root = (Parent) fxmlLoader.load();
            Stage secondStage = new Stage();
            secondStage.setScene(new Scene(root));
            Stage stage = (Stage) navpane.getScene().getWindow();
            // do what you have to do
            stage.hide();
            secondStage.show();
        } catch (IOException ex) {
            Logger.getLogger(AffichageCabinetsController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void gotomodif(MouseEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("FXMLDocument_page2.fxml"));
        Parent root = (Parent) fxmlLoader.load();
        Stage secondStage = new Stage();
        secondStage.setScene(new Scene(root));
         Stage stage = (Stage) navpane.getScene().getWindow();
        // do what you have to do
        stage.hide();
        secondStage.show();
    }

    @FXML
    private void goup(MouseEvent event) {

        TranslateTransition closeNav = new TranslateTransition(new Duration(350), navpane);

        closeNav.setToY(0);
        closeNav.play();

    }

    @FXML
    private void dropdown(MouseEvent event) {
        TranslateTransition closeNav = new TranslateTransition(new Duration(350), navpane);

        closeNav.setToY(35);
        closeNav.play();
    }

}

    
    
    
    
    
    
    
    
    
    
    
    
    ////////////////////////

    

