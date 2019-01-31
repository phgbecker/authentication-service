package com.wipro.spring.authentication.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wipro.spring.authentication.model.Login;
import com.wipro.spring.authentication.repository.LoginRepository;

@Service
public class AuthServiceImpl implements AuthService<Login> {

	private LoginRepository loginRepository;

	@Autowired
	protected AuthServiceImpl(LoginRepository loginRepository) {
		this.loginRepository = loginRepository;
	}

	@Override
	public boolean isValid(Login login) {
		Optional<Login> loginRequest = loginRepository.findByUsernameAndPassword(login.getUsername(), login.getPassword());

		return loginRequest.isPresent();
	}

	@Override
	public Optional<Login> getData(Login login) {
		return loginRepository.findByUsernameAndPassword(login.getUsername(), login.getPassword());
	}

}
