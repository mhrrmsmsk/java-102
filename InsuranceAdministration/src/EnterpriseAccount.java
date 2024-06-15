public class EnterpriseAccount extends Account{

    public EnterpriseAccount(User user) {
        super(user);

    }

    @Override
    public void addInsurance(Insurance insurance) {
getInsuranceList().add(insurance);
    }

}
