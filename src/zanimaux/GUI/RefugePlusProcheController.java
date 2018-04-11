/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package zanimaux.GUI;

import com.lynden.gmapsfx.GoogleMapView;
import com.lynden.gmapsfx.MapComponentInitializedListener;
import com.lynden.gmapsfx.javascript.object.Animation;
import com.lynden.gmapsfx.javascript.object.GoogleMap;
import com.lynden.gmapsfx.javascript.object.InfoWindow;
import com.lynden.gmapsfx.javascript.object.InfoWindowOptions;
import com.lynden.gmapsfx.javascript.object.LatLong;
import com.lynden.gmapsfx.javascript.object.MapOptions;
import com.lynden.gmapsfx.javascript.object.MapTypeIdEnum;
import com.lynden.gmapsfx.javascript.object.Marker;
import com.lynden.gmapsfx.javascript.object.MarkerOptions;
import com.lynden.gmapsfx.service.geocoding.GeocoderStatus;
import com.lynden.gmapsfx.service.geocoding.GeocodingResult;
import com.lynden.gmapsfx.service.geocoding.GeocodingService;
import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.Set;
import java.util.TreeSet;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import zanimaux.Service.RefugeService;
import zanimaux.entities.Refuge;
import zanimaux.entities.User;
import zanimaux.util.Session;

/**
 * FXML Controller class
 *
 * @author Azza
 */
public class RefugePlusProcheController implements Initializable ,MapComponentInitializedListener{

    @FXML
    private GoogleMapView mapView;
    @FXML
    private Button retour;
    @FXML
    private TextField addressTextField;
    private GoogleMap map;
    private StringProperty address = new SimpleStringProperty();
    ArrayList<LatLong> markers = new ArrayList(); 
   
   

    ArrayList<String> listAdresses=new ArrayList();
    private static LatLong latLonfProche;
    private static String re;
    
    /**
     * Initializes the controller class.
     */
            
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
         addressTextField.setVisible(false);
         mapView.addMapInializedListener(this);
       
        // TODO
    }    

    @FXML
    private void Retour(ActionEvent event) {
        try{System.out.println(markers.size());
            Stage stage=(Stage) retour.getScene().getWindow(); 
        stage.setTitle("GoogleMaps");
        Parent root = FXMLLoader.load(getClass().getResource("RefugeClient.fxml"));
        Stage secondStage = new Stage();
                    secondStage.setScene(new Scene(root));
                    stage.hide();
                    secondStage.show();
        } catch (IOException ex) {
           Logger.getLogger(accueilOumaimaController.class.getName()).log(Level.SEVERE, null, ex);
       }
    }

    @Override
    public void mapInitialized() {
        try {
            GeocodingService geocodingService = new GeocodingService();
            GeocodingService geocodingService2 = new GeocodingService();
            MapOptions mapOptions = new MapOptions();
            
            mapOptions.center(new LatLong(36.81897, 10.16579))
                    .mapType(MapTypeIdEnum.ROADMAP)
                    .overviewMapControl(true)
                    .panControl(false)
                    .rotateControl(false)
                    .scaleControl(false)
                    .streetViewControl(false)
                    .zoomControl(true)
                    .zoom(10);
            
            map = mapView.createMap(mapOptions);
            //Geocoder
             int i = 0;
          
            RefugeService rs= new RefugeService();
            RefugeService rs2= new RefugeService();
            Refuge refuge=new Refuge();
            ResultSet r =rs.AfficherTousRefuge();
              
            while(r.next()){
               
                i++;
                 refuge.setImmatriculation(r.getString("immatriculation"));
                refuge.setNomRefuge(r.getString("nomRefuge"));
                refuge.setAdresseRefuge(r.getString("adresseRefuge"));
                refuge.setCodePostaleRefuge(r.getInt("codePostaleRefuge"));
                refuge.setGouvernementRefuge(r.getString("gouvernementRefuge"));
            MarkerOptions markerOptions = new MarkerOptions();
            MarkerOptions markerOptionsUser = new MarkerOptions();
           listAdresses.add(refuge.getAdresseRefuge());
             
            geocodingService.geocode(refuge.getAdresseRefuge()+" "+refuge.getGouvernementRefuge(), (GeocodingResult[] results, GeocoderStatus status) -> {
                
                LatLong latLong = null;
               
                if( status == GeocoderStatus.ZERO_RESULTS) {
                    Alert alert = new Alert(Alert.AlertType.ERROR, "No matching address found");
                    alert.show();
                    return;
                } else if( results.length > 1 ) {
                    Alert alert = new Alert(Alert.AlertType.WARNING, "Multiple results found, showing the first one.");
                    alert.show();
                    latLong = new LatLong(results[0].getGeometry().getLocation().getLatitude(), results[0].getGeometry().getLocation().getLongitude());
                } else {
                    latLong = new LatLong(results[0].getGeometry().getLocation().getLatitude(), results[0].getGeometry().getLocation().getLongitude());
                    
                }
                 
                 markers.add(latLong);
                 

                
                //get Lat et Long de user
                 User user=Session.getLoggedInUser();
                 geocodingService2.geocode(user.getAdresse()+" "+user.getVille(), (GeocodingResult[] results2, GeocoderStatus status2) -> {
                
                
                                      
                  
                        LatLong latLong2 = null;
                        
                        if( status2 == GeocoderStatus.ZERO_RESULTS) {
                            Alert alert = new Alert(Alert.AlertType.ERROR, "No matching address found");
                            alert.show();
                            return;
                        } else if( results2.length > 1 ) {
                            Alert alert = new Alert(Alert.AlertType.WARNING, "Multiple results found, showing the first one.");
                            alert.show();
                            latLong2 = new LatLong(results2[0].getGeometry().getLocation().getLatitude(), results2[0].getGeometry().getLocation().getLongitude());
                        } else {
                            latLong2 = new LatLong(results2[0].getGeometry().getLocation().getLatitude(), results2[0].getGeometry().getLocation().getLongitude());
                            
                        }
                        
                        LatLong latLonfProche = find_closest_marker(latLong2.getLatitude(),latLong2.getLongitude());
                        System.out.println(latLonfProche.toString());
                        int k =markers.indexOf(latLonfProche);
                        //Add the refuge marker to the map
                        map.setCenter(latLonfProche);
                        
                        markerOptions.position( latLonfProche )
                                .visible(Boolean.TRUE)
                                .title("le plus proche refuge")
                                ;
                        
                        Marker marker = new Marker( markerOptions );
                        
                        map.addMarker(marker);
                        //nom de refuge le plus proche
                          try {
                        String adr = listAdresses.get(k);
                        
                    
                         re = rs.NomRefugeByAddress(adr);
                        
                        
                        
                        //distance en Kilometre
                        double distance = distance(latLong2.getLatitude(),latLong2.getLongitude(), latLonfProche.getLatitude(),latLonfProche.getLongitude());
                        InfoWindowOptions infoWindowOptions = new InfoWindowOptions();
                        infoWindowOptions.content("<h2>Nom Refuge:"+re+"</h2>"
                                + "<b>Distance</b> entre vous et le refuge:<br> <b>"
                                + distance+"</b> kilomètres" );
                        
                        InfoWindow RefugeInfoWindow = new InfoWindow(infoWindowOptions);
                        RefugeInfoWindow.open(map, marker);
                        //add marker and info window to the user place
                        
                        markerOptionsUser.position( latLong2 )
                                .visible(Boolean.TRUE)
                                .title("Votre position "+user.getPrenom()+" "+user.getNom())
                                  
                                .icon("http://maps.google.com/mapfiles/ms/icons/green-dot.png");
                        
                        Marker markeruser = new Marker( markerOptionsUser );
                        markeruser.setAnimation(Animation.BOUNCE);
                        
                        map.addMarker(markeruser);
                    } catch (SQLException ex) {
                        Logger.getLogger(RefugePlusProcheController.class.getName()).log(Level.SEVERE, null, ex);
                    }
                   
                    
                 
        
        
            }); 
                 
                
          
                 
                
            }); 
            
              
          
            
        }
            
        }catch (SQLException ex) {
            Logger.getLogger(RefugePlusProcheController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
    
    public LatLong find_closest_marker(double lat1,double lon1 ) {    
    double pi = Math.PI;
    long R = 6371; //equatorial radius
    ArrayList<Double> distances = new ArrayList();
    int closest = -1;

    for( int i=0;i<markers.size(); i++ ) {  
        double lat2 = markers.get(i).getLatitude();
       double lon2 = markers.get(i).getLongitude();

        double chLat = lat2-lat1;
        double chLon = lon2-lon1;

        double dLat = chLat*(pi/180);
        double dLon = chLon*(pi/180);

        double rLat1 = lat1*(pi/180);
        double rLat2 = lat2*(pi/180);

        double a = Math.sin(dLat/2) * Math.sin(dLat/2) + 
                    Math.sin(dLon/2) * Math.sin(dLon/2) * Math.cos(rLat1) * Math.cos(rLat2); 
        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1-a)); 
        double d = R * c;

        distances.add(i,d);
        if ( (closest == -1) || (d < distances.get(closest)) ) {
            closest = i;
        } 
    }

    // (debug) The closest marker is:
        System.out.println(markers.get(closest));
        
        return(markers.get(closest));
}
    public double distance(double lat1, double lng1,double lat2,double lng2) {
        double radlat1 = Math.PI * lat1 / 180;
        double radlat2 = Math.PI * lat2 / 180;
        double radlon1 = Math.PI * lng1 / 180;
        double radlon2 = Math.PI * lng2 / 180;
        double theta = lng1 - lng2;
        double radtheta = Math.PI * theta / 180;
        double dist = Math.sin(radlat1) * Math.sin(radlat2) + Math.cos(radlat1) * Math.cos(radlat2) * Math.cos(radtheta);
        dist = Math.acos(dist);
        dist = dist * 180 / Math.PI;
        dist = dist * 60 * 1.1515;

        //Get in in kilometers
        dist = dist * 1.609344;

        return dist;
    }

}
