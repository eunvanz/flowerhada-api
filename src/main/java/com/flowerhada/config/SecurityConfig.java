package com.flowerhada.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.session.web.http.HeaderHttpSessionStrategy;
import org.springframework.session.web.http.HttpSessionStrategy;

import com.flowerhada.service.UserService;

@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	
	@Autowired UserService userService;
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http
			.csrf().disable()
			.authorizeRequests()
				.antMatchers("/**").permitAll()
//				.antMatchers("/users").permitAll()
//				.antMatchers("/users/**").permitAll()
//				.antMatchers("/main-banners").permitAll()
//				.antMatchers("/main-banners/**").permitAll()
//				.antMatchers("/lessons").permitAll()
//				.antMatchers("/lessons/**").permitAll()
//				.antMatchers("/products").permitAll()
//				.antMatchers("/products/**").permitAll()
//				.antMatchers("/lesson-days").permitAll()
//				.antMatchers("/lesson-days/**").permitAll()
//				.antMatchers("/comments").permitAll()
//				.antMatchers("/comments/**").permitAll()
//				.antMatchers("/carts").permitAll()
//				.antMatchers("/carts/**").permitAll()
//				.antMatchers(HttpMethod.OPTIONS, "/**").permitAll()
//				.antMatchers("/address-histories").permitAll()
//				.antMatchers("/address-histories/**").permitAll()
//				.antMatchers("/point-histories").permitAll()
//				.antMatchers("/point-histories/**").permitAll()
//				.antMatchers("/orders").permitAll()
//				.antMatchers("/orders/**").permitAll()
//				.antMatchers("/errors").permitAll()
//				.antMatchers("/errors/**").permitAll()
				.antMatchers("/admin").hasAuthority("ADMIN")
				.anyRequest().authenticated()
				.and()
			.logout();
	}
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userService)
			.passwordEncoder(userService.passwordEncoder());
	}
	
	@Bean
	@Override
	public AuthenticationManager authenticationManagerBean() throws Exception {
		return super.authenticationManagerBean();
	}
	
	@Bean
	public HttpSessionStrategy httpSessionStrategy() {
		return new HeaderHttpSessionStrategy();
	}
	
}
