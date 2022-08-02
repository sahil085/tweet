package com.example.demo.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class User {
    public Long id;
    public String id_str;
    public String name;
    public String screen_name;
    public String location;
    public String description;
    public String url;
    public Entities entities;
    @JsonProperty("protected")
    public boolean myprotected;
    public int followers_count;
    public int friends_count;
    public int listed_count;
    public String created_at;
    public int favourites_count;
    public int utc_offset;
    public String time_zone;
    public boolean geo_enabled;
    public boolean verified;
    public int statuses_count;
    public String lang;
    public boolean contributors_enabled;
    public boolean is_translator;
    public String profile_background_color;
    public String profile_background_image_url;
    public String profile_background_image_url_https;
    public boolean profile_background_tile;
    public String profile_image_url;
    public String profile_image_url_https;
    public String profile_link_color;
    public String profile_sidebar_border_color;
    public String profile_sidebar_fill_color;
    public String profile_text_color;
    public boolean profile_use_background_image;
    public boolean default_profile;
    public boolean default_profile_image;
    public Object following;
    public boolean follow_request_sent;
    public Object notifications;
    public String profile_banner_url;
}
