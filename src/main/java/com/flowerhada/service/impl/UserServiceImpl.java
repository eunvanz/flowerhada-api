package com.flowerhada.service.impl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.flowerhada.domain.Authority;
import com.flowerhada.domain.User;
import com.flowerhada.repository.AuthorityRepository;
import com.flowerhada.repository.UserRepository;
import com.flowerhada.service.UserService;
import com.flowerhada.util.Constants;
import com.flowerhada.util.Utils;

import javassist.NotFoundException;
import lombok.RequiredArgsConstructor;

@Service
@Transactional
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
	@Autowired UserRepository userRepository;
	@Autowired AuthorityRepository authorityRepository;
	private PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
	
	@Override
	public User createUser(User user) {
		String rawPassword = user.getPassword();
		String encodedPassword = passwordEncoder.encode(rawPassword);
		user.setPassword(encodedPassword);
		user.setRegDate(Utils.getZonedDateTimeNow("Asia/Seoul"));
		user.setAccountNonExpired(true);
		user.setAccountNonLocked(true);
		user.setCredentialsNonExpired(true);
		user.setEnabled(true);
		return userRepository.save(user);
	}
	
	@Override
	public User updateUser(Long id, User user) throws NotFoundException {
		User oldUser = userRepository.findOne(id);
		if (oldUser == null) {
			throw new NotFoundException("user not found");
		}
		
		oldUser.setEmail(user.getEmail());
		String rawPassword = user.getPassword();
		if (rawPassword != null) {
			String encodedPassword = passwordEncoder.encode(rawPassword);
			oldUser.setPassword(encodedPassword);
		}
		oldUser.setName(user.getName());
		oldUser.setPhone(user.getPhone());
		oldUser.setEnabled(user.isEnabled());
		oldUser.setImage(user.getImage());
		return oldUser;
	}
	
	@Override
	public void deleteUser(Long id) throws NotFoundException {
		User oldUser = userRepository.findOne(id);
		if (oldUser == null) {
			throw new NotFoundException("user not found");
		}
		
		oldUser.setEnabled(false);
	}
	
	@Override
	public User findById(Long id) throws NotFoundException {
		User user = userRepository.findOne(id);
		if (user == null) {
			throw new NotFoundException("user not found");
		}
		return user;
	}

	@Override
	public User loadUserByUsername(String id) throws UsernameNotFoundException {
		User user = userRepository.findOne(Long.parseLong(id));
        user.setAuthorities(getAuthorities(Long.parseLong(id)));
        
        return user;
	}
	
	public User loadUserByUsernameAndSocialType(Long id, String email, String socialType) throws UsernameNotFoundException {
		User user = userRepository.findByEmailAndSocialType(email, socialType);
        user.setAuthorities(getAuthorities(id));
        
        return user;
	}

	@Override
	public Collection<GrantedAuthority> getAuthorities(Long id) {
		List<String> string_authorities = authorityRepository.findAuthoritiesById(id);
        List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
        for (String authority : string_authorities) {
            authorities.add(new SimpleGrantedAuthority(authority));
        }
        return authorities;
	}

	@Override
	public PasswordEncoder passwordEncoder() {
		return this.passwordEncoder;
	}

	@Override
	public List<User> findByEmail(String email) {
		List<User> user = userRepository.findByEmail(email);
		return user;
	}

	@Override
	public Authority createAuthority(Long id, String authorityName) {
		Authority authority = new Authority();
		authority.setId(id);
		authority.setAuthorityName(authorityName);
		authorityRepository.save(authority);
		return authority;
	}

	@Override
	public User updateUserPoint(Long id, int point) {
		User oldUser = userRepository.findOne(id);
		oldUser.setPoint(oldUser.getPoint() + point);
		return oldUser;
	}

	@Override
	public List<User> findByPhone(String phone) {
		return userRepository.findByPhoneAndSocialTypeIsNull(phone);
	}

	@Override
	public User updateUserPassword(String email, String password) {
		User oldUser = userRepository.findByEmailAndSocialTypeIsNull(email);
		String encodedPassword = passwordEncoder.encode(password);
		oldUser.setPassword(encodedPassword);
		return oldUser;
	}

	@Override
	public Page<User> findAll(Pageable pageale) {
		return userRepository.findAll(pageale);
	}

	@Override
	public User findByEmailAndSocialType(String email, String socialType) {
		return socialType.equals("null") ? userRepository.findByEmailAndSocialTypeIsNull(email) : userRepository.findByEmailAndSocialType(email, socialType);
	}

	@Override
	public User findByEmailAndSocialTypeIsNull(String email) {
		return userRepository.findByEmailAndSocialTypeIsNull(email);
	}
}
