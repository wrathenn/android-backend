package com.example.androidbackend.models;

import lombok.Data;

@Data
public class CategoryNoIdModel {
    private String name;
    private boolean isAdult;
    private int version;
    private int accessLevel;
}
