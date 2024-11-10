package q3;

public interface Account {

    void deposit(int amount);
    void withdraw(int amount) throws RestrictedWithdrawException, InsufficientBalanceException;
    void transfer(int amount, Account destination) throws RestrictedTransferException, InsufficientBalanceException;
    void showInformation();
    void showMenu();

}
