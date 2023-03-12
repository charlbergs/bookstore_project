package hh.sof3as3.Bookstore;

import java.util.ArrayList;
import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableMethodSecurity(securedEnabled = true) // metoditason suojaus
public class WebSecurityConfig {
	@Bean
	public SecurityFilterChain configure(HttpSecurity http) throws Exception {
		http
			.authorizeHttpRequests()
				.anyRequest().authenticated()
				.and()
			.formLogin()
				.defaultSuccessUrl("/booklist", true)
				.permitAll()
				.and()
			.logout()
				.permitAll()
				.and()
			.httpBasic();
		return http.build();		
	}
	
	// luodaan kaksi testikäyttäjää: user ja admin
	@Bean
	public UserDetailsService userDetailsService() {
		List<UserDetails> users = new ArrayList<UserDetails>();
		PasswordEncoder passwordEncoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();
		UserDetails user = User
				.withUsername("user")
				.password(passwordEncoder.encode("user"))
				.roles("USER")
				.build();
		users.add(user);
		UserDetails user2 = User
				.withUsername("admin")
				.password(passwordEncoder.encode("admin"))
				.roles("ADMIN")
				.build();
		users.add(user2);
		return new InMemoryUserDetailsManager(users);
		
	}
}