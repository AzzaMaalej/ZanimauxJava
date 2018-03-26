/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package zanimaux.Service;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import zanimaux.Technique.DataSource;
import zanimaux.entities.Magasin;

/**
 *
 * @author macbookpro
 */
public class ProduitService {
        public Connection con = DataSource.getInstance().getCon();
    public Statement ste;
    
    public ProduitService() throws SQLException 
    {
        ste=con.createStatement();
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
}
