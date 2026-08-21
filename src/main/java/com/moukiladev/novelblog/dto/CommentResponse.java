package com.moukiladev.novelblog.dto;

public class CommentResponse {
    private Long id;
    private String readerName;
    private String content;

    //Constructors
    public CommentResponse(){}
    public CommentResponse(Long id, String readerName, String content){
        this.id = id;
        this.readerName = readerName;
        this.content = content;
    }

    //Getters and Stters
    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getReaderName() {
        return readerName;
    }

    public void setReaderName(String readerName) {
        this.readerName = readerName;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
