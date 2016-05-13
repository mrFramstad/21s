package com.framstad.blackjack

import spock.lang.Specification

class GameTest extends Specification {

    def "Sam wins a tie of blackjack" () {
        given:
            def sam = buildHand(new Card(Suit.Diamonds, 1), new Card(Suit.Hearts, 11))
            def dealer = buildHand(new Card(Suit.Diamonds, 1), new Card(Suit.Hearts, 11))
            def deck = new Deck()
            def game = new Game()

        when:
            def result = game.play(sam, dealer,deck)

        then:
            result == Game.SAM_WINS_WITH_BLACKJACK
    }

    def "dealer must draw an extra card on a tie of 20 and am wins"() {
        given:
            def sam = buildHand(new Card(Suit.Diamonds, 10), new Card(Suit.Hearts, 10))
            def dealer = buildHand(new Card(Suit.Diamonds, 10), new Card(Suit.Hearts, 10))
            def deck = new Deck()
            def game = new Game()

        when:
            def result = game.play(sam, dealer,deck)

        then:
            result == Game.SAM_WINS
            dealer.hasBusted()
    }

    def "Dealer wins with 20 when Sam has 18" () {
        given:
            def sam = buildHand(new Card(Suit.Diamonds, 10), new Card(Suit.Hearts, 8))
            def dealer = buildHand(new Card(Suit.Diamonds, 12), new Card(Suit.Hearts, 10))
            def deck = new Deck()
            def game = new Game()

        when:
            def result = game.play(sam, dealer,deck)

        then:
            result == Game.DEALER_WINS

    }

    def "Dealer wins with BLACJACK when Sam has 20" () {
        given:
            def sam = buildHand(new Card(Suit.Diamonds, 11), new Card(Suit.Hearts, 11))
            def dealer = buildHand(new Card(Suit.Diamonds, 1), new Card(Suit.Hearts, 11))
            def deck = new Deck()
            def game = new Game()

        when:
            def result = game.play(sam, dealer,deck)

        then:
            result == Game.DEALER_WINS_WITH_BLACKJACK
    }

    def "Sam wins with BLACJACK when dealer has initial 20" () {
        given:
            def sam = buildHand(new Card(Suit.Diamonds, 1), new Card(Suit.Hearts, 13))
            def dealer = buildHand(new Card(Suit.Diamonds, 12), new Card(Suit.Hearts, 11))
            def deck = new Deck()
            def game = new Game()

        when:
            def result = game.play(sam, dealer,deck)

        then:
            result == Game.SAM_WINS_WITH_BLACKJACK
    }

    def "Both players busts with a hand of 24" () {
        given:
            def sam = buildHand(new Card(Suit.Diamonds, 8), new Card(Suit.Hearts, 8),new Card(Suit.Diamonds, 8))
            def dealer = buildHand(new Card(Suit.Diamonds, 8), new Card(Suit.Hearts, 8),new Card(Suit.Diamonds, 8))
            def deck = new Deck()
            def game = new Game()

        when:
            def result = game.play(sam, dealer,deck)

        then:
            result == Game.ALL_PLAYERS_BUSTED
    }

    def buildHand (def ... cards) {
        return new Hand(cards as Card[])
    }
}
