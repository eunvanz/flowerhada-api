package com.flowerhada.domain;

import lombok.Data;

@Data
public class Email {
	
	private String subject;
	private String recipient;
	private String from;
	private String content;
	
}
