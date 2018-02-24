package com.flowerhada.controller;

import java.io.UnsupportedEncodingException;

import javax.mail.MessagingException;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.flowerhada.domain.Email;
import com.flowerhada.util.EmailSender;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/email")
public class EmailController {
	
	@PostMapping()
	public Email sendEmail(@RequestBody Email email) throws UnsupportedEncodingException, MessagingException {
		
		EmailSender.send(email.getRecipient(), email.getSubject(), email.getContent());
		
		return email;
	}
	
}
