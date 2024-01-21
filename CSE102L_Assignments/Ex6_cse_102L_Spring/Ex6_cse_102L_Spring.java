import java.util.ArrayList;

public class Ex6_cse_102L_Spring {
    public static void main(String[] args) {


    }
}
abstract class Product implements Comparable<Product>{
    private String name;
    private double price;

    Product(String name, double price){
        this.name = name;
        this.price = price;
    }
    public double getPrice() {
        return price;
    }
    public String getName() {
        return name;
    }
    public int compareTo(Product other) {
        if (this.price < other.price)
            return -1;
         else if (this.price > other.price)
            return 1;
         return 0;
    }

    public String toString(){
        return getClass().getSimpleName() + " [name=" + name + ", price=" + price + "]";
    }
}
abstract class Book extends Product{
    private String author;
    private int pageCount;

    Book(String name, double price, String author, int pageCount){
        super(name, price);
        this.author = author;
        this.pageCount = pageCount;
    }

    public int getPageCount() {
        return pageCount;
    }

    public String getAuthor() {
        return author;
    }
}
class ReadingBook extends Book{
    private String genre;

    ReadingBook(String name, double price, String author, int pageCount, String genre){
        super(name, price, author, pageCount);
        this.genre = genre;
    }

    public String getGenre() {
        return genre;
    }

}
class ColoringBook extends Book implements Colorable{
    private String color;

    ColoringBook(String name, double price, String author, int pageCount){
        super(name, price, author, pageCount);

    }

    public String getColor() {
        return color;
    }

    @Override
    public void paint(String color) {
    }
}
class ToyHorse extends Product implements Rideable{

    ToyHorse(String name, double price) {
        super(name, price);
    }

    @Override
    public void ride() {
    }
}
class Bicycle extends Product implements Colorable, Rideable{
    private String color;

    Bicycle(String name, double price) {
        super(name, price);
    }

    public String getColor() {
        return color;
    }

    @Override
    public void paint(String color) {

    }

    @Override
    public void ride() {

    }
}
class User{
    private String username;
    private String email;
    private PaymentMethod payment;
    private ArrayList<Product> cart;

    User(String username, String email){
        this.username = username;
        this.email = email;
        cart = new ArrayList<>();
    }

    public String getEmail() {
        return email;
    }
    public String getUsername() {
        return username;
    }
    public void setPayment(PaymentMethod payment) {
        this.payment = payment;
    }
    public Product getProduct(int index) {
        return cart.get(index);
    }

    public void removeProduct(int index) {
        cart.remove(index);
    }
    public void addProduct(Product product){
        cart.add(product);
    }
    public void purchase(){
        double receipt = 0;
        for (Product product: cart) {
            receipt += product.getPrice();
        }
        if(payment.pay(receipt))
            cart.clear();

    }
}
class CreditCard implements PaymentMethod{
    private long cardNumber;
    private String cardHolderName;
    private java.util.Date expirationDate;
    private int cvv;

    CreditCard(long cardNumber, String cardHolderName, java.util.Date expirationDate, int cvv) {
        this.cardNumber = cardNumber;
        this.cardHolderName = cardHolderName;
        this.expirationDate = expirationDate;
        this.cvv = cvv;
    }

    @Override
    public boolean pay(double amount) {
        return true;
    }
}
class PayPal implements PaymentMethod, Password {
    private String username;
    private String password;

    PayPal(String username, String password) {
        this.username = username;
        this.password = encrypt(password);
    }


    @Override
    public boolean pay(double amount) {
        return true;
    }

    @Override
    public String encrypt(String password) {
        int code;
        String result = "";
        for (int i = 0; i < password.length(); i++) {
            code = Math.round((float) Math.random() * 8 + 1);
            result += code + Integer.toHexString(((int) password.charAt(i)) ^ code) + "-";
        }
        return result.substring(0, result.lastIndexOf("-"));
    }
    public String decrypt(String password){
        password = password.replace("-", "");
        String result = "";
        for (int i = 0; i < password.length(); i+=3) {
            String hex =  password.substring(i+1, i+3);
            result += (char) (Integer.parseInt(hex, 16) ^ (Integer.parseInt(String.valueOf(password.charAt(i)))));
        }
        return result;
    }
}
interface Password{
    String encrypt(String password);
}

interface Colorable{
    void paint(String color);
}
interface Rideable{
    void ride();
}
interface PaymentMethod{
    boolean pay(double amount);
}

