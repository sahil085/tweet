package com.example.demo.service.impl;

import com.example.demo.model.APIResponse;
import com.example.demo.model.Root;
import com.example.demo.model.TweetResponse;
import com.example.demo.model.UserResponse;
import com.example.demo.service.TweetService;
import com.example.demo.util.TweetData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.math.BigInteger;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class TweetServiceImpl implements TweetService {


    @Autowired
    TweetData tweetData;

    private TweetResponse getTweetResponse(Root tweet) {
        TweetResponse tweetResponse = new TweetResponse();
        tweetResponse.setTweetText(tweet.getText());
        tweetResponse.setId(tweet.getId());
        tweetResponse.setCreatedDate(tweet.getCreated_at());
        return tweetResponse;
    }

    private UserResponse getUserResponse(Root tweet) {
        UserResponse userResponse = new UserResponse();
        userResponse.setTweetId(tweet.getId());
        userResponse.setUserId(tweet.getUser().getId());
        userResponse.setCreatedDate(tweet.getCreated_at());
        userResponse.setLocation(tweet.getUser().getLocation());
        userResponse.setScreenName(tweet.getUser().getScreen_name());
        return userResponse;
    }

    @Override
    public APIResponse<List<TweetResponse>> getAllTweetData() {

        return APIResponse.<List<TweetResponse>>builder()
                .message("Success")
                .httpCode(HttpStatus.OK.value())
                .body(mapDataToTweetResponse(tweetData.getTweetData()))
                .build();
    }

    @Override
    public APIResponse<List<UserResponse>> getAllTweetUsers() {

        return APIResponse.<List<UserResponse>>builder()
                .message("Success")
                .httpCode(HttpStatus.OK.value())
                .body(mapDataToUserResponse(tweetData.getTweetData()))
                .build();
    }

    @Override
    public APIResponse<Map<BigInteger, List<String>>> getAllLinks() {

        List<Root> rootList = tweetData.getTweetData();
        Map<BigInteger, List<String>> tweetMap = new HashMap<>();

        String urlRegex = "((https?|ftp|gopher|telnet|file):((//)|(\\\\))+[\\w\\d:#@%/;$()~_?\\+-=\\\\\\.&]*)";
        rootList.forEach(tweet -> {
            List<String> containedUrls = new ArrayList<String>();
            Pattern pattern = Pattern.compile(urlRegex, Pattern.CASE_INSENSITIVE);
            Matcher urlMatcher = pattern.matcher(tweet.toString());
            while (urlMatcher.find()) {
                containedUrls.add(tweet.toString().substring(urlMatcher.start(0),
                        urlMatcher.end(0)));
            }
            tweetMap.put(tweet.getId(), containedUrls);
        });

        return APIResponse.<Map<BigInteger, List<String>>>builder()
                .message("Success")
                .httpCode(HttpStatus.OK.value())
                .body(tweetMap)
                .build();
    }

    @Override
    public APIResponse<TweetResponse> getTweetById(BigInteger id) {
        Optional<Root> optionalRoot = tweetData.getTweetData().stream()
                .filter(tweet -> tweet.getId().equals(id))
                .findFirst();
        if (optionalRoot.isPresent()) {
            return APIResponse.<TweetResponse>builder()
                    .message("Success")
                    .httpCode(HttpStatus.OK.value())
                    .body(getTweetResponse(optionalRoot.get()))
                    .build();
        } else {
            return APIResponse.<TweetResponse>builder()
                    .message("Tweet not found for given id")
                    .httpCode(HttpStatus.NOT_FOUND.value())
                    .build();
        }
    }

    @Override
    public APIResponse<UserResponse> getTweetUserByScreenName(String screenName) {
        Optional<Root> optionalRoot = tweetData.getTweetData().stream()
                .filter(tweet -> Objects.equals(tweet.getUser().getScreen_name(), screenName))
                .findFirst();
        if (optionalRoot.isPresent()) {
            return APIResponse.<UserResponse>builder()
                    .message("Success")
                    .httpCode(HttpStatus.OK.value())
                    .body(getUserResponse(optionalRoot.get()))
                    .build();
        } else {
            return APIResponse.<UserResponse>builder()
                    .message("Tweet user not found for given screen name")
                    .httpCode(HttpStatus.NOT_FOUND.value())
                    .build();
        }
    }

    private List<TweetResponse> mapDataToTweetResponse(List<Root> tweetList) {
        List<TweetResponse> tweetResponseList = new ArrayList<>();

        tweetList.forEach(tweet -> {
            tweetResponseList.add(getTweetResponse(tweet));
        });

        return tweetResponseList;
    }

    private List<UserResponse> mapDataToUserResponse(List<Root> tweetList) {
        List<UserResponse> userResponseList = new ArrayList<>();

        tweetList.forEach(tweet -> {
            UserResponse userResponse = getUserResponse(tweet);
            userResponseList.add(userResponse);
        });

        return userResponseList;
    }
}
