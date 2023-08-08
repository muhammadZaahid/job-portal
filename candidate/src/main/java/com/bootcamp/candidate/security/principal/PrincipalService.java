package com.bootcamp.candidate.security.principal;

public interface PrincipalService<T> {
	T getAuthPrincipal();
}
