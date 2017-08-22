package com.nguyeningcode.blackjack;

/**
 * Created by jnguyen on 8/19/17.
 */
public class Player {
    // player should have:
    // hand(s), buyIn, name, position on the table
        // hand belongs to player
    public double buyIn;
    public String name;
    public int tablePosition;

    public Hand hand;

    public boolean hasDoubleDown;

    public Player(String name) {
        this.name = name;
        this.hasDoubleDown = false;
    }

    public double getBuyIn() {
        return buyIn;
    }

    public void setBuyIn(double buyIn) {
        this.buyIn = buyIn;
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


}
