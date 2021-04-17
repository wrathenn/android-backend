package com.example.androidbackend.services;

import com.example.androidbackend.entities.Category;
import com.example.androidbackend.models.CategoryNoIdModel;
import com.example.androidbackend.repositories.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class CategoryService {
    @Autowired
    CategoryRepository categoryRepository;

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
        Category newCategory = new Category(UUID.randomUUID(), category.getName(), category.isAdult(),
                category.getVersion(), category.getAccessLevel());
        categoryRepository.save(newCategory);
        return newCategory;
    }
}
