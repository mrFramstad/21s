package com.framstad.blackjack

import spock.lang.Specification

class HandTest extends Specification {

    def "should produce review reminder event"() {
        given:
        def test = 23

        when:
            def result = 2 * test

        then:
            result == 46
    }
}
