package com.example.androidbackend.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CardWithoutCategoryModel {
    private UUID id;
    private String name;
    private int version;
}
