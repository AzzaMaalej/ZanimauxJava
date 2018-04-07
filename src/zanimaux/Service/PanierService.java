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
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import zanimaux.Technique.DataSource;
import zanimaux.entities.ContenuPanier;
import zanimaux.entities.Magasin;
import zanimaux.entities.Panier;
import zanimaux.entities.Produit;
import zanimaux.entities.User;
import zanimaux.util.Session;

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
            System.out.println("modification panier reussite");
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
            pst.setDate(3,cp.getDateCommande());            
            pst.executeUpdate();
            System.out.println("modification contenu panier reussite");
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
            if (rs.next() == false) 
            listForm = null;
            else { 

             do{
                 listForm.setCin(cin);
                 listForm.setSomme(rs.getFloat("somme"));
                 //listForm.setSomme(rs.getFloat("sommeCommande"));
            }while(rs.next());
             
        }} catch (SQLException ex) {
            Logger.getLogger(Articleservice.class.getName()).log(Level.SEVERE, null, ex);
        }
        return listForm;
    }
    public ContenuPanier rechercheProduitContenuPanier(String cin, Produit p)
    {
        ContenuPanier listForm = new ContenuPanier();
        
        try {  
            String requete = "SELECT * FROM ContenuPanier WHERE `cin`="+cin+" AND `idProduit`="+p.getIdProduit()+" AND `commande`=0";
            ResultSet rs = ste.executeQuery(requete);
            if (rs.next() == false) 
            listForm = null;
            else { 

             do{
                 listForm.setCin(cin);
                 listForm.setCommande(rs.getInt("commande"));
                 listForm.setIdContenuPanier(rs.getInt("idContenuPanier"));
                 listForm.setQuantite(rs.getInt("quantite"));
            }while(rs.next());
             
        }} catch (SQLException ex) {
            Logger.getLogger(Articleservice.class.getName()).log(Level.SEVERE, null, ex);
        }
        return listForm;
        
    }
    public void ajouterProduitPanier(Produit p) throws SQLException
    {
        User u = Session.getLoggedInUser();
        Panier panier = this.recherchePanier(u.getCin());
        //on teste si le client n'a pas encore ajouter un produit au panier, donc on crée le panier

        if (panier==null)
        {
            String requete = "INSERT INTO Panier (cin,somme) VALUES (?,?) ";
       
       try {
            PreparedStatement pst =con.prepareStatement(requete);
            pst.setString(1,u.getCin());
            pst.setDouble(2,p.getPrix());
            pst.executeUpdate();
            System.out.println("ajout panier reussit");
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
        }
       //si le client a deja ajouter un produit au panier, donc on maj juste la somme de ses achats
        else
        {
            panier.setSomme(panier.getSomme()+p.getPrix());
            this.modifPanier(u.getCin(), panier);
        }
        ContenuPanier cp= this.rechercheProduitContenuPanier(u.getCin(), p);
       //on teste si le client n'a pas encore ajouter le produit X au panier, donc on insere

        if(cp==null)
        {
            
            String requete = "INSERT INTO `ContenuPanier` (`cin`, `quantite`, `idProduit`, `commande`) VALUES (?,?,?,?)";
       
       try {
            PreparedStatement pst =con.prepareStatement(requete);
            pst.setString(1,u.getCin());
            pst.setInt(2,1);
            pst.setInt(3, p.getIdProduit());
            pst.setInt(4, 0);
            pst.executeUpdate();
            System.out.println("ajout contenu panier reussit");
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
        }
        //si le client a deja ajouter le produit X au panier, donc on MAJ juste la quantité
        else
        {
            cp.setQuantite(cp.getQuantite()+1);
            this.modifContenuPanier(u.getCin(), cp);
        }
    }
         public List<ContenuPanier> rechercheContenuPanier(String cin)
   {
        List<ContenuPanier> listContenuPanier = new ArrayList<>();
        try {
            String requete = "SELECT * FROM ContenuPanier WHERE `cin`="+cin+" AND `commande`=0";
            ResultSet rs = ste.executeQuery(requete);
            ContenuPanier listForm;
            if (rs.next() == false) 
            listForm = null;
            else { 
            do{
                 listForm = new ContenuPanier();

                 listForm.setCin(cin);
                 listForm.setIdProduit(rs.getInt("idProduit"));
                 listForm.setCommande(rs.getInt("commande"));
                 listForm.setIdContenuPanier(rs.getInt("idContenuPanier"));
                 listForm.setQuantite(rs.getInt("quantite"));
                 listContenuPanier.add(listForm);

            }while(rs.next());
                              
            }   
            
            
        } catch (SQLException ex) {
            Logger.getLogger(PanierService.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
        return listContenuPanier;
    
    }
}
