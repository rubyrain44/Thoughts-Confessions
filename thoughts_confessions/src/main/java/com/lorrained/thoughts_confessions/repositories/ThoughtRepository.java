package com.lorrained.thoughts_confessions.repositories;

import java.util.List;
import org.springframework.data.repository.CrudRepository;
import com.lorrained.thoughts_confessions.models.Thought;


public interface ThoughtRepository extends CrudRepository<Thought, Long> {

	List<Thought> findAll();
	
	List<Thought> findUserThoughtsById(Long id);
	
}
