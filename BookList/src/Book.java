public class Book {
    private String bookName;
    private int pageNumber;
    private String authorName;
    private String date;

    public Book(String bookName, int pageNumber, String authorName, String date) {
        this.bookName = bookName;
        this.pageNumber = pageNumber;
        this.authorName = authorName;
        this.date = date;
    }

    public String getBookName() {
        return bookName;
    }

    public void setBookName(String booName) {
        this.bookName = booName;
    }

    public int getPageNumber() {
        return pageNumber;
    }

    public void setPageNumber(int pageNumber) {
        this.pageNumber = pageNumber;
    }

    public String getAuthorName() {
        return authorName;
    }

    public void setAuthorName(String authorName) {
        this.authorName = authorName;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
