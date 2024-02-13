import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Semaphore;

public class Main {

    public static void main(String[] args) {
        ArrayList<Integer> numbers = new ArrayList<>();
        ArrayList<Integer> evenNumbers = new ArrayList<>();
        ArrayList<Integer> oddNumbers = new ArrayList<>();

        for (int i = 1 ; i<=10000; i++){
            numbers.add(i);
        }

        for (int i = 0; i < 4 ; i++){
            int start = i * 2500;
            int end = (i+1) * 2500;
            List<Integer> subList = numbers.subList(start, end);
            SampleTheread sampleTheread=new SampleTheread(subList,evenNumbers,oddNumbers);
            try {
                sampleTheread.start();
                sampleTheread.join();

            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }


        }

        System.out.println("Even Numbers : " + evenNumbers);


        System.out.println("Odd Numbers : " + oddNumbers);

    }
}