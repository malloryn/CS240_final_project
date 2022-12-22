import java.util.*;
import java.io.*;

public class CoffeeShop implements Restaurant, Serializable {
    
    // used for user menus to take in input
    Scanner input = new Scanner(System.in);
    // keepGoing to use in while loops
    boolean keepGoing = true;
    // ArrayList to store all instances of the customers
    ArrayList<Customer> custArray = new ArrayList<Customer>(); 
    ArrayList<Coffee> coffeeArray = new ArrayList<Coffee>();
    ArrayList<Pastry> pastryArray = new ArrayList<Pastry>();
    
    int currentCustomerIndex;
   
    // instances of all used classes
    LoginMenu login = new LoginMenu();
    Milk milkCoffeeShop = new Milk();
    Espresso espressoCoffeeShop = new Espresso();
    
    public static void main(String[] args) {
        try {
            new CoffeeShop();
        } 
        catch(Exception e) {
            System.out.println(e.getMessage());
        }

    } // end main

    public CoffeeShop() throws ClassNotFoundException, IOException {

      startCoffee();
      load();
      //System.out.println(custArray.size());
      this.login();
      
    } // end constructor

    // creates default coffee types that come with the shop
    public void startCoffee() {
    
        // default milk supply level for the coffee shop
        milkCoffeeShop.setLevel(30);
        // default espresso supply level for the coffee shop
        espressoCoffeeShop.setLevel(10);

    } // end startCoffee

    public void login() throws IOException {
        // calls the loginMenu method and sends custArray
        login.loginMenu(custArray);

        if(login.isOwner) {
            //take me to ownerMenu
            ownerMenu();
            
        }
        else if(login.isValidUser) {
           
            currentCustomerIndex = login.currentCustomerIndex;   
             //take me to customerMenu
            custMenu(currentCustomerIndex);  
        }
        else {
            System.out.println("Something is wrong");
        }

    } // end login
    
    public void custMenu(int currentCustomerIndex) {
        this.greetCustomer();
        
        // while loop to go through system until exit is chosen
        while(keepGoing) {

            System.out.println("---------------------------");  
            System.out.println("*** Select an Option ***");
            System.out.println("");  
            System.out.println("1) View Coffee Shop Menu");
            System.out.println("2) Favorite an Item");
            System.out.println("3) See Your Favorites");
            System.out.println("4) Delete a Favorite");
            System.out.println("5) Check Your Rewards Amount");
            System.out.println("6) Check Your Balance");
            System.out.println("7) Place an Order");
            System.out.println("8) Log Out");
            System.out.println("9) Exit System");
            System.out.print("Option Selected: ");
            // take in the user's response
            String custResponse = input.nextLine();
            System.out.println("---------------------------");

                if (custResponse.equals("1")) {

                    System.out.println(" *** Coffee Shop Menu ***");
                    System.out.println("--------------------------");
                    System.out.println("Coffee               Price");
                    System.out.println("--------------------------");
                    
                    for(int i = 0; i< coffeeArray.size(); i++) {
                        System.out.println(coffeeArray.get(i).getName()+"                "+"$"+coffeeArray.get(i).getPrice());
    
                    }
                    System.out.println("---------------------------");
                    System.out.println("Pastries             Price");
                    System.out.println("---------------------------");
                    
                    for(int i = 0; i< pastryArray.size(); i++) {
                        System.out.println(pastryArray.get(i).getName()+"                "+"$"+pastryArray.get(i).getPrice());
                    }

                } // end if

                else if(custResponse.equals("2")) {

                    System.out.println("*** Add a Coffee to Favorites ***");
                    System.out.println("");
                    System.out.println("Coffee               Price");
                    System.out.println("---------------------------");

                    for(int i = 0; i< coffeeArray.size(); i++) {
                        System.out.println(coffeeArray.get(i).getName()+"                 "+"$"+coffeeArray.get(i).getPrice());
 
                    } 
                    System.out.print("Please type the name of the Coffee you would like to add to Favorites: ");
                    String favoriteResponse = input.nextLine();

                    for(int i = 0; i < coffeeArray.size(); i++) {
                        // checks if the response equals an existing name in the coffeeArray
                        if (favoriteResponse.equals(coffeeArray.get(i).getName())) {
                            // gets the current customer and adds the Coffee object to the favoriteCoffees array
                            custArray.get(currentCustomerIndex).favoriteCoffees.add(coffeeArray.get(i));
                            System.out.println("'"+coffeeArray.get(i).getName()+"'"+" has now been added to your Favorites!");
                        }
                    } 
                }

                else if(custResponse.equals("3")) {
                    
                    if(custArray.get(currentCustomerIndex).favoriteCoffees.size() != 0) {
                        System.out.println("*** List of Favorites ***");
                        System.out.println("");
                        for (int i = 0; i <  custArray.get(currentCustomerIndex).favoriteCoffees.size(); i++) {
                            System.out.println(custArray.get(currentCustomerIndex).favoriteCoffees.get(i).getName());
                            
                        }
                    }
                    else {
                        System.out.println("Your Favorites list is empty!");
                        System.out.println("Consider adding one in 'Favorite an Item'.");
                    }
                }

                else if(custResponse.equals("4")) {
                    
                    System.out.println("*** List of Favorites ***");
                    System.out.println("---------------------------");
                    for (int i = 0; i <  custArray.get(currentCustomerIndex).favoriteCoffees.size(); i++) {
                        System.out.println(custArray.get(currentCustomerIndex).favoriteCoffees.get(i).getName());
                        
                    }
                    System.out.print("Please type the name of the Coffee you would like to delete from Favorites: ");
                    String deleteResponse = input.nextLine();

                    for(int i = 0; i < coffeeArray.size(); i++) {
                        // checks if the response equals an existing name in the coffeeArray
                        if (deleteResponse.equals(coffeeArray.get(i).getName())) {
                            // gets the current customer and deletes the Coffee object in the favoriteCoffees array
                            custArray.get(currentCustomerIndex).favoriteCoffees.remove(coffeeArray.get(i));
                            System.out.println("'"+coffeeArray.get(i).getName()+"'"+" has now been deleted from your Favorites!");
                        }
                    } 
                }

                else if(custResponse.equals("5")) {
                    
                    System.out.println("*** Your Rewards Amount ***");
                    System.out.println("---------------------------");
                    
                    for(int i = 0; i < 1; i++) {

                        System.out.println("       "+custArray.get(currentCustomerIndex).getRewardsAmount()+" rewards");
                    }                    
                }
                else if(custResponse.equals("6")) {
                    
                    System.out.println("*** Your Balance ***");
                    System.out.println("------------------------");
                    
                    for(int i = 0; i < 1; i++) {

                        System.out.println("       $"+custArray.get(currentCustomerIndex).getMoney());
                    }                    
                }

                else if(custResponse.equals("7")) {

                    ShoppingCart shoppingCart = new ShoppingCart();
                    shoppingCart.cartMenu(coffeeArray, pastryArray, custArray, currentCustomerIndex, milkCoffeeShop, espressoCoffeeShop);
                    this.custMenu(currentCustomerIndex);
                }

                else if(custResponse.equals("8")) {

                    System.out.println("Logging you out...");
                    System.out.println("Thank you for visiting! See you soon!");
                    // sends back to login 
                    login.isValidUser=false;
                    try {
                        this.login();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    keepGoing = false;
                }

                else if(custResponse.equals("9")) {

                    System.out.println("Exiting the System...");
                    System.out.println("Thank you for visiting! Have a great day!");
                    try {
                        // saving data
                        save();
                        keepGoing = false;
                    }
                    catch(Exception e) {
                        System.out.println(e.getMessage());
                    }
                }

                else {
                    System.out.println("Invalid entry. Please try again");
                }
        } // end while
    } // end custMenu

    public void ownerMenu() {

        // while loop to go through system until exit is chosen
        while(keepGoing) {

            System.out.println("---------------------------");  
            System.out.println("*** Select an Option ***");
            System.out.println("");  
            System.out.println("1) View Coffee Shop Menu");
            System.out.println("2) Add a Menu Item");
            System.out.println("3) Edit a Menu Item");
            System.out.println("4) Delete a Menu Item");
            System.out.println("5) View Customers");
            System.out.println("6) Check/Order Supplies");
            System.out.println("7) Log Out");
            System.out.println("8) Exit System");
            System.out.print("Option Selected: ");
            // take in the user's response
            String ownerResponse = input.nextLine();
            System.out.println("---------------------------");

            if(ownerResponse.equals("1")) {
                System.out.println("                  *** Your Coffee Shop Menu ***");
                System.out.println("----------------------------------------------------------------------");
                System.out.println("Coffee               Price               Milk               Espresso");
                System.out.println("----------------------------------------------------------------------");
                
                for(int i = 0; i< coffeeArray.size(); i++) {
                    System.out.println(coffeeArray.get(i).getName()+"                "+"$"+coffeeArray.get(i).getPrice()+"                "+coffeeArray.get(i).getMilk()+" oz."+"                "+coffeeArray.get(i).getEspresso()+" oz.");

                }
                System.out.println("----------------------------------------------------------------------");
                System.out.println("Pastries             Price");
                System.out.println("----------------------------------------------------------------------");
                
                for(int i = 0; i< pastryArray.size(); i++) {
                    System.out.println(pastryArray.get(i).getName()+"                "+"$"+pastryArray.get(i).getPrice());

                }
            } // end if

            else if(ownerResponse.equals("2")) {

                System.out.println("*** Adding a Menu Item ***");
                System.out.println("---------------------------");
                System.out.print("What kind of item would you like to add, (C)offee or (P)astry?: ");
                String itemResponse = input.nextLine();

                if(itemResponse.equals("C")) {
                    System.out.print("\nName of New Coffee: ");
                    String coffeeName = input.nextLine();
                    System.out.print("Price of New Coffee: ");
                    String coffeePrice = input.nextLine();
                    System.out.print("\nLevel of Milk Required to Make: ");
                    String coffeeMilk = input.nextLine();
                    System.out.print("Level of Espresso Required to Make: ");
                    String coffeeEspresso = input.nextLine();
                    // adding the responses to the Coffee array
                    Coffee newCoffeeObject = new Coffee(); 
                    newCoffeeObject.setName(coffeeName);
                    newCoffeeObject.setPrice(Double.parseDouble(coffeePrice));
                    newCoffeeObject.setMilk(Integer.parseInt(coffeeMilk));
                    newCoffeeObject.setEspresso(Integer.parseInt(coffeeEspresso));
                    // adding the new coffee information to the coffeeArray
                    coffeeArray.add(newCoffeeObject);
                    System.out.println(coffeeName +" has been added to the menu!");
                }

                else if(itemResponse.equals("P")) {
                    System.out.print("\nName of New Pastry: ");
                    String pastryName = input.nextLine();
                    System.out.print("Price of New Pastry: ");
                    String pastryPrice = input.nextLine();

                    Pastry newPastryObject = new Pastry(); 
                    newPastryObject.setName(pastryName);
                    newPastryObject.setPrice(Double.parseDouble(pastryPrice));
                    // adding the new coffee information to the coffeeArray
                    pastryArray.add(newPastryObject);
                    System.out.println(pastryName +" has been added to the menu!");
                }
                else {
                    System.out.println("Invalid entry. Please try again.");
                }
            }

            else if(ownerResponse.equals("3")) {
                // boolean to see if the menu item is present in arrays
                boolean isFoundDuringMenuChange = false;

                System.out.println("                   *** Editing a Menu Item ***");
                System.out.println("----------------------------------------------------------------------");
                System.out.println("                  *** Your Coffee Shop Menu ***");
                System.out.println("----------------------------------------------------------------------");
                System.out.println("Coffee               Price               Milk               Espresso");
                System.out.println("----------------------------------------------------------------------");
                
                for(int i = 0; i< coffeeArray.size();i++) {
                    System.out.println(coffeeArray.get(i).getName()+"                "+"$"+coffeeArray.get(i).getPrice()+"                "+coffeeArray.get(i).getMilk()+" oz."+"                "+coffeeArray.get(i).getEspresso()+" oz.");

                }
                System.out.println("----------------------------------------------------------------------");
                System.out.println("Pastries             Price");
                System.out.println("----------------------------------------------------------------------");
                
                for(int i = 0; i< pastryArray.size(); i++) {
                    System.out.println(pastryArray.get(i).getName()+"                "+"$"+pastryArray.get(i).getPrice());

                }
                System.out.print("Which item in your menu would you like to change?: ");
                String itemToChange = input.nextLine();

                for(int i = 0; i < coffeeArray.size(); i++) {

                    if(itemToChange.equals(coffeeArray.get(i).getName())) {
                        System.out.println("Making an edit to "+ coffeeArray.get(i).getName()+"...");  

                        // asking user to create a new version of the coffee
                        System.out.print("\nChange Name Here: ");
                        String newCoffeeName = input.nextLine();
                        System.out.print("\nChange Price Here: ");
                        String newCoffeePrice = input.nextLine();
                        System.out.print("\nNew Level of Milk Required to Make: ");
                        String newCoffeeMilk = input.nextLine();
                        System.out.print("\nNew Level of Espresso Required to Make: ");
                        String newCoffeeEspresso = input.nextLine();
                        // adding the responses to the Coffee array
                        Coffee newCoffee = new Coffee(); 
                        newCoffee.setName(newCoffeeName);
                        newCoffee.setPrice(Double.parseDouble(newCoffeePrice));
                        newCoffee.setMilk(Integer.parseInt(newCoffeeMilk));
                        newCoffee.setEspresso(Integer.parseInt(newCoffeeEspresso));

                        System.out.println(coffeeArray.get(i).getName() +" is being changed to "+newCoffeeName+"!");
                        // replacing the old coffee with new coffee
                        coffeeArray.set(i, newCoffee);

                        isFoundDuringMenuChange = true;

                    }
                }
           
                for(int i = 0; i < pastryArray.size(); i++) {

                    if(itemToChange.equals(pastryArray.get(i).getName())) {
                        System.out.println("Making an edit to "+ pastryArray.get(i).getName()+"...");  

                        // asking user to create a new version of the pastry
                        System.out.print("\nChange Name Here: ");
                        String newPastryName = input.nextLine();
                        System.out.print("Change Price Here: ");
                        String newPastryPrice = input.nextLine();
                        // adding the responses to the Coffee array
                        Pastry newPastry = new Pastry(); 
                        newPastry.setName(newPastryName);
                        newPastry.setPrice(Double.parseDouble(newPastryPrice));
                        // replacing the old pastry with new pastry
                        pastryArray.set(i, newPastry);

                        isFoundDuringMenuChange = true;
                    }
                }

                if(!isFoundDuringMenuChange) {
                    System.out.println("Selected Item doesn't exist, please check input.");

                }
                else {
                    System.out.println("Update complete.");

                }
            }

            else if(ownerResponse.equals("4")) {

                System.out.println("*** Deleting a Menu Item ***");
                System.out.println("---------------------------");

                System.out.println("----Coffee Menu----");
                for(int i = 0; i< coffeeArray.size(); i++) {
                    System.out.println(coffeeArray.get(i).getName());

                }
                System.out.println("----Pastry Menu----");
                for(int i = 0; i< pastryArray.size(); i++) {
                    System.out.println(pastryArray.get(i).getName());

                }

                System.out.print("Which item would you like to delete?: ");
                String deleteResponse = input.nextLine();

                for(int i = 0; i< coffeeArray.size(); i++) {
                    if(deleteResponse.equals(coffeeArray.get(i).getName())) {
                        
                        System.out.println("'"+coffeeArray.get(i).getName()+"'"+" has now been removed from the menu!");
                        coffeeArray.remove(coffeeArray.get(i)); 
                    }
                }
                
                for(int i = 0; i< pastryArray.size(); i++) {
                    if(deleteResponse.equals(pastryArray.get(i).getName())) {
                        
                        System.out.println("'"+pastryArray.get(i).getName()+"'"+" has now been removed from the menu!");
                        pastryArray.remove(pastryArray.get(i));
                    }
                }
            }
            
            else if(ownerResponse.equals("5")) {

                System.out.println("  *** Your Customers ***");
                System.out.println("---------------------------");
                for(int i = 0; i < custArray.size(); i++) {
                    System.out.println("     "+ i +") "+custArray.get(i).getName());

                }

            }
            
            else if(ownerResponse.equals("6")) {
  
                System.out.println("        *** Ordering Supplies ***");
                System.out.println("---------------------------------------------");
                System.out.println("Milk Supply Level       Espresso Supply Level");
                System.out.println("---------------------------------------------");
                
                for(int i = 0; i< 1; i++) {
                    System.out.println(milkCoffeeShop.getLevel()+" oz."+"                  "+espressoCoffeeShop.getLevel()+" oz.");
                    
                }    
                // if the level of milk is low then it will suggest ordering more    
                if(milkCoffeeShop.getLevel() <= 20) {
                    System.out.println("---------------------------------------------");
                    System.out.println("Your Milk Supply Level is getting low!");
                    System.out.print("Would you like to order more? (Y)es or (N)o?: ");
                    String mOrderResponse = input.nextLine();

                    if(mOrderResponse.equals("Y")) {
                        System.out.print("How much milk would you like to order?: ");
                        String milkOrderResponse = input.nextLine();
                        int milkOrderInt = Integer.parseInt(milkOrderResponse);
                        int milkTotalAfterOrder = milkOrderInt + milkCoffeeShop.getLevel();
                        milkCoffeeShop.setLevel(milkTotalAfterOrder);
                        System.out.println("Your order has been received and delivered!");

                    }
                    else if(mOrderResponse.equals("N")) {

                        System.out.println("Okay sending you back to the Menu");
                        this.ownerMenu();
                    }
                    else {
                        System.out.println("Invalid input. Please try again.");
                    }
                }
                else if(espressoCoffeeShop.getLevel() <= 10) {
                    System.out.println("---------------------------------------------");
                    System.out.println("Your Espresso Supply Level is getting low!");
                    System.out.print("Would you like to order more? (Y)es or (N)o?: ");
                    String eOrderResponse = input.nextLine();

                    if(eOrderResponse.equals("Y")) {
                        System.out.print("How much espresso would you like to order?: ");
                        String espressoOrderResponse = input.nextLine();
                        int espressoOrderInt = Integer.parseInt(espressoOrderResponse);
                        int espressoTotalAfterOrder = espressoOrderInt + espressoCoffeeShop.getLevel();
                        espressoCoffeeShop.setLevel(espressoTotalAfterOrder);
                        System.out.println("Your order has been received and delivered!");

                    }
                    else if(eOrderResponse.equals("N")) {
                        System.out.println("Okay sending you back to the Menu");
                        this.ownerMenu();

                    }
                    else {
                        System.out.println("Invalid input. Please try again.");
                    }
                }
                else {
                    System.out.println("The supply level of Milk and Espresso is sufficient.");
                }
            }

            else if(ownerResponse.equals("7")) {

                System.out.println("Logging you out... Have a great day!");
                // sending back to login menu
                login.isValidUser=false;
                login.isOwner=false;
                try {
                    this.login();
                    keepGoing = false;
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            else if(ownerResponse.equals("8")) {

                System.out.println("Exiting the System... Have a great day!");
                try {
                    // saving data
                    save();
                    keepGoing = false;
                }
                catch(Exception e) {
                    System.out.println(e.getMessage());
                }
            }

            else {
                System.out.println("Invalid entry. Please try again");
            }
        } // end while
    } // end ownerMenu

    public void greetCustomer() {

        System.out.println("");
        System.out.println("Sit back, relax, and grab a drink or pastry! Here you");
        System.out.println("can decide if you would like to see the shop menu, search for");
        System.out.println("an item in the menu, favorite an item in the menu, check your Rewards");
        System.out.println("amount, or place an order! Enjoy traversing through the shop!");

    } // end greetCustomer

    public void save() throws IOException{
        try {
       
            FileOutputStream fo = new FileOutputStream("coffeeData.dat");
            ObjectOutputStream obOut = new ObjectOutputStream(fo);
            obOut.writeObject(coffeeArray);
            obOut.close();
            fo.close();
        }
        catch (IOException e) {
            System.out.println(e.getMessage());
            System.out.println("could not be saved");
        }
        try {
       
            FileOutputStream foS = new FileOutputStream("pastryData.dat");
            ObjectOutputStream obOutS = new ObjectOutputStream(foS);
            obOutS.writeObject(pastryArray);
            obOutS.close();
            foS.close();
        }
        catch (IOException e) {
            System.out.println(e.getMessage());
            System.out.println("could not be saved");
        }
        try {
            FileOutputStream fOutC = new FileOutputStream("custData.dat");
            ObjectOutputStream obOutC = new ObjectOutputStream(fOutC);
            obOutC.writeObject(custArray);
            obOutC.close();
            fOutC.close();
            //System.out.println("saved customer");
        }
        catch (IOException e) {
            System.out.println(e.getMessage());
            System.out.println("customer could not be saved");
        }
        try {
            FileOutputStream fOutM = new FileOutputStream("milkData.dat");
            ObjectOutputStream obOutM = new ObjectOutputStream(fOutM);
            obOutM.writeObject(milkCoffeeShop);
            obOutM.close();
            fOutM.close();
            //System.out.println("saved milk");
        }
        catch (IOException e) {
            System.out.println(e.getMessage());
            System.out.println("milk could not be saved");
        }
        
        try {
            FileOutputStream fOutE = new FileOutputStream("espressoData.dat");
            ObjectOutputStream obOutE = new ObjectOutputStream(fOutE);
            obOutE.writeObject(espressoCoffeeShop);
            obOutE.close();
            fOutE.close();
            //System.out.println("saved espresso");
        }
        catch (IOException e) {
            System.out.println(e.getMessage());
            System.out.println("espresso could not be saved");
        }
        
    } // end save

    public void load() throws ClassNotFoundException, IOException {
        try {
            System.out.println("Brewing coffee...");
            FileInputStream fIn = new FileInputStream("coffeeData.dat");
            ObjectInputStream obIn = new ObjectInputStream(fIn);
            //cast into ArrayList
            coffeeArray = (ArrayList<Coffee>)obIn.readObject();
            //System.out.println("Loaded Coffee instances...");
            obIn.close();
            fIn.close();

        }  
        catch (IOException e) {
            System.out.println("No previous files available...");
        }
        try {
            //System.out.println("Loading Pastry...");
            FileInputStream fInS = new FileInputStream("pastryData.dat");
            ObjectInputStream obInS = new ObjectInputStream(fInS);
            //cast into ArrayList
            pastryArray = (ArrayList<Pastry>)obInS.readObject();
            //System.out.println("Loaded Pastry instances...");
            obInS.close();
            fInS.close();

        }  
        catch (IOException e) {
            System.out.println("No previous files available...");
        }
        try {
            System.out.println("Loading Customers...");
            FileInputStream fInC = new FileInputStream("custData.dat");
            ObjectInputStream obInC = new ObjectInputStream(fInC);
            //cast into ArrayList
            custArray = (ArrayList<Customer>)obInC.readObject();
            //System.out.println("Loaded Customer instances...");
            obInC.close();
            fInC.close();

        }  
        catch (IOException e) {
            System.out.println("No previous files available...");
        }
        try {
            //System.out.println("Loading Milk...");
            FileInputStream fInM = new FileInputStream("milkData.dat");
            ObjectInputStream obInM = new ObjectInputStream(fInM);
            //cast into ArrayList
            milkCoffeeShop = (Milk)obInM.readObject();
            //System.out.println("Loaded Milk instances...");
            obInM.close();
            fInM.close();

        }  
        catch (IOException e) {
            System.out.println("No previous files available...");
        }
        
        try {
            //System.out.println("Loading Espresso...");
            FileInputStream fInE = new FileInputStream("espressoData.dat");
            ObjectInputStream obInE = new ObjectInputStream(fInE);
            //cast into ArrayList
            espressoCoffeeShop = (Espresso)obInE.readObject();
            //System.out.println("Loaded Customer instances...");
            obInE.close();
            fInE.close();

        }  
        catch (IOException e) {
            System.out.println("No previous files available...");
        }
        
    } // end load
} // end CoffeeShop class