public class Ex3_cse_102L_Spring {
    public static void main(String[] args) {
    }
}
class Author{
    private String name;
    private String surname;
    private String mail;

    Author(String name, String surname, String mail){
        this.name = name;
        this.surname = surname;
        this.mail = mail;
    }

    public String getName(){
        return name;
    }

    public String getSurname(){
        return surname;
    }

    public String getMail(){
        return mail;
    }

    public void setMail(String mail){
        this.mail = mail;
    }
}

class Book {
    private String isbn;
    private String title;
    private Author author;
    private double price;

    Book(String isbn, String title, Author author, double price) {
        this.author = author;
        this.isbn = isbn;
        this.title = title;
        this.price = price;
    }

    Book(String isbn, String title, Author author) {
        this.author = author;
        this.isbn = isbn;
        this.title = title;
        setPrice(15.25);
    }

    public String getIsbn() {
        return isbn;
    }

    public String getTitle() {
        return title;
    }

    public Author getAuthor() {
        return author;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}
class EBook extends Book{
    private String downloadUrl;
    private double sizeMb;

    EBook(String isbn, String title, Author author,
          double price, String downloadUrl, double sizeMb){
        super(isbn, title, author, price);
        this.downloadUrl = downloadUrl;
        this.sizeMb = sizeMb;
    }

    EBook(String isbn, String title, Author author
            , String downloadUrl, double sizeMb){
        super(isbn, title, author);
        this.downloadUrl = downloadUrl;
        this.sizeMb = sizeMb;
    }

    public String getDownloadUrl() {
        return downloadUrl;
    }

    public double getSizeMb() {
        return sizeMb;
    }
}
class PaperBook extends Book{
    private int shippingWeight;
    private boolean inStock;

    PaperBook(String isbn, String title, Author author,
              double price, int shippingWeight, boolean inStock){
        super(isbn, title, author, price);
        this.inStock = inStock;
        this.shippingWeight = shippingWeight;
    }

    PaperBook(String isbn, String title, Author author, boolean inStock){
        super(isbn, title, author);
        this.inStock = inStock;

        this.shippingWeight = (int) (Math.random() * 10) + 5;
    }

    public int getShippingWeight() {
        return shippingWeight;
    }
    public boolean getInStock(){
        return inStock;
    }

    public void setInStock(boolean inStock) {
        this.inStock = inStock;
    }
}