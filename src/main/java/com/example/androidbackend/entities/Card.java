package com.example.androidbackend.entities;

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
@Table(schema = "public", name = "cards")
public class Card {
    @Id
    private UUID id;
    private String name;

    public boolean cmp(Card card) {
        return this.id.equals(card.id) && this.name.equals(card.name);
    }
}
