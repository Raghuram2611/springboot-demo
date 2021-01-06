package com.shopping.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.shopping.entity.Account;
import com.shopping.entity.Authorities;
import com.shopping.repository.UserRepository;

@Service
public class AccountService implements UserDetailsService {
	
	@Autowired
	private UserRepository userRepository;

	public String newUser(String name, String username, String password1, String password2) {
		if (!userRepository.findAll().stream().filter(b -> b.getUsername().equals(username)).collect(Collectors.toList()).isEmpty()) {
			return "User Already Exists";
		}
		if (password1.equals(password2)) {
			Account account = new Account();
			account.setName(name);
			account.setUsername(username);
			account.setPassword(password1);
			userRepository.save(account);
		}
		return "Account Created";
	}

	public boolean validateUser(String username, String password) {
		if (!userRepository.findAll().stream().filter(b -> b.getUsername().equals(username)).collect(Collectors.toList()).isEmpty()) {
			return password.equals(userRepository.findAccountByUsername(username).getPassword());
		}
		return false;
	}


	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		Account user = userRepository.findAccountByUsername(username);
		
		if (user == null)
			throw new UsernameNotFoundException("Username and or password was incorrect");
		
		return new org.springframework.security.core.userdetails.User(user.getUsername(),user.getPassword(),user.getAuthorities());
	}

	public String saveUser(String name, String username, String password1) {
		if (userRepository.findAccountByUsername(username) != null) {
			return "Username Already present";
		}
		Account newuser = new Account();
		newuser.setName(name);
		newuser.setUsername(username);
		newuser.setPassword(passwordEncode(password1));
		Authorities newauth = new Authorities();
		newauth.setAuthority("ROLE_USER");
		newuser.setAuthorities(List.of(newauth));
		userRepository.save(newuser);
		return "User Created";
	}
	
	public String passwordEncode(String password) {
		
		PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		
		return passwordEncoder.encode(password);
	}

	public String getNameByUsername(String username) {
		return userRepository.findAccountByUsername(username).getName();
	}

	public Account getAccountByName(String username) {
		return userRepository.findAccountByUsername(username);
	}


}
