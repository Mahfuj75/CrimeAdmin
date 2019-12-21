package com.onenation.oneworld.mahfuj75.crimeadmin.objectClass;

/**
 * Created by mahfu on 3/18/2017.
 */

public class Message {

    private String message;
    private String uid;
    private String messageDate;
    private String messageTime;

    public Message() {
    }

    public Message(String message, String uid, String messageDate, String messageTime) {
        this.message = message;
        this.uid = uid;
        this.messageDate = messageDate;
        this.messageTime = messageTime;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getMessageDate() {
        return messageDate;
    }

    public void setMessageDate(String messageDate) {
        this.messageDate = messageDate;
    }

    public String getMessageTime() {
        return messageTime;
    }

    public void setMessageTime(String messageTime) {
        this.messageTime = messageTime;
    }
}
