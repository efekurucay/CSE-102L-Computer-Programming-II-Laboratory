public class Ex9_20210808053 {
}
interface Sellable{
    String getName();
    double getPrice();
}
interface Common <T>{
    boolean isEmpty();
    T peek();
    int size();

}
interface Stack<T> extends Common<T>{
    boolean push(T item);
    T pop();
}

interface Node<T>{
    int DEFAULT_CAPACITY = 2;
    void setNext(T item);
    T getNext();
    double getPriority();
}
interface PriorityQueue <T> extends Common<T>{
    int FLEET_CAPACITY = 3;
    boolean enqueue(T item);
    T dequeue();
}
interface Package <T>{
    T extract();
    boolean pack(T item);
    boolean isEmpty();
    double getPriority();
}
interface Wrappable extends Sellable{

}
//Classes

abstract class Product implements Sellable{
    private String name;
    private double price;

    Product(String name, double price){
        this.price = price;
        this.name = name;
    }

    public String toString() {
        return getClass().getSimpleName() + " (" + name + "," + price + ")";
    }
    public String getName() {
        return name;
    }
    public double getPrice() {
        return price;
    }
}
class Mirror extends Product{

    private int width;
    private int height;

    Mirror(int width, int height) {
        super("Mirror", 2);
        this.width = width;
        this.height = height;
    }

    public int getArea(){
        return width * height;
    }
    public <T>T reflect(T item) {
        System.out.println(item);
        return item;
    }
}
class Paper extends Product implements Wrappable{
    private String note;

    Paper(String note) {
        super("A4", 3);
        this.note = note;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }
}
class Matroschka <T extends Wrappable> extends Product implements Wrappable, Package<T>{

    private T item;
    Matroschka(T item) {
        super("Doll", 5 + item.getPrice());
        this.item = item;
    }

    public T extract() {
        T items = item;
        item = null;
        return items;

    }


    public boolean pack(T item) {
        if (isEmpty()){
            this.item = item;
            return true;
        }

        return false;
    }

    public boolean isEmpty() {
        return item == null;
    }

    @Override
    public double getPriority() {
        return 0;
    }

    public String toString() {
        return super.toString() + "{" + item + "}";
    }
}
class Box <T extends Sellable> implements Package<T>{
    private int distanceToAddress;
    private T item;
    private boolean seal;

    Box(){
        this.item = null;
        this.seal = false;
    }
    Box(int distanceToAddress,T item){
        this.distanceToAddress = distanceToAddress;
        this.item = item;
        this.seal = true;
    }


    public T extract() {
        seal = false;
        T items = item;
        item = null;
        return items;
    }


    public boolean pack(T item) {
        if (isEmpty()){
            this.item = item;
            return true;
        }

        return false;
    }

    public boolean isEmpty() {
        return item == null;
    }
    public int getDistanceToAddress(){
        return distanceToAddress;
    }

    @Override
    public double getPriority() {
        return distanceToAddress / item.getPrice();
    }

    public String toString() {
        return super.toString() + " {" + item + "} Seal: " + seal;
    }
}
class Container implements Stack<Box<?>>, Node<Container>, Comparable<Container>{

    private Box<?>[] boxes;
    private int top;
    private int size;
    private double priority;
    private Container next;

    Container(){
        this.boxes = new Box<?>[Node.DEFAULT_CAPACITY];
        this.top = -1;
        this.next = null;
        this.priority = 0;
    }
    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public Box<?> peek() {
        if (isEmpty())
            return null;
        return boxes[top];
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean push(Box<?> item) {
        if (top == boxes.length - 1)
            return false;
        boxes[++top] = item;
        size++;
        priority += item.getPriority();
        return true;
    }

    @Override
    public Box<?> pop() {
        if (isEmpty())
            return null;
        Box<?> temp = boxes[top];
        boxes[top--] = null;
        size--;
        priority -= temp.getPriority();
        return temp;
    }


    @Override
    public void setNext(Container item) {
        this.next = item;
    }

    @Override
    public Container getNext() {
        return next;
    }

    @Override
    public double getPriority() {
        return priority;
    }

    public String toString() {
        return "Container with priority: " + priority;
    }


    public int compareTo(Container o) {
        if (o.getPriority() > this.getPriority())
            return -1;
        else if (o.getPriority() < this.getPriority())
            return 1;
        else if (o.getPriority() == this.getPriority()) {
            if (o.boxes[0].getDistanceToAddress() + o.boxes[1].getDistanceToAddress()
                    > this.boxes[0].getDistanceToAddress() + this.boxes[1].getDistanceToAddress())
                return -1;
            else
                return 1;
        }
        return 0;
    }

}
class CargoFleet implements PriorityQueue<Container>{
    private Container head;
    private int size;

    CargoFleet(){
        this.head = null;
        this.size = 0;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    public Container peek() {
        if (isEmpty())
            return null;
        return head;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean enqueue(Container item) {
        if (size == PriorityQueue.FLEET_CAPACITY)
            return false;
        if (isEmpty())
            head = item;
        else {
            Container currentItem = head;
            while (currentItem.getNext() != null)
                currentItem = currentItem.getNext();
            currentItem.setNext(item);
        }
        size++;
        return true;
    }

    @Override
    public Container dequeue() {
        if (isEmpty())
            return null;
        Container item = head;
        head = head.getNext();
        item.setNext(null);
        size--;
        return item;

    }
}
class CargoCompany{
    private Container stack;
    private CargoFleet queue;

    CargoCompany(){
        stack = new Container();
        queue = new CargoFleet();
    }

    public void add(Box<?> box) {
        if (!stack.push(box)) {
            if (queue.enqueue(stack)) {
                stack = new Container();
                add(box);
            } else {
                ship(queue);
            }
        }
    }
    private void ship(CargoFleet fleet) {
        Container temp=null;
        while (!fleet.isEmpty()) {
            temp=fleet.dequeue();
        }
        if(temp != null)
            empty(temp);
    }

    private void empty(Container container) {
        Box<?> temp=new Box<>();
        while(!container.isEmpty()) {
            temp=container.pop();
        }

        System.out.println(deliver(temp));

    }

    private Sellable deliver(Box<?> box) {
        return box.extract();
    }
}
