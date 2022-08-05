package com.example.demo.service.impl;

import com.example.demo.model.Root;
import com.example.demo.model.TweetResponse;
import com.example.demo.model.UserResponse;
import com.example.demo.service.TweetService;
import com.example.demo.util.TweetData;
import org.springframework.beans.factory.annotation.Autowired;
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
    public List<TweetResponse> getAllTweetData() {
        return mapDataToTweetResponse(tweetData.getTweetData());
    }

    @Override
    public List<UserResponse> getAllTweetUsers() {

        return mapDataToUserResponse(tweetData.getTweetData());
    }

    @Override
    public Map<BigInteger, List<String>> getAllLinks() {

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

        return tweetMap;
    }

    @Override
    public TweetResponse getTweetById(BigInteger id) {
        Optional<Root> optionalRoot = tweetData.getTweetData().stream()
                .filter(tweet -> tweet.getId().equals(id))
                .findFirst();
        return optionalRoot.map(this::getTweetResponse).orElse(null);
    }

    @Override
    public UserResponse getTweetUserByScreenName(String screenName) {
        Optional<Root> optionalRoot = tweetData.getTweetData().stream()
                .filter(tweet -> Objects.equals(tweet.getUser().getScreen_name(), screenName))
                .findFirst();
        return optionalRoot.map(this::getUserResponse).orElse(null);
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
