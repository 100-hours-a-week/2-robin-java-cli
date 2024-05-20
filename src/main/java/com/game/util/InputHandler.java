package com.game.util;

import java.util.Scanner;

public class InputHandler {
    public static int getUserChoice(Scanner sc, int maxOption) {
        int choice;
        while(true) {
            try {
                choice = Integer.parseInt(sc.nextLine());
                if(choice < 1 || choice > maxOption) {
                    System.out.println("다시 입력해주세요.");
                } else {
                    break;
                }
            } catch(NumberFormatException e) {
                System.out.println("숫자를 입력하세요.");
            }
        }
        return choice;
    }
    public int getUserAmount(Scanner sc, int maxAmount) {
        int amount;
        while (true) {
            try {
                amount = Integer.parseInt(sc.nextLine());
                if(amount < 0) {
                    System.out.println("잘못된 입력입니다. 다시입력해주세요");
                } else if (amount > maxAmount) {
                    System.out.println("현재 잔액은 " + maxAmount + "입니다. 다시 입력해주세요.");
                } else {
                    break;
                }
            } catch (NumberFormatException e) {
                System.out.println("숫자를 입력하세요.");
            }
        }
        return amount;
    }
    public int getPeriodFromUser(Scanner sc, int minPeriod, int maxPeriod) {
        int period;
        while (true) {
            try {
                period = Integer.parseInt(sc.nextLine());
                if (period < minPeriod || period > maxPeriod) {
                    System.out.println("기간은 " + minPeriod + "보다 크고 " + (maxPeriod + 1) + "보다 작아야 합니다.");
                } else {
                    break;
                }
            } catch (NumberFormatException e) {
                System.out.println("숫자를 입력하세요.");
            }
        }
        return period;
    }
}
