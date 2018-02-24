package com.flowerhada.domain;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@AllArgsConstructor
@ToString
public class AuthenticationToken {
	
	@Getter @Setter
	private Long id;
	@Getter @Setter
	private Collection<? extends GrantedAuthority> authorities;
	@Getter @Setter
	private String token;
	
}
