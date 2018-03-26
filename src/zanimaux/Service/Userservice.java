/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package zanimaux.Service;

import zanimaux.util.BCrypt;
import zanimaux.entities.User;
import zanimaux.Technique.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Azza
 */
public class Userservice {
     private static int workload = 13;

	/**
	 * This method can be used to generate a string representing an account password
	 * suitable for storing in a database. It will be an OpenBSD-style crypt(3) formatted
	 * hash string of length=60
	 * The bcrypt workload is specified in the above static variable, a value from 10 to 31.
	 * A workload of 12 is a very reasonable safe default as of 2013.
	 * This automatically handles secure 128-bit salt generation and storage within the hash.
	 * @param password_plaintext The account's plaintext password as provided during account creation,
	 *			     or when changing an account's password.
	 * @return String - a string of length 60 that is the bcrypt hashed password in crypt(3) format.
	 */
	public static String hashPassword(String password_plaintext) {
		String salt = BCrypt.gensalt(workload);
		String hashed_password = BCrypt.hashpw(password_plaintext, salt);

		return(hashed_password);
	}

	/**
	 * This method can be used to verify a computed hash from a plaintext (e.g. during a login
	 * request) with that of a stored hash from a database. The password hash from the database
	 * must be passed as the second variable.
	 * @param password_plaintext The account's plaintext password, as provided during a login request
	 * @param stored_hash The account's stored password hash, retrieved from the authorization database
	 * @return boolean - true if the password matches the password of the stored hash, false otherwise
	 */
	public static boolean checkPassword(String password_plaintext, String stored_hash) {
		boolean password_verified = false;

		if(null == stored_hash || !stored_hash.startsWith("$2y$"))
			throw new java.lang.IllegalArgumentException("Invalid hash provided for comparison");

		password_verified = BCrypt.checkpw(password_plaintext, stored_hash);

		return(password_verified);
	}

    
      public Connection con= DataSource.getInstance().getCon();

    public Statement st;

    public Userservice() throws SQLException{
         st=con.createStatement();
    }
    
    public User findUserById(int id){
        return null;
    }
    public void ajouterUser(User g) throws SQLException {
       String req="INSERT INTO fos_user (cin,username,username_canonical, email ,email_canonical ,enabled ,password,roles, nom,prenom , telephone, adresse, ville,codePostale) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
        PreparedStatement pre= con.prepareStatement(req);
        pre.setString(1, g.getCin());
        pre.setString(2, g.getUsername());
        pre.setString(3, g.getUsername_canonical());
        pre.setString(4, g.getEmail());
        pre.setString(5, g.getEmail_canonical());
        pre.setInt(6, g.getEnabled());
        pre.setString(7, hashPassword(g.getPassword()));
        pre.setString(8, g.getRoles());
        pre.setString(9, g.getNom());
        pre.setString(10, g.getPrenom());
        pre.setInt(11, g.getTelephone());
        pre.setString(12, g.getAdresse());
        pre.setString(13, g.getVille());
        
        pre.setInt(14, g.getCodePostale());
        pre.executeUpdate();   
    }
    public User UserByCin(String i)
    { 

        User listForm = new User();
        try {  
            String requete = "SELECT * FROM fos_user WHERE cin='"+i+"' ";

           
            ResultSet rs = st.executeQuery(requete);

             while(rs.next()){
                 listForm.setCin(i);
                 listForm.setUsername(rs.getString("username"));
                 listForm.setUsername_canonical(rs.getString("username_canonical"));
                 listForm.setEmail(rs.getString("email"));
                 listForm.setEmail_canonical(rs.getString("email_canonical"));
                 listForm.setEnabled(rs.getInt("enabled"));
                 listForm.setPassword(rs.getString("password"));
                 listForm.setRoles(rs.getString("roles"));
                 
                 listForm.setNom(rs.getString("nom"));
                 listForm.setPrenom(rs.getString("prenom"));
                 listForm.setTelephone(rs.getInt("telephone"));
                 listForm.setAdresse(rs.getString("adresse"));
                 listForm.setVille(rs.getString("ville"));
                 listForm.setCodePostale(rs.getInt("codePostale"));
            }
             
        } catch (SQLException ex) {
            Logger.getLogger(Articleservice.class.getName()).log(Level.SEVERE, null, ex);
        }
        return listForm;
    
    }        

}
