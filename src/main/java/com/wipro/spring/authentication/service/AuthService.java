package com.wipro.spring.authentication.service;

import java.util.Optional;

public interface AuthService<T> {

	boolean isValid(T entity);

	Optional<T> getData(T entity);

}
