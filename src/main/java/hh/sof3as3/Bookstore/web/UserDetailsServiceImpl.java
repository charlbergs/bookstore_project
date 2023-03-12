package hh.sof3as3.Bookstore.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import hh.sof3as3.Bookstore.domain.User;
import hh.sof3as3.Bookstore.domain.UserRepository;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
	private final UserRepository repository;
	@Autowired
	public UserDetailsServiceImpl(UserRepository userRepository) {
		this.repository = userRepository;
	}
	
	// paketoidaan projektin "oman" User-luokan olion tiedot Springin User-luokan olioksi:
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		// tarkistaa löytyykö parametrina saatua käyttäjätunnusta repositoriosta
		User currUser = repository.findByUsername(username);
		// luo uuden Spring-User-olion
		UserDetails user = new org.springframework.security.core.userdetails.User(
							username, 
							currUser.getPasswordHash(),
							AuthorityUtils.createAuthorityList(currUser.getRole())
							);
		return user;
	}

}
