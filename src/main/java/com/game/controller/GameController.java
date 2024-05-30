package com.game.controller;

import com.game.service.*;
import com.game.util.InputHandler;
import com.game.util.RulePrinter;
import com.game.vo.CheckAccount;
import com.game.vo.FixedAccount;
import com.game.vo.SaveAccount;

import java.util.NoSuchElementException;
import java.util.Random;
import java.util.Scanner;

public class GameController {
    private static final int END_DAY = 6;
    private static final int MIN_FEE = 300;
    private static final int MAX_FEE = 600;
    private static final int INITAIL_MONEY = 1000;
    private static final int SAVING_WITHDRAWAL_FEE = 500;
    private static int amount = 0; // 소지 금액

    //메뉴선택
    private static final int SHOW_ACCOUNT = 1;
    private static final int DEPOSIT = 2;
    private static final int WITHDRAWAL = 3;
    private static final int NEXT_MONTH = 4;

    private static final String filePath = "../bgm/hyper-space-game-208894.wav";
    public void startGame() {


        MusicService musicService = new MusicService();
        //파일 블루어고


        musicService.playMusic("/hyper_space_game-208894.wav");
        Scanner sc = null;
        try {
            sc = new Scanner(System.in);
        } catch (NoSuchElementException e) {
            System.out.println("입력이 존재하지 않습니다.");
        } catch (IllegalStateException e) {
            System.out.println("스캐너 닫음");
        }
        AccountService accountService = new AccountService();
        GameService gameService = new GameService();
        InputHandler inputHandler = new InputHandler();
        RulePrinter rulePrinter = new RulePrinter();
        Random random = new Random();

        String name = InputHandler.getUserName(sc);
        String acNum = accountService.generateAccountNumber();


        CheckAccount checkAccount = new CheckAccount(name, acNum, 0);
        SaveAccount saveAccount = new SaveAccount(name, acNum, 0);

        int period = inputHandler.getPeriodFromUser(sc, 1, END_DAY);
        FixedAccount fixedAccount = new FixedAccount(name, acNum, 0, period);

        accountService.printAccountStates(checkAccount, saveAccount, fixedAccount);

        rulePrinter.printRule(sc);


        playGame(sc, random, checkAccount, saveAccount, fixedAccount, accountService, gameService, inputHandler);
        musicService.stopMusic();
    }

    private boolean playGame(Scanner sc, Random random, CheckAccount checkAccount, SaveAccount saveAccount, FixedAccount fixedAccount, AccountService accountService, GameService gameService, InputHandler inputHandler) {
        boolean stopFixedAccount = false; // 고정계좌의 만기 여부를 추적하는 변수
        Object lock = new Object();
        InterestRateService interestRateService = new InterestRateService(10, lock);
        SaveMoneyService saveMoneyService = new SaveMoneyService(interestRateService, lock, 10);

        interestRateService.start();
        saveMoneyService.start();

        for (int i = 1; i <= END_DAY; i++) {
            System.out.println("--------------------------------------------------------------------------");
            System.out.println(i + "개월");
            int fee = random.nextInt((MAX_FEE - MIN_FEE) + 1) + MIN_FEE;

            if (i != 1) {
                gameService.handleMonthlyFee(checkAccount, fee);
            }

            if (!stopFixedAccount && i == fixedAccount.getPeriod()) {
                gameService.handleExpired(checkAccount, fixedAccount); //고정계좌의 만기처리 여부
                stopFixedAccount = true; // 고정계좌를 만기 상태로 변경
            }

            gameService.handleSavingInterest(saveAccount, interestRateService.getInterest());

            amount = INITAIL_MONEY;
            int stopCheck = 0;
            do {
                System.out.println("소지 잔액 : " + amount + "(소지 잔액은 다음달이 지나면 사라집니다.)");
                System.out.println("원하는 행동을 선택해주세요.");
                printMenu();

                int action = InputHandler.getUserChoice(sc, 4);

                if (handleUserAction(sc, action, checkAccount, saveAccount, fixedAccount, stopFixedAccount, inputHandler, accountService)) { //고정계좌 만기상태라면 사용자 행동에 제약
                    stopCheck = 1;
                } else {
                    stopCheck = 2;
                }
            } while(stopCheck == 2);
        }
        sc.close();
        interestRateService.interrupt();
        saveMoneyService.interrupt();
        int resultMoney = checkAccount.getBalance() + saveAccount.getBalance() + fixedAccount.getBalance();
        System.out.println("6개월 동안 총 " + resultMoney + "원을 모으셨습니다.");
        return true;
    }

    private boolean handleUserAction(Scanner sc, int action, CheckAccount checkAccount, SaveAccount saveAccount, FixedAccount fixedAccount, boolean stopFixedAccount, InputHandler inputHandler, AccountService accountService) {
        return switch (action) {
            case SHOW_ACCOUNT -> {
                accountService.printAccountStates(checkAccount, saveAccount, fixedAccount);
                yield false;
            }
            case DEPOSIT -> {
                amount -= accountService.depositChoice(sc, checkAccount, saveAccount, fixedAccount, inputHandler, amount, stopFixedAccount);
                yield false;
            }
            case WITHDRAWAL -> {
                amount += accountService.withdrawalChoice(sc, checkAccount, saveAccount, fixedAccount, inputHandler, SAVING_WITHDRAWAL_FEE);
                yield false;
            }
            case NEXT_MONTH -> {
                System.out.println("행동 종료(다음달로 넘어갑니다.)");
                yield true;
            }
            default -> {
                System.out.println("다시 입력해주세요");
                yield false;
            }
        };
    }

    private void printMenu() {
        System.out.println("1. 계좌 정보");
        System.out.println("2. 입금");
        System.out.println("3. 출금");
        System.out.println("4. 행동 종료");
    }
}
