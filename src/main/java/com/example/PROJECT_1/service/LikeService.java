package com.example.PROJECT_1.service;


import com.example.PROJECT_1.entity.Post;
import com.example.PROJECT_1.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

@Service
public class LikeService {

    @Autowired
    private RedisTemplate<String, String> redisTemplate;

    public void recordLike(Integer postId) {
        String key = "post:like:" + postId;
        redisTemplate.opsForValue().increment(key);
    }

}