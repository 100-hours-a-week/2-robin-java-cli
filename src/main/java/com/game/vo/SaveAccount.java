package com.game.vo;

import com.game.dto.AccountDTO;

public class SaveAccount extends AccountDTO {
    public SaveAccount(String owner, String accountNumber, int balance) {
        super(owner, accountNumber, balance);
    }

    public void deposit(int amount) {
        setBalance(getBalance() + amount);
    }
    public void withdrawal(int amount) {
        setBalance(getBalance() - amount);
    }

    @Override
    public String toString() {
        return "저축계좌 정보    - 소유자 : "+getOwner()+" 계좌번호 : "+getAcNum()+" 잔액 : "+getBalance();
    }
}
