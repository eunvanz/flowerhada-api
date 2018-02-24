package com.flowerhada.domain;

import java.time.LocalDateTime;
import java.util.Collection;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Data
@Entity
@Table(name="USER")
public class User implements UserDetails {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -238702863403080181L;

	@Id
	@GeneratedValue
	private Long id;
	
	@NotNull
	private String email;
	
	@NotNull
	@JsonProperty(access = Access.WRITE_ONLY)
	private String password;
	
	@NotNull
	private String name;
	
	private String phone;
	
	private int point;
	
	private LocalDateTime regDate;
	
	private String image;
	
	private String socialType;
	
	@Transient
	@Setter
	private Collection<? extends GrantedAuthority> authorities;
	
	@Setter
	private boolean isAccountNonExpired;
	
	@Setter
	private boolean isAccountNonLocked;
	
	@Setter
	private boolean isCredentialsNonExpired;
	
	@Setter
	private boolean isEnabled;

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return authorities;
	}

	@Override
	public String getUsername() {
		return id.toString();
	}
	
	@Override
	public boolean isAccountNonExpired() {
		return isAccountNonExpired;
	}

	@Override
	public boolean isAccountNonLocked() {
		return isAccountNonLocked;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return isCredentialsNonExpired;
	}

	@Override
	public boolean isEnabled() {
		return isEnabled;
	}
}
