/**-----------------------------------------------------
* Akdeniz University CSE102L Lab Assignments
* Name: Yahya Efe Kurucay
* Date: 25.02.2024
* Description: Homework01
* Score: 93
* Website: https://efekurucay.com
*----------------------------------------------------- */
/*
 * 
 * Bu bir otomatik maildir!!
 -------------START----------------
20220808005 - HW01->93
Test Result
╷
├─ JUnit Jupiter ✔
├─ JUnit Vintage ✔
│  └─ HW01_20220808005Test ✔
│     ├─ shouldHaveConstructorWith2Parameter ✔
│     ├─ shouldFanConstructorHaveCorrectParameterTypes ✔
│     ├─ FanClassModifierControls ✔
│     ├─ FanClassConstantsControls ✔
│     ├─ fanShouldNotBeAnInnerClass ✔
│     ├─ shouldHaveStockClass ✔
│     ├─ fanShouldNotBeStaticClass ✔
│     ├─ shouldFanHave2Constructor ✔
│     ├─ StockClassFieldTypeControls ✔
│     ├─ shouldSetRadius ✔
│     ├─ shouldInstantiateWithNoArgConstructor ✔
│     ├─ shouldGetRadius ✔
│     ├─ shouldGetisOn ✔
│     ├─ stockShouldNotBeAnInnerClass ✔
│     ├─ shouldSetColor ✔
│     ├─ shouldStockHaveConstructorWith2Parameter ✘ Person class should have a constructor with 2 parameters
│     ├─ stockShouldNotBeStaticClass ✔
│     ├─ stockShouldGetChangePercent ✘ Stock.<init>(java.lang.String,java.lang.String)
│     ├─ FanClassFieldTypeControls ✔
│     ├─ shouldFanHaveConstructorWith2Parameter ✔
│     ├─ shouldSersonHave1Constructor ✔
│     ├─ shouldGetColor ✔
│     ├─ shouldGetSpeed ✔
│     ├─ shouldHaveFanClass ✔
│     └─ shouldStockConstructorHaveCorrectParameterTypes ✔
└─ JUnit Platform Suite ✔


--------------END-----------------
 */

public class HW01_20220808005 {
  public static void main(String[] args) {
    
  }
}//class HW

class Stock {
    private String symbol;
    private String name;
    private double previousClosingPrice;
    private double currentPrice;

Stock(String symbol, String name, double previousClosingPrice, double currentPrice) {
        this.symbol = symbol.toUpperCase();
        this.name = capitalizeWords(name);
        this.previousClosingPrice = previousClosingPrice;
        this.currentPrice = currentPrice;
    }

String getSymbol() {
        return symbol;
    }
String getName() {
        return name;
    }

double getPreviousClosingPrice() {
        return previousClosingPrice;
    }

double getCurrentPrice() {
        return currentPrice;
    }

void setPreviousClosingPrice(double previousClosingPrice) {
        if (previousClosingPrice < 0) {
            System.out.println("ERROR: Invalid previous closing price");
        } else {
            this.previousClosingPrice = previousClosingPrice;
        }
    }

void setCurrentPrice(double currentPrice) {
        if (currentPrice < 0) {
            System.out.println("ERROR: Invalid current price");
        } else {
            this.currentPrice = currentPrice;
        }
    }

double getChangePercent() {
        return ((currentPrice - previousClosingPrice) / previousClosingPrice) * 100;
    }
private String capitalizeWords(String str) {
        StringBuilder result = new StringBuilder();
        String[] words = str.split(" ");
        for (String word : words) {
            result.append(Character.toUpperCase(word.charAt(0))).append(word.substring(1)).append(" ");
        }
        return result.toString().trim();
    }

    
    
}//class stock

class Fan {
    private static final int SLOW = 1;
    private static final int MEDIUM = 2;
    private static final int FAST = 3;
    private int speed;
    private boolean on;
    private double radius;
    private String color;

    public static int SLOW(){return SLOW;}
    public static int MEDIUM(){return MEDIUM;}
    public static int FAST(){return FAST;}
    Fan() {
        speed = SLOW;
        on = false;
        radius = 5;
        color = "Blue";
        
    }

    Fan(double radius, String color) {
        this();
        this.radius = radius;
        this.color = color;
    }


    int getSpeed() {
        return speed;
    }

    void setSpeed(int speed) {
        if (on) {
            this.speed = speed;
        }
    }

    boolean isOn() {
        return on;
    }

    void change() {
        on = !on;
    }

    double getRadius() {
        return radius;
    }

    void setRadius(double radius) {
        this.radius = radius;
    }

    String getColor() {
        return color;
    }

    void setColor(String color) {
        this.color = color;
    }

    @Override
    public String toString() {
        if (on) {
            return String.format("Speed: %d, Radius: %.2f, Color: %s", speed, radius, color);
        } else {
            return "Fan is off";
        }
    }
}//class fan
