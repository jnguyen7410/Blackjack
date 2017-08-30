package com.nguyeningcode.blackjack;

import java.util.ArrayList;

/**
 * Created by jnguyen on 8/19/17.
 */
public class Hand {

    public int value;
    public boolean doubleDown;
    public int bet;
    public boolean blackjack;
    public boolean soft;
    public boolean playable;
    public ArrayList<Card> cards;
    public boolean canSplit;

    public Hand () {
        value = 0;
        doubleDown = false;
        bet = 0;
        blackjack = false;
        playable = true;
        soft = false;
        cards = new ArrayList<Card>();
    }

    public Hand (int bet, ArrayList<Card> cards) {
        this.cards = cards;
        validateCardValues();
        this.bet = bet;
        doubleDown = false;
        blackjack = isBlackJack(cards);
        playable = !blackjack;
    }

    public boolean canSplit() {
        return this.canSplit;
    }

    public void setCanSplit(boolean canSplit) {
        this.canSplit = canSplit;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public boolean isDoubleDown() {
        return doubleDown;
    }

    public void setDoubleDown(boolean doubleDown) {
        this.doubleDown = doubleDown;
    }

    public int getBet() {
        return bet;
    }

    public void setBet(int bet) {
        this.bet = bet;
    }

    public boolean isBlackjack() {
        return blackjack;
    }

    public void setBlackjack(boolean blackjack) {
        this.blackjack = blackjack;
    }

    public boolean isSoft() {
        return soft;
    }

    public void setSoft(boolean soft) {
        this.soft = soft;
    }

    public static int getHandValue(ArrayList<Card> cards) {
        int sum = 0;
        for (Card card : cards) {
            sum += card.getValue();
        }
        return sum;
    }

    public static boolean isBlackJack(ArrayList<Card> cards) {
        return getHandValue(cards) == 21 && cards.size() == 2;
    }

    public static boolean isSoft(ArrayList<Card> cards) {
        for (Card card : cards) {
            if (card.getValue() == 11) {
                return true;
            }
        }
        return false;
    }

    public int size() {
        return this.cards.size();
    }

    @Override
    public String toString() {
        return "Hand{" +
                "value=" + value +
                ", doubleDown=" + doubleDown +
                ", bet=" + bet +
                ", blackjack=" + blackjack +
                ", soft=" + soft +
                '}' +
                "\n" +
                "Cards: \n" + Card.getCardDetails(this.cards);
    }

    public void addCard(Card card) {
        if(this.playable) {
            //add a card
            this.cards.add(card);
            //if hand > 21 && soft
            this.validateCardValues();
            //check if its blackjack
            if(this.cards.size() == 2) {
                blackjack = isBlackJack(this.cards);
            }
            if(this.value >= 21 || this.doubleDown) {
                this.playable = false;
            }
        }
    }

    public void stay() {
        this.playable = false;
    }

    public boolean checkSplit() {
        if(this.cards.size() == 2) {
            return this.cards.get(0).getFaceValue() == this.cards.get(1).getFaceValue();
        }
        return false;
    }

    public Hand split() {
        if(canSplit()) {
            ArrayList<Card> cards = new ArrayList<Card>();
            cards.add(pop(1));
            return new Hand(this.bet, cards);
        }
        return null;
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

    public void validateCardValues() {
        while (getHandValue(this.cards) > 21 && isSoft(this.cards)) {
            for (Card card : this.cards) {
                if (card.getValue() == 11) {
                    card.setValue(1);
                    break;
                }
            }
            setValue(getHandValue(this.cards));
            setSoft(isSoft(this.cards));
            setCanSplit(checkSplit());
        }
    }

    public void doubleDown(Card card) {
        this.bet *= 2;
        this.setDoubleDown(true);
        this.addCard(card);
    }


}
