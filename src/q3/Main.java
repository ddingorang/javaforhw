package q3;

import java.util.ArrayList;
import java.util.Scanner;

import static q3.AccountImpl.accountType;

public class Main {
    public static void main(String[] args) {
        NormalAccount one = new NormalAccount(1, "홍길동", 0);
        TimeDepositAccount two = new TimeDepositAccount(2, "홍길동", 50000000);
        MinusAccount three = new MinusAccount(3, "홍길동", 0);
        ArrayList<AccountImpl> accountList = new ArrayList<>();
        accountList.add(one);
        accountList.add(two);
        accountList.add(three);
        Scanner sc = new Scanner(System.in);

        while(true) {
            if(accountList.size() == 2) { // 정기예금 계좌가 만기된 이후
                System.out.print("통장을 선택하세요(1: 자유입출금, 3: 마이너스) ");
            }
            else {
                System.out.print("통장을 선택하세요(1: 자유입출금, 2: 정기예금, 3: 마이너스) ");
            }
            AccountImpl cur = null; // 선택된 계좌
            String choice = sc.nextLine();

            if (choice.equals("") || choice.equals("0")) {
                System.out.println("금일 OneHanaBank는 업무를 종료합니다. 감사합니다.");
                System.exit(0);
            }
            switch (choice) {
                case "1" -> cur = one;
                case "2" -> cur = two;
                case "3" -> cur = three;
            }

            cur.showInformation();

            while(true) {
                cur.showMenu();
                String input = sc.nextLine();

                if(input.equals("+")) {
                    if(cur instanceof TimeDepositAccount) {
                        ((TimeDepositAccount) cur).termination(accountList);
                        break;
                    }
                    System.out.print("입금하실 금액은? ");
                    int money = sc.nextInt();
                    sc.nextLine();
                    cur.deposit(money);
                    System.out.println(accountType.get(cur.accountNumber-1) + "에 " +
                            money + "원이 입금되었습니다!");
                }

                else if(input.equals("-")) {
                    int money = -1;
                    while(money != 0 ) {
                        try {
                            money = 0;
                            cur.withdraw(money);
                            System.out.print("출금하실 금액은? ");
                            money = sc.nextInt();
                            sc.nextLine();
                            cur.withdraw(money);
                            if(money != 0) {
                                System.out.println(accountType.get(cur.accountNumber-1) + " 통장에서 " +
                                        money + "원이 출금되었습니다!");
                            }
                        } catch (InsufficientBalanceException | RestrictedWithdrawException e) {
                            System.out.println(e.getMessage());
                            break;
                        }
                    }

                }

                else if(input.equals("T")) {
                    if(cur instanceof TimeDepositAccount) {
                        try {
                            cur.transfer(0, cur);
                        } catch (RestrictedTransferException | InsufficientBalanceException e) {
                            System.out.println(e.getMessage());
                        }
                        continue;
                    }

                    System.out.print("어디로 보낼까요? ");
                    for(int i=0; i<accountType.size(); i++) {
                        if(i== cur.accountNumber-1) continue;
                        System.out.print((i+1)+": " + accountType.get(i));
                        if(i != accountType.size()-1) {
                            System.out.print(", ");
                        }
                    }
                    int c = sc.nextInt();
                    sc.nextLine();
                    if(c==0) break;

                    System.out.print(accountType.get(c-1) + " 통장에 보낼 금액은? ");

                    int amount = sc.nextInt();
                    sc.nextLine();
                    try {
                        cur.transfer(amount, accountList.get(c-1));
                        System.out.println(accountType.get(c-1) + "에 " + amount + "원이 입금되었습니다.");
                        System.out.println(accountType.get(cur.accountNumber-1) + "의 잔액은 " + cur.balance + "원입니다.");
                    } catch (RestrictedTransferException | InsufficientBalanceException e) {
                        System.out.println(e.getMessage());
                    }
                }

                else if(input.equals("I")) {
                    cur.showInformation();
                }

                else if(input.equals("0") || input.isEmpty()){
                    break;
                }
            }

        }

    }
}
