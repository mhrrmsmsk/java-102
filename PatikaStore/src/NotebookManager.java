import java.util.ArrayList;
import java.util.Scanner;

public class NotebookManager implements NotebookManagerService{
    Scanner input = new Scanner(System.in);
    int id=1;
    ArrayList<Notebook> notebooks = new ArrayList<>();


    public NotebookManager(){
        addDefaultNotebooks();
    }

    @Override
    public void listAll() {
        System.out.println("Notebook Listesi\n");
        System.out.println("------------------------------------------------------------------------------------------");
        System.out.println("| ID | Ürün Adı                   | Fiyat     | Marka    | Depolama | Ekran   | RAM      | ");
        System.out.println("------------------------------------------------------------------------------------------");
        for (Notebook notebook: notebooks){
            System.out.format("| %-2s | %-26s | %-9s | %-8s | %-8s | %-7s | %-8s |\n",notebook.getId(), notebook.getProductName(), notebook.getPrice(),notebook.getBrand(), notebook.getMemory(), notebook.getScreenSize(), notebook.getRam());
        }
        System.out.println("------------------------------------------------------------------------------------------");
    }

    @Override
    public void addNotebook() {
        int newId = 0;
        String productName= null;
        double price = 0;
        String brandName = null;
        int memory = 0;
        double screenSize = 0;
        int ram = 0;
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
            System.out.print("Ram :");
            ram = input.nextInt();
        } catch (Exception e) {
            System.out.println("Yanlış bilgi");}
        notebooks.add(new Notebook(newId,productName,price,brandName,0,0,screenSize,ram,memory));
        if (id== notebooks.size()){
            System.out.println("Ürün başarıyla yüklendi...");
        }

    }

    @Override
    public void deleteNotebook() {

        System.out.print("Silinecek cihazın ID'sini giriniz :");
        int deleteByID = input.nextInt();
        for (Notebook notebook : notebooks){
            if (notebook.getId()==deleteByID){
                notebooks.remove(notebook);
                break;
            }
        }

    }
    @Override
    public void addDefaultNotebooks() {
        Brand brand = new Brand();
        Notebook notebook1 = new Notebook(id++,"HUAWEI Matebook 14",7000.0 ,Brand.getBrand("Huawei"),0,20,14,16,512);
        Notebook notebook2 =  new Notebook(id++,"LENOVO V14 IGL",3699.0,Brand.getBrand("Lenovo"),0,12,14,8,1024);
        Notebook notebook3 = new Notebook(id++,"ASUS Tuf Gaming",8199.0,Brand.getBrand("Asus"),0,34,15,16,256);
        notebooks.add(notebook1);
        notebooks.add(notebook2);
        notebooks.add(notebook3);
    }
}
