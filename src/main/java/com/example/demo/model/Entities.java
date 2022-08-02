package com.example.demo.model;

import lombok.Data;
import lombok.ToString;

import java.util.ArrayList;

@Data
@ToString
public class Entities {
    public Url url;
    public Description description;
    public ArrayList<Hashtag> hashtags;
    public ArrayList<Url> urls;
    public ArrayList<UserMention> user_mentions;
    public ArrayList<Medium> media;
}
