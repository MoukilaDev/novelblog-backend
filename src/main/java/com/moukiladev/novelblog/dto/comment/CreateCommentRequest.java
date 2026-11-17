package com.moukiladev.novelblog.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class CreateCommentRequest {
    @NotBlank
    private String authorName;
    @NotBlank(message = "The content is required")
    private String content;
    @NotNull(message = "The id of the post is required")
    private Long PostId;
    //Contructors
    public CreateCommentRequest(){};
    public CreateCommentRequest(String content, String authorName, Long PostId){
        this.content = content;
        this.authorName = authorName;
        this.PostId = PostId;
    };
    //Getters and Setters
    public String getContent() {
        return content;
    }
    public void setContent(String content) {
        this.content = content;
    }
    public String getAuthorName() { return authorName;}
    public void setAuthorName(String authorName) { this.authorName = authorName; }
    public Long getPostId() {
        return PostId;
    }
    public void setPostId(Long postId) {
        this.PostId = postId;
    }
}
