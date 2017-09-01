package com.nguyeningcode.blackjack;

import java.util.ArrayList;

/**
 * Created by jnguyen on 8/19/17.
 */
public class Player {
    // player should have:
    // hand(s), balance, name, position on the table
        // hand belongs to player
    public double balance;
    public String name;
    public int tablePosition;
    public boolean isDealer;
    public ArrayList<Hand> hands;

    public Player(String name, double balance) {
        this.name = name;
        this.isDealer = false;
        this.balance = balance;
        this.hands = new ArrayList<Hand>();
    }

    public Player(String name, boolean isDealer) {
        this.name = name;
        this.isDealer = isDealer;
        this.hands = new ArrayList<Hand>();
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public String getName() {
        return name;
    }

    public int getTablePosition() {
        return tablePosition;
    }

    public void setTablePosition(int tablePosition) {
        this.tablePosition = tablePosition;
    }

    public ArrayList<Hand> getHands() {
        return this.hands;
    }


}
