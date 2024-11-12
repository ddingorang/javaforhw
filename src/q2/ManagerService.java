package q2;

import java.util.ArrayList;
import java.util.List;

public class ManagerService {

    private List<Worker> workers;
    public ManagerService() {
        this.workers = new ArrayList<Worker>();
    }

    public void addworker(Worker worker) {
        workers.add(worker);
    }

    public void showAllSalaryInfo() {
        for (Worker worker : workers) {
            worker.showSalaryInfo();
        }
    }

    public void showSalaryInfo(String name) {
        for (Worker worker : workers) {
            if (worker.name.equals(name)) {
                worker.showSalaryInfo();
            }
        }
    }

    public void showTotalSalary() {
        int totalsalary = 0;
        for (Worker worker : workers) {
            totalsalary += worker.getPay();
        }
        System.out.println("모든 사원들의 급여 총액은 : " + totalsalary + "원");
    }
}
