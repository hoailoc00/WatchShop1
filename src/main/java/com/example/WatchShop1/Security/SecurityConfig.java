package com.example.WatchShop1.Security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import com.example.WatchShop1.Service.iml.CustomUserDetailsService;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
	@Autowired
	private CustomUserDetailsService detailsService;

	@Bean
	BCryptPasswordEncoder encoder() {
		return new BCryptPasswordEncoder();
	}
	@Bean
	SecurityFilterChain securityFilterChain (HttpSecurity http) throws Exception{
		http.csrf(csrf->csrf.disable());
		http.authorizeHttpRequests(auth->auth
				.requestMatchers("/*").permitAll()
				.requestMatchers("/employee/**").hasAuthority("Nhanvien")
				.requestMatchers("/admin/**").hasAuthority("ADMIN")
				.anyRequest().authenticated()
				);
		http.formLogin(login->login
				.loginPage("/login")
				.loginProcessingUrl("/login")
				.usernameParameter("username")
				.passwordParameter("password")
				.defaultSuccessUrl("/")
				
				);
		return http.build();
	}

	@Bean
	WebSecurityCustomizer customizer() {
		return web -> web.ignoring().requestMatchers("/static/**", "/assets/**", "/assets1/**", "/css/**", "/js/**", "/style_dangnhap/**");
	}
}
