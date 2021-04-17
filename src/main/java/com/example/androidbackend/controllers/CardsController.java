package com.example.androidbackend.controllers;

import com.example.androidbackend.models.CardNoIdWithCategoryNameModel;
import com.example.androidbackend.models.CategoryWithCardsModel;
import com.example.androidbackend.services.CardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/cards")
public class CardsController {
    @Autowired
    private CardService cardService;

    @GetMapping()
    public List<CategoryWithCardsModel> getNewCards(@RequestParam int version) {
        return cardService.getCardsByCategory(version);
    }

    @PutMapping()
    public void createCard(@RequestBody CardNoIdWithCategoryNameModel card) {
        cardService.createNewCard(card);
    }
}
