package com.wipro.spring.authentication.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.wipro.spring.authentication.model.Login;

public interface LoginRepository extends JpaRepository<Login, Long> {

	@Query("FROM Login WHERE username = :username AND password = :password")
	Optional<Login> findByUsernameAndPassword(String username, String password);

}
