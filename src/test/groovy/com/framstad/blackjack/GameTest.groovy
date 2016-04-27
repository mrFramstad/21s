package com.framstad.blackjack

import spock.lang.Specification

class GameTest extends Specification {

    def "Sam wins a tie of blackjack" () {
        given:
            def sam = buildHand(1,11)
            def dealer = buildHand(1,11)
            def deck = new Deck()
            def game = new Game()

        when:
            def result = game.play(sam, dealer,deck)

        then:
            result == Game.SAM_WINS_WITH_BLACKJACK
    }

    def "dealer must draw an extra card on a tie of 20 and am wins"() {
        given:
            def sam = buildHand(10,10)
            def dealer = buildHand(10,10)
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
            def sam = buildHand(10,8)
            def dealer = buildHand(12,10)
            def deck = new Deck()
            def game = new Game()

        when:
            def result = game.play(sam, dealer,deck)

        then:
            result == Game.DEALER_WINS

    }

    def "Dealer wins with BLACJACK when Sam has 20" () {
        given:
            def sam = buildHand(10,12)
            def dealer = buildHand(1,11)
            def deck = new Deck()
            def game = new Game()

        when:
            def result = game.play(sam, dealer,deck)

        then:
            result == Game.DEALER_WINS_WITH_BLACKJACK
    }

    def "Sam wins with BLACJACK when dealer has initial 20" () {
        given:
            def sam = buildHand(1,13)
            def dealer = buildHand(12,11)
            def deck = new Deck()
            def game = new Game()

        when:
            def result = game.play(sam, dealer,deck)

        then:
            result == Game.SAM_WINS_WITH_BLACKJACK
    }

    def "Both players busts with a hand of 24" () {
        given:
            def sam = buildHand(8,8, 8)
            def dealer = buildHand(8,8,8)
            def deck = new Deck()
            def game = new Game()

        when:
            def result = game.play(sam, dealer,deck)

        then:
            result == Game.ALL_PLAYERS_BUSTED
    }

    def buildHand (def ... cards) {
        return new Hand(cards as Integer[])
    }
}
