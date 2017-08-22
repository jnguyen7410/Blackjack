package com.nguyeningcode.blackjack;

import java.util.ArrayList;

/**
 * Created by jnguyen on 8/19/17.
 */
public class Deck {

    public int numDecks;
    public boolean jokersIncluded;
    public int sizeOfDeck;
    public ArrayList<Card> cards;

    public Deck () {
        this.numDecks = 1;
        this.jokersIncluded = false;
        this.sizeOfDeck = 52;
        cards = generateDeck();
    }

    public Deck (int numDecks) {
        this.numDecks = numDecks;
        this.jokersIncluded = false;
        this.sizeOfDeck = 52 * numDecks;
        for (int i = 0; i < numDecks; i++) {
            cards.addAll(generateDeck());
        }
    }

    public Deck (int numDecks, boolean jokersIncluded) {
        this.numDecks = numDecks;
        this.jokersIncluded = jokersIncluded;
        this.sizeOfDeck = 52 * numDecks;
    }

    public int getNumDecks() {
        return numDecks;
    }

    public boolean isJokersIncluded() {
        return jokersIncluded;
    }

    public int getSizeOfDeck() {
        return sizeOfDeck;
    }


    public static ArrayList<Card> generateDeck() {
        ArrayList<Card> result = new ArrayList<Card>();
        Card.Suit suit = null;
        for (int i = 0; i < 4; i++) {
            switch(i) {
                case 0:
                    suit = Card.Suit.DIAMOND;
                    break;
                case 1:
                    suit = Card.Suit.HEART;
                    break;
                case 2:
                    suit = Card.Suit.CLUB;
                    break;
                case 3:
                    suit = Card.Suit.SPADE;
                    break;
                default:
                    // this should never happen
                    break;
            }

            for (int j = 1; j <= 13; j++) {
                Card card;
                if (j == 1) {
                   //this is an Ace
                    card = new Card (11, suit, 'A');
                } else if (j == 11) {
                    card = new Card (10, suit, 'J');
                } else if (j == 12) {
                    card = new Card (10, suit, 'Q');
                } else if (j == 13) {
                    card = new Card (10, suit, 'K');
                } else {
                    card = new Card (j, suit, (char) (j + '0'));
                }

                result.add(card);
            }
        }

        return result;
    }


    public static void printDeck(Deck deck) {
        for (Card card : deck.cards) {
            System.out.println(card.toString());
        }
    }


}
