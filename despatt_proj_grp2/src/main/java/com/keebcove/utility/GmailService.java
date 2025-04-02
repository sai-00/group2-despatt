package com.keebcove.utility;

import java.util.Properties;

import jakarta.mail.Authenticator;
import jakarta.mail.Message;
import jakarta.mail.PasswordAuthentication;
import jakarta.mail.Session;
import jakarta.mail.Transport;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;

public class GmailService {
	private static final String EMAIL_FROM = "keebcove@gmail.com";
	private static final String APP_PASSWORD = "aict dxdp vhkj wwcw";
	
	
	//Method to send mail
    public static void sendEmail(String recipient, String subject, String body) throws Exception {
        Message message = new MimeMessage(getEmailSession());
        message.setFrom(new InternetAddress(EMAIL_FROM));
        message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(recipient));
        message.setSubject(subject);
        message.setText(body);
        Transport.send(message);
        System.out.println("Email sent successfully to " + recipient);
    }
	
    //Method for email session
    private static Session getEmailSession() {
		return Session.getInstance(getGmailProperties(), new Authenticator() {
		    protected PasswordAuthentication getPasswordAuthentication() {
		        return new PasswordAuthentication(EMAIL_FROM, APP_PASSWORD);
		    }
		});
	}
    
    //Base properties to link to account
	private static Properties getGmailProperties() {
		Properties prop = new Properties();
		prop.put("mail.smtp.auth", "true");
		prop.put("mail.smtp.starttls.enable", "true");
		prop.put("mail.smtp.host", "smtp.gmail.com");
		prop.put("mail.smtp.port", "587");
		prop.put("mail.smtp.ssl.trust", "smtp.gmail.com");
		return prop;
	}
}