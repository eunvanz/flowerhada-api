package com.flowerhada.service;

import java.util.Collection;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.flowerhada.domain.Authority;
import com.flowerhada.domain.User;

import javassist.NotFoundException;

public interface UserService extends UserDetailsService {
	Collection<GrantedAuthority> getAuthorities(Long id);
	public User createUser(User user);
	public User updateUser(Long id, User user) throws NotFoundException;
	public void deleteUser(Long id) throws NotFoundException;
	public User findById(Long id) throws NotFoundException;
	public PasswordEncoder passwordEncoder();
	public List<User> findByEmail(String email);
	public Authority createAuthority(Long id, String authorityName);
	public User updateUserPoint(Long id, int point);
	public List<User> findByPhone(String phone);
	public User updateUserPassword(String email, String password);
	public Page<User> findAll(Pageable pageale);
	public User findByEmailAndSocialType(String email, String socialType);
	public User loadUserByUsernameAndSocialType(Long id, String email, String socialType) throws UsernameNotFoundException;
	public User findByEmailAndSocialTypeIsNull(String email);
}
