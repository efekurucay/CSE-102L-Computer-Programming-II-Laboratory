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
    Rentable rentOut(Gallery gallery):

    void returnVehice (Gallery gallery);

}

interface Diesel extends Combustion, Rentable{

void cleanDieselFilter();
//Method to clean the diesel filter
}
s
abstract void Vehicle(){

}

interface Aircraft{


}


interface Ship{



}