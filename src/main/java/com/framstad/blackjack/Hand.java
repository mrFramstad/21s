package com.framstad.blackjack;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public final class Hand {
    protected final static int MIN_HAND_VALUE = 17;
    protected final static int MAX_HAND_VALUE = 21;

    private List<Card> cards = new ArrayList<>();

    public Hand(final Card[] cards) {
        Arrays.asList(cards).forEach(this::addCard);
    }

    public void addCard(final Card card) {
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

    private int cardValue(final Card card) {
        return card.value() == 1 ? 11 : Integer.min(card.value(), 10);
    }

    @Override
    public String toString() {
        return cards.stream()
             .map(card -> String.format("[%s:%d]",card.suit().value(), card.value()))
             .collect(Collectors.joining());
    }
}


