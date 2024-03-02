public class Notebook extends Product{
    private double ScreenSize;
    private int ram;
    private int memory;


    public Notebook(int id, String productName, double price, String brand, double discount, int unitInStock, double screenSize, int ram, int memory) {
        super(id, productName, price, brand, discount, unitInStock);
        ScreenSize = screenSize;
        this.ram = ram;
        this.memory = memory;
    }

    public double getScreenSize() {
        return ScreenSize;
    }

    public void setScreenSize(double screenSize) {
        ScreenSize = screenSize;
    }

    public int getRam() {
        return ram;
    }

    public void setRam(int ram) {
        this.ram = ram;
    }

    public int getMemory() {
        return memory;
    }

    public void setMemory(int memory) {
        this.memory = memory;
    }
}
