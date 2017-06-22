package com.elbernante.cookhub.persistence.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.elbernante.cookhub.persistence.model.User;


public interface UserRepository extends JpaRepository<User, Long> {
	User findByUsername(final String username);
	User findByFacebookId(final String facebookId);
}
