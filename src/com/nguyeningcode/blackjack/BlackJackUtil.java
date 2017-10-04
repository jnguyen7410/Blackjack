package com.nguyeningcode.blackjack;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * Created by jnguyen on 8/31/17.
 */
public class BlackJackUtil {

    public static Scanner in = new Scanner(System.in);
    public static boolean hasSplit = false;

    public static int getNumberOfPlayers(int MAX_PLAYERS) {
        System.out.println("How many players? (1-10) : ");
        int numPlayers = 0;
        boolean error;
        do {
            try {
                error = false;
                numPlayers = Integer.parseInt(in.nextLine());
            } catch (Exception e) {
                System.out.println("Please enter a valid number (1-10): ");
                error = true;
            }

            if ((numPlayers < 1 || numPlayers > MAX_PLAYERS) && !error) {
                System.out.println("Please enter a number within the allowed range! (1-10): ");
            }

        } while (numPlayers < 1 || numPlayers > MAX_PLAYERS || error);
        return numPlayers;
    }

    public static void getBetsForPlayers(double[] bets) {
        double tempBet = -1;
        boolean error;
        for (int i = 0; i < Game.players.size(); i++) {
            System.out.println("[" + Game.players.get(i).getName() + "] Enter a bet for player \"" + Game.players.get(i).getName() + "\" (>=" + Game.MIN_BUY_IN + "): ");
            do {
                try {
                    error = false;
                    tempBet = Double.parseDouble(in.nextLine());
                } catch (Exception e) {
                    System.out.println("[" + Game.players.get(i).getName() + "] Please enter a valid number for your bet! (>=" + Game.MIN_BUY_IN + "): ");
                    error = true;
                }

                if (tempBet < Game.MIN_BUY_IN && !error) {
                    System.out.println("[" + Game.players.get(i).getName() + "] Please enter a bet greater then the minimum buy in! (>=" + Game.MIN_BUY_IN + "): ");
                }

                if(!checkBetAgainstBalance(Game.players.get(i).balance, tempBet) && !error) {
                    System.out.println("[" + Game.players.get(i).getName() + "] You don't have enough to bet that! Your balance is " + Game.players.get(i).balance + " but your bet was " + tempBet + "): ");
                }

            } while (tempBet < Game.MIN_BUY_IN || !checkBetAgainstBalance(Game.players.get(i).balance, tempBet) || error);
            bets[i] = tempBet;
        }
    }

    public static void getPlayers(int numPlayers) {
        boolean error;
        for (int i = 0; i < numPlayers; i++) {
            System.out.println("[Player #" + (i+1) + "] Please input your name: ");
            String name = in.nextLine();
            double balance = 0;
            System.out.println("[" + name + "] Please input your balance (>=" + Game.MIN_BUY_IN + "): ");
            do {
                try {
                    error = false;
                    balance = Double.parseDouble(in.nextLine());
                } catch (Exception e) {
                    System.out.println("[" + name + "] Please enter a valid number! (>=" + Game.MIN_BUY_IN + "): ");
                    error = true;
                }

                if (balance < Game.MIN_BUY_IN && !error) {
                    System.out.println("[" + name + "] Please enter a balance greater than " + Game.MIN_BUY_IN + "! (>=" + Game.MIN_BUY_IN + "): ");
                }
            } while (balance < Game.MIN_BUY_IN || error);
            Game.players.add(new Player(name, balance));
        }
    }


    public static boolean checkBetAgainstBalance(double balance, double bet) {
        return balance >= bet;
    }

    public static void dealerBlackjack() {
        // errbody non-BJ
        if (Hand.isBlackJack(Game.dealer.hands.get(0))) {
            // make the non-BlackJack hands of each player unplayable and set setWon to false
            System.out.println("The dealer got a blackjack!");
            for(Player player : Game.players) {
                if(player.hands.get(0).isPlayable()) {
                    player.hands.get(0).setPlayable(false);
                    player.hands.get(0).setWon(false);
                }
            }
            // Update the dealer hand to unplayable and set the hand to win
            Game.dealer.hands.get(0).setPlayable(false);
            Game.dealer.hands.get(0).setBlackjack(true);
            Game.dealer.hands.get(0).setWon(true);
        }
    }

    public static void playersBlackjack() {
        for(Player player : Game.players) {
            if(Hand.isBlackJack(player.hands.get(0))) {
                System.out.println(player.getName() + " got a blackjack!");
                player.hands.get(0).setPlayable(false);
                player.hands.get(0).setBlackjack(true);
                player.hands.get(0).setWon(true);
            }

        }
    }

    public static boolean continueToGameLogic() {
        for(Player player : Game.players) {
            for (Hand hand : player.hands) {
                if(hand.isPlayable()) {
                    return true;
                }
            }
        }
        return false;
    }

    public static void roundLogic(Deck deck) {
        // Players Logic
        hasSplit = false;
        for(Player player : Game.players) {
            if (player.tempHand != null){
                player.hands.add(player.tempHand);
                player.tempHand = null;
            }
            for(Hand hand : player.hands) {
                while(hand.playable) {
                    playerOptions(player, hand, deck);
                    if (hasSplit) {
                        return;
                    }
                }
            }
        }

        // Dealer Logic:
            // 1. Print the dealer hand since the second card is initially hidden
            // 2. While the hand value is less than 17, keep hitting
            // 3. Check for busts after the dealer's hand has reached at least a value of 17

        System.out.println("Dealer\'s hand is : " + Card.getCardDetails(Game.dealer.hands.get(0).cards));
        Hand dealerHand = Game.dealer.hands.get(0);
        while(Hand.getHandValue(dealerHand) < 17) {
            dealerHand.addCard(deck.pop());
            System.out.print("Dealer drew a " + dealerHand.cards.get(dealerHand.cards.size()-1).toString());
        }
        if(checkForBust(dealerHand)) {
            dealerHand.setWon(false);
        }

        // End game logic for determining winners, losers, payouts and losses:
            // 1. Check if player's hand is Blackjack
            // 2. ELSE check if dealer got Blackjack
            // 3. ELSE check for player's hand is bust
            // 4. ELSE check for dealer's hand is bust
            // 5. ELSE compare dealer's hand with player's hand

        // This is looking ugly, any way to optimize?
        for(Player player : Game.players) {
            for(Hand hand : player.hands) {
                if(hand.isBlackjack()) {
                    // player gets 1.5 * hand's bet
                } else if(dealerHand.isBlackjack() || checkForBust(hand)) {
                    // player loses 1 * hand's bet
                } else if(checkForBust(dealerHand)) {
                    // player wins 1 * hand's bet
                } else {
                    if(Hand.getHandValue(hand) > Hand.getHandValue(dealerHand)) {
                        // player wins 1 * hand's bet
                    } else if(Hand.getHandValue(hand) < Hand.getHandValue(dealerHand)) {
                        // player loses 1 * hand's bet
                    } else {
                        // tie: nobody wins
                    }
                }
            }
        }

        // End game logic for determining valid balances, winner/losers, and whether to continue playing
    }

    public static void playerOptions(Player player, Hand hand, Deck deck) {
        System.out.print(player.getName() + ", your hand is: " + hand.toString());

        boolean error = false;
        int menuOption = 0;

        do {
            try {
                printMenu(hand);
                error = false;
                menuOption = Integer.parseInt(in.nextLine());
            } catch(Exception e) {
                error = true;
                System.out.println("Your selection of '" + menuOption + "' is invalid");
            }

            if(!validateMenuInput(hand, menuOption) && !error) {
                System.out.println("You selected an invalid option");
            }
            else if (!error) {
                switch(menuOption){
                    case 1:
                        hand.setPlayable(false);
                        break;
                    case 2:
                        Card tempCard = deck.pop();
                        hand.addCardtempCard);
                        System.out.print(player.getName() + ", you drew a " + tempCard.toString());

                        if(checkForBust(hand)) {
                            hand.setPlayable(false);
                            System.out.println("Ouch, busted!");
                        } else {
                            printMenu(hand);
                        }

                        break;
                    case 3:
                        hand.doubleDown(deck.pop());
                        if(checkForBust(hand)) {
                            System.out.println("Ouch, busted!");
                        }
                        break;
                    case 4:
                        player.tempHand = hand.split(); //check to see that card is removed from original hand
                        hasSplit = true;
                        break;
                    default:
                        // is there a special case that could break this switch?
                        System.out.println("WARNING! Default switch case for player's menu option: " + menuOption);
                        break;
                        // PLEASE CONTINUE HERE LAZY BUM
                }
            }

        } while (hand.isPlayable() && (!validateMenuInput(hand, menuOption) || error));

    }

    public static void printMenu(Hand hand) {
        System.out.println("Please select one of the following options: \n");
        canStay(hand);
        canHit(hand);
        canDoubleDown(hand);
        canSplit(hand);
    }

    public static boolean canStay(Hand hand) {
        if(hand.playable && Hand.getHandValue(hand) <= 21) {
            System.out.println("1 - Stay \n");
            return true;
        }
        return false;
    }

    public static boolean canHit(Hand hand) {
        if(hand.playable && Hand.getHandValue(hand) < 21 && !hand.isDoubleDown()) {
            System.out.println("2 - Hit \n");
            return true;
        }
        return false;
    }

    public static boolean canDoubleDown(Hand hand) {
        if(hand.playable && Hand.getHandValue(hand) < 21 && !hand.isDoubleDown() && hand.size() == 2) {
            System.out.println("3 - Double down \n");
            return true;
        }
        return false;
    }

    public static boolean canSplit(Hand hand) {
        if(hand.playable && !hand.isDoubleDown() && hand.checkSplit()) {
            System.out.println("4 - Split");
            return true;
        }
        return false;
    }

    public static boolean validateMenuInput(Hand hand, int input) {
        if(canStay(hand) && input == 1) {return true;}
        if(canHit(hand) && input == 2) {return true;}
        if(canDoubleDown(hand) && input == 3) {return true;}
        if(canSplit(hand) && input == 4) {return true;}
        return false;
    }

    public static boolean checkForBust(Hand hand) {
        if(Hand.getHandValue(hand) > 21) {
            return true;
        }
        return false;
    }

    public static void printHandResult(Player player, Hand hand, String result) {
        switch(result) {
            case "win":
                System.out.print(player.getName() + ", you win $");
                break;
            case "lose":
                break;
            case "tie":
                break;
            default:
                System.out.println("Invalid string was passed somehow, please look into this.");
                break;
        }
    }
}
