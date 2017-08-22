package com.nguyeningcode.blackjack;

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

    public Suit getSuit() {
        return suit;
    }

    public char getFaceValue() {
        return faceValue;
    }

}
