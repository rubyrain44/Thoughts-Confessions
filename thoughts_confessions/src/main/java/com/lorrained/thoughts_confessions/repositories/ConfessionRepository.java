package com.lorrained.thoughts_confessions.repositories;

import java.util.List;
import org.springframework.data.repository.CrudRepository;
import com.lorrained.thoughts_confessions.models.Confession;


public interface ConfessionRepository extends CrudRepository<Confession, Long> {

	List<Confession> findAll();
	
}
