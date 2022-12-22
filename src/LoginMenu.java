import java.util.*;
import java.io.*;

public class LoginMenu implements Serializable{
    
    Scanner input = new Scanner(System.in);
    boolean isOwner = false;
    boolean isValidUser = false;
    // variables to store the user's inputs
    public String userInput;
    public String passInput;
    // variable to store the current customer number in the ArrayList
   public int currentCustomerIndex;
    
    public LoginMenu() {
       
    } // end constructor

    public void loginMenu(ArrayList<Customer> custArray) {
        try {
            // printing a basic login menu and taking in input of username and password
            System.out.println("---------------------------------");
            System.out.println("*** Coffee Shop Login ***");
            System.out.println("---------------------------------");
            System.out.print("\nEnter your username: ");
            userInput = input.nextLine();
        
            System.out.print("Enter your password: ");
            passInput = input.nextLine();

            verifyUser(custArray);
        }
        catch(Exception e) {
            System.out.print("Incorrect input.");
        }
    } // end loginMenu

    public void verifyUser(ArrayList<Customer> custArray) {

        Owner owner = new Owner();
        // if username and password match those of the owner
        if (userInput.equals(owner.getUsername()) && passInput.equals(owner.getPassword())) {

            System.out.println("Welcome back to your shop, Owner!"); 
            isOwner = true;
            isValidUser = false;
            
        } // end if

        // for loop to go through the Customer array and check if the credentials entered match the values in the array
        for(int i = 0; i < custArray.size(); i++) {
            if (userInput.equals(custArray.get(i).getUsername()) && passInput.equals(custArray.get(i).getPassword())) {
                // sets the current customer number as a variable
                currentCustomerIndex = i;

                System.out.println("");
                System.out.println("Welcome back " + custArray.get(i).getName() + "!");
                isValidUser = true;
                isOwner = false;
            }
        }

        // if input does not equal both a valid customer and owner
        if(!isValidUser && !isOwner) {
            System.out.println("\nNot a valid user, please register below!");
            register(custArray);
        
        }

    } // end verifyUser

    public void register(ArrayList<Customer> custArray) {
        try {
            System.out.println("---------------------------------");
            System.out.println("*** Create a Login ***");
            System.out.println("---------------------------------");
            System.out.print("\nWhat's your full name?: ");
            String fName = input.nextLine();
            System.out.print("Please enter a username: ");
            String username = input.nextLine();
            System.out.print("Please enter a password: ");
            String password = input.nextLine();
            
            // sets the new customer data into the array of Customer objects
            Customer newCust = new Customer();
            newCust.setName(fName);
            newCust.setPassword(password);
            newCust.setUsername(username);
            custArray.add(newCust);
            System.out.println("\nSuccessfully created a new account...");
            System.out.println("\nNow you will be sent to login with your username and password.");

            //call login after registering 
            loginMenu(custArray);
        }
        catch(Exception e) {
            System.out.println("Incorrect input.");
        }
    } // end register 

} // end LoginMenu class