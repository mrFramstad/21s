package com.framstad.blackjack;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.stream.IntStream;

public final class Deck {

    List <Integer> deck = new ArrayList<>();

    public Deck () {
        IntStream.range(0, 52).forEach(card -> deck.add(card % 13 + 1));
        shuffle();
    }

    public void shuffle () {
        Collections.shuffle(deck, new Random(System.nanoTime()));
    }

    public int pick () {
        if (deck.isEmpty()) {
            shuffle();
        }
        return deck.remove(0);
    }

    public void printDeck () {
        deck.stream().forEach(card -> System.out.print(", " + card));
    }
}
