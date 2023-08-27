package service;

import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class MailService {

	public MailService() {}
	
	public boolean sendEmailOTP(String emailReceiver, int otp) {
		final String senderEmail = "nhhoanglai@gmail.com";
		final String senderPassword = "ubdlhyzeogqnftai";
		
		// Mail server properties
        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");

        // Create a session with authentication
        Session session = Session.getInstance(props, new Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(senderEmail, senderPassword);
            }
        });

        try {
            // Create a new message
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(senderEmail));
            message.setRecipient(Message.RecipientType.TO, new InternetAddress(emailReceiver));
            message.setSubject("OTP Verification");
            message.setText("Your OTP is: " + otp);

            // Send the message
            Transport.send(message);

            System.out.println("OTP sent successfully to " + emailReceiver);
        } catch (MessagingException e) {
            e.printStackTrace();
            return false;
        }
        
        return true;
	}
}
