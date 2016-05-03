package com.framstad.blackjack;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public final class Hand {
    protected final static int MIN_HAND_VALUE = 17;
    protected final static int MAX_HAND_VALUE = 21;

    private List<Integer> cards = new ArrayList<>();

    public Hand(final Integer[] cards) {
        Arrays.asList(cards).forEach(this::addCard);
    }

    public void addCard(final int card) {
        cards.add(card);
    }

    public int value() {
        return cards.stream().mapToInt(this::cardValue).sum();
    }

    public boolean hasBlackJack() {
        return cards.size() == 2 && value() == MAX_HAND_VALUE;
    }

    public boolean hasBusted() {
        return value() > MAX_HAND_VALUE;
    }

    private int cardValue(final int value) {
        return value == 1 ? 11 : Integer.min(value, 10);
    }
}


