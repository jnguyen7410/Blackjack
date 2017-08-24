package com.nguyeningcode.blackjack;

import java.util.ArrayList;

/**
 * Created by jnguyen on 8/19/17.
 */
public class Card {

    enum Suit {HEART, DIAMOND, CLUB, SPADE};

    public int value;
    public Suit suit;
    public char faceValue;

    public Card (int value, Suit suit, char faceValue) {
        this.value = value;
        this.suit = suit;
        this.faceValue = faceValue;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public Suit getSuit() {
        return suit;
    }

    public char getFaceValue() {
        return faceValue;
    }

    public String toString() {
        return "Card: [value=" + value + ", suit=" + suit + ", faceValue=" + faceValue + "]";
    }

    public static String getCardDetails(ArrayList<Card> cards) {
        String str = "";
        for (Card card : cards) {
            str += card.toString() + "\n";
        }
        return str;
    }

    public static void printCards(ArrayList<Card> cards){
        System.out.println(getCardDetails(cards));
    }

}
