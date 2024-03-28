/**---------------------------------------------------

 * Akdeniz University CSE102L Examples

 * author: Yahya Efe Kuruçay

 * since: 28.03.2024

 * Description: Abstract classes and interfaces

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
    //Rentable rentOut(Gallery gallery)

    //void returnVehice (Gallery gallery);

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
        // TODO Auto-generated method stub
        return "this.getClass().";
    }



}



abstract class Aircraft extends Vehicle{

    Aircraft(String model, int year){

        super(model, year);

        model=getModel();
        year=getYear();


    }

    void fly(){
        //Abstract method to define flying behavior

    }

}

abstract class Ship extends Vehicle{

    Ship(String model, int year) {
        super(model, year);
        //Constructor to initialize the Ship
    }

    void sail(){

        //Abstract method to define sailing behavior
    }



}