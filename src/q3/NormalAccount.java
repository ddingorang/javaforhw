package q3;

// 자유입출금
public class NormalAccount extends AccountImpl{

    public NormalAccount(int accountNumber, String owner, int balance) {
        super(accountNumber, owner, balance);
    }

    @Override
    public void withdraw(int amount) throws InsufficientBalanceException{
        // 잔액이 부족할 경우 예외를 던짐
        if (amount > this.balance) {
            throw new InsufficientBalanceException("잔액이 부족합니다! (잔액: " + this.balance + "원)");
        }
        this.balance -= amount;

    }

    @Override
    public void transfer(int amount, Account destination) throws InsufficientBalanceException {
        // 잔액이 부족할 경우 예외를 던짐
        if (amount > this.balance) {
            throw new InsufficientBalanceException("잔액이 부족합니다! (잔액: " + this.balance + "원)");
        }
        this.balance -= amount;
        destination.deposit(amount);
    }

}
