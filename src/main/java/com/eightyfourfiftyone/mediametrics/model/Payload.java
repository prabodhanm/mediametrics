package com.eightyfourfiftyone.mediametrics.model;

public class Payload {
    public Payload(String message) {
        this.message = message;
    }

    String message;

    // getters and setters


    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
