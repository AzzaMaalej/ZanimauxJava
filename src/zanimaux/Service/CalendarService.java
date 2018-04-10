/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package zanimaux.Service;

import com.calendarfx.model.Calendar;
import com.calendarfx.model.CalendarSource;
import com.calendarfx.view.CalendarView;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import javafx.collections.ObservableList;
import zanimaux.Technique.DataSource;
import zanimaux.entities.User;
import zanimaux.entities.calendrier1;

/**
 *
 * @author Mariam
 */
public class CalendarService {
     private static CalendarService instance;
      CalendarSource myCalendarSource = new CalendarSource("My Calendars");
      CalendarView calendarView = new CalendarView(); 
  
    public static CalendarService getInstance() throws SQLException {
       
         if (instance == null) {
            instance = new CalendarService();
        }
        return instance;//To change body of generated methods, choose Tools | Templates.
    }
     public Connection con = DataSource.getInstance().getCon();
     public Statement ste;
     public CalendarService() throws SQLException 
    {
        ste=con.createStatement();
    }
     
   public CalendarView ajouterCal(User u) throws SQLException {
      
       calendrier1 vetcal = new calendrier1(u.getUsername()); 
        myCalendarSource.getCalendars().add(vetcal);
            calendarView.getCalendarSources().add(myCalendarSource);
        
        
        
       return calendarView;
        
    }
    public Calendar FindCalByVet(String cin) throws SQLException {
      Userservice u = new Userservice();
      User vet = u.UserByCin(cin);
      String username = vet.getUsername();
      Calendar c=null;
      String name ="";
      for (Calendar calendar : myCalendarSource.getCalendars())
      {name = calendar.getName();
          if (name.equals(username))
      {  c = calendar;}
          }
      
      
         return c;
        
    }
     public CalendarSource getCalendarSource() throws SQLException {
     
        return myCalendarSource;
    }

   
    
}
