package com.game.util;

import java.util.Scanner;

public class RulePrinter {
    public boolean printRule(Scanner sc) {
        System.out.println("--------------------------------------------------------------------------");
        System.out.println("6개월 동안 최대한 돈을 모으는 게임입니다.");
        System.out.println("1개월 마다 1000원을 받습니다.");
        System.out.println("매달 300~600원씩 랜덤하게 지출이 발생합니다.");
        System.out.println("입출금계좌에 지출 비용이 없다면 게임이 종료됩니다.(다음 달로 가기 전 계좌에 돈을 다 넣으세요)");
        System.out.println("입출금계좌 : 입금 출금이 자유롭습니다.");
        System.out.println("저축계좌 : 달마다 계좌 잔액의 10% 이자를 줍니다. 출금 시 수수료 500원이 발생합니다.");
        System.out.println("정기예금계좌 : 기간이 지나면 계좌 잔액의 50% 이자를 줍니다. 출금이 불가능합니다.");
        System.out.println("--------------------------------------------------------------------------");
        return true;
    }
}
