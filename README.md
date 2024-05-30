# 저축 게임

자바의 기본문법, 상속, 스레드 간 상호작용하는 기능을 공부하기 위해 제작한 cli프로그램 입니다.


## 프로젝트 개요

- 콘솔 기반의 계좌 시스템을 이용한 게임
- 사용자가 기능이 다른 계좌를 선택 후 돈을 저축해주는 기능
- 현재 계좌의 정보, 입금, 출금 기능을 제공

## 게임 목표

- 6개월 동안 최대한 돈을 모으는 게임
- 사용자는 달 마다 1000원을 받는다.
- 사용자는 달 마다 300~600원의 랜덤한 지출이 발생한다
- 기능이 다른 계좌의 기능을 활용해 돈을 모은다.


# 폴더 및 파일 설명

# controller

게임을 진행하는데 필요한 기능을 구현한 패키지 (게임에 필요한 상수 설정, 계좌 생성, 메뉴 출력 등)

## GameController

메서드 설명

### `startGame`

- 사용자의 입력 : 이름과 fixedAccount기간을 입력 받는다.
- 각 기능이 다른 계좌를 생성하고 계좌의 정보를 출력
- 게임 설명을 출력한다.

### `playGame`

- 마지막 달까지 게임 반복 for(i < 6)
- CheckAccount에서 지불금액을 출금한다.(지불금액 없으면 게임 패배)
- FixedAccount 기간이 만료되었는지 확인한다.(입금 기간이 정해져있는 계좌)
- SaveAccount 에 이자를 입금한다.(이자를 주는 계좌)
- 매 달 초기 금액을 준다.
- 사용자가 할 수 있는 행동을 보여준다.
- 원하는 행동을 선택한다.

### `handleUserAction`

- 유저의 행동에 맞는 기능을 수행
- 1번. 계좌의 정보를 출력
- 2번. 원하는 계좌를 선택한 후 입금
- 3번. 원하는 계좌를 선택한 후 출금
- 4번. 다음달로 넘어간다.

### `printMenu`

- 유저에 행동에 맞는 기능을 출력한다.
- 상동

# service

## AccountService

메서드 설명

### `depositChoice`

- 입금할 계좌를 선택하는 기능
- FixedAccount를 선택했을 시 계좌에 입금할 수 있는 기간이 끝났는지 체크
    - 기간이 끝났다면 ‘당좌예금계좌 기간 만료’ 를 출력 후 종료
- 원하는 계좌를 선택 후 입금할 금액을 입력한다.
- 작업이 완료된 후 return 입력한 금액;

### `withdrawalChoice`

- 출금할 계좌를 선택하는 기능
- 기능은 depositChoice와 같음

### `printAccountStates`

- 계좌 정보를 출력하는 기능

### `generateAccountNumber`

- 계좌번호를 발급하는 기능
- 0~9의 랜덤한 숫자 12개를 이어붙인 후 반환

## GameService

### `handleMonthlyFee`

- 매 달 지출되는 비용이 있는지 확인하는 기능
- CheckAccount와 매 달 지출되는 랜덤한 비용(300~600)을 매개변수로 받는다.
- CheckAccount의 계좌 금액보다 비용이 더 크다면 게임 종료
- CheckAccount의 계좌 금액보다 비용이 작다면 지출된 비용을 출력하고 종료(반환값 없음)

### `handleExpired`

- FixedAccount의 기간이 끝나면 (계좌 잔액 + 이자)를 CheckAccount에 입금하는 기능
- 계좌 잔액 + ( 계좌 잔액 * 설정한 비율(%) / 100) 의 금액을 입금

### `handleSavingInterest`

- SaveAccount의 계좌에 매 달 이자를 지급하는 기능
- 계좌에 잔액이 있는지 확인 후
- 계좌 잔액 / 설정한 비율 의 금액을 SaveAccount에 입금

# util

## InputHandler

### `getUserName`

- 이름 입력 유효성 검사

### `getUserChoice`

- 유저의 행동 선택 입력 유효성 검사
- 선택가능한 메뉴(maxOption)을 매개변수로 받는다.

### `getUserAmount`

- 유저의 잔액의 입력 유효성 검사
- 음수, 정수가 아닌 입력을 검사

### `getPeriodFromUser`

- 고정계좌(정기예금)의 기간을 설정

## RulePrinter

### `printRule`

- 게임 설명을 출력
- y를 입력시 설명 종료
- 이외의 문자 입력시 설명 다시 듣기

# dto/vo

getter/setter가 있으면 dto, 없으면 vo로 분리

## DTO

### 클래스 설명
`AccountDTO`

- BankDTO의 상속을 받음
- 필드 balance를 추가(계좌 잔액)

### `BankDTO`

- 필드 owner(소유자), acNum(계좌번호)

## VO

클래스 설명

### `CheckAccount`(입출금계좌), `SaveAccount`(저축계좌)

- AccountDTO의 상속을 받음
- 입금 메서드 deposit
- 출금 메서드 withdrawal
- 계좌 정보 출력 메서드 toString이 존재

### `FixedAccount`(고정계좌, 당좌예금계좌)

- 기간을 저장하는 필드 period추가
- 상동

## GameController의 전역변수
    - END_DAY = 진행할 게임 횟수(month)
    - MIN_FEE = 달 마다 지출되는 최소금액 설정
    - MAX_FEE = 달 마다 지출되는 최대금액 설정
    - INITAIL_MONEY = 달 마다 지급되는 금액 설정
    - SAVING_WITHDRAWAL_FEE = SaveAccount 출금 수수료 설정
    - amount = 소유 금액, 입금할 때 amount금액을 입금, 계좌와 다르게 돈이 저장되지 않고 다음달로 진행시 초기화
    - SHOW_ACCOUNT : 사용자 행동에서 계좌정보를 출력하는 상수(1번)
    - DEPOSIT : 사용자 행동에서 입금을 선택하는 상수(2번)
    - WITHDRAWAL : 사용자 행동에서 출금을 선택하는 상수(3번)
    - NEXT_MONTH : 행동종료(다음달) 을 선택하는 상수(4번)

# 간단한 스레드 구현

음악 플레이를 구현

### MusicPlayer 클래스 추가

- 배경음악을 게임이 끝날 때 까지 반복재생하도록 구현하였습니다.
- Thread를 사용하여 게임스레드와 별도의 스레드로 진행됩니다.
- .getResourceAsStream으로 .wav음악파일을 불러오기 위해 src/main/resource에 .wav파일을 넣었습니다.
- 게임이 종료될 때 stopMusic 메서드를 호출하여 배경음악을 종료합니다.
- 코드
    
    ```jsx
    package com.game.bgm;
    
    import javax.sound.sampled.*;
    import java.io.BufferedInputStream;
    import java.io.File;
    import java.io.IOException;
    import java.io.InputStream;
    
    public class MusicPlayer extends Thread {
        private final String filePath;
        private Clip clip;
    
        public MusicPlayer(String filePath) {
            this.filePath = filePath;
        }
    
        @Override
        public void run() {
            try {
                InputStream audioSrc = getClass().getResourceAsStream(filePath);
    
                InputStream bufferedIn = new BufferedInputStream(audioSrc);
                AudioInputStream audioStream = AudioSystem.getAudioInputStream(bufferedIn);
                clip = AudioSystem.getClip();
                clip.open(audioStream);
                clip.loop(Clip.LOOP_CONTINUOUSLY);
                clip.start();
                clip.addLineListener(event -> {
                    if (event.getType() == LineEvent.Type.STOP) {
                        clip.close();
                    }
                });
    
                while(!clip.isRunning()) {
                    Thread.sleep(100);
                }
                while(clip.isRunning()) {
                    Thread.sleep(100);
                }
            } catch(UnsupportedAudioFileException | IOException | LineUnavailableException | InterruptedException e) {
                e.printStackTrace();
            }
        }
        public void stopMusic() {
            if(clip != null) {
                clip.stop();
                clip.close();
            }
        }
    }
    ```
    

# 스레드간 상호작용 할 수 있는 기능 구현

금리가 변동될 때(InterestRateService) 이자(saveMoneyService)도 같이 변경되도록 하는 기능을 구현

### InterestRateService 클래스 추가

- 10초마다 금리가 변동되도록 구현하였습니다.
- Thread를 사용하여 별도의 스레드로 진행됩니다.
- lock.notify를 사용하여 saveMoneyService에게 작업이 끝났음을 알립니다.
- 코드
    
    ```jsx
    package com.game.service;
    
    import java.util.Random;
    
    public class InterestRateService extends Thread {
        private volatile int Interest;
        private final Object lock;
    
        public InterestRateService(int interest, Object lock) {
            this.Interest = interest;
            this.lock = lock;
        }
    
        public int getInterest() {
            return Interest;
        }
    
        @Override
        public void run() {
            Random random = new Random();
            while(!isInterrupted()) {
                try {
                    Thread.sleep(10000);
                    synchronized (lock) {
                        this.Interest = random.nextInt((50 - 10) + 1) + 10;
                        System.out.println(" -- -- -- -- -- -- --- -- -- -- -- -- --");
                        System.out.println("금리 변동 " + this.Interest);
                        System.out.println(" -- -- -- -- -- -- --- -- -- -- -- -- --");
                        System.out.println();
                        lock.notify();
                    }
                } catch(InterruptedException e) {
                    e.printStackTrace();
                    break;
                }
            }
        }
    }
    ```
    

### SaveMoneyService 클래스 추가

- 금리가 변동될 때마다 이자도 같이 변경되도록 구현하였습니다.
- 직전 이자율을 기준으로 이자율 상승, 이자율 하락, 동결을 출력합니다.
- Thread를 사용하여 별도의 스레드로 진행됩니다.
- lock.wait를 사용하여 InterestRateService의 작업이 끝날 때까지 기다립니다.
- 코드
    
    ```jsx
    package com.game.service;
    
    import java.util.Random;
    
    public class SaveMoneyService extends Thread {
        private final InterestRateService interestRateService;
        private final Object lock;
        private int beforeInterest;
    
        public SaveMoneyService(InterestRateService interestRateService, Object lock, int beforeInterest) {
            this.interestRateService = interestRateService;
            this.lock = lock;
            this.beforeInterest = beforeInterest;
        }
    
        @Override
        public synchronized void run() {
            while(!isInterrupted()) {
                synchronized (lock) {
                    try {
                        lock.wait();
                        int currentInterest = interestRateService.getInterest();
                        if(beforeInterest < currentInterest) {
                            System.out.println("이자율 상승 (+)"+ (currentInterest - beforeInterest));
                        } else if(beforeInterest > currentInterest) {
                            System.out.println("이자율 하락 (-)"+ (beforeInterest - currentInterest));
                        } else {
                            System.out.println("동결 0");
                        }
                        beforeInterest = currentInterest;
                    } catch(InterruptedException e) {
                        e.printStackTrace();
                        System.out.println("저축계좌 이자율 정지");
                        break;
                    }
                }
            }
        }
    }
    ```

[시연연상](https://prod-files-secure.s3.us-west-2.amazonaws.com/38552da6-340d-42c1-a9a1-b181ff331f03/5adb1d7c-3ae0-40d1-b765-030b6d03ccc8/%E1%84%92%E1%85%AA%E1%84%86%E1%85%A7%E1%86%AB_%E1%84%80%E1%85%B5%E1%84%85%E1%85%A9%E1%86%A8_2024-05-30_%E1%84%8B%E1%85%A9%E1%84%92%E1%85%AE_1.20.41.mov)

