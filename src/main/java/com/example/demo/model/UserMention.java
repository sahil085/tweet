package com.example.demo.model;

import lombok.Data;
import lombok.ToString;

import java.util.ArrayList;

@Data
@ToString
public class UserMention {
    public String screen_name;
    public String name;
    public int id;
    public String id_str;
    public ArrayList<Integer> indices;
}
