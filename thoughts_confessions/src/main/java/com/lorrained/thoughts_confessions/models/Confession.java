package com.lorrained.thoughts_confessions.models;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name="confessions")
public class Confession {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	//Attributes//
	@NotEmpty(message="Confession cannot be blank.")
	@Size(min=2, max=325, message="Confession must be at least 2 characters long.")
	private String confessionContent;
	
	//Relationships//
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="user")
	private User user;
	
	@OneToMany(mappedBy="confession", fetch = FetchType.LAZY)
	private List<Comment> confessionComments;
	
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
        name = "confessionLikes", 
        joinColumns = @JoinColumn(name = "confession_id"), 
        inverseJoinColumns = @JoinColumn(name = "user_id")
    )
    private List<User> userLikesOnConfession;
	
	//TimeStamps//
	@Column(updatable=false)
    @DateTimeFormat(pattern="yyyy-MM-dd")
    private Date createdAt;
    @DateTimeFormat(pattern="yyyy-MM-dd")
    private Date updatedAt;
    
    public Confession() {}
    
    /////GETTERS & SETTERS/////

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getConfessionContent() {
		return confessionContent;
	}

	public void setConfessionContent(String confessionContent) {
		this.confessionContent = confessionContent;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public List<Comment> getConfessionComments() {
		return confessionComments;
	}

	public void setConfessionComments(List<Comment> confessionComments) {
		this.confessionComments = confessionComments;
	}

	public List<User> getUserLikesOnConfession() {
		return userLikesOnConfession;
	}

	public void setUserLikesOnConfession(List<User> userLikesOnConfession) {
		this.userLikesOnConfession = userLikesOnConfession;
	}

	public Date getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}

	public Date getUpdatedAt() {
		return updatedAt;
	}

	public void setUpdatedAt(Date updatedAt) {
		this.updatedAt = updatedAt;
	}
    
	/////PRE/////
    @PrePersist
    protected void onCreate(){
        this.createdAt = new Date();
    }
    @PreUpdate
    protected void onUpdate(){
        this.updatedAt = new Date();
    }
	 	
}
