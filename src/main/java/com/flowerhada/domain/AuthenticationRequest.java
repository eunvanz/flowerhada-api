package com.flowerhada.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@ToString
public class AuthenticationRequest {
	
	@Setter @Getter
	private Long id;
	@Setter @Getter
	private String email;
	@Setter @Getter
	private String password;
	
}
