package com.example.demo.model;

import lombok.Data;
import lombok.ToString;

import java.util.ArrayList;

@Data
@ToString
public class Place {
    public String id;
    public String url;
    public String place_type;
    public String name;
    public String full_name;
    public String country_code;
    public String country;
    public ArrayList<Object> polylines;
    public BoundingBox bounding_box;
}
