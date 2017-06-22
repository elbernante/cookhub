package com.elbernante.cookhub.security;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.social.connect.Connection;
import org.springframework.social.connect.web.SignInAdapter;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.NativeWebRequest;

import com.elbernante.cookhub.persistence.dao.UserRepository;
import com.elbernante.cookhub.persistence.model.CookHubUserDetails;
import com.elbernante.cookhub.persistence.model.User;

@Service
public class FacebookSignInAdapter implements SignInAdapter {
	
	@Autowired
	private UserRepository userRepository;
	
	public FacebookSignInAdapter() {}

	@Override
	public String signIn(String userId, Connection<?> connection, NativeWebRequest request) {
		System.out.println("====== Sign In adapter ======");
		System.out.println("#### USER ID:" + userId);
		List<SimpleGrantedAuthority> auth = Arrays.asList(new SimpleGrantedAuthority("FACEBOOK_USER"));
		
		String facebookId = connection.getKey().getProviderUserId();
		User user = userRepository.findByFacebookId(facebookId);
		CookHubUserDetails userDetails = new CookHubUserDetails(user, auth);
		UsernamePasswordAuthenticationToken upat = new UsernamePasswordAuthenticationToken(userDetails,
																						  null, 
																						  auth);

        SecurityContextHolder.getContext().setAuthentication(upat);
        return null;
	}

}
