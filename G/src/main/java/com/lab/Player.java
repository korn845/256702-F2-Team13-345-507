package com.lab;

public class Player {
    
    private int playerMoney = 100;

    private void earnMoney(int amount) {
        playerMoney += amount;
        System.out.println("Money: " + playerMoney);
    }

    private void spendMoney(int amount) {
        if (playerMoney >= amount) {
            playerMoney -= amount;
            System.out.println("Money: " + playerMoney);
        } else {
            System.out.println("Not enough money!");
        }
    }
    
}
