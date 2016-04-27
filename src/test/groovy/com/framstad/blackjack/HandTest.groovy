package com.framstad.blackjack

import spock.lang.Specification
import spock.lang.Unroll

class HandTest extends Specification {

    @Unroll
    def "Blackjack is blackjack"() {
        def hand = new Hand ([card1, card2] as Integer[])

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
        when:
            def hand = new Hand ([1,1] as Integer[])
        then:
            hand.hasBusted()
    }

    @Unroll
    def "calculate hand value"() {
        def hand = new Hand ([card1, card2] as Integer[])

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
            def hand = new Hand ([8, 3] as Integer[])
            def initialHandValue = hand.handValue()
        when:
            hand.addCard(5)
        then:
            hand.handValue() != initialHandValue
            hand.handValue() == 16
    }
}
