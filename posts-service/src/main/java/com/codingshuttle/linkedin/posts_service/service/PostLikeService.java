package com.codingshuttle.linkedin.posts_service.service;

import com.codingshuttle.linkedin.posts_service.auth.UserContextHolder;
import com.codingshuttle.linkedin.posts_service.entity.Post;
import com.codingshuttle.linkedin.posts_service.entity.PostLike;
import com.codingshuttle.linkedin.posts_service.event.PostLikedEvent;
import com.codingshuttle.linkedin.posts_service.exception.BadRequestException;
import com.codingshuttle.linkedin.posts_service.exception.ResourceNotFoundException;
import com.codingshuttle.linkedin.posts_service.repository.PostLikeRepository;
import com.codingshuttle.linkedin.posts_service.repository.PostsRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class PostLikeService {

    private final PostLikeRepository postLikeRepository;
    private final PostsRepository postsRepository;
    private final KafkaTemplate<Long, PostLikedEvent> kafkaTemplate;

    @Transactional
    public void likePost(Long postId) {
        Long userId = UserContextHolder.getCurrentUserId();
        userId = 3L;
        log.info("Attempting to like the post with id:{} ", postId);

        Post post = postsRepository.findById(postId).orElseThrow(() ->
                new ResourceNotFoundException("post not found with id: " + postId));

        boolean alreadyLiked = postLikeRepository.existsByUserIdAndPostId(userId, postId);
        if (alreadyLiked) throw new BadRequestException("cannot liked the same post again");

        PostLike postLike = new PostLike();
        postLike.setPostId(postId);
        postLike.setUserId(userId);
        postLikeRepository.save(postLike);
        log.info("post with id: {} liked successfully ", postId);

        PostLikedEvent postLikedEvent = new PostLikedEvent();
        postLikedEvent.setPostId(postId);
        postLikedEvent.setCreatorId(userId);
        postLikedEvent.setCreatorId(post.getUserId());

        kafkaTemplate.send("post-liked-topic", postId, postLikedEvent);
    }

    @Transactional
    public void unlikePost(Long postId) {
        Long userId = UserContextHolder.getCurrentUserId();
        log.info("Attempting to unlike the post with id:{} ", postId);
        boolean exists = postsRepository.existsById(postId);
        if (!exists) throw new ResourceNotFoundException("post not found with id: " + postId);

        boolean alreadyLiked = postLikeRepository.existsByUserIdAndPostId(userId, postId);
        if (!alreadyLiked) throw new BadRequestException("cannot unlike the post which is not liked ");

        postLikeRepository.deleteByUserIdAndPostId(userId, postId);
        log.info("post with id: {} unliked successfully ", postId);
    }
}
