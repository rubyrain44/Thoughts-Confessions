package com.lorrained.thoughts_confessions.repositories;

import java.util.Optional;
import org.springframework.data.repository.CrudRepository;
import com.lorrained.thoughts_confessions.models.User;


public interface UserRepository extends CrudRepository<User, Long> {

	public Optional<User> findByEmail(String email);
	
}
