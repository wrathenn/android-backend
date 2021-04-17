package com.example.androidbackend.controllers;

import com.example.androidbackend.entities.Category;
import com.example.androidbackend.models.CategoryNoIdModel;
import com.example.androidbackend.services.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequestMapping("/categories")
public class CategoryController {
    @Autowired
    CategoryService categoryService;

    @GetMapping
    public int getLastVersion() {
        return categoryService.getLastVersion();
    }

    @PostMapping
    public Category createNewCategory(@RequestBody CategoryNoIdModel category) {
        return categoryService.createNewCategory(category);
    }
}
