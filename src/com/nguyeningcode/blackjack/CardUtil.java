package com.nguyeningcode.blackjack;

import java.util.ArrayList;

public class CardUtil {

    public static Card pop(int index, ArrayList<Card> cards) {
        if(cards.size() > index) {
            return cards.remove(index);
        } else {
            System.out.println("Not enough cards to pop!");
            return null;
        }
    }

    
}
