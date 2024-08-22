package com.example.rest.repository;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.rest.model.User;

public interface UserRepo extends JpaRepository<User, Integer>{
	
	@Query("from User where email=:email and password=:password")
	Optional<User> findByEmailAndPassword (String email,String password);
	
	@Query("from User where email=:email")
	Optional<User> findByEmail(String email);
}
