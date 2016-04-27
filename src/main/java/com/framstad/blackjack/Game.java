package com.framstad.blackjack;


public final class Game {
    protected static final int SAM_WINS = 100;
    protected static final int SAM_WINS_WITH_BLACKJACK = 110;
    protected static final int DEALER_WINS = 200;
    protected static final int DEALER_WINS_WITH_BLACKJACK = 210;
    protected static final int ALL_PLAYERS_BUSTED = 300;

    public static void main(final String[] args ) {
        final Deck deck = new Deck();
        final Hand sam = new Hand(new Integer[] {deck.pick(), deck.pick()});
        final Hand dealer = new Hand(new Integer[] {deck.pick(), deck.pick()});
        final Game game = new Game();

        switch(game.play(sam, dealer, deck)) {
            case SAM_WINS:
                System.out.printf ("Sam WINS! Sam: %d, Dealer: %d.", sam.value(), dealer.value());
                break;

            case SAM_WINS_WITH_BLACKJACK:
                System.out.printf ("Sam has BLACKJACK! Sam: %d, Dealer: %d.", sam.value(), dealer.value());
                break;

            case DEALER_WINS:
                System.out.printf ("Dealer WINS! Sam: %d, Dealer: %d.", sam.value(), dealer.value());
                break;

            case DEALER_WINS_WITH_BLACKJACK:
                System.out.printf ("Dealer has BLACKJACK! Sam: %d, Dealer: %d.", sam.value(), dealer.value());
                break;

            default:
                System.out.printf ("Both players BUSTED: Sam: %d, Dealer: %d.", sam.value(), dealer.value());
        }
    }

    public int play(final Hand sam, final Hand dealer, final Deck deck) {
        if (sam.hasBlackJack())     {return SAM_WINS_WITH_BLACKJACK;}
        if (dealer.hasBlackJack())  {return DEALER_WINS_WITH_BLACKJACK;}

        playSingleHand(sam, Hand.MIN_HAND_VALUE, deck);
        playSingleHand(dealer, sam.value() + 1, deck);

        if (sam.hasBusted() && dealer.hasBusted()) {return ALL_PLAYERS_BUSTED;}

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
