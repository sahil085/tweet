package com.example.demo.model;

import lombok.Data;
import lombok.ToString;

import java.util.ArrayList;

@Data
@ToString
public class Url {
    String expanded_url;
    String display_url;
    String url;
    ArrayList<Integer> indices;

}
