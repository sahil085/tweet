package com.example.demo.controller;


import com.example.demo.model.APIResponse;
import com.example.demo.model.TweetResponse;
import com.example.demo.model.UserResponse;
import com.example.demo.service.TweetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.math.BigInteger;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1")
public class TweetController {

    @Autowired
    TweetService tweetService;


    @GetMapping("/tweets")
    public APIResponse<List<TweetResponse>> getAllTweet() {
        List<TweetResponse> tweetResponseList = tweetService.getAllTweetData();
        if (tweetResponseList.isEmpty()) {
            return APIResponse.<List<TweetResponse>>builder()
                    .message("No content found")
                    .httpCode(HttpStatus.NO_CONTENT.value())
                    .body(tweetResponseList)
                    .build();
        } else {
            return APIResponse.<List<TweetResponse>>builder()
                    .message("Success")
                    .httpCode(HttpStatus.OK.value())
                    .body(tweetResponseList)
                    .build();
        }

    }

    @GetMapping("/tweets/users")
    public APIResponse<List<UserResponse>> getAllTweetUser() {

        List<UserResponse> userResponseList = tweetService.getAllTweetUsers();

        if (userResponseList.isEmpty()) {
           return APIResponse.<List<UserResponse>>builder()
                    .message("No content found")
                    .httpCode(HttpStatus.NO_CONTENT.value())
                    .body(userResponseList)
                    .build();
        } else {
            return APIResponse.<List<UserResponse>>builder()
                    .message("Success")
                    .httpCode(HttpStatus.OK.value())
                    .body(userResponseList)
                    .build();
        }
    }

    @GetMapping("/tweet/{id}")
    public APIResponse<TweetResponse> getTweetById(@PathVariable("id") BigInteger id) {
        TweetResponse tweetResponse = tweetService.getTweetById(id);

        if (tweetResponse != null) {
            return APIResponse.<TweetResponse>builder()
                    .message("Success")
                    .httpCode(HttpStatus.OK.value())
                    .body(tweetResponse)
                    .build();
        } else {
            return APIResponse.<TweetResponse>builder()
                    .message("Tweet not found for given id")
                    .httpCode(HttpStatus.NOT_FOUND.value())
                    .build();
        }
    }

    @GetMapping("/tweet/user")
    public APIResponse<UserResponse> getTweetUserByScreenName(@RequestParam("screenName") String screenName) {
        UserResponse userResponse = tweetService.getTweetUserByScreenName(screenName);
        if (userResponse != null) {
            return APIResponse.<UserResponse>builder()
                    .message("Success")
                    .httpCode(HttpStatus.OK.value())
                    .body(userResponse)
                    .build();
        } else {
            return APIResponse.<UserResponse>builder()
                    .message("Tweet user not found for given screen name")
                    .httpCode(HttpStatus.NOT_FOUND.value())
                    .build();
        }
    }

    @GetMapping("/tweet/links")
    public APIResponse<Map<BigInteger, List<String>>> getAllTweetLinks() {

        Map<BigInteger, List<String>> tweetMap = tweetService.getAllLinks();

        if (tweetMap.isEmpty()) {
            return APIResponse.<Map<BigInteger, List<String>>>builder()
                    .message("No content found")
                    .httpCode(HttpStatus.NO_CONTENT.value())
                    .body(tweetMap)
                    .build();
        } else {
            return APIResponse.<Map<BigInteger, List<String>>>builder()
                    .message("Success")
                    .httpCode(HttpStatus.OK.value())
                    .body(tweetMap)
                    .build();
        }

    }
}
