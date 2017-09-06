package com.nguyeningcode.blackjack;

import java.util.ArrayList;
//import java.util.Scanner;

public class Game {

    //CONSTANTS
    public static final double MIN_BUY_IN = 25;
    public static final int MAX_PLAYERS = 10;

    public static ArrayList<Player> players;
    public static int numPlayers;
    public static Dealer dealer;

    public static void main(String[] args) {
        playGame();
    }


    public static void playGame() {

        /*    SETUP    */

        // Scanner in = new Scanner(System.in);
        // list to hold current players
        players = new ArrayList<Player>();

        // all of the players
        dealer = new Dealer("Dealer");

        numPlayers = BlackJackUtil.getNumberOfPlayers(MAX_PLAYERS);
        BlackJackUtil.getPlayers(numPlayers);

        // generate the deck
        Deck deck = new Deck(4);

        /*    ROUND    */
        // get everyone's bets
        double[] bets = new double[numPlayers];
        BlackJackUtil.getBetsForPlayers(bets);


        /*System.out.println("Deck before hands created: ");
        System.out.println("");
        System.out.println("---------------------------------------------------------------");
        deck.printDeck();
        System.out.println("---------------------------------------------------------------");
        System.out.println("");*/

        CardUtil.generateHands(bets, dealer, deck);

        /*System.out.println("Deck after hands created: ");
        System.out.println("");
        System.out.println("---------------------------------------------------------------");
        deck.printDeck();
        System.out.println("---------------------------------------------------------------");
        System.out.println("");*/

        // print the current state
        CardUtil.printPlayerHands(dealer);
        CardUtil.printPlayersHands(players);


        BlackJackUtil.playersBlackjack();
        BlackJackUtil.dealerBlackjack();
        if (BlackJackUtil.continueToGameLogic()) {
            // game round logic goes here
            System.out.println("Let's start the game!");
        }

        System.out.println("End game logic");

    }
}
