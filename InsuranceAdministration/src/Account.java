
import java.util.*;

public abstract class Account implements Comparable<Account> {

    private User user;

    private List<Insurance> insuranceList;

    private AuthenticationStatus status = AuthenticationStatus.FAIL;

    public Account(User user) {
        this.user = user;
        this.insuranceList = new ArrayList<>();
        this.status = AuthenticationStatus.FAIL;
    }


    public AuthenticationStatus login(String email, String password) throws InvalidAuthenticationException {
        if (this.getUser().getEmail().equals(email) && this.getUser().getPassword().equals(password)) {
            this.status = AuthenticationStatus.SUCCESS;
            user.setLastLogin(new Date());
            return this.status;
        } else {
            throw new InvalidAuthenticationException("Invalid email or password.");
        }

    }

    public void addAddress(Address address) {
        AddressManager.addAddress(user, address);
    }

    public void removeAddress(Address address) {
        AddressManager.deleteAddress(user, address);
    }

    public final void showUserInfo() {

        System.out.println(user.toString());

        for (Insurance insurance : insuranceList){
            System.out.println(insurance.toString());
        }
    }

    abstract void addInsurance(Insurance insurance);

    public User getUser() {
        return user;
    }

    @Override
    public int compareTo(Account other) {
        return this.user.getEmail().compareTo(other.user.getEmail());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Account account = (Account) o;
        return Objects.equals(user, account.user) && Objects.equals(insuranceList, account.insuranceList) && status == account.status;
    }

    @Override
    public int hashCode() {
        return Objects.hash(user, insuranceList, status);
    }

    public List<Insurance> getInsuranceList() {
        return insuranceList;
    }

}
