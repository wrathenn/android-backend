package com.example.androidbackend.entities;

import com.example.androidbackend.models.CardWithoutCategoryModel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(schema = "public", name = "cards")
public class Card {
    @Id
    private UUID id;
    private String name;
    private int version;
    @ManyToOne
    @JoinColumn(name = "category_id", referencedColumnName = "id")
    private Category category;

    public CardWithoutCategoryModel toCardWithoutCategoryModel() {
        return new CardWithoutCategoryModel(id, name, version);
    }
}
