package com.example.studentmanagement.exception;

public class ErrorResponse {

    private String timestamp;
    private String message;
    private int status;

    public ErrorResponse(int status, String message, long timestamp) {
        this.status = status;
        this.message = message;
        this.timestamp = String.valueOf(timestamp);
    }

    public ErrorResponse(String timestamp, String message, int status) {
        this.timestamp = timestamp;
        this.message = message;
        this.status = status;
    }

    // getters & setters

    public String getTimestamp() { return timestamp; }
    public void setTimestamp(String timestamp) { this.timestamp = timestamp; }

    public String getMessage() { return message; }
    public void setMessage(String message) { this.message = message; }

    public int getStatus() { return status; }
    public void setStatus(int status) { this.status = status; }
}
