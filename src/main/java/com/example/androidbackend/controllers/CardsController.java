package com.example.androidbackend.controllers;

import com.example.androidbackend.entities.Card;
import com.example.androidbackend.repositories.CardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@CrossOrigin
@RequestMapping("/cards")
public class CardsController {
    @Autowired
    private CardRepository cardRepository;

    @GetMapping(value = "/load")
    public List<Card> getCards() {
        return cardRepository.findAll();
    }

    @PostMapping(value = "/check")
    // Нужно будет впилить быструю проверку по версиям
    public boolean checkCards(@RequestBody List<UUID> cardsId) {
        List<Card> cardsInStorage = cardRepository.findAll();
        if (cardsId.size() != cardsInStorage.size())
            return false;

        for (int i = 0; i < cardsId.size(); i++) {
            if (!cardsId.get(i).equals(cardsInStorage.get(i).getId()))
                return false;
        }

        return true;
    }

    @PostMapping(value = "/create")
    public Card createCard(@RequestBody String newCardName) {
        return cardRepository.save(new Card(UUID.randomUUID(), newCardName));
    }

    @DeleteMapping(value = "/delete/")
    public void deleteCardById(@RequestBody UUID id) {
        if (cardRepository.findById(id).isPresent()) {
            cardRepository.deleteById(id);
        }
    }

    @DeleteMapping(value = "/delete/name")
    public void deleteCardByName(@RequestBody String name) {
        for (Card card: cardRepository.findAll()) {
            if (card.getName().equals(name)) {
                cardRepository.delete(card);
                break;
            }
        }
    }

    @GetMapping(value="/test")
    public UUID test() {
        return cardRepository.findAll().get(0).getId();
    }
}
