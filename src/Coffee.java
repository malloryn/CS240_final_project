import java.io.*;

public class Coffee implements MenuItem, Serializable {
    
    String coffeeName;
    double coffeePrice;
    // milk and espresso objects to get methods 
    Milk milk;
    Espresso espresso;

    public Coffee(){
       milk = new Milk();
       espresso = new Espresso();
    }

    public void setName(String name) {
        coffeeName = name;   
    }
   
    public String getName() {
        return coffeeName;
    }

    public void setPrice(double price) {
        coffeePrice = price;   
    }

    public double getPrice() {
        return coffeePrice;
    }
    
    public void setMilk(int milkLevel) {
        milk.setLevel(milkLevel);
    }
    
    public int getMilk() {
        return milk.getLevel();
    }

    public void setEspresso(int espressoLevel) {
        espresso.setLevel(espressoLevel);
    }
    
    public int getEspresso() {
        return espresso.getLevel();
    }

} // end Coffee class