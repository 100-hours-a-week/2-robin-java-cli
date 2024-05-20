package com.game.service;


import com.game.vo.CheckAccount;
import com.game.vo.FixedAccount;
import com.game.vo.SaveAccount;

public class GameService {
    private static final int FIXED_INTEREST = 50;
    private static final int SAVING_INTEREST = 10;

    public void handleMonthlyFee(CheckAccount checkAccount, int fee) {
        if (checkAccount.getBalance() < fee) {
            System.out.println("지출비용이 없어 게임에서 패배하였습니다.");
            System.exit(0);
        }
        checkAccount.withdrawal(fee);
        System.out.println("지출 " + fee + "원이 발생하였습니다.");
    }

    public void handleExpired(CheckAccount checkAccount, FixedAccount fixedAccount) {
        int facMoney = fixedAccount.getBalance();
        fixedAccount.withdrawal(facMoney);
        facMoney = facMoney + (facMoney * FIXED_INTEREST / 100);
        checkAccount.deposit(facMoney);
        System.out.println("당좌예금계좌 기간이 끝났습니다. 입출금계좌에 "+facMoney+"이 입금됩니다.");
    }
    public void handleSavingInterest(SaveAccount saveAccount) {
        int savemoney = saveAccount.getBalance() / 10;
        if(savemoney > 0) {
            saveAccount.deposit(savemoney);
            System.out.println("저축계좌 이자 적립");
        }
    }
}