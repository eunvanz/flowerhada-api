package com.flowerhada.util;

import java.io.UnsupportedEncodingException;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class EmailSender {
	
	static private final String HOST = "smtp.naver.com";
	static private final int PORT = 587;
	static private final String EMAIL = "flowerhada@naver.com";
	static private final String SENDER = "꽃하다";

	public static void send(String recipient, String subject, String content) throws MessagingException, UnsupportedEncodingException {
		Properties props = new Properties();
		
		props.put("mail.smtp.host", HOST);
		props.put("mail.transport.protocol", "smtp");
		props.put("mail.smtp.port", PORT);
		props.put("mail.smtp.starttls.enable", "true");
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.socketFactory.port", PORT);
		props.put("mail.smtp.ssl.trust", HOST);
		
		SMTPAuthenticator authenticator = new SMTPAuthenticator(Constants.EMAIL_ID, Constants.EMAIL_PWD);
		Session mailSession = Session.getInstance(props, authenticator);
		
		MimeMessage message = new MimeMessage(mailSession);
		
		message.setFrom(new InternetAddress(EMAIL, SENDER));
		message.addRecipient(Message.RecipientType.TO, new InternetAddress(recipient));
		message.setSubject(subject);
		message.setContent(content, "text/html; charset=UTF-8");
		
		Transport.send(message);
	}
	
}
