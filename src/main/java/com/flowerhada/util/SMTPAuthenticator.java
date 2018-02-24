package com.flowerhada.util;


import javax.mail.Authenticator;
import javax.mail.PasswordAuthentication;

public class SMTPAuthenticator extends Authenticator {

	String id;
	String pwd;
	
	public SMTPAuthenticator(String id, String pwd) {
		this.id = id;
		this.pwd = pwd;
	}
	
	public PasswordAuthentication getPasswordAuthentication() {
		return new PasswordAuthentication(id, pwd);
	}
	
}
