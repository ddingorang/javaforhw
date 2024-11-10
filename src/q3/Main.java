package q3;

import java.util.Scanner;

import static q3.AccountImpl.accountType;

public class Main {
    public static void main(String[] args) {
        NormalAccount one = new NormalAccount(1, "홍길동", 0);
        TimeDepositAccount two = new TimeDepositAccount(2, "홍길동", 50000000);
        MinusAccount three = new MinusAccount(3, "홍길동", 0);
        Scanner sc = new Scanner(System.in);

        while(true) {
            System.out.print("통장을 선택하세요(1: 자유입출금, 2: 정기예금, 3: 마이너스)");
            AccountImpl cur = null; // 선택된 계좌
            String choice = sc.next();
            switch (choice) {
                case "1" -> cur = one;
                case "2" -> cur = two;
                case "3" -> cur = three;
                case "0", "\n" -> {
                    System.out.println("금일 OneHanaBank는 업무를 종료합니다. 감사합니다.");
                    System.exit(0);
                }
            }

            cur.showInformation();

            while(true) {
                cur.showMenu();
                String input = sc.next();

                if(input.equals("+")) {
                    if(cur instanceof TimeDepositAccount) {
                        ((TimeDepositAccount) cur).termination();
                    }
                    System.out.print("입금하실 금액은? ");
                    int money = sc.nextInt();
                    cur.deposit(money);
                    System.out.println(accountType.get(cur.accountNumber-1) + " 통장에 " +
                            money + "원이 입금되었습니다!");
                }

                else if(input.equals("-")) {
                    int money = -1;
                    while(money != 0 ) {
                        System.out.print("출금하실 금액은? ");
                        money = sc.nextInt();
                        try {
                            cur.withdraw(money);
                            if(money != 0) {
                                System.out.println(accountType.get(cur.accountNumber-1) + " 통장에서 " +
                                        money + "원이 출금되었습니다!");
                            }
                        } catch (InsufficientBalanceException | RestrictedWithdrawException e) {
                            System.out.println(e.getMessage());
                        }
                    }

                }

                else if(input.equals("I")) {
                    cur.showInformation();
                }

                else if(input.equals("0") || input.equals("\n")){
                    break;
                }
            }

        }

    }
}
