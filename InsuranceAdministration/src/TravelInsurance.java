import java.util.Date;

public class TravelInsurance extends Insurance{
    public TravelInsurance(String insName, double insPrice, Date isnStart, Date insFinish) {
        super(insName, insPrice, isnStart, insFinish);
    }

    @Override
    double calculate() {
        return getInsPrice();
    }
}
