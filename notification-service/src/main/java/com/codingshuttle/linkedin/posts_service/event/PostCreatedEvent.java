package com.codingshuttle.linkedin.posts_service.event;

import java.io.Serializable;

public class PostCreatedEvent implements Serializable {
    Long creatorId;
    String content;
    Long postId;

    public PostCreatedEvent() {
    }

    public PostCreatedEvent(Long creatorId, String content, Long postId) {
        this.creatorId = creatorId;
        this.content = content;
        this.postId = postId;
    }

    public void setCreatorId(Long creatorId) {
        this.creatorId = creatorId;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public void setPostId(Long postId) {
        this.postId = postId;
    }

    public Long getCreatorId() {
        return creatorId;
    }

    public String getContent() {
        return content;
    }

    public Long getPostId() {
        return postId;
    }
}
