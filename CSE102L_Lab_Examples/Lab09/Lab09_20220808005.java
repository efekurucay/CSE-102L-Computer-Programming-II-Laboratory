

/**---------------------------------------------------
 * Akdeniz University CSE102L Lab Examples
 * author: Yahya Efe Kuruçay
 * since: 02.05.2024
 * Description: Generics
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


import java.util.*;

public class Lab09_20220808005 {

    public static void main(String[] args) {


    }
}

interface Countable{

    int getItemCount();

}

interface IInventory<T extends Countable>{
    void addItem(T item);
    void removeItem(T item);
    T getItem(int index);
    
}

interface Rentable{

void rentItem(Person renter, Date starDate, Date endDate);
boolean isAvailable();


}

class Person{


}

abstract class Product implements Comparable<Product>{

private String name;
private int price;

    Product(String name, int price){

    this.name=name;
    this.price=price;
    }


    @Override
    public int compareTo(Product o) {
        // TODO Auto-generated method stub
        return Integer.compare(price, o.price);
    }

    @Override
    public String toString() {
        // TODO Auto-generated method stub
        return name + "$" + price ;
    }
}

class Book{
private String author;
private int pages;

Book(String author, int pages){

    this.author=author;
    this.pages=pages;

}

@Override
public String toString() {
    
    return super.toString()+"pages: "+pages+", author:"   +author;
}


}

class ColoringBook implements Rentable{

    String color;
    ColoringBook(String author,int pages,String color){

        super();
        this.color=color;

    }
    @Override
    public boolean isAvailable() {
        return false;
    }


    @Override
    public void rentItem(Person renter, Date starDate, Date endDate) {
        
    }
    @Override
    public String toString() {
        return super.toString();
    }
}

abstract class Vehicle implements Comparable<Vehicle>{

    String name;

    int price;

    Vehicle(String name,int price){

        this.name=name;
        this.price=price;
    }

    @Override
    public int compareTo(Vehicle o) {
        return Integer.compare(price, o.price);
    }
    @Override
    public String toString() {
        return name+" ,"+price;
    }




}


class Car extends Vehicle implements Rentable{

    Car(String name, int price) {
        super(name, price);
    }

    @Override
    public void rentItem(Person renter, Date starDate, Date endDate) {
        throw new UnsupportedOperationException("Unimplemented method 'rentItem'");
    }

    @Override
    public boolean isAvailable() {
        throw new UnsupportedOperationException("Unimplemented method 'isAvailable'");
    }
    
}

class Item<T extends Comparable<? super T> &  Countable>implements Countable,Comparable<Item<T>>{
    
    T item;
    int quantity;


    Item(T item, int quantity){

        this.item=item;
        this.quantity=quantity;
    }

@Override
    public int compareTo(Item<T> o) {
return this.item.compareTo(o.item);    }



    @Override
    public int getItemCount() {
return item.getItemCount();    }
    


@Override
public String toString() {
    return "["+item.toString()+" ]x"+quantity;
}




}


class Inventory<T extends Item<?>& Countable> implements Comparable, IInventory<T>{
    ArrayList<T> inventory = new ArrayList<>();


    
@Override
public void addItem(T item) {
inventory.add(item);    
}


@Override
public T getItem(int index) {
    return null;
}


    @Override
    public void removeItem(T item) {
        inventory.remove(item);
    }


    @Override
    public int compareTo(Object o) {
        return 0;
    }




}

class Rented <T extends Rentable, E extends Person>{

T item;
E renter;

Date startDate;
Date endDate;

Rented(T item, E renter, Date starDate, Date endDate){

    this.endDate=endDate;
    this.startDate=starDate;
    this.item=item;
    this.renter=renter;

}

public Date getEndDate() {
    return endDate;
}


public T getItem() {
    return item;
}   


public E getRenter() {
    return renter;
}


public Date getStartDate() {
    return startDate;
}





}