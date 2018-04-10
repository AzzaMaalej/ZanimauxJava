/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package zanimaux.GUI;

import com.calendarfx.model.Calendar;
import com.calendarfx.model.CalendarSource;
import com.calendarfx.view.CalendarView;
import java.io.IOException;
import static java.lang.Thread.sleep;
import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import zanimaux.Service.CalendarService;
import zanimaux.entities.User;
import zanimaux.util.Session;

/**
 * FXML Controller class
 *
 * @author Mariam
 */
public class VetDashboardController implements Initializable {
    @FXML
    private Button GestRef;
    @FXML
    private Button GestAnim;
    @FXML
    private Button btnprofil;
    @FXML
    private Button btnClient;
    @FXML
    private Label LogOut;
 User u= Session.getLoggedInUser();
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
         
        //userName.setText(u.getUsername());
        // TODO
    }    

    @FXML
    private void GestArticles(ActionEvent event) {
         try {
            
            Stage stage=(Stage) btnprofil.getScene().getWindow(); 
        stage.setTitle("Profil");
        Parent root = FXMLLoader.load(getClass().getResource("ajoutArticle.fxml"));
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
        } catch (IOException ex) {
           Logger.getLogger(RefugeDashboardController.class.getName()).log(Level.SEVERE, null, ex);
       }
    }

    @FXML
    private void GestDispo(ActionEvent event) throws SQLException {
//         try {
//             
//         Stage stage=(Stage) btnprofil.getScene().getWindow(); 
//        stage.setTitle("Profil");
//        Parent root = FXMLLoader.load(getClass().getResource("Calendar.fxml"));
//        Scene scene = new Scene(root);
//        stage.setScene(scene);
//        stage.show();
//        } catch (IOException ex) {
//           Logger.getLogger(RefugeDashboardController.class.getName()).log(Level.SEVERE, null, ex);
//       }
          System.out.println("ok1");
//        CalendarView calendarView = new CalendarView(); 
//System.out.println("ok2");
//                Calendar VetCalendar = new Calendar("VetCalendar"); 
//              
//System.out.println("ok3");
//               
//                CalendarSource myCalendarSource = new CalendarSource("My Calendars"); 
//                myCalendarSource.getCalendars().addAll(VetCalendar);
//System.out.println("ok4");
//        
//                calendarView.getCalendars().addAll(VetCalendar);
          CalendarService service = new CalendarService();
          CalendarView cv = service.ajouterCal(u);
               cv.setRequestedTime(LocalTime.now());

                Thread updateTimeThread = new Thread("Calendar: Update Time Thread") {
                        @Override
                        public void run() {
                                while (true) {
                                        Platform.runLater(() -> {
                                                cv.setToday(LocalDate.now());
                                                cv.setTime(LocalTime.now());
                                        });

                                        try {
                                                // update every 10 seconds
                                                sleep(10000);
                                        } catch (InterruptedException e) {
                                                e.printStackTrace();
                                        }

                                }
                        };
                };
                System.out.println("ok6");

                updateTimeThread.setPriority(Thread.MIN_PRIORITY);
                updateTimeThread.setDaemon(true);
                updateTimeThread.start();
                Stage stage=(Stage) btnprofil.getScene().getWindow(); 

                Scene scene = new Scene(cv);
                stage.setTitle("Calendar");
                stage.setScene(scene);
                stage.setWidth(1300);
                stage.setHeight(1000);
                stage.centerOnScreen();
                stage.show();
                System.out.println("ok7");
    
    }

   

    @FXML
    private void VoirProfil(ActionEvent event) {
           try {
                         
        Stage stage=(Stage) btnprofil.getScene().getWindow(); 
        stage.setTitle("Profil");
        Parent root = FXMLLoader.load(getClass().getResource("ProfilManager.fxml"));
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
        } catch (IOException ex) {
           Logger.getLogger(accueilOumaimaController.class.getName()).log(Level.SEVERE, null, ex);
       }
    }

    @FXML
    private void VoirCoteClient(ActionEvent event) {
           try {
        Stage stage=(Stage) btnClient.getScene().getWindow(); 
        stage.setTitle("Les refuges");
        Parent root = FXMLLoader.load(getClass().getResource("Quiz.fxml"));
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
        } catch (IOException ex) {
           Logger.getLogger(RefugeDashboardController.class.getName()).log(Level.SEVERE, null, ex);
       }
    }

    @FXML
    private void Deconnexion(MouseEvent event) {
         Session.setLoggedInUser(null);
          Parent root;
             try {
                 root = FXMLLoader.load(getClass().getResource("login.fxml"));
                 Stage myWindow = (Stage) LogOut.getScene().getWindow();
                 Scene sc = new Scene(root);
                 myWindow.setScene(sc);
                 myWindow.setTitle("Login");
                 myWindow.show();
             } catch (IOException ex) {
                 Logger.getLogger(RefugeDashboardController.class.getName()).log(Level.SEVERE, null, ex);
             }
    }
    
}
