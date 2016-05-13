package com.framstad.blackjack

import spock.lang.Specification
import spock.lang.Unroll

class HandTest extends Specification {

    @Unroll
    def "Blackjack is blackjack"() {
        def hand = new Hand ([card1, card2] as Card[])

        expect:
            hand.hasBlackJack() == result

        where:
            card1                    | card2                     | result
            new Card(Suit.Clubs, 1)  | new Card(Suit.Clubs, 10)  | true
            new Card(Suit.Clubs, 1)  | new Card(Suit.Clubs, 11)  | true
            new Card(Suit.Clubs, 1)  | new Card(Suit.Clubs, 12)  | true
            new Card(Suit.Clubs, 1)  | new Card(Suit.Clubs, 13)  | true
            new Card(Suit.Clubs, 9)  | new Card(Suit.Clubs, 13)  | false
            new Card(Suit.Clubs, 9)  | new Card(Suit.Clubs, 13)  | false
            new Card(Suit.Clubs, 10) | new Card(Suit.Clubs, 13)  | false
            new Card(Suit.Clubs, 12) | new Card(Suit.Clubs, 12)  | false
            new Card(Suit.Clubs, 13) | new Card(Suit.Clubs, 13)  | false
    }

    def "Two aces and the player is busted" () {
        when:
            def hand = new Hand ([new Card(Suit.Clubs, 1), new Card(Suit.Hearts, 1)] as Card [])
        then:
            hand.hasBusted()
    }

    @Unroll
    def "calculate hand value"() {
        def hand = new Hand ([card1, card2] as Card[])

        expect:
            hand.value() == result

        where:
            card1                    | card2                     | result
            new Card(Suit.Clubs, 1)  | new Card(Suit.Clubs, 1)   | 22
            new Card(Suit.Clubs, 1)  | new Card(Suit.Clubs, 2)   | 13
            new Card(Suit.Clubs, 2)  | new Card(Suit.Clubs, 2)   | 4
            new Card(Suit.Clubs, 10) | new Card(Suit.Clubs, 10)  | 20
            new Card(Suit.Clubs, 11) | new Card(Suit.Clubs, 11)  | 20
            new Card(Suit.Clubs, 12) | new Card(Suit.Clubs, 13)  | 20
            new Card(Suit.Clubs, 1)  | new Card(Suit.Clubs, 10)  | 21
            new Card(Suit.Clubs, 1)  | new Card(Suit.Clubs, 11)  | 21
            new Card(Suit.Clubs, 1)  | new Card(Suit.Clubs, 12)  | 21
            new Card(Suit.Clubs, 1)  | new Card(Suit.Clubs, 13)  | 21
    }

    def "Adding a card changes the players hand" () {
        given:
            def hand = new Hand ([new Card(Suit.Clubs, 8), new Card(Suit.Hearts, 3)] as Card [])
            def initialHandValue = hand.value()
        when:
            hand.addCard(new Card(Suit.Clubs, 5))
        then:
            hand.value() != initialHandValue
            hand.value() == 16
    }
}
