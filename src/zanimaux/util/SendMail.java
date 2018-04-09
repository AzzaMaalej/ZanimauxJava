/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package zanimaux.util;
import java.util.Properties;
import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.Session;
import javax.mail.Message;
import javax.mail.Transport;
import javax.mail.Authenticator;
import javax.mail.BodyPart;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.internet.InternetAddress;
import javax.mail.PasswordAuthentication;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.swing.JOptionPane;

/**
 *
 * @author macbookpro
 */
public class SendMail {
     public static void send(String to, String sub,String msg,String dest, final String user, final String pass) 
    {
        Properties props = new Properties();

        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");	
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        
        Session session = Session.getDefaultInstance(props,new Authenticator() 
        {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() 
            {
                return new PasswordAuthentication(user, pass);
            }
        });

        try 
        {
            Message message = new MimeMessage(session);
            
            message.setFrom(new InternetAddress(user));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(to));
            message.setSubject(sub);
            message.setText(msg);
            Multipart multipart = new MimeMultipart();

            // creation partie principale du message
            BodyPart messageBodyPart = new MimeBodyPart();
            
            // creation et ajout de la piece jointe
            messageBodyPart = new MimeBodyPart();
            DataSource source = new FileDataSource(dest);
            messageBodyPart.setDataHandler(new DataHandler(source));
            messageBodyPart.setFileName("facture.pdf");
            multipart.addBodyPart(messageBodyPart);

            // ajout des éléments au mail
            message.setContent(multipart);
            Transport.send(message);
            
            
            
        } catch (MessagingException e) 
        {
            
            throw new RuntimeException(e);
        }
        
    }
}
