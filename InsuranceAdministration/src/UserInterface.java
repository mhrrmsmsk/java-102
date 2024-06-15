import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

public class UserInterface {
    AccountManager accountManager = new AccountManager();
    Scanner input = new Scanner(System.in);
    SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");

    public UserInterface() {
        User user1 = new User("Muharrrem", "Şimşek", "asdfg@gmail.com", "123", "Software Engineer", 22);
        Account account1 = new EnterpriseAccount(user1);
        accountManager.addAccount(account1);
        System.out.println("Sigorta Sistemine Hoşgeldinz...");

        boolean isContinue = true;
        while (isContinue) {
            System.out.println("1-Giriş \n" +
                    "2-Kayıt\n" +
                    "3-Çıkış");
            System.out.print("Tercih:");
            int prefer = input.nextInt();
            input.nextLine();

            switch (prefer) {
                case 1:
                    System.out.print("Email: ");
                    String email = input.nextLine();
                    System.out.print("Şifre: ");
                    String password = input.nextLine();

                    Account loggedAccount = accountManager.login(email, password);
                    if (loggedAccount == null) {
                        System.out.println("Giriş Başarısız!");
                    } else {
                        System.out.println("Giriş Başarılı!");

                    }
                    boolean loginCheck = true;
                    while (loginCheck){
                        System.out.println("Yapmak istediğiniz işlemi giriniz \n 1-Kullanıcı Bilgileri\n 2-Sigorta Ekleme\n 3-Adres Ekleme\n 4-Üst Menüye Geri dön : ");
                        int afterLoginPrefer = input.nextInt();
                        input.nextLine();
                        switch (afterLoginPrefer){
                            case 1:
                                loggedAccount.showUserInfo();
                                System.out.println();
                                break;
                            case 2:
                                addIns(loggedAccount);
                                break;
                            case 3:
                                System.out.print("Addres Giriniz:");
                                System.out.println("Adres türünü Girinin (ev/iş):");
                                String addressType = input.nextLine();
                                System.out.println("Adres Gir:");
                                String address = input.nextLine();
                                if ("ev".equalsIgnoreCase(addressType)) {
                                    Address newHomeAddress = new HomeAddress(address);
                                    loggedAccount.addAddress(newHomeAddress);
                                } else if ("iş".equalsIgnoreCase(addressType)) {
                                    Address newBusinessAddress = new BusinessAddress(address);
                                    loggedAccount.addAddress(newBusinessAddress);
                                }
                                System.out.println("Adres Başıyla Eklendi...");

                                break;
                            case 4:
                                loginCheck=false;
                                break;
                            default:
                                System.out.println("Geçersiz tercih. Lütfen tekrar deneyin.");
                        }
                    }
                    break;
                case 2 :
                  register();
                        break;
                case 3:
                    isContinue = false;
                    break;
                default:
                    System.out.println("Geçersiz tercih. Lütfen tekrar deneyin.");

            }
        }
    }

    void addIns(Account loggedAccount){
        if (loggedAccount != null) {
            System.out.println("Sigorta Tipini Giriniz(sağlık/konut/seyehat/araba):");
            String insuranceType = input.nextLine();

            System.out.println("Başlangıç Tarihini Giriniz (dd-MM-yyyy):");
            String startDateStr = input.nextLine();
            Date startDate = null;
            Date endDate = null;
            try {
                startDate = dateFormat.parse(startDateStr);

                System.out.println("Bitiş Tarihini Giriniz (dd-MM-yyyy):");
                String endDateStr = input.nextLine();
                endDate = dateFormat.parse(endDateStr);

                Insurance insurance = null;
                if ("sağlık".equalsIgnoreCase(insuranceType)) {
                    insurance = new HealthInsurance("Sağlık Sigortası", 1000, startDate, endDate);
                } else if ("konut".equalsIgnoreCase(insuranceType)) {
                    insurance = new ResidenceInsurance("Konut Sigortası", 1500, startDate, endDate);
                } else if ("seyehat".equalsIgnoreCase(insuranceType)) {
                    insurance = new TravelInsurance("Seyehat Sigortası", 500, startDate, endDate);
                } else if ("araba".equalsIgnoreCase(insuranceType)) {
                    insurance = new CarInsurance("Araba Sigortası", 2000, startDate, endDate);
                }

                if (insurance != null) {
                    loggedAccount.addInsurance(insurance);
                    System.out.println("Sigorta Başarıyla Eklendi!");
                } else {
                    System.out.println("Geçersiz Sigorta Tipi.");
                }

                // Display user info again to show the updated insurances
                System.out.println();
                loggedAccount.showUserInfo();
            } catch (ParseException e) {
                System.out.println("Geçersiz Tarih Formatı. Lütfen Bu Formatı Kullanın : dd-MM-yyyy.");
            }
        }
        System.out.println();
    }
    void register(){
        System.out.print("Adı:");
        String newName = input.nextLine();
        System.out.print("Soyadı:");
        String newSurname = input.nextLine();
        System.out.print("Email:");
        String newEmail = input.nextLine();
        System.out.print("Şifre:");
        String newPassword = input.nextLine();
        System.out.print("İş:");
        String newOccupation = input.nextLine();
        System.out.print("Yaş:");
        int newAge = input.nextInt();
        input.nextLine();
        User user = accountManager.register(newName, newSurname, newEmail, newPassword, newOccupation, newAge);
        System.out.println("Hesap Türü(bireysel/kurumsal)");
        String accountType = input.nextLine();
        if (accountType.equalsIgnoreCase("bireysel")) {
            Account account = new IndividualAccount(user);
            accountManager.addAccount(account);
            System.out.println("Adres türünü Girinin (ev/iş):");
            String addressType = input.nextLine();
            System.out.println("Adres Gir:");
            String address = input.nextLine();
            if ("ev".equalsIgnoreCase(addressType)) {
                Address newHomeAddress = new HomeAddress(address);
                account.addAddress(newHomeAddress);
            } else if ("iş".equalsIgnoreCase(addressType)) {
                Address newBusinessAddress = new BusinessAddress(address);
                account.addAddress(newBusinessAddress);
            }
        } else if (accountType.equalsIgnoreCase("kurumsal")) {
            Account account = new EnterpriseAccount(user);
            accountManager.addAccount(account);
            System.out.println("Adres türünü Girinin (ev/iş):");
            String addressType = input.nextLine();
            System.out.println("Adres Gir:");
            String address = input.nextLine();
            if ("ev".equalsIgnoreCase(addressType)) {
                Address newHomeAddress = new HomeAddress(address);
                account.addAddress(newHomeAddress);
            } else if ("iş".equalsIgnoreCase(addressType)) {
                Address newBusinessAddress = new BusinessAddress(address);
                account.addAddress(newBusinessAddress);
            }
        }
    }
}
