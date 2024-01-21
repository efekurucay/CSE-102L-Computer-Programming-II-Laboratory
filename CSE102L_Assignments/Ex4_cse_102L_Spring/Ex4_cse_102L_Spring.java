import java.util.ArrayList;
import java.util.Random;
import java.util.Arrays;

public class Ex4_cse_102L_Spring {
    public static void main(String[] args) {


    }

}
class Computer{
    protected CPU cpu;
    protected RAM ram;

    Computer(CPU cpu, RAM ram) {
        this.cpu = cpu;
        this.ram = ram;
    }

    void run() {
        int a = 0;
        for(int i = 0, j= 0; i < ram.getCapacity(); i++,j++){
            a = cpu.compute(ram.getValue(i,j), a);
        }
        ram.setValue(0,0,a);

    }

    public String toString() {
        return "Computer: " + cpu + " " + ram;
    }

}

class Laptop extends Computer{
    private int milliAmp;
    private int battery;

    Laptop(CPU cpu, RAM ram, int milliAmp, int battery) {
        super(cpu, ram);
        this.milliAmp = (int)(milliAmp * (3.0 / 10));
        this.battery = battery;
    }

    public int batteryPercentage(){
        return (int)((battery / (double)milliAmp * 100.0));
    }

    public void charge(){
        while(batteryPercentage() < 89){
            battery = (int) (battery + (milliAmp * 2.0 / 100));
        }
    }

    public void run(){
        if(batteryPercentage() > 5){
            battery = cpu.compute(battery, (int)(-(milliAmp)*3.0/100));
        }else
            charge();
    }
}
class Desktop extends Computer{
    private java.util.ArrayList<String> peripherals;

    Desktop(CPU cpu, RAM ram, String... peripherals ){
        super(cpu, ram);
        this.peripherals = new ArrayList<>(Arrays.asList(peripherals));
    }

    public void run(){
        int a = 0;
        for (int i = 0; i < ram.getCapacity(); i++) {
            for (int j = 0; j < ram.getCapacity(); j++) {
                a = cpu.compute(ram.getValue(i, j), a);
            }
        }
        ram.setValue(0, 0, a);

    }

    public void plugIn(String peripheral){
        peripherals.add(peripheral);
    }

    public String plugOut(){
        return peripherals.get(peripherals.size() - 1);
    }

    public String plugOut(int index){
        return peripherals.get(index);
    }

    public String toString(){
        StringBuilder sb = new StringBuilder();
        for (String peripheral : peripherals) {
            sb.append(peripheral).append(", ");
        }
        return  super.toString() + sb.substring(0, sb.length() - 2) ;
    }

}

class CPU{
    private String name;
    private double clock;

    CPU(String name, double clock){
        this.name = name;
        this.clock = clock;
    }

    public String getName(){
        return name;
    }

    public double getClock() {
        return clock;
    }

    public int compute(int a, int b){
        return a + b;
    }

    public String toString(){
        return "CPU: " + name + " " + clock + "Ghz";
    }
}

class RAM{
    private String type;
    private int capacity;
    private int[][] memory;

    RAM(String type, int capacity){
        this.type = type;
        this.capacity = capacity;

        initMemory();
    }

    public int getCapacity() {
        return capacity;
    }

    public String getType() {
        return type;
    }

    private void initMemory(){
        memory = new int[capacity][capacity];
        Random rnd = new Random();
        for (int i = 0; i < memory.length; i++)
            for (int j = 0; j < memory[i].length; j++)
                memory[i][j] = rnd.nextInt(11);
    }

    private boolean check(int i, int j){
        return i < capacity && j < capacity && i >= 0 && j >= 0;
    }

    public int getValue(int i, int j){
        if(check(i, j))
            return memory[i][j];
        return -1;
    }

    public void setValue(int i, int j, int value){
        if(check(i,j))
            memory[i][j] = value;
    }

    public String toStirng(){
        return "RAM: " + type + " " + capacity + "GB";
    }
}