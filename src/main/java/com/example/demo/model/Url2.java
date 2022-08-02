package com.example.demo.model;

import lombok.Data;
import lombok.ToString;

import java.util.ArrayList;

@Data
@ToString
public class Url2 {
    public String url;
    public String expanded_url;
    public ArrayList<Integer> indices;
    public String display_url;
}
