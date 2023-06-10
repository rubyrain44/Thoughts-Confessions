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
@Table(name="thoughts")
public class Thought {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	//Attributes//
	@NotEmpty(message="Thought cannot be blank.")
	@Size(min=2, max=325, message="Thought must be at least 2 characters long.")
	private String thoughtContent;
	
	//Relationships//
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="user")
	private User user;
	
	@OneToMany(mappedBy="thought", fetch = FetchType.LAZY)
	private List<Comment> thoughtComments;
	
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
        name = "thoughtLikes", 
        joinColumns = @JoinColumn(name = "thought_id"), 
        inverseJoinColumns = @JoinColumn(name = "user_id")
    )
    private List<User> userLikesOnThought;
	
	//TimeStamps//
	@Column(updatable=false)
    @DateTimeFormat(pattern="yyyy-MM-dd")
    private Date createdAt;
    @DateTimeFormat(pattern="yyyy-MM-dd")
    private Date updatedAt;
    
    public Thought() {}
    
    /////GETTERS & SETTERS/////

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getThoughtContent() {
		return thoughtContent;
	}

	public void setThoughtContent(String thoughtContent) {
		this.thoughtContent = thoughtContent;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public List<Comment> getThoughtComments() {
		return thoughtComments;
	}

	public void setThoughtComments(List<Comment> thoughtComments) {
		this.thoughtComments = thoughtComments;
	}

	public List<User> getUserLikesOnThought() {
		return userLikesOnThought;
	}

	public void setUserLikesOnThought(List<User> userLikesOnThought) {
		this.userLikesOnThought = userLikesOnThought;
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
