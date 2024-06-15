import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;

public class User {
    private String name;
    private String surname;
    private String email;
    private String password;
    private String occupation;
    private int age;
    private ArrayList<Address> addresses;
    private Date lastLogin;

    public User(String name, String surname, String email, String password, String occupation, int age) {
        this.name = name;
        this.surname = surname;
        this.email = email;
        this.password = password;
        this.occupation = occupation;
        this.age = age;
        this.addresses = new ArrayList<>();
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getOccupation() {
        return occupation;
    }

    public void setOccupation(String occupation) {
        this.occupation = occupation;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public ArrayList<Address> getAddresses() {
        return addresses;
    }


    public void setLastLogin(Date lastLoginDate) {
        this.lastLogin = lastLogin;
    }

    public Date getLastLogin() {
        return lastLogin;
    }

    @Override
    public String toString() {
        return "User{" +
                "firstName='" + name + '\'' +
                ", lastName='" + surname + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", profession='" + occupation + '\'' +
                ", age=" + age +
                ", addresses=" + getAddresses() +
                ", lastLoginDate=" + getLastLogin() +
                '}';
    }
}
