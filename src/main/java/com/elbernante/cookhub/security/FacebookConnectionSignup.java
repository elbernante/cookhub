package com.elbernante.cookhub.security;

import static org.apache.commons.lang3.RandomStringUtils.randomAlphanumeric;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.social.connect.Connection;
import org.springframework.social.connect.ConnectionSignUp;
import org.springframework.social.facebook.api.Facebook;
import org.springframework.stereotype.Service;

import com.elbernante.cookhub.persistence.dao.UserRepository;
import com.elbernante.cookhub.persistence.model.FacebookUser;
import com.elbernante.cookhub.persistence.model.User;

@Service
public class FacebookConnectionSignup implements ConnectionSignUp {
	
	@Autowired
	private UserRepository userRepository;

	@Override
	public String execute(Connection<?> connection) {
		System.out.println("==== SIGN UP ====");
		
		Facebook facebook = (Facebook) connection.getApi();
		FacebookUser facebookUser = facebook.fetchObject(connection.getKey().getProviderUserId(),
														 FacebookUser.class,
														 FacebookUser.FIELDS);
		
		User user = userRepository.findByFacebookId(facebookUser.getId());
		if (user == null) {
			user = new User(facebookUser);
			user.setPassword(randomAlphanumeric(60));
		} else {
			user.updateWith(facebookUser);
		}

        userRepository.save(user);
        return user.getId().toString();
	}

}
