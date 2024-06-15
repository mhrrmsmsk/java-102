import java.util.Date;

public class HealthInsurance extends Insurance{

    public HealthInsurance(String insName, double insPrice, Date isnStart, Date insFinish) {
        super(insName, insPrice, isnStart, insFinish);
    }

    @Override
    double calculate() {
        return getInsPrice();
    }
}
