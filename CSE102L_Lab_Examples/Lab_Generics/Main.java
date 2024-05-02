
import java.util.*;

public class Main {

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


}

class Person{

    
}