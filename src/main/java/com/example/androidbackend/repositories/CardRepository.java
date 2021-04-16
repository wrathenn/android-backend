package com.example.androidbackend.repositories;

import com.example.androidbackend.entities.Card;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface CardRepository extends JpaRepository<Card, UUID> {
    public List<Card> findByCategoryVersionGreaterThan(int categoryVersion);
}
