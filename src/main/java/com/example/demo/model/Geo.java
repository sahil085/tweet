package com.example.demo.model;

import lombok.Data;
import lombok.ToString;

import java.util.ArrayList;

@Data
@ToString
public class Geo {
    public String type;
    public ArrayList<Double> coordinates;
}
