/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package zanimaux.Service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import zanimaux.Technique.DataSource;
import zanimaux.entities.Magasin;
import zanimaux.entities.Panier;
import zanimaux.entities.Produit;
import zanimaux.entities.User;

/**
 *
 * @author macbookpro
 */
public class PanierService {
        public Connection con = DataSource.getInstance().getCon();
    public Statement ste;
    
    public PanierService() throws SQLException 
    {
        ste=con.createStatement();
    }
    public boolean modifPanier(String cin, Panier p){
        String requete="UPDATE Panier SET somme=?, sommeCommande=? where `cin`="+cin;
        try {
            PreparedStatement pst =con.prepareStatement(requete);
            pst.setDouble(1,p.getSomme());
            pst.setDouble(2,p.getSommeCommande());            
            pst.executeUpdate();
            System.out.println("modification reussite");
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
            return false;
    
    }
    public Panier recherchePanier(String cin)
    {
        Panier listForm = new Panier();
        try {  
            String requete = "SELECT * FROM Panier WHERE `cin`="+cin;
            ResultSet rs = ste.executeQuery(requete);
             while(rs.next()){
                 listForm.setCin(cin);
                 listForm.setSomme(rs.getFloat("somme"));
                 listForm.setSomme(rs.getFloat("sommeCommande"));
            }
             
        } catch (SQLException ex) {
            Logger.getLogger(Articleservice.class.getName()).log(Level.SEVERE, null, ex);
        }
        return listForm;
    }
    public void ajouterProduitPanier(Produit p)
    {
        Panier panier = this.recherchePanier("09625522");
        if (panier==null)
        {
            String requete = "INSERT INTO Panier (cin,somme) VALUES (?,?) ";
       
       try {
            PreparedStatement pst =con.prepareStatement(requete);
            pst.setString(1,"09625522");
            pst.setDouble(2,p.getPrix());
            pst.executeUpdate();
            System.out.println("ajout reussit");
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
        }
        else
        {
            panier.setSomme(panier.getSomme()+p.getPrix());
        }
        
    }
    
}
