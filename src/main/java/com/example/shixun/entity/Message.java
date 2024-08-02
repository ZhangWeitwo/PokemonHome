package com.example.shixun.entity;

import java.time.LocalDateTime;

public class Message {
    private int messageID;
    private String message;
    private String senderName;
    private LocalDateTime time;

    public Message() {}

    // Parameterized constructor
    public Message(int messageID, String message, String senderName, LocalDateTime time) {
        this.messageID = messageID;
        this.message = message;
        this.senderName = senderName;
        this.time = time;
    }

    // Getters and Setters
    public int getMessageID() {
        return messageID;
    }

    public void setMessageID(int messageID) {
        this.messageID = messageID;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getSenderName() {
        return senderName;
    }

    public void setSenderName(String senderName) {
        this.senderName = senderName;
    }

    public LocalDateTime getTime() {
        return time;
    }

    public void setTime(LocalDateTime time) {
        this.time = time;
    }

    // toString method for easy printing
    @Override
    public String toString() {
        return "Message{" +
                "messageID=" + messageID +
                ", message='" + message + '\'' +
                ", senderName=" + senderName +
                ", time=" + time +
                '}';
    }
}
