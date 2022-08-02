package com.example.demo.service;

import com.example.demo.model.APIResponse;
import com.example.demo.model.TweetResponse;
import com.example.demo.model.UserResponse;

import java.math.BigInteger;
import java.util.List;
import java.util.Map;

public interface TweetService {

    APIResponse<List<TweetResponse>> getAllTweetData();

    APIResponse<List<UserResponse>> getAllTweetUsers();

    APIResponse<Map<BigInteger, List<String>>> getAllLinks();

    APIResponse<TweetResponse> getTweetById(BigInteger id);

    APIResponse<UserResponse> getTweetUserByScreenName(String screenName);

}
