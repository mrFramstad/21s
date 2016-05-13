package com.framstad.blackjack;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.io.File;

public final class Deck {

    final List<Card> deck;

    final Charset charset = Charset.forName("UTF-8");

    public Deck() {
        deck = new ArrayList<Card>();

        deck.addAll(createCardsOf(Suit.Clubs));
        deck.addAll(createCardsOf(Suit.Diamonds));
        deck.addAll(createCardsOf(Suit.Hearts));
        deck.addAll(createCardsOf(Suit.Spades));

        Collections.shuffle(deck);
    }

    public Deck(final String deckFile) throws IOException {
        deck = Files.readAllLines(Paths.get(deckFile), charset).stream()
                    .map(card -> new Card(Suit.valueOf(card.charAt(0)), Integer.valueOf(card.substring(1))))
                    .collect(Collectors.toList());
    }

    public Card pick() {
        if (deck.isEmpty()) {
            throw new RuntimeException("Deck is empty...");
        }
        return deck.remove(0);
    }

    public int cardsLeft() {
        return deck.size();
    }

    private List<Card> createCardsOf (Suit suit) {
        return IntStream.range(1, 14).boxed()
                 .map(value -> new Card(suit, value))
                 .collect(Collectors.toList());
    }
}
