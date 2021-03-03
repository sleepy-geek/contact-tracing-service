package com.thesisproject.ct.contacttracingservice.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter{
	
	@Override
	public void configure(HttpSecurity http) throws Exception {
		http.csrf().disable()
			.authorizeRequests()
			.antMatchers("/", "/forms").permitAll()
			.anyRequest().authenticated()
			.and()
			.formLogin()
			.loginPage("/login").permitAll()
			.defaultSuccessUrl("/admin/home")
			.and()
			.logout().permitAll();
	}
	
	@Bean
	public UserDetailsService userDetailsService() {
//		PasswordEncoder encoder = new BCryptPasswordEncoder(17);
//		String encodedPassword = encoder.encode("password_1");
		UserDetails user = User.withDefaultPasswordEncoder()
							   .username("admin")
							   .password("password_1")
							   .roles("ADMIN")
							   .build();
		return new InMemoryUserDetailsManager(user);
	}
}
