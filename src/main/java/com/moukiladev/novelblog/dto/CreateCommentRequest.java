package com.moukiladev.novelblog.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class CreateCommentRequest {
    @NotBlank(message = "The reader's name is required")
    private String readerName;
    @NotBlank(message = "The content is required")
    private String content;
    @NotNull(message = "The id of the post is required")
    private Long PostId;

    //Contructors
    public CreateCommentRequest(){};
    public CreateCommentRequest(String readerName, String content, Long PostId){};

    //Getters and Setters

    public String getReaderName() {
        return readerName;
    }

    public void setReaderName(String readerName) {
        this.readerName = readerName;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Long getPostId() {
        return PostId;
    }

    public void setPostId(Long postId) {
        PostId = postId;
    }
}
