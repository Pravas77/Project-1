package com.example.PROJECT_1.scheduler;

import com.example.PROJECT_1.entity.Post;
import com.example.PROJECT_1.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.Set;

@Component
public class LikeAggregator {

    @Autowired
    private RedisTemplate<String, String> redisTemplate;

    @Autowired
    private PostRepository repo;


    @Scheduled(fixedRate = 30000)
    public void aggregateLikes() {

        System.out.println("ALWAYS    ALWAYS   ALWAYS");
        Set<String> keys = redisTemplate.keys("post:like:*");
        if (keys == null || keys.isEmpty()) return;

        for (String key : keys) {

            Long postId = Long.valueOf(key.substring(10));
            String likeCountStr = redisTemplate.opsForValue().get(key);
            Long count = Long.valueOf(likeCountStr);

            System.out.println("START    " +  key  + "    START");
            Long currentStoredCount = (long) 0L;
            Post obj = repo.findById(postId).orElse(null);
            if(obj != null) currentStoredCount = obj.getLikeCount();


            Post currentPost = new Post();
            currentPost.setId(postId);
            currentPost.setLikeCount(currentStoredCount + count);
            currentPost.setTitle("XYZ");
            repo.save(currentPost);
            redisTemplate.delete(key);
            System.out.println("End    " +  key  + "    End");


        }
    }

}
