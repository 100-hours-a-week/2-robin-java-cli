package com.game.vo;

import com.game.dto.AccountDTO;

public class CheckAccount extends AccountDTO {
    public CheckAccount(String owner, String acNum, int balance) {
        super(owner, acNum, balance);
    }

    public void deposit(int amount) {
        setBalance(getBalance() + amount);
    }
    public void withdrawal(int amount) {
        setBalance(getBalance() - amount);
    }

    @Override
    public String toString() {
        return "입출금계좌 정보   - 소유자 : "+getOwner()+" 계좌번호 : "+getAcNum()+" 잔액 : "+getBalance();
    }
}
