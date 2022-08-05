package com.example.demo;

import com.example.demo.model.Root;
import com.example.demo.model.TweetResponse;
import com.example.demo.model.UserResponse;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TweetData {

    public static List<UserResponse> getAllTweetUsers(List<Root> tweetList) {
        List<UserResponse> userResponseList = new ArrayList<>();

        tweetList.forEach(tweet -> {
            UserResponse userResponse = getUserResponse(tweet);
            userResponseList.add(userResponse);
        });

        return userResponseList;
    }

    public static List<TweetResponse> getAllTweets(List<Root> tweetList) {
        List<TweetResponse> tweetResponseList = new ArrayList<>();

        tweetList.forEach(tweet -> {
            tweetResponseList.add(getTweetResponse(tweet));
        });

        return tweetResponseList;
    }

    public static TweetResponse getTweetResponse(Root tweet) {
        TweetResponse tweetResponse = new TweetResponse();
        tweetResponse.setTweetText(tweet.getText());
        tweetResponse.setId(tweet.getId());
        tweetResponse.setCreatedDate(tweet.getCreated_at());
        return tweetResponse;
    }

    public static UserResponse getUserResponse(Root tweet) {
        UserResponse userResponse = new UserResponse();
        userResponse.setTweetId(tweet.getId());
        userResponse.setUserId(tweet.getUser().getId());
        userResponse.setCreatedDate(tweet.getCreated_at());
        userResponse.setLocation(tweet.getUser().getLocation());
        userResponse.setScreenName(tweet.getUser().getScreen_name());
        return userResponse;
    }

    public static Map<BigInteger, List<String>> getAllLinks(List<Root> rootList) {

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
}
