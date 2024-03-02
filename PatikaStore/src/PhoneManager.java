import java.util.ArrayList;
import java.util.Scanner;

public class PhoneManager implements PhoneManagerService{
    Scanner input = new Scanner(System.in);
    private ArrayList<Phone> phones= new ArrayList<>();
     int id=1;

    public PhoneManager() {
        addDefaultPhones();

    }

    @Override
    public void addDefaultPhones() {
        Brand brand = new Brand();
        Phone phone1 = new Phone(id++,"SAMSUNG GALAXY A51",7000.0,Brand.getBrand("Samsung"),0,0,128,6.5,4000,6,"Black");
        Phone phone2 = new Phone(id++,"iPhone 11 64 GB ",7379.0,Brand.getBrand("Apple"),0,0,64,6.1,3000,4,"White");
        Phone phone3 = new Phone(id++,"Redmi Note 10 Pro 8GB ",4012.0 ,Brand.getBrand("Xiaomi"),0,0,128,6.5,4000,8,"Black");
        phones.add(phone1);
        phones.add(phone2);
        phones.add(phone3);
    }



    @Override
    public void listAll() {
        System.out.println("Cep Telefonu Listesi\n");
        System.out.println("----------------------------------------------------------------------------------------------------------------");
        System.out.println("| ID | Ürün Adı                   | Fiyat     | Marka    | Depolama | Ekran   | Pil      | RAM      | Renk     | ");
        System.out.println("----------------------------------------------------------------------------------------------------------------");
        for (Phone phone : phones){
            System.out.format("| %-2s | %-26s | %-9s | %-8s | %-8s | %-7s | %-8s | %-8s | %-8s |\n",phone.getId(), phone.getProductName(), phone.getPrice(),phone.getBrand(), phone.getMemory(), phone.getScreenSize(), phone.getBatteryPower(), phone.getRam(), phone.getColor());
        }
        System.out.println("----------------------------------------------------------------------------------------------------------------");
    }

    @Override
    public void addPhone() {
        int newId;
        String productName;
        double price;
        String brandName;
        int memory ;
        double screenSize ;
        int power;
        int ram ;
        String color ;
        try {
            newId = id++;
            System.out.print("Ürün Adı:");
            productName = input.next();
            System.out.print("Fiyat :");
            price = input.nextDouble();
            System.out.print("Marka :");
            brandName = input.next();
            System.out.print("Depolama :");
            memory = input.nextInt();
            System.out.print("Ekran :");
            screenSize = input.nextDouble();
            System.out.print("Pil :");
            power = input.nextInt();
            System.out.print("Ram :");
            ram = input.nextInt();
            System.out.print("Renk :");
            color = input.nextLine();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        phones.add(new Phone(newId,productName,price,brandName,0,0,memory,screenSize,power,ram,color));
        if (id== phones.size()){
            System.out.println("Başarıyla eklendi");
        }


    }

    @Override
    public void deletePhone() {
        System.out.print("Silinecek cihazın ID'sini giriniz :");
        int deleteByID = input.nextInt();
for (Phone phone : phones){
    if (phone.getId()==deleteByID){
        phones.remove(phone);
        break;
    }
}
    }

}
