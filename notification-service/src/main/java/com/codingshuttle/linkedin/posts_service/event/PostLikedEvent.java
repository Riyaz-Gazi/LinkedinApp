package com.codingshuttle.linkedin.posts_service.event;

import java.io.Serializable;

public class PostLikedEvent implements Serializable {
    Long postId;
    Long creatorId;
    Long likedByUserId;

    public PostLikedEvent() {
    }

    public Long getPostId() {
        return postId;
    }

    public void setPostId(Long postId) {
        this.postId = postId;
    }

    public Long getCreatorId() {
        return creatorId;
    }

    public void setCreatorId(Long creatorId) {
        this.creatorId = creatorId;
    }

    public Long getLikedByUserId() {
        return likedByUserId;
    }

    public void setLikedByUserId(Long likedByUserId) {
        this.likedByUserId = likedByUserId;
    }
}
