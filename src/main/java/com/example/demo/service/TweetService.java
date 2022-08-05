package com.example.demo.service;

import com.example.demo.model.TweetResponse;
import com.example.demo.model.UserResponse;

import java.math.BigInteger;
import java.util.List;
import java.util.Map;

public interface TweetService {

    List<TweetResponse> getAllTweetData();

    List<UserResponse> getAllTweetUsers();

    Map<BigInteger, List<String>> getAllLinks();

    TweetResponse getTweetById(BigInteger id);

    UserResponse getTweetUserByScreenName(String screenName);

}
