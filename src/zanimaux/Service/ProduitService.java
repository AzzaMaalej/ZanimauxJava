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
import zanimaux.entities.Articles;
import zanimaux.entities.Magasin;
import zanimaux.entities.Produit;
import zanimaux.entities.User;
import zanimaux.util.Session ;

/**
 *
 * @author macbookpro
 */
public class ProduitService 
{
    
    public Connection con = DataSource.getInstance().getCon();
    public Statement ste;
    
    public ProduitService() throws SQLException 
    {
        ste=con.createStatement();
    }
        public boolean ajouterProduit(Produit p) throws SQLException{
       String requete = "INSERT INTO `Produit` ( `libelle`, `photoProduit`, `marque`, `type`,`idMagasin`, `quantite`, `description`, `prix`) VALUES (?,?,?,?,?,?,?,?) ";
        Produit prod =new Produit();
    
       try {
           
            PreparedStatement pst =con.prepareStatement(requete);
            pst.setString(1, p.getLibelle());
            pst.setString(2,p.getPhotoProduit());
            pst.setString(3,p.getMarque());
            pst.setString(4,p.getType());
            pst.setInt(5,p.getIdMagasin());
            pst.setInt(6,p.getQuantite());
            pst.setString(7,p.getDescription());
            pst.setDouble(8,p.getPrix());
           
             pst.executeUpdate();
            System.out.println("ajout reussi");
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
            return false;
    }
    
    public ResultSet rechercheProduits(int i)
    { 

        ResultSet rs=null;
        try {  
            String requete = "SELECT * FROM Produit WHERE `idMagasin`="+i;
            rs = ste.executeQuery(requete);
             }catch (SQLException ex) {
                 System.out.println(" erreur rechercheProduit(i)");
        }
        return rs ;
    
    }
        public List<Produit> rechercheProduitsMagasin(int i)
    { 

        
        List<Produit> listProduits = new ArrayList<>();
        try {
           
            String requete = "SELECT * FROM Produit WHERE `idMagasin`="+i;
            ResultSet rs = ste.executeQuery(requete);
            while(rs.next()){
                Produit p = new Produit();
               p.setDescription(rs.getString("description") );
               p.setIdMagasin(i);
               p.setIdProduit(rs.getInt("idProduit"));
               p.setLibelle(rs.getString("libelle"));
               p.setMarque(rs.getString("marque"));
               p.setNbFoisVendu(rs.getInt("nbFoisVendu"));
               p.setPhotoProduit(rs.getString("photoProduit"));
               p.setPrix(rs.getDouble("prix"));
               p.setQuantite(rs.getInt("quantite"));
               p.setType(rs.getString("type"));
               listProduits.add(p);
                
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(Articleservice.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
        return listProduits;
    

    
    }
        
         public boolean ModifProduit(Produit p,int id)
    {
        int nbr_ligne;
        try{
            String requete="UPDATE Produit SET `libelle` = ?, `marque` =?, `type` = ?, `nbFoisVendu` = ?, `quantite` =?, `description` =?, `prix` =?, `photoProduit`=? where `idProduit`="+id;
;
            PreparedStatement pst = con.prepareStatement(requete);
            pst.setString(1,p.getLibelle());
            pst.setString(2,p.getMarque());
            pst.setString(3,p.getType());
            pst.setInt(4,p.getNbFoisVendu());
            pst.setInt(5,p.getQuantite());
            pst.setString(6,p.getDescription());
            pst.setDouble(7,p.getPrix());
            pst.setString(8,p.getPhotoProduit());
            nbr_ligne=pst.executeUpdate();
        }
        catch (SQLException ex) {
            Logger.getLogger(Articleservice.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
        if(nbr_ligne == 0){
            return false;
        }else{
            return true;
        }
        
    }
    public void SupprimerProduit(int id)
    {
        String requete="DELETE FROM Produit WHERE idProduit='"+id+"' ";     
        Statement st;
        try {
            st = con.createStatement(); 
            st.executeUpdate(requete);
      System.out.println("produit supprimé");

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
}
}
