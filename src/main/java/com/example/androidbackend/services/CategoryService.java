package com.example.androidbackend.services;

import com.example.androidbackend.entities.Card;
import com.example.androidbackend.entities.Category;
import com.example.androidbackend.models.CategoryNoIdModel;
import com.example.androidbackend.models.CategoryWithCardsModel;
import com.example.androidbackend.repositories.CardRepository;
import com.example.androidbackend.repositories.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class CategoryService {
    @Autowired
    CategoryRepository categoryRepository;
    @Autowired
    CardRepository cardRepository;

    public Category findByName(String name) {
        return categoryRepository.findByName(name);
    }

    public int getLastVersion() {
        List<Category> categories = categoryRepository.findAll();
        int lastVersion = 0;
        for (Category category : categories) {
            lastVersion = Math.max(category.getVersion(), lastVersion);
        }
        return lastVersion;
    }

    public void setCategoryVersion(Category category, int version) {
        category.setVersion(version);
        categoryRepository.save(category);
    }

    public Category createNewCategory(CategoryNoIdModel category) {
        Category newCategory = new Category(UUID.randomUUID(), category.getName(),
                category.getVersion(), category.getAccessLevel());
        categoryRepository.save(newCategory);
        return newCategory;
    }

    public List<CategoryWithCardsModel> getCardsByCategoryVersion(int version) {
        List<Card> cards = cardRepository.findByCategoryVersionGreaterThan(version);

        return cards.stream().collect(Collectors.groupingBy(Card::getCategory))
                .entrySet().stream().map(it -> new CategoryWithCardsModel(
                        it.getKey().getName(),
                        it.getKey().getVersion(),
                        it.getKey().getAccessLevel(),
                        it.getValue().stream().map(itCard -> itCard.getVersion() > version
                                ? itCard.toCardWithoutCategoryModel()
                                : null).collect(Collectors.toList())
                )).collect(Collectors.toList());
    }
}
