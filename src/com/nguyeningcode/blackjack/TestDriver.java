package com.nguyeningcode.blackjack;

/**
 * Created by jnguyen on 8/21/17.
 */
public class TestDriver {
    public static void main(String[] args) {
        Deck deck = new Deck();
        System.out.println("Unshuffled deck: ");
        Deck.printDeck(deck);
        System.out.println("====================================================================================");
        System.out.println("====================================================================================");
        System.out.println("====================================================================================");
        System.out.println("Shuffled deck: ");
        deck = Deck.shuffle(deck);
        Deck.printDeck(deck);

    }
}
