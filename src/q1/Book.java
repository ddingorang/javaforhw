package q1;

public class Book {

    private String isbn;
    private String title;
    private Integer price;
    private String author;
    private String description;
    private String bookType;
    private String publishedDate;


    public Book(String isbn, String title, Integer price, String author,
                String description, String bookType, String publishedDate) {
        this.isbn = isbn;
        this.title = title;
        this.price = price;
        this.author = author;
        this.description = description;
        this.bookType = bookType;
        this.publishedDate = publishedDate;
    }

    public String getIsbn() {
        return isbn;
    }

    public String getTitle() {
        return title;
    }

    public Integer getPrice() {
        return price;
    }

    public String getAuthor() {
        return author;
    }

    public String getDescription() {
        return description;
    }

    public String getBookType() {
        return bookType;
    }

    public String getPublishedDate() {
        return publishedDate;
    }

}
