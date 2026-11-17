package com.moukiladev.novelblog.dto;

public class CommentResponse {
    private Long id;
    private String authorName;
    private String content;
    //Constructors
    public CommentResponse(){}
    public CommentResponse(Long id, String authorName, String content){
        this.id = id;
        this.authorName = authorName;
        this.content = content;
    }
    //Getters and Setters
    public String getContent() {
        return content;
    }
    public void setContent(String content) {
        this.content = content;
    }
    public String getAuthorName() {
        return authorName;
    }
    public void setAuthorName(String authorName) {
        this.authorName = authorName;
    }
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
}
