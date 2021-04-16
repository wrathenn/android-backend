package com.example.androidbackend.entities;

import com.example.androidbackend.models.CardWithoutCategoryModel;
import com.example.androidbackend.models.CategoryWithCardsModel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(schema = "public", name = "card_categories")
public class Category {
    @Id
    private UUID id;
    private String name;
    private boolean isAdult;
    private int version;
    private int accessLevel;
}
