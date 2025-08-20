package com.codingshuttle.linkedin.connections_service.event;

import java.io.Serializable;

public class AcceptConnectionRequestEvent implements Serializable {
    private Long senderId;
    private Long receiverId;

    public AcceptConnectionRequestEvent() {
    }

    public AcceptConnectionRequestEvent(Long senderId, Long receiverId) {
        this.senderId = senderId;
        this.receiverId = receiverId;
    }

    public Long getSenderId() {
        return senderId;
    }

    public void setSenderId(Long senderId) {
        this.senderId = senderId;
    }

    public Long getReceiverId() {
        return receiverId;
    }

    public void setReceiverId(Long receiverId) {
        this.receiverId = receiverId;
    }
}
