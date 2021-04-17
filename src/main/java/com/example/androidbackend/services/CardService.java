package com.example.androidbackend.services;

import com.example.androidbackend.entities.Card;
import com.example.androidbackend.entities.Category;
import com.example.androidbackend.models.CardNoIdWithCategoryNameModel;
import com.example.androidbackend.models.CategoryWithCardsModel;
import com.example.androidbackend.repositories.CardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class CardService {
    @Autowired
    CardRepository cardRepository;
    @Autowired
    CategoryService categoryService;

    public List<CategoryWithCardsModel> getCardsByCategory(int version) {
        List<Card> cards = cardRepository.findByCategoryVersionGreaterThan(version);
        return cards.stream().collect(Collectors.groupingBy(Card::getCategory))
                .entrySet().stream().map(it -> new CategoryWithCardsModel(
                        it.getKey().getName(),
                        it.getKey().isAdult(),
                        it.getKey().getVersion(),
                        it.getKey().getAccessLevel(),
                        it.getValue().stream().map(Card::toCardWithoutCategoryModel)
                                .collect(Collectors.toList())
                )).collect(Collectors.toList());
    }

    public void createNewCard(CardNoIdWithCategoryNameModel card) {
        Category category = categoryService.findByName(card.getCategoryName());
        if (category != null) {
            cardRepository.save(new Card(UUID.randomUUID(), card.getName(), card.getVersion(), category));
            if (category.getVersion() < card.getVersion()) {
                categoryService.setCategoryVersion(category, card.getVersion());
            }
        }
        System.out.println("asd");
    }
}