package com.framstad.blackjack

import spock.lang.Specification
import spock.lang.Unroll

class HandTest extends Specification {

    @Unroll
    def "Blackjack is blackjack"() {
        def cards = new Integer[2]
        cards[0] = card1
        cards[1] = card2
        def hand = new Hand (cards)

        expect:
            hand.hasBlackJack() == result

        where:
            card1   | card2 | result
            1       | 10    | true
            1       | 11    | true
            1       | 12    | true
            9       | 13    | false
            1       | 1     | false
            10      | 13    | false
            12      | 12    | false
            9       | 13    | false
    }

    def "Two aces and the player is busted" () {
        given:
            def cards = new Integer[2]
            cards[0] = 1
            cards[1] = 1
        when:
            def hand = new Hand (cards)
        then:
            hand.hasBusted()
    }

    @Unroll
    def "calculate hand value"() {
        def cards = new Integer[2]
        cards[0] = card1
        cards[1] = card2
        def hand = new Hand (cards)

        expect:
            hand.handValue() == result

        where:
            card1   | card2 | result
            1       | 1     | 22
            1       | 2     | 13
            2       | 2     | 4
            10      | 10    | 20
            11      | 11    | 20
            12      | 13    | 20
            1       | 10    | 21
            1       | 11    | 21
            1       | 12    | 21
            1       | 13    | 21
    }

    def "Adding a card changes the players hand" () {
        given:
            def cards = new Integer[2]
            cards[0] = 8
            cards[1] = 3
            def hand = new Hand (cards)
            def initialHandValue = hand.handValue()
        when:
            hand.addCard(5)
        then:
            hand.handValue() != initialHandValue
            hand.handValue() == 16
    }
}
