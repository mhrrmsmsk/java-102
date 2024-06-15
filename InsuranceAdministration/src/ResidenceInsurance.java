import java.util.Date;

public class ResidenceInsurance extends Insurance{


    public ResidenceInsurance(String insName, double insPrice, Date isnStart, Date insFinish) {
        super(insName, insPrice, isnStart, insFinish);
    }

    @Override
    double calculate() {
        return getInsPrice();
    }
}
