package com.example.demo.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;

import java.math.BigInteger;
import java.util.Date;

// import com.fasterxml.jackson.databind.ObjectMapper; // version 2.11.1
// import com.fasterxml.jackson.annotation.JsonProperty; // version 2.11.1
/* ObjectMapper om = new ObjectMapper();
Root[] root = om.readValue(myJsonString, Root[].class); */

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Root {

    @JsonFormat(pattern = "EEE MMM d HH:mm:ss Z yyyy")
    public Date created_at;
    public BigInteger id;
    public String id_str;
    public String text;
    public String source;
    public boolean truncated;
    public Object in_reply_to_status_id;
    public Object in_reply_to_status_id_str;
    public Object in_reply_to_user_id;
    public Object in_reply_to_user_id_str;
    public Object in_reply_to_screen_name;
    public User user;
    public Geo geo;
    public Coordinates coordinates;
    public Place place;
    public Object contributors;
    public int retweet_count;
    public Entities entities;
    public boolean favorited;
    public boolean retweeted;
    public boolean possibly_sensitive;
    public String lang;


}


