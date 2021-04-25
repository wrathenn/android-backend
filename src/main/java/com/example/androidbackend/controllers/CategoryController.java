package com.example.androidbackend.controllers;

import com.example.androidbackend.entities.Category;
import com.example.androidbackend.models.CategoryNoIdModel;
import com.example.androidbackend.models.CategoryWithCardsModel;
import com.example.androidbackend.services.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/categories")
public class CategoryController {
    @Autowired
    CategoryService categoryService;

    @GetMapping(value = "/version")
    public int getLastVersion() {
        return categoryService.getLastVersion();
    }

    @PostMapping
    public Category createNewCategory(@RequestBody CategoryNoIdModel category) {
        return categoryService.createNewCategory(category);
    }

    @GetMapping()
    public List<CategoryWithCardsModel> getNewCards(@RequestParam int version) {
        return categoryService.getCardsByCategoryVersion(version);
    }
}
