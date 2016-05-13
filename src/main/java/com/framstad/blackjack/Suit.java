package com.framstad.blackjack;

public enum Suit {
    Clubs('C'),
    Diamonds('D'),
    Hearts('H'),
    Spades('S');

    private char suit;

    Suit(char suit) {
        this.suit = suit;
    }

    public char value() {
        return suit;
    }

    public static Suit valueOf (final char suit) {
        switch (suit) {
            case 'C':
                return Suit.Clubs;
            case 'D':
                return Suit.Diamonds;
            case 'H':
                return Suit.Hearts;
            case 'S':
                return Suit.Spades;
            default:
                throw new IllegalArgumentException(String.format("Invalid Suit: %s. Valid suits are ['S','D','H','S']", suit));
        }
    }
}
