package com.laye;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.laye.entity.CustomUserDetails;
import com.laye.entity.Role;
import com.laye.entity.User;
import com.laye.repository.UserRepository;

@SpringBootApplication
public class TestSpringSecurityOAuth2Application {

	public static void main(String[] args) {
		SpringApplication.run(TestSpringSecurityOAuth2Application.class, args);
	}
	
	@Autowired
	private void authenticationManager(AuthenticationManagerBuilder builder, UserRepository repo) throws Exception {
		
		if (repo.count() == 0)
			repo.save(new User("user", "user", Arrays.asList(new Role("ACTUATOR"))));
		
		builder.userDetailsService(s -> new CustomUserDetails(repo.findByUsername(s)));
		
		/*builder.userDetailsService(new UserDetailsService() {
			
			@Override
			public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
				return new CustomUserDetails(repo.findByUsername(s));
			}
		});*/
	}
	
	
}
