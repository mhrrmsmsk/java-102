import java.util.*;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {
        // create books
        Book book1 = new Book("Sefiller",150,"Victor Hugo","10,05,2013");
        Book book2 = new Book("Suç ve Ceza",625,"Dostoyevski","12,07,2003");
        Book book3 = new Book("Ev Sahibesi",87,"Dostoyevski","21,12,2007");
        Book book4 = new Book("Öteki",90,"Dostoyevski","11,10,2013");
        Book book5 = new Book("Zamir",200,"Hakan Günday","17,03,2004");
        Book book6 = new Book("Anne Karenina",820,"Tolstoy","20,02,2012");
        Book book7 = new Book("Vahşetin Çağrısı",180,"Jack London","09,12,2003");
        Book book8 = new Book("Kürk Mantolu Madonna",99,"Sabahattin Ali","28,06,2000");
        Book book9 = new Book("Körlük",400,"Jose Saramango","26,08,1999");
        Book book10 = new Book("Satranç",90,"Stefan Zweig","12,11,1998");
        // adding to a ArrayList
        ArrayList<Book> books = new ArrayList<>();
        Book[] bookList = new Book[]{book1,book2,book3,book4,book5,book6,book7,book8,book9,book10};
        for(Book i: bookList){
            books.add(i);
        }
        // creating a map with using stream API
        Map<String, String> bookAuthorMap = books.stream()
                .collect(Collectors.toMap(Book::getBookName, Book::getAuthorName));
        // creating a sublist that number of pages over 100
        List<Book> booksOver100Pages = books.stream()
                .filter(book -> book.getPageNumber() > 100)
                .toList();
        // print with using stream API
        bookAuthorMap.forEach((bookName, authorName) ->
                System.out.println("Book: " + bookName + " -> Author: " + authorName));
        System.out.println();
        System.out.println("Books with over 100 pages  :");
        System.out.println();
        booksOver100Pages.stream().forEach(i -> System.out.println(i.getBookName()+" -> "+i.getPageNumber()));

    }
}