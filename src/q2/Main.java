package q2;

public class Main {

    public static void main(String[] args) {
        ManagerService manager = new ManagerService();
        manager.addworker(new PermanentWorker("코난", 3000000));
        manager.addworker(new PermanentWorker("장미", 3500000));
        manager.addworker(new TemporaryWorker("알바1", 10000, 80));
        manager.addworker(new TemporaryWorker("알바2", 15000, 105));
        manager.addworker(new SalesWorker("판매왕", 5000000, 2000000, 0.07));
        manager.addworker(new SalesWorker("판매신입", 2200000, 150000, 0.01));
        manager.showAllSalaryInfo();
        manager.showSalaryInfo("코난");
        manager.showTotalSalary();

    }
}
