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
import zanimaux.entities.ContenuPanier;
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
    public boolean modifContenuPanier(String cin, ContenuPanier cp)
    {
        String requete="UPDATE ContenuPanier SET quantite=?, commande=?, dateCommande=? where `cin`="+cin;
        try {
            PreparedStatement pst =con.prepareStatement(requete);
            pst.setInt(1,cp.getQuantite());
            pst.setInt(2,cp.getCommande());
            pst.setDate(2,cp.getDateCommande());            
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
    public ContenuPanier rechercheProduitContenuPanier(String cin, Produit p)
    {
                ContenuPanier listForm = new ContenuPanier();
        try {  
            String requete = "SELECT * FROM ContenuPanier WHERE `cin`="+cin+"AND `idProduit`="+p.getIdProduit()+"`commande`=0";
            ResultSet rs = ste.executeQuery(requete);
             while(rs.next()){
                 listForm.setCin(cin);
                 listForm.setCommande(rs.getInt("commande"));
                 listForm.setIdContenuPanier(rs.getInt("idContenuPanier"));
                 listForm.setQuantite(rs.getInt("quntite"));
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
            this.modifPanier("09625522", panier);
        }
        ContenuPanier cp= this.rechercheProduitContenuPanier("0962552", p);
        if(cp==null)
        {
            
            String requete = "INSERT INTO `ContenuPanier` (`cin`, `quantite`, `idProduit`, `commande`) VALUES (?,?,?,?)";
       
       try {
            PreparedStatement pst =con.prepareStatement(requete);
            pst.setString(1,"09625522");
            pst.setInt(2,1);
            pst.setInt(3, p.getIdProduit());
            pst.setInt(4, 0);
            pst.executeUpdate();
            System.out.println("ajout reussit");
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
        }
        else
        {
            cp.setQuantite(cp.getQuantite()+1);
            this.modifContenuPanier("09625522", cp);
        }
        
        
    }
    
}
