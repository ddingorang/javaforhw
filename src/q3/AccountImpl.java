package q3;

import java.util.ArrayList;

public class AccountImpl implements Account{

    protected final int accountNumber;
    protected final String owner;
    protected int balance;
    protected static ArrayList<String> accountType;

    static {  // static 초기화 블록을 사용하여 accountType 초기화
        accountType = new ArrayList<>();
        accountType.add("자유입출금 통장");
        accountType.add("정기예금 통장");
        accountType.add("마이너스 통장");
    }

    public AccountImpl(int accountNumber, String owner, int balance) {
        this.accountNumber = accountNumber;
        this.owner = owner;
        this.balance = balance;
    }

    @Override
    public void deposit(int amount) {
        this.balance += amount;
    } // 예금

    @Override
    public void withdraw(int amount) throws InsufficientBalanceException, RestrictedWithdrawException { // 출금
        this.balance -= amount;
    }

    @Override
    public void transfer(int amount, Account destination) throws InsufficientBalanceException, RestrictedTransferException { // 이체
        this.balance -= amount;
        destination.deposit(amount);
    }

    @Override
    public void showInformation() { // 계좌 정보
        System.out.println(accountType.get(this.accountNumber-1) + " (계좌번호: "
        + this.accountNumber + ", 잔액: " + this.balance + "원, 예금주: " + this.owner + ")");
    }

    @Override
    public void showMenu() { // 메뉴 표시
        System.out.print("원하시는 업무는? (+: 입금, -: 출금, T: 이체, I: 정보) ");
    }
}
