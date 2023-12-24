import java.util.Iterator;
import java.util.TreeSet;

public class Main {
    public static void main(String[] args) {

        Book b1 = new Book("Suç ve Ceza",680,"Dosteyvski","20-12-1780");
        Book b2 = new Book("Ziyan",180,"Hakan Günday","25-05-2004");
        Book b3 = new Book("Satranç",102,"Stefan Zweig","16-02-1980");
        Book b4 = new Book("Vahşetin Çağrısı",230,"Jack London","23-07-1992");
        Book b5 = new Book("Ev Sahibesi",162,"Dosteyvski","20-09-1847");

        TreeSet<Book> books = new TreeSet<>(new OrderByNameComparator());

        books.add(b1);
        books.add(b2);
        books.add(b3);
        books.add(b4);
        books.add(b5);


        TreeSet<Book> books1 = new TreeSet<>(new OrderByPageNumberComparator());

        books1.add(b1);
        books1.add(b2);
        books1.add(b3);
        books1.add(b4);
        books1.add(b5);

        System.out.println("İsme göre : ");

        System.out.println();

        for (Book book : books ) {
            System.out.println(book.getName());
        }

        System.out.println();

        System.out.println("Sayfa numarasına göre :");

        System.out.println();

        for (Book book : books1 ) {
            System.out.println(book.getName());
        }







    }
}