import java.util.Random;

/**---------------------------------------------------

 * Akdeniz University CSE102L Assignments

Yahya Efe Kuruçay

21.03.2024

 * Description: Lab05 Exercises

 * Score: ?

 * Website: https://efekurucay.com

*---------------------------------------------------*/
public class Ex4_20220808005 {

    public static void main(String[] args) {
        
    }
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
    
class Laptop extends Computer {

    private int milliAmp;
    private int battery;

    public Laptop(CPU cpu, RAM ram, int milliAmp) {
        super(cpu, ram);
        this.milliAmp = milliAmp;
        this.battery = milliAmp * 30 / 100;
    }

    public int batteryPercentage() {
        return battery * 100 / milliAmp;
    }

    public void charge() {
        while (battery < milliAmp * 90 / 100) {
            battery += milliAmp * 2 / 100;
        }
    }

    @Override
    public void run() {
        if (battery > 5) {
            super.run();
            battery -= milliAmp * 3 / 100;
        } else {
            charge();
        }
    }

    @Override
    public String toString() {
        return super.toString() + " Battery: " + battery + "%";
    }
}

class Desktop extends Computer {

    private ArrayList<String> peripherals;

    public Desktop(CPU cpu, RAM ram, String... peripherals) {
        super(cpu, ram);
        this.peripherals = new ArrayList<>(Arrays.asList(peripherals));
    }

    @Override
    public void run() {
        int sum = cpu.compute(ram.getAll()[0], ram.getAll()[1]);
        for (int i = 2; i < ram.getAll().length; i++) {
            sum = cpu.compute(sum, ram.getAll()[i]);
        }
        ram.set(0, 0, sum);
    }

    public void plugIn(String peripheral) {
        peripherals.add(peripheral);
    }

    public String plugOut() {
        return peripherals.remove(peripherals.size() - 1);
    }

    public String plugOut(int index) {
        return peripherals.remove(index);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder(super.toString());
        for (String peripheral : peripherals) {
            sb.append(" ").append(peripheral);
        }
        return sb.toString();
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
    Thread.sleep((int)((4/clock)*1000));

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
private int[][] memory ;


RAM(String type, int capacity){
    this.type=type;
    this.capacity=capacity;
    initMemory(capacity);
}



  
public String getType(String type){

    this.type=type;
    
    return type;
    }
    public int getCapacity(int capacity){
    
        this.capacity=capacity;
        return capacity;
    }
    public int[][] getMemory(int[][] memory){
    
        this.memory=memory;
        return memory;
    }
    
private void initMemory(int capacity) {
    this.memory = new int[capacity][capacity];
    Random random = new Random();
    for (int i = 0; i < capacity; i++) {
      for (int j = 0; j < capacity; j++) {
        this.memory[i][j] = random.nextInt(11); // 0 -10
      }
    }
  }


private boolean check(int i, int j){

if (capacity<i || capacity<j) {
    return false;
    
    
} else {
    return true;
}


}

  
private int getValue(int i, int j){
    
    
    initMemory(capacity);
    return memory[i][j];

}

public void setValue(int i, int j, int value){

    check(i, j);
    initMemory(i);
    memory[i][j]=value;


}


@Override
public String toString() {
    
    return "RAM; "+ type+" "+ capacity+"GB";
}




}