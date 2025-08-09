package com.example.PROJECT_1.controller;


import com.example.PROJECT_1.service.LikeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/posts")
public class PostController {

    @Autowired
    private LikeService service;

    @PostMapping("/{postId}/like")
    public String likePost(@PathVariable Integer postId) {
        service.recordLike(postId);
        return "Liked successfully!!!";
    }

}

