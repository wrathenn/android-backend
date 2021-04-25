package com.example.androidbackend.services;

import com.example.androidbackend.entities.Card;
import com.example.androidbackend.entities.Category;
import com.example.androidbackend.models.CardNoIdWithCategoryNameModel;
import com.example.androidbackend.repositories.CardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class CardService {
    @Autowired
    CardRepository cardRepository;
    @Autowired
    CategoryService categoryService;

    public void createNewCard(CardNoIdWithCategoryNameModel card) {
        Category category = categoryService.findByName(card.getCategoryName());
        if (category != null) {
            cardRepository.save(new Card(UUID.randomUUID(), card.getName(), card.getVersion(), category));
            if (category.getVersion() < card.getVersion()) {
                categoryService.setCategoryVersion(category, card.getVersion());
            }
        }
    }
}