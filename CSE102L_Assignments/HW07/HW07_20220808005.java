
/**---------------------------------------------------
 * Akdeniz University CSE102L Assignments
 * author: Yahya Efe Kuruçay
 * since: 12.05.2024
 * Description: Homework07
 * Score: ?
 * Website: https://efekurucay.com
*---------------------------------------------------*/
/***
 *    ███████ ███████ ███████   |    ███████ ███████ ███████ 
 *    ██      ██      ██        |    ██      ██      ██      
 *    █████   █████   █████     |    █████   █████   █████   
 *    ██      ██      ██        |    ██      ██      ██      
 *    ███████ ██      ███████   |    ███████ ██      ███████ 
 *                            
 *                            
 */

public class HW07_20220808005 {
    public static void main(String[] args) {
        // // Create instances of classes
        // Box<Paper> paperBox = new Box<>();
        // Mirror mirror = new Mirror(10, 20);
        // Matroschka<Paper> matroschka = new Matroschka<>(new Paper());
        // CargoCompany cargoCompany = new CargoCompany();

        // // Add items to the paper box
        // Paper paper = new Paper();
        // paper.setNote("Hello, world!");
        // paperBox.pack(paper);

        // // Add boxes to the cargo company
        // cargoCompany.add(paperBox);
        // cargoCompany.add(matroschka);
        // cargoCompany.add(new Box<>(mirror, 30));

        // // Ship the cargo fleet
        // cargoCompany.ship();
    }
}

//Sellable interface
interface Sellable {
    String getName();    // name of sellable item
    double getPrice();  // price of sellable item
}
//Box class
class Box<T extends Sellable> implements Package<T> {
    private T item;
    private boolean seal;
    private int distanceToAddress;

   
    public Box() {
        this.item = null;
        this.seal = false;
        this.distanceToAddress = 0;
    }

    public Box(T item, int distanceToAddress) {
        this.item = item;
        this.seal = true;
        this.distanceToAddress = distanceToAddress;
    }


    @Override
    public T extract() {
        if (!isEmpty() && !isSealBroken()) {
            T extractedItem = item;
            item = null;
            seal = false;
            return extractedItem;
        }
        return null;
    }

 
    @Override
    public boolean isEmpty() {
        return item == null;
    }


    @Override
    public boolean pack(T item) {
        if (isEmpty() && !isSealBroken()) {
            this.item = item;
            return true;
        }
        return false;
    }

 
    boolean isSealBroken() {
        return !seal;
    }

    @Override
    public double getPriority() {
        if (!isEmpty()) {
            double price = item.getPrice();
            // Example calculation: priority = price / distance
            return price / distanceToAddress;
        }
        return 0; // Return 0 if the box is empty
    }

    @Override
    public String toString() {
        return "Box{" +
                "item=" + item +
                ", seal=" + seal +
                ", distanceToAddress=" + distanceToAddress +
                '}';
    }
}

//CargoCompany class
class CargoCompany {
    private Stack<Container> stack;
    private PriorityQueue<CargoFleet> queue;

   
    public CargoCompany() {
        this.stack = new Stack<>();
        this.queue = new PriorityQueue<>();
    }


    public <T extends Box<?>> void add(T box) {
        if (stack.isEmpty() || stack.peek().isFull()) {
            Container container = new Container();
            container.add(box);
            stack.push(container);
        } else {
            stack.peek().add(box);
        }

        if (stack.size() == Container.MAX_CAPACITY) {
            queue.add(new CargoFleet(stack));
            stack = new Stack<>();
        }

        if (queue.size() == CargoFleet.MAX_FLEET_CAPACITY) {
            ship(queue);
        }
    }


    private void ship(PriorityQueue<CargoFleet> fleet) {
        while (!fleet.isEmpty()) {
            empty(fleet.poll().getContainer());
        }
    }

 
    private void empty(Container container) {
        while (!container.isEmpty()) {
            deliver(container.extract());
        }
    }

    // Delivers a box and extracts its contents
    private <T extends Box<?>> Sellable deliver(T box) {
        return box.extract();
    }
}

interface Common<T> {
    // Checks if the collection is empty
    boolean isEmpty();

    // Retrieves, but does not remove, the head of this collection
    T peek();

    // Returns the number of elements in this collection
    int size();
}

interface Wrappable extends Sellable {

    // No additional methods, inherits getName() and getPrice() from Sellable
}

class Matroschka<T extends Wrappable> extends Product implements Wrappable, Package<T> {
    private T item;

    public Matroschka(T item) {
        super("Matroschka", 5.0); // Base price of Matroschka is 5.0
        this.item = item;
        if (item != null) {
            double totalPrice = getPrice() + item.getPrice();
            setPrice(totalPrice);
        }
    }

    private void setPrice(double totalPrice) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'setPrice'");
    }

    @Override
    public T extract() {
        T extractedItem = item;
        item = null;
        return extractedItem;
    }

    @Override
    public boolean isEmpty() {
        return item == null;
    }

    @Override
    public boolean pack(T item) {
        if (isEmpty()) {
            this.item = item;
            return true;
        }
        return false;
    }

    @Override
    public double getPriority() {
        throw new UnsupportedOperationException("Getting priority is not implemented for Matroschka.");
    }

    @Override
    public String toString() {
        return "Matroschka containing: " + (item != null ? item.getName() : "nothing");
    }
}

class Mirror extends Product {
    private int width;
    private int height;

    public Mirror(int width, int height) {
        super("Mirror", 2.0); // Base price of Mirror is 2.0
        this.width = width;
        this.height = height;
    }

    public int getArea() {
        return width * height;
    }

    @Override
    public double getPrice() {
        // Price calculation based on area
        return 0.5 * getArea(); // $0.5 per unit area
    }

    public <T> T reflect(T item) {
        System.out.println("Reflecting: " + item);
        return item;
    }
}

interface Node<T> {
    static final int DEFAULT_CAPACITY = 10;

    void setNext(T item);

    T getNext();

    double getPriority();
}

interface Package<T> {
    T extract();
    
    boolean pack(T item);
    
    boolean isEmpty();
    
    double getPriority();
}

class Paper extends Product implements Wrappable {
    private String note;

    public Paper() {
        super(0.5); // Base price of 0.5
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }
}

interface PriorityQueue<T> extends Common<T> {
    int FLEET_CAPACITY = 10; // Default fleet capacity

    boolean enqueue(T item);

    T dequeue();
}

abstract class Product implements Sellable {
    private String name;
    private double price;

    public Product(String name, double price) {
        this.name = name;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }

    @Override
    public String toString() {
        return "Product{" +
                "name='" + name + '\'' +
                ", price=" + price +
                '}';
    }
}

interface Stack<T> extends Common<T> {
    boolean push(T item);

    T pop();
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