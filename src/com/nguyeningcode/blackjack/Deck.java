package com.nguyeningcode.blackjack;

import java.util.ArrayList;
import java.util.Arrays;

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
        shuffleDeck();
    }

    public Deck (int numDecks) {
        ArrayList<Card> tempDeck;
        this.cards = new ArrayList<Card>();
        this.numDecks = numDecks;
        this.jokersIncluded = false;
        this.sizeOfDeck = 52 * numDecks;
        for (int i = 0; i < numDecks; i++) {
            tempDeck = generateDeck();
            shuffle(tempDeck);
            this.cards.addAll(tempDeck);
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
                } else if (j == 10) {
                    card = new Card (10, suit, 'T');
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

    public void shuffleDeck() {
        shuffle(this.cards);
    }

    public static void shuffle(ArrayList<Card> cards) {
        for (int i = 0; i < cards.size(); i++) {
            int randomIndex = (int) (Math.random() * (cards.size() - i) + i);
            Card temp = cards.get(i);
            cards.set(i, cards.get(randomIndex));
            cards.set(randomIndex, temp);
        }
    }


    public void printDeck() {
       Card.printCards(this.cards);
    }

    public Card pop() {
        return pop(0);
    }

    public Card pop(int index) {
        if(this.cards.size() > index) {
            Card topCard = this.cards.get(index);
            this.cards.remove(index);
            return topCard;
        } else {
            System.out.println("Not enough cards to pop!");
            return null;
        }
    }


}
