package com.example.demo.controller;


import com.example.demo.model.APIResponse;
import com.example.demo.model.TweetResponse;
import com.example.demo.model.UserResponse;
import com.example.demo.service.TweetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.math.BigInteger;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/tweet")
public class TweetController {

    @Autowired
    TweetService tweetService;


    @GetMapping("")
    public APIResponse<List<TweetResponse>> getAllTweet() {
        return tweetService.getAllTweetData();
    }

    @GetMapping("/findAll/user")
    public APIResponse<List<UserResponse>> getAllTweetUser() {
        return tweetService.getAllTweetUsers();
    }

    @GetMapping("/{id}")
    public APIResponse<TweetResponse> getTweetById(@PathVariable("id") BigInteger id) {
        return tweetService.getTweetById(id);
    }

    @GetMapping("/user")
    public APIResponse<UserResponse> getTweetUserByScreenName(@RequestParam("screenName") String screenName) {
        return tweetService.getTweetUserByScreenName(screenName);
    }

    @GetMapping("/links")
    public APIResponse<Map<BigInteger, List<String>>> getAllTweetLinks() {
        return tweetService.getAllLinks();
    }
}
