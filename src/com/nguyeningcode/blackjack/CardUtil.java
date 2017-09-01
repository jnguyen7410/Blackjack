package com.nguyeningcode.blackjack;

import java.util.ArrayList;

public class CardUtil {

    public static final int NUMBER_OF_CARDS_STARTING = 2;

    public static Card pop(int index, ArrayList<Card> cards) {
        if(cards.size() > index) {
            return cards.remove(index);
        } else {
            System.out.println("Not enough cards to pop!");
            return null;
        }
    }

    public static void generateHands(ArrayList<Player> players, double[] bets, Dealer dealer, Deck deck) {
        for (int i = 0; i < players.size(); i++) {
            players.get(i).hands.add(new Hand(bets[i]));
        }
        dealer.hands.add(new Hand(false));

        for (int i = 0; i < NUMBER_OF_CARDS_STARTING; i++) {
            for (Player player : players) {
                player.hands.get(0).addCard(deck.pop());
            }
            dealer.hands.get(0).addCard(deck.pop());
        }
    }

    public static void printPlayersHands(ArrayList<Player> players) {
        for (Player player : players) {
            System.out.print(player.getName() + ": ");
            for (int i = 0; i < player.hands.size(); i++){
                System.out.println("Hand " + i + ": ");
                System.out.print(player.hands.get(i).toString());
            }
            System.out.println("");
        }
    }

    public static void printPlayerHands(Player player) {
        ArrayList<Player> players = new ArrayList<Player>();
        players.add(player);
        printPlayersHands(players);
    }

}
