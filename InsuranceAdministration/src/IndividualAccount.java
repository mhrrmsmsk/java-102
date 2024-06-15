public class IndividualAccount extends Account{
    public IndividualAccount(User user) {
        super(user);
    }

    @Override
    public void addInsurance(Insurance insurance) {
getInsuranceList().add(insurance);
    }

}
