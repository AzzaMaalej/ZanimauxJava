/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package zanimaux.GUI;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.media.AudioSpectrumListener;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;
import zanimaux.entities.HWCalculator;
import zanimaux.util.Validation;

/**
 * FXML Controller class
 *
 * @author Mariam
 */
public class HWresultController implements Initializable {
    @FXML
    private Button button;
    @FXML
    private Button buttonRefuge;
    @FXML
    private Button evenement;
    @FXML
    private Button userName;
    @FXML
    private Pane pane;
    @FXML
    private Button btn11;
    @FXML
    private Button btn1;
    @FXML
    private ImageView play11;
    @FXML
    private AnchorPane apdeb;
    @FXML
    private Label lb471;
    @FXML
    private Pane pane1;
    @FXML
    private TextField nom;
    @FXML
    private TextField poid;
    @FXML
    private ChoiceBox type;
    @FXML
    private ChoiceBox st;
    @FXML
    private ChoiceBox nivact;
    @FXML
    private Button valider;
    @FXML
    private Label lb;
    @FXML
    private Label lbcal;
    @FXML
    private ImageView imacc;
    @FXML
    private AnchorPane apqst;
    @FXML
    private Pane pvid11;
    @FXML
    private Pane pane211;
    @FXML
    private Label lb111;
    @FXML
    private CheckBox abdoChoice1;
    @FXML
    private CheckBox abdoChoice2;
    @FXML
    private CheckBox abdoChoice3;
    @FXML
    private ImageView stop11;
    @FXML
    private ImageView play12;
    @FXML
    private Pane pvid;
    @FXML
    private Pane pane2;
    @FXML
    private Label lb1;
    @FXML
    private CheckBox coteChoice1;
    @FXML
    private CheckBox coteChoice2;
    @FXML
    private CheckBox coteChoice3;
    @FXML
    private ImageView play;
    @FXML
    private ImageView stop;
    @FXML
    private Pane pvid1;
    @FXML
    private Pane pane21;
    @FXML
    private Label lb11;
    @FXML
    private CheckBox poidChoice1;
    @FXML
    private CheckBox poidChoice2;
    @FXML
    private CheckBox poidChoice3;
    @FXML
    private Button Result;
    @FXML
    private ImageView play1;
    @FXML
    private ImageView stop1;
    @FXML
    private Label lbqst;
    @FXML
    private AnchorPane apres;
    @FXML
    private Label evaluation;
    @FXML
    private ImageView photoanimal;
    @FXML
    private ImageView scale;
    @FXML
    private ImageView scale2;
    @FXML
    private TextArea text1;
    @FXML
    private TextArea text2;
    @FXML
    private TextArea text3;

    private  MediaPlayer player ;

     int typeanimal;
    int stérilisé;
    int niveau;
    int abdo;
    int cote;
    int poid2;
    HWCalculator Hwentity = new HWCalculator();
  

//String content_Url = "<iframe width=\"700\" height=\"500\" src=\"https://www.youtube.com/embed/puAuDt6aNsw\" frameborder=\"0\" allowfullscreen></iframe>";
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
//        ObservableList<String> cursors = FXCollections.observableArrayList("chien","chat"); 
//         type.setItems(cursors);

        //WebEngine webEngine = wv.getEngine();
        //webEngine.loadContent(content_Url);
      

        type.setItems(FXCollections.observableArrayList("Chien", "Chat"));
        st.setItems(FXCollections.observableArrayList("Oui", "Non","Pas sure"));
        nivact.setItems(FXCollections.observableArrayList("Faible", "Moyen","Elevé"));  
//            pane1.setVisible(true);
//       lb471.setVisible(true);
//       lbcal.setVisible(true);
//       imacc.setVisible(true);
        apdeb.setVisible(true);
        // TODO
    }

    @FXML
    private void MagasinButtonAction(ActionEvent event) {
    }

    @FXML
    private void AfficherRefugeAction(ActionEvent event) {
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

    @FXML
    private void profil(ActionEvent event) {
    }
   
    
   

    @FXML
    private void valider1(ActionEvent event) {
        if (type.getSelectionModel().getSelectedItem().toString().equalsIgnoreCase("chien")) {
            typeanimal = 2;
        } else if (type.getSelectionModel().getSelectedItem().toString().equalsIgnoreCase("chat")) {
            typeanimal = 1;
        } else {
            lb.setText("vous devez choisir tous les lignes");
        }

        if (st.getSelectionModel().getSelectedItem().toString().equalsIgnoreCase("Oui")) {
            stérilisé = 1;
        } else if (st.getSelectionModel().getSelectedItem().toString().equalsIgnoreCase("Non")) {
            stérilisé = 0;

        } else if (st.getSelectionModel().getSelectedItem().toString().equalsIgnoreCase("Pas sure")) {
            stérilisé = 2;
        } else {
            lb.setText("vous devez choisir tous les lignes");
        }

        if (nivact.getSelectionModel().getSelectedItem().toString().equalsIgnoreCase("Faible")) {
            niveau = 0;
        } else if (nivact.getSelectionModel().getSelectedItem().toString().equalsIgnoreCase("Moyen")) {
            niveau = 1;

        } else if (nivact.getSelectionModel().getSelectedItem().toString().equalsIgnoreCase("Elevé")) {
            niveau = 2;
        } else {
            lb.setText("vous devez choisir tous les lignes");
        }
        if (nom.getText() == "") {
            lb.setText("vous devez remplir tous les lignes");
        }
        Validation.texNum(poid, lb, "Erreur");

////        HWCalculator Hwentity = new HWCalculator();
        Hwentity.setNomanimal(nom.getText());
        Hwentity.setNeutred(stérilisé);
        Hwentity.setTypeanimal(typeanimal);
        Hwentity.setNiveauactivite(niveau);

//        pane1.setVisible(false);
//        lb471.setVisible(false);
//        lbcal.setVisible(false);
//        imacc.setVisible(false);
        apdeb.setVisible(false);
                
        apqst.setVisible(true);
        
        

         Media media;
        media = new Media("file:///C:/video1.mp4");
        player= new MediaPlayer(media);
        MediaView mv = new MediaView();
        mv.setMediaPlayer(player);

        
        pvid.getChildren().add(mv);

        VBox vbox = new VBox();
        vbox.setVisible(false);
        pvid.getChildren().add(vbox);
        Slider slider = new Slider();
        vbox.getChildren().add(slider);
        final HBox hbox = new HBox();

        vbox.getChildren().add(hbox);
        final int bands = player.getAudioSpectrumNumBands();
        final Rectangle[] rects = new Rectangle[bands];
        for (int i = 0; i < rects.length; i++) {
            rects[i] = new Rectangle();
            rects[i].setFill(Color.GREENYELLOW);
            hbox.getChildren().add(rects[i]);

        }

//        player.play();
        player.setOnReady(new Runnable() {

            @Override
            public void run() {
                int w = player.getMedia().getWidth();
                int h = player.getMedia().getHeight();

                hbox.setMinWidth(w);
                int bandwidth = w / rects.length;
                for (Rectangle r : rects) {
                    r.setWidth(bandwidth);
                    r.setHeight(2);
                }
                pvid.setMinHeight(h);
                pvid.setMinHeight(h);
//                mv.setFitHeight(h);
//                mv.setFitWidth(w);
                vbox.setMinSize(w, 25);
                vbox.setTranslateY(h - 25);
                slider.setMin(0.0);
                slider.setValue(0.0);
                slider.setMax(player.getTotalDuration().toSeconds());

            }
        });
        player.currentTimeProperty().addListener(new ChangeListener<Duration>() {

            @Override
            public void changed(ObservableValue<? extends Duration> observablevalue, Duration duration, Duration current) {
                slider.setValue(current.toSeconds());
            }
        });
        slider.setOnMouseClicked(new EventHandler<MouseEvent>() {

            @Override
            public void handle(MouseEvent event) {
                player.seek(Duration.seconds(slider.getValue()));
            }
        });
        player.setAudioSpectrumListener(new AudioSpectrumListener() {

            @Override
            public void spectrumDataUpdate(double v, double vl, float[] mags, float[] floats1) {
                for (int i = 0; i < rects.length; i++) {
                    double h = mags[i] + 60;
                    if (h > 2) {
                        rects[i].setHeight(h);
                    }
                }
            }
        });
    }
    @FXML
    private void play(MouseEvent event) {
        player.play();
    }

   

    @FXML
    private void stop(MouseEvent event) {
        player.pause();
    }
    
    @FXML
    private void abdo1(ActionEvent event) {
         abdoChoice2.setSelected(false);
            abdoChoice3.setSelected(false);
    }

    @FXML
    private void abdo2(ActionEvent event) {
          abdoChoice1.setSelected(false);
            abdoChoice3.setSelected(false);
    }

    @FXML
    private void abdo3(ActionEvent event) {
          abdoChoice2.setSelected(false);
            abdoChoice1.setSelected(false);
    }

    @FXML
    private void cote1(ActionEvent event) {
        coteChoice2.setSelected(false);
            abdoChoice3.setSelected(false);
    }

    @FXML
    private void cote2(ActionEvent event) {
        coteChoice1.setSelected(false);
            coteChoice3.setSelected(false);
    }

    @FXML
    private void cote3(ActionEvent event) {
        coteChoice2.setSelected(false);
            coteChoice1.setSelected(false);
    }

    @FXML
    private void poid1(ActionEvent event) {
          poidChoice2.setSelected(false);
            poidChoice3.setSelected(false);
    }

    @FXML
    private void poid2(ActionEvent event) {
         poidChoice1.setSelected(false);
            poidChoice3.setSelected(false);
        
    }

    @FXML
    private void poid3(ActionEvent event) {
        poidChoice1.setSelected(false);
            poidChoice2.setSelected(false);
    }

    @FXML
    private void Result(ActionEvent event) {
        if(poidChoice1.isSelected()){
           poid2=1;
        }else if(poidChoice2.isSelected()){
            poid2=2;
        }else if(poidChoice3.isSelected()){
            poid2=3;
        }else
        {    lbqst.setText("vous devez choisir tous les lignes");
          lbqst.setVisible(true);}
        
         if(coteChoice1.isSelected()){
           cote=1;
        }else if(coteChoice2.isSelected()){
           cote=2;
        }else if(coteChoice3.isSelected()){
            cote=3;
        }else
        { lbqst.setText("vous devez choisir tous les lignes");
         lbqst.setVisible(true);}
           if(abdoChoice1.isSelected()){
           abdo=1;
        }else if(abdoChoice2.isSelected()){
           abdo=2;
        }else if(abdoChoice3.isSelected()){
            abdo=3;
        }else
        {lbqst.setText("vous devez choisir tous les lignes");
            lbqst.setVisible(true);}
           apqst.setVisible(false);
            apres.setVisible(true);
           evaluation.setText(Hwentity.getNomanimal());
          int indice= cote+ abdo + poid2;
           if((abdo == 2 ) && (cote == 2 )&&(poid2 == 2)){
//animal parfait
            if(typeanimal == 1){
                Image image= new Image("zanimaux/ImageUtile/cat/cat_bcs_5.jpg") ;
              photoanimal.setImage(image); 
            }
            else{  Image image= new Image("zanimaux/ImageUtile/dog/dog_bcs_5.jpg") ;
            photoanimal.setImage(image); 
               
            }
           Image image1= new Image("zanimaux/ImageUtile/scale/scale_5.png");
             Image image2= new Image("zanimaux/ImageUtile/scale/result_5.png");
            scale.setImage(image1); 
               scale2.setImage(image2);
           text2.setVisible(true);
//            $('#divDescr3').hide();
//            $('#divDescr1').show();
//            $('#divDescr2').hide();
        }
        if((abdo == 1 ) && (cote == 1 )&&(poid2 == 1)){
//animal tres maigre
            if(typeanimal == 1){
                Image image= new Image("zanimaux/ImageUtile/cat/cat_bcs_1.jpg") ;
              photoanimal.setImage(image); 
            }
            else{  Image image= new Image("zanimaux/ImageUtile/dog/dog_bcs_1.jpg") ;
            photoanimal.setImage(image); 
               
            }
           Image image1= new Image("zanimaux/ImageUtile/scale/scale_1.png");
             Image image2= new Image("zanimaux/ImageUtile/scale/result_1.png");
            scale.setImage(image1); 
               scale2.setImage(image2);
           text1.setVisible(true);
//            $('#divDescr3').hide();
//            $('#divDescr1').show();
//            $('#divDescr2').hide();
        }
        else if(indice == 9){ // tres obese
            if(typeanimal == 1){
               
                 Image image= new Image("zanimaux/ImageUtile/cat/cat_bcs_9.jpg") ;
              photoanimal.setImage(image); 
            }
            else{Image image= new Image("zanimaux/ImageUtile/dog/dog_bcs_9.jpg") ;
              photoanimal.setImage(image); 
               
            }
             Image image1= new Image("zanimaux/ImageUtile/scale/scale_9.png");
             Image image2= new Image("zanimaux/ImageUtile/scale/result_9.png");
            scale.setImage(image1); 
               scale2.setImage(image2);
               

//            $("#imgScale").attr("src","{{ asset('img/scale/scale_9.png') }}");
//            $("#imgResultScale").attr("src","{{ asset('img/scale/result_9.png') }}");
//            $('#divDescr1').hide();
//            $('#divDescr3').show();
//            $('#divDescr2').hide();
                 text3.setVisible(true);

        }
        else if(indice > 3 && indice < 6){// animal maigre
            if(typeanimal == 1){
                
                 Image image= new Image("zanimaux/ImageUtile/cat/cat_bcs_3.jpg") ;
              photoanimal.setImage(image); 
            }
            else{
                Image image= new Image("zanimaux/ImageUtile/dog/dog_bcs_3.jpg") ;
              photoanimal.setImage(image); 
            }
             Image image1= new Image("zanimaux/ImageUtile/scale/scale_4.png");
             Image image2= new Image("zanimaux/ImageUtile/scale/result_3.png");
            scale.setImage(image1); 
               scale2.setImage(image2);
                 text1.setVisible(true);
           
        }
        else if(indice > 6 && indice < 9){// surpoid
            if(typeanimal == 1){
               
                 Image image= new Image("zanimaux/ImageUtile/cat/cat_bcs_7.jpg") ;
              photoanimal.setImage(image); 
            }
            else{
                Image image= new Image("zanimaux/ImageUtile/dog/dog_bcs_7.jpg") ;
              photoanimal.setImage(image); 
            }
           Image image1= new Image("zanimaux/ImageUtile/scale/scale_7.png");
             Image image2= new Image("zanimaux/ImageUtile/scale/result_7.png");
            scale.setImage(image1); 
               scale2.setImage(image2);
               text3.setVisible(true);
         
    }
        
    }

    @FXML
    private void hidePane(MouseEvent event) {
    }
    

}
