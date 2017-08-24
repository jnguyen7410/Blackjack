package com.nguyeningcode.blackjack;

import java.util.ArrayList;

/**
 * Created by jnguyen on 8/21/17.
 */
public class TestDriver {
    public static void main(String[] args) {
        //testDeck();
        testHand();
    }

    public static void testDeck() {
        Deck deck = new Deck(2);
        System.out.println("Unshuffled deck: ");
        deck.printDeck();
        System.out.println("====================================================================================");
        System.out.println("====================================================================================");
        System.out.println("====================================================================================");
        System.out.println("Shuffled deck: ");
        deck.shuffleDeck();
        deck.printDeck();
    }

    public static void testHand() {
        Card card01 = new Card(11, Card.Suit.DIAMOND, 'A');
        Card card02 = new Card(11, Card.Suit.SPADE, 'A');
        Card card03 = new Card(10, Card.Suit.CLUB, 'T');
        Card card04 = new Card(11, Card.Suit.CLUB, 'A');

        ArrayList<Card> cards = new ArrayList<Card>();
        cards.add(card01);
        cards.add(card02);
        cards.add(card03);

        Hand hand = new Hand(100, cards);
        System.out.println(hand.toString());
        hand.size();

        hand.addCard(card04);
        System.out.println(hand.toString());
    }


}
