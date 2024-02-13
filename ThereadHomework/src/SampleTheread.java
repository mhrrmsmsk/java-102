import java.util.ArrayList;
import java.util.List;

public class SampleTheread extends Thread{
    List<Integer> sublist = new ArrayList<>();
    ArrayList<Integer> evenList = new ArrayList<>();
    ArrayList<Integer> oddList = new ArrayList<>();

    public SampleTheread(List<Integer> sublist, ArrayList<Integer> evenList, ArrayList<Integer> oddList) {
        this.sublist = sublist;
        this.evenList = evenList;
        this.oddList = oddList;
    }

    @Override
    public  void run()
    {
        for (Integer number : sublist){
            if (number%2==0){
                synchronized (evenList){
                    evenList.add(number);
                    System.out.println(Thread.currentThread().getName()+" -> Even = "+number);
                }
            }else {
                synchronized (oddList){
                    oddList.add(number);
                    System.out.println(Thread.currentThread().getName()+" -> Odd = "+number);
                }
            }
        }

    }
}
