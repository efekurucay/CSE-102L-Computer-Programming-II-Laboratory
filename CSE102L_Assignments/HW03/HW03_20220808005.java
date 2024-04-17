import java.util.Random;
/**---------------------------------------------------

 * Akdeniz University CSE102L Assignments

 * author: Yahya Efe Kuruçay

 * since: 02.04.2024

 * Description: Homework03

 * Score: 98

 * Website: https://efekurucay.com

*---------------------------------------------------*/
/*
 * Bu bir otomatik maildir!!
 -------------START----------------
20220808005 - HW03->98
Test Result
╷
├─ JUnit Jupiter ✔
├─ JUnit Vintage ✔
│  └─ HW03_20220808005Test ✔
│     ├─ AuthorClassFieldModifierControl ✔
│     ├─ BookShouldHaveCorrectConstructorWith4Parameter ✔
│     ├─ shouldHavePaperBookClass ✔
│     ├─ authorShouldNotBeAnInnerClass ✔
│     ├─ BookShouldHaveCorrectConstructorWith3Parameter ✔
│     ├─ eBookClassFieldModifierControl ✔
│     ├─ shouldCreateAuthor ✔
│     ├─ PaperBookShouldHaveCorrectConstructorWith6Parameters ✔
│     ├─ PaperBookShouldNotHaveMutatorMethods ✔
│     ├─ shouldAuthorHave1Constructor ✔
│     ├─ PaperBookShouldBeSubClass ✔
│     ├─ testingAccessorsOfAuthor ✔
│     ├─ bookShouldHave2Constructors ✔
│     ├─ ebookShouldHave2Constructors ✔
│     ├─ testingAccessorsOfPaperBook ✘ Should have accessor methods (1 failure)
│     │         java.lang.IllegalArgumentException: wrong number of arguments: 0 expected: 1
│     ├─ shouldHaveEBookClass ✔
│     ├─ PaperBookClassFieldTypeControl ✔
│     ├─ PaperBookShouldHave2Constructors ✔
│     ├─ bookShouldNotBeAnInnerClass ✔
│     ├─ ebookShouldNotBeAnInnerClass ✔
│     ├─ eBookClassFieldTypeControl ✔
│     ├─ testingAccessorsOfEBook ✔
│     ├─ eBookShouldNotHaveMutatorMethods ✔
│     ├─ PaperBookShouldHaveCorrectConstructorWith4Parameter ✔
│     ├─ testingAccessorsOfBook ✔
│     ├─ PaperBookShouldNotBeAnInnerClass ✔
│     ├─ shouldCreateBookWith4Paramters ✔
│     ├─ AuthorShouldNotHaveMutatorMethods ✔
│     ├─ shouldSetMailOfAuthorInstance ✔
│     ├─ shouldCreateBookWith3Paramters ✔
│     ├─ EBookShouldBeSubClass ✔
│     ├─ shouldAuthorHaveCorrectConstructorWith3Parameter ✔
│     ├─ PaperBookClassFieldModifierControl ✔
│     ├─ BookClassFieldTypeControl ✔
│     ├─ eBookShouldHaveCorrectConstructorWith6Parameters ✔
│     ├─ AuthorClassFieldTypeControl ✔
│     ├─ shouldHaveAuthorClass ✔
│     ├─ BookShouldNotHaveMutatorMethods ✔
│     ├─ BookClassFieldModifierControl ✔
│     ├─ shouldHaveBookClass ✔
│     ├─ BookShouldHaveCorrectConstructorWith5Parameter ✔
│     └─ shouldSetPriceOfBook ✔
└─ JUnit Platform Suite ✔


--------------END-----------------

 */
/***
 *    ███████╗███████╗███████╗
 *    ██╔════╝██╔════╝██╔════╝
 *    █████╗  █████╗  █████╗  
 *    ██╔══╝  ██╔══╝  ██╔══╝  
 *    ███████╗██║     ███████╗
 *    ╚══════╝╚═╝     ╚══════╝
 *                            
 */
public class HW03_20220808005 {
    public static void main(String[] args) {

        Author author = new Author("Yahya Efe", "Kuruçay", "contact@efekurucay.com");

        PaperBook pb1 = new PaperBook("1234567890", "aa", author, 10.99, 10, true);
        System.out.println("paperbook 1: ");
        System.out.println("isbn: " +  pb1.getIsbn());
        System.out.println("title: " + pb1.getTitle());
        System.out.println("author: " + pb1.getAuthor().getName() + " " + pb1.getAuthor().getSurname() + " \nmail: " + pb1.getAuthor().getMail());
        System.out.println("price: $" + pb1.getPrice());
        System.out.println("shipping weight: " + pb1.getShippingWeight() + " lbs");
        System.out.println("in stock: " + pb1.getInStock());

        PaperBook pb2 = new PaperBook("9876543210", "bb", author, false);
        System.out.println("\npaperbook 2(random shipping weight): ");
        System.out.println("isbn: " + pb2.getIsbn());
        System.out.println("title: " + pb2.getTitle());
        System.out.println("author: " + pb2.getAuthor().getName() + " " + pb2.getAuthor().getSurname() + " \nmail: " + pb2.getAuthor().getMail());
        System.out.println("price: $" + pb2.getPrice());
        System.out.println("shipping weight: " + pb2.getShippingWeight() + " lbs");
        System.out.println("in stock: " + pb2.getInStock());

        EBook eb1 = new EBook("3216549870", "cc", author, 24.99, "http://efekurucay.com", 4.5);
        System.out.println("\nebook: ");
        System.out.println("isbn: " + eb1.getIsbn());
        System.out.println("title: " + eb1.getTitle());
        System.out.println("author: " + eb1.getAuthor().getName() + " " + eb1.getAuthor().getSurname() + " \nmail: " + eb1.getAuthor().getMail());
        System.out.println("price: $" + eb1.getPrice());
        System.out.println("downlload url: " + eb1.getDownloadUrl());
        System.out.println("size: " + eb1.getSizeMb() + " mb");
    }
}

class Author {
    //attributes
    private String name;
    private String surname;
    private String mail;

    //methods
    //constructore that takes name, surname, and mail
    Author (String name, String surname, String mail) {
        this.name = name;
        this.surname = surname;
        this.mail = mail;
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }
}

class Book {
    //attributes
    private String isbn;
    private String title;
    private Author author;
    private double price;

    //methods
    //constructor that takes isbn, title, and author 
    //set price to 15.25
    Book(String isbn, String title, Author author, double price) {
        this.isbn = isbn;
        this.title = title;
        this.author = author;
        this.price = price;
    }

    Book(String isbn, String title, Author author) {
        this(isbn, title, author, 15.25);
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

class EBook extends Book {
    //attributes
    private String downloadUrl;
    private double sizeMb;

    //methods
    //constructor that takes isbn, title, author, price, downloadUrl, and sizeMb
    EBook(String isbn, String title, Author author, double price, String downloadUrl, double sizeMb) {
        super(isbn, title, author, price);
        this.downloadUrl = downloadUrl;
        this.sizeMb = sizeMb;
    }

    //constructor that takes isbn, title, author, downloadUrl, and sizeMb
    EBook(String isbn, String title, Author author, String downloadUrl, double sizeMb) {
        this(isbn, title, author, sizeMb, downloadUrl, sizeMb);
    }

    public String getDownloadUrl() {
        return downloadUrl;
    }

    public double getSizeMb() {
        return sizeMb;
    }
}

class PaperBook extends Book {
    //attributes
    private int shippingWeight;
    private boolean inStock;

    //methods
    //constructor that takes isbn, title, author, price, shippingweight, and inStock
    PaperBook(String isbn, String title, Author author, double price, int shippingWeight, boolean inStock) {
        super(isbn, title, author, price);
        this.shippingWeight = shippingWeight;
        this.inStock = inStock;
    }

    //constructor that takes isbn, title, author, and instock
    //set shippingweight to a random number between 5-15
    PaperBook(String isbn, String title, Author author, boolean inStock) {
        this(isbn, title, author, 15.25, new Random().nextInt(11) + 5, inStock);
    }

    public int getShippingWeight() {
        return shippingWeight;
    }

    public boolean getInStock() {
        return inStock;
    }

    public void setInStock(boolean inStock) {
        this.inStock = inStock;
    }
}
