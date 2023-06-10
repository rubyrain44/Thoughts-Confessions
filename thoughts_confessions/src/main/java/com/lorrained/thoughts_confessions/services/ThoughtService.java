package com.lorrained.thoughts_confessions.services;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.lorrained.thoughts_confessions.models.Thought;
import com.lorrained.thoughts_confessions.models.User;
import com.lorrained.thoughts_confessions.repositories.ThoughtRepository;


@Service
public class ThoughtService {

	@Autowired
	private ThoughtRepository thoughtRepo;
	
    ///ALL THOUGHTS
    public List<Thought> allThoughts() {
        return thoughtRepo.findAll();
    }
    
    ///CREATE THOUGHT
    public Thought createThought(Thought t) {
        return thoughtRepo.save(t);
    }
    
    ///UPDATE THOUGHT
    public Thought updateThought(Thought t) {
        return thoughtRepo.save(t);
    }
    
    ///RETRIEVE SINGLE THOUGHT
    public Thought findThought(Long id) {
        Optional<Thought> optionalThought = thoughtRepo.findById(id);
        if(optionalThought.isPresent()) {
            return optionalThought.get();
        } else {
            return null;
        }
    }
    
    
    
    
    ///RETRIEVE ALL USERS THOUGHTS
    public List<Thought> allUsersThoughts(Long id) {
        return thoughtRepo.findUserThoughtsById(id);
    }
    
    
    
    
    ///DELETE THOUGHT
    public void deleteThought (Long id) {
    	thoughtRepo.deleteById(id);
    }
	
    
    ///POPULATE LIKES
    public void likeThought(Thought t, User u) {
    	List<User> userLikesOnThought = t.getUserLikesOnThought();
    	userLikesOnThought.add(u);
    	thoughtRepo.save(t);
    	//adding likes to a thought
    }
    
    ///UNLIKE
    public void unlikeThought(Thought t, User u) {
    	List<User> userLikesOnThought = t.getUserLikesOnThought();
    	userLikesOnThought.remove(u);
    	thoughtRepo.save(t);
    	//removes likes from a thought
    }
}
