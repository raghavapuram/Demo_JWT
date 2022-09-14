package com.demo.doman.car.service;

import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.demo.doman.car.entity.User;
import com.demo.doman.car.repository.UserRepository;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class UserService implements UserDetailsService {

	@Autowired
	UserRepository userRepository;

	@Autowired
	BCryptPasswordEncoder passEnocde;

	public User saveUser(User user) {

		log.info("save User " + user.getName());
		user.setPass(passEnocde.encode(user.getPass()));
		return userRepository.save(user);

	}


	public User getUser(String name) { 

		log.info(" find User " + name);
		User user = userRepository.findByName(name); 
		return user;
	}


	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

		log.info(" load user " + username);

		User user =	getUser(username);
		return new org.springframework.security.core.userdetails.User(username,
				user.getPass(),
				user.getRolls().stream()
				.map(role -> new SimpleGrantedAuthority(role)).collect(Collectors.toList()));
	}

}
