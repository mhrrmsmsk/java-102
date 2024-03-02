import java.util.Scanner;

public class User {
    Scanner input = new Scanner(System.in);
   private PhoneManager phoneManager = new PhoneManager();
   private NotebookManager notebookManager = new NotebookManager();
   private Brand brand = new Brand();


    public User() {

        init();
    }
public void init(){
        boolean checkRun=true;
        while (checkRun){
            System.out.println("PatikaStore Ürün Yönetim Paneli !");
            System.out.println("1 - Notebook İşlemleri");
            System.out.println("2 - Cep Telefonu İşlemleri");
            System.out.println("3 - Marka İşlemleri");
            System.out.println("0 - Çıkış Yap");
            System.out.print("Tercihiniz :");
            int preferNumber = input.nextInt();
            switch (preferNumber){
                case 1:
                    notebookOpr();
                    break;
                case 2:
                    phoneOpr();
                    break;
                case 3:
                    brandOpr();
                    break;
                case 0:
                    checkRun=false;
                    System.out.println("Güle Güle...");
                    break;
                default:
                    System.out.println("Yanlış işlem girdiniz!");
                    break;
            }

        }
}
public void brandOpr(){
        boolean check = true;
        while (check){
            System.out.println("1- Marka Listele");
            System.out.println("2-Marka Ekle");
            System.out.println("3-Marka Sil");
            System.out.println("0-Ana ekrana dön");
            System.out.print("Tercihiniz :");
            int prefer = input.nextInt();
            switch (prefer){
                case 1:
                    brand.getAllBrands();
                    break;
                case 2:
                    System.out.println("Marka adı:");
                    String brandName = input.next();
                    brand.addBrand(brandName);
                    System.out.println();
                    brand.getAllBrands();
                    break;
                case 3:
                    brand.getAllBrands();
                    System.out.println("Marka adı:");
                    String deleteBrandName = input.next();
                    brand.deleteBrand(deleteBrandName);
                    System.out.println();
                    brand.getAllBrands();
                    break;
                case 0 :
                    check = false;
                    System.out.println("Ana sayfaya dönüyor...");
                    break;
                default:
                    check = false;
                    System.out.println("Ana sayfaya dönüyor...");
                    break;
            }

        }
}
public void phoneOpr(){
        boolean check = true;

    while (check){
        System.out.println("1-Ürünleri liste ");
        System.out.println("2-Ürün ekle ");
        System.out.println("3-Ürün sil ");
        System.out.println("0-Ana ekrana dön ");
        System.out.print("Tercihiniz :");
        int prefer = input.nextInt();
        switch (prefer){
            case 1:
                phoneManager.listAll();
                System.out.println();
                break;
            case 2:
                phoneManager.addPhone();
                System.out.println();
                phoneManager.listAll();
                break;
            case 3:
                phoneManager.listAll();
                System.out.println();
                phoneManager.deletePhone();
                System.out.println();
                phoneManager.listAll();
                break;
            case 0:
                check = false;
                System.out.println("Ana sayfaya dönüyor...");
                break;
        }
    }

}
public void notebookOpr(){
boolean check = true;
while (check){
    System.out.println("1-Ürünleri liste ");
    System.out.println("2-Ürün ekle ");
    System.out.println("3-Ürün sil ");
    System.out.println("0-Ana ekrana dön ");
    System.out.print("Tercihiniz :");
    int prefer = input.nextInt();
    switch (prefer){
        case 1:
            notebookManager.listAll();
            System.out.println();
            break;
        case 2 :
            notebookManager.addNotebook();
            System.out.println();
            notebookManager.listAll();
            break;
        case 3:
            notebookManager.listAll();
            System.out.println();
            notebookManager.deleteNotebook();
            System.out.println();
            notebookManager.listAll();
            break;
        case 0:
            System.out.println("Ana sayfaya dönüyor...");
            check=false;
            break;
        default:
            System.out.println("Ana sayfaya dönüyor...");
            check = false;
            break;

    }
}
}

    public PhoneManager getPhoneManager() {
        return phoneManager;
    }

    public void setPhoneManager(PhoneManager phoneManager) {
        this.phoneManager = phoneManager;
    }

    public NotebookManager getNotebookManager() {
        return notebookManager;
    }

    public void setNotebookManager(NotebookManager notebookManager) {
        this.notebookManager = notebookManager;
    }

    public Brand getBrand() {
        return brand;
    }

    public void setBrand(Brand brand) {
        this.brand = brand;
    }
}
