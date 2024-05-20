package com.game.dto;

public class AccountDTO extends BankDTO {
    private int balance;
    public AccountDTO(String owner, String acNum, int balance) {
        super(owner, acNum);
        this.balance = balance;
    }
    public int getBalance() {
        return balance;
    }

    public void setBalance(int balance) {
        this.balance = balance;
    }
}
