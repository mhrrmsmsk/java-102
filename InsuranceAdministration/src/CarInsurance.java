import java.util.Date;

public class CarInsurance extends Insurance{

    public CarInsurance(String insName, double insPrice, Date isnStart, Date insFinish) {
        super(insName, insPrice, isnStart, insFinish);
    }

    @Override
    double calculate() {
        return getInsPrice();
    }
}
