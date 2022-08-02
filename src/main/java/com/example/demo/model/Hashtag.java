package com.example.demo.model;

import lombok.Data;
import lombok.ToString;

import java.util.ArrayList;

@Data
@ToString
public class Hashtag {
    public String text;
    public ArrayList<Integer> indices;
}
