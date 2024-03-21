/**
 * lab05_20220808005
 */

import java.util.Random;

/**---------------------------------------------------

 * Akdeniz University CSE102L Assignments

 * @author: Yahya Efe Kuruçay

 * @since: 21.03.2024

 * Description: Lab05 Exercises

 * Score: ?

 * Website: https://efekurucay.com

*---------------------------------------------------*/
public class lab05_20220808005 {

    
}

abstract class Computer {

protected CPU cpu;
protected RAM ram;
Computer(CPU cpu, RAM ram){}

public void run(){


}

@Override
public String toString() {
    
    return "Computer: " + cpu +", "+ram;
}



}




class CPU{


    private String name;
    private double clock;

    CPU (String name , double clock){

this.name=name;
this.clock=clock;

    }

public String getName() {
    return name;
}
public double getClock() {
    return clock;
}
public int compute (int a, int b){
try {
    Thread.sleep((int)(10/(clock*1000)));

} catch (InterruptedException e) {
    e.printStackTrace();
}

return a+b;
}

@Override
public String toString() {
    
    return "CPU: "+ name+" "+ clock+" Ghz";
}






}
class RAM{

private String type;
private int capacity;
private int[] memory ;


RAM(String type, int capacity){

initMemory();
}

private void initMemory(int capacity) {
    this.memory = new int[capacity];
    Random random = new Random();
    for (int i = 0; i < capacity; i++) {
      for (int j = 0; j < capacity; j++) {
        this.memory[i][j] = random.nextInt(11); // 0 to 10 (inclusive)
      }
    }
  }
  
public String getType(String type){

this.type=type;

return type;
}
public int getCapacity(int capacity){

    this.capacity=capacity;
    return capacity;
}
public int[] getMemory(int[] memory){

    this.memory=memory;
    return memory;
}






}