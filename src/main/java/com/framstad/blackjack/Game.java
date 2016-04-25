package com.framstad.blackjack;


import java.util.ArrayList;
import java.util.Arrays;

public class Game {
    public static void main( String[] args ) {
        Deck deck = new Deck();
        Hand sam = new Hand(deck);
        Hand dealer = new Hand(deck);
        play(sam, dealer, deck);
    }

    public static void play(Hand sam, Hand dealer, Deck deck) {
        if (sam.hasBlackJack())     {System.out.println("Sam has BLACKJACK. Sam wins.");}
        if (dealer.hasBlackJack())  {System.out.println("Dealer has BLACKJACK. Dealer wins.");}

        playSingleHand(sam, Hand.MIN_HAND_VALUE, deck);
        if (sam.hasBusted()) {
            System.out.printf("Sam busted. HandValue: %d. Dealer wins." ,sam.handValue());
            return;

        } else {
            playSingleHand(dealer, sam.handValue() + 1, deck);
            if (dealer.hasBusted()) {
                System.out.printf("Dealer busted. HandValue: %d. Sam wins" ,dealer.handValue());
                return;
            }
        }

        System.out.printf ("Sam: %d, Dealer: %d. %s wins.", sam.handValue(), dealer.handValue(), sam.handValue() > dealer.handValue() ?
                "Sam" : "Dealer" );
    }

    private static void playSingleHand(Hand hand, int handMinValue, Deck deck) {
        while (hand.handValue() < handMinValue) {
            hand.addCard(deck.pick());
        }
    }
}
