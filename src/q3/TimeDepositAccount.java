package q3;

import java.util.HashMap;
import java.util.Scanner;

public class TimeDepositAccount extends AccountImpl{

    private static HashMap<Integer, Double> interestList;

    static {
        interestList = new HashMap<>();
        interestList.put(1, 0.03);
        interestList.put(3, 0.0335);
        interestList.put(6, 0.034);
        interestList.put(9, 0.0335);
        interestList.put(12, 0.0335);
        interestList.put(24, 0.029);
        interestList.put(36, 0.029);
        interestList.put(48, 0.029);
    }

    public TimeDepositAccount(int accountNumber, String owner, int balance) {
        super(accountNumber, owner, balance);
    }

    @Override
    public void withdraw(int amount) throws RestrictedWithdrawException {
        throw new RestrictedWithdrawException("출금할 수 없는 통장입니다.");
    }

    @Override
    public void transfer(int amount, Account destination) throws RestrictedTransferException {
        throw new RestrictedTransferException("이체할 수 없는 통장입니다.");
    }

    @Override
    public void showInformation() {
        System.out.println(accountType.get(this.accountNumber - 1) + " (계좌번호: "
                + this.accountNumber + ", 예치금: " + this.balance + "원, 예금주: " + this.owner);
        System.out.println("* 예치 개월에 따른 적용 금리");
        for (Integer months : interestList.keySet()) {
            System.out.println(months + "개월 이상\t" + interestList.get(months) * 100 + "%");
        }
    }

    @Override
    public void showMenu() {
        System.out.println("정기 예금이 만기되었습니다. (+: 만기처리, -: 인출, T: 이체, I: 정보) ");
    }

    public void termination() {
        Scanner sc = new Scanner(System.in);
        while(true) {
            System.out.print("예치 개월 수를 입력하세요. ");
            String input = sc.nextLine();
            if(input.equals("0") || input.equals("\n")) {
                break;
            }
            while(true) {

            }
        }

        int month = sc.nextInt();

    }
}
