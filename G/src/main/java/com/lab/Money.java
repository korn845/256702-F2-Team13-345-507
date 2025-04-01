package com.lab;

import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;

public class Money {
    private Text moneyText;
    private int currentMoney;
    private static final int ENEMY_KILL_REWARD = 10;
    private static final int WAVE_COMPLETE_REWARD = 50;

    public Money(Pane gamePane, int initialMoney) {
        this.currentMoney = initialMoney;

        moneyText = new Text("Money: $" + currentMoney);
        moneyText.setFill(Color.YELLOW);
        moneyText.setStyle("-fx-font-size: 20px;");
        moneyText.setX(10);
        moneyText.setY(60);
        gamePane.getChildren().add(moneyText);
    }

    public void addKillReward() {
        currentMoney += ENEMY_KILL_REWARD;
        updateMoneyDisplay();
    }

    public void addWaveReward() {
        currentMoney += WAVE_COMPLETE_REWARD;
        updateMoneyDisplay();
    }

    public boolean canAfford(int cost) {
        return currentMoney >= cost;
    }

    public void spendMoney(int amount) {
        if (canAfford(amount)) {
            currentMoney -= amount;
            updateMoneyDisplay();
        }
    }

    private void updateMoneyDisplay() {
        moneyText.setText("Money: $" + currentMoney);
    }

    public int getCurrentMoney() {
        return currentMoney;
    }
}