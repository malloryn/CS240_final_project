import java.util.*;
import java.io.*;

public class ShoppingCart implements Serializable{
    
    // ArrayList of both Coffee and Pastry which inherit from MenuItem
    ArrayList<MenuItem> menuItemsSelected = new ArrayList<MenuItem>();

    Scanner input = new Scanner(System.in);
    boolean keepGoing = true;
    int milkOptionAndFlavorPrice = 1;

    // array of flavors
    String[] flavors = {"Caramel", "Vanilla", "Hazelnut", "Mocha"};
    // array of milk options
    String[] milkOptions = {"Oat Milk", "Whole Milk", "Almond Milk"};

    public ShoppingCart() {

    } // end constructor

    public void cartMenu(ArrayList<Coffee> coffeeArray, ArrayList<Pastry> pastryArray, ArrayList<Customer> custArray, int currentCustomerIndex, Milk milkCoffeeShop, Espresso espressoCoffeeShop) {

        // while loop to go through system until exit is chosen
        while(keepGoing) {

            System.out.println("         *** Shopping Cart ***");
            System.out.println("-------------------------------------");  
            System.out.println("");
            System.out.println("     *** The Coffee Shop Menu ***");  
            System.out.println("-------------------------------------");
            System.out.println("       Coffee             Price");
            System.out.println("-------------------------------------");
            
            for(int i = 0; i < coffeeArray.size(); i++) {
                System.out.println("       "+coffeeArray.get(i).getName()+"              "+"$"+coffeeArray.get(i).getPrice());

            }
            System.out.println("-------------------------------------");
            System.out.println("       Pastries           Price");
            System.out.println("-------------------------------------");
            
            for(int i = 0; i < pastryArray.size(); i++) {
                System.out.println("       "+pastryArray.get(i).getName()+"              "+"$"+pastryArray.get(i).getPrice());
            
            }
            System.out.println("-------------------------------------");
            System.out.print("Add a Flavor for $1.00 extra! Choose from ");
            for(String i : flavors) {
                String flavor = i;
                System.out.print(flavor+" ");
            }
            System.out.println(" ");
            System.out.print("Add a Milk Option for $1.00 extra! Choose from ");
            for(String i : milkOptions) {
                String milk = i;
                System.out.print(milk+" ");
            }

            System.out.println(" ");
            System.out.println("----------------------------------------");
            System.out.println("        *** Start an Order ***");
            System.out.println("----------------------------------------");
            // sending user to startOrder
            this.startOrder(coffeeArray, pastryArray, custArray, currentCustomerIndex, milkCoffeeShop, espressoCoffeeShop);

            keepGoing = false;
        } // end while
    } // end cartMenu

    public void startOrder(ArrayList<Coffee> coffeeArray, ArrayList<Pastry> pastryArray, ArrayList<Customer> custArray, int currentCustomerIndex, Milk milkCoffeeShop, Espresso espressoCoffeeShop) {
        
        boolean doneWithOrder = false;
        ArrayList<String> flavorAndMilkChoices = new ArrayList<String>();

        while(!doneWithOrder) {

            System.out.println("         *** Ordering ***");
            System.out.println("----------------------------------------");
            System.out.print("\tName of Item: ");
            String itemNameResponse = input.nextLine();
            System.out.print("Would you like to add a Flavor or Milk Option? (Y)es or (N)o: ");
            String flavorOrMilkResponse = input.nextLine();

            // bringing in the coffee array and checking if the answer from user exists in array
            for(int i = 0; i < coffeeArray.size(); i++) {
                if(itemNameResponse.equals(coffeeArray.get(i).getName())) {
                    
                    menuItemsSelected.add(coffeeArray.get(i));
                    // sending the specific coffee to affect the milk and espresso
                    int milkValue = 0;
                    int espressoValue = 0;
                    milkValue = milkCoffeeShop.getLevel() - coffeeArray.get(i).getMilk();
                    espressoValue = espressoCoffeeShop.getLevel() - coffeeArray.get(i).getEspresso();
                    milkCoffeeShop.setLevel(milkValue);
                    espressoCoffeeShop.setLevel(espressoValue);
                }
            }
            // bringing in the pastry array and checking if the answer from user exists in array
            for(int i = 0; i < pastryArray.size(); i++) {
                if(itemNameResponse.equals(pastryArray.get(i).getName())) {
                    
                    menuItemsSelected.add(pastryArray.get(i));
                }
            }
            // if user chooses to add a flavor or milk option
            if(flavorOrMilkResponse.equals("Y")) {

                while(keepGoing) {

                    System.out.print("\nFlavors: ");
                    for(String i : flavors) {
                        String flavor = i;
                        System.out.print(flavor+" ");
                    }
                    System.out.println("");
                    System.out.print("\nMilk Options: ");
                    for(String i : milkOptions) {
                        String milk = i;
                        System.out.print(milk+" ");
                    }

                    System.out.println("");
                    System.out.print("\nWhich would you like to add to your order?: ");
                    String flavorMilkResponse = input.nextLine();

                    if(Arrays.asList(flavors).contains(flavorMilkResponse)) {

                        flavorAndMilkChoices.add(flavorMilkResponse);
                        System.out.println(flavorMilkResponse+" has been added to your order.");
                    }
                    else if(Arrays.asList(milkOptions).contains(flavorMilkResponse)) {

                        flavorAndMilkChoices.add(flavorMilkResponse);
                        System.out.println(flavorMilkResponse+" has been added to your order.");
                    }
                    else {
                        System.out.println("Your choice is not an option. Please try again.");
                    }

                    System.out.println("");
                    System.out.print("Your Customization now contains: ");
                    for (int i = 0; i < flavorAndMilkChoices.size(); i++) {

                        System.out.print(flavorAndMilkChoices.get(i)+ " ");
                    }
                    System.out.println("");
                    System.out.print("\nAre you complete with your customization? (Y)es or (N)o: ");
                    String exitCustomizeResponse = input.nextLine();

                    if(exitCustomizeResponse.equals("Y")) {
                        keepGoing = false;
                    }
                    else if(exitCustomizeResponse.equals("N")) {
                        keepGoing = true;
                    }

                } // end while
            }
            else if(flavorOrMilkResponse.equals("N")) {

                System.out.println("");
            }
            else {
                System.out.println("Incorrect input. Please try again.");
            }

            System.out.print("Are you done with your order and ready to checkout? (Y)es or (N)o: ");
            String checkResponse = input.nextLine();

            if(checkResponse.equals("Y")) {
                // counter to add up the amount of elements in the ArrayList of flavors and milk
                int flavorAndMilkCounter = 0;
                for (int i = 0; i < flavorAndMilkChoices.size(); i ++) {
                    if (flavorAndMilkChoices.get(i) != null) {
                        flavorAndMilkCounter ++;
                    }
                }
                // startOrder ends and the user is sent to checkout
                doneWithOrder = true;
                checkout(coffeeArray, pastryArray, custArray, currentCustomerIndex, flavorAndMilkCounter, flavorAndMilkChoices);
            }
            else if(checkResponse.equals("N")) {
                // continue with order
                doneWithOrder = false;
            }
            else {
                System.out.println("Incorrect input. Please try again.");
            }

        } // end while
    } // end startOrder

    public void checkout(ArrayList<Coffee> coffeeArray, ArrayList<Pastry> pastryArray, ArrayList<Customer> custArray, int currentCustomerIndex, int flavorAndMilkCounter, ArrayList<String> flavorAndMilkChoices) {

        ArrayList<Double> pricesArray = new ArrayList<Double>();
        
        // for loop to add the prices from the selected menu items array into the new prices ArrayList
        for(int i = 0; i < menuItemsSelected.size(); i++) {
            
            pricesArray.add(menuItemsSelected.get(i).getPrice());
        }
        // finding the total cost of the flavor or milk options 
        int flavorAndMilkTotalCost = flavorAndMilkCounter * getMilkOptionAndFlavorPrice();

        // variable to store the total cost of the order
        double totalCostofOrder = 0;
        // for loop to go through the prices array and add all of the elements together
        for(int i = 0; i < pricesArray.size(); i++) {
            // total cost of order is the total for the flavors and milk as well as the total for the cost of the coffee
            totalCostofOrder += (pricesArray.get(i)+flavorAndMilkTotalCost);
        }
        // changing totalCostOfOrder from a double to integer
        int totalCostofOrderInt = (int)totalCostofOrder;

        // variable to store the customer index
        int currentCustomer = currentCustomerIndex;
        // sending the total cost of the order to calculate the amount of rewards for the customer
        // and adding that amount of rewards with the amount the customer currently has
        int rewardsForCustomer = calculateRewards(totalCostofOrderInt) + custArray.get(currentCustomer).getRewardsAmount();
        custArray.get(currentCustomer).setRewardsAmount(rewardsForCustomer);

        // decreasing the user's money by the total that they spent
        custArray.get(currentCustomerIndex).decMoney(totalCostofOrder);

        printReceipt(rewardsForCustomer, totalCostofOrderInt, totalCostofOrder, currentCustomer, custArray, flavorAndMilkChoices, flavorAndMilkTotalCost);
        
    } // end checkout

    public int calculateRewards(int totalCostOfOrderInt) {

        int total = totalCostOfOrderInt;
        // the amount of rewards a customer receives is the total cost of their order multiplied by 2
        int calculatedRewards = total * 2;

        return calculatedRewards;
    } // end calculateRewards

    public void printReceipt(int rewardsForCustomer, int totalCostOfOrderInt, double totalCostofOrder, int currentCustomer, ArrayList<Customer> custArray, ArrayList<String> flavorAndMilkChoices, int flavorAndMilkTotalCost) {

        System.out.println("         *** Receipt ***");
        System.out.println("-----------------------------------");  
        System.out.println("       Item            Price");
        System.out.println("-----------------------------------");  

        for(int i = 0; i < menuItemsSelected.size(); i++) {

            System.out.println("       "+menuItemsSelected.get(i).getName()+"          "+"$"+menuItemsSelected.get(i).getPrice());
        }
        for (int i = 0; i < flavorAndMilkChoices.size(); i ++) {
            if (flavorAndMilkChoices.get(i) != null) {
                System.out.println("       "+flavorAndMilkChoices.get(i)+"          "+"$"+getMilkOptionAndFlavorPrice());
            }
        }
        System.out.println("");
        System.out.println("Total: $"+ totalCostofOrder);
        System.out.println("Rewards Earned: "+ calculateRewards(totalCostOfOrderInt));
        System.out.println("Your Total Rewards: "+ rewardsForCustomer);
        System.out.println("");
        System.out.println("Thank you for visiting My Coffee Shop "+custArray.get(currentCustomer).getName()+"! Enjoy!");

    } // end printReceipt

    public int getMilkOptionAndFlavorPrice() {

        return milkOptionAndFlavorPrice;
    }

} // end ShoppingCart class