package com.framstad.blackjack;

import java.util.ArrayList;
import java.util.List;

public class Hand {
    private List<Integer> cards  = new ArrayList<>();
    protected final static int MIN_HAND_VALUE = 17;
    protected final static int MAX_HAND_VALUE = 21;

    public Hand(Deck deck) {
        addCard(deck.pick());
        addCard(deck.pick());
    }

    public void addCard (int card) {
        cards.add(card);
    }

    public int handValue () {
        return cards.stream().mapToInt(this::caclculateCardValue).sum();
    }

    public boolean hasBlackJack () {
        return cards.size() == 2 && handValue() == MAX_HAND_VALUE;
    }

    public boolean hasBusted () {
        return handValue() > MAX_HAND_VALUE;
    }

    public void printHand () {
        cards.stream().forEach(card -> System.out.println(", " + card));
    }

    private int caclculateCardValue (int card) {
        if (card == 1) {
            return 11;
        } else if (card > 9) {
            return 10;
        }
        return card;
    }




}


