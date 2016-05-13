package com.framstad.blackjack

import spock.lang.Specification
import spock.lang.Unroll

class DeckTest extends Specification {
    def "A fresh deck contains 52 cards"() {
        when:
            def deck = new Deck()
        then:
            deck.cardsLeft() == 52;
    }

    def "All cards in a deck have a value in the range [1..13]"() {
        given:
            def deck = new Deck();

        when:
            def validRangeCount = 0;
            1.upto(52, {
                def card = deck.pick()
                if (card.value in 1..13) {
                    validRangeCount++
                }
            })

        then:
            validRangeCount == 52;
    }

    def "A fresh deck contains four cards of each value"() {
        given:
            def deck = new Deck();
            def map = ["1": 0, "2": 0, "3": 0, "4": 0, "5": 0, "6": 0, "7": 0, "8": 0, "9": 0, "10": 0, "11": 0, "12": 0, "13": 0]

        when:
            1.upto(52, {
                def card = deck.pick()
                map["" + card.value] = ++map["" + card.value]
            })

        then:
            map.size() == 13
            map["1"] == 4
            map["2"] == 4
            map["3"] == 4
            map["4"] == 4
            map["5"] == 4
            map["6"] == 4
            map["7"] == 4
            map["8"] == 4
            map["9"] == 4
            map["10"] == 4
            map["11"] == 4
            map["12"] == 4
            map["13"] == 4
    }

    def "A RuntimeException is thrown when picking a card from an empty deck"() {
        given:
            def deck = new Deck()

        when:
            1.upto(53, { deck.pick() })

        then:
            thrown(RuntimeException)
    }

    def "A deck initilized from file contains the correct number of cards"() {
        given:
            def deck = new Deck("src/test/resources/deckWith_1_to_13_of_spades.txt")

        when:
            def size = deck.cardsLeft()

        then:
            size == 13
    }

    def "A deck initilized from file contains cards with correct suit"() {
        given:
            def deck = new Deck("src/test/resources/deckWith_1_to_13_of_spades.txt")
            def map = [:];

        when:
            1.upto(13, {
                def card = deck.pick()
                map[card.value] = card.suit
            })

        then:
            map.size() == 13
            map[1] == Suit.Spades
    }

    def "A deck initilized from file contains cards with correct suit and value in the correct order"() {
        given:
            def deck = new Deck("src/test/resources/deck_with_4_cards_of_different_suit.txt")

        when:
            def card1 = deck.pick()
            def card2 = deck.pick()
            def card3 = deck.pick()
            def card4 = deck.pick()

        then:
            card1.value == 4
            card1.suit == Suit.Spades
            card2.value == 5
            card2.suit == Suit.Hearts
            card3.value == 6
            card3.suit == Suit.Clubs
            card4.value == 7
            card4.suit == Suit.Diamonds
    }

    def "A deck initilized from file containing invalid card throws an exception"() {
        when:
            new Deck("src/test/resources/deck_with_4_cards_of_invalid_suit_and_value.txt")

        then:
            thrown(IllegalArgumentException)
    }
}