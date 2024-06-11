/**---------------------------------------------------
 * Akdeniz University CSE102L Quiz II 
 * author: Yahya Efe Kurucay - 20220808005
 * since: 11.06.2024
 * Description: Quiz 02
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
import java.util.*;

public class Quiz02_20220808005 {
    public static void main(String[] args) {
    Engineer eng1 = new Engineer("Yahya", 5);
    Engineer eng2 = new Engineer("Efe", 7);
    ChiefEngineer chiefEng = new ChiefEngineer("Kurucay", 10);
    chiefEng.hireEngineer(eng1); chiefEng.hireEngineer(eng2);
    QualityAssuranceSpecialist qaSpecialist = new QualityAssuranceSpecialist("Gokcen");
    qaSpecialist.addDurabilityTarget("GTX 1080", 80);
    qaSpecialist.addDurabilityTarget("RTX 3080", 90);
    GPU gpu1 = new GPU("GTX 1080", 75, 8);
    GPU gpu2 = new GPU("RTX 3080", 85, 10);
    CPU cpu1 = new CPU("i7-9700K", 70, 3.6);
    ProductionFacility facility = new ProductionFacility();
    facility.addProductOrder(gpu1);facility.addProductOrder(gpu2);facility.addProductOrder(cpu1);
    Product product;
    while ((product = facility.processNextProductOrder()) != null) {
    if (product instanceof GPU) {
        eng1.enhanceDurability(product);
            qaSpecialist.testProduct((GPU) product);
            if (((GPU) product).getQualityScore() >= 80) {
                 facility.storeProduct(product);
                  facility.addProductToInventory(product);
               } else {
                facility.addProductOrder(product); 
             }
            } else if (product instanceof CPU) {
              eng2.enhanceDurability(product);
                facility.storeProduct(product);
              facility.addProductToInventory(product);
            }
        }

        facility.analyzeInventory();
    }
}

abstract class Product {
    private String modelName;
    private int initialDurability;

    public Product(String modelName, int initialDurability) {
        this.modelName = modelName;
        this.initialDurability = initialDurability;
    }

    public String getModelName() {
        return modelName;
    }

    public int getInitialDurability() {
        return initialDurability;
    }

    public void setInitialDurability(int initialDurability) {
        this.initialDurability = initialDurability;
    }

    public abstract void printDetails();
}

interface Testable {
    void testProduct();
}

class GPU extends Product implements Testable {
    private int vramSize;
    private int qualityScore;
    public GPU(String modelName, int initialDurability, int vramSize) {
        super(modelName, initialDurability);
        this.vramSize = vramSize;
    }
    public int getVramSize() {
        return vramSize;
    }
    public int getQualityScore() {
        return qualityScore;
    }
    public void setQualityScore(int qualityScore) {
        this.qualityScore = qualityScore;
    }
    @Override
    public void testProduct() {
        System.out.println("Testing GPU: " + getModelName());
        if (getInitialDurability() >= 80) {
            setQualityScore(90);
        } else {
            setQualityScore(70);
        }
    }
    @Override
    public void printDetails() {
        System.out.println("GPU Model: " + getModelName() + ", VRAM: " + vramSize + "GB, Durability: " + getInitialDurability());
    }
}
class CPU extends Product {
    private double operatingFrequency;
    public CPU(String modelName, int initialDurability, double operatingFrequency) {
        super(modelName, initialDurability);
        this.operatingFrequency = operatingFrequency;
    }

    public double getOperatingFrequency() {
        return operatingFrequency;
    }

    @Override
    public void printDetails() {
        System.out.println("CPU Model: " + getModelName() + ", Frequency: " + operatingFrequency + "GHz, Durability: " + getInitialDurability());
    }
}

abstract class Employee {
    private String name;

    public Employee(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}

interface DurabilityEnhancer {
    void enhanceDurability(Product product);
}

class Engineer extends Employee implements DurabilityEnhancer {
    private int experienceLevel;

    public Engineer(String name, int experienceLevel) {
        super(name);
        this.experienceLevel = experienceLevel;
    }

    public int getExperienceLevel() {
        return experienceLevel;
    }

    @Override
    public void enhanceDurability(Product product) {
        int newDurability = product.getInitialDurability() + experienceLevel * 5;
        product.setInitialDurability(newDurability);
        System.out.println("Engineer " + getName() + " enhanced durability of " + product.getModelName() + " to " + newDurability);
    }
}

class QualityAssuranceSpecialist extends Employee {
    private Map<String, Integer> durabilityTargets;

    public QualityAssuranceSpecialist(String name) {
        super(name);
        this.durabilityTargets = new HashMap<>();
    }

    public void addDurabilityTarget(String modelName, int target) {
        durabilityTargets.put(modelName, target);
    }

    public void updateDurabilityTarget(String modelName, int target) {
        durabilityTargets.put(modelName, target);
    }

    public void testProduct(GPU gpu) {
        System.out.println("Testing product by QA Specialist " + getName());
        Integer target = durabilityTargets.get(gpu.getModelName());
        if (target != null) {
            if (gpu.getInitialDurability() >= target) {
                gpu.setQualityScore(100);
                System.out.println("Product " + gpu.getModelName() + " passed with quality score: " + gpu.getQualityScore());
            } else {
                gpu.setQualityScore(50);
                System.out.println("Product " + gpu.getModelName() + " failed with quality score: " + gpu.getQualityScore());
            }
        } else {
            System.out.println("No durability target found for model " + gpu.getModelName());
        }
    }
}
class ChiefEngineer extends Engineer {
    private List<Engineer> engineers;
    public ChiefEngineer(String name, int experienceLevel) {
        super(name, experienceLevel);
        this.engineers = new ArrayList<>();
    }
    public void hireEngineer(Engineer engineer) {
        engineers.add(engineer);
        System.out.println("Hired Engineer: " + engineer.getName());
    }
    public void fireEngineer(Engineer engineer) {
        engineers.remove(engineer);
        System.out.println("Fired Engineer: " + engineer.getName());
    }
    public List<Engineer> getEngineers() {
        return engineers;
    }
}
class ProductionFacility {
    private Queue<Product> productQueue;
    private Stack<Product> warehouse;
    private Map<String, List<Product>> inventory;
    public ProductionFacility() {
        productQueue = new LinkedList<>();
        warehouse = new Stack<>();
        inventory = new HashMap<>();
    }
    public void addProductOrder(Product product) {
        productQueue.add(product);
        System.out.println("Added product order: " + product.getModelName());
    }
    public Product processNextProductOrder() {
        Product product = productQueue.poll();
        if (product != null) {
            System.out.println("Processing product order: " + product.getModelName());
        }
        return product;
    }
    public void storeProduct(Product product) {
     warehouse.push(product);
     System.out.println("Stored product in warehouse: " + product.getModelName());
    }
    public void addProductToInventory(Product product) {
     inventory.computeIfAbsent(product.getModelName(), k -> new ArrayList<>()).add(product);
        System.out.println("Added product to inventory: " + product.getModelName());
    }

    public void analyzeInventory() {
      System.out.println("Analyzing inventory...");
        inventory.forEach((modelName, products) -> {
         System.out.println("Model: " + modelName + ", Quantity: " + products.size());
        });
        String mostPopularModel = null;
        int maxCount = 0;
      for (Map.Entry<String, List<Product>> entry : inventory.entrySet()) {
            if (entry.getValue().size() > maxCount) {
              maxCount = entry.getValue().size();
                mostPopularModel = entry.getKey();
           }
        }
        System.out.println("Most popular model: " + mostPopularModel + " with quantity: " + maxCount);
    }
}



 /*
 *            __     _                                                          
 *           / _|   | |                                                         
 *       ___| |_ ___| | ___   _ _ __ _   _  ___ __ _ _   _   ___ ___  _ __ ___  
 *      / _ \  _/ _ \ |/ / | | | '__| | | |/ __/ _` | | | | / __/ _ \| '_ ` _ \ 
 *     |  __/ ||  __/   <| |_| | |  | |_| | (_| (_| | |_| || (_| (_) | | | | | |
 *      \___|_| \___|_|\_\\__,_|_|   \__,_|\___\__,_|\__, (_)___\___/|_| |_| |_|
 *                                                    __/ |                     
 *                                                   |___/                      
 */
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

