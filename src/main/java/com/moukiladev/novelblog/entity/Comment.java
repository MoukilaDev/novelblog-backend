package com.moukiladev.novelblog.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;

@Entity
public class Comment {
    // Attribute
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "content is required")
    private String content;

    @ManyToOne
    @JoinColumn(name = "userEntity_id", nullable = false)
    private UserEntity author;

    @ManyToOne
    @JoinColumn(name = "post_id", nullable = false)
    private Post post;

    //Constructors
    public Comment(){}
    public Comment(String content, Post post){
        this.content = content;
        this.post = post;
    }

    //Getters and setters
    public Long getId() {

        return id;
    }

    public void setId(Long id) {

        this.id = id;
    }

    public String getContent() {

        return content;
    }

    public void setContent(String content) {

        this.content = content;
    }

    public UserEntity getAuthor() {

        return author;
    }

    public void setAuthor(UserEntity author) {
        this.author = author;
    }

    public Post getPost() {

        return post;
    }

    public void setPost(Post post) {

        this.post = post;
    }
}
