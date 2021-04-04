package com.valdemar.demo.security.service;

import com.valdemar.demo.security.model.User;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import com.valdemar.demo.security.security.model.Role;

import javax.annotation.PostConstruct;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author ard333
 */
@Service
public class UserService {
	
	// this is just an example, you can load the user from the database from the repository

	private Map<String, User> data;
	
	@PostConstruct
	public void init(){
		data = new HashMap<>();
		
		//username:passwowrd -> user:user
		data.put("user", new User("user", "cBrlgyL2GI2GINuLUUwgojITuIufFycpLG4490dhGtY=", true, Collections.singletonList(Role.ROLE_USER)));

		//username:passwowrd -> admin:admin
		data.put("admin", new User("admin", "dQNjUIMorJb8Ubj2+wVGYp6eAeYkdekqAcnYp+aRq5w=", true, Collections.singletonList(Role.ROLE_USER)));
	}
	
	public Mono<User> findByUsername(String username) {
		if (data.containsKey(username)) {
			return Mono.just(data.get(username));
		} else {
			return Mono.empty();
		}
	}
	
}
