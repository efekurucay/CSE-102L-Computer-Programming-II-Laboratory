


public class HW01_20220808005 {

}//class HW

class Stock {




private String symbol;
private String name;
private double previousClosingPrice;
private double currentPrice;

private double getChangePercent(){
//returns the percentage change from
//previousClosingPrice to currentPrice.
//|100 x the difference between numbers divided by the old one.|

double difference=Math.abs(currentPrice-previousClosingPrice);
if(previousClosingPrice==0){return 0;}

return 100*(difference/previousClosingPrice);
}

public String toString(){

    return  symbol+" - "+ name+": "+ currentPrice;
}











   
}//class Stock















class Fan{


}//class Fan
