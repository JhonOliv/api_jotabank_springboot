package com.jotabank.api.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.jotabank.api.models.Role;
import jakarta.servlet.DispatcherType;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration {
	
	@Autowired
	private SecurityFilterToken secFilter;
	
	@Bean
	public SecurityFilterChain 	securityFilterChain(HttpSecurity http) throws Exception {
		return http.csrf(csrf -> csrf.disable())
				.headers(headers -> headers.frameOptions(
						frame -> frame.disable()))
				.cors(cors -> cors.configure(http))
				.sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
				.authorizeHttpRequests(autorize -> autorize.dispatcherTypeMatchers(DispatcherType.ERROR).permitAll()
				.requestMatchers(HttpMethod.POST, "/auth/login").permitAll()
				.requestMatchers(HttpMethod.POST, "v1/api/conta/register").permitAll()
				.requestMatchers(HttpMethod.GET, "auth/teste").permitAll()
				.requestMatchers("/h2-console/**").permitAll()
				.requestMatchers("/admin/**").hasRole(Role.ADMIN.toString())
				.requestMatchers("/manager/**").hasRole(Role.MANAGER.toString())
				.anyRequest().authenticated())
				.addFilterBefore(secFilter, UsernamePasswordAuthenticationFilter.class).build();
				

	}
	
	@Bean
	public AuthenticationManager authenticationManager(AuthenticationConfiguration authConfig) throws Exception {
		return authConfig.getAuthenticationManager();
	}
	
	@Bean
	public PasswordEncoder passwordEncode() {
		return new BCryptPasswordEncoder();
	}

}

