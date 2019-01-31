package com.wipro.spring.authentication.controller;

import java.util.Optional;

import javax.validation.Valid;
import javax.ws.rs.core.MediaType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.wipro.spring.authentication.model.Login;
import com.wipro.spring.authentication.service.AuthService;

@RestController
public class AuthController {

	private AuthService<Login> authService;

	@Autowired
	public AuthController(AuthService<Login> authenticationService) {
		this.authService = authenticationService;
	}

	@PostMapping(value = "authentication", consumes = MediaType.APPLICATION_JSON)
	public String doLogin(@Valid @RequestBody Login login) {
		if (authService.isValid(login)) {
			Optional<Login> loginData = authService.getData(login);

			if (loginData.isPresent())
				return "Welcome back, " + loginData.get().getFullName();
		}

		return "Wrong \"Username\" or \"Password\"";
	}

}
