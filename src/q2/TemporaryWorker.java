package q2;

public class TemporaryWorker extends Worker{

    private int payPerHour;
    private int workTime;

    public TemporaryWorker(String name, int payPerHour, int workTime) {
        super(name);
        this.payPerHour = payPerHour;
        this.workTime = workTime;
    }

    @Override
    public int getPay() {
        return this.payPerHour * this.workTime;
    }

    @Override
    public void showSalaryInfo(String name) {
        System.out.println("사원 " + name + "의 근무시간은" + this.workTime
        + "시간, 시간 수당은 " + this.payPerHour + "원 급여는 " + this.getPay() + "원");
    }
}
