package com.example.demo.model;

import lombok.Data;
import lombok.ToString;

import java.util.ArrayList;

@Data
@ToString
public class BoundingBox {
    public String type;
    public ArrayList<ArrayList<ArrayList<Double>>> coordinates;
}
