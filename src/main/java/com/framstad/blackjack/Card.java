package com.framstad.blackjack;

public class Card {
    private final Suit suit;
    private final int value;

    public Card(final Suit suit, final int value) {
        if (value < 0 || value > 13) {
            throw new IllegalArgumentException(String.format("Invalid card value: %d. Must be in the range [1..13]", value));
        }
        this.suit = suit;
        this.value = value;
    }

    public Suit suit() {
        return suit;
    }

    public int value() {
        return value;
    }
}

