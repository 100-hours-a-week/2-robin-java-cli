package com.game.vo;

import com.game.dto.AccountDTO;

public class FixedAccount extends AccountDTO {
    private final int period;

    public FixedAccount(String owner, String acNum, int balance, int period) {
        super(owner, acNum, balance);
        this.period = period;
    }

    public int getPeriod() {
        return period;
    }

    public void deposit(int amount) {
        setBalance(getBalance() + amount);
    }

    public void withdrawal(int amount) {
        setBalance(getBalance() - amount);
    }

    @Override
    public String toString() {
        return "정기예금계좌 정보 - 소유자: " + getOwner() + ", 계좌번호: " + getAcNum() + ", 잔액: " + getBalance() + ", 기간: " + period;
    }
}
