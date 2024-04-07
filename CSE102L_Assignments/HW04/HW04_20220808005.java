/**---------------------------------------------------

 * Akdeniz University CSE102L Assignments
 * author: Yahya Efe Kuruçay
 * since: 07.04.2024
 * Description: Homework04
 * Score: ?
 * Website: https://efekurucay.com
*---------------------------------------------------*/
/***
 *    ███████ ███████ ███████ 
 *    ██      ██      ██      
 *    █████   █████   █████   
 *    ██      ██      ██      
 *    ███████ ██      ███████ 
 *                            
 *                            
 */
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

public class HW04_20220808005 {

    public static void main(String[] args) {
        // Create a CPU
        CPU cpu = new CPU("Intel Core i7", 3.5);

        // Create a RAM
        RAM ram = new RAM("DDR4", 4);

        // Create a Computer
        Computer computer = new Computer(cpu, ram);

        try {
            // Test Computer's run method
            computer.run();
        } catch (ComputationException | MemoryException e) {
            e.printStackTrace();
        }

        // Create a Laptop
        Laptop laptop = new Laptop(cpu, ram, 5000);

        try {
            // Test Laptop's run method
            laptop.run();
        } catch (ComputationException | MemoryException e) {
            e.printStackTrace();
        }

        // Create a Desktop
        Desktop desktop = new Desktop(cpu, ram, "Keyboard", "Mouse", "Monitor");

        try {
            // Test Desktop's run method
            desktop.run();
        } catch (ComputationException | MemoryException e) {
            e.printStackTrace();
        }

        // Display objects
        System.out.println(computer.toString());
        System.out.println(laptop.toString());
        System.out.println(desktop.toString());
    }


    static class Computer {
        protected CPU cpu;
        protected RAM ram;

        public Computer(CPU cpu, RAM ram) {
            this.cpu = cpu;
            this.ram = ram;
        }

        public void run() throws ComputationException, MemoryException {
            int sum = 0;
            for (int i = 0; i < ram.getCapacity(); i++) {
                sum += ram.getValue(i, i);
            }
            try {
                cpu.compute(sum, 0);
            } catch (ComputationException e) {
                fixComputation(sum, 0);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        private void fixComputation(int val1, int val2) {
            int absVal1 = Math.abs(val1);
            int absVal2 = Math.abs(val2);
            try {
                cpu.compute(absVal1, absVal2);
            } catch (ComputationException | InterruptedException e) {
                e.printStackTrace();
            }
        }

        @Override
        public String toString() {
            return "Computer: " + cpu + " " + ram;
        }
    }

    static class Laptop extends Computer {
        private int milliAmp;
        private int battery;

        public Laptop(CPU cpu, RAM ram, int milliAmp) {
            super(cpu, ram);
            this.milliAmp = milliAmp;
            this.battery = milliAmp * 30 / 100;
        }

        public int batteryPercentage() {
            return (battery * 100) / milliAmp;
        }

        public void charge() {
            while (batteryPercentage() < 90) {
                battery += 2;
            }
        }

        @Override
        public void run() throws ComputationException, MemoryException {
            if (batteryPercentage() > 5) {
                super.run();
                battery -= 3;
            } else {
                charge();
            }
        }

        @Override
        public String toString() {
            return super.toString() + " " + battery;
        }
    }

    static class Desktop extends Computer {
        private ArrayList<String> peripherals;

        public Desktop(CPU cpu, RAM ram, String... peripherals) {
            super(cpu, ram);
            this.peripherals = new ArrayList<>(Arrays.asList(peripherals));
        }

        @Override
public void run() throws ComputationException, MemoryException {
    int sum = 0;
    for (int i = 0; i < ram.getCapacity(); i++) {
        for (int j = 0; j < ram.getCapacity(); j++) {
            try {
                sum = cpu.compute(sum, ram.getValue(i, j));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}


        public void plugIn(String peripheral) {
            peripherals.add(peripheral);
        }

        public String plugOut() {
            if (!peripherals.isEmpty()) {
                return peripherals.remove(peripherals.size() - 1);
            }
            return null;
        }

        public String plugOut(int index) {
            if (index >= 0 && index < peripherals.size()) {
                return peripherals.remove(index);
            }
            return null;
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

    static class CPU {
        private String name;
        private double clock;

        public CPU(String name, double clock) {
            this.name = name;
            this.clock = clock;
        }

        public String getName() {
            return name;
        }

        public double getClock() {
            return clock;
        }

        public int compute(int a, int b) throws ComputationException, InterruptedException {
            if (a + b < 0) {
                throw new ComputationException(this, a, b);
            }
            try {
                Thread.sleep((long) (5 / clock * 1000));
            } catch (InterruptedException e) {
                throw e;
            }
            return a + b;
        }

        @Override
        public String toString() {
            return "CPU: " + name + " " + clock + "Ghz";
        }
    }

    static class RAM {
        private String type;
        private int capacity;
        private int[][] memory;

        public RAM(String type, int capacity) {
            this.type = type;
            this.capacity = capacity;
            initMemory();
        }

        public String getType() {
            return type;
        }

        public int getCapacity() {
            return capacity;
        }

        private void initMemory() {
            memory = new int[capacity][capacity];
            Random rand = new Random();
            for (int i = 0; i < capacity; i++) {
                for (int j = 0; j < capacity; j++) {
                    memory[i][j] = rand.nextInt(11); // 0 to 10
                }
            }
        }

        private boolean check(int i, int j) throws MemoryException {
            if (i < 0 || i >= capacity || j < 0 || j >= capacity) {
                throw new MemoryException(this, i, j);
            }
            return true;
        }

        public int getValue(int i, int j) throws MemoryException {
            if (check(i, j)) {
                return memory[i][j];
            }
            return -1;
        }

        public void setValue(int i, int j, int value) throws MemoryException {
            if (check(i, j)) {
                memory[i][j] = value;
            }
        }

        @Override
        public String toString() {
            return "RAM: " + type + " " + capacity + "GB";
        }
    }

    static class MemoryException extends RuntimeException {
        private RAM ram;
        private int address1;
        private int address2;

        public MemoryException(RAM ram, int address1, int address2) {
            super("Memory out of range! With addresses #" + address1 + ", " + address2);
            this.ram = ram;
            this.address1 = address1;
            this.address2 = address2;
        }
    }

    static class ComputationException extends Exception {
        private CPU cpu;
        private int value1;
        private int value2;

        public ComputationException(CPU cpu, int value1, int value2) {
            super("Computation exception occurred on " + cpu + " with values: (" + value1 + ", " + value2 + ")");
            this.cpu = cpu;
            this.value1 = value1;
            this.value2 = value2;
        }

        public ComputationException(ComputationException e) {
            super("Unhandled exception occurred at " + e.cpu + " with values " + e.value1 + " and " + e.value2 + ":\n\t" + e.getMessage());
            this.cpu = e.cpu;
            this.value1 = e.value1;
            this.value2 = e.value2;
        }
    }
}

 /***
 *              _____                    _____                    _____          
 *             /\    \                  /\    \                  /\    \         
 *            /::\    \                /::\    \                /::\    \        
 *           /::::\    \              /::::\    \              /::::\    \       
 *          /::::::\    \            /::::::\    \            /::::::\    \      
 *         /:::/\:::\    \          /:::/\:::\    \          /:::/\:::\    \     
 *        /:::/__\:::\    \        /:::/__\:::\    \        /:::/__\:::\    \    
 *       /::::\   \:::\    \      /::::\   \:::\    \      /::::\   \:::\    \   
 *      /::::::\   \:::\    \    /::::::\   \:::\    \    /::::::\   \:::\    \  
 *     /:::/\:::\   \:::\    \  /:::/\:::\   \:::\    \  /:::/\:::\   \:::\    \ 
 *    /:::/__\:::\   \:::\____\/:::/  \:::\   \:::\____\/:::/__\:::\   \:::\____\
 *    \:::\   \:::\   \::/    /\::/    \:::\   \::/    /\:::\   \:::\   \::/    /
 *     \:::\   \:::\   \/____/  \/____/ \:::\   \/____/  \:::\   \:::\   \/____/ 
 *      \:::\   \:::\    \               \:::\    \       \:::\   \:::\    \     
 *       \:::\   \:::\____\               \:::\____\       \:::\   \:::\____\    
 *        \:::\   \::/    /                \::/    /        \:::\   \::/    /    
 *         \:::\   \/____/                  \/____/          \:::\   \/____/     
 *          \:::\    \                                        \:::\    \         
 *           \:::\____\                                        \:::\____\        
 *            \::/    /                                         \::/    /        
 *             \/____/                                           \/____/         
 *                                                                               
 */
