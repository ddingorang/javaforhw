package q1;

public class ShoppingList {

    private String isbn;
    private Integer quantity;

    public ShoppingList(String isbn, Integer quantity) {
        this.isbn = isbn;
        this.quantity = quantity;
    }

    public String getIsbn() {
        return isbn;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

}
