import java.util.*;

public class Customer extends User {
    
    String fullName = "fName";
    double moneyAmount = 100;
    int rewardsAmount = 0;

    // ArrayList to store the customer favorites
    ArrayList<Coffee> favoriteCoffees = new ArrayList<Coffee>();

    public Customer(){
        
    } // end constructor

    public void setName(String fName) {

        this.fullName = fName;
    } // end setName 

    public String getName() {

        return this.fullName;
    } // end getName 

    public void setMoney(double money) {

        this.moneyAmount = money;
    } // end setMoney

    public double getMoney() {

        return this.moneyAmount;
    } // end getMoney

    public void decMoney(double money) {

        if (this.moneyAmount > 10) {
            this.moneyAmount-=money;
        }
        else {
            System.out.println("You don't have money to spend.");
        }
    } // end decMoney 
    
    public void setRewardsAmount(int rewards) {

        this.rewardsAmount = rewards;
    }

    public int getRewardsAmount() {

        return this.rewardsAmount;
    }
    
} // end Customer class