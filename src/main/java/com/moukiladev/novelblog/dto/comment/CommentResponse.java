package com.moukiladev.novelblog.dto.comment;

public class CommentResponse {
    private Long id;
    private String author;
    private String content;
    //Constructors
    public CommentResponse(){}
    public CommentResponse(Long id, String authorName, String content){
        this.id = id;
        this.author = authorName;
        this.content = content;
    }
    //Getters and Setters
    public String getContent() {
        return content;
    }
    public void setContent(String content) {
        this.content = content;
    }
    public String getAuthor() {
        return author;
    }
    public void setAuthor(String author) {
        this.author = author;
    }
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
}
