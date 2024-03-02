import java.util.TreeMap;
public class Brand {
    private int id;
    private String brandName;
   private static TreeMap<String,Integer> brands = new TreeMap<>();
    int i =1;

    public Brand() {
        addDefaultBrand();
    }


public void addDefaultBrand(){
   this.brands.put("Samsung",i++);
    this.brands.put("Lenovo",i++);
    this.brands.put("Apple",i++);
    this.brands.put("Huawei",i++);
    this.brands.put("Casper",i++);
    this.brands.put("Asus",i++);
    this.brands.put("HP",i++);
    this.brands.put("Xiaomi",i++);
    this.brands.put("Monster",i++);
}
    public  static String getBrand(String brandName){

        if(brands.containsKey(brandName)){
            return brandName;
        }else{
            return null;
        }

    }

public void addBrand(String brandName){
this.brands.put(brandName,i++);
}

public void deleteBrand(String brandName){
   brands.remove(brandName);
}
public void getAllBrands(){
    System.out.println("Markalarımız :");
    System.out.println("--------------");
        for (String brand : brands.keySet()){
            System.out.println("- "+brand);
        }
    System.out.println("--------------");
}

    public TreeMap<String , Integer> getBrands() {
        return brands;
    }
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getBrandName() {
        return brandName;
    }
    public void setBrandName(String brandName) {
        this.brandName = brandName;
    }
}
