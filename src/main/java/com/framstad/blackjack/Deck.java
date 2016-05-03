package com.framstad.blackjack;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.stream.IntStream;

public final class Deck {

    final List<Integer> deck = new ArrayList<>();

    public Deck() {
        shuffle();
    }

    public void shuffle() {
        IntStream.range(0, 52).forEach(card -> deck.add(card % 13 + 1));
        Collections.shuffle(deck, new Random(System.nanoTime()));
    }

    public int pick() {
        if (deck.isEmpty()) {
            shuffle();
        }
        return deck.remove(0);
    }

    public int cardsLeft() {
        return deck.size();
    }
}
