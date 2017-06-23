package com.elbernante.cookhub.service;

import javax.transaction.Transactional;
import javax.transaction.Transactional.TxType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.elbernante.cookhub.persistence.dao.UserRepository;
import com.elbernante.cookhub.persistence.model.User;

@Service
@Transactional(value=TxType.REQUIRES_NEW)
public class UserService {

	@Autowired
	private UserRepository userRepository;
	
	public User userFindById(long id) {
		return userRepository.findOne(id);
	}
}
