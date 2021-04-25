package com.example.androidbackend.models;

import com.example.androidbackend.entities.Card;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CategoryWithCardsModel {
    private String name;
    private int version;
    private int accessLevel;
    private List<CardWithoutCategoryModel> cards;
}
