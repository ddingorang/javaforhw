package q3;

import java.util.*;

// 정기예금
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
                + this.accountNumber + ", 예치금: " + this.balance + "원, 예금주: " + this.owner + ")");
        System.out.println("* 예치 개월에 따른 적용 금리");

        // HashMap에서 키를 추출하여 List로 저장
        List<Integer> keys = new ArrayList<>(interestList.keySet());

        // 키를 오름차순으로 정렬
        Collections.sort(keys);
        for (Integer key : keys) {
            System.out.println(key + "개월 이상\t" + (double)(Math.round(interestList.get(key) * 10000))/100 + "%");
        }
    }

    @Override
    public void showMenu() {
        System.out.print("정기 예금이 만기되었습니다. (+: 만기처리, -: 인출, T: 이체, I: 정보) ");
    }

    public void termination(ArrayList<AccountImpl> accountList) {
        Scanner sc = new Scanner(System.in);
        while(true) {
            System.out.print("예치 개월 수를 입력하세요. (1 ~ 60개월) ");
            String input = sc.nextLine();
            if(input.equals("0") || input.equals("\n")) {
                break;
            }
            while(true) {
                int int_input = Integer.parseInt(input);
                double ir = getInterestRate(int_input);
                System.out.print(int_input + "개월(적용금리" + (double)(Math.round(ir * 10000))/100 + "%)로 만기 처리하시겠어요? (y/n) ");
                String choice = sc.nextLine();
                if(choice.equals("y")) {
                    System.out.print("어디로 보낼까요? (1: 자유입출금, 3: 마이너스) ");
                    int c = sc.nextInt();
                    if(c == 0) break;
                    AccountImpl selectedAccount = accountList.get(c-1);
                    int amount = (int) (this.balance * (1000 + (double)(Math.round(ir * 10000))/10) / 1000);
                    this.balance -= amount;
                    selectedAccount.deposit(amount);

                    System.out.println(accountType.get(c-1) + "에 " + amount + "원이 입금되었습니다.");
                    System.out.println("정기예금 통장은 해지되었습니다. 감사합니다.");
                    accountList.remove(this); // 통장 해지
                    return;
                }
                else break;


            }
        }

    }

    // 예치 개월 수에 따른 금리 계산 메서드 (조건 추가)
    public double getInterestRate(int months) {

        if (months >= 1 && months < 3) {
            return interestList.get(1);
        }
        else if (months >= 3 && months < 6) {
            return interestList.get(3);
        }
        else if (months >= 6 && months < 9) {
            return interestList.get(6);
        }
        else if (months >= 9 && months < 12) {
            return interestList.get(9);
        }
        else if (months >= 12 && months < 24) {
            return interestList.get(12);
        }
        else if (months >= 24 && months < 36) {
            return interestList.get(24);
        }
        else if (months >= 36 && months < 48) {
            return interestList.get(36);
        }
        else if (months >= 48) {
            return interestList.get(48);
        }
        return 0.0;
    }
}
