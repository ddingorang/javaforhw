package q2;

public class PermanentWorker extends Worker{

    private int salary;
    public PermanentWorker(String name, int salary) {
        this.name = name;
        this.salary = salary;
    }
    public int getPay() {
        return salary;
    }

    @Override
    public void showSalaryInfo() {
        System.out.println("사원 " + this.name + "의 급여는 " + this.getPay() + "원");
    }

}
