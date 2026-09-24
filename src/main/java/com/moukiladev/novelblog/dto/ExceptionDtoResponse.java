package com.moukiladev.novelblog.dto;

public class ExceptionDtoResponse {
    //Attribute
    private String message;
    //Constructors
    public ExceptionDtoResponse(){}
    public ExceptionDtoResponse(String message){
        this.message = message;
    }
    //Getter and Setter
    public String getMessage() {
        return message;
    }
    public void setMessage(String message) {
        this.message = message;
    }
}
