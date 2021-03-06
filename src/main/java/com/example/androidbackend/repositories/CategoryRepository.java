package com.example.androidbackend.repositories;

import com.example.androidbackend.entities.Card;
import com.example.androidbackend.entities.Category;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface CategoryRepository extends JpaRepository<Category, UUID> {
    public Category findByName(String name);
}
