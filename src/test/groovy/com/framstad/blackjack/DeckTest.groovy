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
            def valueMap = new HashMap<String, Integer>();

        when:
            1.upto(13, {
                valueMap.put("" + it, 0)
            })


            1.upto(52, {
                int card = deck.pick()
                valueMap.put("" + card, ++valueMap.get("" + card))
            })

        then:
            //valueMap.each{ k, v -> println "${k}:${v}" }
            valueMap.size() == 13
            valueMap.get("1") == 4;
            valueMap.get("2") == 4;
            valueMap.get("3") == 4;
            valueMap.get("4") == 4;
            valueMap.get("5") == 4;
            valueMap.get("6") == 4;
            valueMap.get("7") == 4;
            valueMap.get("8") == 4;
            valueMap.get("9") == 4;
            valueMap.get("10") == 4;
            valueMap.get("11") == 4;
            valueMap.get("12") == 4;
            valueMap.get("13") == 4;
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
