
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

    }
}

interface Sellable {
    String getName();    
    double getPrice();  
}

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
           
            return price / distanceToAddress;
        }
        return 0;
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

class CargoCompany {
    private CargoFleet queue;
    private Container stack;

    CargoCompany() {
        this.queue = new CargoFleet();
        this.stack = new Container();
    }

    public <T extends Box<?>> void add(T box){
        if(stack.size()< Container.DEFAULT_CAPACITY){
            stack.push(box);
        }
        else if(queue.size()<CargoFleet.FLEET_CAPACITY){
            queue.enqueue(stack);
            stack = new Container();
            stack.push(box);
        }
        else{
            ship(queue);
            stack.push(box);
        }
    }

    private void ship(CargoFleet fleet){
        while (!fleet.isEmpty()) {
            empty(fleet.dequeue());
        } 
    }

    private void empty(Container container){
        while (!container.isEmpty()) {
            deliver(container.pop());
        }
    }

    private <T extends Box<?>> Sellable deliver(T box){
        return box.extract();
    }
    
}

interface Common<T> {
  
    boolean isEmpty();

    T peek();

    int size();
}

interface Wrappable extends Sellable {

}

class Matroschka<T extends Wrappable> extends Product implements Wrappable, Package<T> {
    private T item;

    public Matroschka(T item) {
        super("Matroschka", 5.0); 
        this.item = item;
        if (item != null) {
            double totalPrice = getPrice() + item.getPrice();
           
        }
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
        super("Mirror", 2.0); 
        this.width = width;
        this.height = height;
    }

    public int getArea() {
        return width * height;
    }

    @Override
    public double getPrice() {
     
        return 0.5 * getArea(); 
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
        super(0.5); 
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }
}

interface PriorityQueue<T> extends Common<T> {
    int FLEET_CAPACITY = 10; 

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

    public Product(double d) {
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

class CargoFleet implements PriorityQueue<Container> {
    private Container head;
    private int size;
    
    CargoFleet() {
        this.head = null;
        this.size = 0;
    }

    @Override
    public boolean enqueue(Container item) {
        if(size< PriorityQueue.FLEET_CAPACITY){
            if(head== null){
                head = item;
            }
            else{
                Container instant = head;
                while(instant.getNext() != null && instant.getNext().getPriority()<item.getPriority()){
                    instant = instant.getNext();
                }
                item.setNext(instant.getNext());
                instant.setNext(item);
            }
            size++;
            return true;
        }
        return false;
    }

    @Override
    public Container dequeue() {
        if(!isEmpty()){
            Container remove = head;
            head = head.getNext();
            size--;
            return remove;
        }
        return null;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public Container peek() {
        return head;
    }

    @Override
    public int size() {
        return size;
    }
    
}

class Container implements Stack<Box<?>>, Node<Container>, Comparable<Container>{
    private Box<?>[] boxes;
    private Container next;
    private double priority ;
    private int size;
    private int top;

    
    Container(){
        boxes = new Box[DEFAULT_CAPACITY];
    }

    @Override
    public String toString() {
        return "Priority of container:" + priority;
    }
    
    @Override
    public int compareTo(Container o) {
        return Double.compare(this.priority, o.priority);
    }
    
    @Override
    public Container getNext() {
        return next;
    }

    @Override
    public double getPriority() {
        return priority;
    }

    @Override
    public boolean isEmpty() {
        return size == 0 ;
    }

    @Override
    public Box<?> peek() {
        if (isEmpty()) {
            return null;
        } else {
            return boxes[top];
        }
    }

    @Override
    public Box<?> pop() {
        if (isEmpty()) {
            return null;
        } else {
            Box<?> removed = boxes[top];
            boxes[top] = null;
            top--;
            size--;
            return removed;
        }
    }

    @Override
    public boolean push(Box<?> item) {
        if (size < boxes.length) {
            top++;
            boxes[top] = item;
            size++;
            return true;
        } else {
            return false; 
        }
    }
    
     @Override
    public void setNext(Container item) {
        this.next = item;
    }

    @Override
    public int size() {
        return size;
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