package com.framstad.blackjack

import spock.lang.Specification

class DeckTest extends Specification {
    def "A fresh deck contains 52 cards"() {
        given:
            def deck = new Deck();
        when:
            def size = deck.deckSize();
        then:
            size == 52;
    }

    def "All cards in a deck have a value in the range [1..13]" () {
        given:
            def deck = new Deck();

        when:
            def validRangeCount = 0;
            1.upto(52, {
                int card = deck.pick()
                if (card in 1..13) {
                    validRangeCount++
                }
            })

        then:
            validRangeCount == 52;
    }

    def "A fresh deck contains four cards of each value" () {
        given:
            def deck = new Deck();
            def map = ["1" : 0,"2" : 0,"3" : 0,"4" : 0,"5" : 0,"6" : 0,"7" : 0,"8" : 0,"9" : 0,"10" : 0,"11" : 0, "12" : 0, "13" : 0]

        when:
            1.upto(52, {
                int card = deck.pick()
                map["" + card] = ++ map["" + card]
            })

        then:
            map.size() == 13
            map["1"] == 4;
            map["2"] == 4;
            map["3"] == 4;
            map["4"] == 4;
            map["5"] == 4;
            map["6"] == 4;
            map["7"] == 4;
            map["8"] == 4;
            map["9"] == 4;
            map["10"] == 4;
            map["11"] == 4;
            map["12"] == 4;
            map["13"] == 4;
    }

    def "A deck is shuffled when the last card is picked" () {
        given:
            def deck = new Deck()

        when:
            1.upto(52, {deck.pick()})

        then:
            deck.deckSize() == 0
            deck.pick()
            deck.deckSize() == 51
    }
}
