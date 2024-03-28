/**---------------------------------------------------
 * Akdeniz University CSE102L Examples
 * author: Yahya Efe Kuruçay
 * since: 28.03.2024
 * Description: Abstract classes and interfaces
 * Score: ?
 * Website: https://efekurucay.com
*---------------------------------------------------*/
import java.util.ArrayList;
/***
 *    ███████╗███████╗███████╗
 *    ██╔════╝██╔════╝██╔════╝
 *    █████╗  █████╗  █████╗  
 *    ██╔══╝  ██╔══╝  ██╔══╝  
 *    ███████╗██║     ███████╗
 *    ╚══════╝╚═╝     ╚══════╝
 *                            
 */
 public class Lab06_20220808005 {

 
    public static void main(String[] args) {
        
    }
 }

interface Electric{

    void chargeBattery();
    //Method to charge the vehicle's battery

}

interface Combustion{
    void refuel();
    //Method to refuel the vehicle

}

interface Rentable{
    Rentable rentOut(Gallery gallery)

    void returnVehice (Gallery gallery);

}

interface Diesel extends Combustion, Rentable{

void cleanDieselFilter();
//Method to clean the diesel filter

}

abstract class Vehicle{


    private String model; //The model of the vehicle
    private int year; //The year of the vehicle
    

    Vehicle(String model, int year){
        //Constructor to initialize the vehicle
        this.model=model;
        this.year=year;

    }

    public String getModel() {
        return model;
    }

    public int getYear() {
        return year;
    }

    public abstract void startEngine();

    @Override
    public String toString() {
        
        return "this.getClass().";
    }



}

abstract class Aircraft extends Vehicle{

    Aircraft(String model, int year){

        super(model, year);
    }

   public abstract void fly();

}

abstract class Ship extends Vehicle{

    Ship(String model, int year) {
        super(model, year);
        //Constructor to initialize the Ship
    }

   public abstract void sail();



}

abstract class Car extends Vehicle implements Comparable<Car>{

    Car(String model, int year, int horsepower){

        super(model, year);
        this.horsepower=horsepower;

    }

    public void drive(){
        System.out.println(this.toString()+" with horsepower"+horsepower);
    }

    private int horsepower;
    public int getHorsepower() {
        return horsepower;
    }

    @Override
    public void startEngine() {
        System.out.println("Started the engine o");
    }

@Override
public int compareTo(Car o) {
    return this.getHorsepower()-o.getHorsepower();
}

}

class Tesla extends Car implements Electric, Rentable{
//Multiple Inheritance
    Tesla(String model, int year, int horsepower) {
        super(model, year, horsepower);
        //Constructor to initialize the Tesla
    }

    private int currentCapacity;
    
    @Override
    public void chargeBattery() {
        this.currentCapacity=currentCapacity;
    }
    @Override
    public void drive() {
       
        
    }


class Ford extends Car implements Combustion{

    Ford(String model, int year, int horsepower) {
        super(model, year, horsepower);
        //Constructor to initialize the Ford
    }


class Mercedes extends Car implements Electric, Diesel{

    Mercedes(String model, int year, int horsepower) {
        super(model, year, horsepower);
       
    }

    @Override
    public void refuel() {
   
        throw new UnsupportedOperationException("Unimplemented method 'refuel'");
    }

    @Override
    public void cleanDieselFilter() {
      
        throw new UnsupportedOperationException("Unimplemented method 'cleanDieselFilter'");
    }

    @Override
    public void chargeBattery() {
       
        throw new UnsupportedOperationException("Unimplemented method 'chargeBattery'");
    }


    
}


@Override
public void refuel() {
    
    throw new UnsupportedOperationException("Unimplemented method 'refuel'");
}



    
}


@Override
public Rentable rentOut(Gallery gallery) {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException("Unimplemented method 'rentOut'");
}
@Override
public void returnVehice(Gallery gallery) {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException("Unimplemented method 'returnVehice'");
}





    
}

class Mercedes extends Car implements Electric, Diesel{

    Mercedes(String model, int year, int horsepower) {
        super(model, year, horsepower);
       
    }

    @Override
    public void refuel() {
      
        throw new UnsupportedOperationException("Unimplemented method 'refuel'");
    }

    @Override
    public Rentable rentOut(Gallery gallery) {
       
        throw new UnsupportedOperationException("Unimplemented method 'rentOut'");
    }

    @Override
    public void returnVehice(Gallery gallery) {
       
        throw new UnsupportedOperationException("Unimplemented method 'returnVehice'");
    }

    @Override
    public void cleanDieselFilter() {
        
        throw new UnsupportedOperationException("Unimplemented method 'cleanDieselFilter'");
    }

    @Override
    public void chargeBattery() {
       
        throw new UnsupportedOperationException("Unimplemented method 'chargeBattery'");
    }
    
}

class Gallery{


ArrayList<String> Combustion = new ArrayList<String>();


ArrayList<String> Electric = new ArrayList<String>();

Gallery(){

    
}

void addCar(Car car){

}
void addCombustionCar(Combustion car){


}

void addElectricCar(Electric car){

    
}

void displayRentableCars(){


}









}