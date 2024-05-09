
/**---------------------------------------------------
 * Akdeniz University CSE102L Assignments
 * author: Yahya Efe Kuruçay
 * since: 08.05.2024
 * Description: Homework06
 * Score: ?
 * Website: https://efekurucay.com
*---------------------------------------------------*/
/***
 *    ███████╗███████╗███████╗
 *    ██╔════╝██╔════╝██╔════╝
 *    █████╗  █████╗  █████╗  
 *    ██╔══╝  ██╔══╝  ██╔══╝  
 *    ███████╗██║     ███████╗
 *    ╚══════╝╚═╝     ╚══════╝
 *                            
 */

/**
 * HW06_20220808005
 */
public class HW06_20220808005 {

    public static void main(String[] args) {
        
    }
}



//INTERFACES ------


// Sellable interface
interface Sellable {
    String getName();
    double getPrice();
}

// Package interface with a generic type 
interface Package<T> {
    T extract(); // removes the item T (assigns null) and returns item only if it is not empty. Returns null if it is empty. 
    boolean pack(T item); // puts an item T in if it is empty 
    boolean isEmpty(); // checks if item T is null or not
}

// Wrappable interface, extends Sellable
interface Wrappable extends Sellable {
    /*This interface is used to restrict which items 
can be put inside a Matroschka instance. */
}


// Abstract Product class implements Sellable
abstract class Product implements Sellable {
    private String name;
    private double price;

    // Constructor
    public Product(String name, double price) {
        this.name = name;
        this.price = price;
    }

    // Accessors
    @Override
    public String getName() {
        return name;
    }

    @Override
    public double getPrice() {
        return price;
    }

    @Override
    public String toString() {
        return this.getClass().getSimpleName() + " (" + name + ", " + price + ")";
    }
}

// Mirror class extends Product
class Mirror extends Product {
    private int width;
    private int height;

    // Constructor
    public Mirror(int width, int height) {
        super("mirror", 2); // Name "mirror", and starting price 2
        this.width = width;
        this.height = height;
    }

    // Method to get area
    public int getArea() {
        return width * height;
    }

    // Override getPrice to scale with area
    @Override
    public double getPrice() {
        return 2 * getArea(); // scaling with area
    }

    // reflecting an item
    public <T> T reflect(T item) {
        System.out.println("Reflecting: " + item);
        return item;
    }
}

// Paper class extending Product and implementing Wrappable
class Paper extends Product implements Wrappable {
    private String note;

    // No-arg constructor
    public Paper() {
        super("Paper", 0.5);
    }

    // Accessor and Mutator for note
    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }
}

// Matroschka class implementing Wrappable and Package
class Matroschka<T extends Wrappable> extends Product implements Wrappable, Package<T> {
    private T item;

    // Constructor that takes an item and packs it
    public Matroschka(T item) {
        super("Doll", 5 + (item != null ? item.getPrice() : 0));
        this.item = item;
    }

    @Override
    public boolean pack(T item) {
        if (this.item == null) {
            this.item = item;
            return true;
        }
        return false;
    }

    @Override
    public T extract() {
        T temp = item;
        item = null;
        return temp;
    }

    @Override
    public boolean isEmpty() {
        return item == null;
    }

    @Override
    public String toString() {
        return super.toString() + "{" + item + "]";
    }
}

// Box class implementing Package with a generic type
class Box<T extends Sellable> implements Package<T> {
    private T item;
    private boolean seal;

    // No-arg constructor
    public Box() {
        this.item = null;
        this.seal = false;
    }

    // Constructor with item
    public Box(T item) {
        this.item = item;
        this.seal = true;
    }

    @Override
    public boolean pack(T item) {
        if (this.item == null) {
            this.item = item;
            this.seal = true;
            return true;
        }
        return false;
    }

    @Override
    public T extract() {
        T temp = item;
        item = null;
        seal = false;
        return temp;
    }

    @Override
    public boolean isEmpty() {
        return item == null;
    }

    public boolean isSealBroken() {
        return !seal;
    }

    @Override
    public String toString() {
        return this.getClass().getSimpleName() + " {" + item + "} Seal: " + seal;
    }
}
/***
 *              _____                    _____                    _____          
 *             /\    \                  /\    \                  /\    \         
 *            /::\    \                /::\    \                /::\    \        
 *           /::::\    \              /::::\    \              /::::\    \       
 *          /::::::\    \            /::::::\    \            /::::::\    \      
 *         /:::/\:::\    \          /:::/\:::\    \          /:::/\:::\    \     
 *        /:::/__\:::\    \        /:::/__\:::\    \        /:::/__\:::\    \    
 *       /::::\   \:::\    \      /::::\   \:::\    \      /::::\   \:::\    \   
 *      /::::::\   \:::\    \    /::::::\   \:::\    \    /::::::\   \:::\    \  
 *     /:::/\:::\   \:::\    \  /:::/\:::\   \:::\    \  /:::/\:::\   \:::\    \ 
 *    /:::/__\:::\   \:::\____\/:::/  \:::\   \:::\____\/:::/__\:::\   \:::\____\
 *    \:::\   \:::\   \::/    /\::/    \:::\   \::/    /\:::\   \:::\   \::/    /
 *     \:::\   \:::\   \/____/  \/____/ \:::\   \/____/  \:::\   \:::\   \/____/ 
 *      \:::\   \:::\    \               \:::\    \       \:::\   \:::\    \     
 *       \:::\   \:::\____\               \:::\____\       \:::\   \:::\____\    
 *        \:::\   \::/    /                \::/    /        \:::\   \::/    /    
 *         \:::\   \/____/                  \/____/          \:::\   \/____/     
 *          \:::\    \                                        \:::\    \         
 *           \:::\____\                                        \:::\____\        
 *            \::/    /                                         \::/    /        
 *             \/____/                                           \/____/         
 *                                                                               
 */