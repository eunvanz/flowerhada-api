package com.flowerhada.controller;

import java.io.UnsupportedEncodingException;
import java.util.List;

import javax.mail.MessagingException;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.context.HttpSessionSecurityContextRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.flowerhada.domain.AuthenticationRequest;
import com.flowerhada.domain.AuthenticationToken;
import com.flowerhada.domain.User;
import com.flowerhada.service.UserService;
import com.flowerhada.util.Constants;
import com.flowerhada.util.EmailSender;

import javassist.NotFoundException;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/users")
public class UserController {

	@Autowired
	UserService userService;
	@Autowired
	AuthenticationManager authenticationManager;

	@GetMapping("/{id}")
	public User findById(@PathVariable Long id) throws Exception {
		return userService.findById(id);
	}

	@GetMapping()
	public List<User> findByEmail(@RequestParam("email") String email) {
		return userService.findByEmail(email);
	}
	
	@GetMapping("/dup-check")
	public User findByEmailAndSocialTypeIsNull(@RequestParam("email") String email) {
		return userService.findByEmailAndSocialTypeIsNull(email);
	}

	@GetMapping("/all")
	public Page<User> findAll(@RequestParam("curPage") int curPage, @RequestParam("perPage") int perPage) {
		PageRequest pageRequest = new PageRequest(curPage, perPage, new Sort(Direction.DESC, "id"));
		return userService.findAll(pageRequest);
	}

	@PostMapping
	public User createUser(@RequestBody User user) {
		User createdUser = userService.createUser(user);
		userService.createAuthority(createdUser.getId(), Constants.ROLE_USER);
		return createdUser;
	}

	@PostMapping("/login")
	public AuthenticationToken login(@RequestBody AuthenticationRequest authenticationRequest, HttpSession session)
			throws NotFoundException {
		System.out.println("authenticationRequest: " + authenticationRequest);
		Long id = authenticationRequest.getId();
		String password = authenticationRequest.getPassword();

		UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(id, password);

		Authentication authentication = authenticationManager.authenticate(token);
		SecurityContextHolder.getContext().setAuthentication(authentication);
		session.setAttribute(HttpSessionSecurityContextRepository.SPRING_SECURITY_CONTEXT_KEY,
				SecurityContextHolder.getContext());

		User user = (User) userService.loadUserByUsername(id.toString());
		if (user == null)
			throw new NotFoundException("해당하는 계정 정보가 없습니다.");
		return new AuthenticationToken(user.getId(), user.getAuthorities(), session.getId());
	}

	@PostMapping("/social-login")
	public AuthenticationToken socialLogin(@RequestBody AuthenticationRequest authenticationRequest,
			HttpSession session, @RequestParam("socialType") String socialType) throws NotFoundException {
		Long id = authenticationRequest.getId();
		String password = authenticationRequest.getPassword();
		String email = authenticationRequest.getEmail();

		UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(id, password);

		Authentication authentication = authenticationManager.authenticate(token);
		SecurityContextHolder.getContext().setAuthentication(authentication);
		session.setAttribute(HttpSessionSecurityContextRepository.SPRING_SECURITY_CONTEXT_KEY,
				SecurityContextHolder.getContext());

		User user = (User) userService.loadUserByUsernameAndSocialType(id, email, socialType);
		if (user == null)
			throw new NotFoundException("해당하는 계정 정보가 없습니다.");
		return new AuthenticationToken(user.getId(), user.getAuthorities(), session.getId());
	}
	
	@GetMapping("/auth-test")
	public String authTest() {
		return "has authority USER";
	}

	@PutMapping("/{id}/point/{point}")
	public User updateUserPoint(@PathVariable Long id, @PathVariable int point) {
		return userService.updateUserPoint(id, point);
	}

	@PutMapping("/{id}")
	public User updateUser(@PathVariable Long id, @RequestBody User user) throws NotFoundException {
		return userService.updateUser(id, user);
	}

	@GetMapping("/phone/{phone}")
	public List<User> getUserByPhone(@PathVariable String phone) {
		List<User> foundUsers = userService.findByPhone(phone); // SocialType이 Null인것만 가져옴
		for (User user : foundUsers) {
			String email = user.getEmail();
			int indexOfAt = email.indexOf('@');
			String firstEmail = email.substring(0, indexOfAt - 3);
			String lastEmail = email.substring(indexOfAt);
			user.setEmail(firstEmail + "***" + lastEmail);
		}
		return foundUsers;
	}

	@PutMapping("/reset-password")
	public User updateUserPassword(@RequestParam("email") String email)
			throws UnsupportedEncodingException, MessagingException {
		String password = "";
		for (int i = 0; i < 8; i++) {
			if (i % 3 != 0) {
				password += (char) ((Math.random() * 26) + 65);
			} else {
				password += (int) (Math.random() * 10);
			}
		}

		String subject = "[꽃하다] 임시비밀번호 발급";
		String content = "임시비밀번호: <strong>" + password + "</strong>" + "<p>로그인 후 꼭 비밀번호 변경을 해주세요.<p>";

		EmailSender.send(email, subject, content);

		return userService.updateUserPassword(email, password);
	}

	@GetMapping("/social")
	public User getUserByEmailAndSocialType(@RequestParam("email") String email,
			@RequestParam("socialType") String socialType) {
		return userService.findByEmailAndSocialType(email, socialType);
	}

}
