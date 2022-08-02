package com.example.demo.model;

import lombok.Data;
import lombok.ToString;

import java.util.ArrayList;

@Data
@ToString
public class Medium {
    public long id;
    public String id_str;
    public ArrayList<Integer> indices;
    public String media_url;
    public String media_url_https;
    public String url;
    public String display_url;
    public String expanded_url;
    public String type;
    public Sizes sizes;
}
