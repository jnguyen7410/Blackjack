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

    public boolean hasDoubleDown;

    public Player(String name) {
        this.name = name;
        this.hasDoubleDown = false;
        this.isDealer = false;
    }

    public Player(String name, boolean isDealer) {
        this.name = name;
        this.hasDoubleDown = false;
        this.isDealer = isDealer;
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
