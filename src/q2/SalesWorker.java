package q2;

public class SalesWorker extends PermanentWorker{

    private int salesAmount;
    private double bonusRatio;

    public SalesWorker(String name, int salary, int salesAmount, double bonusRatio) {
        super(name, salary);
        this.salesAmount = salesAmount;
        this.bonusRatio = bonusRatio;
    }

    @Override
    public int getPay() {
        return (int)(super.getPay() + salesAmount * bonusRatio);
    }

    @Override
    public void showSalaryInfo() {
        System.out.println("사원 " + this.name + "의 급여는 월급 " + super.getPay()
        + "원, 수당 " + (salesAmount*bonusRatio) + "원을 합한 총액 " + this.getPay() + "원");
    }
}
