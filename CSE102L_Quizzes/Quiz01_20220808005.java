import java.util.*;
/**---------------------------------------------------
 * Akdeniz University CSE102L Midterm (Quiz01)
 * author: Yahya Efe Kuruçay
 * since: 17.04.2024
 * Description: Quiz01
 * Score: ?
 * Website: https://efekurucay.com
*---------------------------------------------------*/
/***
 *     _______  _______  _______ 
 *    |   ____||   ____||   ____|
 *    |  |__   |  |__   |  |__   
 *    |   __|  |   __|  |   __|  
 *    |  |____ |  |     |  |____ 
 *    |_______||__|     |_______|
 *                               
 */
import java.util.*;
 
public class Quiz01_20220808005 {
    public static void main(String[] args) {

        
      }
      
}
 
// Interfaces
interface Item extends Comparable<Item> {
    double getPrice();
    String getName();
}
 
interface PaymentMethod {
    boolean pay(List<Item> cart);
}
 
interface Colorable {
    void paint(String color);
}
 


// Classes
abstract class Product implements Item {
    protected String name;
    protected double price;
 
    public Product(String name, double price) throws InvalidPriceException {
        if (price < 0)
            throw new InvalidPriceException(price);
        this.name = name;
        this.price = price;
    }
 
    public void setName(String name) {
        this.name = name;
    }
 
    public void setPrice(double price) throws InvalidPriceException {
        if (price < 0)
            throw new InvalidPriceException(price);
        this.price = price;
    }
 
    public String getName() {
        return name;
    }
 
    public double getPrice() {
        return price;
    }
}
 
class Tax implements Item {
    private double taxRate;
    private Item item;
 
    public Tax(double taxRate, Item item) {
        this.taxRate = taxRate / 100;
        this.item = item;
    }
 
    public double getPrice() {
        return item.getPrice() + item.getPrice() * taxRate;
    }
 
    public String getName() {
        return item.getName();
    }

    @Override
    public int compareTo(Item o) {
        throw new UnsupportedOperationException("Unimplemented method 'compareTo'");
    }
}
 
class Discount implements Item {
    private double percent;
    private Item item;
 
    public Discount(double percent, Item item) {
        this.percent = percent / 100;
        this.item = item;
    }
 
    public double getPrice() {
        return item.getPrice() - item.getPrice() * percent;
    }
 
    public String getName() {
        return item.getName();
    }

    @Override
    public int compareTo(Item o) {
     
        throw new UnsupportedOperationException("Unimplemented method 'compareTo'");
    }
}
 
class CreditCard implements PaymentMethod {
    private long cardNumber;
    private String holderName;
    private Date expirationDate;
    private int cvv;
 
    public CreditCard(long cardNumber, String holderName, Date expirationDate, int cvv) throws CreditCardException {
        if (String.valueOf(cardNumber).length() != 16)
            throw new CreditCardException(cardNumber);
        if (String.valueOf(cvv).length() != 3)
            throw new CreditCardException(cvv);
        this.cardNumber = cardNumber;
        this.holderName = holderName;
        this.expirationDate = expirationDate;
        this.cvv = cvv;
    }
 
    public boolean pay(List<Item> cart) {
        double total = 0;
        for (Item item : cart) {
            total += item.getPrice();
        }
        cart.clear();
        System.out.println("Paid: " + total + " with CreditCard " + holderName);
        return true;
    }
}
 
class PayPal implements PaymentMethod {
    private String username;
    private String password;
 
    public PayPal(String username, String password) {
        this.username = username;
        this.password = password;
    }
 
    public boolean pay(List<Item> cart) {
        double total = 0;
        for (Item item : cart) {
            total += item.getPrice();
        }
        total -= total * 0.06; // 6% discount
        cart.clear();
        System.out.println("Paid: " + total + " with PayPal " + username);
        return true;
    }
}

class Customer {
    private String name;
    private PaymentMethod paymentMethod;
    private List<Item> cart;
    private ArrayList<Object> items;
    public Customer(String name) {
        this.name = name;
        this.paymentMethod = null;
        this.cart = new ArrayList<>();
        this.items = new ArrayList<>();
    }
    public void addItem(Object item) {
       
        this.items.add(item);
    }
    public String getName() {
        return name;
    }
 
    public void setPaymentMethod(PaymentMethod paymentMethod) {
        this.paymentMethod = paymentMethod;
    }
 
    public boolean addItem(Item item) {
        if (item instanceof Colorable) {
            item = new Discount(0.08, item);
        }
        if (item.getPrice() > 500) {
            item = new Tax(20, item);
        }
        item = new Tax(12, item);
        return cart.add(item);
    }
 
    public boolean removeItem(Item item) {
        return cart.remove(item);
    }
 
    public boolean purchase() {
        if (paymentMethod == null || cart.isEmpty())
            return false;
        return paymentMethod.pay(cart);
    }
 
    public void display() {
        cart.sort(Comparator.comparingDouble(Item::getPrice));
        for (Item item : cart) {
            System.out.println(item.getPrice() + " - " + item.getName());
        }
    }
}
 
 // Exceptions
class CreditCardException extends Exception {
    private long cardNumber;
    private int cvv;
 
    public CreditCardException(int cvv) {
        super("ERROR: Invalid cvv " + cvv);
        this.cvv = cvv;
    }
 
    public CreditCardException(long cardNumber) {
        super("ERROR: Invalid card number " + cardNumber);
        this.cardNumber = cardNumber;
    }
 
    public long getCardNumber() {
        return cardNumber;
    }
 
    public int getCvv() {
        return cvv;
    }
}
 
class InvalidPriceException extends RuntimeException {
    private double price;
 
    public InvalidPriceException(double price) {
        super("ERROR: Invalid price: " + price);
        this.price = price;
    }
 
    public double getPrice() {
        return price;
    }
}



