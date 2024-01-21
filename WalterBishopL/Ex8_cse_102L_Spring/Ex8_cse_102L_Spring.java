public class Ex8_20210808053 {
    public static void main(String[] args) {
        
    }
}
interface Sellable{
    String getName();
    double getPrice();
}
interface Package <T>{
    T extract();
    boolean pack(T item);
    boolean isEmpty();
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

    public String toString() {
        return super.toString() + "{" + item + "}";
    }
}
class Box <T extends Sellable> implements Package<T>{

    private T item;
    private boolean seal;

    Box(){
        this.item = null;
        this.seal = false;
    }
    Box(T item){
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

    public String toString() {
        return super.toString() + " {" + item + "} Seal: " + seal;
    }
}