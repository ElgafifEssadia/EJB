package com.project.businesslogic;

import java.util.Properties;

import javax.ejb.Schedule;
import javax.ejb.Stateless;
import javax.ejb.Timer;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMultipart;

import com.project.businesslayer.MDB;

import java.util.logging.Logger;
import java.util.logging.Level;




@Stateless
public class SendPicture {

    /**
     * Default constructor. 
     */
    public SendPicture() {
        // TODO Auto-generated constructor stub
    }
	
	@SuppressWarnings("unused")
	@Schedule(second="*/10", minute="*", hour="8-23", dayOfWeek="Mon-Fri",
      dayOfMonth="*", month="*", year="*", info="MyTimer")
    private void scheduledTimeout(final Timer t) {
        System.out.println("@Schedule called at: " + new java.util.Date());
        
    }
	
	public void sendEmail() {
		try {
            System.out.println("--- Start Ok ");
            
            //Données de configuration pour ouvrir une session
            Properties prop = new Properties();
            prop.put("mail.smtp.host", "smtp.gmail.com");
            prop.put("mail.transport.protocol", "smtp");
            prop.put("mail.smtp.port", "587");
            prop.put("mail.smtp.auth", "true");
            prop.put("mail.smtp.starttls.enable", "true");//Sécurité
            prop.put("mail.smtp.ssl.trust", "smtp.gmail.com");
           
            Session session = Session.getInstance(prop, null);
              
            //Préparer un objet Massage
            Message courrier = (Message) new MimeMessage(session);
            
            courrier.setRecipient(Message.RecipientType.TO,new InternetAddress("s.elgafif@gmail.com"));
            ((MimeMessage) courrier).setSubject("Image");
        
            //message = image archivé
            MDB mdb = new MDB();
            //courrier.setText(mdb.onMessage(message), "utf-8");
            Transport transport = session.getTransport("smtp");
            
            transport.connect("smtp.gmail.com", "s.elgafif@gmail.com","password");
            
            transport.sendMessage(courrier, courrier.getAllRecipients());
            
            System.out.println("--- Success after transport.sendMessage()");
            
            transport.close();
            System.out.println("End ");
            

        } catch (MessagingException ex) {
            Logger.getLogger(SendPicture.class.getName()).log(Level.SEVERE, null, ex);
        }
		
	}
}