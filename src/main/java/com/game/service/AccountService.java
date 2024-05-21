package com.game.service;

import com.game.util.InputHandler;
import com.game.vo.CheckAccount;
import com.game.vo.FixedAccount;
import com.game.vo.SaveAccount;

import java.util.Random;
import java.util.Scanner;

public class AccountService {

    public int depositChoice(Scanner sc, CheckAccount checkAccount, SaveAccount saveAccount, FixedAccount fixedAccount, InputHandler inputHandler, int amount, boolean stopFixedAccount) {
        System.out.println("--------------------------------------------------------------------------");
        System.out.println("입금할 계좌를 선택하세요.");
        System.out.println("1. 입출금계좌");
        System.out.println("2. 저축계좌");
        System.out.println("3. 당좌예금계좌");
        System.out.println("4. 취소");
        System.out.println("--------------------------------------------------------------------------");

        int choice = inputHandler.getUserChoice(sc, 4);
        if (choice == 4) return 0;
        if (choice == 3 && stopFixedAccount) {
            System.out.println("당좌예금계좌 기간 만료");
            return 0;
        }
        System.out.println("입금금액을 입력해주세요 현재 소유금액 : " + amount);
        int depositAmount = inputHandler.getUserAmount(sc, amount);
        switch (choice) {
            case 1 -> checkAccount.deposit(depositAmount);
            case 2 -> saveAccount.deposit(depositAmount);
            case 3 -> fixedAccount.deposit(depositAmount);
        }

        System.out.println("입금이 완료되었습니다.");
        System.out.println("--------------------------------------------------------------------------");
        return depositAmount;
    }

    public int withdrawalChoice(Scanner sc, CheckAccount checkAccount, SaveAccount saveAccount, FixedAccount fixedAccount, InputHandler inputHandler, int saveFee) {
        System.out.println("--------------------------------------------------------------------------");
        System.out.println("출금할 계좌를 선택하세요.");
        System.out.println("1. 입출금계좌");
        System.out.println("2. 저축계좌(수수료 발생)");
        System.out.println("3. 취소");
        System.out.println("--------------------------------------------------------------------------");

        int choice = inputHandler.getUserChoice(sc, 3);
        if (choice == 3) return 0;

        int balance = (choice == 1) ? checkAccount.getBalance() : saveAccount.getBalance() - saveFee;
        if (balance < 0) {
            System.out.println("계좌에 수수료비용이 부족합니다. 출금이 종료됩니다.");
            return 0;
        }

        System.out.println("출금금액을 입력해주세요. 현재 계좌 잔액(수수료포함) : " + balance);
        int withdrawalAmount = inputHandler.getUserAmount(sc, balance);

        if (choice == 1) {
            checkAccount.withdrawal(withdrawalAmount);
        } else {
            saveAccount.withdrawal(withdrawalAmount);
        }

        System.out.println("출금이 완료되었습니다.");
        System.out.println("--------------------------------------------------------------------------");
        return withdrawalAmount;
    }

    public void printAccountStates(CheckAccount checkAccount, SaveAccount saveAccount, FixedAccount fixedAccount) {
        System.out.println("--------------------------------------------------------------------------");
        System.out.println(checkAccount);
        System.out.println(saveAccount);
        System.out.println(fixedAccount);
        System.out.println("--------------------------------------------------------------------------");
    }

    public String generateAccountNumber() {
        System.out.println("계좌번호 발급");
        Random random = new Random();
        StringBuilder accountNumberBuilder = new StringBuilder();
        for (int i = 0; i < 12; i++) {
            int digit = random.nextInt(10);
            accountNumberBuilder.append(digit);
        }
        System.out.println(accountNumberBuilder.toString());
        return accountNumberBuilder.toString();
    }
}
