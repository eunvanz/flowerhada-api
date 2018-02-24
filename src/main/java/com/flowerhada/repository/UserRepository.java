package com.flowerhada.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.flowerhada.domain.User;

public interface UserRepository extends JpaRepository<User, Long> {
	List<User> findByEmail(String email);
	User findByEmailAndPassword(String email, String password);
	List<User> findByPhone(String phone);
	User findByEmailAndSocialType(String email, String socialType);
	User findByEmailAndSocialTypeIsNull(String email);
	List<User> findByPhoneAndSocialTypeIsNull(String phone);
}
