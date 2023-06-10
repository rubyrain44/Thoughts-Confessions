package com.lorrained.thoughts_confessions.services;


import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.lorrained.thoughts_confessions.models.Confession;
import com.lorrained.thoughts_confessions.models.User;
import com.lorrained.thoughts_confessions.repositories.ConfessionRepository;


@Service
public class ConfessionService {

	@Autowired
	private ConfessionRepository confessionRepo;
	
	
    ///ALL CONFESSIONS
    public List<Confession> allConfessions() {
        return confessionRepo.findAll();
    }
    
    ///CREATE CONFESSION
    public Confession createConfession(Confession c) {
        return confessionRepo.save(c);
    }
    
    ///UPDATE CONFESSION
    public Confession updateConfession(Confession c) {
        return confessionRepo.save(c);
    }
    
    ///RETRIEVE SINGLE CONFESSION
    public Confession findConfession(Long id) {
        Optional<Confession> optionalConfession = confessionRepo.findById(id);
        if(optionalConfession.isPresent()) {
            return optionalConfession.get();
        } else {
            return null;
        }
    }
    
    ///DELETE CONFESSION
    public void deleteConfession (Long id) {
    	confessionRepo.deleteById(id);
    }
    
    ///POPULATE LIKES
    public void likeConfession(Confession c, User u) {
    	List<User> userLikesOnConfession = c.getUserLikesOnConfession();
    	userLikesOnConfession.add(u);
    	confessionRepo.save(c);
    	//adding likes to a thought (will have to figure out restrictions later)
    }
    
    ///UNLIKES
    public void unlikeConfession(Confession c, User u) {
    	List<User> userLikesOnConfession = c.getUserLikesOnConfession();
    	userLikesOnConfession.remove(u);
    	confessionRepo.save(c);
    	//removing likes to a thought (will have to figure out restrictions later)
    }		
}
