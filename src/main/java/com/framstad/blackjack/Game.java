package com.framstad.blackjack;


import java.io.IOException;

public final class Game {
    protected static final int SAM_WINS = 100;
    protected static final int SAM_WINS_WITH_BLACKJACK = 110;
    protected static final int DEALER_WINS = 200;
    protected static final int DEALER_WINS_WITH_BLACKJACK = 210;
    protected static final int ALL_PLAYERS_BUSTED = 300;

    public static void main(final String[] args) throws IOException {
        System.out.println("######## LET'S PLAY BLACKJACK! ########");

        final Deck deck = args.length == 0 ? new Deck() : new Deck(args[0]);

        final Hand sam = new Hand(new Card[]{deck.pick(), deck.pick()});
        final Hand dealer = new Hand(new Card[]{deck.pick(), deck.pick()});
        final Game game = new Game();
        String result;

        switch (game.play(sam, dealer, deck)) {
            case SAM_WINS:
                result = String.format("Sam WINS! Sam: %d, Dealer: %d.", sam.value(), dealer.value());
                break;

            case SAM_WINS_WITH_BLACKJACK:
                result = String.format("Sam has BLACKJACK! Sam: %d, Dealer: %d.", sam.value(), dealer.value());
                break;

            case DEALER_WINS:
                result = String.format("Dealer WINS! Sam: %d, Dealer: %d.", sam.value(), dealer.value());
                break;

            case DEALER_WINS_WITH_BLACKJACK:
                result = String.format("Dealer has BLACKJACK! Sam: %d, Dealer: %d.", sam.value(), dealer.value());
                break;

            default:
                result = String.format("Both players BUSTED: Sam: %d, Dealer: %d.", sam.value(), dealer.value());
        }

        System.out.println(String.format("Sam's hand: %s", sam.toString()));
        System.out.println(String.format("Dealer's hand: %s", dealer.toString()));
        System.out.println(result);
        System.out.println("#######################################");

    }

    public int play(final Hand sam, final Hand dealer, final Deck deck) {
        if (sam.hasBlackJack()) {
            return SAM_WINS_WITH_BLACKJACK;
        }
        if (dealer.hasBlackJack()) {
            return DEALER_WINS_WITH_BLACKJACK;
        }

        playSingleHand(sam, Hand.MIN_HAND_VALUE, deck);
        playSingleHand(dealer, sam.value() + 1, deck);

        if (sam.hasBusted() && dealer.hasBusted()) {
            return ALL_PLAYERS_BUSTED;
        }

        return winningHand(sam, dealer);
    }

    private int winningHand(final Hand sam, final Hand dealer) {
        int samValue = sam.hasBusted() ? 0 : sam.value();
        int dealerValue = dealer.hasBusted() ? 0 : dealer.value();

        return samValue >= dealerValue ? SAM_WINS : DEALER_WINS;
    }

    private void playSingleHand(final Hand hand, final int handMinValue, final Deck deck) {
        while (hand.value() < handMinValue) {
            hand.addCard(deck.pick());
        }
    }
}
