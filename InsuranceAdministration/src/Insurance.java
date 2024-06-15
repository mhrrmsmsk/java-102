import java.util.Date;

 abstract class Insurance {
    private String insName;
    private double insPrice;
    private Date isnStart;
    private Date insFinish;


     public Insurance(String insName, double insPrice, Date isnStart, Date insFinish) {
         this.insName = insName;
         this.insPrice = insPrice;
         this.isnStart = isnStart;
         this.insFinish = insFinish;
     }

     abstract double calculate();
    public String getInsName() {
        return insName;
    }

    public void setInsName(String insName) {
        this.insName = insName;
    }

    public double getInsPrice() {
        return insPrice;
    }

    public void setInsPrice(double insPrice) {
        this.insPrice = insPrice;
    }

    public Date getIsnStart() {
        return isnStart;
    }

    public void setIsnStart(Date isnStart) {
        this.isnStart = isnStart;
    }

    public Date getInsFinish() {
        return insFinish;
    }

    public void setInsFinish(Date insFinish) {
        this.insFinish = insFinish;
    }

     @Override
     public String toString() {
         return "Insurance{" +
                 "insName='" + insName + '\'' +
                 ", insPrice=" + insPrice +
                 ", isnStart=" + isnStart +
                 ", insFinish=" + insFinish +
                 '}';
     }
 }
