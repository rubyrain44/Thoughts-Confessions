package com.lorrained.thoughts_confessions.repositories;

import java.util.List;
import org.springframework.data.repository.CrudRepository;
import com.lorrained.thoughts_confessions.models.Comment;


public interface CommentRepository extends CrudRepository<Comment, Long> {

	List<Comment> findAll();
	
}
