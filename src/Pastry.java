import java.io.*;

public class Pastry implements MenuItem, Serializable {
    
    String pastryName;
    double pastryPrice;

    public void setName(String name) {
        pastryName = name;   
    }

    public void setPrice(double price) {
        pastryPrice = price;   
    }

    public String getName() {
        return pastryName;
    }

    public double getPrice() {
        return pastryPrice;
    }

} // end Pastry class