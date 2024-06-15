import java.util.TreeSet;

public class AccountManager {
    private TreeSet<Account> accounts;

    public AccountManager() {
        this.accounts = new TreeSet<>();
    }
    void addAccount(Account account){
        accounts.add(account);

    }
    public Account login(String email,String password){
        for (Account account : accounts){
            try {
                account.login(email,password);
                return account;
            } catch (InvalidAuthenticationException e) {
                e.getMessage();
            }
        }
        return null;
    }
    public User register(String name, String surname, String email, String password, String occupation, int age){
        User newUser = new User(name,surname,email,password,occupation,age);
        return newUser;
    }

    public TreeSet<Account> getAccounts() {
        return accounts;
    }

    public void setAccounts(TreeSet<Account> accounts) {
        this.accounts = accounts;
    }
}
