package com.elbernante.cookhub.security;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.elbernante.cookhub.persistence.dao.UserRepository;
import com.elbernante.cookhub.persistence.model.CookHubUserDetails;
import com.elbernante.cookhub.persistence.model.User;

@Service
public class CookHubUserDetailsService implements UserDetailsService {
	
	@Autowired
	private UserRepository userRepository;
	
	public CookHubUserDetailsService() {
		super();
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		System.out.println("##### LOAD BY USER NAME: " + username);
		final User user = userRepository.findByUsername(username);
		if (user == null) throw new UsernameNotFoundException(username);
		
		return new CookHubUserDetails(user, Arrays.asList(new SimpleGrantedAuthority("ROLE_USER")));
//		return new org.springframework.security.core.userdetails.User(user.getId().toString(), 
//																	  user.getPassword(),
//																	  true, 	// enabled
//																	  true, 	// accountNonExpired
//																	  true, 	// credentialsNonExpired
//																	  true, 	// accountNonLocked
//																	  Arrays.asList(new SimpleGrantedAuthority("ROLE_USER")));	//authorities)
	}

}
