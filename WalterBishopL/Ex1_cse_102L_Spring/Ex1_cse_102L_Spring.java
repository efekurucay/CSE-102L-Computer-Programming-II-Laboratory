/**
 * @since 03.03.2023
 */

import java.util.Scanner;

public class Ex1_cse_102L_Spring {
    public static void main(String[] args) {
        Stock a = new Stock("ORCL","Oracle Corporation");
        a.currentPrice = 34.35;
        a.previousClosingPrice = 34.50;
        System.out.println(a.getChangePercent(a.previousClosingPrice,a.currentPrice));
        Fan f = new Fan(5,"blue");
        System.out.println(f.toString());
        f.test();
    }
}
//Exercise 1
class Stock {
    String symbol;
    String name;
    double previousClosingPrice;
    double currentPrice;

    Stock(String symbol, String name) {
        this.symbol = symbol;
        this.name = name;
    }

    double getChangePercent(double previousClosingPrice, double currentPrice) {
        return Math.abs((previousClosingPrice - currentPrice) / previousClosingPrice) * 100;
    }
    Stock(){

    }

}
//Exercise 2
class Fan{
    private int SLOW = 1;
    private int MEDIUM = 2;
    private int FAST = 3;
    private int speed = SLOW;
    private boolean on = false;
    private double radius = 5;
    String color = "blue";
    public int getSpeed(){
        return this.speed;
    }
    public void setSpeed(int speed){
        this.speed = speed;
    }
    public boolean getOn(){
        return this.on;
    }
    public void setOn(boolean on){
        this.on = on;
    }
    public double getRadius(){
        return this.radius;
    }
    public void setRadius(double radius){
        this.radius = radius;
    }
    public String getColor(){
        return this.color;
    }
    public void setColor(String color) {
        this.color = color;
    }
    Fan(){
    }
    Fan(double radius,String color){
        this.radius = radius;
        this.color = color;
    }
    public String toString(){
        String s = Integer.toString(speed);
        String r = Double.toString(radius);
        if (!getOn()){
            return s + " " + color + " " + r;
        }
        return color + " " + r + " fan is off";
    }
    public void test(){
        //burada input olarak user dan bir sayı alacak
        System.out.println("lütfen bir sayı giriniz");
        Scanner scan = new Scanner(System.in);
        int amount = scan.nextInt();
        Fan array[] = new Fan[amount];
        for(int i = 0; i < amount; i++){
            if (i % 2 == 1){
                array[i] = new Fan(6,"yellow");
            }else
                array[i] =new Fan();

        }
        fans(array);
        for(int i = 0; i < amount; i++) {
            System.out.println(array[i].toString());
        }
    }
    public void fans(Fan[] array){
        for(int i = 0; i < array.length;i++){
            if(i % 3 == 2 && !(array[i].getOn())){
                int a = ((array[i].getSpeed()) % 3) + 1;
                array[i].setSpeed(a);
            }
        }
    }
}




