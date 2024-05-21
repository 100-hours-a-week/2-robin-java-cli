package com.game.util;

import java.util.InputMismatchException;
import java.util.Scanner;

public class InputHandler {
    public static String getUserName(Scanner sc) {
        int validInput = 0;
        String name = "TEMP-NAME";
        do {
            try {
                System.out.println("이름 입력");
                name = sc.nextLine();
                if(name.trim().isEmpty()) {
                    System.out.println("이름을 입력해주세요.");
                    validInput = 2;
                } else {
                    validInput = 1;
                }
            } catch (Exception e) {
                System.out.println("잘못된 입력입니다.");
                validInput = 2;
            }
        } while(validInput == 2);
        return name;
    }
    public static int getUserChoice(Scanner sc, int maxOption) {
        int choice =-1;
        int validInput = 0;
        do {
            try {
                choice = Integer.parseInt(sc.nextLine());
                if(choice < 1 || choice > maxOption) {
                    System.out.println("다시 입력해주세요.");
                    validInput = 2;
                } else {
                    validInput = 1;
                }
            } catch(NumberFormatException e) {
                System.out.println("숫자를 입력하세요.");
                validInput = 2;
            }
        } while(validInput == 2);

        return choice;
    }
    public int getUserAmount(Scanner sc, int maxAmount) {
        int amount = 0;
        int validInput = 0;
        do {
            try {
                amount = Integer.parseInt(sc.nextLine());
                if(amount < 0) {
                    System.out.println("잘못된 입력입니다. 다시입력해주세요");
                    validInput = 2;
                } else if (amount > maxAmount) {
                    System.out.println("현재 잔액은 " + maxAmount + "입니다. 다시 입력해주세요.");
                    validInput = 2;
                } else {
                    validInput = 1;
                }
            } catch (NumberFormatException e) {
                System.out.println("숫자를 입력하세요.");
                validInput = 2;
            }
        } while(validInput == 2);
        return amount;
    }
    public int getPeriodFromUser(Scanner sc, int minPeriod, int maxPeriod) {
        System.out.println("정기예금 기간을 정해주세요.(1~6)개월 ");
        int period = -1;
        int validInput = 0;
        do {
            try {
                period = Integer.parseInt(sc.nextLine());
                if (period < minPeriod || period > maxPeriod) {
                    System.out.println("기간은 " + minPeriod + "보다 크고 " + (maxPeriod + 1) + "보다 작아야 합니다.");
                    validInput = 2;
                } else {
                    validInput = 1;
                }
            } catch (NumberFormatException e) {
                System.out.println("숫자를 입력하세요.");
                validInput = 2;
            }
        } while(validInput == 2);

        return period;
    }
}
