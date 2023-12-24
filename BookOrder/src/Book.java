public class Book {
    private final String name ;
    private final int pageNum;
    private final String author;
    private final String date ;
    public Book(String name, int pageNum, String author, String date) {
        this.name = name;
        this.pageNum = pageNum;
        this.author = author;
        this.date = date;
    }
    public String getName() {
        return name;
    }
    public int getPageNum() {
        return pageNum;
    }
    public String getAuthor() {
        return author;
    }

   

    public String getDate() {
        return date;
    }


}
